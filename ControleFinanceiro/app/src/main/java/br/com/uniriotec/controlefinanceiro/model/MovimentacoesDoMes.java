package br.com.uniriotec.controlefinanceiro.model;

import java.io.Serializable;
import java.util.List;

import br.com.uniriotec.controlefinanceiro.fixo.Mes;

/**
 * Classe que representa um mês de movimentações
 */
public class MovimentacoesDoMes implements Serializable {


	private Mes mes;
	private int ano;
	private List<Movimentacao> movimentacoes;

	public MovimentacoesDoMes(Mes mes, int ano) {
		this.mes = mes;
		this.ano = ano;
	}

	public Mes getMes() {
		return mes;
	}

	public void setMes(Mes mes) {
		this.mes = mes;
	}

	public int getAno() {
		return ano;
	}

	public void setAno(int ano) {
		this.ano = ano;
	}

	public List<Movimentacao> getMovimentacoes() {
		return movimentacoes;
	}

	public void setMovimentacoes(List<Movimentacao> movimentacoes) {
		this.movimentacoes = movimentacoes;
	}

	@Override
	public String toString() {
		return mes.getDescricao() + "/" + ano;
	}
}
