# SPRING JPA - Make JPA even more simple

### Dependency 
```groovy
implementation 'org.springframework.boot:spring-boot-starter-data-jpa'
```

The table used here is defined in 10. Spring_JDBC.md

## NOTE : WHEN WE DEFINE THE SCHEMA USING THE BELOW ENTITY CLASS INSTEAD OF A SCHEMA SQL FILE, THE COLUMN ORDER IS NOT GARUNTEEED.

1. Lets create an @Entity POJO class

	```java
	import jakarta.persistence.Entity;
	import jakarta.persistence.Id;

	@Entity
	public class Course {

		@Id
		//If we want the JPA to generate this we can keep @GeneratedValue
		private long id;
		//@Column(name="xyz_name")  We can put mapping in here if we had different names, in this case it not mandatory but just keeping it
		private String name;
		private String author;
	    //Getters Setters Constructors
	```

2. Lets Create the Repo and we see no need to talk to JPA Entity manager

    ```java
    import org.springframework.data.jpa.repository.JpaRepository;
    public interface SpringDataJPARepo extends JpaRepository<Course, Long> {}
    ```
3. Lets update the Command line Runner
    ```java
    @Component
    public class CourseCommandLineRunner implements CommandLineRunner {

    	@Autowired
    	private CourseSpringDataJPARepo repository;

    	@Override
    	public void run(String... args) throws Exception {
    		repository.save(new Course(1, "Learn AWS", "Syndicate"));
    		repository.save(new Course(2, "Learn Google", "Syndicate"));
    		repository.save(new Course(3, "Learn Azure", "Syndicate"));

    		repository.deleteById(2l);

    		System.out.println(repository.findById(1l)); // Optional[Course [id=1, name=Learn AWS, author=Syndicate]]
    		System.out.println(repository.findById(3l)); // Optional[Course [id=3, name=Learn Azure, author=Syndicate]]
    	}
    }
    ```
4. Now Lets play with Custom SQL Function we can create in Spring Data JPA. SpringDataJPARepo Interface
	```java
	public interface CourseSpringDataJPARepo extends JpaRepository<Course, Long> {
		List<Course> findByAuthor(String author);
	}
	```
5. Lets invoke it : 
	```java
	System.out.println(repository.findByAuthor("Syndicate")); //[Course [id=1, name=Learn AWS, author=Syndicate], Course [id=3, name=Learn Azure, author=Syndicate]]
	```
6. Without writting a single SQL we were able to do a custom operation.

## SPRING JPA ADVANCED ENTITY RELATIONSHIP

1. Suppose we have 2 tables. `Users` & `Post`
2. Lets have a relationship between users and post
3. Single user can have multiple post ( One to Many ). 

	```java
	@Entity(name = "user_details")
	public class User {

		@Id
		@GeneratedValue
		private Integer id;
		private String name;
		private LocalDate birthday;

		@OneToMany(mappedBy = "user")
		@JsonIgnore // We don't expect this as part of the request object
		private List<Post> posts;
	```
4. In the post the relationship will be opposite

	```java
	@Entity
	public class Post {
		@Id
		@GeneratedValue
		private Integer id;
		private String description;
		
		@ManyToOne(fetch = FetchType.LAZY)
		@JsonIgnore // We don't expect this as part of the request object
		private User user;
	```

5. The FetchType - Lazy strategy is a hint to the persistence provider  runtime that data should be fetched lazily when it is  first accessed

6. One the above code is saved. We can do a property update to see the SQL queries

	```groovy
	spring:
	jpa:
		defer-datasource-initialization: true
		show-sql : true
	```

7. Logs once the application starts

	```log
	Hibernate: drop table if exists post cascade 
	Hibernate: drop table if exists user_details cascade 
	Hibernate: drop sequence if exists post_seq
	Hibernate: drop sequence if exists user_details_seq
	Hibernate: create sequence post_seq start with 1 increment by 50
	Hibernate: create sequence user_details_seq start with 1 increment by 50
	Hibernate: create table post (id integer not null, user_id integer, description varchar(255), primary key (id))
	Hibernate: create table user_details (birthday date, id integer not null, name varchar(255), primary key (id))
	Hibernate: alter table if exists post add constraint FKa3biitl48c71riii9uyelpdhb foreign key (user_id) references user_details
	```
	We can see the tables being created & the Fkey,Pkey created

8. Lets do some initial sql. Create file `/rest-web-service/src/main/resources/data.sql`. Only works with in-mem DB

	```sql
	insert into user_details(birthday,id,name) values (current_date(),1001,'Bala');
	insert into user_details(birthday,id,name) values (current_date(),1003,'Ravi');
	insert into user_details(birthday,id,name) values (current_date(),1004,'Ranga');

	insert into post(id,description,user_id) values(2001,'I want to learn AWS',1001);
	insert into post(id,description,user_id) values(2002,'I want to learn dev-ops',1003);
	insert into post(id,description,user_id) values(2003,'I want to learn Google Cloud',1001);
	insert into post(id,description,user_id) values(2004,'I want to learn Azure',1004);
	```

	We can do a simple join query 
	```sql
	SELECT * FROM USER_DETAILS inner join post on  USER_DETAILS.id = post.user_id

	--Output
	BIRTHDAY  	ID  	NAME  	ID  	USER_ID  	DESCRIPTION  
	2024-01-08	1001	Bala	2001	1001	I want to learn AWS
	2024-01-08	1003	Ravi	2002	1003	I want to learn dev-ops
	2024-01-08	1001	Bala	2003	1001	I want to learn Google Cloud
	2024-01-08	1004	Ranga	2004	1004	I want to learn Azure

	-- Notice how Bala has 2 posts
	```
9. Lets make a GetCall

	```java
	public interface UserRepository extends JpaRepository<User, Long> {}
	@RestController
	public class UserController {
		private UserRepository repository; // Autowire through constructor

		@GetMapping("/users/{id}/posts")
		public List<Post> retrievePostForUser(@PathVariable("id") long id){
			Optional<User> user =  repository.findById(id);
			return user.get().getPosts();
		}	
	```

	```bash
	$ curl http://localhost:8080/users/1001/posts
	[{"id":2001,"description":"I want to learn AWS"},{"id":2003,"description":"I want to learn Google Cloud"}]
	```

10. `Notice how its getting the data from post but we haven't created any Repo interface or wrote SQL queries`.

11. However , lets create a post repo and lets see how we can play around with it.

	```java
	public interface PostRepository extends JpaRepository<Post, Long>{}
	@RestController
	public class UserController {
		private UserRepository userRepository; // wire it
		private PostRepository postRepository; // wire it

		@PostMapping("/users/{id}/posts")
		public ResponseEntity<Post> createPostForUser(@PathVariable("id") long id, @RequestBody Post post) {
			Optional<User> user = userRepository.findById(id);
			if (!Objects.nonNull(user)) {
				throw new UserNotFoundException("id:" + id);
			}
			System.out.println(user.get());
			post.setUser(user.get());
			System.out.println(post);
			postRepository.save(post);
			return new ResponseEntity<>(post, HttpStatus.CREATED);
		}
	```

	```bash
	$ curl 'http://localhost:8080/users/1004/posts' \
	--header 'Content-Type: application/json' \
	--data '{
		"description" : "I want to learn docker fundamentals"
	}'
	{"id":1,"description":"I want to learn docker fundamentals"}
	```

12. The thing to note here is the fact that we are not passing user data above in the request just the user id.

