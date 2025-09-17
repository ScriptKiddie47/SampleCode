# Volumes in K8

1. A broad variety of Volumes types / drivers are supported
   - Local Volumes ( Node )
   - Cloud provider specific volumes
1. Volume lifetime depends on the Pod Lifetime
   - Volumes survive container restarts ( and removal )
   - Volumes are removed when Pods are destroyed

## Volatile Volumes

1. https://kubernetes.io/docs/concepts/storage/volumes/#volume-types
1. We have node service which store data in a file and fetch it. Simple.

   ```yaml
   ---
   apiVersion: apps/v1
   kind: Deployment
   metadata:
   name: story-deployment
   spec:
   replicas: 1
   selector:
       matchLabels:
       app: story-app
   template:
       metadata:
       labels:
           app: story-app
       spec:
       containers:
       - name: story-app-container
           image: syndicate47/synimage:stories-app-1.0.0
   ---

   apiVersion: v1
   kind: Service
   metadata:
   name: story-service
   # Additional Stuff for MicroK8 Starts
   annotations:
       metallb.universe.tf/address-pool: custom-addresspool
   # Additional Stuff for MicroK8 Ends
   spec:
   selector:
       app: story-app
   ports:
       - protocol: TCP
       port: 80
       targetPort: 3000
   type: LoadBalancer
   ---
   ```

1. Output

   ```ps
   $ curl --request GET \
   --url http://192.168.1.2/story
   {"story":""}

   $ curl --request POST \
   --url http://192.168.1.2/story \
   --header 'content-type: application/json' \
   --data '{
   "text": "My text"
   }'
   {"message":"Text was stored!"}
   ```

1. If the container restarts the data is gone so lets save the data in volume.
1. K8 can mount volumes in Containers
1. We do have a lot of options : https://kubernetes.io/docs/concepts/storage/volumes/#volume-types
1. Lets look at a few.

### EmpyDir [ Lifetime as Pod ]

1. Below `spec` we add `volumes` which defines all the volumes which should be part of the `pod` and all `containers` in that pod will then be able to use the volumes
1. `emptyDir` is the type. `{}` -> Use as default settings. `emptyDir` created a new empty directory when the pod starts and it keeps this dir alive and filled with data as long as pod is alive.
1. Now we need to make it available inside of the container. To do that we can define its access inside container config
1. `volumeMounts` : Define where which volume should be mounted.
1. `mountPath` : Application folder where data is going to be written
1. `name` : Volume name as we can have multiple volumes inside one Pod.

   ```yaml
   apiVersion: apps/v1
   kind: Deployment
   metadata:
     name: story-deployment
   spec:
     replicas: 1
     selector:
       matchLabels:
         app: story-app
     template:
       metadata:
         labels:
           app: story-app
       spec:
         containers:
           - name: story-app-container
             image: syndicate47/synimage:stories-app-1.0.0
             imagePullPolicy: Always
             volumeMounts:
               - mountPath: /app/story
                 name: story-volume
         volumes:
           - name: story-volume
             emptyDir: {}
   ```

1. The volume data , we can see in K8 Dashboard
1. Problem with EmptyDir : As the volume is connected to Pod if we create replicas we would now have 2 volumes and traffic redirects so its an issue.

### hostPath [ Lifetime as node ]

1. https://kubernetes.io/docs/concepts/storage/volumes/#hostpath
1. Mounth Volume on Host Machine which is the worker node.
1. `path` : Where data must be stored in Host Machine.
1. `type` : `DirectoryOrCreate` - Create DIR if it doesn't exists

   ```yaml
   apiVersion: apps/v1
   kind: Deployment
   metadata:
     name: story-deployment
   spec:
     replicas: 1
     selector:
       matchLabels:
         app: story-app
     template:
       metadata:
         labels:
           app: story-app
       spec:
         containers:
           - name: story-app-container
             image: syndicate47/synimage:stories-app-1.0.0
             imagePullPolicy: Always
             volumeMounts:
               - mountPath: /app/story
                 name: story-volume
         volumes:
           - name: story-volume
             hostPath:
               path: /data
               type: DirectoryOrCreate
   ```

1. Downside : Pods running on Different nodes won't have access to this data.

## Persistent Volumes

1. Built independent of Pod & Nodes.
1. https://kubernetes.io/docs/concepts/storage/persistent-volumes/#types-of-persistent-volumes

### hostPath

1. `kind: PersistentVolume`
1. `capacity` : Determine how much capacity can be used
1. `storage: 1Gi` : Upto 1 GB of space can be used.
1. `volumeMode` : `Block` or `Filesystem` : These are storage types
1. `accessModes` : Define how we can access
   - `ReadWriteOnce` : Can only be mounted by a single node.
   - `ReadOnlyMany` : Read Access Can be claimed by multiple nodes [ Not applicable to hostPath ]
   - `ReadWriteMany` : Write Access Can be claimed by multiple nodes [ Not applicable to hostPath ]
1. https://kubernetes.io/docs/concepts/storage/persistent-volumes/#access-modes

    ```yaml
    apiVersion: v1
    kind: PersistentVolume
    metadata:
      name: host-pv
    spec:
      capacity:
        storage: 1Gi
      volumeMode: Filesystem
      storageClassName: microk8s-hostpath
      accessModes:
        - ReadWriteOnce
      hostPath:
        path: /data
        type: DirectoryOrCreate
    ```


1. `storageClassName` : Advanced concept but we should just use a standard storage class.

### Persistent Volume Claim

1. Claim a volume defined
1. To connect claim to persistent volume , we define the spec
1. `resources: request: storage:` this is equal to `capacity` in above.

    ```yaml
    apiVersion: v1
    kind: PersistentVolumeClaim
    metadata:
      name: host-pvc
    spec:
      volumeName: host-pv
      accessModes:
        - ReadWriteOnce
      resources:
        requests:
          storage: 1Gi
    ```

1. Lets establish a connection the pod in the deployment Yaml

    ```yaml
    apiVersion: apps/v1
    kind: Deployment
    metadata:
      name: story-deployment
    spec:
      replicas: 1
      selector:
        matchLabels:
          app: story-app
      template:
        metadata:
          labels:
            app: story-app
        spec:
          containers:
            - name: story-app-container
              image: syndicate47/synimage:stories-app-1.0.0
              imagePullPolicy: Always
              volumeMounts:
                - mountPath: /app/story
                  name: story-volume
          volumes:
            - name: story-volume
              persistentVolumeClaim:
                claimName: host-pvc
    ```
1. Logs

    ```ps
    synsv@synsv-ms7e05:~/Documents/CodeSource/GitHub/SampleCode/Docker_&_K8
    $ kubectl get sc
    NAME                          PROVISIONER            RECLAIMPOLICY   VOLUMEBINDINGMODE      ALLOWVOLUMEEXPANSION   AGE
    microk8s-hostpath (default)   microk8s.io/hostpath   Delete          WaitForFirstConsumer   false                  3m44s
    synsv@synsv-ms7e05:~/Documents/CodeSource/GitHub/SampleCode/Docker_&_K8
    $ kubectl get pv
    NAME      CAPACITY   ACCESS MODES   RECLAIM POLICY   STATUS   CLAIM              STORAGECLASS        VOLUMEATTRIBUTESCLASS   REASON   AGE
    host-pv   1Gi        RWO            Retain           Bound    default/host-pvc   microk8s-hostpath   <unset>                          88s
    synsv@synsv-ms7e05:~/Documents/CodeSource/GitHub/SampleCode/Docker_&_K8
    $ kubectl get pvc
    NAME       STATUS   VOLUME    CAPACITY   ACCESS MODES   STORAGECLASS        VOLUMEATTRIBUTESCLASS   AGE
    host-pvc   Bound    host-pv   1Gi        RWO            microk8s-hostpath   <unset>                 93s
    ```

1. Complete Config

    ```yaml
    ---
    # Defined Persistent Volume

    apiVersion: v1
    kind: PersistentVolume
    metadata:
      name: host-pv
    spec:
      capacity:
        storage: 1Gi
      volumeMode: Filesystem
      storageClassName: microk8s-hostpath
      accessModes:
        - ReadWriteOnce
      hostPath:
        path: /data
        type: DirectoryOrCreate

    ---
    # Define Persistent Claim

    apiVersion: v1
    kind: PersistentVolumeClaim
    metadata:
      name: host-pvc
    spec:
      volumeName: host-pv
      accessModes:
        - ReadWriteOnce
      resources:
        requests:
          storage: 1Gi

    ---
    apiVersion: apps/v1
    kind: Deployment
    metadata:
      name: story-deployment
    spec:
      replicas: 1
      selector:
        matchLabels:
          app: story-app
      template:
        metadata:
          labels:
            app: story-app
        spec:
          containers:
            - name: story-app-container
              image: syndicate47/synimage:stories-app-1.0.0
              imagePullPolicy: Always
              volumeMounts:
                - mountPath: /app/story
                  name: story-volume
          volumes:
            - name: story-volume
              persistentVolumeClaim:
                claimName: host-pvc

    ---
    apiVersion: v1
    kind: Service
    metadata:
      name: story-service
      # Additional Stuff for MicroK8 Starts
      annotations:
        metallb.universe.tf/address-pool: custom-addresspool
      # Additional Stuff for MicroK8 Ends
    spec:
      selector:
        app: story-app
      ports:
        - protocol: TCP
          port: 80
          targetPort: 3000
      type: LoadBalancer
    ---
    ```