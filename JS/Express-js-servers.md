# Express JS Easy Servers

1. Get the npm package : `npm install express --save`

### Simple Server

```js
var express = require('express');
var app = express();
/*
$ curl  http://localhost:5000
Hello World
*/
app.get('/', function (req, res) {
res.send('Hello World');
})
var server = app.listen(5000, function () {
console.log("Express App running at http://127.0.0.1:5000/");
})
```

### JSON Server

```js
var express = require('express');
var app = express();

app.use(express.json());

/*
curl --location 'localhost:5000' \
--header 'Content-Type: application/json' \
--data '{
    "name" : "Syn"
}'
*/

app.post('/', function (req, res) {
    console.log(` Name: ${req.body.name}`)
    const responseBody = {firstName:req.body.name}
    res.send(responseBody);
})

var server = app.listen(5000, function () {
   console.log("Express App running at http://127.0.0.1:5000/");
})
```

### XML server

1. Install `npm install express body-parser body-parser-xml xmlbuilder`
`

```ps
$ curl --location 'localhost:3000' \
--header 'Content-Type: application/xml' \
--data '<name>Syn</name>'
<?xml version="1.0"?>
<name>Syn</name>
<age>10</age>
```





```js
const express = require("express");
const bodyParser = require("body-parser");
const bodyParserXml = require("body-parser-xml");
const xmlbuilder = require("xmlbuilder");

const app = express();
bodyParserXml(bodyParser);

app.use(
  bodyParser.xml({
    limit: "1MB", // Reject payload bigger than 1 MB
    xmlParseOptions: {
      normalize: true, // Trim whitespace inside text nodes
      normalizeTags: true, // Lowercase tags
      explicitArray: false, // Only put nodes in array if >1
    },
  })
);

// Route to receive and modify XML
app.post("/", (req, res) => {
  const xmlData = req.body;

  //Modify Data
  xmlData.age = 10;
  // Convert the modified data back to XML
  const xmlResponse = xmlbuilder.create(xmlData).end({ pretty: true });

  // Set the content type to XML and send the response
  res.header("Content-Type", "application/xml");
  res.send(xmlResponse);
});

// Start the server
const PORT = 3000;
app.listen(PORT, () => {
  console.log(`Server is running on http://localhost:${PORT}`);
});

```