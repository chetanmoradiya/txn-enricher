package com.cloudtechies.txnenricher.kafka;

import com.cloudtechies.txnenricher.config.TransactionEnricherProperties;
import com.cloudtechies.txnenricher.service.InstrumentDataService;
import com.cloudtechies.txnenricher.service.DataEnrichmentService;
import com.cloudtechies.txnenricher.model.InstrumentData;
import com.cloudtechies.txnenricher.model.TransactionReport;
import com.cloudtechies.txnenricher.service.TransactionDataService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class EnrichDataTopicListener
{

    @Autowired
    TransactionEnricherProperties transactionEnricherProperties;
    @Autowired
    TransactionDataService transactionDataService;
    @Autowired
    InstrumentDataService instrumentDataService;
    @Autowired
    DataEnrichmentService dataEnrichmentService;
    @Autowired
    KafkaOutputAdapter kafkaOutputAdapter;

    @KafkaListener(topics = "#{transactionEnricherProperties.kafkaEnrichTxnOutputTopic}",
                   groupId = "#{transactionEnricherProperties.kafkaConsumerGroupName}",
                   concurrency = "1")
    public void handleTxnInputEvent(@Payload List<String> messages){
        log.info("message received:{}",messages.size());

        List<TransactionReport> transactionReports= transactionDataService.getTransactionReportList(messages);
        Map<String, InstrumentData> instrumentDataMap= instrumentDataService.getInstrumentData(transactionReports);
        dataEnrichmentService.enrichTransactionData(transactionReports,instrumentDataMap);
        transactionDataService.updateTransactionData(transactionReports);
        kafkaOutputAdapter.sendMsgToKafka(transactionReports);
        log.info("Out handleTxnInputEvent");
    }
}
