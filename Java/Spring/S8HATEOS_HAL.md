# HATEOAS

https://www.udemy.com/course/microservices-with-spring-boot-and-spring-cloud/learn/lecture/33578746#content

Hypermedia as the Engine of Application State

1. Enahance REST API to tell consumers about how to perform subsequent actions
2. HAL ( JSON Hypertext Application Language ) : Simple format that gives a consistent and easy to hyperlink between resrouces in your API

### Dependency 
```groovy
implementation 'org.springframework.boot:spring-boot-starter-hateoas'
```

1. Lets Define a few things. User POJO Class

```java
public class User {
	private Integer id;
	private String name;
	private LocalDate birthday;
    // Constructor,getters,setters..!
}
```

2. Controller - Just imagine a Service method which returns a user object.
```java
@GetMapping("/users/{id}")
public User findUserById(@PathVariable("id") int id) {
	User user =  daoService.findUserById(id);
	return user;
}
```

3. Curl will produce the below result

```bash
$ curl 'http://localhost:8080/users/1'
{"id":1,"name":"Adam","birthday":"1993-12-19"}
```

4. Cool , now what we will try to do as part of HATEOS is to return a link which displays a list of users
5. We will make use of entity model. Lets modify the controller

```java

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;

@GetMapping(path = "/users")
public List<User> findAll() {
	return daoService.findAll();
}

// Changes are below
@GetMapping("/users/{id}")
public EntityModel<User> findUserById(@PathVariable("id") int id) {
	User user =  daoService.findUserById(id);
	EntityModel<User> entityModel = EntityModel.of(user);
	WebMvcLinkBuilder builder = linkTo(methodOn(this.getClass()).findAll()); // Refererecne to findAll() method
	return entityModel.add(builder.withRel("all-users"));
}	
```

6. Lets Curl it

```bash
$ curl 'http://localhost:8080/users/1'
{"id":1,"name":"Adam","birthday":"1993-12-19","_links":{"all-users":{"href":"http://localhost:8080/users"}}}
```

7. Here we notify the user that we have a endpoint through though which the user can check all users present.

# HAL Explorer

An API Explorer for HAL

### Dependency

```groovy
 implementation 'org.springframework.data:spring-data-rest-hal-explorer'
```
Note : The example is built upon the above mentioned HATEOS example.

Navigate to http://localhost:8080/ and obersve the HAL explorer.

