package br.com.estacionamento.enumeration;

public enum TipoUsuario {
	ADM("Administrador"),G("Gerente"),F("Funcionario");
	
	private String descricao;
	
	private TipoUsuario(String descricao) {
		this.descricao=descricao;
	}
	public String getDescricao() {
		return descricao;
	}
}
