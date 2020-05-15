# kafka-sample
A sample kafka that has one producer, one consumer group and multiple consumers in that consumer group. This sample assumes a test topic with 40 partitions created on kafka.

Following kafka steps should be used to setup the system:
- bin/zookeeper-server-start.sh config/zookeeper.properties
- bin/kafka-server-start.sh config/server.properties
- bin/kafka-topics.sh --create --bootstrap-server localhost:9092 --replication-factor 1 --partitions 40 --topic test

