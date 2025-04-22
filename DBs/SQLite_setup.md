# Setup

Eveything is a File.

## Direct Installation as SQLite is Very Small in Size

```ps
syndicate@syndicate-H610M-H-DDR4 ~ $ sudo apt install sqlite3
syndicate@syndicate-H610M-H-DDR4 ~ $ sqlite3 --version
3.37.2 2022-01-06 13:25:41 872ba256cbf61d9290b571c0e6d82a20c224ca3ad82971edc46b29818d5dalt1
```

### Connect and Quit out of SQLite CLI

```ps
syndicate@syndicate-H610M-H-DDR4 ~ $ sqlite3
SQLite version 3.37.2 2022-01-06 13:25:41
Enter ".help" for usage hints.
Connected to a transient in-memory database.
Use ".open FILENAME" to reopen on a persistent database.
sqlite> .quit
syndicate@syndicate-H610M-H-DDR4 ~ $ 
```


### Create and Check DB location


```ps
syndicate@syndicate-H610M-H-DDR4 ~/Documents/Tools/SQLite3DBs $ sqlite3 testsqlite3db.db
SQLite version 3.37.2 2022-01-06 13:25:41
Enter ".help" for usage hints.
sqlite> .database
main: /home/syndicate/Documents/Tools/SQLite3DBs/testsqlite3db.db r/w
sqlite> .quit
syndicate@syndicate-H610M-H-DDR4 ~/Documents/Tools/SQLite3DBs $ 
```

### Connect back to the existing database

```ps
SQLite version 3.37.2 2022-01-06 13:25:41
Enter ".help" for usage hints.
Connected to a transient in-memory database.
Use ".open FILENAME" to reopen on a persistent database.
sqlite> .open testsqlite3db.db
sqlite> .databases
main: /home/syndicate/Documents/Tools/SQLite3DBs/testsqlite3db.db r/w
sqlite> 
```
#### Sample Data Set : https://github.com/dtaivpp/car_company_database/blob/master/Car_Database.db

### Make the response Good looking

```ps
sqlite> .mode column
sqlite> select * from models;
model_id  model_name      model_base_price  brand_id
--------  --------------  ----------------  --------
1         The Blonde      23000             1       
2         The Brunette    25000             1       
3         The Red Head    29000             1       
4         Hat             22000             2       
5         Sweater         25000             2       
6         T-Shirt         27000             2       
7         Orange          15000             3       
8         Blue            12000             3       
9         Green           17000             3       
10        LaFerrari       125000            4       
11        450             75000             4       
12        F12 Berlinetta  110000            4       
13        F40             100000            4       
14        Extra           30000             5       
15        Too Much        35000             5       
16        Beats           24000             6       
17        Bars            35000             6       
sqlite>
```

### SQLite Clear Console

1. For Linux : `sqlite> .shell clear`


### Table Describe - pragma

```ps
sqlite> pragma table_info(models);
cid  name              type         notnull  dflt_value  pk
---  ----------------  -----------  -------  ----------  --
0    model_id          INTEGER      0                    1 
1    model_name        VARCHAR(50)  1                    0 
2    model_base_price  INTEGER      1                    0 
3    brand_id          INTEGER      1                    0 
sqlite> 
```

### Show All Tables

```ps
$ sqlite3 Car_Database.db
SQLite version 3.40.1 2022-12-28 14:03:47
sqlite> .database
main: /home/syndicate/Documents/CodeSource/Sqlite3DBs/Car_Database.db r/w
sqlite> .tables
Brands              Car_Vins            Dealer_Brand        Models            
Car_Options         Customer_Ownership  Dealers           
Car_Parts           Customers           Manufacture_Plant 
```
