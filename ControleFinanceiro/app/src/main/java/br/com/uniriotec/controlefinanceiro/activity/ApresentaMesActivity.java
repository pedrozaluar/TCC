package br.com.uniriotec.controlefinanceiro.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import br.com.uniriotec.controlefinanceiro.R;
import br.com.uniriotec.controlefinanceiro.customComponent.MovimentacaoAdapter;
import br.com.uniriotec.controlefinanceiro.fixo.Constantes;
import br.com.uniriotec.controlefinanceiro.model.Movimentacao;
import br.com.uniriotec.controlefinanceiro.model.MovimentacaoVariavel;
import br.com.uniriotec.controlefinanceiro.model.MovimentacoesDoMes;

public class ApresentaMesActivity extends Activity {

	private MovimentacoesDoMes mesMovimentacoes;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_apresenta_mes);

		mesMovimentacoes = (MovimentacoesDoMes) getIntent().getSerializableExtra(Constantes.PARAM_MES_MOVIMENTACAO);
		TextView labelTitulo = (TextView) findViewById(R.id.labelTitulo);
		labelTitulo.setText(mesMovimentacoes.toString());

		List<Movimentacao> movimentacoes = new ArrayList<Movimentacao>();
		movimentacoes.add(new MovimentacaoVariavel(1, "Salário", new BigDecimal("1000.00"), true));
		movimentacoes.add(new MovimentacaoVariavel(3, "Lanche (Hamburguer)", new BigDecimal("23.50"), false));
		movimentacoes.add(new MovimentacaoVariavel(3, "Almoço", new BigDecimal("15.00"), false));
		movimentacoes.add(new MovimentacaoVariavel(11, "Sorvete", new BigDecimal("5.00"), false));
		movimentacoes.add(new MovimentacaoVariavel(23, "Compra", new BigDecimal("100.00"), false));
		movimentacoes.add(new MovimentacaoVariavel(1, "Salário", new BigDecimal("1000.00"), true));
		movimentacoes.add(new MovimentacaoVariavel(3, "Lanche (Hamburguer)", new BigDecimal("23.50"), false));
		movimentacoes.add(new MovimentacaoVariavel(3, "Almoço", new BigDecimal("15.00"), false));
		movimentacoes.add(new MovimentacaoVariavel(11, "Sorvete", new BigDecimal("5.00"), false));
		movimentacoes.add(new MovimentacaoVariavel(23, "Compra", new BigDecimal("100.00"), false));
		movimentacoes.add(new MovimentacaoVariavel(1, "Salário", new BigDecimal("1000.00"), true));
		movimentacoes.add(new MovimentacaoVariavel(3, "Lanche (Hamburguer)", new BigDecimal("23.50"), false));
		movimentacoes.add(new MovimentacaoVariavel(3, "Almoço", new BigDecimal("15.00"), false));
		movimentacoes.add(new MovimentacaoVariavel(11, "Sorvete", new BigDecimal("5.00"), false));
		movimentacoes.add(new MovimentacaoVariavel(23, "Compra", new BigDecimal("100.00"), false));
		movimentacoes.add(new MovimentacaoVariavel(1, "Salário", new BigDecimal("1000.00"), true));
		movimentacoes.add(new MovimentacaoVariavel(3, "Lanche (Hamburguer)", new BigDecimal("23.50"), false));
		movimentacoes.add(new MovimentacaoVariavel(3, "Almoço", new BigDecimal("15.00"), false));
		movimentacoes.add(new MovimentacaoVariavel(11, "Sorvete", new BigDecimal("5.00"), false));
		movimentacoes.add(new MovimentacaoVariavel(23, "Compra", new BigDecimal("100.00"), false));

		preencherListaMovimentacoes(movimentacoes);
	}

	private void preencherListaMovimentacoes(List<Movimentacao> movimentacoes) {
		ListView listViewMovimentacoes = (ListView) findViewById(R.id.lista_movimentacoes);
		MovimentacaoAdapter adapter = new MovimentacaoAdapter(this, R.layout.row_movimentacao, movimentacoes);
//		String[] arr = {"Item 1", "Item 2", "Item 33"};
//		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arr);
		listViewMovimentacoes.setAdapter(adapter);
	}

	public void onClickBtnAdicionarCredito(View view) {
		prepararCriarMovimentacao(true);
	}

	public void onClickBtnAdicionarDebito(View view) {
		prepararCriarMovimentacao(true);
	}

	private void prepararCriarMovimentacao(boolean isCredito) {
		Movimentacao movimentacao = new MovimentacaoVariavel();
		movimentacao.setValorPositivo(isCredito);

		Intent intent = new Intent(this, CadastraMovimentoActivity.class);
		intent.putExtra(Constantes.PARAM_ID_MES_MOVIMENTACOES, mesMovimentacoes.getId());
		intent.putExtra(Constantes.PARAM_MOVIMENTACAO, movimentacao);
		startActivity(intent);
	}
}
