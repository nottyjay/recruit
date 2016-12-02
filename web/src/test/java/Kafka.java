import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.junit.Test;

import java.util.Properties;

/**
 * Created by jileilei on 2016/12/1.
 */
public class Kafka {

    @Test
    public void send(){
        Properties properties = new Properties();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "192.168.2.96:9092");
        properties.put(ProducerConfig.ACKS_CONFIG, "all");
        properties.put(ProducerConfig.RETRIES_CONFIG, 0);
        properties.put(ProducerConfig.BATCH_SIZE_CONFIG, 16384);
        properties.put(ProducerConfig.LINGER_MS_CONFIG, 1);
        properties.put(ProducerConfig.BUFFER_MEMORY_CONFIG, 33554432);
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");

        Producer<String, String> producer = new KafkaProducer<>(properties);
        producer.send(new ProducerRecord<String, String>("lagou", "job", "{\"url\":\"https://www.lagou.com/jobs/2564360.html\",\"name\":\"Node.js开发工程师\",\"city\":\"上海\",\"salary\":\"15k-25k\",\"createTime\":\"2016-12-01 19:28:58\",\"company\":{\"id\":56741,\"name\":\"红星美凯龙家居集团股份有限公司\",\"shortname\":\"红星美凯龙互联网集团\",\"advantage\":\"免费食堂 带薪年假 年终奖 上市集团 大牛多\",\"financeStage\":\"上市公司\"}}"));
        producer.flush();
        producer.close();
    }
}
