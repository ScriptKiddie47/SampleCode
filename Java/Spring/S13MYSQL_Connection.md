# MYSQL DOCKER SETUP

```bash
docker run --detach --env MYSQL_ROOT_PASSWORD=dummypassword --env MYSQL_USER=social-media-user --env MYSQL_PASSWORD=dummypassword --env MYSQL_DATABASE=social-media-database --name mysql --publish 3306:3306 mysql:8-oracle
``` 

### Gradle Dependency
```groovy
runtimeOnly 'com.mysql:mysql-connector-j'
```

### Spring YAML properties

```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/social-media-database
    username: social-media-user
    password: dummypassword
  jpa:
    show-sql : true
    hibernate:
      ddl-auto: update
    properties:
      dialect: org.hibernate.dialect.MySQLDialect
```


Note : 
Use mysql-connector-j instead of mysql-connector-java if you are using Spring Boot 3.1 or greater.
Remember: groupId is a little different (com.mysql instead of mysql)

## Connect to SHELL using `mysqlsh` from docker - terminal

mysqlsh is already present in the image.

```bash
MySQL Error 1045 (28000): Access denied for user 'root'@'localhost' (using password: YES)
sh-4.4# mysqlsh
Please provide the password for 'root@/var%2Frun%2Fmysqld%2Fmysqld.sock': *************
MySQL Shell 8.2.1

MySQL  localhost  JS > \connect social-media-user@localhost:3306
Creating a session to 'social-media-user@localhost:3306'
Please provide the password for 'social-media-user@localhost:3306': *************

 MySQL  localhost:3306 ssl  JS > \use social-media-database
Default schema set to `social-media-database`.
 MySQL  localhost:3306 ssl  social-media-database  JS > \sql
Switching to SQL mode... Commands end with ;
Fetching global names, object names from `social-media-database` for auto-completion... Press ^C to stop.
Error during auto-completion cache update: Access denied; you need (at least one of) the PROCESS privilege(s) for this operation
 MySQL  localhost:3306 ssl  social-media-database  SQL > select * from user_details;
+----+------------+------+
| id | birthday   | name |
+----+------------+------+
|  1 | 1999-12-18 | John |
+----+------------+------+
1 row in set (0.0008 sec)
 MySQL  localhost:3306 ssl  social-media-database  SQL > select * from post;;
+----+-------------------------------------+---------+
| id | description                         | user_id |
+----+-------------------------------------+---------+
|  1 | I want to learn docker fundamentals |       1 |
+----+-------------------------------------+---------+
1 row in set (0.0006 sec)
ERROR: 1065 (42000): Query was empty
 MySQL  localhost:3306 ssl  social-media-database  SQL > 
```