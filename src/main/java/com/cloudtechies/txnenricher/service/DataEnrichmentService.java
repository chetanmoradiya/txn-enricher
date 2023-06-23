package com.cloudtechies.txnenricher.service;

import com.cloudtechies.txnenricher.enums.TransactionStatus;
import com.cloudtechies.txnenricher.exception.UnrecoverableException;
import com.cloudtechies.txnenricher.model.InstrumentData;
import com.cloudtechies.txnenricher.model.TransactionReport;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class DataEnrichmentService {

    public void enrichTransactionData(List<TransactionReport> transactionReports, Map<String, InstrumentData> instrumentDataMap) {
        log.info("In enrichTransactionData");
        for(TransactionReport transactionReport:transactionReports){
            String securityIdentifier=transactionReport.getSecIdentifier();
            if(instrumentDataMap.containsKey(securityIdentifier)){
                InstrumentData instrumentData=instrumentDataMap.get(securityIdentifier);

                if(transactionReport.getTypeOfAsset()==null || transactionReport.getTypeOfAsset().isBlank()){
                    transactionReport.setTypeOfAsset(instrumentData.getTypeOfAsset());
                }
                if(transactionReport.getClsOfASec()==null || transactionReport.getClsOfASec().isBlank()){
                    transactionReport.setClsOfASec(instrumentData.getClsOfASec());
                }
                if(transactionReport.getLoanBaseProduct()==null || transactionReport.getLoanBaseProduct().isBlank()){
                    transactionReport.setLoanBaseProduct(instrumentData.getLoanBaseProduct());
                }
                if(transactionReport.getLoanSubProduct()==null || transactionReport.getLoanSubProduct().isBlank()){
                    transactionReport.setLoanSubProduct(instrumentData.getLoanSubProduct());
                }
                if(transactionReport.getLoanFurthrSubProd()==null || transactionReport.getLoanFurthrSubProd().isBlank()){
                    transactionReport.setLoanFurthrSubProd(instrumentData.getLoanFurthrSubProd());
                }
                if(transactionReport.getLoanLeiOfIssuer()==null || transactionReport.getLoanLeiOfIssuer().isBlank()){
                    transactionReport.setLoanLeiOfIssuer(instrumentData.getLoanLeiOfIssuer());
                }
                if(transactionReport.getLoanMaturityOfSecurity()==null || transactionReport.getLoanMaturityOfSecurity().isBlank()){
                    transactionReport.setLoanMaturityOfSecurity(instrumentData.getLoanMaturityOfSecurity());
                }
                if(transactionReport.getLoanJurisOfIssuer()==null || transactionReport.getLoanJurisOfIssuer().isBlank()) {
                    transactionReport.setLoanJurisOfIssuer(instrumentData.getLoanJurisOfIssuer());
                }
                transactionReport.setTxnStatus(TransactionStatus.ACPT);
                transactionReport.setUpdateTs(Instant.now());
            }else{
                String error="securityIdentifier does not exist in instrument data "+ securityIdentifier;
                log.error(error);
                throw new UnrecoverableException(error);
            }
        }
        log.info("Out enrichTransactionData");
    }
}
