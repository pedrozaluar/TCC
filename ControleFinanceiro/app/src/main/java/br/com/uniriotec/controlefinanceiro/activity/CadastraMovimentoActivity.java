package br.com.uniriotec.controlefinanceiro.activity;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import br.com.uniriotec.controlefinanceiro.R;
import br.com.uniriotec.controlefinanceiro.fixo.Constantes;
import br.com.uniriotec.controlefinanceiro.fixo.TipoDeMovimentacao;
import br.com.uniriotec.controlefinanceiro.model.Movimentacao;
import br.com.uniriotec.controlefinanceiro.util.MensagemUtils;

public class CadastraMovimentoActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cadastra_movimento);

		Movimentacao movimentacao = (Movimentacao) getIntent().getSerializableExtra(Constantes.PARAM_MOVIMENTACAO);

		TextView labelTitulo = (TextView) findViewById(R.id.labelTitulo);
		TextView switchEfetuada = (TextView) findViewById(R.id.switchEfetuada);
		EditText txtValor = (EditText) findViewById(R.id.txtValor);

		if (movimentacao.isValorPositivo()) {
			labelTitulo.setText("Novo Crédito");
			labelTitulo.setTextColor(Color.parseColor("#008000"));
			txtValor.setTextColor(Color.parseColor("#008000"));
			switchEfetuada.setText("Recebido");
//			OU:
//			labelTitulo.setTextColor(Color.rgb(200,0,0));
//			OU:
//			xml: <color name="errorColor">#f00</color>
//			java:labelTitulo.setTextColor(getResources().getColor(R.color.errorColor));

		} else {
			labelTitulo.setText("Novo Débito");
			labelTitulo.setTextColor(Color.parseColor("#BB0000"));
			txtValor.setTextColor(Color.parseColor("#BB0000"));
			switchEfetuada.setText("Pago");
		}

		ArrayAdapter<TipoDeMovimentacao> adapter = new ArrayAdapter<TipoDeMovimentacao>(this, android.R.layout.simple_spinner_item, TipoDeMovimentacao.values());
		Spinner seletorTipoDeMovimentacao = (Spinner) findViewById(R.id.seletorTipoDeMovimentacao);
		seletorTipoDeMovimentacao.setAdapter(adapter);

		seletorTipoDeMovimentacao.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				TipoDeMovimentacao tipoDeMovimentacao = (TipoDeMovimentacao) parent.getSelectedItem();
				onSelectTipoDeMovimentacao(tipoDeMovimentacao);
			}
			@Override
			public void onNothingSelected(AdapterView<?> parent) {}
		});
	}

	private void onSelectTipoDeMovimentacao(TipoDeMovimentacao tipoDeMovimentacao) {
		MensagemUtils.mostrarMensagemRapida(this, tipoDeMovimentacao.toString());
	}
}

