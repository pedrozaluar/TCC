package br.com.uniriotec.controlefinanceiro.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import br.com.uniriotec.controlefinanceiro.R;
import br.com.uniriotec.controlefinanceiro.util.Constantes;


public class InicioActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_inicio);

		String[] meses = {"Agosto/2015", "Setembro/2015", "Outubro/2015", "Novembro/2015"};
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, meses);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		Spinner listaMeses = (Spinner) findViewById(R.id.seletorDeMes);
		listaMeses.setAdapter(adapter);
	}

	public void onClickBtnEntrar(View view) {
		Spinner listaMeses = (Spinner) findViewById(R.id.seletorDeMes);
		String mesSelecionado = (String) listaMeses.getSelectedItem();

		Intent intent = new Intent(this, ApresentaMesActivity.class);
		intent.putExtra(Constantes.PARAM_MES_SELECIONADO, mesSelecionado);
		startActivity(intent);
	}
}
