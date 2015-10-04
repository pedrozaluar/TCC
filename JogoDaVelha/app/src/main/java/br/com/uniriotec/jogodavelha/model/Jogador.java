package br.com.uniriotec.jogodavelha.model;

/**
 * Created by PedroLZ on 29/09/2015.
 */
public class Jogador {
	private String nome;
	private String simbolo;

	public Jogador(String nome, String simbolo) {
		this.nome = nome;
		this.simbolo = simbolo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSimbolo() {
		return simbolo;
	}

	public void setSimbolo(String simbolo) {
		this.simbolo = simbolo;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Jogador jogador = (Jogador) o;

		if (!nome.equals(jogador.nome)) return false;
		if (!simbolo.equals(jogador.simbolo)) return false;

		return true;
	}
}
