# K8 Buzzwords

Note : Pod and Containers are different things

1. Pod : Responsible for running containers. Runs on Worker node
1. Container : Smallest unit in K8.
1. Worder Node : Run pod/containers. 'Nodes' are your machines/virutal instances. Can run multiple containers
1. Proxy / Config : Runns on a worker node to control network traffic.
1. Master Node ( The Control Plane ) : Control all deployments
1. Deployments : 
    - Control Pods
    - Can be scaled dynamically
    - Pod State
1. Service : 
    - Exposes pods to the cluster or externally
    - Without services , pods are hard to reach.
1. Replica : 
    - Specify number of instances of a POD 

# Local Installation

1. https://microk8s.io/#install-microk8s
1. Just Follow the steps
1. Dashboard : https://microk8s.io/docs/addon-dashboard
1. MicroK8 comes with kubectl. We can work with that or install separately : https://microk8s.io/docs/working-with-kubectl


    ```
    synsv@synsv-ms7e05:~/Documents/CodeSource/GitHub
    $ microk8s status --wait-ready
    microk8s is running
    high-availability: no
      datastore master nodes: 127.0.0.1:19001
      datastore standby nodes: none
    addons:
      enabled:
        dashboard            # (core) The Kubernetes dashboard
        dns                  # (core) CoreDNS
        ha-cluster           # (core) Configure 
        .
        .
    ```

# Declarative Approach

## Deployment

1. API Reference : https://kubernetes.io/docs/reference/kubernetes-api/
1. Lets create a Config .
1. `Metadata` : 
    - Includes name
    - Includes label
1. `Spec` : Specification 
    - Defines the deployment
1. `selector` : 
    - `matchLabels` :
        - Key values pairs
1. `replicas` : Default is 1.
1. `template` : Pods that would be created
    - `metadata` : We need new metadata because pod is an object just like deployment
        - `label` : Create a label
    - `spec` : Here we define the specification of the individual pods
        - `containers` : List of containers part of the pod
            - `name` : Container name
            - `image` : Image URL

1. Deployments are dynamic objects. It control pods. It does the control using `selectors`. Multiple types of selectors. `Expression` or `matchLabels`. Labels are easy. So for `matchLabels` we have key values pairs of label on the pods which should be control by the deployment.
1. So line 76 matches with 80

    ```yaml
    apiVersion: apps/v1
    kind: Deployment
    metadata:
      name: first-app-deployment
    spec:
      replicas: 1
      selector:
        matchLabels:
          app: first-app
      template:
        metadata:
          labels:
            app: first-app
        spec:
          containers:
          - name: first-node
            image: syndicate47/synimage:k8-first-app-1.0.0
    ```

1. Lets apply the above config

    ```ps
    synsv@synsv-ms7e05:~/Documents/CodeSource/GitHub/SampleCode/Docker_&_K8
    $ kubectl apply -f=node-app-config.yaml
    deployment.apps/first-app-deployment created
    ```

1. Note : Pod is created and defined in Deployment not in Service.

## Service

1. Recap on selector - It identifies resources connected / maintained by a resource.
1. `selector`: Here we define which pods are part of the service. It works a little diff than deployment. We don't specify `matchLabels` here because all we are doing is matching labels so we can directly write the labels
  - `app: first-app` : key value pair
1. `ports` : We can expose more than one port.
1. `protocol` : Default is `TCP`
1. `targetPort` : Target port of Container
1. `type` : `ClusterIP` is default & internal. `NodePort` expose on worker node. `LoadBalancer` for Outside access.

    ```yaml
    ---
    apiVersion: v1
    kind: Service
    metadata:
      name: backend
      # Additional Stuff for MicroK8 Starts
      annotations:
        metallb.universe.tf/address-pool: custom-addresspool
      # Additional Stuff for MicroK8 Ends
    spec:
      selector:
        app: first-app
      ports:
        - protocol: TCP
          port: 80
          targetPort: 8080
      type: LoadBalancer
    ---

    ```

1. Apply it

    ```ps
    synsv@synsv-ms7e05:~/Documents/CodeSource/GitHub/SampleCode/Docker_&_K8
    $ kubectl apply -f=node-app-config.yaml
    deployment.apps/first-app-deployment unchanged
    service/backend created
    ```

1. MicroK8 is GOD. Ingore. Just follow https://microk8s.io/docs/addon-metallb and you good.
1. Lets utilize the Loadbalancer

    ```ps
    synsv@synsv-ms7e05:~/Documents/CodeSource/GitHub/SampleCode/Docker_&_K8
    $ kubectl get pods
    NAME                                    READY   STATUS    RESTARTS   AGE
    first-app-deployment-8594c7c857-vq7zc   1/1     Running   0          30m
    synsv@synsv-ms7e05:~/Documents/CodeSource/GitHub/SampleCode/Docker_&_K8
    $ kubectl get svc
    NAME         TYPE           CLUSTER-IP       EXTERNAL-IP   PORT(S)        AGE
    backend      LoadBalancer   10.152.183.155   192.168.1.1   80:32759/TCP   13m
    kubernetes   ClusterIP      10.152.183.1     <none>        443/TCP        48d
    synsv@synsv-ms7e05:~/Documents/CodeSource/GitHub/SampleCode/Docker_&_K8
    $ curl 192.168.1.1:80

        <h1>Hello from this NodeJS app!</h1>
        <p>Try sending a request to /error and see what happens</p>
    ```

1. This is nice.

## Delete Resources

1. Delete resource created from the file

    ```ps
    synsv@synsv-ms7e05:~/Documents/CodeSource/GitHub/SampleCode/Docker_&_K8
    $ kubectl delete -f=node-app-config.yaml
    deployment.apps "first-app-deployment" deleted
    service "backend" deleted
    ```

## Liveness

1. `livenessProbe` : ontainer is checked by the Pod to see if its up and running
1. `httpGet` : HTTP get is sent by the pod to the running app
1. `periodSeconds` : How often we send the signal
1. `initialDeplaySeconds` : Deplay before first check

    ```yaml
    apiVersion: apps/v1
    kind: Deployment
    metadata:
      name: first-app-deployment
    spec:
      replicas: 1
      selector:
        matchLabels:
          app: first-app
      template:
        metadata:
          labels:
            app: first-app
        spec:
          containers:
          - name: first-node
            image: syndicate47/synimage:k8-first-app-1.0.0
            livenessProbe:
              httpGet:
                path: /
                port: 8080
              periodSeconds: 10
              initialDelaySeconds: 5
    ```

## Common Config

1. `imagePullPolicy` : Defaults to Always. `Always` pull latest image. `Never` : Never try pulling it if the image isn't present. This is tricky but can be ignored.