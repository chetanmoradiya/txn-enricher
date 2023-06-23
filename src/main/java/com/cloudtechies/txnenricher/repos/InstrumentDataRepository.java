package com.cloudtechies.txnenricher.repos;

import com.cloudtechies.txnenricher.model.InstrumentData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

public interface InstrumentDataRepository extends JpaRepository<InstrumentData, String> {

    List<InstrumentData> findBySecIdentifierIn(@Param("secIdentifier") List<String> secIdentifier);
}
