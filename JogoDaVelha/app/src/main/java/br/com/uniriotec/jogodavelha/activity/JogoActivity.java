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


public class JogoActivity extends ActionBarActivity {

	public static final int TAMANHO_TABELA = 3;

	private Jogador jogador1;
	private Jogador jogador2;
	private Jogador jogadorCorrente;
	private Button matrizBotoes[][];

	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jogo);
    }

	@Override
	protected void onStart() {
		super.onStart();
		jogador1 = new Jogador("Jogador 1", "X");
		jogador2 = new Jogador("Jogador 2", "O");
		jogadorCorrente = jogador1;
		mostrarJogadorCorrente();
		guardarMatrizBotoes();
	}

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

	private void alternarJogadorCorrente() {
		if (jogadorCorrente.equals(jogador1)) {
			jogadorCorrente = jogador2;
		} else {
			jogadorCorrente = jogador1;
		}
	}

	private void mostrarJogadorCorrente() {
		TextView labelJogador = (TextView) findViewById(R.id.label_jogador);
		labelJogador.setText(jogadorCorrente.getNome() + " (" + jogadorCorrente.getSimbolo() + ")");
	}

	private void mostrarMensagemResultado(String mensagem) {

		AlertDialog.Builder builder = new AlertDialog.Builder(JogoActivity.this);
		builder.setTitle("Resultado");
		builder.setMessage(mensagem);
		builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int id) {
				dialog.dismiss();
				reiniciaJogo();
			}
		});

		AlertDialog alertDialog = builder.create();
		alertDialog.show();
	}

	void reiniciaJogo() {
		for (int linha=0; linha < TAMANHO_TABELA; linha++) {
			for (int coluna=0; coluna < TAMANHO_TABELA; coluna++) {
				matrizBotoes[linha][coluna].setText("");
				matrizBotoes[linha][coluna].setEnabled(true);
			}
		}
	}

/*
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_jogo, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();

		//noinspection SimplifiableIfStatement
		if (id == R.id.action_settings) {
			return true;
		}

		return super.onOptionsItemSelected(item);
	}
*/
}
