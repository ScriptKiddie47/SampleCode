# Opentelemetry


#### A standardization of what distributed systems are doing

#### How to Generate & Transmit the Data ? --> Opentelemetry
#### What will do with the data ? 

## MELT - Metrics Events Logs Traces


Opentelemerty will standardize the data
Use analysis tool  

### SPAN - A span represents a unit of work or operation. Spans are the building blocks of Traces
### Distributed Tracing is Context + Propagation
### Context is nothing but Metadata. It has 2 types 
### Span Context  - Trace ID,Span ID,Trace Flags,Trace State
### Co-relation Context - User defined properties ex : Customer name , policy number
### Propagation - Mechanism we use to bundle up our context & transfer it across services




### Steps

1. Docker image for zipkin and will downloaded and container will be started at the mentioned port
2. docker run --rm -d -p 9411:9411 --name zipkin openzipkin/zipkin ( --rm -> removes a stopped container )
3. 

Digest: sha256:5fd55e6a109233b36d419d7fd2449588d17a6e4da7ed7a3fd0d09c86f1c75a15
Status: Downloaded newer image for openzipkin/zipkin:latest
5b385f0fc86dd1c088a8c2431f6bcab893dc5af158ed70d310f34d761472d002

Zipkin UI : http://localhost:9411

4. npm i @opentelemetry/core
5. Deprecatred -->> npm i @opentelemetry/node  New Package -->> npm i @opentelemetry/sdk-trace-node
5. Deprecatred -->> npm i @opentelemetry/plugin-http New Package -->> npm i @opentelemetry/instrumentation-http ( OpenTelemetry HTTP and HTTPS Instrumentation for Node.js )
6. npm i @opentelemetry/exporter-zipkin
7. Deprecatred -->> npm i @opentelemetry/tracing New Package -->> npm i @opentelemetry/sdk-trace-base
8. npm i express
9. npm i @opentelemetry/instrumentation-express

10. npm install @opentelemetry/sdk-node @opentelemetry/api  @opentelemetry/auto-instrumentations-node   @opentelemetry/sdk-metrics
