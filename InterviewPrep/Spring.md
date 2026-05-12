`Spring`
    General framework for building Java applications with flexibility
    Requires manual XML or annotation-based configuration
    More complex and time-consuming to set up
    Needs an external server like Tomcat or Jetty
    Developers manually manage dependencies
    Steeper learning curve due to modular structure and manual configurations
`Spring Boot`
    `Streamlined` framework for quickly creating production-ready Spring applications
    Provides` auto-configuration` to reduce boilerplate setup
    Simple setup with `minimal configuration` needed
    Comes with `embedded servers`; no external setup required
    `Uses Starter POMs` for simplified dependency management
    Easier and faster to start and deploy applications

`@SpringBootApplication `- @EnableAutoConfiguration,@Configuration,@ComponentScan
`@EnableAutoConfiguration `- > Checks classPaths,Loads 

`@Bean` - when you need custom configuration or when working with third-party classes you can't annotate directly
`@Component` -For your own classes where you let Spring handle instantiation.

`@ExceptionHandler` - Class or Methods
`@ControllerAdvise` - Globally accross all controller

`@Autowired `— Field Injection - Spring directly injects into the field via reflection. 
`Constructor Injection` - Constructor injection makes dependencies explicit, immutable ( Field can be final), and testable
    - Fails fast — missing dependency caught at startup, not runtime

Spring Security - `@EnableWebSecurity` - Define our : `SecurityFilterChain` - Also define `Role Based Authorization`