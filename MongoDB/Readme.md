### Mongo DB

JSON Object  -->  Document
	mongo is the command line shell that connects to a specific instance of 'mongod'
	mongod is the mongo deamon that hosts the DB

Types of install :
1. Community Server
2. Docker

Mongo GUI Client

1. Mongo Express
2. Mongo Compass
3. DataGrip

Mongo stores documents ( rows ) in collections ( table )
Mongo stores records as BSON documents. BSON -> Binary representation of JSON



Mongo Shell - ( Node JS REPL environment ) 
	Lets first connect to the docker container & then the mongo instance
		PS D:\Workspaces\docker_mongo> docker exec -it 8fa88f5bae3b bash  `8fa88f5bae3b is taken from docker ps`
	We see something like this : 
		root@8fa88f5bae3b:/#
		root@8fa88f5bae3b:/# mongosh mongodb://localhost:27017 -u root -p example
		
		test> show dbs; ( Mongo Command,now we see default database )
		
			admin   100.00 KiB
			config   60.00 KiB
			local    72.00 KiB
		
		Switch to a  DB :
		
			test> use sample-db-1
			switched to db sample-db-1
			sample-db-1>
		
		Minor Commands:
		
		`sample-db-1> db.getName();` --> `sample-db-1`
		`sample-db-1> db.createCollection('Hello');` --> `{ ok: 1 }`
		`sample-db-1> db.dropDatabase();` --> `{ ok: 1, dropped: 'sample-db-1' }`
		`sample-db-1> db.person.drop();` --> `true` drop collection
		`sample-db-1> db.person.stats();` --> See all the stats configured for the collection
		`sample-db-1> db.student.countDocuments()` --> Count number of documents
		`sample-db-1> db.student.insertOne(student);`--> Insert document into collections
		
		`sample-db-1> db.student.find()` --> Show all documents
		`sample-db-1> db.student.find().pretty()` --> Pretty print
		
		`sample-db-1> db.student.insertMany(students)` -- > Insert Many
		
		`sample-db-1> db.student.find({firstName : 'John5'})` --> Sends us a document where firstName is John5
		`sample-db-1> db.student.find({firstName : 'John'},{firstName : 1 })` --> [ { _id: ObjectId("638cb50df9132d1b8637e94a"), firstName: 'John' } ] --> We only see the firstName being returned
		
		`sample-db-1> db.student.find({firstName : 'John'},{isStudentActive : 1,totalSpentInBooks : 1 })` -->
		We get both the properties
		
		`sample-db-1> db.student.find({firstName : 'John'},{isStudentActive : 0,totalSpentInBooks : 0 })` --> 2 fields are excluded and rest of the document is returned

		`sample-db-1> db.student.updateOne({ _id : ObjectId("638cbe81f9132d1b8637e94f") }, {$set:{firstName : 'Maria' }} )` --> Update name
		
		`sample-db-1> db.student.update({ _id : ObjectId("638cbe81f9132d1b8637e94f") }, { $inc : {totalSpentInBooks : 88}})` --> Increment by 88
		
		`sample-db-1> db.student.update({ _id : ObjectId("638cbe81f9132d1b8637e94f") }, { $pull : { favoriteSubjects : 'IT' }}) ` --> remove value from array
		
		`sample-db-1> db.student.update({ _id : ObjectId("638cbe81f9132d1b8637e94f") }, { $push : { favoriteSubjects : 'IT' }})`-- > add value
		
		
		`sample-db-1> db.student.deleteOne( { _id: ObjectId("638cb50df9132d1b8637e94a") } )` --> Delete the Object
		
		`db.zips.find({ city: { $in: ["PHOENIX", "CHICAGO"] } })` - returns based on array match.
		`db.transactions.find({ transaction_count :  { $gt: 99 } },{account_id:1,transaction_count:1})` --> Greater than
		`db.transactions.find({ transaction_count :  { $lt: 2 } },{account_id:1,transaction_count:1})` --> Less than
		`$lte` --> less than
		`$gte` -- > greather than
		
		Query Array 
		-> `db.accounts.find({products:"InvestmentStock"})` --> All products field in Array or scalar
		-> `db.accounts.find({products : { $elemMatch:{ $eq :"InvestmentStock"}}})` --> Only arrays returned
		
		============================================================================================
		
		Replace -->
		db.grades.replaceOne({ _id:ObjectId("63a2e5617034eb0eb2b460b5")},{"student_id": 999999})
		
		
		Update -->
		`db.grades.updateOne({ _id: ObjectId("63aad9ea1a298608d82b305b")}, { $set:{student_id: 1002}})`
		
		Upsert ( Insert a document if matching doc doesn't exist ) -->
		`db.grades.updateOne({ student_id : 1003 }, { $set:{subjects: ["Maths","Sanskrit"]}}, { upsert: true })`
		
		Update an array -->		
		`db.grades.updateOne({ _id : ObjectId("63aadebf798db5eda30fe606") }, { $push : { subjects: "Geography"}})`
		
		Update & Return updated document -->
		`db.grades.findAndModify( {query : { _id : ObjectId("63aad9ea1a298608d82b305b") }, update : { $inc : { NoOfAwards: 1}} , new : true })`
		
		UpdateMany -->
		
		Delete Documents -->
		
		`db.podcasts.deleteOne({ _id: Objectid("6282c9862acb966e76bbf20a") })`
		`db.podcasts.deleteMany({category: “crime”})
`
		============================================================================================
		
		
		Modifying Query Results
		
		Sort title in ascending order --> 
		`db.movies.find( { rated : "UNRATED" } , { title : 1 , rated : 1} ).sort( { title : 1 } )`
		
		Sort title in desending order --> 
		`db.movies.find( { rated : "UNRATED" } , { title : 1 } ).sort( { title : -1 } )`
		
		Limit number of Responses --> 
		`db.movies.find( { rated : "UNRATED" } , { title : 1 } ).sort( { title : -1 } ).limit(3)`
		
		Projection -->
		
		( _id is included by default but can be suppressed ) 
		`db.movies.find( { rated : "UNRATED" } , { title : 1, rated : 1 , _id : 0 } ).sort( { title : -1 } ).limit(3)` 
		title & rated is visible , _id is not visible
		
		
		db.movies.find( { genres : { $in : ["Drama","Romance"] } } , { _id : 0 , "tomatoes.viewer" : 0}  ).limit(10)
		/*  "" -> for an object inside object */ 
		
		============================================================================================
		
		Count Documents -->
		`db.movies.countDocuments( { rated : "UNRATED" })`
		
		============================================================================================
		
		Index -->
		
		`db.customers.createIndex({ birthdate : 1 });` - Index Created with Sort - Order
		`db.customers.createIndex ( { email:1 },{ unique : true } )` - Index created where there is a contrainst unique mentioned
		`db.customers.getIndexes();` - Prints number of indexes
		`db.customers.explain().find({ birthdate: { $gt:ISODate("1996-08-01")}} , { username : 1 })` - Explain()
		
		============================================================================================
		
		
Docker Commands :

	PS C:\Users\Syn> docker --version
	Docker version 20.10.21, build baeda1f

	Create Docker Containers: 

		$ docker-compose -f docker-compose.yaml up
		$ docker compose -f docker-compose.yaml up
	
	Boots up the containers, lets now remove this
		
		$ docker compose down
	
	Run in Detached mode : 
	
		$ docker compose up -d
		
	Once we open http://localhost:8081/ , we can see the DB's up & running !
	
	Now , to remove the DB's we can just do `$ docker compose down` -> This will remove everything but 
	we can also do `$ docker compose stop`. Post that we can just do `$ docker compose start`
	
	
	$ docker ps -- > Number of instances
	
----------------------------------------------------------------------------------------------------------

Mongo Atlas

Email : sh.bala12@gmail.com
Password : F%IlgH7o0i8W

Cluster name : scriptkiddie-cluster
username : noobuser
password : ZBMEeIMfN0EgVpqq


----------------------------------------------------------------------------------------------------------

Mongo DB Trainings:
https://learn.mongodb.com/learning-paths/introduction-to-mongodb

DB - library
Collections - books,magazines


mongodb+srv://noobuser:<password>@scriptkiddie-cluster.bc109kt.mongodb.net/?retryWrites=true&w=majority

Shell :  mongosh "mongodb+srv://scriptkiddie-cluster.bc109kt.mongodb.net/myFirstDatabase" --apiVersion 1 --username noobuser


Errors : 
MongoServerError: bad auth : Authentication failed solution : 
	Solution : Check that your username and password are spelled correctly in your c	onnection string.

MongoServerSelectionError: connection <monitor> to 34.239.188.169:27017 closed
	Solution : This is a network access error. You need to check the Network Access panel to ensure that your desired IP address is on the allowlist. If not, you may experience a connection timeout.
	
	
----------------------------------------------------------------------------------------------------------

NODE JS SOURCE CODE

const { MongoClient, ServerApiVersion } = require('mongodb');
const uri = "mongodb+srv://noobuser:<password>@scriptkiddie-cluster.bc109kt.mongodb.net/?retryWrites=true&w=majority";
const client = new MongoClient(uri, { useNewUrlParser: true, useUnifiedTopology: true, serverApi: ServerApiVersion.v1 });
client.connect(err => {
  const collection = client.db("test").collection("devices");
  // perform actions on the collection object
  client.close();
});
	