const fs = require("fs");
var csvfile = fs.readFileSync("sample_data.csv");

function csvJSON(csv) {
    const result = []
    const lines = csv.split(/\r\n|\n/);
    //console.log("lines", lines);
    // "Month", "P1Sale", "P2Sale", "P3Sale"
    for (var i = 1; i < lines.length; i++) {
        //console.log(`Lines - ${i} - Value : ${lines[i]}`);
        let currentLine = lines[i].split(",");
        let obj = {}
        for (var j = 1; j < 4; j++) {
           // console.log(`Current Line - ${i} - Value : ${currentLine[j]}`);
            obj = {}
            obj.Month = currentLine[0].trim().replace(/["]+/g, '');
            obj.P1Sale = currentLine[1].trim();;
            obj.P2Sale = currentLine[2].trim();
            obj.P3Sale = currentLine[3].trim();
        }
        result.push(obj)
    }
    return result;
}
let resultObj = csvJSON(csvfile.toString());
//console.log(JSON.stringify(resultObj));
let responseList = []
resultObj.forEach( e => {
    if(e.P3Sale === '432'){
        responseList.push({ month : e.Month});
    }
})
console.log(JSON.stringify(responseList));
