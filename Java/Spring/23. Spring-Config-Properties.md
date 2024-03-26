# Spring Boot Configuration Properties

1. Source: https://github.com/danvega/list-map-props
1. Lets do the boilerplate
1. Out Classes / Records

```java
public record SocialMediaAccount (String name, String username,String url){}
@ConfigurationProperties("social.media")
public record SocialMediaConfigProperties (List<SocialMediaAccount> accounts){}
```
1. In main class , we need to define the property  `@EnableConfigurationProperties` and pass in the class name

```java
@EnableConfigurationProperties(value = SocialMediaConfigProperties.class)
@SpringBootApplication
public class SpringPropTestApplication {
```
## Lists

1. Lets define a list
2. `application.yaml`

```yaml
social:
  media:
    accounts:
      -   name: Twitter
          url: https://twitter.com/syndicate
          username: therealsyndicate
      -   name: YouTube
          url: https://www.youtube.com/@syndicate
          username: syndicate1
```

3. Java Code to read it

```java
@Component
public class AppRunner implements CommandLineRunner {
	private static final Logger log = LoggerFactory.getLogger(AppRunner.class);
	@Autowired
	private SocialMediaConfigProperties configProperties;
	@Override
	public void run(String... args) throws Exception {
		log.info("list size : {}", configProperties.accounts().size()); // 2
		log.info("Name : {}", configProperties.accounts().get(0).name()); // twitter
	}
}
```

## Maps

1. We will do the whole thing in one box. Pretty straight

```java
public record Website(String url) {}

@ConfigurationProperties("bookmarks")
public record BookMarksProperties(Map<String, Website> websites) {}

@EnableConfigurationProperties(value = { SocialMediaConfigProperties.class, BookMarksProperties.class })
@SpringBootApplication
public class SpringPropTestApplication {

bookmarks:
  websites:
    dan-vega: https://www.danvega.dev
    spring-academy: https://springacademy.dev
    spring-io: https://springio.dev
    spring-one: https://springone.io

@Component
public class AppRunner implements CommandLineRunner {
	private static final Logger log = LoggerFactory.getLogger(AppRunner.class);
	@Autowired
	private BookMarksProperties bookMarksProperties;
	@Override
	public void run(String... args) throws Exception {
		log.info("spring-io-bookmark : {}", bookMarksProperties.websites().get("spring-io").url()); // spring-io-bookmark : https://springio.dev
	}
}
```

# Alternative 

## Read application properties into Java Code - Simple

```yaml
mongodev:
  username: user-write-access-x1
```

```java
// Must be defined at class level not function
@Value("${mongodev.username}")
private String mongoUsername;
```

### So what if we want to take the values out of our app properties file

#### Case 1
1. IDE / Console arguments

```yaml
geopsace:
  api-username: "user7833"
  api-password: ${geopsace.api-password}
```

#### Case 2 
1. Passwing password using STS - Program Arguments in Arguments tab


```bash
--geopsace.api-password="super-secret-eclipse"
```

#### Case 3

1. Gralde Boot Run :

```gradle
./gradlew bootRun --args='--geopsace.api-password=super-secret-eclipse'
```

#### Case 4 - spring-config-import

1. Separate file 
1. application.yaml
```yaml
spring:
  config:
    import: optional:secrets.yaml
```

1. secrets.yaml

```yaml
geopsace:
  api-password: "password-from-secrets.yaml"
```

1. gitingore - add line - `secrets.yaml`