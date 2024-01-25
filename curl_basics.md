# Cheat Dock 
https://curl.se/docs/manual.html

### Get HTTP Call

```bash
syndicate@syndicate-H610M-H-DDR4 ~/Desktop/Curl_Learn $ curl https://reqres.in/api/users/1
{"data":{"id":1,"email":"george.bluth@reqres.in","first_name":"George","last_name":"Bluth","avatar":"https://reqres.in/img/faces/1-image.jpg"},"support":{"url":"https://reqres.in/#support-heading","text":"To keep ReqRes free, contributions towards server costs are appreciated!"}}syndicate@syndicate-H610M-H-DDR4 ~/Desktop/Curl_Learn $ 
```

## Save Curl Response to a file ( -o)
```bash
$ curl -o output.json https://reqres.in/api/users/2
```


### Post HTTP Call ( --data or -d )

```bash
syndicate@syndicate-H610M-H-DDR4 ~/Desktop/Curl_Learn $ curl --data '{"name": "morpheus","job": "leader"}' https://reqres.in/api/users
{"{\"name\": \"morpheus\",\"job\": \"leader\"}":"","id":"113","createdAt":"2023-08-20T18:35:16.610Z"}

sbala@sbala-Nitro-AN515-52 ~ 
$ curl --location 'http://localhost:8080/api/posts' \
--header 'Content-Type: application/json' \
--data '{
    "userId": 998,
    "id": 999,
    "title": "DataSet1",
    "body": "RamRajya"
}'
```

### PUT HTTP call

```bash
curl --location --request PUT 'http://localhost:8080/api/posts/1' \
--header 'Content-Type: application/json' \
--data '{
    "userId": 1,
    "id": 1,
    "title": "DataSet2",
    "body": "RamRajyaEstablished"
}'
```

## DELETE CALL

```bash
curl --location --request DELETE 'http://localhost:8080/api/posts/1'
```

#### We can specify the method as well using -X flag

```bash
syndicate@syndicate-H610M-H-DDR4 ~/Desktop/Curl_Learn $ curl -X "POST" -d '{"name": "morpheus","job": "leader"}' https://reqres.in/api/users
{"{\"name\": \"morpheus\",\"job\": \"leader\"}":"","id":"833","createdAt":"2023-08-20T18:36:44.642Z"}
```

### Fetching Site Headers ( -i or --dump-header)

```bash
~/Desktop/Curl_Learn $ curl --dump-header headerDetails.txt https://reqres.in/api/users/1

syndicate@syndicate-H610M-H-DDR4 ~/Desktop/Curl_Learn $ cat headerDetails.txt 
HTTP/2 200 
date: Sun, 20 Aug 2023 18:42:54 GMT
content-type: application/json; charset=utf-8
content-length: 280
x-powered-by: Express
access-control-allow-origin: *
etag: W/"118-P3iKcVpIgCj9HqYeOOrGLX8qJVc"
via: 1.1 vegur
cache-control: max-age=14400
cf-cache-status: HIT
age: 1624
accept-ranges: bytes
report-to: {"endpoints":[{"url":"https:\/\/a.nel.cloudflare.com\/report\/v3?s=j0ozN6guneWyiqika0ON0WWg9GfosXleQSOp9PIq2c7Cq4GOG9gNEq7Kpu2yshSY9H%2FlRJXDSKX8bcaOW7WsqMOR%2BAcdGXjmAi8s9VuJalD1SGrhU5d9178eqw%3D%3D"}],"group":"cf-nel","max_age":604800}
nel: {"success_fraction":0,"report_to":"cf-nel","max_age":604800}
server: cloudflare
cf-ray: 7f9cc761ef444442-BOM
```

#### To print to the Console :
```
$ curl -i https://reqres.in/api/users/1
```

### Timing a connection with CURL ( -w ):

```bash
syndicate@syndicate-H610M-H-DDR4 ~/Desktop/Curl_Learn $ curl -w "\n%{time_total}\n" https://reqres.in/api/users/1
{"data":{"id":1,"email":"george.bluth@reqres.in","first_name":"George","last_name":"Bluth","avatar":"https://reqres.in/img/faces/1-image.jpg"},"support":{"url":"https://reqres.in/#support-heading","text":"To keep ReqRes free, contributions towards server costs are appreciated!"}}
0.212709
```




