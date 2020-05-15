package kafka;

import java.io.IOException;
import java.util.Properties;
import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.clients.producer.*;

public class Sender implements Runnable {

  private static final String[] messages = {
    "one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten"
  };

  private final Producer<String, String> producer;
  private boolean stop = false;

  public Sender() {
    Properties properties = new Properties();
    load(properties, "producer.properties");
    producer = new KafkaProducer<String, String>(properties);
  }

  static void load(Properties properties, String name) {
    try {
      properties.load(Sender.class.getResourceAsStream("/" + name));
    } catch (IOException ioe) {
      throw new RuntimeException(ioe);
    }
  }

  @Override
  public void run() {
    int i = 0;
    while (!stop || i < 100) {
      producer.send(
          new ProducerRecord<String, String>("test", Integer.toString(i), messages[i % 10]));
      try {
        Thread.sleep(200);
      } catch (Exception e) {
      }
      i++;
    }
  }

  public void stop() {
    stop = true;
    producer.close();
  }
}
