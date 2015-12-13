package br.com.uniriotec.controlefinanceiro.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import br.com.uniriotec.controlefinanceiro.R;
import br.com.uniriotec.controlefinanceiro.fixo.Constantes;
import br.com.uniriotec.controlefinanceiro.fixo.Mes;
import br.com.uniriotec.controlefinanceiro.model.MovimentacoesDoMes;


public class InicioActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_inicio);

		List<MovimentacoesDoMes> movimentacoesMes = new ArrayList<MovimentacoesDoMes>();
		movimentacoesMes.add(new MovimentacoesDoMes(Mes.AGOSTO, 2015));
		movimentacoesMes.add(new MovimentacoesDoMes(Mes.SETEMBRO, 2015));
		movimentacoesMes.add(new MovimentacoesDoMes(Mes.OUTUBRO, 2015));
		movimentacoesMes.add(new MovimentacoesDoMes(Mes.NOVEMBRO, 2015));
		movimentacoesMes.add(new MovimentacoesDoMes(Mes.DEZEMBRO, 2015));

//		String[] meses = {"Agosto/2015", "Setembro/2015", "Outubro/2015", "Novembro/2015"};
		ArrayAdapter<MovimentacoesDoMes> adapter = new ArrayAdapter<MovimentacoesDoMes>(this, android.R.layout.simple_spinner_item, movimentacoesMes);

		Spinner seletorDeMes = (Spinner) findViewById(R.id.seletorDeMes);
		seletorDeMes.setAdapter(adapter);
	}

	public void onClickBtnEntrar(View view) {
		Spinner listaMeses = (Spinner) findViewById(R.id.seletorDeMes);
		MovimentacoesDoMes mesSelecionado = (MovimentacoesDoMes) listaMeses.getSelectedItem();

		Intent intent = new Intent(this, ApresentaMesActivity.class);
		intent.putExtra(Constantes.PARAM_MES_SELECIONADO, mesSelecionado);
		startActivity(intent);
	}
}
