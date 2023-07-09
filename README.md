# Challenge 3 - Compass.UOL

---
This challenge consists of an ecosystem of microservices that will be responsible for the management of users and products.
There are five microservices in this project, all of them written in [Java 17](https://www.oracle.com/java/technologies/downloads/#java17) and using [Spring Boot](https://spring.io/projects/spring-boot) as the framework.

| Microservice    | Description                      | Port            |
|-----------------|----------------------------------|-----------------|
| naming-server   | Eureka Naming Server             | 8761            |
| api-gateway     | application gateway              | 8080            |
| ms-auth         | authentication and authorization | 8100            |
| ms-notification | notification via email           | 8800 (not used) |
| ms-products     | product management               | 8200            |
| ms-users        | user management                  | 8300            |

## How to run
To run this project, you will need to have [Docker](https://www.docker.com/) installed on your machine. After that, you can run the following command in the root folder of the project:
```bash
docker compose up
```
This command will run all the dependencies of the project, such as the database and the message broker, and then you can run the microservices manually.

## How to use

### Authentication
To use the microservices, you will need to authenticate yourself. To do that, you will need to send a POST request to the authentication microservice at the endpoint `/oauth/token` via http basic authentication. Having a
successful authentication you will receive a token that you will need to send in the header of every request to the microservices.

### Products
To manage the products, you will need to send a request to the product's microservice. The following endpoints are available:

| Method | Endpoint                                                     | Description                                        |
|--------|--------------------------------------------------------------|----------------------------------------------------|
| POST   | /products                                                    | Create a new product                               |
| GET    | /products/:id                                                | Get a product by id                                | 
| GET    | /products/?page=1&linesPerPage=5&direction=DESC&orderBy=name | Get a list of products with pagination and sorting |
| DELETE | /products /:id                                               | Delete a product by id                             |
| PUT    | /products /:id                                               | Update a product by id                             |

### Users
To manage the users, you will need to send a request to the user's microservice. The following endpoints are available:

| Method | Endpoint                                                     | Description                                        |
|--------|--------------------------------------------------------------|----------------------------------------------------|
| POST   | /users                                                       | Create a new user                                  |
| GET    | /users/:id                                                   | Get a user by id                                   |
| PUT    | /users/:id                                                   | Update a user by id                                |

## Technologies
The following technologies were used in this project:
 - [Java 17](https://www.oracle.com/java/technologies/downloads/#java17)
 - [Spring Boot](https://spring.io/projects/spring-boot)

For the database and message broker, the following technologies were used:
 - [PostgreSQL](https://www.postgresql.org/)
 - [RabbitMQ](https://www.rabbitmq.com/)

For the authentication and authorization, the following technologies were used:
 - [Spring Security](https://spring.io/projects/spring-security)

For the microservice management and communication, the following technologies were used:
 - [Eureka Naming Server](https://spring.io/projects/spring-cloud-netflix)
 - [Eureka Client](https://spring.io/projects/spring-cloud-netflix)

For the API gateway, the following technologies were used:
 - [Spring Cloud Gateway](https://spring.io/projects/spring-cloud-gateway)

For the email notification, the following technologies were used:
 - [Spring Mail](https://spring.io/projects/spring-boot)
 - [Google SMTP](https://support.google.com/a/answer/176600?hl=en)

## Author
- Guilherme Silva
