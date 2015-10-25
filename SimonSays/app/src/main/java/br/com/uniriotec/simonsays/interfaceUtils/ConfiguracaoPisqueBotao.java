package br.com.uniriotec.simonsays.interfaceUtils;

import android.widget.Button;

/**
 * Permite a definição personalizada do 'pisque' de um botão
 */
public interface ConfiguracaoPisqueBotao {

	/**
	 * Define como será o realce do botão quando ele for piscar
	 * @param button - botão a ser piscado
	 */
	public void acender(Button button);

	/**
	 * Define como o botão voltará ao normal quando terminar de piscar
	 * @param button - botão a ser apagado
	 */
	public void apagar(Button button);

	/**
	 * Opcional: Define uma execução para quando terminar de piscar todos os botões.
	 */
	public void executarAposPiscarBotoes();
}
