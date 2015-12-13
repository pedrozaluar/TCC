package br.com.uniriotec.controlefinanceiro.model;

import java.io.Serializable;
import java.math.BigDecimal;

import br.com.uniriotec.controlefinanceiro.fixo.Constantes;
import br.com.uniriotec.controlefinanceiro.util.Util;

/**
 * Classe que representa uma movimentação que sua quitação (em caso de dívida) ou recebimento (em caso de crédito)
 * foi dividida em parcelas. Ex: Compra de uma televisão dividida em 10 parcelas/prestações
 */
public class MovimentacaoParcelada extends Movimentacao implements Serializable {

	private BigDecimal valorTotal;
	private Integer totalParcelas;
	private Integer parcelaCorrente;

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

	public Integer getTotalParcelas() {
		return totalParcelas;
	}

	public String getTotalParcelasDescr() {
		return Util.obterDescricaoValor(totalParcelas);
	}

	public void setTotalParcelas(Integer totalParcelas) {
		this.totalParcelas = totalParcelas;
	}

	public Integer getParcelaCorrente() {
		return parcelaCorrente;
	}

	public String getParcelaCorrenteDescr() {
		return Util.obterDescricaoValor(parcelaCorrente);
	}

	public void setParcelaCorrente(Integer parcelaCorrente) {
		this.parcelaCorrente = parcelaCorrente;
	}
}
