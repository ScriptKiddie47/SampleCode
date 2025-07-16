# OAuth

1. Pattern : https://auth0.com/docs/get-started/authentication-and-authorization-flow/client-credentials-flow

## Client Credentials Flow

1. Application sends application's credentials to the Auth0 Authorization Server. To learn more about client authentication methods, read Application Credentials.
1. Auth0 Authorization Server validates application's credentials.
1. Auth0 Authorization Server responds with an access token.
1. Application can use the access token to call an API on behalf of itself. For more information on this process, see Validate JSON Web Tokens.
1. API responds with requested data.

### Client Credentials Flow - Implementation 

1. Source : https://auth0.com/docs/get-started/authentication-and-authorization-flow/client-credentials-flow/call-your-api-using-the-client-credentials-flow
1. Create an API : https://auth0.com/docs/get-started/auth0-overview/set-up-apis
1. Test it 

    ```ps
    syndicate  curl --request POST \
    --url https://xxxxxxxxxxxxxxxxx/oauth/token \
    --header 'content-type: application/x-www-form-urlencoded' \
    --data grant_type=client_credentials \
    --data audience=car-order-service \
    --data client_id=xxxxxxxxx \
    --data client_secret=xxxxxxxxxxx
    {"access_token":"xxxxxxxxxxxxxxxx","expires_in":86400,"token_type":"Bearer"}%  
    ```

1. Cool. Now Lets write the Java Code Referencing : https://auth0.com/docs/quickstart/backend/java-spring-security5/interactive
1. Dependencies 

    ```gradle
    plugins {
        id 'java'
        id 'org.springframework.boot' version '3.5.3'
        id 'io.spring.dependency-management' version '1.1.7'
    }
    dependencies {
        implementation 'org.springframework.boot:spring-boot-starter-web'
        implementation 'com.okta.spring:okta-spring-boot-starter:3.0.5'
    }
    ```

1. Application Yaml

    ```yaml
    okta:
    oauth2:
        issuer: https://dev-gnxwtf02tybfe6he.us.auth0.com/
        audience: car-order-service
    ```

1. Controller

    ```java
    @RestController
    @RequestMapping(path = "api", produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin(origins = "*")
    public class CarOrderOktaController {
        @GetMapping(value = "/public")
        public Message publicEndpoint() {
            return new Message("All good. You DO NOT need to be authenticated to call /api/public.");
        }

        @GetMapping(value = "/private")
        public Message privateEndpoint() {
            return new Message("All good. You can see this because you are Authenticated.");
        }

        @GetMapping(value = "/private-scoped")
        public Message privateScopedEndpoint() {
            return new Message(
                    "All good. You can see this because you are Authenticated with a Token granted the 'read:messages' scope");
        }

        record Message(String quote) {
        }
    }
    ```

1. Security Config 

    ```java
    import static org.springframework.security.config.Customizer.withDefaults;

    @Configuration
    public class SecurityConfig {
        @Bean
        public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
            return http
                    .authorizeHttpRequests((authorize) -> authorize
                            .requestMatchers("/api/public").permitAll()
                            .requestMatchers("/api/private").authenticated()
                            .requestMatchers("/api/private-scoped").hasAuthority("SCOPE_read:messages"))
                    .cors(withDefaults())
                    .oauth2ResourceServer(oauth2 -> oauth2
                            .jwt(withDefaults()))
                    .build();
        }
    }
    ```

1. Lets Invoke the endpoints. Note: The scope thing is not working. Not sure why

    ```ps
    syndicate  curl --request GET \
    --url http://localhost:8080/api/private \
    --header 'authorization: Bearer xxxxxxxxxxxxxxxxx'
    {"quote":"All good. You can see this because you are Authenticated."}
    ```