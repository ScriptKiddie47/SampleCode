# Kakfa Setup

1. QuickStart Guide : https://kafka.apache.org/quickstart
1. Download : https://kafka.apache.org/downloads -> Download the binary image.
1. Extract to a folder.

1. Start the ZooKeeper service ✔
   - `bin/zookeeper-server-start.sh config/zookeeper.properties`
1. Start the Kafka broker service ✔ 
   - `bin/kafka-server-start.sh config/server.properties`
1. Create a topic ( Folder in a filesystem ) ✔ 
   - `bin/kafka-topics.sh --create --topic quickstart-events --bootstrap-server localhost:9092`
   - `quickstart-events` -> name of the topic
1. Display usage information of the topics
   - `bin/kafka-topics.sh --describe --topic quickstart-events --bootstrap-server localhost:9092`
1. Write some events in the topic
   - A Kafka client communicates with the Kafka brokers via the network for writing (or reading) events
   - Once received, the brokers will store the events in a durable and fault-tolerant manner for as long as you need—even forever.
   - Run the console producer client to write a few events into your topic.
   - By default, each line you enter will result in a separate event being written to the topic.
   - `bin/kafka-console-producer.sh --topic quickstart-events --bootstrap-server localhost:9092`
   - You can stop the producer client with Ctrl-C at any time
1. READ THE EVENTS
   - Open another terminal session and run the console consumer client to read the events you just created
   - `bin/kafka-console-consumer.sh --topic quickstart-events --from-beginning --bootstrap-server localhost:9092`
   - You can stop the consumer client with Ctrl-C at any time.
