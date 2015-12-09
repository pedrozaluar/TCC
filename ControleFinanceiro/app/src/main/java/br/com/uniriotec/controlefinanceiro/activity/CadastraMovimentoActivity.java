package br.com.uniriotec.controlefinanceiro.activity;

import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import br.com.uniriotec.controlefinanceiro.R;
import br.com.uniriotec.controlefinanceiro.util.Constantes;

public class CadastraMovimentoActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cadastra_movimento);

		boolean isValorPositivo = getIntent().getBooleanExtra(Constantes.PARAM_MOVIMENTO_VALOR_POSITIVO, false);
		TextView labelTitulo = (TextView) findViewById(R.id.labelTitulo);
		labelTitulo.setText(isValorPositivo ? "Novo\nCrédito" : "Novo\nDébito");
	}
}
