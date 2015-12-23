package br.com.uniriotec.controlefinanceiro.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import br.com.uniriotec.controlefinanceiro.model.MesAno;
import br.com.uniriotec.controlefinanceiro.model.Movimentacao;
import br.com.uniriotec.controlefinanceiro.model.MovimentacaoVariavel;
import br.com.uniriotec.controlefinanceiro.model.MesDeMovimentacoes;

/**
 * Classe para fazer a persistência dos dados dos meses de movimentações guardando em memória
 */
public class MovimentacaoDaoMemory implements MovimentacaoDao {

	private static int idUltimaMovimentacao;
	private static List<MesDeMovimentacoes> mesesDeMovimentacoes;

	public MovimentacaoDaoMemory() {
		if (mesesDeMovimentacoes == null) {
			idUltimaMovimentacao = 0;
			mesesDeMovimentacoes = new ArrayList<MesDeMovimentacoes>();
		}
	}

	@Override
	public List<MesDeMovimentacoes> obterMesesDeMovimentacoes() {
		return mesesDeMovimentacoes;
	}

	@Override
	public void criarMesDeMovimentacoes(MesAno mesAno) {
		MesDeMovimentacoes mesDeMovimentacoes = new MesDeMovimentacoes(mesAno);

/*		todo: Adicionando preenchimento inicial para teste
*/		if (idUltimaMovimentacao == 0) {
			List<Movimentacao> movimentacoes = new ArrayList<Movimentacao>();
			movimentacoes.add(new MovimentacaoVariavel(1, 1, "Salário", new BigDecimal("1000.00"), true));
			movimentacoes.add(new MovimentacaoVariavel(2, 3, "Lanche (Hamburguer)", new BigDecimal("20.00"), false));
			movimentacoes.add(new MovimentacaoVariavel(3, 3, "Almoço", new BigDecimal("15.00"), false));
			movimentacoes.add(new MovimentacaoVariavel(4, 11, "Sorvete", new BigDecimal("5.00"), false));
			movimentacoes.add(new MovimentacaoVariavel(5, 23, "Compra", new BigDecimal("100.00"), false));
			idUltimaMovimentacao = 5;

			mesDeMovimentacoes.setMovimentacoes(movimentacoes);
		}

		mesesDeMovimentacoes.add(0, mesDeMovimentacoes);
	}

	@Override
	public void removerUltimoMesDeMovimentacoes() {
		if (!mesesDeMovimentacoes.isEmpty()) {
			mesesDeMovimentacoes.remove(0);
		}
	}

	@Override
	public List<Movimentacao> obterMovimentacoesDoMes(MesAno mesAno) {
		for (MesDeMovimentacoes mesDeMovimentacoes : mesesDeMovimentacoes) {
			if (mesDeMovimentacoes.getMesAno().equals(mesAno)) {
				return mesDeMovimentacoes.getMovimentacoes();
			}
		}
		return null;
	}

	@Override
	public void inserirMovimentacao(int idMesMovimentacoes, Movimentacao movimentacao) {
		for (MesDeMovimentacoes mesDeMovimentacoes : mesesDeMovimentacoes) {
			if (mesDeMovimentacoes.getId() == idMesMovimentacoes) {
				idUltimaMovimentacao++;
				movimentacao.setId(idUltimaMovimentacao);
				mesDeMovimentacoes.getMovimentacoes().add(movimentacao);
				Collections.sort(mesDeMovimentacoes.getMovimentacoes());
				break;
			}
		}
	}

	@Override
	public void alterarMovimentacao(Movimentacao movimentacaoAlt) {
		for (MesDeMovimentacoes mesDeMovimentacoes : mesesDeMovimentacoes) {
			for (int i = 0; i < mesDeMovimentacoes.getMovimentacoes().size(); i++) {
				Movimentacao movimentacao = mesDeMovimentacoes.getMovimentacoes().get(i);
				if (movimentacao.getId().equals(movimentacaoAlt.getId())) {
					mesDeMovimentacoes.getMovimentacoes().remove(i);
					mesDeMovimentacoes.getMovimentacoes().add(i, movimentacaoAlt);
					return;
				}
			}
		}
	}
}
