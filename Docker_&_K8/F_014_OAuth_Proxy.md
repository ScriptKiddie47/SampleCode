# This is utter BS but working

```yaml
# Simple Web App Deployment

apiVersion: apps/v1
kind: Deployment
metadata:
  name: simple-web-app-deployment
spec:
  replicas: 1
  selector:
    matchLabels:
      app: simple-web-app
  template:
    metadata:
      labels:
        app: simple-web-app
    spec:
      containers:
        - name: simple-web-app-container
          image: syndicate47/synimage:simple-web-app-1.0.0
          imagePullPolicy: Always

---
# Simple Web App Service

apiVersion: v1
kind: Service
metadata:
  name: simple-web-app-service
spec:
  selector:
    app: simple-web-app
  ports:
    - protocol: TCP
      port: 5000
      targetPort: 5000

---
# Oauth Proxy Deployment

apiVersion: apps/v1
kind: Deployment
metadata:
  name: oauth2-proxy-app-deployment
spec:
  replicas: 1
  selector:
    matchLabels:
      app: oauth2-proxy-app
  template:
    metadata:
      labels:
        app: oauth2-proxy-app
    spec:
      containers:
        - name: oauth2-proxy-app-container
          image: quay.io/oauth2-proxy/oauth2-proxy:v7.6.0
          imagePullPolicy: Always
          args:
            - --provider=oidc
            - --oidc-issuer-url=https://dev-gnxwtf02tybfe6he.us.auth0.com/
            - --client-id=<client_id>
            - --client-secret=<client_secret>
            - --cookie-secret=bVbMfxlt8Wmq4E1OYTICqS_AZHTeVDUEdFP5LGbY05c
            - --email-domain=*
            - --upstream=file:///dev/null
            - --http-address=0.0.0.0:4180 
            - --skip-jwt-bearer-tokens=true
            - --pass-access-token=true
            - --oidc-extra-audience=car-order-service
          ports:
            - containerPort: 4180

---
## Oauth Proxy Service

apiVersion: v1
kind: Service
metadata:
  name: oauth2-proxy-app-service
spec:
  selector:
    app: oauth2-proxy-app
  ports:
    - protocol: TCP
      port: 4180
      targetPort: 4180

---
# Simple Web App Ingress

apiVersion: networking.k8s.io/v1
kind: Ingress
metadata:
  name: http-ingress
  annotations:
    nginx.ingress.kubernetes.io/proxy-body-size: "2000m"
    nginx.ingress.kubernetes.io/proxy-buffer-size: "32k"
    nginx.ingress.kubernetes.io/auth-url: "http://oauth2-proxy-app-service.default.svc.cluster.local:4180/oauth2/auth"
    nginx.ingress.kubernetes.io/auth-signin: "http://$host/oauth2/start"
    nginx.ingress.kubernetes.io/rewrite-target: /
spec:
  rules:
    - http:
        paths:
          - path: /app
            pathType: Prefix
            backend:
              service:
                name: simple-web-app-service
                port:
                  number: 5000
          - path: /oauth2
            pathType: Prefix
            backend:
              service:
                name: oauth2-proxy-app-service
                port:
                  number: 4180

```

1. Curl

```ps
curl --request POST \
  --url http://127.0.0.1/app \
  --header 'authorization: Bearer eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCIsImtpZCI6InZid28yYkpvUm1VNlN2WEIzRTNsViJ9.eyJpc3MiOiJodHRwczovL2Rldi1nbnh3dGYwMnR5YmZlNmhlLnVzLmF1dGgwLmNvbS8iLCJzdWIiOiJtb1RhdVBpZGVldzdvdWNNRURVNlF3cWVvbWFKNEE3c0BjbGllbnRzIiwiYXVkIjoiY2FyLW9yZGVyLXNlcnZpY2UiLCJpYXQiOjE3NTg2MjM1MjUsImV4cCI6MTc1ODcwOTkyNSwiZ3R5IjoiY2xpZW50LWNyZWRlbnRpYWxzIiwiYXpwIjoibW9UYXVQaWRlZXc3b3VjTUVEVTZRd3Flb21hSjRBN3MifQ.JYKi0nG89_u91jLbNZ7Sa_ILo9V6du0i3GknFd3pIS3UUv8Uvwn9IP1tIVVE3EegSEvYblXxWfAcj13rlyAat_I0qMDx0JBC8ftZ5Mm_bZqiVoYXEPnqIuWlD6nUtK4OTCFgsq5maMdJHnXSw2uXLv3V2RLID_PwELB4yMMxIsiummn3LG2RLy2tHIrn7LIeHCJzWDoCyhBs21PKOkbmwQznCjnG5n0HfAyUaSGc0fiw7Vamzhye2uCjOCAucwN5X0-RnLa0wwyAGUkrZTUetQX69ugEdajySu71m1VJm5_q4R2n9tegGRSukKlyeffgjY6meu6h1DFuBLQxpb-IZQ' \
  --header 'content-type: application/json' \
  --data '{
  "name": "Ritam"
}'
```