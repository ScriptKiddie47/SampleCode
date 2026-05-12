# JPA

1. ddl-auto -> validate or none is best option for prod.
1. SimpleJPARepository implements JPARepository..and does all the operations
1. PersistantContext -> Works as a First Level Cache
1. @Transaction -> Does dirty checks during commit phase.
1. JPA writes JPQL using @Query & Native Query
    1. `@Query("SELECT p FROM Patient p WHERE p.birthDate > :birthDate")` -> Hibernates converts into pure SQL.
    1. `@Query(value = "SELECT * FROM patient",nativeQuery = true)` -> Hibernate doesn't generate pure SQL for this.

1. When we do a group by call or any aggregation in DB the returned data is different from Entity. So we can use projection here to properly process it else we need to rely on `Objects`.
    1. @Query("SELECT new com.scripter.hospitalmanagement.entity.BgCount(p.bloodGroup,Count(p)) FROM Patient p GROUP BY p.bloodGroup")
    1. Projection Query cannot be done with Native Query
1. Pagination
    1. `Pageable`,`Page` comes from `org.springframework.data.domain`
1. Cascading -> When they are modification to Parent entity how should I child entity behave
1. Orphan Removal ->  An entity that is no longer attached to its parent is the definition of being an orphan