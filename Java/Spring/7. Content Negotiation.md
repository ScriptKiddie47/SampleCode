# Content Negotiation

1. Same Resource - Same URI 
2. Different COntext type - XML or JSON
3. Different Language - English / Dutch
4. This is call done through 'accept-header' or 'accept-language-header'

## Add XML's representation with Spring Boot
```groovy
implementation 'com.fasterxml.jackson.dataformat:jackson-dataformat-xml:2.15.3'
```

### Note : The Jackson version should match with the Jackson jars present in the project.

## Controller

1. No additional Code changes are required in the controller

```java
@GetMapping(path = "/users")
public List<User> findAll() {
	return daoService.findAll();
}
```
2. Add 'Accept: application/xml' header
```bash
$ curl 'http://localhost:8080/users' \
--header 'Accept: application/xml'
<List><item><id>1</id><name>Adam</name><birthday>1993-12-18</birthday></item><item><id>2</id><name>Eve</name><birthday>1998-12-18</birthday></item><item><id>3</id><name>Jim</name><birthday>2003-12-18</birthday></item></List>
```

3. Removing 'Accept: application/xml' will default to JSON.

# Internationalization - i18n

1. Header - Accept-Language
2. en - English , nl - Dutch , fr - French
3. Create a file '/rest-web-service/src/main/resources/messages.properties'
```
good.morning.message=Good Morning
```
4. Create a file '/rest-web-service/src/main/resources/messages_nl.properties'
```
good.morning.message=Goedemoergen
```

5. Controller

```java
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

@RestController
public class UserController {

	private MessageSource messageSource;

	public UserController(MessageSource messageSource) {
		this.messageSource = messageSource;
	}
	
	@GetMapping("/greeting")
	public String helloWorld() {
		Locale locale = LocaleContextHolder.getLocale(); // If no local provided , default locale will be used
		return messageSource.getMessage("good.morning.message", null, "Default Message", locale);
	}
}
```

6. We just need to switch the Language Header

```bash
$ curl 'http://localhost:8080/greeting' --header 'Accept-Language: en'
Good Morning
$ curl 'http://localhost:8080/greeting' --header 'Accept-Language: nl'
Goedemoergen
```