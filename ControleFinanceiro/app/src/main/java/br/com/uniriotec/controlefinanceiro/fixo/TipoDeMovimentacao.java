package br.com.uniriotec.controlefinanceiro.fixo;

/**
 * Enum utilizado para representar os possíveis tipos de movimentação
 */
public enum TipoDeMovimentacao {

	VARIAVEL("Variável"),
	FIXA("Fixa"),
	PARCELADA("Parcelada");

	private String descricao;

	private TipoDeMovimentacao(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	@Override
	public String toString() {
		return descricao;
	}
}
