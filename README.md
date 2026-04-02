# Assignment 4 PROG3360 Group 2 Chhetri,Hall,Turner,Rogers 
# Domexa Rentals – Observability Task Force

## Part 1 Running the TenantApi

### Step 1: Download repository

Clone the GitHub repository in order to set up all the necessary TenantApi files.

### Step 2: Start TenantApi

From IntelliJ (or a similar IDE), start the TenantApiApplication service. It should start running on port 8081.

### Step 3: Test the API

Inside of a web browser or application such as Postman, send HTTP requests to localhost:8081/api/tenants. The JSON structure for a new tenant is:

```
{
    "id" : int,
    "name" : string,
    "address" : string,
    "email" : string,
    "rent" : float,
    "amountOwing" : float
}
```

## Part 2 Spring Boot Actuator

Now that the API is up and running, the actuator endpoints have been configured to be exposed.

### Health

To see the health endpoint, go to:

```http://localhost:8081/actuator/health```

### Metrics

For names of available metrics, go to:

```http://localhost:8081/actuator/metrics```

Metric #43 is our custom metric, named tenantapi.tenantcount. To view that metric, go to:

```http://localhost:8081/actuator/metrics/tenantapi.tenantcount```

As we have yet to add any tenants, its value will be 0.








### Prometheus

To see the raw Prometheus data, go to:

```http://localhost:8081/actuator/prometheus```



### Info

To see the info endpoint, go to:

```http://localhost:8081/actuator/info```

## Install Prometheus

From the official Prometheus site, make sure the correct version is installed for your machine. https://prometheus.io/download/

##Part3 
Pre requisties
Tenant API running on:
http://localhost:8081

step 1: Start Prometheus
Open terminal in the Prometheus directory and run:
.\prometheus.exe --config.file=prometheus.yml
Open Prometheus UI in browser:
http://localhost:9090

step 2: Verify Service Connection
Go to Status → Targets
Check the tenant-api job(status= up)

step 3: Validate JVM Metrics
Go to Graph tab
Enter query:
jvm_memory_used_bytes

step 4: Validate HTTP Metrics
Enter query:
http_server_requests_seconds_count
GET request:
http://localhost:8081/api/tenants
POST request:
POST http://localhost:8081/api/tenants

step 5: Custom metrics
Todo

## Part 4 Grafana Dashboards

### Step 0

download the latest version of grafana at https://grafana.com/grafana/download?platform=windows 

### Step 1

go to http://localhost:3000 

### Step 2

login using username: admin and password: admin

### Step 3

click dashboards then new then import

### Step 4

import each of the grafana json files separately

## Part 5 Distributed Tracing with Zipkin 

### Step 0: Zipkin Configuration already completed in the application.properties

### Step 1: Zipkin Quickstart (Terminal)

```docker run -d -p 9411:9411 openzipkin/zipkin``` 

### Verify it's running

```docker ps``` 

### Step 2: Verify that the Zipkin UI is Running (browser)

http://localhost:9411

### Step 3: Generate traces through endpoint calls via Postman

Run the application

Open Postman to run a few api calls to populate data for Zipkin to display

``` GET http://localhost:8081/api/tenants ``` 

``` POST http://localhost:8081/api/tenants``` 
{
  "id": "205",
  "name": "Pam Halpert",
  "address": "123 Main Street Kitchener ON",
  "email": "pam@gmail.com",
  "rent": 2000,
  "amountOwing" : 2000
}

``` POST http://localhost:8081/api/tenants``` 
{
  "id": "204",
  "name": "Jim Halpert",
  "address": "123 Main Street Kitchener ON",
  "email": "jim@gmail.com",
  "rent": 2000,
  "amountOwing" : 2000
}

``` GET http://localhost:8081/api/tenants/205``` 

### Step 5: Open the Zipkin UI to View the Traces

http://localhost:9411

After running the above requests in Postman, there should be 4 traces visible in the Zipkin UI.

### Step 6: Show Trace IDs and Spans 

Click "Show" on one of the traces

Select the GET trace, the Trace ID and Span is now visible

### Step 7: Latency breakdown

In that same trace, the duration will be visible. A simple GET request, the duration was  X ms. Since this is a simple application, the whole latency is just the time spent in the controller, and the duration is being handled by a single service.

### Step 8: Explanation of Trace Flow 

HTTP request made to Tenant Service is automatically traced by Micrometer, and sent to Zipkin. When a request is made, Zipkin will assign a unique trace ID. Each trace contains a trace ID, total spans, duration, number of services, the outcome of the request, and other attributes. In the case of this assignment, there is only one span as we only have one service and one controller. The duration will show how long the request took (latency), this can help to determine slow endpoints. If there were multiple services, there would be a span per service.  

In summary, every HTTP request becomes a trace, and Controller & Service becomes a span. This is enabled with Spring Boot dependencies for micrometer tracing. 
