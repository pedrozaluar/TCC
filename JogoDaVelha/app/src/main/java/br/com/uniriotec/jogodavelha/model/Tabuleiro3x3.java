package br.com.uniriotec.jogodavelha.model;

import android.widget.Button;

/**
 * Classe que representa o tabuleiro de tamanho 3x3 do jogo da velha
 */
public class Tabuleiro3x3 implements Tabuleiro {

	public static final int TAMANHO = 3;
	private Button matrizBotoes[][];

	public Tabuleiro3x3(Button[][] matrizBotoes) {
		this.matrizBotoes = matrizBotoes;
	}

	/**
	 * Método que identifica se tem uma sequência completa de três símbolos na horizontal, vertical ou diagonal.
	 * @return true, se existe uma sequência de três símbolos iguais na horizontal, vertical ou
	 * diagonal, senão false.
	 */
	public boolean completouSequenciaDeSimbolos() {
		// Verifica se há alguma linha com mesmo valor (e diferente de vazio)
		for (int linha=0; linha < TAMANHO; linha++) {
			if (!matrizBotoes[linha][0].getText().equals("") &&
				 matrizBotoes[linha][0].getText().equals(matrizBotoes[linha][1].getText()) &&
				 matrizBotoes[linha][1].getText().equals(matrizBotoes[linha][2].getText())) {
				return true;
			}
		}

		// Verifica se há alguma coluna com mesmo valor (e diferente de vazio)
		for (int coluna=0; coluna < TAMANHO; coluna++) {
			if (!matrizBotoes[0][coluna].getText().equals("") &&
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
	 * Método que identifica se ainda há casas (botões) que não foram clicadas (que estão vazias)
	 * @return true, se tem algum casa vazia, senão false.
	 */
	public boolean existemMaisMovimentos() {
		for (int linha=0; linha < TAMANHO; linha++) {
			for (int coluna=0; coluna < TAMANHO; coluna++) {
				if (matrizBotoes[linha][coluna].getText().length() == 0) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Reinicia o tabuleiro, limpando e habilitando os botões
	 */
	public void reiniciar() {
		for (int linha=0; linha < TAMANHO; linha++) {
			for (int coluna=0; coluna < TAMANHO; coluna++) {
				matrizBotoes[linha][coluna].setText("");
				matrizBotoes[linha][coluna].setEnabled(true);
			}
		}
	}
}
