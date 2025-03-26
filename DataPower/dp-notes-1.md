Source: https://www.youtube.com/watch?v=pA2m0HNE6Ao

# Installing IBM Datapower Appliance

    1. Prerequisite is docker.
    2. Image pull from IBM [ Cannot be used for commercial purpose ]
    3. Image Location : docker pull icr.io/integration/datapower/datapower-limited:10.0.3.0

## Commands

    1. Make sure docker is up & running

```ps
$ docker image pull   icr.io/integration/datapower/datapower-limited:10.0.3.0
```
1. Create some folders, provide access

```ps
Syn: ~/Documents/CodeSource 
$ mkdir datapower
Syn: ~/Documents/CodeSource 
$ cd datapower/
Syn: ~/Documents/CodeSource/datapower 
$ mkdir cert config local
Syn: ~/Documents/CodeSource/datapower 
$ cd ..
Syn: ~/Documents/CodeSource 
$ chmod -R 777 datapower
Syn: ~/Documents/CodeSource 
$ cd datapower/
Syn: ~/Documents/CodeSource/datapower 
$ ls -al
total 20
drwxrwxrwx  5 syndicate syndicate 4096 Aug  9 16:24 .
drwxrwxr-x 20 syndicate syndicate 4096 Aug  9 16:22 ..
drwxrwxrwx  2 syndicate syndicate 4096 Aug  9 16:24 cert
drwxrwxrwx  2 syndicate syndicate 4096 Aug  9 16:24 config
drwxrwxrwx  2 syndicate syndicate 4096 Aug  9 16:24 local
Syn: ~/Documents/CodeSource/datapower
Syn: ~ 
$ docker image ls
REPOSITORY                                       TAG                  IMAGE ID       CREATED        SIZE
icr.io/integration/datapower/datapower-limited   10.0.3.0             54dfd20a14cc   3 years ago    1.72GB
```

1. Copy Image and keep it with you : 54dfd20a14cc.
2. Use it in a below command to spawn a container using mounts

```ps
Syn: ~/Documents/CodeSource/datapower 
$ docker run -it -v $PWD/config:/opt/ibm/datapower/drouter/config -v $PWD/local:/opt/ibm/datapower/drouter/local -v $PWD/cert:/opt/ibm/datapower/root/secure/usrcerts  -e DATAPOWER_ACCEPT_LICENSE=true -e DATAPOWER_INTERACTIVE=true -p 9090:9090 -p 5550:5550 -p 9022:22 -p 5554:5554 -p 8000-8010:8000-8010 --name idg 54dfd20a14cc
```

1. It gives a login prompt. UserID & Password is 'admin'


```ps
20240809T105953.265Z [0x00350014][mgmt][notice] quota-enforcement-server(QuotaEnforcementServer): tid(831): Operational state up
login: admin
Password: *****

Welcome to IBM DataPower Gateway console configuration. 
Copyright IBM Corporation 1999, 2021 

Version: IDG.10.0.3.0 build 333705 on Jun 16, 2021 9:06:57 PM
Delivery type: CD
Serial number: 0000001

idg# co
Global mode
idg(config)# web-mgmt
Modify Web Management Service configuration

idg(config web-mgmt)# admin-state enabled
idg(config web-mgmt)# exit
idg(config)# 20240809T110234.246Z [0x8100003f][mgmt][notice] domain(default): tid(303): Domain configuration has been modified.
20240809T110234.248Z [0x00350014][mgmt][notice] web-mgmt(WebGUI-Settings): tid(303): Operational state up
idg(config)# write mem
Overwrite previously saved configuration? Yes/No [y/n]: y
Configuration saved successfully.
idg(config)# 20240809T110312.407Z [0x8100000c][mgmt][notice] : tid(7176): Saved current configuration to 'config:///auto-startup.cfg'
20240809T110312.407Z [0x81000040][mgmt][notice] domain(default): tid(7176): Domain configuration has been saved.
20240809T110312.423Z [0x8100000c][mgmt][notice] : tid(111): Saved current configuration to 'config:///auto-user.cfg'
idg(config)# exit
idg# exit
Goodbye.
db3a1954e956
Unauthorized access prohibited.
```

1. To open in Browser: https://localhost:9090
2. Use 'admin' as username & password.

### Shutting Down and Restating 

1. 
