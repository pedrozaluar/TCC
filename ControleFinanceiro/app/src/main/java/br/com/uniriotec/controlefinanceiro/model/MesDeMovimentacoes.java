package br.com.uniriotec.controlefinanceiro.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import br.com.uniriotec.controlefinanceiro.fixo.Mes;

/**
 * Classe que representa um mês de movimentações
 */
public class MesDeMovimentacoes implements Serializable {

	private MesAno mesAno;
	private List<Movimentacao> movimentacoes;

	public MesDeMovimentacoes(MesAno mesAno) {
		this.mesAno = mesAno;
		this.movimentacoes = new ArrayList<Movimentacao>();
	}

	public int getId() {
		return mesAno.getId();
	}

	public MesAno getMesAno() {
		return mesAno;
	}

	public void setMesAno(MesAno mesAno) {
		this.mesAno = mesAno;
	}

	public List<Movimentacao> getMovimentacoes() {
		return movimentacoes;
	}

	public void setMovimentacoes(List<Movimentacao> movimentacoes) {
		this.movimentacoes = movimentacoes;
	}

	@Override
	public String toString() {
		return mesAno.toString();
	}

	public BigDecimal obterValorTotalMovimentacoes() {
		BigDecimal total = BigDecimal.ZERO;
		for (Movimentacao movimentacao : movimentacoes) {
			if (movimentacao.isEfetuada()) {
				if (movimentacao.isValorPositivo()) {
					total = total.add(movimentacao.getValor());
				} else {
					total = total.subtract(movimentacao.getValor());
				}
			}
		}
		return total;
	}
}
