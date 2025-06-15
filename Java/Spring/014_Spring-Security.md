# Spring security

## How it works


![plot](Images/image6_spring_security.png)


### Gradle Dependency
```groovy
implementation 'org.springframework.boot:spring-boot-starter-security'
testImplementation 'org.springframework.security:spring-security-test'
```
## What happens once we pull those 2 jars

1. Once we install the above depepencies we arrive at a `login page` for every operation we do with the rest api

```bash
$ curl -i http://localhost:8080/users/1/posts
HTTP/1.1 401 
Vary: Origin
Vary: Access-Control-Request-Method
```

Peeking into the logs reveal 
```log
Using generated security password: 0601166d-ff59-4561-8c80-2629474f40f0
This generated password is for development use only. Your security configuration must be updated before running your application in production.
```
2. So the username is `user` & the password is above : 0601166d-ff59-4561-8c80-2629474f40f0 in the browser
3. We see our API working in the browser, GET requests
4. What about CURL or POSTMAN ?

```bash
curl --request GET 'http://localhost:8080/users' \
--header 'Content-Type: application/json' \
--header 'Authorization: Basic dXNlcjowNjAxMTY2ZC1mZjU5LTQ1NjEtOGM4MC0yNjI5NDc0ZjQwZjA=' \
--data '{
    "description" : "I want to learn docker fundamentals"
}'
```

5. We have to do `basic auth` and base encode the auth header. Postman certainly makes it easier.
6. If we start/restart app again we get a different password. So lets configure in properties

```yaml
spring:
  security:
    user:
      name: username
      password: password

```
8. Once we do this , password won't be generated in the logs. So we need to use the above.
1. So we customize the filter chain. In short we overwrite it.

1. Create a config class and bean method

    ```java
    import org.springframework.context.annotation.Bean;
    import org.springframework.context.annotation.Configuration;
    import org.springframework.security.config.annotation.web.builders.HttpSecurity;
    import org.springframework.security.web.SecurityFilterChain;

    @Configuration
    public class SpringSecurityConfiguration {
        
        @Bean
        SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
            return http.build();
        }
    }
    ```

1. At this stage we have disabled, everything so everything works without auth.

    ```bash
    $ curl http://localhost:8080/users
    [{"id":1,"name":"John","birthday":"1999-12-18"}]
    ```
1. But we shouldn't do that , so lets code

    ```java
    import org.springframework.context.annotation.Bean;
    import org.springframework.context.annotation.Configuration;
    import org.springframework.security.config.Customizer;
    import org.springframework.security.config.annotation.web.builders.HttpSecurity;
    import org.springframework.security.web.SecurityFilterChain;

    @Configuration
    public class SpringSecurityConfiguration {

        @Bean
        SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
            // 1. All request should be authenticateed
            http.authorizeHttpRequests(auth -> auth.anyRequest().authenticated());
            // 2. Setup basic auth ( pop up instead of webpage )
            http.httpBasic(Customizer.withDefaults());
            // 3. Disable CSRF
            http.csrf(t -> t.disable());
            return http.build();
        }
    }
    ```
1. Now most of the ops work.



## CSRF

1. You are logged-in to your bank website
    - A cookie `cookie-A` is saved in your web browser
1. You go to a malicious website without logging out
1. Malicious website executes a bank transfer without your knowledge using `cookie-A` 

1. Suprisingly the POST request doesn't work & we get a 401.
1. Spring security intercepts the request and runs a series of `Filter Chains`.
    - All request should be authenticated
    - If not webpage is shown
    - `CSRF` Impacts POST,PUT

1. For Webpages tools like thymeleaf is already included.

    ![plot](Images/image7_spring_security.png)

1. So what can we do for rest api calls ?
1. For now lets fetch the token

    ```java
    @GetMapping("/csrf-token")
    public CsrfToken retrieveCsrfToken(HttpServletRequest httpServletRequest) {
        return (CsrfToken) httpServletRequest.getAttribute("_csrf");
    }


    {
        "token": "eVsuanzzIB__EdhTfj8047adGRjAm4OlQXy_EGtFwo8tb7MHQD4ZCUzLFi7SdbkwSRIAhoekNHqir-WIcxmKdlp28LkdWoo0",
        "headerName": "X-CSRF-TOKEN",
        "parameterName": "_csrf"
    }
    ```
1. Copy the token and include it as a header for post request `X-CSRF-TOKEN:eVsuanzzIB__EdhTfj8047adGRjAm4OlQXy_EGtFwo8tb7MHQD4ZCUzLFi7SdbkwSRIAhoekNHqir-WIcxmKdlp28LkdWoo0`

1. Now we see our post request succeeds ! 
1. If we have a stateless app ( no sessions ), we can disable csrf.
1. We can also do samesite cookie as a protection against CSRF.


### Disable CSRF - stateless app

1. This is more detailed that previously shown above

```java
@Configuration
public class BasiAuthSecurityConfiguration {
	
	@Bean
	SecurityFilterChain SecurityFilterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests((requests) -> requests
				.anyRequest()
				.authenticated());
		http.sessionManagement(session -> session
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
//		http.formLogin(withDefaults());
		http.httpBasic(withDefaults());
		http.csrf(t -> t.disable());
		return http.build();
	}
}
```

1. With the above code we can disabled - CSRF,FORM Login ( we get a pop up in he browser now for basic auth )


# CORS - Cross Origin Resource Sharing

1. Browsers do not allow AJAX calls to resources outside current origin
1. Two configuration
    - Global Configuration
    - Local COnfiguration - Controller class level annotation - @CrossOrigin

# Storing USER CREDS

1. In Memory - For Test Purpose
1. DB - We can use JDBC/JPA to access the creds
1. LDAP - Lightweight Directory Access Protocol.
    -    Open protocol for directory service and authentication

### In Memory

1. Lets have multiple users in memory. First we need to remove the username & password present in the application.properies

```java
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class BasiAuthSecurityConfiguration {
	
	@Bean
	SecurityFilterChain SecurityFilterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests((requests) -> requests
				.anyRequest()
				.authenticated());
		http.sessionManagement(session -> session
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
//		http.formLogin(withDefaults());
		http.httpBasic(withDefaults());
		http.csrf(t -> t.disable());
		return http.build();
	}
	
	@Bean
	public UserDetailsService userDetailsService() {
		var admin = User.withUsername("admin").password("{noop}admin").roles("admin").build();
		var intern = User.withUsername("intern").password("{noop}intern").roles("user").build();
		return new InMemoryUserDetailsManager(admin,intern);
	}
}
```
```bash
$ curl --location 'http://localhost:8080/todos' --header 'Authorization: Basic aW50ZXJuOmludGVybg=='
[{"username":"bala","description":"Learn AWS"},{"username":"bala","description":"Get AWS Certified"},{"username":"john","description":"Get Azure Certified"}]
```



1. `password("{noop}admin")` - noop here signifies no encoding


### Store Creds in DB

1. If we try to open H2 console. First we get a popup asking for our basic auth which we can fetch and provide form above. 
1.But proceeding further providing H2 creds , we see an unfamiliar screen.

   ![plot](Images/image8_spring_security.png)

1. This is because of HTML Frames & by default spring security disable frames. so what we need to is enable frames - `http.headers(h -> h.frameOptions(f -> f.sameOrigin()));`

1. Now we see our DB.
1. Cool , moving on. We already have a DDL user schema.

```java
public class JdbcDaoImpl extends JdbcDaoSupport implements UserDetailsService, MessageSourceAware {
    public static final String DEFAULT_USER_SCHEMA_DDL_LOCATION = "org/springframework/security/core/userdetails/jdbc/users.ddl";
```

1. We can see the file in the same package...! 

```java
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;
@Configuration
public class BasiAuthSecurityConfiguration {
    @Bean
    public DataSource dataSource() {
    	return new EmbeddedDatabaseBuilder()
    			.setType(EmbeddedDatabaseType.H2)
    			.addScript(JdbcDaoImpl.DEFAULT_USER_SCHEMA_DDL_LOCATION)
    			.build();
    }
```

1. We can now see the table present in H2-Console.
1. Lets crete users and insert it into DB.

```java
@Configuration
public class BasiAuthSecurityConfiguration {
    @Bean
    public UserDetailsService userDetailsService(DataSource dataSource) {
    	var admin = User.withUsername("admin").password("{noop}admin").roles("admin").build();
    	var intern = User.withUsername("intern").password("{noop}intern").roles("user").build();
    	var jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);
    	jdbcUserDetailsManager.createUser(admin);
    	jdbcUserDetailsManager.createUser(intern);
    	return jdbcUserDetailsManager;
    }
```


   ![plot](Images/image9_spring_security.png)


1. There is also authorities which is kinda similar.
1. The same old curl request just works.

1. Howeve we do observe that the password in stored in an unencryted way. Lets fix it.

## Encoding vs Hashing vs Encryption

1. Encoding is to transform data to one form to another
    - Does not use a key or password
    - is reversible
    - Typically not used for securing data
    - Usecase : Compression,Streaming Data
    - Example : Base64,Wave,MP3

1. Hashing : Convert Data into Hash ( String )
    - One way process
    - Not reverisble
        -  Cannot get the original data back
    - Usecase : Validate integrity of data
    - Example : bcrypt,scrypt

1. Encryption : Encoding data using a key or password
    - You need a key or password to decrypt
    - RSA

1. The proccess we will follow is to store the hashkey in DB and re-check the password & checking it matches with stored DB Hash
1. Hashing algorithm like SHA-256 are no longer secure because modern system can perform billion of hash calculation in a second
1. Recommended : Use adaptive one way functions with Work factor of 1 second.
    - Work Factor : How much time it takes to verify a password in our system. In our case its 1 second.
    - Example : bcrypt,scrypt,argon2
1. Interface for performing one way transformation of a password - `PasswordEncoder`
1. 

```java
@Configuration
public class BasiAuthSecurityConfiguration {
	@Bean
	BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
    @Bean
    UserDetailsService userDetailsService(DataSource dataSource) {
		var admin = User.withUsername("admin")
//				.password("{noop}admin")
				.password("admin")
				.passwordEncoder(str -> bCryptPasswordEncoder().encode(str))
				.roles("admin")
				.build();
		var intern = User.withUsername("intern")
				.password("intern")
				.passwordEncoder(str -> bCryptPasswordEncoder().encode(str))
				.roles("user")
				.build();
		var jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);
		jdbcUserDetailsManager.createUser(admin);
		jdbcUserDetailsManager.createUser(intern);
		return jdbcUserDetailsManager;
	}
```
In DB :

USERNAME  	PASSWORD  	ENABLED  
admin	$2a$10$jvgDLbPYrk7l1KevCo96z.kczKESf6GlPBpCzEod7mjrEU1dQEyRu	TRUE
intern	$2a$10$PKLiFZiS.NUgDkQlBZIrcemg3.pmLB4qUYdCNYoEACD/7tohuOeY6	TRUE

# JWT Auth


1. Website : https://jwt.io/
1. Basic Auth Issues
    - No exiprationTime
    - No user details
    - Easily Decoded

1. What does JWT contain ?
1. Header
    - Type JWT
    - Hashing Algorithm
1. Payload
    - Standart Attributes
        - issues
        - subject
        - audience
        - expiry date
        - iat - When was token issues
1. Signature 
    - Includes a secret.

1. Symmetric Encryption algorithms use the same key for encryption & decryption
1. Asymmetric Encryption
    - Two Keys : Public key & Private Key
    - Also called Public key Cryptography
    - Encrypt data with public key and decrypt with Private key
    - Share public key with the 'public' duh!

1. High Level Overview

    ![plot](Images/image10_spring_security.png)


1. Implementaiton will require quite a few steps
    - Create Key Pair
    - Create RSA key object using key pair
    - Create JWKSource
    - Use RSA Public key for Decoding
    - Use JWKSource for encoding


```gradle
implementation 'org.springframework.boot:spring-boot-starter-oauth2-resource-server'
```