# Car services center appointments REST API

Car services center appointments REST API is a java/spring REST API to manage appointments for "the Car services center"

## Requires

- java 1.8+
- maven


## Installation

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
