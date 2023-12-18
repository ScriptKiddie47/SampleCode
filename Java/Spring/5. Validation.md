# Validation

Suppose we want to stop a user creation where name is missing. We can utilize spring validation.

#### Starter Validation 

```groovy
implementation 'org.springframework.boot:spring-boot-starter-validation'
```

1. So when we use the @Valid annotation , when the data binding happens. The validation defined in the objects are auto-invoked

2. Java Model Class

```java
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

public class User {
	
	private Integer id;
	@Size(min = 2,message = "Name should have atleast 2 characters") // Minimum of 2 characters in the name
	private String name;
	@Past(message = "Birth Date should be in the past") // BirthDate should be in the past
	private LocalDate birthday;
```

3. Controller

```java
import jakarta.validation.Valid;

@PostMapping("/users")
public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
	User createdUser = daoService.save(user);
	URI location = ServletUriComponentsBuilder
			.fromCurrentRequest().path("/{id}")
			.buildAndExpand(createdUser.getId()).toUri();
	return ResponseEntity.created(location ).build();
}
```

4. We need to OVERWRITE handleMethodArgumentNotValid in ResponseEntityExceptionHandler

```java
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler{
	.
    .
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		// ex.getFieldError().getDefaultMessage() - Customization performed to send the first error back
		// But we can loop through as well and capture all the errors messages
		List<String> errList = new ArrayList<>();
		for (FieldError fieldError : ex.getFieldErrors()) {
			errList.add(fieldError.getDefaultMessage());
		}	
		ErrorDetails details = new ErrorDetails(LocalDate.now(), errList.toString(), request.getDescription(false));
		return new ResponseEntity<>(details,HttpStatus.BAD_REQUEST);
	}
}
```

5. Lets Curl it

```bash
$ curl -i 'http://localhost:8080/users' \
--header 'Content-Type: application/json' \
--data '{
    "name": "",
    "birthday": "2025-12-18"
}'
HTTP/1.1 400 
Content-Type: application/json
Transfer-Encoding: chunked
Date: Mon, 18 Dec 2023 14:00:39 GMT
Connection: close

{"timestamp":"2023-12-18","message":"[Birth Date should be in the past, Name should have atleast 2 characters]","details":"uri=/users"}
```