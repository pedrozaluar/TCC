package br.com.uniriotec.mancala.model;

import android.widget.Button;

/**
 * Classe que representa um local que possa conter sementes no jogo Mancala (como um repositório ou cavidade)
 */
public class LocalDeSementes {

	protected Button botaoLocalDeSementes;

	/**
	 * Construtor
	 * @param botaoLocalDeSementes - botão da tela relacionado ao local de sementes
	 */
	public LocalDeSementes(Button botaoLocalDeSementes) {
		this.botaoLocalDeSementes = botaoLocalDeSementes;
	}

	/**
	 * Retorna o número de sementes que tem neste local
	 * @return
	 */
	public int getNumeroDeSementes() {
		return Integer.parseInt(botaoLocalDeSementes.getText().toString());
	}

	/**
	 * Muda a quantidade de sementes que tem neste local
	 * @param numeroDeSementes - nova quantidade de sementes no local
	 */
	public void setNumeroDeSementes(int numeroDeSementes) {
		botaoLocalDeSementes.setText(String.valueOf(numeroDeSementes));
	}

	/**
	 * Adiciona no local o número de sementes passado por parâmetro
	 * @param numeroDeSementesAdicionar
	 */
	public void adicionarSementes(int numeroDeSementesAdicionar) {
		int numSementesAtual = getNumeroDeSementes();
		setNumeroDeSementes(numSementesAtual + numeroDeSementesAdicionar);
	}
}
