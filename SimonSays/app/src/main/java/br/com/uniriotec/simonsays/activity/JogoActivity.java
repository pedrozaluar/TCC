package br.com.uniriotec.simonsays.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.Random;

import br.com.uniriotec.simonsays.R;
import br.com.uniriotec.simonsays.interfaceUtils.ButtonUtils;
import br.com.uniriotec.simonsays.interfaceUtils.ConfiguracaoPisqueBotao;
import br.com.uniriotec.simonsays.util.Lista;
import br.com.uniriotec.simonsays.util.ListaDeArray;


/**
 * Classe responsável por apresentar a tela do tabuleiro do jogo e implementar suas funcionalidades
 */
public class JogoActivity extends Activity {

	public class MinhaConfiguracaoPisqueBotao implements ConfiguracaoPisqueBotao {
		@Override
		public void acender(Button button) {
			button.setSelected(true);
		}

		@Override
		public void apagar(Button button) {
			button.setSelected(false);
		}

		@Override
		public void executarAposPiscarBotoes() {
			habilitarBotoesDoJogo(true);
		}
	}

	public static final int NUM_SEQUENCIA_MAXIMA = 8;

	private Button[] botoes;
	private Button botaoPlay;
	private Lista<Button> listaBotoesSorteados;
	private int indiceBotaoVerificar;
	private int quantidadeCliquesRestantesRodada;

	private ConfiguracaoPisqueBotao configuracaoPisqueBotao;

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
		guardarArrayBotoes();
		botaoPlay = (Button) findViewById(R.id.botao_play);
		listaBotoesSorteados = new ListaDeArray<Button>(NUM_SEQUENCIA_MAXIMA);
		configuracaoPisqueBotao = new MinhaConfiguracaoPisqueBotao();
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
		Button botaoQueDeveriaClicar = listaBotoesSorteados.get(indiceBotaoVerificar);

		if (botaoClicado.equals(botaoQueDeveriaClicar)) {
			indiceBotaoVerificar++;
			setQuantidadeCliquesRestantesRodada(quantidadeCliquesRestantesRodada - 1);

			if (quantidadeCliquesRestantesRodada == 0) {
				// venceu a rodada
				if (listaBotoesSorteados.estahCheia()) {
					mostrarMensagemResultado("VITÓRIA", "Você conseguiu fazer a sequência de " + listaBotoesSorteados.tamanho() + " cores!");
					finalizarJogo();
				} else {
					iniciarNovaRodada();
				}
			}
		} else {
			mostrarMensagemResultado("PERDEU", "Maior sequência feita: " + (listaBotoesSorteados.tamanho()-1) + " cor(es)!");
			finalizarJogo();
		}
	}

	/**
	 * Finaliza o jogo e mostra o início novamente (sem iniciar uma nova partida),
	 * deixando a tela como se o usuário tivesse reiniciado o aplicativo
	 */
	private void finalizarJogo() {
		habilitarBotoesDoJogo(false);
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
		habilitarBotoesDoJogo(false);
		adicionarNovoBotaoSorteadoNaLista();
		indiceBotaoVerificar = 0;
		setQuantidadeCliquesRestantesRodada(listaBotoesSorteados.tamanho());
		ButtonUtils.piscarBotoes(listaBotoesSorteados, configuracaoPisqueBotao);
	}

	/**
	 * Habilita/Desabilita os botões das cores na tela
	 * @param habilitar
	 */
	private void habilitarBotoesDoJogo(boolean habilitar) {
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
		int numeroDoBotaoAleatorio = geradorDeAleatorios.nextInt(botoes.length);
		Button botaoAleatorio = botoes[numeroDoBotaoAleatorio];

		listaBotoesSorteados.add(botaoAleatorio);
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
