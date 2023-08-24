# Kakfa on Docker

1. Start zookeeper
2. Start Kafka
3. Kafka depends on Zookeeper

### Lets execute docker-compose.yaml file

`$ docker-compose -f docker-compose.yaml up -d`

```
syndicate@syndicate-H610M-H-DDR4 ~ $ docker ps
CONTAINER ID   IMAGE                      COMMAND                  CREATED         STATUS         PORTS                                                  NAMES
a1fa3511649d   bitnami/kafka:latest       "/opt/bitnami/script…"   7 seconds ago   Up 6 seconds   0.0.0.0:9092->9092/tcp                                 kakfa
8597f41d9ab6   bitnami/zookeeper:latest   "/opt/bitnami/script…"   8 seconds ago   Up 7 seconds   2888/tcp, 3888/tcp, 0.0.0.0:2181->2181/tcp, 8080/tcp   zookeeper
syndicate@syndicate-H610M-H-DDR4 ~ $ 
```
### Connect to the Kafa Container

```
syndicate@syndicate-H610M-H-DDR4 ~ $ docker exec -it kakfa /bin/sh
$ ls -al
total 88
drwxr-xr-x   1 root root 4096 Aug 24 18:42 .
drwxr-xr-x   1 root root 4096 Aug 24 18:42 ..
-rwxr-xr-x   1 root root    0 Aug 24 18:42 .dockerenv
drwxr-xr-x   2 root root 4096 Aug 21 13:51 bin
drwxr-xr-x   1 root root 4096 Aug 21 13:51 bitnami
```

### Create Topics

Once inside we will need to navigate to `/opt/bitnami/kafka/bin`

Post that we can execute one topic - 
1. `kafka-topics.sh --create --topic test-topic --bootstrap-server localhost:9092 --replication-factor 1 --partitions 1`
2. `kafka-topics.sh --create --topic news --bootstrap-server localhost:9092 --replication-factor 1 --partitions 1`

Lets List down the topics

```
$ kafka-topics.sh --list --bootstrap-server localhost:9092
news
test-topic
```


### Lets exit
`Control + D to exit out of the docker container`

```
syndicate@syndicate-H610M-H-DDR4 ~/Documents/CodeSource/Github/SampleCode/Kafka (master)$ docker-compose -f docker-compose.yaml down
Stopping kakfa     ... done
Stopping zookeeper ... done
Removing kakfa     ... done
Removing zookeeper ... done
Removing network kafka_default
syndicate@syndicate-H610M-H-DDR4 ~/Documents/CodeSource/Github/SampleCode/Kafka (master)$ 
```