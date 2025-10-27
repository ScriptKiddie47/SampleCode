# Ingress with Micro K8s

1. https://microk8s.io/docs/addon-ingress

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

# Simple Web App Ingress

apiVersion: networking.k8s.io/v1
kind: Ingress
metadata:
  name: http-ingress
spec:
  rules:
  - http:
      paths:
      - path: /
        pathType: Prefix
        backend:
          service:
            name: simple-web-app-service
            port:
              number: 5000

---
```

Note: The host must be blank for Ingress