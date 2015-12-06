package br.com.uniriotec.mancala.model;

import android.widget.Button;

/**
 * Classe que representa um repositório no jogo Mancala
 */
public class Repositorio extends LocalDeSementes {

	/**
	 * Construtor
	 * @param botaoRepositorio - botão da tela relacionado ao repositório
	 */
	public Repositorio(Button botaoRepositorio) {
		super(botaoRepositorio);
	}
}
