# Localization

1. Make application adjustable to different languages

    ```java
    BigDecimal premium = BigDecimal.valueOf(9999);
    Double tax = 0.2;
    Locale uk = Locale.of("en","GB"); // en_GB -> Locale Initialization
    NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(uk); // Formatter Initialization
    NumberFormat percentageFormat = NumberFormat.getPercentInstance(uk);
    String formattedPremium = currencyFormat.format(premium); // £9,999.00
    String formattedtax = percentageFormat.format(tax); // 20%
    ```

## Formatting and Parsing Date and Time Values

1. `java.time.format.DateTimeFormatter` parses and formates date and time values

```java
LocalDate date = LocalDate.now();
Locale locale = Locale.of("en","GB");
DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("EEEE dd MM yyyy",locale);
String result = date.format(dateTimeFormatter); // Friday 03 04 2026
String result2 = dateTimeFormatter.format(date); // Friday 03 04 2026
```

## Localizable Resources

1. This is niche..! Best to avoid