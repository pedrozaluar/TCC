package br.com.uniriotec.simonsays;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class JogoActivity extends Activity {

	private Button[] botoes;
	private List<Button> listaBotoesSorteados;
	private int proximoIndiceBotaoVerificar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_jogo);
	}

	@Override
	protected void onStart() {
		super.onStart();
		this.listaBotoesSorteados = new ArrayList<Button>();
		guardarArrayBotoes();
	}

	private void guardarArrayBotoes() {
		botoes = new Button[4];
		botoes[0] = (Button)findViewById(R.id.botao_vermelho);
		botoes[1] = (Button)findViewById(R.id.botao_azul);
		botoes[2] = (Button)findViewById(R.id.botao_verde);
		botoes[3] = (Button)findViewById(R.id.botao_amarelo);
	}

	public void onClickColorButton(View view) {
		Button botaoClicado = (Button) view;
		Button botaoQueDeveriaClicar = listaBotoesSorteados.get(proximoIndiceBotaoVerificar);

		if (botaoClicado.equals(botaoQueDeveriaClicar)) {
			proximoIndiceBotaoVerificar++;

			if(proximoIndiceBotaoVerificar > listaBotoesSorteados.size()-1) {
				// venceu a rodada
				iniciarNovaRodada();
			}
		} else {
			mostrarMensagemResultado();
			habilitarBotoes(false);
			listaBotoesSorteados.clear();
			Button start = (Button) findViewById(R.id.botao_start);
			start.setEnabled(true);
		}
	}

	public void onClickPlay(View view) {
		Button botaoPlay = (Button) view;
		botaoPlay.setEnabled(false);

		iniciarNovaRodada();
	}

	private void iniciarNovaRodada() {
		habilitarBotoes(false);
		adicionarNovoBotaoSorteadoNaLista();
		piscarBotoesNaTela();
		proximoIndiceBotaoVerificar = 0;
		habilitarBotoes(true);
	}

	private void habilitarBotoes(boolean habilitar) {
		for(Button botao : botoes) {
			botao.setEnabled(habilitar);
		}
	}

	private void adicionarNovoBotaoSorteadoNaLista() {

		// Random.nextInt(n): gera um número aleatório entre 0 e n-1
		Random geradorDeAleatorios = new Random();
		int numeroAleatorioEntre0e3 = geradorDeAleatorios.nextInt(4);

		listaBotoesSorteados.add(botoes[numeroAleatorioEntre0e3]);
	}

	private void piscarBotoesNaTela() {
		for(Button botao : listaBotoesSorteados) {
			piscarBotao(botao);
		}
	}

	private void piscarBotao(Button botao) {
/*		botao.setText("X");
		//espera
		SystemClock.sleep(3000);
		try {
			new Thread().sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		botao.setText("");
*/	}

	private void mostrarMensagemResultado() {

		AlertDialog.Builder builder = new AlertDialog.Builder(JogoActivity.this);
		builder.setTitle("Você perdeu =(");
		builder.setMessage("Maior sequência feita: " + (listaBotoesSorteados.size()-1) + " cores!");
		builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int id) {
				dialog.dismiss();
			}
		});

		AlertDialog alertDialog = builder.create();
		alertDialog.show();
	}
}
