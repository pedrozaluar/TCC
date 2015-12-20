package br.com.uniriotec.controlefinanceiro.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import br.com.uniriotec.controlefinanceiro.R;
import br.com.uniriotec.controlefinanceiro.fixo.Constantes;
import br.com.uniriotec.controlefinanceiro.model.Movimentacao;

/**
 * Classe de utilidades
 */
public class InterfaceUtils {

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

	public static int obterIdCorDoCampoValor(Movimentacao movimentacao) {
		return obterIdCorDoCampoValor(movimentacao.isValorPositivo(), movimentacao.isEfetuada());
	}

	public static int obterIdCorDoCampoValor(boolean isValorPositivo, boolean isEfetuada) {
		if (isValorPositivo) {
			if (isEfetuada) {
				return R.color.cor_valor_positivo_efetuado;
			} else {
				return R.color.cor_valor_positivo_nao_efetuado;
			}
		} else {
			if (isEfetuada) {
				return R.color.cor_valor_negativo_efetuado;
			} else {
				return R.color.cor_valor_negativo_nao_efetuado;
			}
		}
	}
}
