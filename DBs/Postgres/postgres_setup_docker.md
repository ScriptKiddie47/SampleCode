# Postgres Docker

### Start a Postgres Instance
```
$ docker run --name syn-postgres -p 5432:5432 -e POSTGRES_PASSWORD=admin -d postgres
```
### Sample DataSet - God DataSet

#### https://github.com/pthom/northwind_psql#

### pgadmin

```
curl https://www.pgadmin.org/static/packages_pgadmin_org.pub | sudo apt-key add
sudo sh -c 'echo "deb https://ftp.postgresql.org/pub/pgadmin/pgadmin4/apt/$(lsb_release -cs) pgadmin4 main" > /etc/apt/sources.list.d/pgadmin4.list && apt update'
sudo sh -c 'echo "deb https://ftp.postgresql.org/pub/pgadmin/pgadmin4/apt/focal/ pgadmin4 main" > /etc/apt/sources.list.d/pgadmin4.list && apt update'
sudo apt install pgadmin4
```

### How to Insert Sample DataSet 'dvdrental.tar'
1. `$ docker cp dvdrental.tar syn-postgres:/home` - Copy file to Docker Container
2. `$ docker exec -it syn-postgres bash` - Connect to Docker SSH bash
3. Create Database ( I did it through DBeaver)
4. Navigate the home folder and execute the command : `pg_restore -c -U postgres -d dvdrental -v "dvdrental.tar" -W`
