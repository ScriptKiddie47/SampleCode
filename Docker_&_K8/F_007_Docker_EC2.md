# Lets run Docker on EC2

1. Get an server. SSH into it
1. Below commands to install docker

    ```ps
    # use this approach / these commands:
    $ sudo yum update -y
    $ sudo yum -y install docker
    $ sudo service docker start
    $ sudo usermod -a -G docker ec2-user
    # Make sure to log out + back in after running these commands.
    # Once you logged back in, run this command:
    $ sudo systemctl enable docker
    # Thereafter, you can check whether Docker is available by running:
    $ docker version
    ```
1. Generate the image and push ut to docker hub.
1. Run the image in amazon ec2 machine : `docker run -p 80:80 -d --rm syndicate47/synimage:node-js-app-1.0.0`
1. Check

    ```ps
    [ec2-user@ip-172-31-32-223 ~]$ docker ps -a
    CONTAINER ID   IMAGE                                    COMMAND                  CREATED          STATUS          PORTS                               NAMES
    f8f94b2bca75   syndicate47/synimage:node-js-app-1.0.0   "docker-entrypoint.s…"   20 seconds ago   Up 18 seconds   0.0.0.0:80->80/tcp, :::80->80/tcp   keen_chaplygin
    [ec2-user@ip-172-31-32-223 ~]$ curl http://localhost:80
    <!DOCTYPE html>
    <html lang="en">
    <head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Deployment Example</title>
    <link rel="stylesheet" href="styles.css">
    </head>
    <body>
    <main>
        <h1>This works!</h1>
        <h2>Congratulations, this app seems to run fine!</h2>
    </main>
    </body>
    </html>[ec2-user@ip-172-31-32-223 ~]$ 
    ```

1. Modify the SG group to allow HTTP Requst from anywhere.

    ```ps
    syndicate@pop-os:~/Documents
    $ curl http://65.0.101.166/
    <!DOCTYPE html>
    <html lang="en">
    <head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Deployment Example</title>
    <link rel="stylesheet" href="styles.css">
    </head>
    <body>
    <main>
        <h1>This works!</h1>
        <h2>Congratulations, this app seems to run fine!</h2>
    </main>
    </body>
    </html>syndicate@pop-os:~/Documents
    ```

1. Notice that this is `HTTP`.
1. 