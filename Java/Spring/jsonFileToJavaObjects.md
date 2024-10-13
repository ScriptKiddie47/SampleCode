# JSON FILE TO JAVA OBJECTS

## JSON FILE - HeathDashboard.json

```json
[
  {
    "service-name": "Syndicate"
  }
]
```

## JAVA CODE 

```java
package com.scripter.htmx.creator.controller;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class ClientRestController {

    private List<HeathDashBoard> heathDashBoards = new ArrayList<>();
    @Autowired
    private ResourceLoader resourceLoader;

    @GetMapping("/health")
    public List<HeathDashBoard> getHeathDashBoards() throws IOException {
        Resource resource = resourceLoader.getResource("classpath:HeathDashboard.json");
        ObjectMapper objectMapper = new ObjectMapper();
        heathDashBoards = objectMapper.readValue(resource.getInputStream(), new  TypeReference<List<HeathDashBoard>>(){});
        return heathDashBoards;
    }
}
record HeathDashBoard(@JsonProperty("service-name") String serviceName){}
```
