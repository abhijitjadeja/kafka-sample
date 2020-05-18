package kafka;

import java.io.IOException;
import java.util.Properties;

public abstract class Utils {
  static final String TOPIC="kafka-client-test-topic";
  static void load(Properties properties, String name) {
    try {
      properties.load(Sender.class.getResourceAsStream("/" + name));
    } catch (IOException ioe) {
      throw new RuntimeException(ioe);
    }
  }
}
