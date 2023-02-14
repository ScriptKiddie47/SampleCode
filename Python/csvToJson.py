import os
import csv
import json

stateList = []
responseList = []
with open('stateList.csv', mode='r')as file:
    # reading the CSV file
    csvFile = csv.reader(file)
    # displaying the contents of the CSV file
    for lines in csvFile:
        stateList.append(lines)

if os.path.exists("demofile2.json"):
    os.remove("demofile2.json")
else:
    print("The file does not exist")

f = open("demofile2.json", "a")
for list in stateList:
    stateOb = {"name": list[0], "literal": list[1]}
    responseList.append(stateOb)

print(responseList)
f.write(json.dumps(responseList))
f.close()
