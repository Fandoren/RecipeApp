# RecipeApp

# Required technologies:

- Java 11
- MongoDB 5.0.3 or higher
- Maven 3.8.1 or higher
- InteliJ IDEA

# Installation

### 1. DB initialisation

* install MongoDB from official site (https://www.mongodb.com/try/download/community). Choose default settings
* for convenience, I recommend downloading MongoDBCompass and MongoDB Shell (https://www.mongodb.com/try/download/shell)

 *Optional*

*You can add base values using the dump file (`dump.zip`) included in the project via unzipping archive and executing command `mongorestore <full path to file>\dump`*

### 2. Backend Launch
* Open `RecipeApp` project folder with Intellij IDEA
* Now we will launch microservices, starting with config service 


1. At the top of the window choose `Edit configurations... > Add New Configuration > Application`. 
2. In the name field place desired name (for example `ConfigService`). 
3. Click `Main class` field and in the opened window `Choose Main Class` go in the `Project` tab.
4. Follow the path to the file `ConfigServiceApplication.java` (e.g. `RecipeApp\config-service\src\main\java\com\surmin\config`).
5. Choose it, press `Ok` and in the main window press `Apply` and then `Ok`.
6. At the top, where you chose `Edit configurations...` now you should see your named configuration. Choose it and press `Run` button.
7. If you did everything right, at the bottom, among the logs you will see message like 

```
2022-11-03 23:42:34.645  INFO 16644 --- [           main] .s.c.n.e.s.EurekaAutoServiceRegistration : Updating port to 10100
2022-11-03 23:42:34.758  INFO 16644 --- [           main] c.s.config.ConfigServiceApplication      : Started ConfigServiceApplication in 10.178 seconds (JVM running for 10.902)
```
8. Now you should make similar thing to other microservice

* Microservices start order: 
  1. Config Service
  2. Registry Service
  3. Gateway Service
  4. Uaa Service
  5. Recipe Service
* After launch of all microservice you can go on to launching Frontend part of application

# Swagger links

* [RECIPE SERVICE](http://localhost:10010/swagger-ui/#/)

# Docker

If you want to launch application in docker container, you need to follow next steps:

* Download docker (or Docker Desktop)
* Install docker and launch docker daemon
* In core folder of this project run command `docker-compose up --build`
