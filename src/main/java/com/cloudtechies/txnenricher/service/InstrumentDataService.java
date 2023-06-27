package com.cloudtechies.txnenricher.service;

import com.cloudtechies.txnenricher.model.InstrumentData;
import com.cloudtechies.txnenricher.model.TransactionReport;
import com.cloudtechies.txnenricher.repos.InstrumentDataRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@Slf4j
public class InstrumentDataService {

    @Autowired
    InstrumentDataRepository instrumentDataRepository;

    public Map<String,InstrumentData> getInstrumentData(List<TransactionReport> transactionReports) {
        log.debug("In getInstrumentData");
        List<String> securityIdentifier = transactionReports.stream().map(i -> i.getSecIdentifier()).distinct().collect(
                Collectors.toList());
        List<InstrumentData> instrumentData=instrumentDataRepository.findBySecIdentifierIn(securityIdentifier);

        Map<String, InstrumentData> instrumentDataMap = instrumentData.stream()
                .collect(Collectors.toMap(InstrumentData::getSecIdentifier, Function.identity()));

        log.debug("Out getInstrumentData {}", instrumentDataMap.size());
        return instrumentDataMap;
    }
}
