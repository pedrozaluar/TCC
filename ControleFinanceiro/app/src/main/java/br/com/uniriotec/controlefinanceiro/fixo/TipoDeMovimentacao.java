package br.com.uniriotec.controlefinanceiro.fixo;

import br.com.uniriotec.controlefinanceiro.model.Movimentacao;
import br.com.uniriotec.controlefinanceiro.model.MovimentacaoFixa;
import br.com.uniriotec.controlefinanceiro.model.MovimentacaoParcelada;
import br.com.uniriotec.controlefinanceiro.model.MovimentacaoVariavel;

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

	public static TipoDeMovimentacao obterPelaMovimentacao(Movimentacao movimentacao) {
		if (movimentacao instanceof MovimentacaoVariavel) {
			return VARIAVEL;
		} else if (movimentacao instanceof MovimentacaoFixa) {
			return FIXA;
		} else if (movimentacao instanceof MovimentacaoParcelada) {
			return PARCELADA;
		}
		return null;
	}
}
