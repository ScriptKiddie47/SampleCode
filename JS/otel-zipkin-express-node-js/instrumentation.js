const { ZipkinExporter } = require("@opentelemetry/exporter-zipkin");
const {
  NodeTracerProvider,
  SimpleSpanProcessor,
} = require("@opentelemetry/sdk-trace-node");
const provider = new NodeTracerProvider();
const exporter = new ZipkinExporter({
  serviceName: "example-app", // Replace with your service name
  url: "http://localhost:9411/api/v2/spans", // Replace with your Zipkin server URL
});
provider.addSpanProcessor(new SimpleSpanProcessor(exporter));
provider.register();
module.exports = { provider };
