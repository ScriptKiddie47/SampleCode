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

# Validation with @RestControllerAdvice

1. Controller

	```java
	@GetMapping(path = "/validate",produces = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> validate(
		@RequestParam(value = "productCd") @NotEmpty(message = "Product Code must not be empty") String prdCd,
		@RequestParam(value = "ruleName") @NotEmpty(message = "Rule Name cannot be empty") String rName
		
		){
		LOG.info("prdCd : {}",prdCd);
		return ResponseEntity.ok("validated");
	}
	```

1. RestControllerAdvice Class

	```java
	package com.scripter.spring.hello.controller;

	import java.util.LinkedHashMap;
	import java.util.List;
	import java.util.Map;

	import org.slf4j.Logger;
	import org.slf4j.LoggerFactory;
	import org.springframework.http.HttpStatus;
	import org.springframework.http.ResponseEntity;
	import org.springframework.web.bind.MissingServletRequestParameterException;
	import org.springframework.web.bind.annotation.ExceptionHandler;
	import org.springframework.web.bind.annotation.ResponseStatus;
	import org.springframework.web.bind.annotation.RestControllerAdvice;
	import org.springframework.web.method.annotation.HandlerMethodValidationException;


	@RestControllerAdvice
	public class HelloExceptionController {

		private static final Logger LOG = LoggerFactory.getLogger(HelloExceptionController.class);

		@ResponseStatus(code = HttpStatus.BAD_REQUEST)
		@ExceptionHandler(exception = MissingServletRequestParameterException.class)
		public ResponseEntity<Map<String, Object>> handleMissingRequestParam(MissingServletRequestParameterException ex) {

			LOG.error("Missing request parameter: {}", ex.getParameterName());

			Map<String, Object> error = new LinkedHashMap<>();
			error.put("status", HttpStatus.BAD_REQUEST.value());
			error.put("error", "Missing Request Parameter");

			Map<String, String> details = new LinkedHashMap<>();
			details.put("parameter", ex.getParameterName());
			details.put("expectedType", ex.getParameterType());
			details.put("message", String.format("Required parameter '%s' is missing", ex.getParameterName()));

			error.put("details", details);

			return ResponseEntity.badRequest().body(error);
		}

		@ExceptionHandler(HandlerMethodValidationException.class)
		public ResponseEntity<Map<String, Object>> handleValidationException(HandlerMethodValidationException ex) {

			// Log it for debugging
			LOG.error("Validation failed: {}", ex.getParameterValidationResults());

			// Build a readable error response
			List<Map<String, String>> errors = ex.getParameterValidationResults().stream()
					.flatMap(result -> result.getResolvableErrors().stream())
					.map(error -> {
						Map<String, String> err = new LinkedHashMap<>();
						err.put("param", error.getCodes() != null ? error.getCodes()[0] : "unknown");
						err.put("message", error.getDefaultMessage());
						return err;
					})
					.toList();

			Map<String, Object> body = new LinkedHashMap<>();
			body.put("status", HttpStatus.BAD_REQUEST.value());
			body.put("error", "Validation failed");
			body.put("details", errors);

			return ResponseEntity.badRequest().body(body);
		}
	}
	```
1. Curl

	```ps
	$ curl --request GET \
	--url http://localhost:8080/validate
	{"status":400,"error":"Missing Request Parameter","details":{"parameter":"productCd","expectedType":"String","message":"Required parameter 'productCd' is missing"}}

	$ curl --request GET \
	--url 'http://localhost:8080/validate?productCd=&ruleName='
	{"status":400,"error":"Validation failed","details":[{"param":"NotEmpty.helloController#validate.prdCd","message":"Product Code must not be empty"},{"param":"NotEmpty.helloController#validate.rName","message":"Rule Name cannot be empty"}]}
	```
