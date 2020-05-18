package kafka;

import static kafka.Utils.*;
public class App {

 
  public static void main(String[] args) {
    MessageAdmin admin = new MessageAdmin();
    if (!admin.topicExists(TOPIC)){
        admin.createTopic(TOPIC);
    }
    admin.close();
    Sender s = new Sender();
    Thread t = new Thread(s);
    t.start();
    Receiver[] receivers = new Receiver[10];
    for (int i = 0; i < 10; i++) {
      receivers[i] = new Receiver(i + 1);
      new Thread(receivers[i]).start();
    }
    try {
      Thread.sleep(100000);
    } catch (Exception e) {
    }
    for (int i = 0; i < 10; i++) {
      receivers[i].stop();
    }
    s.stop();
    try {
      Thread.sleep(1000);
    } catch (Exception e) {
    }
  }
}
