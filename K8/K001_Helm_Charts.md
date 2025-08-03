# Helm Charts

1. Helps us manage K8
1. Install Helm chart using the script : https://helm.sh/docs/intro/install/#from-script
1. Instructions : https://jhooq.com/getting-start-with-helm-chart/

### Confirm Installation

1. Confirm Installation

   ```ps
   syn@debian-server:~/Documents/k8-helmcharts/helloworld$ helm version
   version.BuildInfo{Version:"v3.17.0", GitCommit:"301108edc7ac2a8ba79e4ebf5701b0b6ce6a31e4", GitTreeState:"clean", GoVersion:"go1.23.4"}
   ```

### Create a Hello World Helm Chart

1. Command

   ```ps
   syn@debian-server:~/Documents/k8-helmcharts$ helm create helloworld
   ```

1. Document Structure

   ```ps
   syn@debian-server:~/Documents/k8-helmcharts$ cd helloworld/
   syn@debian-server:~/Documents/k8-helmcharts/helloworld$ tree
   .
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

   4 directories, 10 files
   ```

1. Lets update some one the values in values.yaml

   ```ps
   service:
    type: NodePort
    port: 80
   ```

1. Install the Helm Chart - Deploy to K8

   ```ps
   syn@debian-server:~/Documents/k8-helmcharts$ helm install dev-helloworld helloworld
   NAME: dev-helloworld
   LAST DEPLOYED: Wed Jul 16 14:58:03 2025
   NAMESPACE: default
   STATUS: deployed
   REVISION: 1
   NOTES:
   1. Get the application URL by running these commands:
   export NODE_PORT=$(kubectl get --namespace default -o jsonpath="{.spec.ports[0].nodePort}" services dev-helloworld)
   export NODE_IP=$(kubectl get nodes --namespace default -o jsonpath="{.items[0].status.addresses[0].address}")
   echo http://$NODE_IP:$NODE_PORT
   syn@debian-server:~/Documents/k8-helmcharts$
   ```

1. Now lets check Helm list

   ```ps
   syn@debian-server:~/Documents/k8-helmcharts$ helm list -a
   NAME          	NAMESPACE	REVISION	UPDATED                                	STATUS  	CHART           	APP VERSION
   dev-helloworld	default  	1       	2025-07-16 14:58:03.391757841 +0530 IST	deployed	helloworld-0.1.0	1.16.0
   syn@debian-server:~/Documents/k8-helmcharts$
   ```

1. Kubectl check Services

   ```ps
   syn@debian-server:~/Documents/k8-helmcharts$ kb get services -o wide
   NAME             TYPE        CLUSTER-IP       EXTERNAL-IP   PORT(S)        AGE     SELECTOR
   dev-helloworld   NodePort    10.110.230.178   <none>        80:30517/TCP   3m56s   app.kubernetes.io/instance=dev-helloworld,app.kubernetes.io/name=helloworld
   kubernetes       ClusterIP   10.96.0.1        <none>        443/TCP        160d    <none>
   syn@debian-server:~/Documents/k8-helmcharts$
   ```

1. From the above Port to Access : 80:30517/TCP
1. Lets connect using `minikube service`

   ```ps
   syn@debian-server:~/Documents/k8-helmcharts$ kb get services -o wide
   NAME             TYPE        CLUSTER-IP       EXTERNAL-IP   PORT(S)        AGE    SELECTOR
   dev-helloworld   NodePort    10.110.230.178   <none>        80:30517/TCP   134m   app.kubernetes.io/instance=dev-helloworld,app.kubernetes.io/name=helloworld
   kubernetes       ClusterIP   10.96.0.1        <none>        443/TCP        160d   <none>
   syn@debian-server:~/Documents/k8-helmcharts$ minikube service dev-helloworld --rul
   Error: unknown flag: --rul
   See 'minikube service --help' for usage.
   syn@debian-server:~/Documents/k8-helmcharts$ minikube service dev-helloworld --url
   http://192.168.59.100:30517
   syn@debian-server:~/Documents/k8-helmcharts$ curl http://192.168.59.100:30517/
   <!DOCTYPE html>
   .
   .
   <h1>Welcome to nginx!</h1>
   .
   .
   syn@debian-server:~/Documents/k8-helmcharts$
   ```

1. Lets uninstall the Helm Chart

   ```ps
   syn@debian-server:~/Documents/k8-helmcharts$ helm list -a
   NAME          	NAMESPACE	REVISION	UPDATED                                	STATUS  	CHART           	APP VERSION
   dev-helloworld	default  	1       	2025-07-16 14:58:03.391757841 +0530 IST	deployed	helloworld-0.1.0	1.16.0
   syn@debian-server:~/Documents/k8-helmcharts$
   syn@debian-server:~/Documents/k8-helmcharts$ helm uninstall dev-helloworld
   release "dev-helloworld" uninstalled
   ```

1. We no longer see the infra.

## Lets Upgrade our Helm Chart

1. Lets look at our Helm Repo & Services> Notice REVISION = 1

    ```ps
    syn@debian-server:~/Documents/k8-helmcharts$ helm list -a
    NAME          	NAMESPACE	REVISION	UPDATED                                	STATUS  	CHART           	APP VERSION
    dev-helloworld	default  	1       	2025-07-16 14:58:03.391757841 +0530 IST	deployed	helloworld-0.1.0	1.16.0
    syn@debian-server:~/Documents/k8-helmcharts$ kb get services -o wide
    NAME             TYPE        CLUSTER-IP      EXTERNAL-IP   PORT(S)   AGE    SELECTOR
    dev-helloworld   ClusterIP   10.102.197.12   <none>        80/TCP    51s    app.kubernetes.io/instance=dev-helloworld,app.kubernetes.io/name=helloworld
    kubernetes       ClusterIP   10.96.0.1       <none>        443/TCP   160d   <none>
    ```

1. Here we haven't updated the values file like in Step 3 above.
1. Repeat the step 3 process like the above example

    ```ps
    syn@debian-server:~/Documents/k8-helmcharts$ helm upgrade dev-helloworld helloworld
    Release "dev-helloworld" has been upgraded. Happy Helming!
    NAME: dev-helloworld
    LAST DEPLOYED: Wed Jul 16 17:33:00 2025
    NAMESPACE: default
    STATUS: deployed
    REVISION: 2
    NOTES:
    1. Get the application URL by running these commands:
    export NODE_PORT=$(kubectl get --namespace default -o jsonpath="{.spec.ports[0].nodePort}" services dev-helloworld)
    export NODE_IP=$(kubectl get nodes --namespace default -o jsonpath="{.items[0].status.addresses[0].address}")
    echo http://$NODE_IP:$NODE_PORT
    syn@debian-server:~/Documents/k8-helmcharts$ 
    ```

1. Note REVISION = 2
1. Lets Quickly check the service & we notice the change  TYPE is now set as NodePort

    ```ps
    syn@debian-server:~/Documents/k8-helmcharts$ kb get services -o wide
    NAME             TYPE        CLUSTER-IP      EXTERNAL-IP   PORT(S)        AGE     SELECTOR
    dev-helloworld   NodePort    10.102.197.12   <none>        80:30207/TCP   7m42s   app.kubernetes.io/instance=dev-helloworld,app.kubernetes.io/name=helloworld
    kubernetes       ClusterIP   10.96.0.1       <none>        443/TCP        160d    <none>
    syn@debian-server:~/Documents/k8-helmcharts$ 
    ```
## RollBack

1. We need to specify the revision number of our chart.

    ```ps
    syn@debian-server:~/Documents/k8-helmcharts$ helm list -a
    NAME          	NAMESPACE	REVISION	UPDATED                                	STATUS  	CHART           	APP VERSION
    dev-helloworld	default  	2       	2025-07-16 17:33:00.547010495 +0530 IST	deployed	helloworld-0.1.0	1.16.0     
    syn@debian-server:~/Documents/k8-helmcharts$ 
    syn@debian-server:~/Documents/k8-helmcharts$ helm rollback dev-helloworld 1
    Rollback was a success! Happy Helming!
    syn@debian-server:~/Documents/k8-helmcharts$
    ```

1. Lets look for the server type & helm list

    ```
    syn@debian-server:~/Documents/k8-helmcharts$ helm list -a
    NAME          	NAMESPACE	REVISION	UPDATED                                	STATUS  	CHART           	APP VERSION
    dev-helloworld	default  	3       	2025-07-16 17:37:00.184466859 +0530 IST	deployed	helloworld-0.1.0	1.16.0     
    syn@debian-server:~/Documents/k8-helmcharts$ kb get services -o wide
    NAME             TYPE        CLUSTER-IP      EXTERNAL-IP   PORT(S)   AGE    SELECTOR
    dev-helloworld   ClusterIP   10.102.197.12   <none>        80/TCP    11m    app.kubernetes.io/instance=dev-helloworld,app.kubernetes.io/name=helloworld
    kubernetes       ClusterIP   10.96.0.1       <none>        443/TCP   160d   <none>
    syn@debian-server:~/Documents/k8-helmchar
    ```
1. Note REVISION = 3. Beacause its a new change.

