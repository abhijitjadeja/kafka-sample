package kafka;

import java.util.*;
import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.clients.producer.*;

public class App {

  public static void main(String[] args) {
    Thread t = new Thread(new Sender());
    t.start();
    Thread[] receivers = new Thread[10];
    for (int i = 0; i < 10; i++) {
      receivers[i] = new Thread(new Thread(new Receiver(i + 1)));
      receivers[i].start();
    }
    try {
      Thread.sleep(100000);
    } catch (Exception e) {
    }
    for (int i = 0; i < 10; i++) {
      receivers[i].stop();
    }
    t.stop();
    try {
      Thread.sleep(1000);
    } catch (Exception e) {
    }
  }
}
