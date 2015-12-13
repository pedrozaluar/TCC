package br.com.uniriotec.controlefinanceiro.model;

import java.io.Serializable;
import java.math.BigDecimal;

import br.com.uniriotec.controlefinanceiro.fixo.Constantes;

/**
 * Classe que representa uma movimentação que sua quitação (em caso de dívida) ou recebimento (em caso de crédito)
 * foi dividida em parcelas. Ex: Compra de uma televisão dividida em 10 parcelas/prestações
 */
public class MovimentacaoParcelada extends Movimentacao implements Serializable {

	private BigDecimal valorTotal;
	private int totalParcelas;
	private int parcelaCorrente;

	@Override
	public BigDecimal getValor() {
		return valorTotal.divide(BigDecimal.valueOf(totalParcelas), Constantes.BIGDECIMAL_SCALE, Constantes.BIGDECIMAL_ROUNDING_MODE);
	}

	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}

	public int getTotalParcelas() {
		return totalParcelas;
	}

	public void setTotalParcelas(int totalParcelas) {
		this.totalParcelas = totalParcelas;
	}

	public int getParcelaCorrente() {
		return parcelaCorrente;
	}

	public void setParcelaCorrente(int parcelaCorrente) {
		this.parcelaCorrente = parcelaCorrente;
	}
}
