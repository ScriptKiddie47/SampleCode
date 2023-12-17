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
