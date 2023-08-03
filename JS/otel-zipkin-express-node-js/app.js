const PORT = process.env.PORT || "8080";
const express = require("express");
const app = express();
const { provider } = require("./instrumentation.js");
const http = require("http");
const axios = require('axios').default;

app.get("/", (req, res) => {
  console.log("Default Endpoint HIT");
  res.send(`Hello World`);
});

app.get("/date", async (req, res) => {
  const span = startEndSpan();
  console.log("Return Date");
  res.send({ today: new Date() });
  const response = await httpCall();
  console.log(response);
  endSpan(span);
});

app.listen(parseInt(PORT, 10), () => {
  console.log(`Listening for requtes on http://localhost:${PORT}`);
});

function startEndSpan() {
  console.log("Start Trace");
  const tracer = provider.getTracer("example");
  // Create a new span
  const span = tracer.startSpan("example-span");
  // Add attributes to the span
  span.setAttribute("service.name", "app");
  span.setAttribute("foo", "bar");

  return span;
}

function endSpan(span) {
  // End the span
  span.end();
  console.log("End Trace");
}

async function httpCall() {
  const response = await axios.get("https://reqres.in/api/users/2");
  return response.data;
}
