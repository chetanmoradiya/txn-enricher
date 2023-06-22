package com.cloudtechies.txnenricher.repos;

import com.cloudtechies.txnenricher.model.InstrumentData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

public interface InstrumentDataRepository extends JpaRepository<InstrumentData, String> {

	@Query(value="select distinct sec_identifier, * from instrument_data where sec_identifier IN :securityIdentifier", nativeQuery = true)
    Map<String,InstrumentData> findInstrumentDataBySecurityIdentifier(@Param("securityIdentifier") List<String> securityIdentifier);
}
