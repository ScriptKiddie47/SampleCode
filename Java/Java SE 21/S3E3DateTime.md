# Local Date & Time

1. Package -> `java.time` Introduces to us `LocalDate`,`LocalDateTime`,`LocalTime`.

```java
LocalDate date = LocalDate.now(); // 2026-04-02 ( now )
LocalDate april4 = LocalDate.of(2026, Month.APRIL, 4); // 2026-04-04
```

1. This API was introduces in SE8. Prior to that we had `java.util.Date`
1. Immutable. We have operations to plus or minus dates.
1. There is also `isBefore()` or `isAfter()`
1. We can also extract particular items from Date objects like `getYear()`

    ```java
    LocalDate date = LocalDate.now(); // 2026-04-02
    date = date.plusDays(5); // 2026-04-07
    Month month = date.getMonth(); // APRIL
    ```
## Instants,Durations,Period

1. All mutable
1. Duration - Time in Nanoseconds.
1. Periods - Time in units such as years or days.
1. Instants - Instanteneous point on the timeline.

    ```java
    LocalDate today = LocalDate.now(); // 2026-04-02
    LocalDate foolsDay = LocalDate.of(2019, Month.APRIL, 1); // 2019-04-01
    Instant timeInstant = Instant.now(); // 2026-04-02T17:59:09.646293461Z
    int nanoSecondsFromLastSecond = timeInstant.getNano(); // 646293461
    Period howLong = Period.between(foolsDay, today); // P7Y1D -> Its an ISO 8601 which means P -> Period , 7Y -> 7 Years, 1D -> 1 Day
    Duration twoHours = Duration.ofHours(2); // PT2H -> T -> Time separator, 2H — 2 Hours
    long seconds = twoHours.minusMinutes(15).getSeconds(); // 300
    int days = howLong.getDays(); // 1
    ```

1. Instant & Duration is more suited for implmenting system task.
1. Period is more suitable for implementing business logic.

## Zoned Date & Time.

1. We can use 
1. This is confusing. Maybe we skip it.

    ```java
    ZoneId london = ZoneId.of("Europe/London");
    ZoneId la = ZoneId.of("America/Los_Angeles");
    LocalDateTime now = LocalDateTime.now(); // 2026-04-03T14:08:25.351705918
    ZonedDateTime laDateTime = ZonedDateTime.of(now, la); // 2026-04-03T14:08:25.351705918-07:00[America/Los_Angeles]
    ZonedDateTime londonDateTime = ZonedDateTime.of(now, london);  // 2026-04-03T14:08:25.351705918+01:00[Europe/London]
    ```
