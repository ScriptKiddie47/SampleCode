# H2 Database - In memory databse 

1. Dependency 
    ```groovy
    runtimeOnly 'com.h2database:h2'
    ```
2. application.yaml to Enable Console
    spring:
    ```yaml
    spring:
      h2:
        console:
          enabled: true
      datasource:
        url: jdbc:h2:mem:testdb
    ```
3. Endpoint In WebBrowser - http://localhost:8080/h2-console
4. If H2 JDBC URL is not configured , it gets dynamically generated and stored in the logs. However its recommended to configure a URL as above.
   - JDBC URL : jdbc:h2:mem:testdb
   - Username : sa
   - Password : -NA- Since we didn't configure it.

# SQL Schema File

1. A unique file present in this location /spring-jpa-jdbc/src/main/resources/schema.sql.
2. When an application startrs schema sql is executed.

    ```sql
    create table course(
    	id bigint not null,
    	name varchar(255) not null,
    	author varchar(255) not null,
    	primary key(id)
    );

    insert into course(id,name,author) values(1 , 'Learn AWS', 'Syndicate');
    ```
3. Once we start the service , we observe that the table is created in the H2 DB.

# SQL File

1. Suppose we have a SQL file that contains some insert statement.
2. File Location : /rest-web-service/src/main/resources/data.sql ( Only works with in-mem)
3. Content
    ```sql
    insert into user_details(birthday,id,name) values (current_date(),1001,'S')
    ```
4. What spring does is to execute it at the start itself before schema gets read from above file or through JPA Entity based class.
5. We need to slow this down, we can utilize a property
    ```yaml
    spring:
      jpa:
        defer-datasource-initialization: true
    ```   

# SPRING JDBC ( CRUD OPS )

### Dependency
```groovy
implementation 'org.springframework.boot:spring-boot-starter-data-jdbc'
```

1. JDBC - Write SQL Queries, write a lot of boilerplate code
2. Spring JDBC - Write SQL Queries, write less code
3. Lets do a simple insert. ( Above schema is utilized here)
    ```java
    import org.springframework.jdbc.core.JdbcTemplate;
    @Repository
    public class CourseJdbcRepository {
    	@Autowired
    	private JdbcTemplate springTemplate;
    	private static String insertQuery = """
    			insert into course (id,name,author) values(1 , 'Learn AWS', 'Syndicate');
    			""";

    	public void insert() {
    		springTemplate.update(insertQuery);
    	}
    }
    ```
4. Lets use a simple command line runner here to trigger the application.
    ```java
    @Component
    public class CourseJDBCCommandLineRunner implements CommandLineRunner {

    	@Autowired
    	private CourseJdbcRepository repository;

    	@Override
    	public void run(String... args) throws Exception {
    		repository.insert();
    	}

    }
    ```
5. Once we Start the application , we observe that the table is updated.
6. Lets now use objects. Define a Course POJO Class

    ```java
    public class Course {
    	private long id;
    	private String name;
    	private String author;
        public Course(long id, String name, String author) {... // Constructor,getters,setters
    ```

7. Lets Update the CourseJdbcRepository Class
    ```java
    private static String insertQuery = "insert into course (id,name,author) values(? , ? , ?)";
    public void insert(Course course) {
    	springTemplate.update(insertQuery, course.getId(), course.getName(), course.getAuthor());
    }
    ```
8. Command line runner 

    ```java
    @Override
	public void run(String... args) throws Exception {
		repository.insert(new Course(1,"Learn AWS","Syndicate"));
	}
    ```
9. We will see the data reflected in H2.
10. Lets write a delete query in the CourseJdbcRepository
    ```java
    private static String deleteQuery = "delete from course where id = ? ";
    public void delete(int id) {
        springTemplate.update(deleteQuery,id);
    }
    ```
    
11. Lets invoke it as part of the command line runner.
    ```java
    repository.delete(1);
    ```

12. Lets do a Select Query - CourseJdbcRepository Code
    ```java
    private static String selectQuery = "select * from course where id = ?";
    public Course findById(long id) {
    	// ResultSet to Bean Mapping using RowMapper
    	return springTemplate.queryForObject(selectQuery,new BeanPropertyRowMapper<>(Course.class),id);
    }
    ```
13. Lets invoke it as part of the command line runner.
    ```java
    System.out.println(repository.findById(1)); // Course [id=1, name=Learn AWS, author=Syndicate]
	System.out.println(repository.findById(3)); // Course [id=3, name=Learn Azure, author=Syndicate]
    ```
14. Lets do a Select Query For a <b>List</b> - CourseJdbcRepository Code
    ```java
    public List<Course> findAll() {
		return springTemplate.query(selectEveryThingQuery,new BeanPropertyRowMapper<>(Course.class));
	}
    ```
15. Lets invoke it as part of the command line runner.
    ```java
    System.out.println(repository.findAll()); //[Course [id=1, name=Learn AWS, author=Syndicate], Course [id=2, name=Learn Google, author=Syndicate], Course [id=3, name=Learn Azure, author=Syndicate]] 
    ```