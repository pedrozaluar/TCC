package br.com.uniriotec.controlefinanceiro.model;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Classe abstrata que representa uma movimentação (genérica)
 */
public abstract class Movimentacao implements Serializable {

	private int dia;
	private String descricao;
	private boolean valorPositivo;
	private boolean efetuada;

	public Movimentacao() {
		this.dia = 1;
		this.valorPositivo = false;
		this.efetuada = true;
	}

	public abstract BigDecimal getValor();

	public int getDia() {
		return dia;
	}

	public void setDia(int dia) {
		this.dia = dia;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public boolean isValorPositivo() {
		return valorPositivo;
	}

	public void setValorPositivo(boolean valorPositivo) {
		this.valorPositivo = valorPositivo;
	}

	public boolean isEfetuada() {
		return efetuada;
	}

	public void setEfetuada(boolean efetuada) {
		this.efetuada = efetuada;
	}
}
