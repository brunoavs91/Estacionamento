package br.com.estacionamento.domain;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.estacionamento.enumeration.TipoVeiculo;

@Entity
public class RegistroEntradaSaida extends GenericDomain {
	private static final long serialVersionUID = -5157309792319625962L;


	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataEntrada;
	
	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataSaida;

	@Column(length = 7, nullable = false ,unique=true)
	private String placa;
	
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private TipoVeiculo tipo;
	
	@Column(length = 60 , nullable = false)
	private String modelo;
	
	@Column
	private BigDecimal valorPago;
	
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


	public TipoVeiculo getTipo() {
		return tipo;
	}

	public void setTipo(TipoVeiculo tipo) {
		this.tipo = tipo;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	

	public BigDecimal getValorPago() {
		return valorPago;
	}

	public void setValorPago(BigDecimal valorPago) {
		this.valorPago = valorPago;
	}

	public Vaga getVaga() {
		return vaga;
	}

	public void setVaga(Vaga vaga) {
		this.vaga = vaga;
	}
}
