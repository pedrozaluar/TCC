package br.com.uniriotec.jogodavelha.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import br.com.uniriotec.jogodavelha.R;
import br.com.uniriotec.jogodavelha.model.Jogador;

/**
 * Classe responsável por apresentar a tela do tabuleiro do jogo da velha e implementar suas funcionalidades
 */
public class JogoActivity extends ActionBarActivity {

	public static final int TAMANHO_TABELA = 3;

	private Jogador jogador1;
	private Jogador jogador2;
	private Jogador jogadorCorrente;
	private Button matrizBotoes[][];

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
		guardarMatrizBotoes();
		jogadorCorrente = jogador1;
		mostrarJogadorCorrente();
	}

	/**
	 * Guarda os botões da tela em uma matriz ao início da execução, para não precisarmos ficar
	 * "procurando" a referência dos botões no xml toda hora.
	 */
	private void guardarMatrizBotoes() {
		matrizBotoes = new Button[TAMANHO_TABELA][TAMANHO_TABELA];
		matrizBotoes[0][0] = (Button) findViewById(R.id.buttonLine1Col1);
		matrizBotoes[0][1] = (Button) findViewById(R.id.buttonLine1Col2);
		matrizBotoes[0][2] = (Button) findViewById(R.id.buttonLine1Col3);

		matrizBotoes[1][0] = (Button) findViewById(R.id.buttonLine2Col1);
		matrizBotoes[1][1] = (Button) findViewById(R.id.buttonLine2Col2);
		matrizBotoes[1][2] = (Button) findViewById(R.id.buttonLine2Col3);

		matrizBotoes[2][0] = (Button) findViewById(R.id.buttonLine3Col1);
		matrizBotoes[2][1] = (Button) findViewById(R.id.buttonLine3Col2);
		matrizBotoes[2][2] = (Button) findViewById(R.id.buttonLine3Col3);
	}

	/**
	 * Este método será chamado ao clicar em qualquer um dos botões do tabuleiro do jogo
	 * @param view - botão clicado
	 */
	public void onClickButton(View view) {
		Button botaoClicado = (Button) view;
		botaoClicado.setText(jogadorCorrente.getSimbolo());
		botaoClicado.setEnabled(false);

		if (jogadorCorrenteVenceu()) {
			mostrarMensagemResultado(jogadorCorrente.getNome() + " venceu!");
		} else if (existemMaisMovimentos()) {
			alternarJogadorCorrente();
			mostrarJogadorCorrente();
		} else {
			mostrarMensagemResultado("Deu velha! (ninguém venceu)");
		}
	}

	/**
	 * Método que identifica se o jogador que acabou de executar a jogada venceu o jogo
	 * @return true, se existe uma sequência de três símbolos iguais na vertical, horizontal ou
	 * diagonal, senão false.
	 */
	private boolean jogadorCorrenteVenceu() {
		// Verifica se há alguma linha com mesmo valor (e diferente de vazio)
		for (int linha=0; linha < TAMANHO_TABELA; linha++) {
			if (!matrizBotoes[linha][0].getText().equals("") &&
				 matrizBotoes[linha][0].getText().equals(matrizBotoes[linha][1].getText()) &&
				 matrizBotoes[linha][1].getText().equals(matrizBotoes[linha][2].getText())) {
				return true;
			}
		}

		// Verifica se há alguma coluna com mesmo valor (e diferente de vazio)
		for (int coluna=0; coluna < TAMANHO_TABELA; coluna++) {
			if (matrizBotoes[0][coluna].getText().length() != 0 &&
				matrizBotoes[0][coluna].getText().equals(matrizBotoes[1][coluna].getText()) &&
				matrizBotoes[1][coluna].getText().equals(matrizBotoes[2][coluna].getText())) {
				return true;
			}
		}

		// Verifica diagonal 1
		if (matrizBotoes[0][0].getText().length() != 0 &&
			matrizBotoes[0][0].getText().equals(matrizBotoes[1][1].getText()) &&
			matrizBotoes[1][1].getText().equals(matrizBotoes[2][2].getText())) {
			return true;
		}

		// Verifica diagonal 2
		if (matrizBotoes[0][2].getText().length() != 0 &&
			matrizBotoes[0][2].getText().equals(matrizBotoes[1][1].getText()) &&
			matrizBotoes[1][1].getText().equals(matrizBotoes[2][0].getText())) {
			return true;
		}

		return false;
	}

	/**
	 * Método que identifica se ainda há botões que não foram clicados (que estão vazios)
	 * @return true, se tem algum botão vazio, senão false.
	 */
	boolean existemMaisMovimentos() {
		for (int linha=0; linha < TAMANHO_TABELA; linha++) {
			for (int coluna=0; coluna < TAMANHO_TABELA; coluna++) {
				if (matrizBotoes[linha][coluna].getText().length() == 0) {
					return true;
				}
			}
		}
		return false;
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
	}

	/**
	 * Abre um diálogo na tela informando o restultado da partida. Reinicia o jogo automaticamente
	 * quando o usuário fechar o diálogo.
	 * @param mensagem - mensagem a ser exibida
	 */
	private void mostrarMensagemResultado(String mensagem) {

		// Cria a caixa de diálogo
		AlertDialog.Builder builder = new AlertDialog.Builder(JogoActivity.this);
		builder.setTitle("Resultado");
		builder.setMessage(mensagem);
		builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int id) {
				dialog.dismiss(); // fecha o diálogo
				reiniciarJogo();
			}
		});
		AlertDialog alertDialog = builder.create();

		// mostra a caixa de diálogo na tela
		alertDialog.show();
	}

	/**
	 * Reinicia o jogo, limpando e habilitando os botões
	 */
	void reiniciarJogo() {
		for (int linha=0; linha < TAMANHO_TABELA; linha++) {
			for (int coluna=0; coluna < TAMANHO_TABELA; coluna++) {
				matrizBotoes[linha][coluna].setText("");
				matrizBotoes[linha][coluna].setEnabled(true);
			}
		}
	}
}
