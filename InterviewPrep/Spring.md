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
`@ControllerAdvise` - Globally accross all controllers

`@Autowired `— Field Injection - Spring directly injects into the field via reflection. 
`Constructor Injection` - Constructor injection makes dependencies explicit, immutable ( Field can be final), and testable
    - Fails fast — missing dependency caught at startup, not runtime


# Questions 
What happens a Rest Request Hits a Spring Boot Controller?
`Embedded Tomcat` -> `DispatcherServlet` -> `Handler Mapping(@RequestingMapping,@GetMapping)` ->  `Interceptors/Filter` -> `@RestController` -> `HttpMessageConverter` -> `Json`
Note : If `@Controller` -> `ViewResolver` -> `TemplateEnginer(Thymeleaf/JSP)` -> `HTML`

`@Primary` - Sets a default bean when multiple candidates exist. Solves multiple beans of same type issue
`@Qualifier` - Explicitly names. Solves multiple beans of same type

Bean Scopes in Spring
    - `@Singleton` - Once ( Default). Lives untill App Shutdown
    - `@Prototype` - Every Injection. Garbage collected when unused.
    - `@RequestScope` - Every HTTP Request. Request ends
    - `@SessionScope` - Every HTTP Session. Session expires


# Spring Security

Spring Security - `@EnableWebSecurity` - Define our : `SecurityFilterChain` - Also define `Role Based Authorization`
JWT,OKTA(Auth2.0)

## How Spring Security Works
Every HTTP request passes through a `Security Filter Chain` — a series of filters before hitting the controller.
```
HTTP Request -> Security Filter Chain -> DispatcherServlet -> Controller
```
Key filter: `UsernamePasswordAuthenticationFilter` (for form login) or `OncePerRequestFilter` (for JWT)

## SecurityFilterChain (Modern Config — Spring Security 6+)
```java
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())                          // disable for stateless REST APIs
            .sessionManagement(session -> session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // no HTTP session for JWT
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/auth/**").permitAll()       // public endpoints
                .requestMatchers("/api/admin/**").hasRole("ADMIN")
                .anyRequest().authenticated()
            )
            .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
```

## JWT Authentication Flow
```
1. POST /login  ->  Validate credentials  ->  Generate JWT  ->  Return token to client
2. GET /api/...  ->  JwtAuthFilter reads token from header  ->  Validate & set SecurityContext  ->  Continue
```
- JWT stored client-side (localStorage or cookie) — server is stateless
- Token contains: `header.payload.signature` (Base64 encoded)
- Payload carries claims: `sub` (subject/userId), `roles`, `iat`, `exp`

**JwtAuthFilter (custom filter)**
```java
@Component
public class JwtAuthFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain) throws IOException, ServletException {
        String header = request.getHeader("Authorization");
        if (header != null && header.startsWith("Bearer ")) {
            String token = header.substring(7);
            String username = jwtService.extractUsername(token);
            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                if (jwtService.isTokenValid(token, userDetails)) {
                    UsernamePasswordAuthenticationToken authToken =
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }
            }
        }
        chain.doFilter(request, response);
    }
}
```

## UserDetailsService
Spring Security calls this to load the user during authentication.
```java
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByUsername(username)
            .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return new org.springframework.security.core.userdetails.User(
            user.getUsername(), user.getPassword(),
            List.of(new SimpleGrantedAuthority("ROLE_" + user.getRole()))
        );
    }
}
```

## Role-Based Authorization
```java
// Method-level security — enable with @EnableMethodSecurity
@PreAuthorize("hasRole('ADMIN')")
public void deleteUser(Long id) { ... }

@PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
public List<User> getAllUsers() { ... }

@PostAuthorize("returnObject.username == authentication.name")  // check after method runs
public User getUser(Long id) { ... }
```
- `ROLE_` prefix is automatic — store `ADMIN`, Spring checks `ROLE_ADMIN`
- `hasAuthority("READ")` — for fine-grained permissions (no prefix)

## OAuth2 / OKTA
- `OKTA` = Identity Provider (IdP) implementing OAuth2 + OpenID Connect (OIDC)
- Spring Boot integrates via `spring-boot-starter-oauth2-client`
- `OAuth2` = Authorization framework (grants access tokens)
- `OIDC` = Identity layer on top of OAuth2 (provides ID tokens with user info)

**Flow (Authorization Code)**
```
User -> App -> Redirect to OKTA login -> OKTA returns Auth Code -> App exchanges for Access Token -> Use token to call APIs
```

## Password Encoding
Always hash passwords — never store plaintext.
```java
@Bean
public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();  // BCrypt is the standard
}
// usage
passwordEncoder.encode(rawPassword);
passwordEncoder.matches(rawPassword, hashedPassword);
```

## Common Interview Questions
**Q: What is CSRF and when do you disable it?**
Cross-Site Request Forgery — attacker tricks browser into sending requests using existing session cookies.
Disable for stateless REST APIs using JWT (no session cookies, so CSRF doesn't apply).
Keep enabled for stateful apps with form-based login.

**Q: How does Spring Security know who is logged in?**
`SecurityContextHolder` — a thread-local holder of the `Authentication` object.
Set by the filter after validating JWT or session. Cleared after request completes.

**Q: Difference between `@Secured`, `@PreAuthorize`, `@RolesAllowed`?**
- `@Secured({"ROLE_ADMIN"})` — simple, no SpEL
- `@PreAuthorize("hasRole('ADMIN')")` — supports SpEL, most flexible
- `@RolesAllowed("ADMIN")` — JSR-250 standard annotation
