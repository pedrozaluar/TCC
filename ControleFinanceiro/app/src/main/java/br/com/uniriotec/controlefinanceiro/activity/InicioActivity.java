package br.com.uniriotec.controlefinanceiro.activity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.List;

import br.com.uniriotec.controlefinanceiro.R;
import br.com.uniriotec.controlefinanceiro.dao.MovimentacaoDao;
import br.com.uniriotec.controlefinanceiro.dao.MovimentacaoDaoMemory;
import br.com.uniriotec.controlefinanceiro.fixo.Constantes;
import br.com.uniriotec.controlefinanceiro.model.MesAno;
import br.com.uniriotec.controlefinanceiro.model.MesDeMovimentacoes;
import br.com.uniriotec.controlefinanceiro.util.MensagemUtils;


public class InicioActivity extends Activity {

	private MovimentacaoDao movimentacaoDao;

	public InicioActivity() {
		movimentacaoDao = new MovimentacaoDaoMemory();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_inicio);
		carregarSeletorDeMes();
	}

	public void onClickBotaoEntrar(View view) {
		Spinner seletorDoMes = (Spinner) findViewById(R.id.seletorDeMes);
		MesDeMovimentacoes mesDeMovimentacoesSelecionado = (MesDeMovimentacoes) seletorDoMes.getSelectedItem();

		Intent intent = new Intent(this, ApresentaMesActivity.class);
		intent.putExtra(Constantes.PARAM_MES_MOVIMENTACAO, mesDeMovimentacoesSelecionado);
		startActivity(intent);
	}

	public void onClickAdicionarMes(View view) {
		List<MesDeMovimentacoes> mesesDeMovimentacoes = movimentacaoDao.obterMesesDeMovimentacoes();
		MesDeMovimentacoes ultimoMesDeMovimentacoes = mesesDeMovimentacoes.get(0);
		MesAno ultimoMesAnoDeMovimentacoes = ultimoMesDeMovimentacoes.getMesAno();
		MesAno proximoMesAnoDeMovimentacoes = MesAno.obterProximoMesAno(ultimoMesAnoDeMovimentacoes);

		movimentacaoDao.criarMesDeMovimentacoes(proximoMesAnoDeMovimentacoes);
		carregarSeletorDeMes();
		MensagemUtils.mostrarMensagemRapida(this, "Mês de " + proximoMesAnoDeMovimentacoes + " adicionado com sucesso!");
	}

	public void onClickRemoverUltimoMes(View view) {
		DialogInterface.OnClickListener onConfirmRemover = new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int id) {
				movimentacaoDao.removerUltimoMesDeMovimentacoes();
				carregarSeletorDeMes();
			}
		};
		MensagemUtils.mostrarCaixaDialogoConfirmacao(this, "Deseja mesmo remover o último mês de movimentações?", onConfirmRemover);
	}

	private void carregarSeletorDeMes() {
		List<MesDeMovimentacoes> mesesDeMovimentacoes = movimentacaoDao.obterMesesDeMovimentacoes();

		if (mesesDeMovimentacoes.isEmpty()) {
			MesAno mesAnoAtual = MesAno.obterMesAnoAtual();
			movimentacaoDao.criarMesDeMovimentacoes(mesAnoAtual);
			mesesDeMovimentacoes = movimentacaoDao.obterMesesDeMovimentacoes();
		}

		ArrayAdapter<MesDeMovimentacoes> adapter = new ArrayAdapter<MesDeMovimentacoes>(this, android.R.layout.simple_spinner_item, mesesDeMovimentacoes);
		Spinner seletorDeMes = (Spinner) findViewById(R.id.seletorDeMes);
		seletorDeMes.setAdapter(adapter);
	}
}
