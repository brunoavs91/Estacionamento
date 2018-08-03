package br.com.estacionamento.enumeration;

public enum TamanhoVaga {
	
	P("Pequena"),M("Media"),G("Grande");
	
	private String descricao;
	
	private TamanhoVaga(String descricao) {
		this.descricao=descricao;
	}
	public String getDescricao() {
		return descricao;
	}
}
