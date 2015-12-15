package br.com.uniriotec.controlefinanceiro.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import br.com.uniriotec.controlefinanceiro.R;
import br.com.uniriotec.controlefinanceiro.activity.helper.FormularioMovimentacaoHelper;
import br.com.uniriotec.controlefinanceiro.dao.MovimentacoesDoMesDao;
import br.com.uniriotec.controlefinanceiro.dao.MovimentacoesDoMesDaoMemory;
import br.com.uniriotec.controlefinanceiro.fixo.Constantes;
import br.com.uniriotec.controlefinanceiro.fixo.TipoDeMovimentacao;
import br.com.uniriotec.controlefinanceiro.model.Movimentacao;

public class CadastraMovimentoActivity extends Activity {

	private Movimentacao movimentacao;
	private MovimentacoesDoMesDao movimentacoesDoMesDao;
	private FormularioMovimentacaoHelper formularioHelper;

	public CadastraMovimentoActivity() {
		movimentacoesDoMesDao = new MovimentacoesDoMesDaoMemory();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cadastra_movimento);

		formularioHelper = new FormularioMovimentacaoHelper(this);
		movimentacao = (Movimentacao) getIntent().getSerializableExtra(Constantes.PARAM_MOVIMENTACAO);
		carregarTela();
	}

	private void carregarTela() {
		mudarLayoutCreditoOuDebito(movimentacao.isValorPositivo());
		carregarSeletorTipoDeMovimentacao();
		formularioHelper.colocaNoFormulario(movimentacao);
	}

	private void mudarLayoutCreditoOuDebito(boolean isCredito) {
		TextView labelTitulo = (TextView) findViewById(R.id.labelTitulo);
		Switch switchEfetuada = (Switch) findViewById(R.id.switchEfetuada);
		EditText txtValor = (EditText) findViewById(R.id.txtValor);

		if (isCredito) {
			labelTitulo.setText("Novo Crédito");
			switchEfetuada.setText("Recebido");
			labelTitulo.setTextColor(getResources().getColor(R.color.cor_credito));
			txtValor.setTextColor(getResources().getColor(R.color.cor_credito));
//			OU: Color.parseColor("#008000")

		} else {
			labelTitulo.setText("Novo Débito");
			switchEfetuada.setText("Pago");
			labelTitulo.setTextColor(getResources().getColor(R.color.cor_debito));
			txtValor.setTextColor(getResources().getColor(R.color.cor_debito));
		}
	}

	private void carregarSeletorTipoDeMovimentacao() {
		// Carregando valores a escolher
		ArrayAdapter<TipoDeMovimentacao> adapter = new ArrayAdapter<TipoDeMovimentacao>(this, android.R.layout.simple_spinner_item, TipoDeMovimentacao.values());
		Spinner seletorTipoDeMovimentacao = (Spinner) findViewById(R.id.seletorTipoDeMovimentacao);
		seletorTipoDeMovimentacao.setAdapter(adapter);

		// Definindo método que será chamado ao selecionar um item
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

		mostrarElementosComATag(R.string.tag_movimentacao_fixa, false);
		mostrarElementosComATag(R.string.tag_movimentacao_parcelada, false);

		if (tipoDeMovimentacao == TipoDeMovimentacao.FIXA) {
			mostrarElementosComATag(R.string.tag_movimentacao_fixa, true);
		}
		if (tipoDeMovimentacao == TipoDeMovimentacao.PARCELADA) {
			mostrarElementosComATag(R.string.tag_movimentacao_parcelada, true);
		}
	}

	private void mostrarElementosComATag(int codigoTag, boolean mostrar) {
		String tag = getResources().getString(codigoTag);
		ViewGroup telaCadastro = (ViewGroup) findViewById(R.id.telaCadastroMovimento);

		for (int i=0; i < telaCadastro.getChildCount(); i++) {
			View view = telaCadastro.getChildAt(i);

			if (view.getTag() != null && view.getTag().toString().equals(tag)) {
				mostrarElementoDaTela(view, mostrar);
			}
		}
	}

	/**
	 * Mostra/Esconde o elemento da tela (View) passado como parâmetro
	 * @param elementoDaTela
	 * @param mostrar
	 */
	private void mostrarElementoDaTela(View elementoDaTela, boolean mostrar) {
		if (mostrar)
			elementoDaTela.setVisibility(View.VISIBLE);
		else
			elementoDaTela.setVisibility(View.GONE);
	}

	public void onClickSalvar(View view) {
		if (formularioHelper.validarCampos()) {
			formularioHelper.colocaNaMovimentacao(movimentacao);
			int idMesMovimentacoes = getIntent().getIntExtra(Constantes.PARAM_ID_MES_MOVIMENTACOES, 0);

			if (movimentacao.getId() == null) {
				movimentacoesDoMesDao.inserirMovimentacaoMes(idMesMovimentacoes, movimentacao);
			} else {
				movimentacoesDoMesDao.alterarMovimentacaoMes(movimentacao);
			}
			finish();
		}
	}

	public void onClickCancelar(View view) {
		finish();
	}
}

