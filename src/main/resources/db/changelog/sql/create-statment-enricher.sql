CREATE TABLE if not exists instrument_data(
    sec_identifier VARCHAR (50) NOT NULL,
    typ_of_asset VARCHAR (50),
    class_of_a_sec VARCHAR(50),
    loan_base_prod VARCHAR(50),
    loan_sub_prod VARCHAR (50),
    loan_fur_sub_prod VARCHAR (50),
    loan_lei_of_issuer VARCHAR (50),
    loan_maturity_of_secu VARCHAR (50),
    loan_juris_of_issuer VARCHAR (50),
    constraint sec_identifier_pk primary key (sec_identifier)
);

CREATE TABLE if not exists transaction_report(
 	txn_report_id UUID not null,
	payload_id UUID not null,
	create_ts timestamp not null,
	update_ts timestamp,
    txn_status varchar(20),
    rjct_reason jsonb,
  	trn_id varchar(100),
	cntrct_type varchar(100),
	action_type varchar(100),
	uti varchar(100),
	level varchar(100),
    rep_ctrpty_cd varchar(20),
 	rep_ctrpty_fin_sts varchar(100),
	rep_ctrpty_sec varchar(100),
    non_rep_ctrpty_cd varchar(20),
    non_rep_ctrpty_fin_sts varchar(100),
    non_rep_ctrpty_sec varchar(100),
    ctrpty_side varchar(100),
    event_date varchar(10),
    trading_venue varchar(100),
    mstr_agreement_typ varchar(100),
    value_dt varchar(10),
    gen_coll_ind varchar(100),
    typ_of_asset varchar(100),
    sec_identifier varchar(12),
    class_of_a_sec varchar(100),
    loan_base_prod varchar(100),
    loan_sub_prod varchar(100),
    loan_fur_sub_prod varchar(100),
    loan_lei_of_issuer varchar(100),
    loan_maturity_of_secu varchar(10),
    loan_juris_of_issuer varchar(100),
    constraint transaction_report_pk primary key (txn_report_id, create_ts)
);



COPY instrument_data(typ_of_asset, sec_identifier, class_of_a_sec, loan_base_prod, loan_sub_prod, loan_fur_sub_prod, loan_lei_of_issuer, loan_maturity_of_secu, loan_juris_of_issuer)
FROM '/anand/temp/mnt/instruments/instrumentData.csv'
DELIMITER ','
CSV HEADER;