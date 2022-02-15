****Book Store Api****

**_TechStack:_**

Java 8, Spring(Spring Boot, Spring Data, Spring Security, Spring Cloud),
Mongo, Eureka, Zuul

**_Architecture:_**

Microservice with three-tier architecture is used. Zuul as a api-gw is used to authenticate the users.
It is also responsible for routing the inner services.
So, the clients of this api will only able to access the api-gw and all other services will be deployed in private network.

Each service uses their own mongo database so that the data is decoupled
The data share is done with inner service communications using feign clients.

Eureka is used for service registry and discovery.




**_Services:_**

user-service:

This is the zuul service. All requests are routed by this service to inner services.
It is also responsible for signin and singup.

book-stock-service:

This is the service where you can add, update, delete book stock.

tracker-service:

It is the service to track to sells of the book. As I didn't implement the order functionality, sell records are tracked by this service.
Sells and the book store is decoupled.

recommendation-service:

Recommends the cheapest books considering the number of sales of the book for a provided genre.
This service uses tracker-service and book-store then merges the data to find the best suitable recommendation.


eureka-service:

This is the service registry.

