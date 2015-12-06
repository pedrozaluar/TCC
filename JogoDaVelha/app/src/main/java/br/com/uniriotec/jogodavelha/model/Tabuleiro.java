package br.com.uniriotec.jogodavelha.model;

/**
 * Interface que representa o tabuleiro do jogo da velha
 */
public interface Tabuleiro {
	public boolean completouSequenciaDeSimbolos();
	public boolean existemMaisMovimentos();
	public void reiniciar();
}
