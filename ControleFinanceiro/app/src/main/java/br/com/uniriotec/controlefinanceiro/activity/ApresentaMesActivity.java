package br.com.uniriotec.controlefinanceiro.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import br.com.uniriotec.controlefinanceiro.R;
import br.com.uniriotec.controlefinanceiro.customComponent.MovimentacaoAdapter;
import br.com.uniriotec.controlefinanceiro.dao.MovimentacoesDoMesDao;
import br.com.uniriotec.controlefinanceiro.dao.MovimentacoesDoMesDaoMemory;
import br.com.uniriotec.controlefinanceiro.fixo.Constantes;
import br.com.uniriotec.controlefinanceiro.model.Movimentacao;
import br.com.uniriotec.controlefinanceiro.model.MovimentacaoVariavel;
import br.com.uniriotec.controlefinanceiro.model.MovimentacoesDoMes;

public class ApresentaMesActivity extends Activity {

	private MovimentacoesDoMesDao movimentacoesDoMesDao;
	private MovimentacoesDoMes mesMovimentacoes;
	private MovimentacaoAdapter adapterListaMovimentacoes;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_apresenta_mes);

		movimentacoesDoMesDao = new MovimentacoesDoMesDaoMemory();

		mesMovimentacoes = (MovimentacoesDoMes) getIntent().getSerializableExtra(Constantes.PARAM_MES_MOVIMENTACAO);
		TextView labelTitulo = (TextView) findViewById(R.id.labelTitulo);
		labelTitulo.setText(mesMovimentacoes.toString());
	}

	@Override
	protected void onResume() {
		super.onResume();
		if (adapterListaMovimentacoes == null) {
			carregarListaMovimentacoes(mesMovimentacoes.getMovimentacoes());
		} else {
			List<Movimentacao> movimentacoes = movimentacoesDoMesDao.obterMovimentacoesDoMes(mesMovimentacoes.getMesAno());
			mesMovimentacoes.getMovimentacoes().clear();
			mesMovimentacoes.getMovimentacoes().addAll(movimentacoes);
			adapterListaMovimentacoes.notifyDataSetChanged();
		}
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
		intent.putExtra(Constantes.PARAM_ID_MES_MOVIMENTACOES, mesMovimentacoes.getId());
		intent.putExtra(Constantes.PARAM_MOVIMENTACAO, movimentacao);
		startActivity(intent);
	}
}
