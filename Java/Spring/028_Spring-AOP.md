# AOP - Aspect Oriented Programming

1. A layed approach is typically used to build applications
    - Web Layer - View Logic or JSON Conversion
    - Business Layer
    - Data Layer - Persistence Logic

1. Each layerhas different responsibilities
1. However there are few common aspects
    - Security
    - Performance
    - Logging

1. There common aspects are called `Cross Cutting concerns`

1. AOP can be used to implement `Cross Cutting concerns`.

1. Popular AOP Frameworks
    - Spring AOP
        - Not a complete AOP
        - Only works with Spring Beans
        - Ex : Intercept method calls to Spring Beans
    - Aspect J
        - Complete AOP solution but rarely used
        - Ex : Intercept any method calls on any Java class
        - Ex : Intercept changes in values

```gradle
implementation 'org.springframework.boot:spring-boot-starter-aop'
```