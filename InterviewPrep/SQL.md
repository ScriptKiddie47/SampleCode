# SQL

1. `group by` columns must appear in `select` column apart form aggregate functions.
1. `where` statements should not used on aggregate function
1. Alias `as` can only be used in select statement. Not in `where` or `group by`
1. `OFFSET` 


SQL
    Tables with rows and columns
    Fixed schema (predefined structure)
    ACID-compliant (strong consistency)
    SQL (Structured Query Language)
    Efficient for complex queries and transactions
    Best for transactional systems (banking, ERP, etc.)
NOSQL
    Document-based, key-value, column-family, or graph-based
    Flexible schema (dynamic and adaptable)
    BASE-compliant (more available, less consistent)
    Varies (e.g., MongoDB uses its own query language)
    Better for large-scale data and fast read/write operations
    Ideal for big data, real-time web apps, and data lakes

# Joins

1. `Inner Join` - `select` t1.id `as` t1_id,t2.id `as` t2_id from `table1` t1 `inner join` table2 t2 `on` t1.id = t2.id
1. `Cross Join` - `select t1.id as t1_id,t2.id as t2_id from table1 t1 cross join table2 t2`

