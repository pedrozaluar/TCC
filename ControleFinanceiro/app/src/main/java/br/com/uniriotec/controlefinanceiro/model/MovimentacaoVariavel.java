package br.com.uniriotec.controlefinanceiro.model;

import java.io.Serializable;
import java.math.BigDecimal;

import br.com.uniriotec.controlefinanceiro.util.Util;

/**
 * Classe que representa uma movimentação simples e única. Ex: um lanche, cinema, roupa comprada.
 */
public class MovimentacaoVariavel extends Movimentacao implements Serializable {

	private BigDecimal valor;

	public MovimentacaoVariavel() {
		super();  // Chama o contrutor vazio da classe base
	}

	public MovimentacaoVariavel(int id, int dia, String descricao, BigDecimal valor, boolean isValorPositivo) {
		super(); // Chama o contrutor vazio da classe base
		setId(id);
		setDia(dia);
		setDescricao(descricao);
		this.valor = valor;
		setValorPositivo(isValorPositivo);
	}

	@Override
	public BigDecimal getValor() {
		return valor;
	}

	public String getValorDescr() {
		return Util.obterDescricaoValor(valor);
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
}
