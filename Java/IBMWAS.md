# Setup IBM WAS

1. Video Guide : https://www.youtube.com/watch?v=peROboAYXyY
2. Webiste : https://www.ibm.com/docs/en/call-center/10.0.0?topic=prerequisites-downloading-starting-installation-manager [ Read ]
3. Website : https://www.ibm.com/support/pages/installation-manager-and-packaging-utility-download-documents [ Read ]
4. Click on : Installation Manager Download Links (all versions and platforms)
5. On the right side , there is filter. Select OS
6. I am getting the below mentioned doc

	```
	refresh pack: 1.9.3.1-IBMIM-LINUX-X86_64-20250403_2010 
	IBM Installation Manager Install Kit for all x86_64 Linux versions supported by version 1.9.3.1 
	```

7. Run `sudo ./install` in the DIR
8. [FYI NO ACTIONS ]Installation Manager Directory auto set : /opt/IBM/InstallationManager/eclipse
7. We now have the installation manager available as an app.
8. Lets add the repository for WAS 9.0.5. 
9. Navigate to : https://www.ibm.com/docs/en/was/9.0.5?topic=installation-online-product-repositories-websphere-application-server-offerings
10. What we want -> IBM WebSphere Application Server 	https://www.ibm.com/software/repositorymanager/com.ibm.websphere.BASE.v90
11. Open IBM Installation Manager -> File -> Add Repo -> Enter IBM Username & Password -> Repo Added.
12. Once we click on Instal in 'IBM Installation Manager'. We get both Server & SDK. [ IBM brings its own SDK ]. 
13. So ran into a problem where Version 9.0.5.20 was not working so switching to 9.0.5.0. IBM SDK remains the same top level. [ Show all versions button to rescue ]
14. In the install package selection. I only selected 9.0.5.0-WS-WASProd-IFPH31320 9.0.5000.20201126_1304 . Why ? I don't know
15. 