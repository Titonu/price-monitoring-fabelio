# Price Monitoring App Fabelio



## Tech
Uses a number of open source projects to work properly:

* [Java 8] 
* [Spring Boot] 
* [H2 Database] 

## Installation

You need to have Maven and [Java 8]  installed.
Java 8 should be used for building in order to support both [Java 8]  at runtime.

### Live Application
Application has deployed on [Heroku].
```sh
https://price-monitoring-app-fabelio.herokuapp.com/
```

### Compile Jar Application
Make sure you have installed [Java 8] before start the application.
```sh
> mvn package clean install spring-boot:repackage
```

### Running Jar Application
Make sure you have installed [Java 8] before compile the code into jar applicaiton.
```sh
> cd  cd price-monitoring/target/
```
```sh
> java -jar price-monitoring-0.0.1-SNAPSHOT.jar
```

### Configure Database
In this  project, the database by default is using H2 Database so you can run the application without initiate database before. But if you  need to see the database scheme and value, you must comment the H2 Database configuration in properties   file and disable comment the MySql Database Configuration, but remember you have installed the  MySql  Databse before and create table new with **price-monitoring-fabelio** name.

### Test Application

To running a whole test:
```sh
mvn test
```

   [Java 8]: <https://www.oracle.com/technetwork/java/javase/overview/java8-2100321.html>
   [Spring Boot]: <http://spring.io/projects/spring-boot>
   [H2 Database]: <https://www.h2database.com/html/main.html>
   [Heroku]: <https://www.heroku.com>
