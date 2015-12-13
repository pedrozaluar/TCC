package br.com.uniriotec.controlefinanceiro.model;

import java.io.Serializable;
import java.math.BigDecimal;

import br.com.uniriotec.controlefinanceiro.util.Util;

/**
 * Classe que representa uma movimentação que é mensal, ou seja, Todo mês o usuário faz aquela mesma
 * movimentação. Ex: Salário, aluguel, conta de internet, luz, etc.
 */
public class MovimentacaoFixa extends Movimentacao implements Serializable {

	private BigDecimal valor;
	private boolean ultimoMes;

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

	public boolean isUltimoMes() {
		return ultimoMes;
	}

	public void setUltimoMes(boolean ultimoMes) {
		this.ultimoMes = ultimoMes;
	}
}
