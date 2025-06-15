# Actuator

1. Once we navigate to `http://localhost:8080/actuator`
1. We observe some default properties

```json
{
  "_links": {
    "self": {
      "href": "http://localhost:8080/actuator",
      "templated": false
    },
    "health": {
      "href": "http://localhost:8080/actuator/health",
      "templated": false
    },
    "health-path": {
      "href": "http://localhost:8080/actuator/health/{*path}",
      "templated": true
    }
  }
}
```

1. We can expand on these by adding properties

```yaml
management:
  endpoints:
    web:
      exposure:
        include: "*"
```

1. Now navigating to `http://localhost:8080/actuator` reveals a lot more info

```json
{
  "_links": {
    "self": {
      "href": "http://localhost:8080/actuator",
      "templated": false
    },
    "beans": {
      "href": "http://localhost:8080/actuator/beans",
      "templated": false
    },
    "caches-cache": {
      "href": "http://localhost:8080/actuator/caches/{cache}",
      "templated": true
    },
    "caches": {
      "href": "http://localhost:8080/actuator/caches",.
      .
      .
      .
```

1. We can also fine tune props

```yaml
management:
  endpoints:
    web:
      exposure:
        include: health,metrics
```