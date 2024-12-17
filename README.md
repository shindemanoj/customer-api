
# Customer API - Spring Boot Application

## Overview

This project is a RESTful API for managing customer information, built using **Spring Boot** and an **H2 in-memory database**. It includes basic CRUD operations, unit testing, and observability features for application monitoring.

## Features

- **CRUD operations** for the Customer entity:
  - Create, Read, Update, and Delete customers.
- **Observability**:
  - Health check, metrics, and monitoring using Spring Boot Actuator.
- **Containerization**:
  - Dockerfile for containerized deployment.
- **Unit and Integration Tests**:
  - Ensures robust functionality.
- **H2 Database**:
  - Simplified development with an in-memory database.

## Prerequisites

Ensure the following tools are installed:

- **Java 17** or later
- **Maven 3.6+**
- **Docker** (for containerized deployment)

## Build and Run Locally

### 1. Clone the Repository

```bash
git clone https://github.com/your-username/customer-api.git
cd customer-api
```

### 2. Build the Project

Use Maven to package the application:

```bash
mvn clean package
```

### 3. Run the Application

Run the application locally:

```bash
java -jar target/customer-api-0.0.1-SNAPSHOT.jar
```

The application will start on **port 8080**.

---

## Run with Docker

### 1. Build the Docker Image

```bash
docker build -t customer-api:latest .
```

### 2. Run the Container

```bash
docker run -p 8080:8080 customer:latest
```

The API will be accessible at: `http://localhost:8080`

---

## API Endpoints

### **Health Check**
- **GET** `/actuator/health`  
  Verifies application status.

### **CRUD Operations**

1. **Create Customer**  
   **POST** `/api/customers`  
   Request Body:
   ```json
   {
       "firstName": "John",
       "middleName": "M",
       "lastName": "Doe",
       "emailAddress": "john.doe@example.com",
       "phoneNumber": "123-456-7890"
   }
   ```

2. **Get All Customers**  
   **GET** `/api/customers`

3. **Get Customer by ID**  
   **GET** `/api/customers/{id}`

4. **Update Customer**  
   **PUT** `/api/customers/{id}`  
   Request Body:
   ```json
   {
       "firstName": "Jane",
       "middleName": "A",
       "lastName": "Smith",
       "emailAddress": "jane.smith@example.com",
       "phoneNumber": "987-654-3210"
   }
   ```

5. **Delete Customer**  
   **DELETE** `/api/customers/{id}`

---

## Observability

The application uses **Spring Boot Actuator** to provide key observability features:

1. **Health Check**  
   Endpoint: `/actuator/health`  
   Checks the status of the application.

2. **Metrics**  
   Endpoint: `/actuator/metrics`  
   Provides application metrics, such as memory usage, request counts, and response times.
   
   Endpoint: `/actuator/prometheus`
   Exposes metrics in Prometheus format for monitoring.
   Example metrics include:
	•	http_server_requests_seconds: HTTP request duration
	•	jvm_memory_used_bytes: JVM memory usage
	•	process_cpu_usage: CPU usage

3. **HTTP Trace**  
   Endpoint: `/actuator/httptrace`  
   Shows recent HTTP requests and responses for debugging purposes.

4. **Logging Configuration**  
   Endpoint: `/actuator/loggers`  
   Allows dynamic logging level changes.

---

## Testing

Run unit and integration tests with Maven:

```bash
mvn test
```

---

## Deployment with Kubernetes (Optional)

- The project includes a `Dockerfile` for containerization.
- Kubernetes manifests can be created for deploying the container.

---

## License

This project is licensed under the **MIT License**.

---

## Author

Manoj Shinde
