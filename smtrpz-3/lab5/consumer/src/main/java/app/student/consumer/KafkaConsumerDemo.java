package app.student.consumer;
import java.util.Properties;

import java.util.Arrays;

import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.common.serialization.LongDeserializer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.kafka.annotation.EnableKafka;

@EnableKafka
public class KafkaConsumerDemo {
    private final static String TOPIC_CREATED = "create.entity";
    private final static String TOPIC_UPDATED = "update.entity";
    private final static String BOOTSTRAP_SERVERS = "kafka:9092";

    private static Consumer<String, String> createConsumer() {
        final Properties props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "KafkaConsumer");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, LongDeserializer.class.getName());
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());

        final Consumer<String, String> consumer =
                new KafkaConsumer<>(props);

        consumer.subscribe(Arrays.asList(TOPIC_CREATED, TOPIC_UPDATED));
        return consumer;
    }
    public static void runConsumer() {

        final Consumer<String, String> consumer = createConsumer();
        try {
            while (true) {

                ConsumerRecords<String, String> records = consumer.poll(100);

                for (ConsumerRecord<String, String> record : records) {
                    System.out.printf("Consumer Record:(%s, %s, %d, %d)\n",
                            record.key(), record.value(),
                            record.partition(), record.offset());
                }
            }
        } finally {
            consumer.close();
        }
    }
}
