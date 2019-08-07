# Car services center appointments REST API

Car services center appointments REST API is a java/spring REST API to manage appointments for "the Car services center"

## Requires

- java 1.8+
- maven


## Deployment

- Setup docker bridged network
    
    `docker network create --driver bridge appointments-net`
    
- Run the db container:
    ```shell
    docker run --name appointments-db --net=appointments-net -e MYSQL_ROOT_PASSWORD=123 -v <path to db/data>:/var/lib/mysql -d mysql
    ```
    
Download the latest stable release
build with maven:
```bash
mvn package
```
run the jar:
```bash
java -jar target/car-service-center-<version>.jar
```

## Usage

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

Logging:
- https://docs.spring.io/spring-boot/docs/current/reference/html/howto-logging.html

DEvOps:
- https://medium.com/containerum/do-i-need-ci-cd-and-what-tool-should-i-choose-7f0da4bf38fc

Authentication:
- https://medium.com/better-programming/secure-a-spring-boot-rest-api-with-json-web-token-reference-to-angular-integration-e57a25806c50