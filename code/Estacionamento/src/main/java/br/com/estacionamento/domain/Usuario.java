package br.com.estacionamento.domain;

import javax.persistence.Column;
import javax.persistence.Entity;

@SuppressWarnings("serial")
@Entity
public class Usuario extends GenericDomain {
	
	@Column(length = 100 , nullable = false)
	private String nome;
	
	@Column(length = 11 , nullable = false)
	private String cpf;
	
	@Column(length = 100 , nullable = false)
	private String email;
	
	@Column(length = 100 , nullable = false)
	private String telefone;
	
	@Column(length = 20 , nullable = false)
	private String tipoUsuario;

	
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getTipoUsuario() {
		return tipoUsuario;
	}

	public void setTipoUsuario(String tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}
	
	
}
