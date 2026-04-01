# Assignment 4 PROG3360 Group 2 Chhetri,Hall,Turner,Rogers 
# Domexa Rentals – Observability Task Force

## Part 5 Distributed Tracing with Zipkin 

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
  "rent": 2000
}

``` POST http://localhost:8081/api/tenants``` 
{
  "id": "204",
  "name": "Jim Halpert",
  "address": "123 Main Street Kitchener ON",
  "email": "jim@gmail.com",
  "rent": 2000
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
