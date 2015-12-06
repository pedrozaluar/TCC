package br.com.uniriotec.mancala.model;

import android.widget.Button;

/**
 * Classe que representa uma cavidade no jogo Mancala
 */
public class Cavidade extends LocalDeSementes {

	public Cavidade(Button botaoCavidade) {
		super(botaoCavidade);
	}

	public void habilitar() {
		botaoLocalDeSementes.setEnabled(true);
	}

	public void desabilitar() {
		botaoLocalDeSementes.setEnabled(false);
	}

	public int pegarSementes() {
		int numSementes = getNumeroDeSementes();
		botaoLocalDeSementes.setText("0");
		return numSementes;
	}

	public boolean temSementes() {
		return getNumeroDeSementes() != 0;
	}

	public int getNumero() {
		String tag = botaoLocalDeSementes.getTag().toString();
		char ultimoCaractereDaTag = tag.charAt(tag.length()-1);
		return Character.getNumericValue(ultimoCaractereDaTag);
//      OU:
//		String textoTagCavidade = cavidade.getTag().toString().split("-")[1];
//		String strNumeroCavidade = textoTagCavidade.replace("cavidade", "");
//		return Integer.parseInt(strNumeroCavidade);
	}

	public String getTag() {
		return botaoLocalDeSementes.getTag().toString();
	}
}
