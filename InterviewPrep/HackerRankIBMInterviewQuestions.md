# Quiz Questions & Answers

---

## Java

**Q: What is the output of `int arr[2]; System.out.println(arr[0]); System.out.println(arr[1]);`?**

**A: 0 / 0**

Java always initialises array elements to default values (`0` for `int`). Unlike C/C++, there are no garbage values.
- ❌ Compiler Error — `int arr[2]` is valid Java syntax
- ❌ Garbage value — Java behaviour, not C/C++
- ❌ Exception — indices 0 and 1 are valid for a size-2 array

---

**Q: What is the output of the `Outer`/`Inner` static nested class program with `temp1`–`temp5`?**

**A: Compilation Error**

A static nested class cannot access instance (non-static) members of the outer class. `temp3` and `temp4` are non-static instance variables, making them inaccessible from the static `Inner` class.

| Variable | Type | Accessible from static Inner? |
|----------|------|-------------------------------|
| `temp1` | `public static int` | ✅ |
| `temp2` | `private static int` | ✅ |
| `temp3` | `public int` (instance) | ❌ |
| `temp4` | `private int` (instance) | ❌ |
| `temp5` | `private static int` | ✅ |

---

**Q: What is the output of the following code?**
```java
Thread t = new Thread(new ThreadTest());
t.run();
t.run();
t.start();
```

**A: Prints `runningrunningrunning`**

- `t.run()` is just a **plain method call** on the main thread — does NOT spawn a new thread.
- `t.start()` is the only call that **spawns a new thread**, which internally calls `run()`.
- So `run()` executes 3 times → prints `"running"` three times.

> **Key trap:** `t.run()` bypasses the threading mechanism entirely. Only `t.start()` creates a new thread.

---

**Q: What is the output?**
```java
class Super {
    public int i = 0;
    public Super(String text) { i = 1; }
}
class Sub extends Super {
    public Sub(String text) { i = 2; }
}
// main: new Sub("Hello"); System.out.println(sub.i);
```

**A: Compilation Error**

- `Sub`'s constructor does not call `super(text)`.
- Since `Super` has no no-arg constructor (a custom one is defined), Java cannot implicitly call `super()`.
- **Fix:** Add `super(text);` as the first line in `Sub`'s constructor.

> **Rule:** Java only auto-generates a no-arg constructor when you define **zero** constructors. Once you define any constructor, you own all constructor chaining responsibilities.

---

**Q: Which error is thrown when loading a JAR file with a duplicate name?**

**A: `java.lang.ClassNotFoundException` (best available option)**

- Having duplicate JARs on the classpath doesn't throw a guaranteed specific exception — the ClassLoader picks the **first match** (first-wins strategy).
- `ClassNotFoundException` (checked) or `NoClassDefFoundError` (unchecked) are the closest real errors if resolution fails.
- `DuplicateClassError` does **not exist** in standard Java.
- In real-world architecture, duplicate JARs are a **classpath hell** problem managed by build tools (Maven/Gradle), not Java itself.

---

**Q: What is the output?**
```java
enum Levels {
    private machinA,
    public machinB,
    protected machinC;
}
```

**A: Compilation Error**

- Enum constants **cannot have access modifiers**.
- They are implicitly `public static final`.
- The compiler rejects access modifiers on enum constants — this never reaches runtime.

**Valid syntax:**
```java
enum Levels {
    machinA, machinB, machinC;
}
```

---

## Java 8

**Q: Select the correct examples of functional interface**

**A: `java.lang.Runnable` and `java.util.Comparator`**

A functional interface has exactly **one abstract method (SAM)**:
- ✅ `Runnable` — one abstract method: `run()`
- ✅ `Comparator` — one abstract method: `compare()` (other methods are `default`/`static`)
- ❌ `java.util.List` — multiple abstract methods
- ❌ `java.io.InputStream` — multiple abstract methods

> `default` and `static` methods don't count toward the SAM requirement.

---

**Q: Implement the `Reporting` class methods: `totalOrderAmountPerCustomer`, `totalOrderAmountOnDate`, and `getOrder`.**

**A:**

```java
@Override
public int totalOrderAmountPerCustomer(int customerId) {
    return orders.stream()
        .filter(o -> o.customer.id == customerId)
        .mapToInt(o -> o.amount)
        .sum();
}

@Override
public int totalOrderAmountOnDate(Date date) {
    return orders.stream()
        .filter(o -> o.date.equals(date))
        .mapToInt(o -> o.amount)
        .sum();
}

@Override
public List<Order> getOrder(int customerId) {
    return orders.stream()
        .filter(o -> o.customer.id == customerId)
        .collect(Collectors.toList());
}
```

**Architecture note:** The structure follows a **Repository-style pattern**. `IReporting` acts as the interface/contract; `Reporting` is the in-memory implementation. In production, aggregations should be SQL-level (`SUM`, `GROUP BY`) rather than in-memory streams for scalability.

---

## JavaScript

**Q: Which JavaScript method brings a webpage back like clicking the back button twice?**

**A: `history.go(-2)`**

`history.go()` accepts a delta integer. Negative = go back, positive = go forward.
- ❌ `history.back().back()` — `back()` returns `undefined`, cannot chain
- ❌ `history.back(2)` — `back()` takes no arguments
- ❌ `history.before(2)` — not a real method

---

**Q: What happens when `var foo = 9` is declared inside an IIFE and `alert(foo)` is called outside?**

**A: ReferenceError — `foo` is not defined**

`var` is function-scoped. `foo` is trapped inside the IIFE and invisible to the outer scope.

```
Global Scope
    └── IIFE Scope
            └── var foo = 9  ← only lives here
```

---

**Q: Which of the following are true about the Fetch API?**

**A: Options B and D**

- **B. The Promise returned by `fetch()` resolves on any successful response** — TRUE. `fetch()` resolves as long as the server responds, regardless of HTTP status code.
- **D. To extract JSON data from fetch's Response, we call its `json()` method** — TRUE. `response.json()` returns a Promise that resolves with the parsed JSON body.

**Why others are wrong:**
- ❌ A — `fetch()` does NOT reject on HTTP error codes (404, 500, etc.). It only rejects on network failures. You must manually check `response.ok`.
- ❌ C — There is no `json` property on the Response object.

---

## ReactJS

**Q: How would you prevent a component from rendering?**

**A: Both A & B**

- **`shouldComponentUpdate`** — returns `false` to skip the render cycle entirely (also the mechanism behind `React.PureComponent`)
- **Returning `null` from render** — outputs nothing to the DOM, though the render cycle still runs
- Key difference: `shouldComponentUpdate` skips diffing entirely; returning `null` runs render but outputs nothing

---

**Q: Which about server-side rendering is true?**

**A: Both A & B**

- **`renderToString` from `react-dom/server`** — core SSR API that renders the component tree to an HTML string on the server
- **`dangerouslySetInnerHTML`** — React's replacement for `innerHTML`, intentionally named to warn of XSS risk; used in SSR contexts to inject pre-rendered HTML

---

**Q: What is an ErrorBoundary in React?**

**A: Error boundaries are components that catch errors in their child component tree**

- Implemented via `static getDerivedStateFromError()` and/or `componentDidCatch()`
- Follows the **Bulkhead pattern** — isolates failures so one broken subtree doesn't crash the whole UI
- ⚠️ Does NOT catch errors in event handlers, async code, or SSR

---

**Q: Which methods are invoked when there is an error during rendering in the React lifecycle?**

**A: Both A and B**

| Method | Phase | Purpose |
|--------|-------|---------|
| `getDerivedStateFromError()` | Render phase | Updates state to show fallback UI |
| `componentDidCatch()` | Commit phase | Side effects like logging/reporting the error |

These two methods together form **Error Boundaries** — React's equivalent of try-catch for the component tree.

> **Note:** Error Boundaries only work with **class components**. There is no Hook equivalent for functional components.

---

**Q: What is the output of this React component?**
```js
this.state = { name: "peter" }
// renders: {this.state.name}
```

**A: `peter` (lowercase)**

- The string literal is `"peter"` — React does not capitalize state values.
- No error because `super()` without props is acceptable when props aren't used in the constructor.

> **Best practice:** Always use `constructor(props) { super(props) }` to avoid `this.props` being undefined inside the constructor.

---

## Spring Boot

**Q: When a client registers with Eureka, what metadata information does it share?**

**A: A, B & C — Hostname, Port Number, and Health Indicator URI**

All three are shared during Eureka registration:
- **Hostname** — the host on which the service is running
- **Port number** — the port the service is listening on
- **Health Indicator URI** — endpoint Eureka uses to periodically check instance health (typically `/actuator/health`)

---

**Q: From where does the Spring container get its instructions?**

**A: Properties File, XML File, and Annotations**

- **Properties File** (`application.properties` / `application.yml`) — externalised configuration
- **XML File** — original way to declare beans and wiring (`applicationContext.xml`)
- **Annotations** — modern code-first approach (`@Component`, `@Bean`, `@Autowired`, etc.)
- ❌ Spring JARs — runtime libraries, not configuration sources

---

## Spring Cloud

**Q: Which of the following products is a Client Side Load Balancing application?**

**A: Netflix Ribbon**

Ribbon runs *inside* the calling microservice, fetches available instances from Eureka, and applies a load-balancing algorithm before making the call.
- ❌ Netflix Eureka — Service Registry, not a load balancer
- ❌ Amazon ELB — Server-Side load balancer
- ❌ HAProxy — Server-Side, infrastructure-level load balancer

---

## Spring Microservices

**Q: Which microservices feature can Spring repositories enable?**

**A: Polyglot Persistence and CQRS**

- **Polyglot Persistence** — Spring Data's store-agnostic repository abstraction allows different microservices to use different databases (MySQL, MongoDB, Redis, etc.)
- **CQRS** — Spring repositories support splitting read and write models across different backing stores
- ❌ BFF — API gateway pattern, unrelated to repositories
- ❌ Microfrontends — UI architecture concern, unrelated to repositories

---

## Microservices

**Q: Which of the following statements are true about fault tolerance?**

**A: The circuit breaker has three states (Closed, Open, Half-Open) and in Half-Open state it periodically checks dependency health**

- ✅ Circuit Breaker has three states: **Closed** (normal), **Open** (failing, blocked), **Half-Open** (probing recovery)
- ✅ Half-Open state lets probe requests through to check downstream health
- ⚠️ Bulkhead pattern isolates resource pools (thread pools, connection pools) — it is NOT a layer of abstraction between client and service (that describes a Façade/API Gateway)

---

## Design Patterns

**Q: Which design pattern provides a single class with simplified methods required by the client and delegates calls to those methods?**

**A: Façade**

Provides a simplified interface to a complex subsystem. The client talks only to the Façade, which delegates internally.
- ❌ Adapter — interface conversion/translation, not simplification
- ❌ Builder — constructs complex objects step by step
- ❌ Prototype — clones existing objects

---

**Q: Which design pattern suggests multiple classes through which a request is passed, and only relevant classes carry out operations?**

**A: Chain of Responsibility**

A request is passed along a chain of handlers; each decides to process or forward it. Classic example: Spring Security's filter chain.
- ❌ Singleton — ensures single instance
- ❌ State — changes behaviour based on internal state
- ❌ Bridge — decouples abstraction from implementation

---

**Q: Identify the pattern used in the code with `CreditCard` interface, `BankDetails` class, and `BankCustomer extends BankDetails implements CreditCard`**

**A: Adapter (Class Adapter variant)**

`BankCustomer` adapts two incompatible types by extending `BankDetails` (adaptee) and implementing `CreditCard` (target interface). The client codes against `CreditCard` without knowing about `BankDetails`.

```
CreditCard (Target Interface)
    ↑ implements
BankCustomer (Adapter) → extends → BankDetails (Adaptee)
```

---

## DevOps

**Q: What does `git reset --hard HEAD` do?**

**A: To revert to a previous commit, ignoring any changes**

Moves HEAD and **destroys all uncommitted changes** (staged and unstaged).

| Mode | Effect |
|------|--------|
| `--soft` | Moves HEAD only, keeps changes staged |
| `--mixed` | Moves HEAD, unstages changes, keeps files |
| `--hard` | Moves HEAD, **destroys all changes** |

> ⚠️ Use `git stash` before running if unsure — no recovery without `git reflog`

---

**Q: How do you determine which Maven POM contains a missing transitive dependency?**

**A: `mvn -X`**

Enables debug mode, printing the full dependency resolution process. Best used as:
```
mvn dependency:tree -X
```
- ❌ `mvn -A`, `mvn -M`, `mvn -R` — not standard Maven flags

---

## Python

**Q: To open a file `c:/lacrosse.txt` for appending data, we use:**

**A: `outfile = open("c:/lacrosse.txt", 'a')`**

| Mode | Meaning |
|------|---------|
| `'r'` | Read (default) |
| `'w'` | Write (overwrites existing) |
| `'a'` | **Append** (adds to end, creates if not exists) |
| `'b'` | Binary mode (not an append mode) |

**Java equivalent:**
```java
FileWriter fw = new FileWriter("c:/lacrosse.txt", true); // true = append mode
BufferedWriter outfile = new BufferedWriter(fw);
```
