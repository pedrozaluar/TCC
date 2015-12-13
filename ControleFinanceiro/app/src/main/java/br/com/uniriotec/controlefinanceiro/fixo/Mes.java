package br.com.uniriotec.controlefinanceiro.fixo;

/**
 * Enum que representa os meses do ano
 */
public enum Mes {
	JANEIRO(1, "Janeiro"),
	FEVEREIRO(2, "Fevereiro"),
	MARCO(3, "Março"),
	ABRIL(4, "Abril"),
	MAIO(5, "Maio"),
	JUNHO(6, "Junho"),
	JULHO(7, "Julho"),
	AGOSTO(8, "Agosto"),
	SETEMBRO(9, "Setembro"),
	OUTUBRO(10, "Outubro"),
	NOVEMBRO(11, "Novembro"),
	DEZEMBRO(12, "Dezembro");

	private int numero;
	private String descricao;

	private Mes(int numero, String descricao) {
		this.numero = numero;
		this.descricao = descricao;
	}

	public int getNumero() {
		return numero;
	}

	public String getDescricao() {
		return descricao;
	}

	public Mes obterMesPeloNumero(int numero) {
		for (Mes mes : Mes.values()) {
			if (numero == mes.getNumero())
				return mes;
		}
		return null;
	}

	public Mes obterMesSeguinte() {
		if (this == DEZEMBRO) {
			return JANEIRO;
		} else {
			return obterMesPeloNumero(numero+1);
		}
	}
}
