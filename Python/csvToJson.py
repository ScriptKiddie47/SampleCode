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


# AL,AK,AZ,AR,CA,CO,CT,DE,DC,FL,GA,HI,ID,IL,IN,IA,KS,KY,LA,ME,MT,NE,NV,NH,NJ,NM,NY,NC,ND,OH,OK,OR,MD,MA,MI,MN,MS,MO,PA,RI,SC,SD,TN,TX,UT,VT,VA,WA,WV,WI,WY
