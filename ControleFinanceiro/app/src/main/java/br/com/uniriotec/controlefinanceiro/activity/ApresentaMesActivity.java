package br.com.uniriotec.controlefinanceiro.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.math.BigDecimal;
import java.util.List;

import br.com.uniriotec.controlefinanceiro.R;
import br.com.uniriotec.controlefinanceiro.customComponent.MovimentacaoAdapter;
import br.com.uniriotec.controlefinanceiro.dao.MovimentacaoDao;
import br.com.uniriotec.controlefinanceiro.dao.MovimentacaoDaoMemory;
import br.com.uniriotec.controlefinanceiro.fixo.Constantes;
import br.com.uniriotec.controlefinanceiro.model.Movimentacao;
import br.com.uniriotec.controlefinanceiro.model.MovimentacaoVariavel;
import br.com.uniriotec.controlefinanceiro.model.MesDeMovimentacoes;
import br.com.uniriotec.controlefinanceiro.util.InterfaceUtils;

public class ApresentaMesActivity extends Activity {

	private MovimentacaoDao movimentacaoDao;
	private MesDeMovimentacoes mesDeMovimentacoes;
	private MovimentacaoAdapter adapterListaMovimentacoes;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_apresenta_mes);

		movimentacaoDao = new MovimentacaoDaoMemory();

		mesDeMovimentacoes = (MesDeMovimentacoes) getIntent().getSerializableExtra(Constantes.PARAM_MES_MOVIMENTACAO);
		TextView labelTitulo = (TextView) findViewById(R.id.labelTitulo);
		labelTitulo.setText(mesDeMovimentacoes.toString());
	}

	@Override
	protected void onResume() {
		super.onResume();
		if (adapterListaMovimentacoes == null) {
			carregarListaMovimentacoes(mesDeMovimentacoes.getMovimentacoes());
		} else {
			List<Movimentacao> movimentacoes = movimentacaoDao.obterMovimentacoesDoMes(mesDeMovimentacoes.getMesAno());
			mesDeMovimentacoes.getMovimentacoes().clear();
			mesDeMovimentacoes.getMovimentacoes().addAll(movimentacoes);
			adapterListaMovimentacoes.notifyDataSetChanged();
		}
		carregarValorTotalMovimentacoes();
	}

	private void carregarValorTotalMovimentacoes() {
		BigDecimal valorTotalMovimentacoes = mesDeMovimentacoes.obterValorTotalMovimentacoes();
		boolean isPositivo = valorTotalMovimentacoes.compareTo(BigDecimal.ZERO) > 0;

		TextView labelValorTotal = (TextView) findViewById(R.id.labelValorTotal);
		labelValorTotal.setText(InterfaceUtils.obterDescricaoValor(valorTotalMovimentacoes));
		labelValorTotal.setTextColor(getResources().getColor(InterfaceUtils.obterIdCorDoCampoValor(isPositivo, true)));
	}

	private void carregarListaMovimentacoes(List<Movimentacao> movimentacoes) {
		// Carregando valores exibidos na lista
		ListView listViewMovimentacoes = (ListView) findViewById(R.id.lista_movimentacoes);
		adapterListaMovimentacoes = new MovimentacaoAdapter(this, R.layout.row_movimentacao, movimentacoes);
		listViewMovimentacoes.setAdapter(adapterListaMovimentacoes);

		// Definindo método que será chamado ao selecionar um item
		listViewMovimentacoes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Movimentacao movimentacaoSelecionada = (Movimentacao) parent.getItemAtPosition(position);
				chamarTelaCadastroMovimentacao(movimentacaoSelecionada);
			}
		});
	}

	public void onClickBtnAdicionarCredito(View view) {
		Movimentacao movimentacao = new MovimentacaoVariavel();
		movimentacao.setValorPositivo(true);
		chamarTelaCadastroMovimentacao(movimentacao);
	}

	public void onClickBtnAdicionarDebito(View view) {
		Movimentacao movimentacao = new MovimentacaoVariavel();
		movimentacao.setValorPositivo(false);
		chamarTelaCadastroMovimentacao(movimentacao);
	}

	private void chamarTelaCadastroMovimentacao(Movimentacao movimentacao) {
		Intent intent = new Intent(this, CadastraMovimentoActivity.class);
		intent.putExtra(Constantes.PARAM_ID_MES_MOVIMENTACOES, mesDeMovimentacoes.getId());
		intent.putExtra(Constantes.PARAM_MOVIMENTACAO, movimentacao);
		startActivity(intent);
	}
}
