package br.com.uniriotec.controlefinanceiro.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import br.com.uniriotec.controlefinanceiro.fixo.Constantes;

/**
 * Classe de utilidades
 */
public class Util {

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
			return new DecimalFormat("#,##0.00").format(valor);
//		    return NumberFormat.getCurrencyInstance().format(valor);
	}

	public static String obterDescricaoValorEdicao(BigDecimal valor) {
		if (valor == null)
			return "";
		else
			return new DecimalFormat("0.00").format(valor).replace(",", ".");
	}

	public static BigDecimal toBigDecimal(String valorStr) {
		BigDecimal valor = new BigDecimal(valorStr.replace(",", "."));
		valor = valor.setScale(Constantes.BIGDECIMAL_SCALE, Constantes.BIGDECIMAL_ROUNDING_MODE);
		return valor;
	}
}
