# Helm File

1. Need to be installed Separately
1. Helps us manage Helm Charts
1. Instead of running -> helm install,rollback...
1. We can reply on Helm File
1. Guide : https://jhooq.com/helmfile-manage-helmchart/?query=helmfile

## The SYNC Command

1. Below helm Commands

    ```ps
    helm install my-app ./my-chart
    helm uninstall my-app
    ```

1. Can be replaced by 1 command

    ```ps
    helmfile sync
    ```
## Helm File

1. `helmfile`

    ```yaml
    ---
    releases:
        - name: helloworld-dev # Release name
        chart: ./helloworld # Directory
        installed: true # Flag set to true to install helm chart. False will uninstall.
    ```

## Setup

1. Navigate to : https://github.com/helmfile/helmfile/releases
1. Download OS based zip
1. Follow the guide

    ```ps
    syn@debian-server:~$ helmfile --version
    helmfile version 0.170.1
    ```

1. We are good to go.

### Lets Create a Helm File

1. Lets work with a the basic Helm Chart.

    ```ps
    syn@debian-server:~/Documents/k8-helmcharts$ helm create helloworld
    Creating helloworld
    syn@debian-server:~/Documents/k8-helmcharts$ ls
    helloworld
    ```

1. Lets now create the helm file - `helmfile.yaml` in the same dir

    ```ps
    syn@debian-server:~/Documents/k8-helmcharts$ touch helmfile.yaml
    syn@debian-server:~/Documents/k8-helmcharts$ ls
    helloworld  helmfile.yaml
    ```

1. `helmfile.yaml` Content 

    ```yaml
    ---
    releases:

    - name: helloworld-dev
        chart: ./helloworld
        installed: true 
    ```

1. Lets run the sync command

    ```ps
    syn@debian-server:~/Documents/k8-helmcharts$ helmfile sync
    Building dependency release=helloworld-dev, chart=helloworld
    Upgrading release=helloworld-dev, chart=helloworld, namespace=
    Release "helloworld-dev" does not exist. Installing it now.
    NAME: helloworld-dev
    LAST DEPLOYED: Thu Jul 17 12:18:14 2025
    NAMESPACE: default
    STATUS: deployed
    REVISION: 1
    NOTES:
    1. Get the application URL by running these commands:
    export POD_NAME=$(kubectl get pods --namespace default -l "app.kubernetes.io/name=helloworld,app.kubernetes.io/instance=helloworld-dev" -o jsonpath="{.items[0].metadata.name}")
    export CONTAINER_PORT=$(kubectl get pod --namespace default $POD_NAME -o jsonpath="{.spec.containers[0].ports[0].containerPort}")
    echo "Visit http://127.0.0.1:8080 to use your application"
    kubectl --namespace default port-forward $POD_NAME 8080:$CONTAINER_PORT

    Listing releases matching ^helloworld-dev$
    helloworld-dev	default  	1       	2025-07-17 12:18:14.974112958 +0530 IST	deployed	helloworld-0.1.0	1.16.0     


    UPDATED RELEASES:
    NAME             NAMESPACE   CHART          VERSION   DURATION
    helloworld-dev               ./helloworld   0.1.0           0s
    ```

1. We should be seeing the apps in K8 Dashboard. Lets uninstall our helm charts. Just set installed -> false & run helmfile sync again.

    ```ps
    syn@debian-server:~/Documents/k8-helmcharts$ helmfile sync
    Listing releases matching ^helloworld-dev$
    helloworld-dev	default  	1       	2025-07-17 12:18:14.974112958 +0530 IST	deployed	helloworld-0.1.0	1.16.0     

    Deleting helloworld-dev
    release "helloworld-dev" uninstalled


    DELETED RELEASES:
    NAME             NAMESPACE   DURATION
    helloworld-dev                     0
    ```
## GitHub Helm Chart Repository

1. Github Repo : https://github.com/rahulwagh/helmchart
1. Lets use the Helm Chart here.
1. We need to install a plugin before we start doing this : https://github.com/aslafy-z/helm-git
1. Once done , lets create file `helmfile.yaml`

    ```ps
    syn@debian-server:~/Documents/k8-helmcharts$ ls
    helmfile.yaml
    ```

1. File `helmfile.yaml` Content

    ```yaml
    ---
    repositories:
    - name: helloworld
        url: git+https://github.com/rahulwagh/helmchart@helloworld?ref=master&sparse=0

    releases:

    - name: helloworld
        chart: helloworld/helloworld
        installed: true 
    ```

1. Sync it

    ```ps
    syn@debian-server:~/Documents/k8-helmcharts$ helmfile sync
    Adding repo helloworld git+https://github.com/rahulwagh/helmchart@helloworld?ref=master&sparse=0
    "helloworld" has been added to your repositories

    Upgrading release=helloworld, chart=helloworld/helloworld, namespace=
    Release "helloworld" does not exist. Installing it now.
    NAME: helloworld
    LAST DEPLOYED: Thu Jul 17 14:15:54 2025
    NAMESPACE: default
    STATUS: deployed
    REVISION: 1
    NOTES:
    1. Get the application URL by running these commands:
    export NODE_PORT=$(kubectl get --namespace default -o jsonpath="{.spec.ports[0].nodePort}" services helloworld)
    export NODE_IP=$(kubectl get nodes --namespace default -o jsonpath="{.items[0].status.addresses[0].address}")
    echo http://$NODE_IP:$NODE_PORT

    Listing releases matching ^helloworld$
    helloworld	default  	1       	2025-07-17 14:15:54.005595549 +0530 IST	deployed	helloworld-0.1.0	1.16.0     


    UPDATED RELEASES:
    NAME         NAMESPACE   CHART                   VERSION   DURATION
    helloworld               helloworld/helloworld   0.1.0           1s
    ```