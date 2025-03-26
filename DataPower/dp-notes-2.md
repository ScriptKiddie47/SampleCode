User : admin
Password : admin

=============

user : dev-user-1
password : dev-user-1
Domain Access : Lab

============

DP Endpoint : https://localhost:9090/
DP Gateway : eth0_ipv4_1 : 172.17.0.2

===========

XML Management Interface  --> Administrative state --> Enabled

   URL : https://localhost:5550/service/mgmt/amp/3.0

   Request :

   <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:ns="http://www.datapower.com/schemas/appliance/management/3.0">
      <soapenv:Header/>
      <soapenv:Body>
         <ns:GetDomainListRequest/>
      </soapenv:Body>
   </soapenv:Envelope>

   Response:

   <env:Envelope xmlns:env="http://schemas.xmlsoap.org/soap/envelope/">
      <env:Body>
         <amp:GetDomainListResponse xmlns:amp="http://www.datapower.com/schemas/appliance/management/3.0">
            <amp:Domain>default</amp:Domain>
            <amp:Domain>Lab</amp:Domain>
            <amp:Domain>Test</amp:Domain>
         </amp:GetDomainListResponse>
      </env:Body>
   </env:Envelope>

===============

Web Service Proxy 1: ( Didn't work )

   WSDL  -> https://www.dataaccess.com/webservicesserver/NumberConversion.wso
   DataPower Web Service Proxy WSDL -> http://localhost:8002/webservicesserver/NumberConversion?wsdl

Web Service Proxy 2: ( Didn't work )

   WSDL -> http://localhost:8080/ws/countries.wsdl ( From : https://spring.io/guides/gs/producing-web-service )
   DataPower WSDL: http://localhost:8001/wsc/countries.wsdl

========

Rule Direction 

Server to Client : Resonse from System to DP
Client to Server : Request from DP to System


============

MPG : 
   
   MPG we can handle JSON
   


































