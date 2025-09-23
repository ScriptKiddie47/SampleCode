# Deploy a Front End App

1. So we have a React UI app which calls other services.
1. The ngix image is defined in the Docker file of frontend app.
1. We also have an Nginx server with the below config
1. Nginx reroutes request to our UI app - I think

```nginx
server{
    listen 80;
    location/{
        root /usr/share/nginx/html;
        index index.html index.htm;
        try_files $uri/ /index.html =404;
    }
    include /etc/nginx/extra-conf.d/*.conf;
}
```

1. Okay so we know in a front-end apps the browser as a client request data from another app. Using smart nginx routing we can reroute those request to our containers

```
server{
    listen 80;

    location /api/ {
        proxy_pass http://user-task-service.default:8000/;
    }

    location/{
        root /usr/share/nginx/html;
        index index.html index.htm;
        try_files $uri/ /index.html =404;
    }
    include /etc/nginx/extra-conf.d/*.conf;
}
```

1. Here all request for the `/api` route is directed to `http://user-task-service.default:8000/`.

