# Rest Client

1. Introduced in Spring Boot 3.2
2. Successor of Rest Template
3. There is mechanism to swap the underlying HTTP mechanism that REST CLIENT utilizes
4. If we don't want to use the default one & instead rely on JDK HTTP Client. We can do that too.

## Let Wire it in 

1. We are going to use a free REST API - https://jsonplaceholder.typicode.com/
2. Lets define the controller

```java
@RestController
@RequestMapping("/api/posts")
public class PostController {
	private final PostService postService;
	public PostController(PostService postService) {
		super();
		this.postService = postService;
	}
	@GetMapping("")
	public List<Post> finalAll() {
		return postService.findAll();
	}
	@GetMapping("/{id}")
	public Post findById(@PathVariable("id") Integer id) {
		return postService.findById(id);
	}
	@PostMapping
	public Post create(@RequestBody Post post) {
		return postService.create(post);
	}
	@PutMapping("/{id}")
	public Post update(@PathVariable("id") Integer id, @RequestBody Post post) {
		return postService.update(id, post);
	}
	@DeleteMapping("/{id}")
	public void delete(@PathVariable("id") Integer id) {
		postService.delete(id);
	}
}
```
3. POJO Class
```java
public record Post(Integer userId, Integer id,String title,String body) {}
```
4. Configuration to invoke the Rest Client 
```java
@Configuration
public class PostConfiguration {
	@Bean
	public RestClient restClient() {
		return RestClient
				.builder()
				.baseUrl("https://jsonplaceholder.typicode.com")
				.build();
	}
}
```
5. Cool , now lets make the calls.

## GET call - returns a list of json

```java
@Service
public class PostService {
	private final RestClient restClient;
	public PostService(RestClient restClient) {
		super();
		this.restClient = restClient;
	}
	public List<Post> findAll() {	
		return restClient
				.get()
				.uri("/posts")
				.retrieve()
				.body(new ParameterizedTypeReference<List<Post>>(){});
	}
}
```

Invocation Curl : 
```bash
$ curl 'http://localhost:8080/api/posts'
[{"userId":1,"id":1,"title":"sunt aut facere r......
```

## GET call - return a object json + URI value passed

```java
public Post findById(Integer id) {
	return restClient
			.get()
			.uri("/posts/{id}",id)
			.retrieve()
			.body(Post.class);
}
```

CURL : 

```bash
$ curl 'http://localhost:8080/api/posts/2'
{"userId":1,"id":2,"title":"qui est esse","body":".............
```

## POST Request

```java
public Post create(Post post) {
	return restClient
			.post()
			.uri("/posts")
			.contentType(MediaType.APPLICATION_JSON)
			.body(post)
			.retrieve()
			.body(Post.class);
}
```

CURL : 

```bash
sbala@sbala-Nitro-AN515-52 ~ 
$ curl --location 'http://localhost:8080/api/posts' \
--header 'Content-Type: application/json' \
--data '{
    "userId": 998,
    "id": 999,
    "title": "DataSet1",
    "body": "RamRajya"
}'
{"userId":998,"id":101,"title":"DataSet1","body":"RamRajya"}
```

## PUT REQUEST

```java
public Post update(Integer id, Post post) {
	return restClient
			.put()
			.uri("/posts/{id}",id)
			.contentType(MediaType.APPLICATION_JSON)
			.body(post)
			.retrieve()
			.body(Post.class);
}
```

CURL :

```bash
curl --location --request PUT 'http://localhost:8080/api/posts/1' \
--header 'Content-Type: application/json' \
--data '{
    "userId": 1,
    "id": 1,
    "title": "DataSet2",
    "body": "RamRajyaEstablished"
}'
```

## DELETE :

```java
public void delete(Integer id) {
	 restClient.delete()
			.uri("/posts/{id}",id)
			.retrieve()
			.toBodilessEntity();
}
```

CURL :

```bash
curl --location --request DELETE 'http://localhost:8080/api/posts/1'
```