package br.com.uniriotec.simonsays.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;

import java.util.Random;

import br.com.uniriotec.simonsays.R;
import br.com.uniriotec.simonsays.util.Lista;
import br.com.uniriotec.simonsays.util.ListaDeArray;


/**
 * Classe responsável por apresentar a tela do tabuleiro do jogo e implementar suas funcionalidades
 */
public class JogoActivity extends Activity {

	public static final int NUM_SEQUENCIA_MAXIMA = 8;
	public static final int MILLISEGUNDOS_PISCAR_BOTAO = 1000;
	public static final int MILLISEGUNDOS_ESPERAR_PISCAR_BOTAO = 500;

	private Button[] botoes;
	private Button botaoPlay;
	private Lista<Button> listaBotoesSorteados;
	private int proximoIndiceBotaoVerificar;
	private int quantidadeCliquesRestantesRodada;

	/**
	 * Além de chamar o método onCreate da classe pai, indica o XML referente à tela que esta
	 * classe irá chamar.
	 * Obs.1: Este método é chamado ao iniciar o aplicativo.
	 * Obs.2: Método criado automaticamente pela IDE (Android Studio).
	 * @param savedInstanceState
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_jogo);
	}

	/**
	 * Além de chamar o método onStart da classe pai, este método inicializa o jogo,
	 * instanciando variáveis que usaremos.
	 * PS: Este método é chamado após o onCreate, quando a classe já reconhece o XML referente à tela desta activity.
	 */
	@Override
	protected void onStart() {
		super.onStart();
		listaBotoesSorteados = new ListaDeArray<Button>(NUM_SEQUENCIA_MAXIMA);
		guardarArrayBotoes();
		botaoPlay = (Button) findViewById(R.id.botao_play);
	}

	/**
	 * Guarda os botões da tela em um array ao início da execução, para não precisarmos ficar
	 * "procurando" a referência dos botões no xml toda hora.
	 */
	private void guardarArrayBotoes() {
		botoes = new Button[4];
		botoes[0] = (Button)findViewById(R.id.botao_vermelho);
		botoes[1] = (Button)findViewById(R.id.botao_azul);
		botoes[2] = (Button)findViewById(R.id.botao_verde);
		botoes[3] = (Button)findViewById(R.id.botao_amarelo);
	}

	/**
	 * Este método será chamado ao clicar em qualquer um dos botões de cores do tabuleiro
	 * @param view - botão clicado
	 */
	public void onClickColorButton(View view) {
		Button botaoClicado = (Button) view;
		Button botaoQueDeveriaClicar = listaBotoesSorteados.get(proximoIndiceBotaoVerificar);

		if (botaoClicado.equals(botaoQueDeveriaClicar)) {
			proximoIndiceBotaoVerificar++;
			setQuantidadeCliquesRestantesRodada(quantidadeCliquesRestantesRodada - 1);

			if(proximoIndiceBotaoVerificar > listaBotoesSorteados.tamanho()-1) {
				// venceu a rodada
				if (listaBotoesSorteados.estahCheia()) {
					mostrarMensagemResultado("VITÓRIA", "Você conseguiu fazer a sequência de " + listaBotoesSorteados.tamanho() + " cores!");
					finalizarJogo();
				} else {
					iniciarNovaRodada();
				}
			}
		} else {
			mostrarMensagemResultado("Você perdeu =(", "Maior sequência feita: " + (listaBotoesSorteados.tamanho()-1) + " cor(es)!");
			finalizarJogo();
		}
	}

	/**
	 * Finaliza o jogo e mostra o início novamente (sem iniciar uma nova partida),
	 * deixando a tela como se o usuário tivesse reiniciado o aplicativo
	 */
	private void finalizarJogo() {
		habilitarBotoesDeCores(false);
		listaBotoesSorteados.limpar();
		botaoPlay.setText("PLAY");
		botaoPlay.setEnabled(true);
	}

	/**
	 * Este método será chamado ao clicar no botão "PLAY"
	 * @param view
	 */
	public void onClickPlay(View view) {
		botaoPlay.setEnabled(false);
		iniciarNovaRodada();
	}

	/**
	 * Inicia uma nova rodada do jogo
	 * Obs.: Uma rodada se inicia 'piscando' uma sequência de cores e termina quando o usuário
	 * clica nos botões na ordem da sequência piscada.
	 */
	private void iniciarNovaRodada() {
		habilitarBotoesDeCores(false);
		adicionarNovoBotaoSorteadoNaLista();
		piscarBotoesNaTela();
		proximoIndiceBotaoVerificar = 0;
		setQuantidadeCliquesRestantesRodada(listaBotoesSorteados.tamanho());
	}

	/**
	 * Habilita/Desabilita os botões das cores na tela
	 * @param habilitar
	 */
	private void habilitarBotoesDeCores(boolean habilitar) {
		for(Button botao : botoes) {
			botao.setEnabled(habilitar);
		}
	}

	/**
	 * Sorteia um dos botões coloridos da tela e adiciona ele na lista 'listaBotoesSorteados'
	 */
	private void adicionarNovoBotaoSorteadoNaLista() {

		// Random.nextInt(n): gera um número aleatório entre 0 e n-1
		Random geradorDeAleatorios = new Random();
		int numeroAleatorioEntre0e3 = geradorDeAleatorios.nextInt(4);

		listaBotoesSorteados.add(botoes[numeroAleatorioEntre0e3]);
	}

	/**
	 * Pisca os botões na sequência que eles estão na lista 'listaBotoesSorteados'
	 */
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
				boolean temMaisBotoesParaPiscar = proximoIndiceBotaoPiscar < listaBotoesSorteados.tamanho();

				if (temMaisBotoesParaPiscar) {
					esperarParaPiscar(proximoIndiceBotaoPiscar);
				} else {
					habilitarBotoesDeCores(true);
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

	/**
	 * Abre um diálogo na tela informando o restultado da partida.
	 * @param titulo - Titulo do diálogo
	 * @param mensagem - Mensagem do diálogo
	 */
	private void mostrarMensagemResultado(String titulo, String mensagem) {

		AlertDialog.Builder builder = new AlertDialog.Builder(JogoActivity.this);
		builder.setTitle(titulo);
		builder.setMessage(mensagem);
		builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int id) {
				dialog.dismiss();
			}
		});

		AlertDialog alertDialog = builder.create();
		alertDialog.show();
	}

	/**
	 * Muda o valor da quantidade de cliques restantes para terminar a rodada, para apresentar na tela.
	 * @param quantidadeCliquesRestantesRodada
	 */
	public void setQuantidadeCliquesRestantesRodada(int quantidadeCliquesRestantesRodada) {
		this.quantidadeCliquesRestantesRodada = quantidadeCliquesRestantesRodada;
		botaoPlay.setText("Restantes: " + quantidadeCliquesRestantesRodada);
	}
}
