# Spring Beans P2

## Lazy Initialization

1. Default initialization of Spring Beans is Eager

    ```java
    @Component
    class A{}
    @Component
    class B{
        private A a;
        public B(A a) {
            System.out.println("Constructor Invoked");
            this.a  =a ;
        }
        public void sayHello() { System.out.println("Say Hello");}
    }
    @Configuration
    @ComponentScan
    public class SpringLearnFmApplication {
        public static void main(String[] args) {
            try (var context = new AnnotationConfigApplicationContext(SpringLearnFmApplication.class)) {			
            } catch (BeansException e) {
                e.printStackTrace();
            }
        }
    }
    ```

    Output

    ```
    Constructor Invoked
    ```

1. So even though we didn't use the beans a & b. It already initialized & available for us.
1. Now we can prevent this from happening using `@Lazy`

```java
@Component
@Lazy
class B{
```

1. So the constructor is never invoked. So when does it happen ? It happpens when someone is using it.

    ```java
    @Component
    class A{}
    @Component
    @Lazy
    class B{
        private A a;
        public B(A a) {
            System.out.println("Constructor Invoked");
            this.a  =a ;
        }
        public void sayHello() { System.out.println("Say Hello");}
    }
    @Configuration
    @ComponentScan
    public class SpringLearnFmApplication {

        public static void main(String[] args) {
            try (var context = new AnnotationConfigApplicationContext(SpringLearnFmApplication.class)) {	
                context.getBean(B.class).sayHello();
            } catch (BeansException e) {
                e.printStackTrace();
            }
        }
    }
    ```

    Output 

    ```
    Constructor Invoked
    Say Hello
    ```

1. So its loaded just before someone makes use of it.
1. Eager is recommended as it indentifies error during startup.
1. It can used with a config class by adding @Lazy annotation at class level.


## Bean Scopes - Prototype & Singleton

1. Scopes
    - Singleton - One object instance per IOC container
        - Recommended for Stateless Beans
    - Prototype - Possibly many object instances per IOC container
        - Recommended for Statefull Beans
        - Beans that have user info
1. Scopes applicable ONLY for web-aware Spring ApplicationContext
    - Request - One object instance is created per single HTTP request
    - Session - One object instance per user HTTP session.
    - Application - One object instance per Web Application runtime
    - Websocket - One object instance per WebSocket instance.
1. Java Singleton(GOF - Gang of Four) vs Spring Singleton
    - Singleton - One object instance per IOC container
    - One object instance per JVM
    - We can run multiple IOC container per JVM.
1. We can use the @Scope Annotation to make the component a prototype class- `@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)`
1. So everytime we invoke the Spring Container to give us a new object for SCOPE_PROTOTYPE
1. We can do a simple validation using Object Hashcode

```java
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;

@Component
class NormalClass{}

@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Component
class PrototypeClass{}

@Configuration
@ComponentScan
public class SpringLearnFmApplication {
	public static void main(String[] args) {
		try (var context = new AnnotationConfigApplicationContext(SpringLearnFmApplication.class)) {					
			System.out.println(context.getBean(NormalClass.class));
			System.out.println(context.getBean(NormalClass.class));
			System.out.println(context.getBean(PrototypeClass.class));
			System.out.println(context.getBean(PrototypeClass.class));
		} catch (BeansException e) {
			e.printStackTrace();
		}
	}
}
```

Output:

```
com.scripter.springlearnfm.NormalClass@36b4fe2a
com.scripter.springlearnfm.NormalClass@36b4fe2a
com.scripter.springlearnfm.PrototypeClass@574b560f
com.scripter.springlearnfm.PrototypeClass@ba54932
```

## PostConstruct & PreDe

1. What if I want to perform some logic or initialization once the dependency are accounter for in Bean
1. Use `@PostConstruct` from `import jakarta.annotation.PostConstruct;`
1. We can also do the opposite when a bean is removed / destroyed from application context.
1. Use `@PreDestroy` from `import jakarta.annotation.PreDestroy;`


```java
@Component
class SomeClass{
	private SomeDependency dependency;
	public SomeClass(SomeDependency dependency) {
		super();
		this.dependency = dependency;
		System.out.println("All dependencies are ready!");
	}
	@PostConstruct
	public void initialization() {
		SomeDependency.getReady();
	}	
	@PreDestroy
	public void cleanUp() {
		System.out.println("Closing DB Operations");
	}
}

@Component
class SomeDependency {
	public static void getReady() {
		System.out.println("Some logic using SomeDependency");
	}
}

@Configuration
@ComponentScan
public class SpringLearnFmApplication {
	public static void main(String[] args) {
		try (var context = new AnnotationConfigApplicationContext(SpringLearnFmApplication.class)) {			
		} catch (BeansException e) {
			e.printStackTrace();
		}
	}
}
```

Output:

```
All dependencies are ready!
Some logic using SomeDependency
....
Closing DB Operations
```

## Jakarta Contexts and Dependency Injection (CDI)

1. CDI specifications introduced in Java EE 6 platform in Dec 2009.
1. Now Called Jakarta Contexts and Dependency Injection
1. CDI is a specification ( Interface )
1. Spring Framework implements CDI
1. Important Inject API Annotations
    - Inject ( Similar to Spring @Autowired )
    - Named ( Similar to Spring @Component )

1. Lets see it in actions.
1. We need a dependecy

```gradle
implementation 'jakarta.inject:jakarta.inject-api:2.0.1'
```
```java
//@Component
@Named
class BusinessService{
	private DataService data;
	public DataService getData() { return data; }
//	@Autowired
	@Inject
	public void setData(DataService data) {
		System.out.println("Setter Injection");
		this.data = data;
	}
}

//@Component
@Named
class DataService {}

@Configuration
@ComponentScan
public class SpringLearnFmApplication {
	public static void main(String[] args) {
		try (var context = new AnnotationConfigApplicationContext(SpringLearnFmApplication.class)) {			
			System.out.println(context.getBean(BusinessService.class).getData());
		} catch (BeansException e) {
			e.printStackTrace();
		}
	}
}
```

Output:

```
Setter Injection
..
com.scripter.springlearnfm.DataService@346d61be
```

## XML Configration

1. In the earlier days , XML config was everything. Lets go back to it.
2. Create a file `/spring-learn-fm/src/main/resources/contextConfiguration.xml`

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xmlns:context="http://www.springframework.org/schema/context"
	xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
						http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context.xsd"> 
	<!-- bean definitions here -->
	<bean id="user-name" class="java.lang.String">
		<constructor-arg value="Syndicate"/> <!-- This is Constructor Injection -->
	</bean>
	<bean id="user-age" class="java.lang.Integer">
		<constructor-arg value="35"/>
	</bean>
	<context:component-scan base-package="com.scripter.springlearnfm"/> <!-- Equivalent to Component Scan -->
</beans>
```

```java
public class SpringLearnFmApplication {
	public static void main(String[] args) {
		try (var context = new ClassPathXmlApplicationContext("contextConfiguration.xml")) {			
			System.out.println(context.getBean("user-name")); // Syndicate
			System.out.println(context.getBean("user-age")); // 35
		} catch (BeansException e) {
			e.printStackTrace();
		}
	}
}
```

## 