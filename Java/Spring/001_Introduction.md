# Spring Basics

## Starting the Project

1. Spring initalizer is great.
2. The Spring boot version we see 
```
3.2.1 (SNAPSHOT) 3.2.0(M4) 3.1.7 (SNAPSHOT) 3.1.6
```
3. M stands for Milestone release , avoid snapshots which are currently under development.

## 1. Application Properties

1. Enable Spring Logging Properties

```yaml
logging:
  level:
    org:
      springframework: debug
```

## AutoConfiguratio in Spring

1. Essence of everything , spring configures everything for us.
1. Configuration was defined in classpath 
1. Jar name : `spring-boot-autoconfigure-3.2.2.jar`
1. When we start the app in debug mode we see a lot of logs.
    - Positive matches: Configurations that were auto-configured.
    - Negative matches: Did not get auto-configured.

1. Exclude AutoConfiguration say for Mongo

```java
@SpringBootApplication
@EnableAutoConfiguration(exclude = {MongoAutoConfiguration.class})
public class JsmtdApplication {
```

## Spring Profiles

1. dev & test profiles

	```yaml
	spring:
	  profiles:
	    active: 'dev'
	
	---
	spring:
	  config:
	    activate:
	      on-profile: dev
	
	environment: 'dev-1'
	billing-info-service-url: 'http://127.0.0.1:3001/v2/billing-info'
	---
	spring:
	  config:
	    activate:
	      on-profile: test
	 
	environment: 'test-1'
	billing-info-service-url: 'http://127.0.0.1:3001/v1/billing-info'
	---
	```

1. Spring Invoke

	```java
	@Value("${billing-info-service-url}")
	private String billingInfoServiceUrl;
	```

## Bootsrap & Jquery in Spring Boot

1. Through web-jars


```gradle
implementation 'org.webjars:bootstrap:5.3.2'
implementation 'org.webjars.bower:jquery:3.7.1'
```

Add them to the HTML/JSP page


```html
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
	<head>
		<link href="webjars/bootstrap/5.3.2/css/bootstrap.min.css" rel="stylesheet"/>
		<title>List Todo's</title>
	</head>
	<body>
		<div>
			.....
		</div>
		<script src="webjars/bootstrap/5.3.2/js/bootstrap.min.js"></script>
		<script src="webjars/jquery/3.7.1/dist/jquery.min.js"></script>
	</body>
</html>
```

