# 🏗️ Software Architecture with Spring 
**Design scalable and high-performance Java applications with Spring**

<table style="border: none;">
  <tr>
    <td><img src="book-cover.png" alt="Software Architecture with Spring" width="250"/></td>
    <td style="vertical-align: top; padding-left: 20px;">
      <ul>
        <li>🔗 <strong><a href="https://lnkd.in/dZ8Wjqrd">Buy on Packt</a></strong></li>
        <li>🛒 <strong><a href="https://packt.link/P1FKw">Buy on Amazon</a></strong></li>
      </ul>
    </td>
  </tr>
</table>

Welcome to the repository for **"Software Architecture with Spring"**! This book is a practical guide for software engineers, architects, and developers looking to master modern software architecture principles, especially in the context of Java and Spring Framework. 

## 🔍 Overview

This book explores the journey of building robust, scalable, and maintainable applications using a structured approach. It covers a wide range of architectural styles and design principles, equipping readers with insights and strategies for architecting resilient systems. From monolithic applications to microservices, this guide walks through key concepts and provides actionable examples to enhance your software architecture skills.

## 🛠️ Tools to Follow Along the Examples

### 💻 IDEs
- [IntelliJ](https://www.jetbrains.com/idea/download) – JetBrains Java IDE
- [Spring Tools](https://spring.io/tools) – Spring-based Eclipse distribution
- [Visual Studio Code](https://code.visualstudio.com/download) – Lightweight code editor

### 🗄️ Database Clients
- [DBeaver](https://dbeaver.io/) – Universal database client
- [Compass](https://www.mongodb.com/try/download/compass) – MongoDB GUI
- [DataGrip](https://www.jetbrains.com/datagrip/download) – JetBrains SQL IDE

### 📬 Kafka Client
- 🛠️ [Kafka Tool](https://kafkatool.com/download.html) – GUI for managing and browsing Kafka clusters 

### 🔌 API Testing Tools
- [Postman](https://www.postman.com/downloads/) – API platform for building and testing APIs

## 🐳 Containerization
- [Docker](https://www.docker.com/get-started/) – Container platform for building and running applications

### Sample Code

The book provides sample code for each major chapter, allowing readers to experiment and implement concepts in real-world scenarios. Please check each chapter's directory for related code snippets and explanations.

## 📘 Chapter 1: Diving into the Software Architecture
  The examples provided delve into fundamental architectural concepts, such as high and low coupling, cohesion, and the SOLID principles.

### 📁 Folder Structure

  ```
  ch1/
  ├── 01-high-coupling-shopping-application/           # Example with high coupling
  ├── 02-low-coupling-shopping-application/            # Example with low coupling
  ├── 03-low-cohesion-usermanagement-application/      # Example with low cohesion
  ├── 04-high-cohesion-usermanagement-application/     # Example with high cohesion
  ├── 05-solid-srp-library-application/                # SRP - Single Responsibility Principle
  ├── 06-open-closed-library-application/              # OCP - Open/Closed Principle
  ├── 07-liskov-substitution-library-application/      # LSP - Liskov Substitution Principle
  ├── 08-interface-segregation-library-application/    # ISP - Interface Segregation Principle
  └── 09-dependency-inversion-library-application/     # DIP - Dependency Inversion Principle
```

## 📦 Chapter 4: Monolithic Architecture

### 📁 Folder Structure

```
ch4/
├── docker-resources/
│   ├── postgresql/
│   │   └── init.sql        # SQL DDL and DML
│   ├── .env                # PostgreSQL credentials
│   └── docker-compose.yml # Runs PostgreSQL and populates data
└── onlineauction/          # Source code application
```
  ### ⚙️ Prerequisites
    - ☕ Java 21  
    - 🐳 Docker & Docker Compose  
    - 🧰 Maven 3.9.9  

  ### 🚀 Setup Instructions

  #### 🐳 Spin up PostgreSQL with Docker
    1. Go the docker ch4/docker folder
    2. Execute the command: ```docker-compose up -d```

  #### 🧱 Build & Run the Application
    3. Go to the project's folder: onlineauction
    4. Execute the command: ```mvn clean package```
    5. To run the project execute the command:  ```mvn spring-boot:run```

  ### 🌐 Accessing the Application
    6. Open your browser and go to http://localhost:8080
    7. Click on the Login menu  
    8. Use one of the following credentials:
       👑 Administrator : 
          Username: admin - Password: test123
       🧑‍💻 User:
          Username: user - Password: test123
  
  ### 🔗 Database Connection Details

  - **URL**: `jdbc:postgresql://localhost:5432/auction_db`  
  - **User**: `auction_app`  
  - **Password**: `auction123`  
  - **Database**: `auction_db`

## 📦 Chapter 5: Client-Server Architecture

### 📁 Folder Structure

```
ch5/
├── docker-resources/
│   ├── postgresql/
│   │   └── init.sql              # SQL DDL and DML scripts
│   ├── .env                      # PostgreSQL credentials
│   └── docker-compose.yml        # Spins up PostgreSQL and populates data
├── onlineauction/                # Server application
├── onlineauction-client-mobile/  # Mobile client application
└── onlineauction-client-web/     # Web client application
```

---

### ⚙️ Prerequisites

- ☕ Java 21  
- 🐳 Docker & Docker Compose  
- 🧰 Maven 3.9.9  
- 🌐 Node.js and npm  
- ⚛️ React  
- 📱 React Native  
- 📲 Expo Go App  

---

### 🚀 Setup Instructions

#### 🐳 Set Up and Run the Server

1. Navigate to the Docker folder: ch5/docker-resources
2. Start the containers:  
   ```bash
   docker-compose up -d
   ```
3. Navigate to the server project folder: ch5/onlineauction-server
4. Build the application:  
   ```bash
   mvn clean package
   ```
5. Run the application:  
   ```bash
   mvn spring-boot:run
   ```
6. Access OpenAPI documentation:  
   [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)

7. Credentials to generate a token:
   - 👑 **Administrator**  
     - Username: `admin`  
     - Password: `test123`  
   - 🧑‍💻 **User**  
     - Username: `user`  
     - Password: `test123`  


### 🔗 Database Connection Details

  - **URL**: `jdbc:postgresql://localhost:5432/auction_db`  
  - **User**: `auction_app`  
  - **Password**: `auction123`  
  - **Database**: `auction_db`

---

#### 🌐 Run the Web Client

1. Navigate to the web client folder: ch5/onlineauction-client-web

2. Install dependencies:  
   ```bash
   npm install
   ```
3. Start the application:  
   ```bash
   npm start
   ```
4. Access the web app at:  
   [http://localhost:3000](http://localhost:3000)

5. Use the following credentials to log in:  
   - 👑 **Administrator**  
     - Username: `admin`  
     - Password: `test123`  
   - 🧑‍💻 **User**  
     - Username: `user`  
     - Password: `test123`  

---

#### 📱 Run the Mobile Client

1. Navigate to the mobile client folder: ch5/onlineauction-client-mobile  
2. Install Expo CLI globally (you may need to use sudo):  
   ```bash
   npm install -g expo-cli
   ```
3. Install project dependencies:  
   ```bash
   npm install
   ```
4. Fix dependencies (if needed):  
   ```bash
   expo doctor --fix-dependencies
   ```
5. Check configuration:  
   ```bash
   npx expo-doctor
   ```
6. Install required dependencies:  
   ```bash
   npx expo install --check
   ```
7. Update Expo:  
   ```bash
   npm install expo@latest
   ```
8. Install Expo Metro runtime:  
   ```bash
   npx expo install @expo/metro-runtime
   ```
9. Start the app:  
   ```bash
   npx expo start
   ```
10. Press `w` to open the app in the browser, or scan the QR code with Expo Go.  

11. Use the following credentials to log in:  
   - 👑 **Administrator**  
     - Username: `admin`  
     - Password: `test123`  
   - 🧑‍💻 **User**  
     - Username: `user`  
     - Password: `test123`  

---

> 📝 **Note**: Some commands may not be necessary, but following these steps ensures the app runs on Expo Go.  

Here’s the formatted content for Chapter 6: Microservices Architecture in the same style as your Chapter 5 section. You can copy and paste it directly into your README.md:

## 🧩 Chapter 6: Microservices Architecture

### 📁 Folder Structure
```
ch6/
├── docker/
│   ├── postgresql/
│   │   └── init.sql               # SQL DDL and DML scripts
│   ├── mongo-init/
│   │   └── init.js                # MongoDB: create DB, collections, insert data
│   ├── .env                       # PostgreSQL and MongoDB credentials
│   └── docker-compose.yml        # Spins up PostgreSQL, MongoDB, and all microservices
├── docker-resources/
│   ├── postgresql/
│   │   └── init.sql               # SQL DDL and DML scripts
│   ├── mongo-init/
│   │   └── init.js                # MongoDB: create DB, collections, insert data
│   ├── .env                       # PostgreSQL and MongoDB credentials
│   └── docker-compose.yml        # Spins up databases and populates data for IDE execution
├── postman/
│   └── ch6.postman_collection.json  # Postman collection to test all services
├── authentication-services/      # Authentication microservice
├── user-services/                # User microservice
└── product-services/             # Product microservice
```

### ⚙️ Prerequisites
	•	☕ Java 21
	•	🐳 Docker & Docker Compose
	•	🧰 Maven 3.9.9

### 🚀 Setup Instructions

#### 👨‍💻 Run Microservices via IDE
1.	Navigate to the Docker folder: ch6/docker-resources
2.	Start the containers:

```
docker-compose up -d
```

3.	Open the microservices (authentication-services, user-services, product-services) in your IDE
4.	For each microservice, run the following commands:
```  
mvn clean package
mvn spring-boot:run
```
5.	Navigate to the postman folder and import the collection ch6.postman_collection.json [Postman Import Documentation](https://learning.postman.com/docs/getting-started/importing-and-exporting/importing-data/)
6.	Use Postman to trigger requests to the microservices
7.  Use the following credentials to generate the token:  
   - 👑 **Administrator**  
     - Username: `admin@wxauction.com`  
     - Password: `test123`  
   - 🧑‍💻 **User**  
     - Username: `user@wxauction.com`  
     - Password: `test123`


#### 🛠️ Run All Services Without Code (Using Prebuilt Images)

1. Navigate to the Docker folder: ch6/docker
2. Start the containers:
```
  docker-compose up -d
```
3. Navigate to the postman folder and import the collection ch6.postman_collection.json
4. Use Postman to trigger requests to the microservices
5. Use the following credentials to generate the token:  
   - 👑 **Administrator**  
     - Username: `admin@wxauction.com`  
     - Password: `test123`  
   - 🧑‍💻 **User**  
     - Username: `user@wxauction.com`  
     - Password: `test123`

### 📄 Swagger API Documentation

- 🔐 **Authentication Service**: [localhost:8081/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)
- 👤 **User Service**: [localhost:8081/swagger-ui/index.html](http://localhost:8081/swagger-ui/index.html)
- 📦 **Product Service**: [localhost:8082/swagger-ui/index.html](http://localhost:8082/swagger-ui/index.html)

### 🔗 Database Connection Details
	•	PostgreSQL
	•	🛢️ User DB URL: jdbc:postgresql://localhost:5432/user_db
	•	🛢️ Product DB URL: jdbc:postgresql://localhost:5432/product_db
	•	👤 User: auction_app
	•	🔑 Password: auction123
	•	MongoDB
	•	🌐 URL: mongodb://auction_app:auction123@localhost:27017/authentication_db?authSource=admin
	•	👤 User: auction_app
	•	🔑 Password: auction123


## 🧩 Chapter 7: Microservices Patterns with Spring Cloud

### ⚠️ Alerts

- If you encounter issues accessing `http://localhost:8080/v1/api/auth`, please use  
`http://localhost:8072/authentication/v1/api/auth` instead.
The services are running behind the gateway, and the correct route is exposed through the gateway service.

- 🧭 **Service Startup Order Matters**  
  Run the services in the following order:
  1. 🧩 `service-discovery-services`
  2. 💪 `configuration-services`
  3. 🌐 `gateway-services`
  4. 📦 Other services like `authentication-services`, `user-services`, and `product-services`

- ⏱️ **Wait for Eureka**  
  After starting services, wait up to **30 seconds** before making any requests.  
  This ensures Eureka has synchronized its registry (due to default heartbeat/cache refresh intervals).

---

### 📁 Folder Structure

```
ch7/
├── 01-Service-Discovery/              # Introduces Eureka for service discovery
│   ├── service-discovery-services/    # Eureka Server
│   ├── authentication-services/       # Basic authentication service registered with Eureka
│   ├── user-services/                 # User service registered with Eureka
│   └── product-services/              # Product service registered with Eureka
├── 02-Load-Balancer/                  # Demonstrates load balancing with Spring Cloud LoadBalancer
│   ├── service-discovery-services/    # Eureka Server
│   ├── authentication-services/       # Authentication service with load balancing support
│   ├── user-services/                 # User service with load balancing support
│   └── product-services/              # Product service with load balancing support
├── 03-Gateway/                        # Introduces routing via Spring Cloud Gateway
│   ├── service-discovery-services/    # Eureka Server
│   ├── gateway-services/              # Spring Cloud Gateway routing requests
│   ├── authentication-services/       # Auth service behind the gateway
│   ├── user-services/                 # User service 
│   └── product-services/              # Product service
├── 04-Configuration-Server/           # Centralized configuration with Spring Cloud Config
│   ├── online-auction-configuration/  # Git-based configuration repository
│   ├── service-discovery-services/    # Eureka Server
│   ├── configuration-services/        # Spring Cloud Config Server
│   ├── gateway-services/              # Gateway consuming config from config server
│   ├── authentication-services/       # Auth service using centralized config
│   ├── user-services/                 # User service using centralized config
│   └── product-services/              # Product service using centralized config
├── 05-Resiliency/                     # Applies resilience patterns with Resilience4J
│   ├── service-discovery-services/    # Eureka Server
│   ├── configuration-services/        # Config Server
│   ├── gateway-services/              # Gateway 
│   ├── authentication-services/       # Auth service with esilience support such as circuit breaker and timeouts.
│   ├── user-services/                 # User service with resilience 
│   └── product-services/              # Product service
├── docker-resources/
│   ├── postgresql/
│   │   └── init.sql               # SQL DDL and DML scripts
│   ├── mongo-init/
│   │   └── init.js                # MongoDB: create DB, collections, insert data
│   ├── .env                       # PostgreSQL and MongoDB credentials
│   └── docker-compose.yml        # Spins up databases and populates data for IDE execution
└── postman/                           # Postman collection to test all services
    └── ch7.postman_collection.json
```

---

### ⚙️ Prerequisites

- ☕ Java 21  
- 🐳 Docker & Docker Compose  
- 🧰 Maven 3.9.9  

---

### 🚀 Setup Instructions

#### 👨‍💻 Run Microservices via IDE

1. Navigate to the Docker folder: `ch7/docker-resources`
2. Start containers:
   ```bash
   docker-compose up -d
   ```
3. Open the microservices in your IDE  
   **Run them in this order:**
   - 🧩 Discovery Services
   - 💪 Configuration Services
   - 🌐 Gateway Services
4. Go to the desired chapter folder:
   - `01-Service-Discovery`
   - `02-Load-Balancer`
   - `03-Gateway`
   - `04-Configuration-Server`
   - `05-Resiliency`

   For each service:
   ```bash
   mvn clean package
   mvn spring-boot:run
   ```
   To run multiple instances (e.g., for load balancing):
   ```bash
   mvn spring-boot:run -Dspring-boot.run.arguments="--server.port=<port_number>"
   ```
5. Import the Postman collection from the `postman` folder  
   📄 [Postman Import Documentation](https://learning.postman.com/docs/getting-started/importing-and-exporting/importing-data/)
6. Use Postman to send requests to your services

---

### 🔗 Database Connection Details

- **PostgreSQL**
  - 🛢️ User DB URL: `jdbc:postgresql://localhost:5432/user_db`
  - 🛢️ Product DB URL: `jdbc:postgresql://localhost:5432/product_db`
  - 👤 User: `auction_app`
  - 🔑 Password: `auction123`

- **MongoDB**
  - 🌐 URL: `mongodb://auction_app:auction123@localhost:27017/authentication_db?authSource=admin`
  - 👤 User: `auction_app`
  - 🔑 Password: `auction123`

## 🧩 Chapter 8: Event-Driven Architecture

### 📁 Folder Structure

```
ch8/
├── docker-resources/
│   ├── kafka/
│   │   └── docker-compose.yml         # Runs a Kafka instance
│   ├── databases/
│   │   ├── postgresql/
│   │   │   └── init.sql               # SQL DDL and DML scripts
│   │   ├── mongo-init/
│   │   │   └── init.js                # MongoDB: create DB, collections, insert data
│   │   ├── .env                       # PostgreSQL and MongoDB credentials
│   │   └── docker-compose.yml        # Runs PostgreSQL and MongoDB with populated data
├── postman/
│   └── ch08.postman_collection.json   # Postman collection for chapter 8 services
├── service-discovery-services/        # Eureka server for service discovery
├── configuration-services/            # Spring Cloud Config server
├── gateway-services/                  # Spring Cloud Gateway for routing
├── authentication-services/           # Authentication microservice
├── user-services/                     # User management microservice
├── product-services/                  # Product management microservice (optional)
├── auction-services/                  # Auction microservice
├── escrow-services/                   # Escrow microservice
```

---

### ⚙️ Prerequisites

- ☕ Java 21  
- 🐳 Docker & Docker Compose  
- 🧰 Maven 3.9.9  

---

### 🚀 Setup Instructions

#### 👨‍💻 Run Microservices via IDE

1. Navigate to the databases folder:  ch8/docker-resources/databases
   ```bash
   docker-compose up -d
   ```

2. Navigate to the Kafka folder:  ch8/docker-resources/kafka
   ```bash
   docker-compose up -d
   ```

3. Open the microservices in your IDE from the `ch8` folder.

   🧭 **Run the services in the following order**:
   - `service-discovery-services`
   - `configuration-services`
   - `gateway-services`
   - `authentication-services`
   - `user-services`
   - `auction-services`
   - `escrow-services`
   - `product-services` (optional)

4. Import the Postman collection from the `postman` folder:  
   📄 [Postman Import Documentation](https://learning.postman.com/docs/getting-started/importing-and-exporting/importing-data/)

5. Use Postman to test the services.

---

### 🔗 Database Connection Details

- **PostgreSQL**
  - 🛢️ User DB URL: `jdbc:postgresql://localhost:5432/user_db`
  - 🛢️ Product DB URL: `jdbc:postgresql://localhost:5432/product_db`
  - 👤 User: `auction_app`
  - 🔑 Password: `auction123`

- **MongoDB**
  - 🌐 URL: `mongodb://auction_app:auction123@localhost:27017/authentication_db?authSource=admin`
  - 👤 User: `auction_app`
  - 🔑 Password: `auction123`

### 📡 Kafka Connection Details

- **Kafka**
  - 📡 Bootstrap Server: localhost:9092
  - 🔑 Security Type: Plaintext

- ## Chapter 9 : Pipe and Filter and Serverless Architecture
  
    - ch9:
      - docker-resources/
        - postgresql/
          - init.sql -> SQL DDL and DML
        - .env -> PostgreSQL credentials
        - docker-compose.yml -> Run an image of PostgreSQL and populate it.
      - postman
        - ch9.postman_collection.json -> Postman collection for chapter 9 to request the services.
      - data-files/ -> CSV files that are to be processed and their data inserted into the database.
        - Users.csv  -> User's CSV file that contains data from user's database.
        - Products.csv -> Product's CSV file that contains data from product's database.
        - Bids.csv -> Bid's CSV file that contains data from bid's database.
        - Auctions.csv -> Auction's CSV file that contains data from auction's database.
      - etl-batch-process/ -> Code to process the data files and save them to database. (Pipe and Filter architecture code)
      - alerting-faas -> Code to send alerts to Slack channel (group) informing when the batch process is completed.  (Serverless architecture)

  #### Prerequisites:
      - Java 21
      - Docker and Docker Compose
      - Maven 3.9.9

  ## Instructions: 
    ### To set up environment and run the project (Run the services via IDE):
    ## Piper and Filter Architecture
    1. Go the docker ch9/docker-resources folder
    2. Execute the command: ```docker-compose up -d```
    3. Open the etl-batch-process application into your favorite IDE 
    4. For each microservices execute the command: ```mvn clean package``` and then  ```mvn spring-boot:run```    
    6. Now, execute the request for the desired service.
    5. To check the data inserted into tables, connect to the PostgreSQL: 
        - Url: jdbc:postgresql://localhost:5432/report_db
        - User: auction_app
        - Password: auction123

    ## Serverless Architecture
    1. Open the alerting-faas application into your favorite IDE 
    2. Execute the command: ```mvn clean package``` and then  ```mvn spring-boot:run```    
    3. Go to the project's folder: postman and import the collection
    4. Now, execute the request for the desired service.

- ## Chapter 10 : Security
  
    - ch10:
      - docker-resources/
        - postgresql/
          - init.sql -> SQL DDL and DML
        - mongo-init/
          - init.js -> Create database, collection and insert data.
        - .env -> PostgreSQL and MongoDB credentials
        - docker-compose.yml -> Run images of PostgreSQL and MongoDB, populate them with data, and execute both databases.
      - authorization-server → The Authorization Server, implemented using the Spring Authorization Server framework, responsible for authenticating users and issuing access tokens.
	    -	client-application → The Client Application, which authenticates with the Authorization Server to obtain tokens and make requests to the Resource Server.
	    - product-services → The Resource Server, which hosts protected resources and validates access tokens before serving requests.
 
  #### Prerequisites:
      - Java 21
      - Docker and Docker Compose
      - Maven 3.9.9

  ## Instructions: 
    ### To set up environment and run the project (Run the services via IDE):
    1. Go the docker ch10/docker-resources/databases folder.
    2. Execute the command: ```docker-compose up -d``` to run the databases.
    3. Go to ch10 folder and open the microservices into your favorite IDE.
       * RUN THE SERVICES IN THE FOLLOWING ORDER: 
         * authorization-server
         * product-services
         * client-application
    4. To connect to the PostgreSQL: 
        - Url user database: jdbc:postgresql://localhost:5432/user_db
        - Url product database: jdbc:postgresql://localhost:5432/product_db
        - User: auction_app
        - Password: auction123
    5. To connect to the MongoDB: mongodb://auction_app:auction123@localhost:27017/
    authentication_db?authSource=admin
        - User: auction_app
        - Password: auction123    

- ## Chapter 11 : Observability
  
    - ch11:
      - docker-resources/
        - databases/
          - postgresql/
            - init.sql -> SQL DDL and DML
          - mongo-init/
            - init.js -> Create database, collection and insert data.
          - .env -> PostgreSQL and MongoDB credentials
          - docker-compose.yml -> Run images of PostgreSQL and MongoDB, populate them with data, and execute both databases.
        - observability/
          - docker-compose.yml -> Run images of Elasticsearch, Logstash, Kibana, Opentelemetry Collector and Zipkin.
          - otel-collector-config.yml -> The configuration file for the OpenTelemetry Collector. It defines settings for receivers, processors, and exporters, specifying how telemetry data (logs, metrics, and traces) should be collected, processed, and sent to observability backends like Jaeger, Prometheus, or Elasticsearch.
          - logstash/
              - pipeline/
                - logstash.conf -> Logstash configuration file to instruct Logstash to process incoming logs from Spring Boot applications and send them to Elasticsearch.
      - postman
        - ch11.postman_collection.json -> Postman collection for chapter 11 to request the services.
      - online-auction-configuration -> Applications properties saved on GitHub
      - service-discovery-services -> Service discovery (Eureka Server) code.
      - configuration-services -> Configuration service's code.
      - gateway-services -> Gateway services code.
      - authentication-services -> Authentication services code.
      - user-services -> User services code.
      - product-services -> Product services code.

  #### Prerequisites:
      - Java 21
      - Docker and Docker Compose
      - Maven 3.9.9

  ## Instructions: 
    ### To set up environment and run the project (Run the services via IDE):
    1. Go the docker ch11/docker-resources/databases folder.
    2. Execute the command: ```docker-compose up -d``` to run the databases.
    3. Go the docker ch11/docker-resources/observability folder.
    4. Execute the command: ```docker-compose up -d``` to run the Elasticsearch, Logstash, Kibana, Opentelemetry Collector and Zipkin.
    5. Go to ch11 folder and open the microservices into your favorite IDE.
       * RUN THE SERVICES IN THE FOLLOWING ORDER: 
         * service-discovery-services
         * configuration-services
         * gateway-services
         * authentication-services
         * user-services
         * product-services (optional)
    6. To connect to the PostgreSQL: 
        - Url user database: jdbc:postgresql://localhost:5432/user_db
        - Url product database: jdbc:postgresql://localhost:5432/product_db
        - User: auction_app
        - Password: auction123
    7. To connect to the MongoDB: mongodb://auction_app:auction123@localhost:27017/
    authentication_db?authSource=admin
        - User: auction_app
        - Password: auction123    

- ## Chapter 12 : Testing
  
    - ch12:
      - docker-resources/
        - databases/
          - postgresql/
            - init.sql -> SQL DDL and DML
          - mongo-init/
            - init.js -> Create database, collection and insert data.
          - .env -> PostgreSQL and MongoDB credentials
          - docker-compose.yml -> Run images of PostgreSQL and MongoDB, populate them with data, and execute both databases.
        - observability/
          - docker-compose.yml -> Run images of Elasticsearch, Logstash, Kibana, Opentelemetry Collector and Zipkin.
      -JMeter/
        - Thread Group.jmx -> This is the configuration file for the stress test. If you wish, you can import it into JMeter instead of following the steps.
      - online-auction-configuration -> Applications properties saved on GitHub
      - service-discovery-services -> Service discovery (Eureka Server) code.
      - configuration-services -> Configuration service's code.
      - gateway-services -> Gateway services code.
      - authentication-services -> Authentication services code.
      - user-services -> User services code.The unit and integration tests lie here.
      - product-services -> Product services code.
      

  #### Prerequisites:
      - Java 21
      - Docker and Docker Compose
      - Maven 3.9.9
      - Apache JMeter 5.6.3

  ## Instructions: *** IT IS NEEDED TO RUN THE STRESS TEST ONLY:
    ### To set up environment and run the project (Run the services via IDE) 
    1. Go the docker ch12/docker-resources folder.
    2. Execute the command: ```docker-compose up -d``` to run the databases.
    3. Go the docker ch11/docker-resources/observability folder.
    4. Execute the command: ```docker-compose up -d``` to run the Elasticsearch, Logstash, Kibana, Opentelemetry Collector and Zipkin.
    4. Go to ch12 folder and open the microservices into your favorite IDE.
       * RUN THE SERVICES IN THE FOLLOWING ORDER: 
         * service-discovery-services
         * configuration-services
         * gateway-services
         * authentication-services
         * user-services
         * product-services (optional)
    5. To connect to the PostgreSQL: 
        - Url user database: jdbc:postgresql://localhost:5432/user_db
        - Url product database: jdbc:postgresql://localhost:5432/product_db
        - User: auction_app
        - Password: auction123
    6. To connect to the MongoDB: mongodb://auction_app:auction123@localhost:27017/
    authentication_db?authSource=admin
        - User: auction_app
        - Password: auction123 

### 👨‍💼 Who This Book is For

  - 🔧 Software architects looking to deepen Spring-based architecture skills
  - 💻 Developers & engineers designing scalable systems
  - 👨‍📈 Technical leads focused on performance, scalability, and security

## 💬 Feedback and Contributions

Your feedback is invaluable! Feel free to contact us about issues, suggest improvements, or share your thoughts. This repository will be updated with any errata and additional resources to accompany the book.

## 👤 About the Author
Wanderson Xesquevixos is a seasoned software engineer and architect with over twenty-five years of experience. He has dedicated his career to developing and designing complex systems for many global companies in the banking, telecom, tourism, retail, and e-commerce industries. He earned his computer science degree from UNIVAP in 2005. He holds an MBA in Data Science and Analytics and is pursuing another MBA in Software Engineering from the prestigious Universidade de São Paulo (USP). He holds certifications as a Java Programmer, Web Component Developer, JEE Business Component Developer, and AWS Certified Solutions Architect. Originally from Jacareí, SP, Wanderson resides in São Francisco do Sul, SC, with his wife Caroline and son Alexander.
<div align="center"><img src="me.png" alt="Software Architecture with Spring" width="50%"/></div>


*🚀 Thank you for embarking on this journey in software architecture! Happy coding and architecting!*