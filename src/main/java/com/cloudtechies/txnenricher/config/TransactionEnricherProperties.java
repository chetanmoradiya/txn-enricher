package com.cloudtechies.txnenricher.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Data
@Component
public class TransactionEnricherProperties
{

    @Value("${txn.enricher.kafkaClusterURL}")
    private String kafkaClusterURL;

    @Value("${txn.enricher.kafkaEnrichTxnInputTopic}")
    private String kafkaEnrichTxnInputTopic;

    @Value("${txn.enricher.kafkaEnrichTxnOutputTopic}")
    private String kafkaEnrichTxnOutputTopic;

    @Value("${txn.enricher.restRetryCount}")
    private Integer restRetryCount;

    @Value("${txn.enricher.restBackoffTime}")
    private Integer restBackoffTime;

    @Value("${txn.enricher.maxPollSize}")
    private Integer maxPollSize;

    @Value("${txn.enricher.kafkaConsumerGroupName}")
    private String kafkaConsumerGroupName;


}
