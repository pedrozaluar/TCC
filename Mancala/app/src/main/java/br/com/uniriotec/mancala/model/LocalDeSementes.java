package br.com.uniriotec.mancala.model;

import android.widget.Button;

/**
 * Classe que representa um local que possa conter sementes no jogo Mancala (como um repositório ou cavidade)
 */
public class LocalDeSementes {

	protected Button botaoLocalDeSementes;

	public int getId() {
		return botaoLocalDeSementes.getId();
	}

	public LocalDeSementes(Button botaoLocalDeSementes) {
		this.botaoLocalDeSementes = botaoLocalDeSementes;
	}

	public int getNumeroDeSementes() {
		return Integer.parseInt(botaoLocalDeSementes.getText().toString());
	}

	public void setNumeroDeSementes(int numeroDeSementes) {
		botaoLocalDeSementes.setText(String.valueOf(numeroDeSementes));
	}

	public void adicionarSementes(int numeroDeSementesAdicionar) {
		int numSementesAtual = Integer.parseInt(botaoLocalDeSementes.getText().toString());
		botaoLocalDeSementes.setText(String.valueOf(numSementesAtual + numeroDeSementesAdicionar));
	}
}
