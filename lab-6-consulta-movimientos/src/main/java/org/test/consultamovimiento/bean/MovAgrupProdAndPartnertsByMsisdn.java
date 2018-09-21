package org.test.consultamovimiento.bean;

import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("MovAgrupProdAndPartnertsByMsisdn")
public class MovAgrupProdAndPartnertsByMsisdn {
	
	private String msisdn;
	private String multisim;
	private String timestamp;	
	private Integer idtrans;
	private double importe;
	private String agrupadores;
	private String productName;
	private String market;
	private String flag_suscripcion;
	
	public MovAgrupProdAndPartnertsByMsisdn() {
		super();
	}

	public MovAgrupProdAndPartnertsByMsisdn(String msisdn, String multisim, String timestamp, Integer idtrans,
			double importe, String agrupadores, String productName, String market, String flag_suscripcion) {
		super();
		this.msisdn = msisdn;
		this.multisim = multisim;
		this.timestamp = timestamp;
		this.idtrans = idtrans;
		this.importe = importe;
		this.agrupadores = agrupadores;
		this.productName = productName;
		this.market = market;
		this.flag_suscripcion = flag_suscripcion;
	}

	public String getMsisdn() {
		return msisdn;
	}

	public void setMsisdn(String msisdn) {
		this.msisdn = msisdn;
	}

	public String getMultisim() {
		return multisim;
	}

	public void setMultisim(String multisim) {
		this.multisim = multisim;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public Integer getIdtrans() {
		return idtrans;
	}

	public void setIdtrans(Integer idtrans) {
		this.idtrans = idtrans;
	}

	public double getImporte() {
		return importe;
	}

	public void setImporte(double importe) {
		this.importe = importe;
	}

	public String getAgrupadores() {
		return agrupadores;
	}

	public void setAgrupadores(String agrupadores) {
		this.agrupadores = agrupadores;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getMarket() {
		return market;
	}

	public void setMarket(String market) {
		this.market = market;
	}

	public String getFlag_suscripcion() {
		return flag_suscripcion;
	}

	public void setFlag_suscripcion(String flag_suscripcion) {
		this.flag_suscripcion = flag_suscripcion;
	}	
}
