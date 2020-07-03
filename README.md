# Currency Exchange 

Currency Exchange Spring Boot application.
The application handles 5 currencies (EUR, USD, GBP, CHF, HUF).

## Technology stack
I used the following technology stack:
- Spring Boot (http://start.spring.io)
- Hibernate, Spring Data JPA
- Lombok
- Swagger
- Gradle

## Getting Started

### Prerequisites

You will need a MySQL database, for example in a docker container:

```
docker run -d -p 6033:3306 --name=docker-mysql --env="MYSQL_ROOT_PASSWORD=root" --env="MYSQL_PASSWORD=root" --env="MYSQL_DATABASE=exchange_currency" mysql
```

In the `application.properties` you have to set your own MySQL server details:

```spring.datasource.url={replaceWithDataSourceURL}```

```spring.datasource.username={replaceWithUsername}```

```spring.datasource.password={replaceWithPassword}```

Fill the database with sample data, for example use the attached ```data.sql``` file

## Test the application

### JMeter

Use the attached jmx file to try out the abilities of the API

### Postman 

Use the postman to send requests to the API

### Swagger

Use the created Swagger UI to explain how it works

For example:

``` 
http://localhost:8080/swagger-ui.html
```

## Authors

* **Tamas Meszaros**
