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






# Notes
This repo is a simple ToDo application with 2 controllers: one is pretty (Thymeleaf) and one is REST

This repo demonstrates a few things:
1. Spring Boot
2. Spring Data Cassandra
3. Pivotal Cloud Foundry (PCF) and connecting to a Custom User-Provided Service (CUPS)

## Configuration
To run you need a DSE cluster.  The contact points for that cluster are stored in the `src/main/resources/application.properties` file.

If you are running without PCF, you can just edit the dse.contactPoints parameter with the contact points for the DSE cluster.

If you are running with PCF, you can do the same as above (hard-code the contact points), or you can set up a service to provide those 
parameters (and others, if you like).  I did this via PCF-DEV and a CUPS service.

## Running the application without PCF
Make sure that the `dse.connectionPoints` value in `src/main/resources/application.properties` is set to the IP address of DSE.
Once you build the application with `mvn clean package`, you can start the app via `java -jar target/TodoDemo-0.0.1-SNAPSHOT.jar`.
You can now access the ToDo app via the webpage at `http://localhost:8222/` and the REST API via `http://localhost:8222/rest/`.

## PCF-DEV and CUPS Setup
Make sure that the `dse.connectionPoints` value in `src/main/resources/application.properties` is set to the service variable.  In this
case it would be `vcap.services.dsecups.credentials.contact_points`.
The steps in PCF-DEV are as follows:
1. Download and install PCF-DEV
2. Run `cf dev start`
3. Login to PCF-DEV by running `cf login -a api.local.pcfdev.io --skip-ssl-validation`.  When prompted use the username `user` and the
password `pass`.  The API endpoint should be `api.local.pcfdev.io`.
4. Set up the CUPS service by running `cf cups dsecups -p "contact_points"`.  When prompted, provide the IP/hostname for the contact point
for DSE.
5. Clone this repo, cd into it, and build it via `mvn clean package`
6. Deploy the app by running `cf push --hostname dsetodo`
7. Bind the service to the app by running `cf bind-service dsetodo dsecups`
8. You may need to re-deploy the app, which you can do via `cf push --hostname dsetodo`

You should now be able to access the ToDo app via the webpage at `http://dsetodo.local.pcfdev.io/`.  
The REST API is available at `http://dsetodo.local.pcfdev.io/rest/`.
