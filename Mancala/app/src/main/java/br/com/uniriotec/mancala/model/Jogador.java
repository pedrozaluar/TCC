package br.com.uniriotec.mancala.model;

import android.widget.Button;

/**
 * Classe que representa um jogador no jogo Mancala
 */
public class Jogador {

	private int id;
	private String nome;
	private Button[] cavidades;
	private Button repositorio;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Button[] getCavidades() {
		return cavidades;
	}

	public void setCavidades(Button[] cavidades) {
		this.cavidades = cavidades;
	}

	public Button getRepositorio() {
		return repositorio;
	}

	public void setRepositorio(Button repositorio) {
		this.repositorio = repositorio;
	}

	public int pegarSementesDaCavidade(int numeroDaCavidade) {
		Button cavidade = obterCavidadePeloNumero(numeroDaCavidade);
		int numSementes = Integer.parseInt(cavidade.getText().toString());
		cavidade.setText("0");
		return numSementes;
	}

	private Button obterCavidadePeloNumero(int numeroDaCavidade) {
		int indiceDaCavidade = numeroDaCavidade - 1;
		return cavidades[indiceDaCavidade];
	}

	public int getTotalSementes() {
		return getTotalSementesCavidades() + Integer.parseInt(repositorio.getText().toString());
	}

	public int getTotalSementesCavidades() {
		int totalSementes = 0;
		for (Button cavidade : cavidades) {
			totalSementes += Integer.parseInt(cavidade.getText().toString());
		}
		return totalSementes;
	}
}
