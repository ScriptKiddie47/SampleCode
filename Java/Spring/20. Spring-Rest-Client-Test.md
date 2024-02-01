# Lets write some unit test 

Source : https://www.youtube.com/watch?v=jhhi03AIin4

## Lets quickly create a simple server 

1. We can use the revious one as well , here I have created a new one.
2. Not defining the controller & other stuff as its pretty common to see its tests

```java
public record Post(Integer userId, Integer id,String title,String body) {}
@Component
public class PostClient {
	private final RestClient restClient;
	public PostClient(RestClient.Builder builder) {
		this.restClient = builder
				.baseUrl("https://jsonplaceholder.typicode.com")
				.build();
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

3. So we not going to make an actual call in the Junit to the HTTP service.So mock it.


```java
@RestClientTest(components = PostClient.class)
class PostClientTest {

	@Autowired
	private MockRestServiceServer server;
	@Autowired
	private PostClient client;
	@Autowired
	private ObjectMapper mapper;

	@Test
	void shouldFindAllPosts() throws JsonProcessingException {
		List<Post> data = List.of(new Post(1, 1, "Hello World", "This is my first post"),
				new Post(2, 2, "Hello Universe", "This is my second post"));

		// WHEN
		// Anytime a call is being made to the below URI , we are going to mock it out.
		// Respond with a success so 200 , use object mapper to write the data so we are
		// getting a JSON back
		server.expect(requestTo("https://jsonplaceholder.typicode.com/posts"))
				.andRespond(withSuccess(mapper.writeValueAsString(data), MediaType.APPLICATION_JSON));

		// THEN
		List<Post> posts = client.findAll();
		assertEquals(2, posts.size());
	}
}
```

4. ps : using different HTTP client kinda haywires the Junit.