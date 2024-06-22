# Reading CSV

1. Gradle Dependency - `implementation 'com.opencsv:opencsv:5.9'`
1. Sample CSV file

   ```csv
   name, rollno, department, result, cgpa
   amar, 42, cse, pass, 8.6
   rohini, 21, ece, fail, 3.2
   aman, 23, cse, pass, 8.9
   rahul, 45, ee, fail, 4.6
   pratik, 65, cse, pass, 7.2
   raunak, 23, me, pass, 9.1
   suvam, 68, me, pass, 8.2
   ```

1. Java Model 

   ```java
   package com.scripter.csvreader.model;
   import com.opencsv.bean.CsvBindByName;
   public class School {
	   @CsvBindByName
	   private String name;
	   @CsvBindByName
	   private int rollno;
	   @CsvBindByName
	   private String department;
	   @CsvBindByName
	   private String result;
	   @CsvBindByName
	   private double cgpa;
      .
      . 
      // Getters & Setters 
   }
   ```
1. Service Code

   ```java
   @Service
   public class CsvReaderService {
	   public List<School> getStudentResults(){	
		   try {
			   InputStream inputStream = new ClassPathResource("school.csv").getInputStream();
			   Reader targetReader = new InputStreamReader(inputStream);
			   return new CsvToBeanBuilder<School>(targetReader)
				          .withType(School.class).build().parse();
		   } catch (Exception e) {
			   e.printStackTrace();
		   }
		   return null;
	   }
   }
   ```
1. Service Code to make it make the function resuable
  
   ```java
	   @SuppressWarnings("unchecked")
	   public <T extends CsvClasses> List<T> readCSVFromFile(T obj, String filename){
		   try {
			   InputStream inputStream = new ClassPathResource(filename).getInputStream();
			   Reader targetReader = new InputStreamReader(inputStream);
			   return new CsvToBeanBuilder<T>(targetReader)
				          .withType((Class<? extends T>) obj.getClass())
				          .build()
				          .parse();
		   } catch (Exception    e) {
			   e.printStackTrace(); 
		   }
		   return null;
	   }
   ```
