# kafka-sample
A sample kafka that has one producer, one consumer group and multiple consumers in that consumer group. This sample creates a kafka-client-test-topic topic with 40 partitions created on kafka.

Following kafka steps should be used to setup the system:
- bin/zookeeper-server-start.sh config/zookeeper.properties
- bin/kafka-server-start.sh config/server.properties
Run App.java after starting up server on port 9092.
The properties are included in resources folder.

