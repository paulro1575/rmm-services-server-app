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

To run the postgres container instance, you can run this command:
```bash
docker run --name postgres -p 5432:5432 -e POSTGRES_PASSWORD=rmm-user-2020 -d postgres
```

Remember that if you pull a docker specific postgres version image, you can run the container instance add the version
like this:
```bash
docker run --name {postgres_container_name} -p 5432:5432 -e POSTGRES_PASSWORD={postgres_password} -d postgres:{version}
```

To start the postgres docker container you can use the following command:
```bash
docker start {postgres_container_name}
```

### REST API Service

This services was build using Gradle, so in this content you can use the following tasks:

#### Build

You can build the services using this command:

```bash
./gradlew build
```

#### Run

You can run the services using this command:

```bash
./gradlew bootRun
```
Or executing the jar compiled file, after execution of build command, like this:
```bash
java -jar {rmm-services_path}/build/libs/rmmservices-0.0.1-SNAPSHOT.jar
```
