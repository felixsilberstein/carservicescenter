# Car services center appointments REST API

Car services center appointments REST API is a java/spring REST API to manage appointments for "the Car services center"


## Requirements
- MySQL
- java 1.8+
- maven
- docker if using docker images

## First time setup

#### Server Setup
    - Instal and secure mysql
    - Run the SQL file: db.sql
    - Install Java 1.8+
    - Instal Maven

### Docker setup
- Setup docker bridged network:
    ```shell script
    >docker network create --driver bridge appointments-net
    ```
- Pull and run the images/containers:
```shell script
  >docker login
  >docker pull fxarte/appointments-db:latest
  >docker pull fxarte/carservices-appointments:latest
  >docker run --name appointment-db --net=appointments-net -e MYSQL_ROOT_PASSWORD=<my root password> -v <host path to db data>:/var/lib/mysql -d fxarte/appointments-db:latest
  >docker run  --name=api --net=appointments-net --env-file .env -p 8080:8080 -d carservices-appointments:latest

```
## Deployment

### Server
- Connect to your server and cd to the application folder
- Pull the code from source control
- package with maven
- run the application:
    ```shell script
    java -jar target/car-service-center-<version>.jar
  ```

### Docker
```shell script
  docker pull fxarte/appointments-db:latest
  docker pull fxarte/carservices-appointments:latest
  docker run --name appointment-db --net=appointments-net -e MYSQL_ROOT_PASSWORD=<my root password> -v <host path to db data>:/var/lib/mysql -d fxarte/appointments-db:latest
  docker run  --name=api --net=appointments-net --env-file .env -p 8080:8080 -d carservices-appointments:latest
```

## API Usage

```bash
# retrieve an appointment
curl localhost:8080/api/v1/appointments/5

# create an appointment
curl -H "Content-Type: application/json" -X POST -d '{"startDateTime":"2019-08-02 09:00:00","endDateTime":"2019-08-02 10:00:00","customerName":"Felix0802","carId":"Tesla Sx3-0802-1"}' localhost:8080/api/v1/appointments

# update an appointment
curl -H "Content-Type: application/json" -X PUT -d '{"id":5,"status":1,"startDateTime":"2019-08-02 09:00:00","endDateTime":"2019-08-02 10:00:00","customerName":"Felix0802","carId":"Tesla Sx3-0802-1", "serviceType":2, "mechanic":1}' localhost:8080/api/v1/appointments/5

# delete an appointment
curl -H "Content-Type: application/json" -X DELETE localhost:8080/api/v1/appointments/4

```

## Contributing
Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.

Please make sure to update tests as appropriate.

## License
[MIT](https://choosealicense.com/licenses/mit/)


# References:
**Guides:**
- https://www.baeldung.com/spring-boot-sqlite
- https://spring.io/guides/gs/spring-boot-docker/
- https://spring.io/guides/gs/rest-service/
- https://stackify.com/guide-docker-java/
- https://www.mkyong.com/spring-boot/spring-rest-hello-world-example/

Cache:
- https://docs.spring.io/spring-boot/docs/current/reference/html/boot-features-caching.html#boot-features-caching-provider-redis

MySql:
- https://dev.mysql.com/doc/refman/5.7/en/

Docker:
- https://stackify.com/guide-docker-java/
- https://docs.docker.com/v17.09/engine/userguide/networking/#user-defined-networks
- https://docs.docker.com/samples/library/mysql/
- https://spring.io/guides/gs/spring-boot-docker/

Logging:
- https://docs.spring.io/spring-boot/docs/current/reference/html/howto-logging.html

DEvOps:
- https://medium.com/containerum/do-i-need-ci-cd-and-what-tool-should-i-choose-7f0da4bf38fc

Authentication:
- https://medium.com/better-programming/secure-a-spring-boot-rest-api-with-json-web-token-reference-to-angular-integration-e57a25806c50

Testing:
- https://junit.org/junit5/docs/current/user-guide/#overview
- https://www.vogella.com/tutorials/Mockito/article.html
- https://site.mockito.org/