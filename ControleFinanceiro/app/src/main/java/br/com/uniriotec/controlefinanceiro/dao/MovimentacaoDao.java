package br.com.uniriotec.controlefinanceiro.dao;

import java.util.List;

import br.com.uniriotec.controlefinanceiro.model.MesAno;
import br.com.uniriotec.controlefinanceiro.model.Movimentacao;
import br.com.uniriotec.controlefinanceiro.model.MesDeMovimentacoes;

/**
 * Interface para fazer a persistência dos dados dos meses de movimentações
 */
public interface MovimentacaoDao {
	public List<MesDeMovimentacoes> obterMesesDeMovimentacoes();

	void criarMesDeMovimentacoes(MesAno mesAno);

	void removerUltimoMesDeMovimentacoes();

	List<Movimentacao> obterMovimentacoesDoMes(MesAno mesAno);

	void inserirMovimentacao(int idMesMovimentacoes, Movimentacao movimentacao);

	void alterarMovimentacao(Movimentacao movimentacao);
}
