# JSON response

1. Simple Controller

```java
@GetMapping(path = "/hello-world-bean")
public HelloWorldBean helloWorldBean() {
	return new HelloWorldBean("Hello World");
}
```

2. Class

```java
public class HelloWorldBean {

	private String message;
	public HelloWorldBean(String message) {
		this.message = message;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}

```

We just return the class object , spring boot takes care of printing it out in JSON

3. Curl

```bash
$ curl --location 'localhost:8080/hello-world-bean'
{"message":"Hello World"}
```

# Filitering 

1. Serialization - Convert object to a stream ( ex: JSON )
2. Serialization - Framework - Jackson API
3. Customize field names in a response
   - @JSONProperty 

	```java
	import com.fasterxml.jackson.annotation.JsonProperty;

	@JsonPrperty("user_name")
	private String name; // POJO class Memeber
	```
	```bash
	$ curl 'http://localhost:8080/users'
	[{"id":1,"birthday":"1993-12-19","user_name":"Adam"},{"id":2,"birthday":"1998-12-19","user_name":"Eve"},{"id":3,"birthday":"2003-12-19","user_name":"Jim"}]
	```

4. Filtering
   - Static - Same filtering for bean across the rest API - @JsonIgnore,@JsonIgnoreProperties
   - Dynamic - Customize filtering for a bean for a specific REST API

### Static Filtering

5. Static Filtering - Java POJO Class
	```java
	import com.fasterxml.jackson.annotation.JsonIgnore;

	public class SomeBean {
		private String field1;
		@JsonIgnore
		private String field2; //Suppose this is a password so we don't want to return it.
		private String field3;
		// Constructor,Getters,Setters
	```
6. Static Filtering - Controller

	```java
		@GetMapping("/filtering")
		public SomeBean filtering() {
			return new SomeBean("value1","value2","value3");
		}
	```
7. Static Filtering - Curl it
	```bash
	$ curl 'http://localhost:8080/filtering'
	{"field1":"value1","field3":"value3"}
	```

8. Static Filtering - We can also do it at the class level. Curl will produce the same response
	```java
	@JsonIgnoreProperties("field2")
	public class SomeBean {
		private String field1;
		private String field2;
		private String field3;
	```

### Dynamic Filtering

9. Dynamic Filtering - Lets suppose we have 2 rest-api ( controller function ) & we wan't filtering for one of them or different filtering

10. Pojo Class
	```java
	import com.fasterxml.jackson.annotation.JsonFilter;

	@JsonFilter("someBeanFilter") // Keep a note of thisfield , will be used again
	public class SomeBean {
		private String field1;
		private String field2;
		private String field3;
		// Constructor,Setters,Getters
	```
11. Controller
	```java
	import org.springframework.http.converter.json.MappingJacksonValue;
	import com.fasterxml.jackson.databind.ser.FilterProvider;
	import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
	import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

	@RestController
	public class FilteringController {

		@GetMapping("/filtering1")
		public MappingJacksonValue filtering1() {
			SomeBean bean =  new SomeBean("value1","value2","value3");
			MappingJacksonValue mapJValue  = new MappingJacksonValue(bean);
			SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field1","field3"); // We get back everything except field2
			FilterProvider filters = new SimpleFilterProvider().addFilter("someBeanFilter", filter );
			mapJValue.setFilters(filters);
			return mapJValue;

		}

		@GetMapping("/filtering2")
		public MappingJacksonValue filtering2() {
			List<SomeBean> list = Arrays.asList(new SomeBean("value4", "value5", "value6"),
					new SomeBean("value7", "value8", "value9"));

			MappingJacksonValue mapJValue  = new MappingJacksonValue(list);
			SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field2","field3"); // We get back everything except field1
			FilterProvider filters = new SimpleFilterProvider().addFilter("someBeanFilter", filter );
			mapJValue.setFilters(filters);
			return mapJValue;
		}
	}
	```

12. Lets Curl it
	```bash
	$ curl 'http://localhost:8080/filtering1'
	{"field1":"value1","field3":"value3"}
	$ curl 'http://localhost:8080/filtering2'
	[{"field1":"value4","field3":"value6"},{"field1":"value7","field3":"value9"}]
	```

## Deep Dive

### How does the HelloWorldBean gets converted into JSON

1. @ResponseBody - Its found in the @interface RestController which basically says we return the message as is
2. org.springframework.boot.autoconfigure.http.JacksonHttpMessageConvertersConfiguration

