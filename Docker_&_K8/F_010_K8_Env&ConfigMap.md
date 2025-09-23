# Environment Variables

1. We can define `env` variables in Deloyments


```yaml
    spec:
      containers:
        - name: story-app-container
          image: syndicate47/synimage:stories-app-1.0.0
          imagePullPolicy: Always
          env:
            - name: STORY_FOLDER
              value: "story"
```

## Config Map

1. If we want to have our env variables in a separate resource
1. This helps other deployments to reference our env variables
1. `name` is used to access this config map
1. `folder: 'story'` Key value pairs



```yaml
apiVersion: v1
kind: ConfigMap
metadata:
  name: data-store-env
data:
  folder: 'story'
```

1. Apply the configuration 

```ps
$ kubectl get configmap
NAME               DATA   AGE
data-store-env     1      46s
```

1. Lets utilize the configmap now
1. So back to deployment config yaml
1. `configMapKeyRef` : Point to the config map and a key name

```yaml
          env:
            - name: STORY_FOLDER
              valueFrom:
                configMapKeyRef:
                  name: data-store-env
                  key: folder
```

1. The above code pull the value using the key and config map name.

1. Full Code


```yaml
# Config Map

apiVersion: v1
kind: ConfigMap
metadata:
  name: data-store-env
data:
  folder: "story"

---
# Deployment

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
          env:
            - name: STORY_FOLDER
              valueFrom:
                configMapKeyRef:
                  name: data-store-env
                  key: folder
          volumeMounts:
            - mountPath: /app/story
              name: story-volume
      volumes:
        - name: story-volume
          persistentVolumeClaim:
            claimName: host-pvc
```

