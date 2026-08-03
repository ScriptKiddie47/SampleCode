# KeyTerms

1. Consistent Hashing : Map both nodes and requests onto a hash ring. Each request is served by the next node clockwise on the ring. Adding/removing a node only reshuffles a fraction of keys (vs. full rehash with modulo). Virtual nodes (vnodes) are used to distribute load evenly across physical nodes.

# How to Handle 1 Million Requests

1. LB,Caching,Async - Event Driven Architecture ( Queue Everything), Multi Region. DataBase Sharding, Health Checks, Metrics, 
1. Build Custom Solutions or leverage other available infra. Edge Computing ( Location Based )

# How to Handle Java Failure on a Microservice

Identify Bottlenecks - High CPU,Out of memory,Threads stuck,Queue full
Increase thread pool, Never let threads block indefinitely,Database connection pool, introduce caching, Scale, 
Code Smell, Back-Pressure [Server End] ( Don't accept more than you can handle. Push back )
Exponential Back-off [Client End] ( Client tells itself to backoff )
DLQ,Spring Retry,Resilience4j( Circuit Breaker )


## Tools

1. Code Quality - SonarQube

# Cache Policy

1. LRU - Least Recently Used
1. Thrashing - Constantly putting data in & out of cache
1. Cache Aside - App checks cache first; on miss, loads from DB and populates cache (most common)
1. Write Through - ( Data Update on Cache then on DB — always consistent, but write latency is higher )
1. Write Back Cache - Write to cache only, async flush to DB later ( fast writes, risk of data loss )
1. Read Through - Cache sits in front of DB; cache handles miss automatically

# Microservice Communication 

How do two microservices communicate with each other ?
- REST API's via HTTP calls
- Message Queue - Event Driver - Rabbit MQ & Kafka
- Grpc 

When would we use sync vs async communication ?
    - sync - Need immediate Response
    - async - Result is not needed immediately

# Microservice Architecture

Circuit Breaker : Stops repeated calls to a failing service. Open,Closed,Open-Closed State | failureRateThreshold,slidingWindowSize,waitDuration
Saga : [Distributed Transaction] Orchestrator Saga | Choreographed Saga-> Compensating Transaction to undo effects
Strangler Pattern : Monolithic to Microservice , Strangler Facade ( Reroute[API Gateway] Request to Legacy & Modern)
CQRS : Command Query Responsibility Segregation -> Command involves` Create,Update,Delete` & Query is for `Read`

# Production Issue

OutOfMemoryError: Java heap space
IBM Liberty : jvm.options file : -Xms512m -Xmx2g

# Database Sharding

Horizontal Partitioning — split rows across multiple DB instances by a shard key (e.g. user_id % N).
- Range Sharding : shard by value range (e.g. A–M, N–Z) — simple but can cause hotspots
- Hash Sharding : hash(key) % shards — even distribution, harder to range-query
- Directory Sharding : lookup table maps keys to shards — flexible, but lookup table is a SPOF

Problems: Cross-shard joins are hard, rebalancing when adding shards is costly (consistent hashing helps).
Vertical Partitioning : split by columns — e.g. separate table for large blobs or infrequently accessed fields.

# Achieve High Availability

Route 53 ( DNS ) -> CloudFront ( CDN ) -> ELB -> EKS Auto Scaling -> RDS Multi Region / S3 ...

CAP Theorem

A distributed system can only guarantee 2 of 3:
- Consistency (C) : Every read gets the most recent write
- Availability (A) : Every request gets a response (not guaranteed to be latest)
- Partition Tolerance (P) : System works despite network partitions (always required in practice)

When a partition occurs you must choose C or A:
- CP : Zookeeper, HBase, etcd — returns error if can't guarantee consistency
- AP : Cassandra, DynamoDB, CouchDB — returns stale data rather than failing
- CA : Not realistic in distributed systems (can't ignore partitions)

PACELC Extension : Even without partition, trade-off between Latency and Consistency.

# Common System Design Problems

Rate Limiter : Token Bucket (smooth bursts) | Leaky Bucket (strict rate) | Fixed/Sliding Window Counter. Store counts in Redis with TTL. Distributed rate limiting needs Lua scripts or Redis INCR for atomicity.

URL Shortener : Hash(long URL) → 6-char base62 key. Store in DB (key → long URL). Redirect via 301 (cached by browser) or 302 (trackable). Handle collisions by appending counter.

Chat System (WhatsApp) : WebSocket for real-time push. Message stored in Cassandra (time-series friendly). Presence service (online/offline) via heartbeats. Fan-out: write to each recipient's inbox or use pub/sub.

News Feed (Twitter/Instagram) : Push model (fanout on write) — precompute feed on post, fast reads but expensive for celebrities. Pull model (fanout on read) — compute at read time, cheaper writes. Hybrid: push for regular users, pull for high-follower accounts.

Search Autocomplete : Trie stored in cache, updated async. Limit to top-K suggestions per prefix using frequency scores.

