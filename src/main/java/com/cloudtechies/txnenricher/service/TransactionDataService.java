package com.cloudtechies.txnenricher.service;

import com.cloudtechies.txnenricher.exception.UnrecoverableException;
import com.cloudtechies.txnenricher.model.TransactionReport;
import com.cloudtechies.txnenricher.repos.TransactionReportRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class TransactionDataService {

    @Autowired
    TransactionReportRepository transactionReportRepository;

    public List<TransactionReport> getTransactionReportList(List<String> messages) {
        log.debug("In getTransactionReportList");
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        List<TransactionReport> transactionReports = new ArrayList<>();
        for (String message : messages) {
            TransactionReport transactionReport;
            try {
                transactionReport = objectMapper.readValue(message, TransactionReport.class);
            } catch (JsonProcessingException e) {
                log.error("Got error while deserializing message to TransactionReport {}", e.getMessage());
                throw new UnrecoverableException(e.getMessage());
            }
            transactionReports.add(transactionReport);
        }
        log.debug("Out getTransactionReportList");
        return transactionReports;
    }

    public void updateTransactionData(List<TransactionReport> transactionReports) {
        log.debug("In updateTransactionData");
        transactionReportRepository.saveAll(transactionReports);
        log.debug("In updateTransactionData");
    }
}
