package br.com.estacionamento.domain;

import javax.persistence.Column;
import javax.persistence.Entity;

@SuppressWarnings("serial")
@Entity
public class Vaga extends GenericDomain {
	
	@Column(nullable = false)
	private int numeroVaga;
	
	@Column(length = 50 ,nullable = false )
	private String localizacao;
	
	@Column(length = 50 ,nullable = false )
	private String tamanho;
	
	@Column(nullable = false )
	private double valor;
	
	@Column(length = 255 ,nullable = false )
	private String situacao;

	public int getNumeroVaga() {
		return numeroVaga;
	}

	public void setNumeroVaga(int numeroVaga) {
		this.numeroVaga = numeroVaga;
	}

	public String getLocalizacao() {
		return localizacao;
	}

	public void setLocalizacao(String localizacao) {
		this.localizacao = localizacao;
	}

	public String getTamanho() {
		return tamanho;
	}

	public void setTamanho(String tamanho) {
		this.tamanho = tamanho;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public String getSituacao() {
		return situacao;
	}

	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}
	
	
}
