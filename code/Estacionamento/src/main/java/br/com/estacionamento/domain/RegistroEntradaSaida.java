package br.com.estacionamento.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class RegistroEntradaSaida extends GenericDomain {
	private static final long serialVersionUID = -5157309792319625962L;


	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataEntrada;
	
	
	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataSaida;

	@Column(length = 50 , nullable = false)
	private String placa;
	
	@Column(length = 50 , nullable = false)
	private String tipo;
	
	@Column(length = 60 , nullable = false)
	private String modelo;
	
	@Column(nullable = false)
	private double valorPago;
	
	@OneToOne
	private Vaga vaga;
	

	public Date getDataEntrada() {
		return dataEntrada;
	}

	public void setDataEntrada(Date dataEntrada) {
		this.dataEntrada = dataEntrada;
	}


	public Date getDataSaida() {
		return dataSaida;
	}

	public void setDataSaida(Date dataSaida) {
		this.dataSaida = dataSaida;
	}


	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public double getValorPago() {
		return valorPago;
	}

	public void setValorPago(double valorPago) {
		this.valorPago = valorPago;
	}

	public Vaga getVaga() {
		return vaga;
	}

	public void setVaga(Vaga vaga) {
		this.vaga = vaga;
	}
}
