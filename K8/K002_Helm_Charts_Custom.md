# Deploy a Spring Boot App

1. The app is quite simple & returns `Hi, I am Shrutosom Bala` page.
1. Docker Image URL : https://hub.docker.com/r/syndicate47/portfolio-app-image

## Create Helm Chart

1. Create Dir : portfolio-app-project

    ```ps
    syn@debian-server:~/Documents/k8-helmcharts$ helm create portfolio-app-project
    Creating portfolio-app-project
    ```

1. Navigate to `Chart.yaml` & comment out `appVersion: "1.16.0"`
1. Navigate to `values.yaml` & Update the repo source

    ```yaml
    image:
      repository: syndicate47/portfolio-app-image
      pullPolicy: IfNotPresent
      tag: "latest"
    ```

1. Lets specify the service type `values.yaml`

    ```yaml
    service:
      type: NodePort
      port: 8080
    ```

1. Comment out Probes in `values.yaml`

    ```yaml
    # livenessProbe:
    #   httpGet:
    #     path: /
    #     port: http
    # readinessProbe:
    #   httpGet:
    #     path: /
    #     port: http
    ```

1. Install Deploy & Test

    ```ps
    syn@debian-server:~/Documents/k8-helmcharts$ helm install portfolio-app-dev portfolio-app-project
    syn@debian-server:~/Documents/k8-helmcharts$ helm list -a
    NAME             	NAMESPACE	REVISION	UPDATED                                	STATUS  	CHART                      	APP VERSION
    portfolio-app-dev	default  	4       	2025-07-17 00:58:41.578531556 +0530 IST	deployed	portfolio-app-project-0.1.0	
    syn@debian-server:~/Documents/k8-helmcharts$ kb get services -o wide
    NAME                                      TYPE        CLUSTER-IP      EXTERNAL-IP   PORT(S)          AGE     SELECTOR
    kubernetes                                ClusterIP   10.96.0.1       <none>        443/TCP          160d    <none>
    portfolio-app-dev-portfolio-app-project   NodePort    10.104.249.43   <none>        8080:31264/TCP   5m51s   app.kubernetes.io/instance=portfolio-app-dev,app.kubernetes.io/name=portfolio-app-project
    syn@debian-server:~/Documents/k8-helmcharts$ 
    syn@debian-server:~/Documents/k8-helmcharts$ minikube service portfolio-app-dev-portfolio-app-project
    |-----------|-----------------------------------------|-------------|-----------------------------|
    | NAMESPACE |                  NAME                   | TARGET PORT |             URL             |
    |-----------|-----------------------------------------|-------------|-----------------------------|
    | default   | portfolio-app-dev-portfolio-app-project | http/8080   | http://192.168.59.100:31264 |
    |-----------|-----------------------------------------|-------------|-----------------------------|
    🎉  Opening service default/portfolio-app-dev-portfolio-app-project in default browser...
    syn@debian-server:~/Documents/k8-helmcharts$ curl http://192.168.59.100:31264/
    <html>
    <head>
      <link rel="stylesheet" href="main.css">
      <title>Shrutosom Bala</title>
    </head>
    <body>
      <div>
        <h1>Hi, I am Shrutosom Bala</h1>
      </div>
    </body>
    </html>syn@debian-server:~/Documents/k8-helmcharts$ 
    ```

## Set namespace with HELM Charts

1. Switching to HelmFile for Ease. Create `helmfile.yaml`

    ```yaml
    ---
    releases:

      - name: portfolio-app
        namespace: portfolio-app-dev
        chart: ./portfolio-app-project
        installed: true 

    ```

1. Files:

    ```
    syn@debian-server:~/Documents/k8-helmcharts$ tree
    .
    ├── helmfile.yaml
    └── portfolio-app-project
        ├── charts
        ├── Chart.yaml
        ├── templates
        │   ├── deployment.yaml
        │   ├── _helpers.tpl
        │   ├── hpa.yaml
        │   ├── ingress.yaml
        │   ├── NOTES.txt
        │   ├── serviceaccount.yaml
        │   ├── service.yaml
        │   └── tests
        │       └── test-connection.yaml
        └── values.yaml

    5 directories, 11 files
    ```

1. Run `helmfile sync`. Lets hit the app

    ```ps
    syn@debian-server:~/Documents/k8-helmcharts$ kb get svc -o wide -n=portfolio-app-dev
    NAME                                  TYPE       CLUSTER-IP       EXTERNAL-IP   PORT(S)          AGE     SELECTOR
    portfolio-app-portfolio-app-project   NodePort   10.108.206.156   <none>        8080:30159/TCP   2m39s   app.kubernetes.io/instance=portfolio-app,app.kubernetes.io/name=portfolio-app-project
    syn@debian-server:~/Documents/k8-helmcharts$ minikube service portfolio-app-portfolio-app-project -n=portfolio-app-dev
    |-------------------|-------------------------------------|-------------|-----------------------------|
    |     NAMESPACE     |                NAME                 | TARGET PORT |             URL             |
    |-------------------|-------------------------------------|-------------|-----------------------------|
    | portfolio-app-dev | portfolio-app-portfolio-app-project | http/8080   | http://192.168.59.100:30159 |
    |-------------------|-------------------------------------|-------------|-----------------------------|
    🎉  Opening service portfolio-app-dev/portfolio-app-portfolio-app-project in default browser...
    syn@debian-server:~/Documents/k8-helmcharts$ 
    ```

## Ingress Time - Check for NGINX Ingress Controller

1. This is a little cellotape kind off. This is fucking hard for no reason with minikube
1. Check if Check for NGINX Ingress Controller

    ```ps
    syn@debian-server:~/Documents/k8-helmcharts$ kubectl get pods -A | grep ingress
    syn@debian-server:~/Documents/k8-helmcharts$ 
    ```

1. Looks like we need to get it ? But what exactly is it?
1. Ingress is This is a Kubernetes API object that defines rules for routing external traffic (e.g., portfolio.local) to internal services.  Ingress Controller This is the software component that reads your Ingress resource and sets up the actual networking to handle requests.Without an Ingress Controller, Kubernetes just stores your Ingress rules but does nothing with them. No traffic gets routed.

1. Enable Ingress Controller : `minikube addons enable ingress`
1. Check again 

    ```ps
    syn@debian-server:~/Documents/k8-helmcharts$ kubectl get pods -A | grep ingress
    ingress-nginx          ingress-nginx-admission-create-9zwgr                    0/1     Completed          0                29m
    ingress-nginx          ingress-nginx-admission-patch-zr4ts                     0/1     Completed          0                29m
    ingress-nginx          ingress-nginx-controller-597664746c-nt7bp               1/1     Running            0                29m
    ```

1. `values.yaml` changes. Switch service type to `ClusterIP`.

    ```yaml
    service:
      type: ClusterIP
      port: 8080

    ingress:
      enabled: true
      className: "nginx"
      annotations: #{}
        kubernetes.io/ingress.class: nginx
        # kubernetes.io/tls-acme: "true"
      hosts:
        - host: portfolio.local
          paths:
            - path: /
              pathType: Prefix
      tls: []
    ```

1. Lets look at the ingress settings

    ```ps
    syn@debian-server:~/Documents/k8-helmcharts$ kb get ingress -o wide -n=portfolio-app-dev
    NAME                                  CLASS   HOSTS             ADDRESS          PORTS   AGE
    portfolio-app-portfolio-app-project   nginx   portfolio.local   192.168.59.100   80      47m
    ```

1. Add minikube IP to /etc/host so that we can use the host name. Interestingly ingress is mapped to minukube IP

    ```
    syn@debian-server:~/Documents/k8-helmcharts$ minikube ip
    192.168.59.100
    echo "$(minikube ip)  portfolio.local" | sudo tee -a /etc/hosts
    ```

1. Invoke the app.

    ```ps
    syn@debian-server:~/Documents/k8-helmcharts$ curl http://portfolio.local
    <html>
    <head>
      <link rel="stylesheet" href="main.css">
      <title>Shrutosom Bala</title>
    </head>
    <body>
      <div>
        <h1>Hi, I am Shrutosom Bala</h1>
      </div>
    </body>
    </html>
    ```

## OAuth2.0 Proxy

