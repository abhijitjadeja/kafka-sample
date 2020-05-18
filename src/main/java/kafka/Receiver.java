package kafka;

import static kafka.Utils.*;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;
import org.apache.kafka.clients.consumer.*;

public class Receiver implements Runnable {

  private final Consumer<String, String> consumer;
  private boolean stop = false;
  private final int id;

  public Receiver(int id) {
    Properties properties = new Properties();
    load(properties, "consumer.properties");
    consumer = new KafkaConsumer<String, String>(properties);
    consumer.subscribe(Collections.singletonList(TOPIC));
    this.id = id;
  }

  @Override
  public void run() {
    for (int i = 0; i < 99 || stop; i++) {
      ConsumerRecords<String, String> messages = consumer.poll(Duration.ofSeconds(1));
      messages.forEach(m -> System.out.println(id + "::" + m.value()));
    }
    System.out.println("Receiver "+id+" done");
  }

  public void stop() {
    stop = true;
    consumer.close();
  }
}
