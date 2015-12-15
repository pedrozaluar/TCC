package br.com.uniriotec.controlefinanceiro.activity.helper;

import android.app.Activity;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;

import br.com.uniriotec.controlefinanceiro.R;
import br.com.uniriotec.controlefinanceiro.fixo.TipoDeMovimentacao;
import br.com.uniriotec.controlefinanceiro.model.Movimentacao;
import br.com.uniriotec.controlefinanceiro.model.MovimentacaoFixa;
import br.com.uniriotec.controlefinanceiro.model.MovimentacaoParcelada;
import br.com.uniriotec.controlefinanceiro.model.MovimentacaoVariavel;
import br.com.uniriotec.controlefinanceiro.util.MensagemUtils;
import br.com.uniriotec.controlefinanceiro.util.Util;

/**
 * Classe de apoio para manuseio do formulário de cadastro de movimentação
 */
public class FormularioMovimentacaoHelper {

	private Spinner seletorTipoDeMovimentacao;
	private EditText txtDescricao;
	private EditText txtDia;
	private EditText txtValor;
	private Switch switchEfetuada;

	private Switch switchFinalizar;

	private EditText txtTotalParcelas;
	private EditText txtParcelaCorrente;

	public FormularioMovimentacaoHelper(Activity formularioActivity) {

		activity = formularioActivity;
		seletorTipoDeMovimentacao = (Spinner) formularioActivity.findViewById(R.id.seletorTipoDeMovimentacao);
		txtDescricao = (EditText) formularioActivity.findViewById(R.id.txtDescricao);
		txtDia = (EditText) formularioActivity.findViewById(R.id.txtDia);
		txtValor = (EditText) formularioActivity.findViewById(R.id.txtValor);
		switchEfetuada = (Switch) formularioActivity.findViewById(R.id.switchEfetuada);

		switchFinalizar = (Switch) formularioActivity.findViewById(R.id.switchFinalizar);

		txtTotalParcelas = (EditText) formularioActivity.findViewById(R.id.txtTotalParcelas);
		txtParcelaCorrente = (EditText) formularioActivity.findViewById(R.id.txtParcelaCorrente);
	}

	public void colocaNoFormulario(Movimentacao movimentacao) {

		txtDescricao.setText(movimentacao.getDescricao());
		txtDia.setText(movimentacao.getDiaDescr());
		txtValor.setText(movimentacao.getValorDescr());
		switchEfetuada.setChecked(movimentacao.isEfetuada());

		if (movimentacao instanceof MovimentacaoFixa) {
			MovimentacaoFixa movimentacaoFixa = (MovimentacaoFixa) movimentacao;
			switchFinalizar.setChecked(movimentacaoFixa.isUltimoMes());

		} else if (movimentacao instanceof MovimentacaoParcelada) {

			MovimentacaoParcelada movimentacaoParcelada = (MovimentacaoParcelada) movimentacao;
			txtTotalParcelas.setText(movimentacaoParcelada.getTotalParcelasDescr());
			txtParcelaCorrente.setText(movimentacaoParcelada.getParcelaCorrenteDescr());
		}
	}

	public void colocaNaMovimentacao(Movimentacao movimentacao) {
		movimentacao.setDescricao(txtDescricao.getText().toString());
		movimentacao.setDia(Integer.parseInt(txtDia.getText().toString()));
		movimentacao.setEfetuada(switchEfetuada.isChecked());

		TipoDeMovimentacao tipoDeMovimentacao = (TipoDeMovimentacao) seletorTipoDeMovimentacao.getSelectedItem();

		switch (tipoDeMovimentacao) {
			case VARIAVEL:
				MovimentacaoVariavel movimentacaoVariavel = (MovimentacaoVariavel) movimentacao;
				movimentacaoVariavel.setValor(Util.toBigDecimal(txtValor.getText().toString()));
				break;

			case FIXA:
				MovimentacaoFixa movimentacaoFixa = (MovimentacaoFixa) movimentacao;
				movimentacaoFixa.setValor(Util.toBigDecimal(txtValor.getText().toString()));
				movimentacaoFixa.setUltimoMes(switchFinalizar.isChecked());
				break;

			case PARCELADA:
				MovimentacaoParcelada movimentacaoParcelada = (MovimentacaoParcelada) movimentacao;
				movimentacaoParcelada.setValorTotal(Util.toBigDecimal(txtValor.getText().toString()));
				movimentacaoParcelada.setTotalParcelas(Integer.parseInt(txtTotalParcelas.getText().toString()));
				movimentacaoParcelada.setParcelaCorrente(Integer.parseInt(txtParcelaCorrente.getText().toString()));
				break;

			default: break;
		}
	}

	private Activity activity;
	public boolean validarCampos() {
		MensagemUtils.mostrarMensagemRapida(activity, "Falta implementar validação dos campos");
		return true;
	}
}
