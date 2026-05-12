# KeyTerms

1. Consistent Hashing : Take Request ID and Hash It.

# How to Handle 1 Million Requests

1. LB,Caching,Async - Event Driven Architecture ( Queue Everything), Multi Region. DataBase Sharding, Health Checks, Metrics, 
1. Build Custom Solutions or leverage other available infra. Edge Computing ( Location Based )

# How to Handle Java Failure on a Microservice

Identify Bottlenecks - High CPU,Out of memory,Threads stuck,Queue full
Increase thread pool, Never let threads block indefinitely,Database connection pool, introduce caching, Scale, 
Code Smell, Back-Pressure [Server End] ( Don't accept more than you can handle. Push back )
Exponential Back-off [Client End] ( Client tells itself to backoff )
DLQ,Spring Retry,Resillence4j( Circuit Breaker )


## Tools

1. Code Quality - SonarQube

# Cache Policy

1. LRU - Least Recently Used
1. Thrashing - Constantly putting data in & out of cache
1. Write Through - ( Date Update on Cache then on DB) or Write Back Cache ( Hit DB then make entry in cache)
