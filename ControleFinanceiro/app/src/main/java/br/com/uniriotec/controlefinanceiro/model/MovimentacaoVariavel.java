package br.com.uniriotec.controlefinanceiro.model;

import java.math.BigDecimal;

/**
 * Classe que representa uma movimentação simples e única. Ex: um lanche, cinema, roupa comprada.
 */
public class MovimentacaoVariavel extends Movimentacao {

	private BigDecimal valor;

	public MovimentacaoVariavel() {
		super();  // Chama o contrutor vazio da classe base
		valor = BigDecimal.ZERO;
	}

	public MovimentacaoVariavel(int dia, String descricao, BigDecimal valor) {
		super(); // Chama o contrutor vazio da classe base
		setDia(dia);
		setDescricao(descricao);
		this.valor = valor;
	}

	@Override
	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
}