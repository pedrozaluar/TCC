package br.com.uniriotec.mancala.model;

import android.widget.Button;

/**
 * Classe que representa uma cavidade no jogo Mancala
 */
public class Cavidade extends LocalDeSementes {

	/**
	 * Construtor
	 * @param botaoCavidade - botão da tela relacionado a cavidade
	 */
	public Cavidade(Button botaoCavidade) {
		super(botaoCavidade);
	}

	/**
	 * Habilita o clique no botão que representa esta cavidade
	 */
	public void habilitar() {
		botaoLocalDeSementes.setEnabled(true);
	}

	/**
	 * Desabilita o clique no botão que representa esta cavidade
	 */
	public void desabilitar() {
		botaoLocalDeSementes.setEnabled(false);
	}

	/**
	 * Retira e retorna as sementes dessa cavidade
	 * @return número de sementes que foram apanhadas
	 */
	public int pegarSementes() {
		int numSementes = getNumeroDeSementes();
		botaoLocalDeSementes.setText("0");
		return numSementes;
	}

	/**
	 * Indica se tem sementes nesta cavidade
	 * @return
	 */
	public boolean temSementes() {
		return getNumeroDeSementes() != 0;
	}

	/**
	 * Obtém o número da cavidade (obs: o número da cavidade é o último caractere da tag deste elemento no xml da tela)
	 * @return
	 */
	public int getNumero() {
		String tag = getTag();
		char ultimoCaractereDaTag = tag.charAt(tag.length()-1);
		return Character.getNumericValue(ultimoCaractereDaTag);
//      OU:
//		String textoTagCavidade = cavidade.getTag().toString().split("-")[1];
//		String strNumeroCavidade = textoTagCavidade.replace("cavidade", "");
//		return Integer.parseInt(strNumeroCavidade);
	}

	/**
	 * Obtém o texto da tag deste elemento especificado no xml da tela
	 * @return
	 */
	public String getTag() {
		return botaoLocalDeSementes.getTag().toString();
	}
}
