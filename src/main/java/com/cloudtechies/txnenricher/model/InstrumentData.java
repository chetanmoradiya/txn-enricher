package com.cloudtechies.txnenricher.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@Table(name="instrument_data")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InstrumentData
{
	@Id
	@Column(name="sec_identifier")
	private String secIdentifier;

	@Column(name="typ_of_asset")
	private String typeOfAsset;

	@Column(name="class_of_a_sec")
	private String clsOfASec;

	@Column(name="loan_base_prod")
	private String loanBaseProduct;

	@Column(name="loan_sub_prod")
	private String loanSubProduct;

	@Column(name="loan_fur_sub_prod")
	private String loanFurthrSubProd;

	@Column(name="loan_lei_of_issuer")
	private String loanLeiOfIssuer;

	@Column(name="loan_maturity_of_secu")
	private String loanMaturityOfSecurity;

	@Column(name="loan_juris_of_issuer")
	private String loanJurisOfIssuer;
}
