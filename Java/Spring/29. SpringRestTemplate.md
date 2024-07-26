# Spring RestTemplate

1. This is feature complete. Spring recommends `rest-client` instead.

### Java Code

```java
record Todo(Integer userId,Integer id,String title,String completed) { }

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.List;
@Service
public class TodoClient {
    private final RestTemplate restTemplate;
    public TodoClient(RestTemplateBuilder builder) {
        this.restTemplate = builder.build();
    }
    public List<Todo> findAll() {
        return restTemplate.exchange("https://jsonplaceholder.typicode.com/todos",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Todo>>() {
                }).getBody();
    }
}
```

### Unit Test

```java
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TodoClientTest {
    @Autowired
    private TodoClient todoClient;
    @Test
    void testFindAll(){
        List<Todo> todos = todoClient.findAll();
        assertEquals(200,todos.size());
    }
}
```
