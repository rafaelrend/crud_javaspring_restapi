# API REST JAVA SPRING BOOT

## Description
NetBeans Maven Project. 

Java API REST Example with Spring MVC and JPA. In this example there are two CRUDs: Book and Author.

## Resources
- Maven 4.0.0
- Spring Boot Starter version 2.0.4
- JPA with Hibernate (spring-boot-starter-data-jpa)
- TomCat Core 8.5.32.

## Developed with
- Java JDK 1.8
- MYSQL 5.7.11
- NetBeans 8.2 with Maven and TomCat Server.

## How Run Firt Time
1. Open project in NetBeans (or other IDE with Maven) and Build. Wait maven download all packages.
2. Execute script at /extras/script.sql in your mysql database.
3. Change your Mysql configuration, and server port, at application.properties file.
4. Run project as Java Application.
5. Open url localhost:9000 at Browser.

## API Methods.
- /authors    (GET, PUT, DELETE, POST)
- /books      (GET, PUT, DELETE, POST)

## Extra Files (Folder /extras)
- Database create script.
- PostMan Collection (v 2.1) to tests.





