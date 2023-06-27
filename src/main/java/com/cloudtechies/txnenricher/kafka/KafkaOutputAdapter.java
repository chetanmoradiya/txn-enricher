package com.cloudtechies.txnenricher.kafka;

import com.cloudtechies.txnenricher.config.TransactionEnricherProperties;
import com.cloudtechies.txnenricher.exception.UnrecoverableException;
import com.cloudtechies.txnenricher.model.TransactionReport;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

@Component
@Slf4j
public class KafkaOutputAdapter {

    @Autowired
    KafkaProducer kafkaProducer;

    @Autowired
    TransactionEnricherProperties transactionEnricherProperties;


    public void sendMsgToKafka(List<TransactionReport> transactionReports) {
        log.debug("In sendMsgToKafka");
        ObjectMapper objectMapper=new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        for(TransactionReport transactionReport:transactionReports){
            String msg=getSerializeMessage(transactionReport,objectMapper);
            kafkaProducer.send(transactionEnricherProperties.getKafkaEnrichTxnOutputTopic(),msg,new HashMap<>());
        }
        log.debug("Out sendMsgToKafka");
    }

    private String getSerializeMessage(TransactionReport transactionReport, ObjectMapper objectMapper) {
        try {
            return objectMapper.writeValueAsString(transactionReport);
        } catch (JsonProcessingException e) {
            log.error("Got error while serializing  message to TransactionReport{}",e.getMessage());
            throw new UnrecoverableException(e.getMessage());
        }
    }
}
