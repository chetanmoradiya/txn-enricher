package com.cloudtechies.txnenricher.model;

import com.cloudtechies.txnenricher.enums.TransactionStatus;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.vladmihalcea.hibernate.type.json.JsonType;
import lombok.*;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name="transaction_report")
@IdClass(TransactionReportPK.class)
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TypeDef(name="json", typeClass = JsonType.class)
public class TransactionReport {

    @Id
    @Column(name="txn_report_id",columnDefinition = "UUID")
    @JsonProperty("TRANSACTION REPORT ID")
    private UUID transactionReportId;

    @Column(name="payload_id",columnDefinition = "UUID")
    @JsonProperty("PAYLOAD ID")
    private UUID payloadId;

    @Column(name="create_ts")
    @Id
    @JsonProperty("CREATE TIMESTAMP")
    private Instant createTs;

    @Column(name="update_ts")
    @JsonProperty("UPDATE TIMESTAMP")
    private Instant updateTs;

    @Enumerated(EnumType.STRING)
    @Column(name="txn_status")
    @JsonProperty("TRANSACTION STATUS")
    private TransactionStatus txnStatus;

    @Type(type="json")
    @Column(name="rjct_reason")
    @JsonProperty("REJECTED REASONS")
    private List<String> rjctReasons;

    @Column(name="trn_id")
    @JsonProperty("TRANSACTION ID")
    private String trnId;

    @Column(name="cntrct_type")
    @JsonProperty("CONTRACT TYPE")
    private String contractType;

    @Column(name="action_type")
    @JsonProperty("ACTION TYPE")
    private String actionType;

    @Column(name="uti")
    @JsonProperty("UTI")
    private String uti;

    @Column(name="level")
    @JsonProperty("LEVEL")
    private String level;

    @Column(name="rep_ctrpty_cd")
    @JsonProperty("REPORTING COUNTERPARTY CODE")
    private String repCtrPtyCd;

    @Column(name="rep_ctrpty_fin_sts")
    @JsonProperty("REPORTING COUNTERPARTY FINANCIAL STATUS")
    private String repCtrPtyFinSts;

    @Column(name="rep_ctrpty_sec")
    @JsonProperty("REPORTING COUNTERPARTY SECTOR")
    private String repCtrPtySec;

    @Column(name="non_rep_ctrpty_cd")
    @JsonProperty("NON-REPORTING COUNTERPARTY CODE")
    private String nonRepCtrPtyCd;

    @Column(name="non_rep_ctrpty_fin_sts")
    @JsonProperty("NON-REPORTING COUNTERPARTY FINANCIAL STATUS")
    private String nonRepCtrPtyFinSts;

    @Column(name="non_rep_ctrpty_sec")
    @JsonProperty("NON-REPORTING COUNTERPARTY SECTOR")
    private String nonRepCtrPtySec;

    @Column(name="ctrpty_side")
    @JsonProperty("COUNTERPARTY SIDE")
    private String ctrPtySide;

    @Column(name="event_date")
    @JsonProperty("EVENT DATE")
    private String eventDate;

    @Column(name="trading_venue")
    @JsonProperty("TRADING VENUE")
    private String tradingVenue;

    @Column(name="mstr_agreement_typ")
    @JsonProperty("MASTER AGREEMENT TYPE")
    private String mstrAgreementType;

    @Column(name="value_dt")
    @JsonProperty("VALUE DATE")
    private String valueDt;

    @Column(name="gen_coll_ind")
    @JsonProperty("GENERAL COLLATERAL INDICATOR")
    private String genCollInd;

    @Column(name="typ_of_asset")
    @JsonProperty("TYPE OF ASSET")
    private String typeOfAsset;

    @Column(name="sec_identifier")
    @JsonProperty("SECURITY IDENTIFIER")
    private String secIdentifier;

    @Column(name="class_of_a_sec")
    @JsonProperty("CLASSIFICATION OF A SECURITY")
    private String clsOfASec;

    @Column(name="loan_base_prod")
    @JsonProperty("LOAN BASE PRODUCT")
    private String loanBaseProduct;

    @Column(name="loan_sub_prod")
    @JsonProperty("LOAN SUB PRODUCT")
    private String loanSubProduct;

    @Column(name="loan_fur_sub_prod")
    @JsonProperty("LOAN FURTHER SUB PRODUCT")
    private String loanFurthrSubProd;

    @Column(name="loan_lei_of_issuer")
    @JsonProperty("LOAN LEI OF THE ISSUER")
    private String loanLeiOfIssuer;

    @Column(name="loan_maturity_of_secu")
    @JsonProperty("LOAN MATURITY OF THE SECURITY")
    private String loanMaturityOfSecurity;

    @Column(name="loan_juris_of_issuer")
    @JsonProperty("LOAN JURISDICTION OF THE ISSUER")
    private String loanJurisOfIssuer;
}
