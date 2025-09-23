## Container to Container Communication in the same POD

1. For Pod Internal Communication. When 2 containers run in the same pod K8 allows us to send a request to a localhost address of the other container
1. So `localhost` is a magic address we can use for another container running in the same pod
1. Source Code :

```yaml
# User API Deployment

apiVersion: apps/v1
kind: Deployment
metadata:
  name: user-app-deployment
spec:
  replicas: 1
  selector:
    matchLabels:
      app: user-app
  template:
    metadata:
      labels:
        app: user-app
    spec:
      containers:
        - name: user-app-container
          image: syndicate47/synimage:user-api-1.0.0
          env:
            - name: AUTH_ADDRESS
              valueFrom:
                configMapKeyRef:
                  name: data-store-env
                  key: auth_app_address
        - name: user-auth-container
          image: syndicate47/synimage:user-auth-1.0.0

# ConfigMap

apiVersion: v1
kind: ConfigMap
metadata:
  name: data-store-env
data:
  auth_app_address: "localhost"

```

1. Also note the usage of env

## Pod to Pod or Service to Service Communication

1. Pod's IP keep changing so we need service as a mechanism for 2 pods to communicate.
1. One service will be on loadbalancer other on ClusterIP
1. K8 Automatically creates environments variables available for us to use. We can take look at it.

```ps
synsv@synsv-ms7e05:~/Documents/CodeSource/GitHub/SampleCode/Docker_&_K8
$ kubectl exec user-app-deployment-7db558bb5c-l8r7v -- printenv | grep SERVICE
USER_AUTH_SERVICE_SERVICE_HOST=10.152.183.141
USER_AUTH_SERVICE_PORT_80_TCP_PORT=80
KUBERNETES_SERVICE_PORT=443
....
```

1. `user-app-deployment-7db558bb5c-l8r7v` is my pod name. It has access to the below env variables

1. For a service it will be `USER_AUTH_SERVICE_SERVICE_HOST`

```yaml
# User App Deployment

apiVersion: apps/v1
kind: Deployment
metadata:
  name: user-app-deployment
spec:
  replicas: 1
  selector:
    matchLabels:
      app: user-app
  template:
    metadata:
      labels:
        app: user-app
    spec:
      containers:
        - name: user-app-container
          image: syndicate47/synimage:user-api-1.0.0
          imagePullPolicy: Always
          env:
            - name: AUTH_ADDRESS
              value: $(USER_AUTH_SERVICE_SERVICE_HOST)

---
# User App Service

apiVersion: v1
kind: Service
metadata:
  name: user-app-service
  annotations:
    metallb.universe.tf/address-pool: custom-addresspool
spec:
  selector:
    app: user-app
  ports:
    - protocol: TCP
      port: 8080
      targetPort: 8080
  type: LoadBalancer

---
# User Auth App Deployment

apiVersion: apps/v1
kind: Deployment
metadata:
  name: user-auth-deployment
spec:
  replicas: 1
  selector:
    matchLabels:
      app: user-auth
  template:
    metadata:
      labels:
        app: user-auth
    spec:
      containers:
        - name: user-auth-container
          image: syndicate47/synimage:user-auth-1.0.0

---
# User Auth App Service

apiVersion: v1
kind: Service
metadata:
  name: user-auth-service
  annotations:
    metallb.universe.tf/address-pool: custom-addresspool
spec:
  selector:
    app: user-auth
  ports:
    - protocol: TCP
      port: 80
      targetPort: 80
  type: ClusterIP

---
```

1. We use $() so that K8 can resolve it to an IP
1. Note: We cannot use config maps here. It is not able to resolve the Host Name
1. Logs: `Connecting to Host:10.152.183.42`

## CORE DNS Service to Service Connection

1. K8 comes with CoreDNS. It is a domain name service.  Its Cluster Internal Servers.
1. We can use it to lookup service and it is the preferred way
1. So it will be `service-name`.`namespace`.`port` . Use port unless it default

```yaml
# User App Deployment

apiVersion: apps/v1
kind: Deployment
metadata:
  name: user-app-deployment
spec:
  replicas: 1
  selector:
    matchLabels:
      app: user-app
  template:
    metadata:
      labels:
        app: user-app
    spec:
      containers:
        - name: user-app-container
          image: syndicate47/synimage:user-api-1.0.0
          imagePullPolicy: Always
          env:
            - name: AUTH_ADDRESS
              valueFrom:
                configMapKeyRef:
                  name: data-store-env
                  key: auth_app_address

---
# User App Service

apiVersion: v1
kind: Service
metadata:
  name: user-app-service
  annotations:
    metallb.universe.tf/address-pool: custom-addresspool
spec:
  selector:
    app: user-app
  ports:
    - protocol: TCP
      port: 8080
      targetPort: 8080
  type: LoadBalancer

---
# User Auth App Deployment

apiVersion: apps/v1
kind: Deployment
metadata:
  name: user-auth-deployment
spec:
  replicas: 1
  selector:
    matchLabels:
      app: user-auth
  template:
    metadata:
      labels:
        app: user-auth
    spec:
      containers:
        - name: user-auth-container
          image: syndicate47/synimage:user-auth-1.0.0

---
# User Auth App Service

apiVersion: v1
kind: Service
metadata:
  name: user-auth-service
  annotations:
    metallb.universe.tf/address-pool: custom-addresspool
spec:
  selector:
    app: user-auth
  ports:
    - protocol: TCP
      port: 80
      targetPort: 80
  type: ClusterIP

---
# ConfigMap

apiVersion: v1
kind: ConfigMap
metadata:
  name: data-store-env
data:
  auth_app_address: user-auth-service.default
```

1. Log : `Connecting to Host:user-auth-service.default`