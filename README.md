# Sample To Do List web application using Spring Boot and DSE

A simple Todo list application using Spring Boot with the following options:

- Spring Data Cassandra for persistence.
- Thymeleaf templae for the rendering.
- REST API for simple add/list operations.

To build and run the sample from a fresh clone of this repo:

## Configure DSE

1. Create a keyspace and table in DSE via `cqlsh -f src/main/resources/schema.ddl`



## Build and run the sample

1. `mvn clean package`
2. `java -jar target/TodoDemo-0.0.1-SNAPSHOT.2ar`
3. Open a web browser to http://localhost:8222

As you add and update tasks in the app you can verify the changes in the database through cqlsh using simple statements like 
`select * from demo_sdc.todo`.