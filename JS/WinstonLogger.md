# Winston Logger Simple


### Logger Factory

```js
const { createLogger, format, transports } = require("winston");
const { combine, timestamp, label, printf } = format;

const myFormat = printf(({ level, message, label, timestamp }) => {
  return `${timestamp} [${label}] ${level.toUpperCase()}: ${message}`;
});

const customLogger = (customerLabel) => {
  const logger = createLogger({
    level: process.env.loglevel | "debug",
    format: combine(label({ label: customerLabel }), timestamp(), myFormat),
    transports: [new transports.Console()],
  });
  return logger;
};

module.exports = customLogger;
```

### Logger Implementation

```js
const customLogger = require("./logger-factory");
const logger = customLogger("app.js");

logger.info("Hello There");
logger.info("Hello There");
logger.info("Hello There");
logger.info("ndustry. Lorem Ipsu");
logger.debug(
  "This is the older Node.js syntax used before ES6 modules. Still commonly found in older projects or some Node.js setups."
);
logger.info("Hello There");
logger.info(
  "ng, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset "
);
logger.info("Hello There");
```

### Results

```ps
syndicate@syn-debian:~/Documents/CodeSource/node-projects/winston-logger
$ npm start

> winston-logger@1.0.0 start
> node app.js

2025-04-18T19:46:45.714Z [app.js] INFO: Hello There
2025-04-18T19:46:45.715Z [app.js] INFO: Hello There
2025-04-18T19:46:45.715Z [app.js] INFO: Hello There
2025-04-18T19:46:45.715Z [app.js] INFO: ndustry. Lorem Ipsu
2025-04-18T19:46:45.715Z [app.js] DEBUG: This is the older Node.js syntax used before ES6 modules. Still commonly found in older projects or some Node.js setups.
2025-04-18T19:46:45.715Z [app.js] INFO: Hello There
2025-04-18T19:46:45.715Z [app.js] INFO: ng, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset 
2025-04-18T19:46:45.715Z [app.js] INFO: Hello There
syndicate@syn-debian:~/Documents/CodeSource/node-projects/winston-logger
```