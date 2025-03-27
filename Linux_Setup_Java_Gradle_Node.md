## Java Installation 
### Download JDK's from https://jdk.java.net/
1. open .bashrc file in preferred editor - $ xed ~/.bashrc & paste the below code 
2. export JAVA_HOME=/usr/lib/jvm/jdk-17.0.2
3. export PATH=$PATH:$JAVA_HOME/bin
4. Final Command :\
$ sudo update-alternatives --install "/usr/bin/java" "java" "/usr/lib/jvm/jdk-17.0.2/bin/java" 1\
$ sudo update-alternatives --set java /home/sbala/Documents/Tools/jdk-17/bin/java

Source : https://community.linuxmint.com/tutorial/view/1372

```
syndicate@syndicate-H610M-H-DDR4 / $ java -version
openjdk version "17.0.2" 2022-01-18
OpenJDK Runtime Environment (build 17.0.2+8-86)
OpenJDK 64-Bit Server VM (build 17.0.2+8-86, mixed mode, sharing)

```

## Gradle 
1. export PATH=$PATH:/opt/gradle/gradle-8.2.1/bin

syndicate@syndicate-H610M-H-DDR4:~$ gradle -v

```bash
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
   
```bash
syndicate@syndicate-H610M-H-DDR4:~$ npm -v
9.6.7
syndicate@syndicate-H610M-H-DDR4:~$ node -v
v18.17.0
```

## How to Add Applications Icons 

```
Create a file - sudo xed /usr/share/applications/STS.desktop

[Desktop Entry]
Name=SpringSource Tool Suite
Comment=SpringSource Tool Suite
Exec=~/springsource/sts-3.4.0-RELEASE/STS
Icon=~/springsource/sts-3.4.0-RELEASE/icon.xpm
StartupNotify=true
Terminal=false
Type=Application
Categories=Development;IDE;Java;
```

## Linux Terminal Themes

1. URL : https://github.com/Gogh-Co/Gogh
2. https://gogh-co.github.io/Gogh/
3. https://askubuntu.com/questions/881949/ugly-color-for-directories-in-gnome-terminal

## Cat Command Enhanced

1. https://www.cyberciti.biz/open-source/bat-linux-command-a-cat-clone-with-written-in-rust/
2. `$ sudo apt install bat`
3. Update alias in .bashrc - `alias cat="batcat"`

## Git Terminal [ bash.rc file ]

```
parse_git_branch() {
     git branch 2> /dev/null | sed -e '/^[^*]/d' -e 's/* \(.*\)/(\1)/'
}
export PS1="\u@\h \[\e[32m\]\w \[\e[91m\]\$(parse_git_branch)\[\e[00m\]$ "
```

## Terminal Themes 

1. Terminal Themes  :  bash -c "$(wget -qO- https://git.io/vQgMr)"

## Font Settings in VSCode

'JetBrains Mono', monospace
