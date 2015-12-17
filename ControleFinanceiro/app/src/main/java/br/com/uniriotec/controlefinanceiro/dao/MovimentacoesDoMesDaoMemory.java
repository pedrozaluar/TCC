package br.com.uniriotec.controlefinanceiro.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import br.com.uniriotec.controlefinanceiro.model.MesAno;
import br.com.uniriotec.controlefinanceiro.model.Movimentacao;
import br.com.uniriotec.controlefinanceiro.model.MovimentacaoVariavel;
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

		// todo: tirar (MOCK DADOS MOVIMENTAÇÕES)
		List<Movimentacao> movimentacoes = new ArrayList<Movimentacao>();
		movimentacoes.add(new MovimentacaoVariavel(20, 1, "Salário", new BigDecimal("1000.00"), true));
		movimentacoes.add(new MovimentacaoVariavel(21, 3, "Lanche (Hamburguer)", new BigDecimal("23.50"), false));
		movimentacoes.add(new MovimentacaoVariavel(22, 3, "Almoço", new BigDecimal("15.00"), false));
		movimentacoes.add(new MovimentacaoVariavel(23, 11, "Sorvete", new BigDecimal("5.00"), false));
		movimentacoes.add(new MovimentacaoVariavel(24, 23, "Compra", new BigDecimal("100.00"), false));
		movimentacoesDoMes.setMovimentacoes(movimentacoes);
		// FIM MOCK DADOS MOVIMENTAÇÕES

		movimentacoesMeses.add(0, movimentacoesDoMes);
	}

	@Override
	public void removerUltimoMesDeMovimentacoes() {
		if (!movimentacoesMeses.isEmpty()) {
			movimentacoesMeses.remove(0);
		}
	}

	@Override
	public List<Movimentacao> obterMovimentacoesDoMes(MesAno mesAno) {
		for (MovimentacoesDoMes movimentacoesDoMes : movimentacoesMeses) {
			if (movimentacoesDoMes.getMesAno().equals(mesAno)) {
				return movimentacoesDoMes.getMovimentacoes();
			}
		}
		return null;
	}

	@Override
	public void inserirMovimentacao(int idMesMovimentacoes, Movimentacao movimentacao) {
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
	public void alterarMovimentacao(Movimentacao movimentacaoAlt) {
		for (MovimentacoesDoMes movimentacoesDoMes : movimentacoesMeses) {
			for (int i = 0; i < movimentacoesDoMes.getMovimentacoes().size(); i++) {
				Movimentacao movimentacao = movimentacoesDoMes.getMovimentacoes().get(i);
				if (movimentacao.getId().equals(movimentacaoAlt.getId())) {
					movimentacoesDoMes.getMovimentacoes().remove(i);
					movimentacoesDoMes.getMovimentacoes().add(i, movimentacaoAlt);
					return;
				}
			}
		}
	}
}
