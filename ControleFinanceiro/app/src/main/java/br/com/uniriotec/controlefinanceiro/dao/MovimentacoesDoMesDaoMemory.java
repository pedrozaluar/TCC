package br.com.uniriotec.controlefinanceiro.dao;

import java.util.ArrayList;
import java.util.List;

import br.com.uniriotec.controlefinanceiro.model.MesAno;
import br.com.uniriotec.controlefinanceiro.model.MovimentacoesDoMes;

/**
 * Classe para fazer a persistência dos dados dos meses de movimentações guardando em memória
 */
public class MovimentacoesDoMesDaoMemory implements MovimentacoesDoMesDao {

	private static List<MovimentacoesDoMes> movimentacoesMeses;

	public MovimentacoesDoMesDaoMemory() {
		if (movimentacoesMeses == null) {
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
}
