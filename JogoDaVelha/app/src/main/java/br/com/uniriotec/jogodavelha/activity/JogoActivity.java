package br.com.uniriotec.jogodavelha.activity;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import br.com.uniriotec.jogodavelha.R;
import br.com.uniriotec.jogodavelha.interfaceUtils.MensagemUtils;
import br.com.uniriotec.jogodavelha.model.Jogador;
import br.com.uniriotec.jogodavelha.model.Tabuleiro;
import br.com.uniriotec.jogodavelha.model.Tabuleiro3x3;

/**
 * Classe responsável por apresentar a tela do tabuleiro do jogo da velha e implementar suas funcionalidades
 */
public class JogoActivity extends ActionBarActivity {

	private Jogador jogador1;
	private Jogador jogador2;
	private Jogador jogadorCorrente;
	private Tabuleiro tabuleiro;

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
	 * instanciando variáveis que usaremos e apresentando informações na tela.
	 * PS: Este método é chamado após o onCreate, quando a classe já reconhece o XML referente à tela desta activity.
	 */
	@Override
	protected void onStart() {
		super.onStart();
		jogador1 = new Jogador("Jogador 1", "X");
		jogador2 = new Jogador("Jogador 2", "O");
		iniciarTabuleiro();
		jogadorCorrente = jogador1;
		mostrarJogadorCorrente();
	}

	/**
	 * Guarda os botões da tela em uma matriz ao início da execução, para não precisarmos ficar
	 * "procurando" a referência dos botões no xml toda hora.
	 */
	private void iniciarTabuleiro() {
		Button matrizBotoes[][] = new Button[Tabuleiro3x3.TAMANHO][Tabuleiro3x3.TAMANHO];
		matrizBotoes[0][0] = (Button) findViewById(R.id.buttonLine1Col1);
		matrizBotoes[0][1] = (Button) findViewById(R.id.buttonLine1Col2);
		matrizBotoes[0][2] = (Button) findViewById(R.id.buttonLine1Col3);

		matrizBotoes[1][0] = (Button) findViewById(R.id.buttonLine2Col1);
		matrizBotoes[1][1] = (Button) findViewById(R.id.buttonLine2Col2);
		matrizBotoes[1][2] = (Button) findViewById(R.id.buttonLine2Col3);

		matrizBotoes[2][0] = (Button) findViewById(R.id.buttonLine3Col1);
		matrizBotoes[2][1] = (Button) findViewById(R.id.buttonLine3Col2);
		matrizBotoes[2][2] = (Button) findViewById(R.id.buttonLine3Col3);

		tabuleiro = new Tabuleiro3x3(matrizBotoes);
	}

	/**
	 * Este método será chamado ao clicar em qualquer um dos botões do tabuleiro do jogo
	 * @param view - botão clicado
	 */
	public void onClickButton(View view) {
		Button botaoClicado = (Button) view;
		botaoClicado.setText(jogadorCorrente.getSimbolo());
		botaoClicado.setEnabled(false);

		if (tabuleiro.completouSequenciaDeSimbolos()) {
			MensagemUtils.mostrarCaixaDialogoSimples(JogoActivity.this, "Parabéns", jogadorCorrente.getNome() + " venceu!");
			tabuleiro.reiniciar();
		} else if (tabuleiro.existemMaisMovimentos()) {
			alternarJogadorCorrente();
			mostrarJogadorCorrente();
		} else {
			MensagemUtils.mostrarCaixaDialogoSimples(JogoActivity.this, "Velha", "Deu velha! (ninguém venceu)");
			tabuleiro.reiniciar();
		}
	}

	/**
	 * Alterna o jogador que será o próximo a jogar
	 */
	private void alternarJogadorCorrente() {
		if (jogadorCorrente.equals(jogador1)) {
			jogadorCorrente = jogador2;
		} else {
			jogadorCorrente = jogador1;
		}
	}

	/**
	 * Apresenta na tela o jogador corrente
	 */
	private void mostrarJogadorCorrente() {
		TextView labelJogador = (TextView) findViewById(R.id.label_jogador);
		labelJogador.setText(jogadorCorrente.getNome() + " (" + jogadorCorrente.getSimbolo() + ")");
		// OU: se implementar o método 'toString()' da classe Jogador poderia ficar:
		// labelJogador.setText(jogadorCorrente);
	}
}
