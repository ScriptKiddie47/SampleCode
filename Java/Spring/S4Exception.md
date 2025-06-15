# Exception

1. Suppose we tried to find a user and user was not present. Lets return a 404

## 1. Simple Exception Handling for Controller

#### Controller

```java
@GetMapping("/users/{id}")
public User findUserById(@PathVariable("id") int id) {
	User user =  daoService.findUserById(id);
	if(user == null) {
		throw new UserNotFoundException("id:" + id);
	}
	return user;
}
```
#### Exception Class
```java
@ResponseStatus(code = HttpStatus.NOT_FOUND) // Determines the HTTP status code being sent back
public class UserNotFoundException extends RuntimeException {
	public UserNotFoundException(String message) {
		super(message);
	}
}
```

#### Response back to client 
```bash
$ curl -i http://localhost:8080/users/10
HTTP/1.1 404 
Content-Type: application/json
Transfer-Encoding: chunked
Date: Mon, 18 Dec 2023 12:01:59 GMT

{"timestamp":"2023-12-18T12:01:59.376+00:00","status":404,"error":"Not Found","path":"/users/10"}
```
#### Note

When are working with Spring Dev-tools , we see a lot of Additional Stack trace in Error Scenarios

## 2. Complex Exception Handling

1. Create Error Model Class
Note: See how it mimcs the Error JSON response above

```java
public class ErrorDetails {
	private LocalDate timestamp;
	private String message;
	private String details;
    .
    //Constructors and Getters/Setters
```
2. Standard class 'ResponseEntityExceptionHandler' which handles all spring raised expection and it returns formatted error details
3. So what we will do here is re-implement (not overwrite) the handleException method in ResponseEntityExceptionHandler
4. @ControllerAdvice - Makes it applicable to all controllers in the project

```java
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler{
	
	// @ExceptionHandler - Define what exception we want to handle.
	// @ExceptionHandler(Exception.class) - This means we handle all exception
	
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<ErrorDetails> handleAllException(Exception ex, WebRequest request) throws Exception {
		ErrorDetails details = new ErrorDetails(LocalDate.now(), ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<>(details,HttpStatus.INTERNAL_SERVER_ERROR);
	}

    @ExceptionHandler(UserNotFoundException.class)
	public final ResponseEntity<ErrorDetails> handleUserNotFoundException(Exception ex, WebRequest request) throws Exception {
		ErrorDetails details = new ErrorDetails(LocalDate.now(), ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<>(details,HttpStatus.BAD_REQUEST);
	}
}

```

5. And Now we see a change in the repsonse structure of the error. The strucure is defined in class - ErrorDetails. For UserNotFoundException we observe a 400. However for other exception we will see a 500

```bash
$ curl -i http://localhost:8080/users/10
HTTP/1.1 400 
Content-Type: application/json
Transfer-Encoding: chunked
Date: Mon, 18 Dec 2023 13:13:56 GMT
Connection: close

{"timestamp":"2023-12-18","message":"id:10","details":"uri=/users/10"}
```


