package br.com.one.conversor.moeda.enuns;

public enum ParConversao {

	REAL_PARA_DOLAR("BRL-USD"), 
	REAL_PARA_EURO("BRL-EUR"), 
	REAL_PARA_LIBRA("BRL-GBP"), 
	DOLAR_PARA_REAL("USD-BRL"),
	EURO_PARA_REAL("EUR-BRL"), 
	LIBRA_PARA_REAL("GBP-BRL");

	private String descricao;

	ParConversao(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return this.descricao;
	}
}
