# Spring Mongo Without Starter Mongo Usage

## Dependencies

```gradle
implementation 'org.mongodb:mongodb-driver-sync:4.11.1'
implementation 'org.mongodb:bson:4.11.1'
implementation 'org.mongodb:mongodb-driver-core:4.11.1'
```

## Java Code

1. Important to remove the AutoConfiguration of Spring 

```java

public record Product(String name,String description,String price) {
}


@SpringBootApplication
@EnableAutoConfiguration(exclude = {MongoAutoConfiguration.class})
public class JsmtdApplication {
```

1. Simple Product record + Controller

```java
package com.scripter.jsmtd;

import static com.mongodb.MongoClientSettings.getDefaultCodecRegistry;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;


import org.bson.codecs.configuration.CodecProvider;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;


import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

@RestController
public class Controller {
	private static final Logger log = LoggerFactory.getLogger(Controller.class);

	
	@GetMapping("/")
	public String getMethodName() {
		return "Hello There";
	}
	
	@GetMapping("/product")
	public List<Product> getAllProducts() {
		List<Product> list = new ArrayList<>();
		CodecProvider pojoCodecProvider = PojoCodecProvider.builder().automatic(true).build();
        CodecRegistry pojoCodecRegistry = fromRegistries(getDefaultCodecRegistry(), fromProviders(pojoCodecProvider));
        String uri = "mongodb+srv://noobuser:*****@scriptkiddie-cluster.bc109kt.mongodb.net";
        try (MongoClient mongoClient = MongoClients.create(uri)) {
            MongoDatabase database = mongoClient.getDatabase("product_list").withCodecRegistry(pojoCodecRegistry);
            MongoCollection<Product> collection = database.getCollection("product", Product.class);
            collection.find().into(list);
            log.info("Product List : " + list);
        }catch (Exception e) {
			e.printStackTrace();
		}
        return list;
	}
}
```

Output :


```json
[{"name":"Iphone 13","description":"Latest & Greatest","price":"12000"},{"name":"Iphone 14","description":"Latest & Greatest v2","price":"98000"}]
```