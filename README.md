# rmm-services-server-app
Java REST API for RMM basic services to allows: 
 - Manage customer accounts
 - Manage customer devices
 - Add, update and remove services to customer devices
 - Calculate monthly price to pay for device services
 
## Technologies
 - PostgreSQL
 - Java 8
 - Gradle 6.4.1
 
## Security

This API uses JWT for manage API access
 
## Requirements

 - PostgreSQL
 - Java 8 

## Setup

To install and run this project you need to:

### PostgreSQL

You can install PostgreSQL database as you wish. I recommend to use postgreSQL inside a docker container to a 
quickly installation.

To install PostgreSQL database docker image last version we can run this command: 
```bash
docker pull postgres
```
If you need to install PostgreSQL database specific version you can run this command:
```bash
docker pull postgres:{version}
```

To run the PostgreSQL container instance, you can run this command:
```bash
docker run --name {postgres_container_name} -p 5432:5432 -e POSTGRES_PASSWORD={postgres_password} -d postgres
```

Remember if you pull a docker specific PostgreSQL version image, you can run the container instance add the version 
like this:
```bash
docker run --name {postgres_container_name} -p 5432:5432 -e POSTGRES_PASSWORD={postgres_password} -d postgres:{version}
```

To start the PostgreSQL docker container you can use the following command:
```bash
docker start {postgres_container_name}
```

### DATABASE SCHEMA

In order to initialize the database schema, a file have been included in the db-scripts folder:
 - /db-scripts/db-schema.sql

In order to load the schema and data into the docker database, we must execute the following commands:

 - Database creation:
 ```bash
 docker exec -it {postgres_container_name} psql -U postgres -c "CREATE DATABASE {database_name};"
```
 - Database user creation:
 ```bash
 docker exec -it {postgres_container_name} psql -U postgres -c "CREATE USER {database_user} WITH PASSWORD '{database_password}';"
```
 - Grant privileges to the user for database:
 ```bash
 docker exec -it {postgres_container_name} psql -U postgres -c "GRANT ALL PRIVILEGES ON DATABASE {database_name} TO {database_user};"
```

Remember that these credentials must be configured into the application.yml file found in src/main/resources/application.yml.

If you want a quick test of the api you can execute the above commands with:

```bash
docker exec -it {postgres_container_name} psql -U postgres -c "CREATE DATABASE rmmservices;"
docker exec -it {postgres_container_name} psql -U postgres -c "CREATE USER rmmuser WITH PASSWORD 'rmm-user-2020';"
docker exec -it {postgres_container_name} psql -U postgres -c "GRANT ALL PRIVILEGES ON DATABASE rmmservices TO rmmuser;"
```

And now you can execute the database schema initialization script with:
```bash
docker exec -i {docker_container_name} psql -U {postgres_user} -d {database_name} -a < db-schema.sql
```
To initialize database schema with same credentials of this api you can execute:
```bash
docker exec -i {postgres_container_name} psql -U rmmuser -d rmmservices -a < db-schema.sql
```

### DATABASE DATA

This api exposes all endpoints for manage database. However, there is a script to fill tables with test data and you can execute it with:
```bash
docker exec -i {docker_container_name} psql -U {postgres user} -d {database_name} -a < db-data.sql
```
To fill database with the same credentials of this api you can execute:
```bash
docker exec -i {postgres_container_name} psql -U rmmuser -d rmmservices -a < db-data.sql
```
> In this data, the default API user is `rmm-user` and the default password is `1234abcd`

### REST API Service

This services uses Gradle Build Tool, so in this context you can use the following tasks:

#### Build

You can build the services using this command:

```bash
./gradlew build
```

### Test
You can run the API unit tests using this command:

```bash
./gradlew test
```

Also, you can run a clean test using this command:

```bash
./gradlew clean test
```

#### Run

You can run the services using this command:

```bash
./gradlew bootRun
```
Also, you can run the services executing the jar compiled file, after execution of build command, like this:
```bash
java -jar {rmm-services_path}/build/libs/rmmservices-0.0.1-SNAPSHOT.jar
```

## ENDPOINTS

The API exposes the next endpoints:

####  Customer

The customer data has the next exposed endpoints:
 - **Register:** POST `http://{HOST}:{PORT}/rmmservices/customer/register`
 
 This endpoint is public for all users. It registers a new customer into database, the request body should be:
 ```json
{
	"username": "new-user",
	"password": "password"
}
```
 The response should be `201 CREATED` status code with the body:
 ```json
{
  "id": 1,
  "username": "new-user"
}
```
 - **Login:**  POST `http://{HOST}:{PORT}/rmmservices/login`
 
This endpoint is public, the customer can use it to sign in into API and get the JWT TOKEN. The request body should be:
```json
{
  "id": 1,
  "username": "new-user"
}
```
The response should be `200 OK` status code with no response body, but 1 header value named token. The token will be used in private endpoints requests.

####  Service
 
 - **Create Service:** POST `http://{HOST}:{PORT}/rmmservices/service/`
 
 This endpoint is private, it requires a header value called `Authorization` with a valid token. 
 It saves a Service into database, the request body should be:
 ```json
{
	"serviceName": "TeamViewer"
}
```
 The response should be `201 CREATED` status code with the body:
 ```json
{
    "id": 1,
	"serviceName": "TeamViewer"
}
```

 - **Update Service:** PUT `http://{HOST}:{PORT}/rmmservices/service/{service-id}`
 
 This endpoint is private, it requires a header value called `Authorization` with a valid token. 
 It updates a Service into database, the request body should be:
 ```json
{
	"serviceName": "TeamViewer"
}
```
 The response should be `200 OK` status code with the body:
 ```json
{
    "id": 1,
	"serviceName": "TeamViewer"
}
```

 - **List services:** GET `http://{HOST}:{PORT}/rmmservices/service/`
 
 This endpoint is private, it requires a header value called `Authorization` with a valid token. 
 It shows all services existed into database, request don't require body.  The response should be `200 OK` status code with the body:
 ```json
[
  {
    "id": 1,
    "serviceName": "Antivirus"
  },
  {
    "id": 2,
    "serviceName": "Cloudberry"
  },
  {
    "id": 3,
    "serviceName": "PSA"
  },
  {
    "id": 4,
    "serviceName": "TeamViewer"
  }
]
```
 - **Delete Service:** DELETE `http://{HOST}:{PORT}/rmmservices/service/{service-id}`
 
 This endpoint is private, it requires a header value called `Authorization` with a valid token. 
 It deletes a Service into database, the request don't require body.  The response should be `200 OK` status code with the body:
 ```json
{
  "result": "Object was deleted successfully"
}
```

####  Device Type
 
 - **Create Device Type:** POST `http://{HOST}:{PORT}/rmmservices/device-type/`
 
 This endpoint is private, it requires a header value called `Authorization` with a valid token. 
 It saves a Device Type into database, the request body should be:
 ```json
{
	"typeName": "Windows WorkStation"
}
```
 The response should be `201 CREATED` status code with the body:
 ```json
{
   "id": 1,
   "typeName": "Windows WorkStation",
   "devicePrice": 4.00
}
```

 - **Update Device Type:** PUT `http://{HOST}:{PORT}/rmmservices/device-type/{device-type-id}`
 
 This endpoint is private, it requires a header value called `Authorization` with a valid token. 
 It updates a Device Type into database, the request body should be:
 ```json
{
	"typeName": "Mac"
}
```
 The response should be `200 OK` status code with the body:
 ```json
{
   "id": 1,
   "typeName": "Mac",
   "devicePrice": 4.00
}
```

 - **List Device Type:** GET `http://{HOST}:{PORT}/rmmservices/device-type/`
 
 This endpoint is private, it requires a header value called `Authorization` with a valid token. 
 It shows all device types existed into database, request don't require body.  The response should be `200 OK` status code with the body:
 ```json
[
  {
    "id": 1,
    "typeName": "Mac",
    "devicePrice": 4.00
  },
  {
    "id": 3,
    "typeName": "Windows Server",
    "devicePrice": 4.00
  },
  {
    "id": 4,
    "typeName": "Windows WorkStation",
    "devicePrice": 4.00
  }
]
```
 - **Delete Device Type:** DELETE `http://{HOST}:{PORT}/rmmservices/device-type/{device-type-id}`
 
 This endpoint is private, it requires a header value called `Authorization` with a valid token. 
 It deletes a Device Type into database, the request don't require body.  The response should be `200 OK` status code with the body:
 ```json
{
  "result": "Object was deleted successfully"
}
```

####  Service Price
 
 - **Create Service Price:** POST `http://{HOST}:{PORT}/rmmservices/service/price/`
 
 This endpoint is private, it requires a header value called `Authorization` with a valid token. 
 It saves a price for a device type into database, the request body should be:
 ```json
{
	"deviceTypeName":"Mac",
	"rmmServiceName":"PSA",
	"price":"4.00"
}
```
 The response should be `201 CREATED` status code with the body:
 ```json
{
  "id": 1,
  "deviceTypeName": "Mac",
  "rmmServiceName": "PSA",
  "price": 4.00
}
```

 - **List Service Price:** GET `http://{HOST}:{PORT}/rmmservices/service/price/`
 
 This endpoint is private, it requires a header value called `Authorization` with a valid token. 
 It shows all device types existed into database, request don't require body.  The response should be `200 OK` status code with the body:
 ```json
[
  {
    "id": 1,
    "deviceTypeName": "Windows WorkStation",
    "rmmServiceName": "TeamViewer",
    "price": 2.00
  },
  {
    "id": 2,
    "deviceTypeName": "Mac",
    "rmmServiceName": "TeamViewer",
    "price": 2.00
  },
  {
    "id": 3,
    "deviceTypeName": "Mac",
    "rmmServiceName": "Cloudberry",
    "price": 2.00
  }
]
```
 - **Delete Service Price:** DELETE `http://{HOST}:{PORT}/rmmservices/service/price/{service-price-id}`
 
 This endpoint is private, it requires a header value called `Authorization` with a valid token. 
 It deletes a price for a device type into database, the request don't require body.  The response should be `200 OK` status code with the body:
 ```json
{
  "result": "Object was deleted successfully"
}
```

####  Customer Devices
 
 - **Create Customer Device:** POST `http://{HOST}:{PORT}/rmmservices/customer/device/`
 
 This endpoint is private, it requires a header value called `Authorization` with a valid token. 
 It saves a customer device into database, the request body should be:
 ```json
{
	"systemName": "User-Mac",
	"deviceTypeName": "Mac"
}
```
 The response should be `201 CREATED` status code with the body:
 ```json
{
    "id": 1,
    "systemName": "User-Mac",
    "deviceTypeName": "Mac",
    "customerId": 1
}
```

 - **Update Customer Device:** PUT `http://{HOST}:{PORT}/rmmservices/customer/device/{customer-device-id}`
 
 This endpoint is private, it requires a header value called `Authorization` with a valid token. 
 It updates a Customer Device into database, the request body should be:
 ```json
  {
    "systemName": "User-iMac",
    "deviceTypeName": "Mac" 
  }
```
 The response should be `200 OK` status code with the body:
 ```json
{
  "id": 1,
  "systemName": "User-iMac",
  "deviceTypeName": "Mac",
  "customerId": 1
}
```

 - **List Customer Devices:** GET `http://{HOST}:{PORT}/rmmservices/customer/device/`
 
 This endpoint is private, it requires a header value called `Authorization` with a valid token. 
 It shows all customer devices existed into database, request don't require body.  The response should be `200 OK` status code with the body:
 ```json
[
  {
    "id": 1,
    "systemName": "User-PC",
    "deviceTypeName": "Windows WorkStation",
    "customerId": 1
  },
  {
    "id": 2,
    "systemName": "User-PC-HOME",
    "deviceTypeName": "Windows WorkStation",
    "customerId": 1
  },
  {
    "id": 3,
    "systemName": "User-Mac",
    "deviceTypeName": "Mac",
    "customerId": 1
  },
  {
    "id": 4,
    "systemName": "User-MacBook",
    "deviceTypeName": "Mac",
    "customerId": 1
  },
  {
    "id": 5,
    "systemName": "User-Server",
    "deviceTypeName": "Mac",
    "customerId": 1
  }
]
```
 - **Delete Customer Device:** DELETE `http://{HOST}:{PORT}/rmmservices/customer/device/{customer-device-id}`
 
 This endpoint is private, it requires a header value called `Authorization` with a valid token. 
 It deletes a Customer Device into database, the request don't require body.  The response should be `200 OK` status code with the body:
 ```json
{
  "result": "Object was deleted successfully"
}
```

####  Customer Services
 
 - **Create Customer Service:** POST `http://{HOST}:{PORT}/rmmservices/customer/service/`
 
 This endpoint is private, it requires a header value called `Authorization` with a valid token. 
 It saves a customer service into database, the request body should be:
 ```json
{
	"serviceName": "Cloudberry"
}
```
 The response should be `201 CREATED` status code with the body:
 ```json
{
  "id": 1,
  "customerId": 1,
  "serviceName": "Cloudberry"
}
```

 - **List Customer Devices:** GET `http://{HOST}:{PORT}/rmmservices/customer/service/`
 
 This endpoint is private, it requires a header value called `Authorization` with a valid token. 
 It shows all customer services existed into database, request don't require body.  The response should be `200 OK` status code with the body:
 ```json
[
  {
    "id": 1,
    "customerId": 1,
    "serviceName": "Antivirus"
  },
  {
    "id": 2,
    "customerId": 1,
    "serviceName": "Cloudberry"
  },
  {
    "id": 3,
    "customerId": 1,
    "serviceName": "TeamViewer"
  }
]
```
 - **Delete Customer Device:** DELETE `http://{HOST}:{PORT}/rmmservices/customer/service/{customer-service-id}`
 
 This endpoint is private, it requires a header value called `Authorization` with a valid token. 
 It deletes a Customer Service into database, the request don't require body.  The response should be `200 OK` status code with the body:
 ```json
{
  "result": "Object was deleted successfully"
}
```

### Calculate Customer Monthly Costs
 
 - **Calculate customer monthly cost:** GET `http://{HOST}:{PORT}/rmmservices/customer/service/bill`
 
 This endpoint is private, it requires a header value called `Authorization` with a valid token. 
 It shows a Montly bill calculation for a customer, the request don't require body. The response should be `200 OK` status code with the body:
 ```json
{
  "output": 71.00,
  "explanation": [
    {
      "service": "5 Devices",
      "monthlyCost": 20.00
    },
    {
      "service": "TeamViewer",
      "monthlyCost": 5.00
    },
    {
      "service": "Antivirus",
      "monthlyCost": 31.00
    },
    {
      "service": "Cloudberry",
      "monthlyCost": 15.00
    }
  ]
}
```

### NOTES
The individual cost per device can be changed on config:device-cost into application.yml properties file located on /src/main/resources/