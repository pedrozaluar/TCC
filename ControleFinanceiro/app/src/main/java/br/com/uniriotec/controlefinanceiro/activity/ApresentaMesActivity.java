package br.com.uniriotec.controlefinanceiro.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import br.com.uniriotec.controlefinanceiro.R;
import br.com.uniriotec.controlefinanceiro.model.Movimentacao;
import br.com.uniriotec.controlefinanceiro.model.MovimentacaoVariavel;
import br.com.uniriotec.controlefinanceiro.util.Constantes;

public class ApresentaMesActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_apresenta_mes);

		String mesSelecionado = getIntent().getStringExtra(Constantes.PARAM_MES_SELECIONADO);
		TextView labelTitulo = (TextView) findViewById(R.id.labelTitulo);
		labelTitulo.setText(mesSelecionado);

		List<Movimentacao> movimentacoes = new ArrayList<Movimentacao>();
		movimentacoes.add(new MovimentacaoVariavel(1, "Lanche (Hamburguer)", BigDecimal.valueOf(23.50)));
		movimentacoes.add(new MovimentacaoVariavel(3, "Almoço", BigDecimal.valueOf(15.00)));
		movimentacoes.add(new MovimentacaoVariavel(3, "Sorvete", BigDecimal.valueOf(5.00)));
		movimentacoes.add(new MovimentacaoVariavel(7, "Compra", BigDecimal.valueOf(100.00)));
	}

	public void onClickBtnAdicionarCredito(View view) {
		Intent intent = new Intent(this, CadastraMovimentoActivity.class);
		intent.putExtra(Constantes.PARAM_MOVIMENTO_VALOR_POSITIVO, true);
		startActivity(intent);
	}

	public void onClickBtnAdicionarDebito(View view) {
		Intent intent = new Intent(this, CadastraMovimentoActivity.class);
		intent.putExtra(Constantes.PARAM_MOVIMENTO_VALOR_POSITIVO, false);
		startActivity(intent);
	}
}
