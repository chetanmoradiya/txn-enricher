package com.cloudtechies.txnenricher.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.*;

import java.util.HashMap;
import java.util.Map;

@EnableKafka
@Configuration
@Slf4j
public class kafkaConfig {

    @Autowired
    private TransactionEnricherProperties transactionEnricherProperties;

    @Bean
    public ConsumerFactory<String,String> consumerFactory(){
        log.info("Creating consumer factory");
        Map<String,Object> props=new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, transactionEnricherProperties.getKafkaClusterURL());
        props.put(ConsumerConfig.GROUP_ID_CONFIG, transactionEnricherProperties.getKafkaConsumerGroupName());
        props.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, transactionEnricherProperties.getMaxPollSize());
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,StringDeserializer.class);
        return new DefaultKafkaConsumerFactory<>(props);
    }


    @Bean
    public ConcurrentKafkaListenerContainerFactory<String,String> kafkaListenerContainerFactory(){
        log.info("Creating consumer listener factory");
        ConcurrentKafkaListenerContainerFactory<String,String> factory=new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        factory.setBatchListener(Boolean.TRUE);
        return factory;
    }

    @Bean
    public ProducerFactory<String,String> producerFactory(){
        log.info("Creating producer factory");
        Map<String,Object> props=new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, transactionEnricherProperties.getKafkaClusterURL());
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        return new DefaultKafkaProducerFactory<>(props);
    }

    @Bean
    public KafkaTemplate<String, String> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }

//    @Bean
//    public NewTopic txnDataInput(){
//        return TopicBuilder.name(transactionEnricherProperties.getKafkaEnrichTxnInputTopic()).build();
//    }
}