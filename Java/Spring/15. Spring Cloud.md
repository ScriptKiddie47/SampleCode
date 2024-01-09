# Spring Cloud

## Configuration Management
1. Spring Config Server

## Dynamic Scalling
1. Eureka naming server - Ribbon Load Balancing / Spring Cloud LoadBalancer ( new )


## Visibility and Monitoring
1. Zipkin Distributed Tracing
2. Netflix Zuul API Gateway / Spring Cloud Gateway ( new )

## Fault Tolerance
1. Hixtrix / Resilence4j ( new )

## Microservices setup for Spring Cloud Config Server ( Microservice name - limits-service port 8080 )
( This is more like the initial setup , final connection is done below )
1. Gradle Dependency 
    ```groovy
    implementation 'org.springframework.cloud:spring-cloud-starter-config'
    ```

2. application properties 
    ```yaml
    spring:
      config:
        import: optional:configserver:http://localhost:8888
    ```

3. The optional property deduces that once the config server is available , connect to it.
4. Lets do simple service , controller and return some data

    ```java
    @RestController
    public class LimitsController {
        
        @GetMapping("/limits")
        public Limits retrieveLimits() {
            return new Limits(1,100);
        }
    }
    public class Limits {
        private int minimum;
        private int maximum;
    ```

    ```bash
    $ curl 'http://localhost:8080/limits'
    {"minimum":1,"maximum":1}
    ```
5. Easy Peasy , but lets get the data from application properties. Easy as well.
    ```yaml
    spring:
    config:
        import: optional:configserver:http//localhost:8888
    limits-service:
        minimum: 2
        maximum: 998
    ```
6. Java Code to pick it up.

    ```java
    import org.springframework.boot.context.properties.ConfigurationProperties;
    import org.springframework.stereotype.Component;
    @Component
    @ConfigurationProperties("limits-service")
    public class LimitsConfiguration {
        private int minimum;
        private int maximum;
        // Getters and Setters

    @RestController
    public class LimitsController {
        private LimitsConfiguration limitsconfiguration; // Inject it
        @GetMapping("/limits")
        public Limits retrieveLimits() {
            return new Limits(limitsconfiguration.getMinimum(), limitsconfiguration.getMaximum());
        }
    }
    ```
    ```bash
    $ curl 'http://localhost:8080/limits'
    {"minimum":2,"maximum":998}
    ``` 

## Lets Create the Spring Cloud Config Server ( New Application called - limits-config-server port 8888)

1. Gradle Dependency 
    ```groovy
    implementation 'org.springframework.cloud:spring-cloud-config-server'
    ```
2. Properties

    ```yaml
    server:
    port: 8888
    spring:
    application:
        name: limits-config-server
    ```
## Create Git Local Repository
1. Now comes the interesting part , we will create a folder and initialize git in it. We will store all our configuration files here.

    ```bash
    Syn: ~/Documents/CodeSource/spring-project-nogit-link 
    $ mkdir git-localconfig-repo
    Syn: ~/Documents/CodeSource/spring-project-nogit-link 
    $ cd git-localconfig-repo/
    Syn: ~/Documents/CodeSource/spring-project-nogit-link/git-localconfig-repo 
    $ git init
    Initialized empty Git repository in /home/syndicate/Documents/CodeSource/spring-project-nogit-link/git-localconfig-repo/.git/
    Syn: ~/Documents/CodeSource/spring-project-nogit-link/git-localconfig-repo 
    ```

2. Lets create a new file inside the folder called `limits-service.yaml`
    ```yaml
    limits-service:
        minimum: 5
        maximum: 999
    ```
3. Lets commit the change as well.

## Connect `limits-config-server` to Git Local Repo

1. Navigate to /limits-config-server/src/main/resources/application.yaml & configure git local repo folder.

    ```yaml
    server:
    port: 8888
    spring:
    application:
        name: limits-config-server
    cloud:
        config:
        server:
            git:
            uri: file:///home/syndicate/Documents/CodeSource/spring-project-nogit-link/git-localconfig-repo
            default-label: master
    ```

2. Provide the annotation `@EnableConfigServer` in /limits-config-server/src/main/java/com/scripter/limitsconfigserver/LimitsConfigServerApplication.java.

    ```java
    @EnableConfigServer
    @SpringBootApplication
    public class LimitsConfigServerApplication {...
    ```

3. Curl the config-server

    ```bash
    $ curl 'http://localhost:8888/limits-service/default'
    ```
    Response has been formatted in JSON & Captured below

    ```json
    {
    "name": "limits-service",
    "profiles": ["default"],
    "label": null,
    "version": "a55a7dcfe778f57697f21814ef03e90232559fdf",
    "state": null,
    "propertySources": [
        {
        "name": "file:///home/syndicate/Documents/CodeSource/spring-project-nogit-link/git-localconfig-repo/limits-service.yaml",
        "source": { "limit-service.minimum": 5, "limit-service.maximum": 999 }
        }
    ]
    }
    ```
4. Now we can say that `limits-config-server` application is connected to `git-local-repo`

## Connect `limits-service` application to `limits-config-server`

1. We already have done some prev configuration above for /limits-service/src/main/resources/application.yaml, so lets expand a little on it.

    ```yaml
    spring:
    config:
        import: configserver:http://localhost:8888
    application:
        name: limits-service
    limits-service:
    minimum: 2
    maximum: 998
    ```
2. Now lets curl it.
    ```bash
    $ curl 'http://localhost:8080/limits'
    {"minimum":5,"maximum":999}
    ```
3. See the magic here, even though we have the minimum & maximum above as 2 & 998 we get something else in the reponse & this is because of the configuration/value is coming for the config server. In the `limits-service` logs we see the below line which tells us where we are pulling the data from 

    ```log
    ..ConfigServerConfigDataLoader: Fetching config from server at : http://localhost:8888
    ```
    This is the endpoint of the `limits-config-server`

4. So values in git repo have more priority than local.

## Setup for Multiple Environments

1. So in the git local repo , we create the separate files as per environemnt
```bash
├── limits-service-dev.yaml [6,996]
├── limits-service-test.yaml [9,999]
└── limits-service.yaml [0,0]
```
2. We can configure different values in them. 
3. If we want to pickup the configuration related to dev , we can start `limits-config-server` and curl
```bash
$ curl 'http://localhost:8888/limits-service/dev'
    {"name":"limits-service","profiles":["dev"],"label":null,"version":"a55a7dcfe778f57697f21814ef03e90232559fdf","state":null,"propertySources":[{"name":"file:///home/syndicate/Documents/CodeSource/spring-project-nogit-link/git-localconfig-repo/limits-service-dev.yaml","source":{"limits-service.minimum":6,"limits-service.maximum":996}},{"name":"file:///home/syndicate/Documents/CodeSource/spring-project-nogit-link/git-localconfig-repo/limits-service.yaml","source":{"limits-service.minimum":0,"limits-service.maximum":0}}]}
```
4. Its difficult to observe but we get both default value and dev values. ( ps : I have now switched the default to 0 & 0). Dev has high priority
5. So now lets navigate to `limits-config-server` application and configure profiles

    ```yaml
    spring:
    config:
        import: optional:configserver:http://localhost:8888
    application:
        name: limits-service
    profiles:
        active: dev
    ```

    This makes sure we pick up the dev profile 

    ```log
    The following 1 profile is active: "dev"
    Located environment: name=limits-service, profiles=[dev], label=null, version=a55a7dcfe778f57697f21814ef03e90232559fdf, state=null
    ```

    ```curl
    $ curl 'http://localhost:8080/limits'
    {"minimum":6,"maximum":996}
    ```
6. 