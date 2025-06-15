# JPA - This is Spring JPA

Directly map bean to DB. No SQL Queries. The table used here is defined in 10. Spring_JDBC.md

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

2. Repo Class

	```java
	import jakarta.persistence.EntityManager;
	import jakarta.persistence.PersistenceContext;
	import jakarta.transaction.Transactional;

	@Repository
	@Transactional
	public class CourseJpaRepository {
		@PersistenceContext
		private EntityManager entityManager;

		public void insert(Course course) {
			entityManager.merge(course);
		}

		public Course findById(long id) {
			return entityManager.find(Course.class, id);
		}

		public void deleteById(long id) {
			Course find = entityManager.find(Course.class, id);
			entityManager.remove(find);
		}
	}
	```

3. Command line runner

	```java
	@Component
	public class CourseJDBCCommandLineRunner implements CommandLineRunner {

		@Autowired
		private CourseJpaRepository repository;

		@Override
		public void run(String... args) throws Exception {
			repository.insert(new Course(1,"Learn AWS","Syndicate"));
			repository.insert(new Course(2,"Learn Google","Syndicate"));
			repository.insert(new Course(3,"Learn Azure","Syndicate"));
			repository.deleteById(2);
			System.out.println(repository.findById(1)); // Course [id=1, name=Learn AWS, author=Syndicate]
			System.out.println(repository.findById(3)); // Course [id=3, name=Learn Azure, author=Syndicate]
		}
	}
	```

4. Things to focus on - Look at the packages , it looks more dependent of the jakarta packages than
Spring packages.

5. Enable JPA logs
	```yaml
	spring:
	  jpa:
	    show-sql: true
	```
6. It looks something like 
	```
	Hibernate: select c1_0.id,c1_0.author,c1_0.name from course c1_0 where c1_0.id=?
	Hibernate: insert into course (author,name,id) values (?,?,?)
	.
	.
	```
# HIBERNATE VS JPA

1. JPA defines the specifications. Its an API
   - How do you define entities?
   - How do you map attributes?
2. Hibernate is one of the popular implementation of JPA.
3. Using Hibernate directly would result in a lock to hibernate.

