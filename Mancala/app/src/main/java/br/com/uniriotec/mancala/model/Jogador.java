package br.com.uniriotec.mancala.model;

import android.util.Log;

/**
 * Classe que representa um jogador no jogo Mancala
 */
public class Jogador {

	private int id;
	private String nome;
	private Cavidade[] cavidades;
	private Repositorio repositorio;

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

	public Cavidade[] getCavidades() {
		return cavidades;
	}

	public void setCavidades(Cavidade[] cavidades) {
		this.cavidades = cavidades;
	}

	public Repositorio getRepositorio() {
		return repositorio;
	}

	public void setRepositorio(Repositorio repositorio) {
		this.repositorio = repositorio;
	}

	public int pegarSementesDaCavidade(int numeroDaCavidade) {
		Cavidade cavidade = obterCavidadePeloNumero(numeroDaCavidade);
		return cavidade.pegarSementes();
	}

	public Cavidade obterCavidadePeloNumero(int numeroDaCavidade) {
		for (Cavidade cavidade : cavidades) {
			if (cavidade.getNumero() == numeroDaCavidade) {
				return cavidade;
			}
		}
		Log.e("ERRO", "Tentou obter cavidade com número inválido");
		return null;
	}

	public int obterTotalDeSementes() {
		return obterTotalSementesDasCavidades() + repositorio.getNumeroDeSementes();
	}

	public int obterTotalSementesDasCavidades() {
		int totalSementes = 0;
		for (Cavidade cavidade : cavidades) {
			totalSementes += cavidade.getNumeroDeSementes();
		}
		return totalSementes;
	}

	public boolean ehDonoDaCavidade(Cavidade cavidade) {
		return cavidade.getTag().startsWith("jogador" + id);
	}
}
