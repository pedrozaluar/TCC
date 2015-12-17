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
import br.com.uniriotec.controlefinanceiro.dao.MovimentacoesDoMesDao;
import br.com.uniriotec.controlefinanceiro.dao.MovimentacoesDoMesDaoMemory;
import br.com.uniriotec.controlefinanceiro.fixo.Constantes;
import br.com.uniriotec.controlefinanceiro.model.MesAno;
import br.com.uniriotec.controlefinanceiro.model.MovimentacoesDoMes;
import br.com.uniriotec.controlefinanceiro.util.MensagemUtils;


public class InicioActivity extends Activity {

	private MovimentacoesDoMesDao movimentacoesDoMesDao;

	public InicioActivity() {
		movimentacoesDoMesDao = new MovimentacoesDoMesDaoMemory();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_inicio);
		carregarSeletorDeMes();
	}

	public void onClickBotaoIr(View view) {
		Spinner seletorDoMes = (Spinner) findViewById(R.id.seletorDeMes);
		MovimentacoesDoMes mesMovimentacaoSelecionado = (MovimentacoesDoMes) seletorDoMes.getSelectedItem();

		Intent intent = new Intent(this, ApresentaMesActivity.class);
		intent.putExtra(Constantes.PARAM_MES_MOVIMENTACAO, mesMovimentacaoSelecionado);
		startActivity(intent);
	}

	public void onClickAdicionarMes(View view) {
		List<MovimentacoesDoMes> movimentacoesDosMeses = movimentacoesDoMesDao.obterMovimentacoesDosMeses();
		MovimentacoesDoMes ultimoMesMovimentacoes = movimentacoesDosMeses.get(0);
		MesAno ultimoMesAnoDeMovimentacoes = ultimoMesMovimentacoes.getMesAno();
		MesAno proximoMesAnoDeMovimentacoes = MesAno.obterProximoMesAno(ultimoMesAnoDeMovimentacoes);

		movimentacoesDoMesDao.criarMesDeMovimentacoes(proximoMesAnoDeMovimentacoes);
		carregarSeletorDeMes();
		MensagemUtils.mostrarMensagemRapida(this, "Mês de " + proximoMesAnoDeMovimentacoes + " adicionado com sucesso!");
	}

	public void onClickRemoverUltimoMes(View view) {
		DialogInterface.OnClickListener onConfirmRemover = new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int id) {
				movimentacoesDoMesDao.removerUltimoMesDeMovimentacoes();
				carregarSeletorDeMes();
			}
		};
		MensagemUtils.mostrarCaixaDialogoConfirmacao(this, "Deseja mesmo remover o último mês de movimentações?", onConfirmRemover);
	}

	private void carregarSeletorDeMes() {
		List<MovimentacoesDoMes> movimentacoesDosMeses = movimentacoesDoMesDao.obterMovimentacoesDosMeses();

		if (movimentacoesDosMeses.isEmpty()) {
			MesAno mesAnoAtual = MesAno.obterMesAnoAtual();
			movimentacoesDoMesDao.criarMesDeMovimentacoes(mesAnoAtual);
			movimentacoesDosMeses = movimentacoesDoMesDao.obterMovimentacoesDosMeses();
		}

		ArrayAdapter<MovimentacoesDoMes> adapter = new ArrayAdapter<MovimentacoesDoMes>(this, android.R.layout.simple_spinner_item, movimentacoesDosMeses);
		Spinner seletorDeMes = (Spinner) findViewById(R.id.seletorDeMes);
		seletorDeMes.setAdapter(adapter);
	}
}
