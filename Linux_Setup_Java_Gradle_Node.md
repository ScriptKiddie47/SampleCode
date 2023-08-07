## Java Installation 
### Download JDK's from https://jdk.java.net/
1. open .bashrc file in preferred editor - $ xed ~/.bashrc & paste the below code 
2. export JAVA_HOME=/usr/lib/jvm/jdk-17.0.2
3. export PATH=$PATH:$JAVA_HOME/bin
4. Note :  The java -version command will still show java 11 or existing java installation but its fine. You are on the JDK 17 only

```
syndicate@syndicate-H610M-H-DDR4:~$ java -version
openjdk version "11.0.20" 2023-07-18
OpenJDK Runtime Environment (build 11.0.20+8-post-Ubuntu-1ubuntu122.04)
OpenJDK 64-Bit Server VM (build 11.0.20+8-post-Ubuntu-1ubuntu122.04, mixed mode, sharing)
```

## Gradle 
1. export PATH=$PATH:/opt/gradle/gradle-8.2.1/bin

syndicate@syndicate-H610M-H-DDR4:~$ gradle -v

```
------------------------------------------------------------
Gradle 8.2.1
------------------------------------------------------------

Build time:   2023-07-10 12:12:35 UTC
Revision:     a38ec64d3c4612da9083cc506a1ccb212afeecaa

Kotlin:       1.8.20
Groovy:       3.0.17
Ant:          Apache Ant(TM) version 1.10.13 compiled on January 4 2023
JVM:          17.0.2 (Oracle Corporation 17.0.2+8-86)
OS:           Linux 5.15.0-78-generic amd64
```

## Node 
### Download NODE Binary from https://nodejs.org/en/download

1. Set the path export PATH=$PATH:/opt/node/node-v18.17.0-linux-x64/bin and you are good to go.
   
```
syndicate@syndicate-H610M-H-DDR4:~$ npm -v
9.6.7
syndicate@syndicate-H610M-H-DDR4:~$ node -v
v18.17.0
```

