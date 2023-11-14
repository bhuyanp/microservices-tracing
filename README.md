## Microservices Distributed Tracing Demo

### Run locally

##### Set up zipkin
docker run -d -p 9411:9411 openzipkin/zipkin
##### Run the services
cd into api-gateway, customer-service, credit-report-service one by one and run
```agsl
mvn spring-boot:run
```

### Run in docker container
```agsl
mvn clean package
```

```agsl
docker-compose up -d
```



### Test
```
curl --location 'http://localhost:8081/api/v1/customer' \
--header 'Content-Type: application/json' \
--data '{
"id": 0,
"firstName": "string",
"lastName": "string",
"email": "string",
"social": 0,
"fraudDetected": true,
"creditScore": 0
}'
```

Or, you could add the customer from the swagger ui interface

http://localhost:8081/

### Examine Zipkin Traces
http://localhost:9411

### Examine Actuator Metrics
http://localhost:8081/actuator/metrics/customerSvc


