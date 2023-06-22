package com.cloudtechies.txnenricher.kafka;

import com.cloudtechies.txnenricher.config.TransactionEnricherProperties;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.test.context.EmbeddedKafka;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@EmbeddedKafka(partitions = 1, brokerProperties = { "listeners=PLAINTEXT://localhost:9092", "port=9092" })
class EnrichTransactionDataTopicListenerTest
{
	@Autowired
	KafkaProducer kafkaProducer;

	@Autowired
	TransactionEnricherProperties transactionEnricherProperties;

	@Test
	void test_kafka()
	{
		kafkaProducer.send(transactionEnricherProperties.getKafkaEnrichTxnOutputTopic(),"message1",new HashMap<>());
	}
}