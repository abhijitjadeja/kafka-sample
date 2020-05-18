package kafka;

import org.apache.kafka.clients.admin.Admin;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.CreateTopicsResult;
import org.apache.kafka.clients.admin.NewTopic;

import static kafka.Utils.*;

import java.util.Collections;
import java.util.Properties;
import java.util.concurrent.ExecutionException;

public class MessageAdmin {
    private final Admin adminClient;

    public MessageAdmin() {
        Properties properties = new Properties();
        load(properties, "admin.properties");
        adminClient = AdminClient.create(properties);
    }

    public boolean topicExists(String topicName) {
        try {
            return adminClient.listTopics().names().get().contains(topicName);
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    public void createTopic(String topicName) {
        final NewTopic newTopic = new NewTopic(topicName, 40, (short) 1);
        final CreateTopicsResult result = adminClient.createTopics(Collections.singleton(newTopic));
        try {
            result.values().get(topicName).get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    public void close(){
        adminClient.close();
    }
}