package br.com.uniriotec.controlefinanceiro.dao;

import java.util.List;

import br.com.uniriotec.controlefinanceiro.model.MesAno;
import br.com.uniriotec.controlefinanceiro.model.MovimentacoesDoMes;

/**
 * Interface para fazer a persistência dos dados dos meses de movimentações
 */
public interface MovimentacoesDoMesDao {
	public List<MovimentacoesDoMes> obterMovimentacoesDosMeses();

	void criarMesDeMovimentacoes(MesAno mesAno);

	void removerUltimoMesDeMovimentacoes();
}
