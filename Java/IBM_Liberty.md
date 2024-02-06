# IBM Liberty

Reference : https://www.youtube.com/watch?v=JzycB3E6r08

## Download IBM Liberty

1. Source : https://www.ibm.com/support/pages/websphere-liberty-developers
2. Download : WebSphere Liberty Jakarta EE 10 24.0.0.1 or higher
3. No registration required.
4. Project Structure

```bash
sbala@sbala-Nitro-AN515-52 /media/sbala/Data/JavaServers/wlp-jakartaee10-24.0.0.1 
$ tree -L 2
.
└── wlp
    ├── bin
    ├── CHANGES.TXT
    ├── clients
    ├── Copyright.txt
    ├── dev
    ├── lafiles
    ├── lib
    ├── README.TXT
    ├── templates
    └── usr
```

## Create Sample Server

1. Read the README.TXT
2. Create a Liberty app

```bash
sbala@sbala-Nitro-AN515-52 /media/sbala/Data/JavaServers/wlp-jakartaee10-24.0.0.1/wlp 
$ bin/server create helloworld-liberty-app
Server helloworld-liberty-app created.
```

3. Folder Structure

```
sbala@sbala-Nitro-AN515-52 /media/sbala/Data/JavaServers/wlp-jakartaee10-24.0.0.1/wlp/usr/servers 
$ tree
.
└── helloworld-liberty-app
    ├── apps
    ├── dropins
    ├── server.env
    ├── server.xml
    └── workarea
```

4. We can start the app , however it has no jar,ear,war files.
6. Get the jar from https://openliberty.io/guides/maven-intro.html#creating-the-project-pom-file, https://github.com/OpenLiberty/guide-maven-intro/tree/prod/finish.
7. Perform a `mvn package` & obtain the war file.
8. Copy paste it to apps folder in your newly created server
9. Make an entry in the server xml to point to the newly added war file. - https://www.ibm.com/docs/en/was-liberty/base?topic=liberty-deploying-web-application


```xml
    <application context-root="ServletSample" type="war" id="ServletSample"
    location="ServletSample.war" name="ServletSample"/>
```
10. File Structure

```
$ tree
.
└── helloworld-liberty-app
    ├── apps
    │   └── ServletSample.war
    ├── dropins
    ├── server.env
    ├── server.xml
    └── workarea
```

11. Run the app 

```bash
sbala@sbala-Nitro-AN515-52 /media/sbala/Data/JavaServers/wlp-jakartaee10-24.0.0.1/wlp 
$ bin/server list

The following servers are defined relative to the user directory /media/sbala/Data/JavaServers/wlp-jakartaee10-24.0.0.1/wlp/usr.

helloworld-liberty-app
sbala@sbala-Nitro-AN515-52 /media/sbala/Data/JavaServers/wlp-jakartaee10-24.0.0.1/wlp 
$ bin/server run helloworld-liberty-app

Launching helloworld-liberty-app (WebSphere Application Server 24.0.0.1/wlp-1.0.85.cl240120240115-2042) on OpenJDK 64-Bit Server VM, version 17+35-2724 (en_IN)
[AUDIT   ] CWWKE0001I: The server helloworld-liberty-app has been launched.
[AUDIT   ] CWWKE0100I: This product is licensed for development, and limited production use. The full license terms can be viewed here: https://public.dhe.ibm.com/ibmdl/export/pub/software/websphere/wasdev/license/base_ilan/ilan/24.0.0.1/lafiles/en.html
[WARNING ] CWWKS3103W: There are no users defined for the BasicRegistry configuration of ID com.ibm.ws.security.registry.basic.config[basic].
[AUDIT   ] CWWKZ0058I: Monitoring dropins for applications.
[AUDIT   ] CWPKI0820A: The default keystore has been created using the 'keystore_password' environment variable.
[AUDIT   ] CWPKI0803A: SSL certificate created in 1.283 seconds. SSL key file: /media/sbala/Data/JavaServers/wlp-jakartaee10-24.0.0.1/wlp/usr/servers/helloworld-liberty-app/resources/security/key.p12
[AUDIT   ] CWWKS4104A: LTPA keys created in 0.300 seconds. LTPA key file: /media/sbala/Data/JavaServers/wlp-jakartaee10-24.0.0.1/wlp/usr/servers/helloworld-liberty-app/resources/security/ltpa.keys
[AUDIT   ] CWWKI0001I: The CORBA name server is now available at corbaloc:iiop:localhost:2809/NameService.
[AUDIT   ] CWWKT0016I: Web application available (default_host): http://localhost:9080/ServletSample/
[AUDIT   ] CWWKZ0001I: Application ServletSample started in 1.989 seconds.
[AUDIT   ] CWWKF0012I: The server installed the following features: [appAuthentication-3.0, appAuthorization-2.1, appClientSupport-2.0, appSecurity-5.0, batch-2.1, beanValidation-3.0, cdi-4.0, concurrent-3.0, connectors-2.1, distributedMap-1.0, enterpriseBeans-4.0, enterpriseBeansHome-4.0, enterpriseBeansLite-4.0, enterpriseBeansPersistentTimer-4.0, enterpriseBeansRemote-4.0, expressionLanguage-5.0, faces-4.0, jakartaee-10.0, jdbc-4.2, jndi-1.0, jsonb-3.0, jsonp-2.1, mail-2.1, managedBeans-2.0, mdb-4.0, messaging-3.1, messagingClient-3.0, messagingSecurity-3.0, messagingServer-3.0, pages-3.1, persistence-3.1, persistenceContainer-3.1, restfulWS-3.1, restfulWSClient-3.1, servlet-6.0, ssl-1.0, transportSecurity-1.0, webProfile-10.0, websocket-2.1, xmlBinding-4.0, xmlWS-4.0].
[AUDIT   ] CWWKF0011I: The helloworld-liberty-app server is ready to run a smarter planet. The helloworld-liberty-app server started in 22.925 seconds.
```

Curl it ! 

```bash
sbala@sbala-Nitro-AN515-52 /media/sbala/Data/JavaServers/wlp-jakartaee10-24.0.0.1/wlp 
$ curl http://localhost:9080/ServletSample/servlet
Hello! How are you today?
```

12. We good ! 
