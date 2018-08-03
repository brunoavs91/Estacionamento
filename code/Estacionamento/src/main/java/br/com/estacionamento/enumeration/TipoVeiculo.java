package br.com.estacionamento.enumeration;

public enum TipoVeiculo {
	
	P("Pequeno"),M("Medio"),G("Grande");
	
	private String descricao;
	
	private TipoVeiculo(String descricao) {
		this.descricao=descricao;
	}
	public String getDescricao() {
		return descricao;
	}
}
