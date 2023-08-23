# Redis on Docker

### Run Redis Container
`docker run --name redis -p 6379:6379 redis`

### Connect to Redis CLI
Now we can go inside the container and try using the CLI
-it ==> Interactive Terminal

```
syndicate@syndicate-H610M-H-DDR4 ~ $ 
127.0.0.1:6379>
```

### Lets add some key-value data in the Redis Server & get the value back
```
127.0.0.1:6379> set name "Syndcate"
OK
127.0.0.1:6379> 
127.0.0.1:6379> get name
"Syndcate"
127.0.0.1:6379>
```


### Exists and Delete
```
127.0.0.1:6379> exists name
(integer) 1
127.0.0.1:6379> del name
(integer) 1
127.0.0.1:6379> exists name
(integer) 0
```

### Append
```
127.0.0.1:6379> append name 'Syndicate'
(integer) 9
127.0.0.1:6379> get name
"Syndicate"
127.0.0.1:6379> append name '47A'
(integer) 12
127.0.0.1:6379> get name
"Syndicate47A"
```


### Subscribe - Shift to a push model , we wait for things to happen. ( open a new terminal to push data )

```
127.0.0.1:6379> subscribe newvideos
1) "subscribe"
2) "newvideos"
3) (integer) 1

Everything stops

( New Terminal - Reconnect to the redis CLI)

syndicate@syndicate-H610M-H-DDR4 ~ $ docker exec -it redis redis-cli
127.0.0.1:6379> 
127.0.0.1:6379> 
127.0.0.1:6379> publish newvideos "Redis Crash Course is up !"
(integer) 1
127.0.0.1:6379> 

( In the old terminal - we see)

127.0.0.1:6379> subscribe newvideos
1) "subscribe"
2) "newvideos"
3) (integer) 1
1) "message"
2) "newvideos"
3) "Redis Crash Course is up !"


```


