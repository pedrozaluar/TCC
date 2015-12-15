package br.com.uniriotec.controlefinanceiro.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import br.com.uniriotec.controlefinanceiro.fixo.Mes;

/**
 * Classe que representa um mês de movimentações
 */
public class MovimentacoesDoMes implements Serializable {


	private MesAno mesAno;
	private List<Movimentacao> movimentacoes;

	public MovimentacoesDoMes(MesAno mesAno) {
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
}
