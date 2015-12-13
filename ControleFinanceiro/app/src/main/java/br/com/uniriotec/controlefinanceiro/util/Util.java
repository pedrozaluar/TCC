package br.com.uniriotec.controlefinanceiro.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * Classe de utilidades
 */
public class Util {

	public static String toString(BigDecimal valor) {
		return new DecimalFormat("#,##").format(valor);
	}

	public static String obterDescricaoValor(Integer valor) {
		if (valor == null)
			return "";
		else
			return valor.toString();
	}

	public static String obterDescricaoValor(BigDecimal valor) {
		if (valor == null)
			return "";
		else
			return toString(valor);
	}
}
