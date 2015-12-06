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

	/**
	 * Retira e retorna as sementes da cavidade de número 'numeroDaCavidade'
	 * @param numeroDaCavidade - Número da cavidade que devem ser apanhadas as sementes
	 * @return número de sementes que foram apanhadas
	 */
	public int pegarSementesDaCavidade(int numeroDaCavidade) {
		Cavidade cavidade = obterCavidadePeloNumero(numeroDaCavidade);
		return cavidade.pegarSementes();
	}

	/**
	 * Obtém a cavidade de número 'numeroDaCavidade' deste jogador
	 * @param numeroDaCavidade
	 * @return cavidade de número 'numeroDaCavidade'
	 */
	public Cavidade obterCavidadePeloNumero(int numeroDaCavidade) {
		for (Cavidade cavidade : cavidades) {
			if (cavidade.getNumero() == numeroDaCavidade) {
				return cavidade;
			}
		}
		Log.e("ERRO", "Tentou obter cavidade com número inválido");
		return null;
	}

	/**
	 * Retorna o total de sementes do jogador, somando as sementes das cavidades com as do repositório
	 * @return
	 */
	public int obterTotalDeSementes() {
		return obterTotalSementesDasCavidades() + repositorio.getNumeroDeSementes();
	}

	/**
	 * Retorna o total de sementes encontradas nas cavidades do jogador
	 * @return
	 */
	public int obterTotalSementesDasCavidades() {
		int totalSementes = 0;
		for (Cavidade cavidade : cavidades) {
			totalSementes += cavidade.getNumeroDeSementes();
		}
		return totalSementes;
	}

	/**
	 * Indica se a cavidade passada por parâmetro é deste jogador
	 * @param cavidade
	 * @return
	 */
	public boolean ehDonoDaCavidade(Cavidade cavidade) {
		return cavidade.getTag().startsWith("jogador" + id);
	}

	/**
	 * Habilita ou desabilita as cavidades do jogador
	 * @param habilitar - se true, habilita, senão desabilita
	 */
	public void habilitarCavidades(boolean habilitar) {
		for (Cavidade cavidade : cavidades) {
			if (habilitar) {
				cavidade.habilitar();
			} else {
				cavidade.desabilitar();
			}
		}
	}
}
