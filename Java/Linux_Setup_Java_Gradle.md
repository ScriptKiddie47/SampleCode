## Java Installation 
### Download JDK's from https://jdk.java.net/
open .bashrc file in preferred editor - $ xed ~/.bashrc & paste the below code 
export JAVA_HOME=/usr/lib/jvm/jdk-17.0.2
export PATH=$PATH:$JAVA_HOME/bin

Gradle 
export PATH=$PATH:/opt/gradle/gradle-8.2.1/bin

Note : The java -version command will still show java 11 or existing java installation but its fine.

syndicate@syndicate-H610M-H-DDR4:~$ java -version
openjdk version "11.0.20" 2023-07-18
OpenJDK Runtime Environment (build 11.0.20+8-post-Ubuntu-1ubuntu122.04)
OpenJDK 64-Bit Server VM (build 11.0.20+8-post-Ubuntu-1ubuntu122.04, mixed mode, sharing)

You are on the JDK 17 only
