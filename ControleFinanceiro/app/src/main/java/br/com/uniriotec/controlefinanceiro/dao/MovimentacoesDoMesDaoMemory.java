package br.com.uniriotec.controlefinanceiro.dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import br.com.uniriotec.controlefinanceiro.model.MesAno;
import br.com.uniriotec.controlefinanceiro.model.Movimentacao;
import br.com.uniriotec.controlefinanceiro.model.MovimentacoesDoMes;

/**
 * Classe para fazer a persistência dos dados dos meses de movimentações guardando em memória
 */
public class MovimentacoesDoMesDaoMemory implements MovimentacoesDoMesDao {

	private static int idUltimaMovimentacao;
	private static List<MovimentacoesDoMes> movimentacoesMeses;

	public MovimentacoesDoMesDaoMemory() {
		if (movimentacoesMeses == null) {
			idUltimaMovimentacao = 0;
			movimentacoesMeses = new ArrayList<MovimentacoesDoMes>();
		}
	}

	@Override
	public List<MovimentacoesDoMes> obterMovimentacoesDosMeses() {
		return movimentacoesMeses;
	}

	@Override
	public void criarMesDeMovimentacoes(MesAno mesAno) {
		MovimentacoesDoMes movimentacoesDoMes = new MovimentacoesDoMes(mesAno);
		movimentacoesMeses.add(movimentacoesDoMes);
	}

	@Override
	public void removerUltimoMesDeMovimentacoes() {
		if (!movimentacoesMeses.isEmpty()) {
			movimentacoesMeses.remove(movimentacoesMeses.size()-1);
		}
	}

	@Override
	public void inserirMovimentacaoMes(int idMesMovimentacoes, Movimentacao movimentacao) {
		for (MovimentacoesDoMes movimentacoesDoMes : movimentacoesMeses) {
			if (movimentacoesDoMes.getId() == idMesMovimentacoes) {
				idUltimaMovimentacao++;
				movimentacao.setId(idUltimaMovimentacao);
				movimentacoesDoMes.getMovimentacoes().add(movimentacao);
				Collections.sort(movimentacoesDoMes.getMovimentacoes());
				break;
			}
		}
	}

	@Override
	public void alterarMovimentacaoMes(Movimentacao movimentacaoAlt) {
		for (MovimentacoesDoMes movimentacoesDoMes : movimentacoesMeses) {
			for (int i = 0; i < movimentacoesDoMes.getMovimentacoes().size()-1; i++) {
				Movimentacao movimentacao = movimentacoesDoMes.getMovimentacoes().get(i);
				if (movimentacao.getId() == movimentacaoAlt.getId()) {
					movimentacoesDoMes.getMovimentacoes().remove(i);
					movimentacoesDoMes.getMovimentacoes().add(i, movimentacaoAlt);
					return;
				}
			}
		}
	}
}
