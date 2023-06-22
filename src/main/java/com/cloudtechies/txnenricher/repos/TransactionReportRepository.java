package com.cloudtechies.txnenricher.repos;

import com.cloudtechies.txnenricher.model.TransactionReport;
import com.cloudtechies.txnenricher.model.TransactionReportPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface TransactionReportRepository extends JpaRepository<TransactionReport, TransactionReportPK> {

    @Query(value="select * from transaction_report where txn_status='PREENRICHVALID' "
	        + "order by create_ts limit 1000", nativeQuery = true)
    List<TransactionReport> findPreEnrichValidTransactions();

	@Modifying
	@Query(value="update transaction_report set txn_status='ACPT' "
			+"where txn_report_id IN :transactionReportIds",nativeQuery = true)
	Integer updateTransactionStatus(List<UUID> transactionReportIds);
}
