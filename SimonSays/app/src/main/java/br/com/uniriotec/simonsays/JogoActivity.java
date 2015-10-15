package br.com.uniriotec.simonsays;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class JogoActivity extends Activity {

	public static final int MILLISEGUNDOS_PISCAR_BOTAO = 1000;
	public static final int MILLISEGUNDOS_ESPERAR_PISCAR_BOTAO = 500;

	private Button[] botoes;
	private Button botaoPlay;
	private List<Button> listaBotoesSorteados;
	private int proximoIndiceBotaoVerificar;
	private int quantidadeCliquesRestantesRodada;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_jogo);
	}

	@Override
	protected void onStart() {
		super.onStart();
		listaBotoesSorteados = new ArrayList<Button>();
		guardarArrayBotoes();
		botaoPlay = (Button) findViewById(R.id.botao_play);
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
			setQuantidadeCliquesRestantesRodada(quantidadeCliquesRestantesRodada - 1);

			if(proximoIndiceBotaoVerificar > listaBotoesSorteados.size()-1) {
				// venceu a rodada
				iniciarNovaRodada();
			}
		} else {
			mostrarMensagemResultado();
			habilitarBotoes(false);
			listaBotoesSorteados.clear();
			botaoPlay.setText("PLAY");
			botaoPlay.setEnabled(true);
		}
	}

	public void onClickPlay(View view) {
		botaoPlay.setEnabled(false);
		iniciarNovaRodada();
	}

	private void iniciarNovaRodada() {
		habilitarBotoes(false);
		adicionarNovoBotaoSorteadoNaLista();
		piscarBotoesNaTela();
		proximoIndiceBotaoVerificar = 0;
		setQuantidadeCliquesRestantesRodada(listaBotoesSorteados.size());
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
		piscarBotoesSorteados(0);
	}

	/**
	 * Pisca (acende por um tempo depois apaga)* os botoes a partir do indice "indiceBotaoInicioPiscar" na lista "listaBotoesSorteados"
	 * *Acender é colocar uma cor mais clara, apagar é voltar à cor original do botão
	 * @param indiceBotaoInicioPiscar
	 */
	private void piscarBotoesSorteados(final int indiceBotaoInicioPiscar) {

		// marca o botão para acender ao final do método
		listaBotoesSorteados.get(indiceBotaoInicioPiscar).setSelected(true);

		// chama método assíncrono para executar daqui a X milissegundos para apagar o botão e acender os próximos botões
		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				// marca o botão para "apagar" ao final do método
				listaBotoesSorteados.get(indiceBotaoInicioPiscar).setSelected(false);

				// Continua piscando os próximos botões ou, se terminou, habilita para o jogador fazer a jogada
				int proximoIndiceBotaoPiscar = indiceBotaoInicioPiscar + 1;
				boolean temMaisBotoesParaPiscar = proximoIndiceBotaoPiscar < listaBotoesSorteados.size();

				if (temMaisBotoesParaPiscar) {
					esperarParaPiscar(proximoIndiceBotaoPiscar);
				} else {
					habilitarBotoes(true);
				}
			}
		}, MILLISEGUNDOS_PISCAR_BOTAO);
	}

	/**
	 * Espera um tempo para piscar o próximo botão da lista
	 * @param indiceBotaoInicioPiscar
	 */
	private void esperarParaPiscar(final int indiceBotaoInicioPiscar) {
		new Handler().postDelayed(new Runnable() {
			public void run() {
				piscarBotoesSorteados(indiceBotaoInicioPiscar);
			}
		}, MILLISEGUNDOS_ESPERAR_PISCAR_BOTAO);
	}

	private void mostrarMensagemResultado() {

		AlertDialog.Builder builder = new AlertDialog.Builder(JogoActivity.this);
		builder.setTitle("Você perdeu =(");
		builder.setMessage("Maior sequência feita: " + (listaBotoesSorteados.size()-1) + " cor(es)!");
		builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int id) {
				dialog.dismiss();
			}
		});

		AlertDialog alertDialog = builder.create();
		alertDialog.show();
	}

	public void setQuantidadeCliquesRestantesRodada(int quantidadeCliquesRestantesRodada) {
		this.quantidadeCliquesRestantesRodada = quantidadeCliquesRestantesRodada;
		botaoPlay.setText("Restantes: " + quantidadeCliquesRestantesRodada);
	}
}
