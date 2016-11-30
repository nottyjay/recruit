package com.d3code.recruit.kafka;

import com.d3code.recruit.kafka.config.KafkaConfig;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.Properties;

/**
 * Created by Nottyjay on 2016/11/22 0022.
 */
@Component("kafkaProducer")
public class Producer{

    private org.apache.kafka.clients.producer.Producer producer;
    @Autowired
    private KafkaConfig config;

    @PostConstruct
    public void init(){
        Properties properties = new Properties();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, config.getBootstrapServers());
        properties.put(ProducerConfig.ACKS_CONFIG, config.getAcks());
        properties.put(ProducerConfig.RETRIES_CONFIG, config.getRetries());
        properties.put(ProducerConfig.BATCH_SIZE_CONFIG, config.getBatchSize());
        properties.put(ProducerConfig.LINGER_MS_CONFIG, config.getLingerMs());
        properties.put(ProducerConfig.BUFFER_MEMORY_CONFIG, config.getBufferMemory());
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, config.getKeySerializer());
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, config.getValueSerializer());

        producer = new KafkaProducer<>(properties);
    }

    public void sendMessage(String topic, String key, Object value){
        producer.send(new ProducerRecord<>(topic, key, value));
    }

    @PreDestroy
    public void close(){
        producer.close();
    }
}
