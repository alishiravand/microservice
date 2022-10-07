docker compose up -d

docker run -d -p 9411:9411 openzipkin/zipkin

bin/zookeeper-server-start.sh config/zookeeper.properties
bin/kafka-server-start.sh config/server.properties
bin/kafka-console-consumer.sh --topic ash-topic --from-beginning --bootstrap-server localhost:9092
