package br.com.uniriotec.controlefinanceiro.model;

import java.io.Serializable;
import java.math.BigDecimal;

import br.com.uniriotec.controlefinanceiro.util.InterfaceUtils;

/**
 * Classe abstrata que representa uma movimentação (genérica)
 */
public abstract class Movimentacao implements Serializable, Comparable {

	private Integer id;
	private Integer dia;
	private String descricao;
	private boolean valorPositivo;
	private boolean efetuada;

	public Movimentacao() {
		this.valorPositivo = false;
		this.efetuada = true;
	}

	public abstract BigDecimal getValor();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getValorDescr() {
		return InterfaceUtils.obterDescricaoValor(getValor());
	}

	public String getValorEdicao() {
		return InterfaceUtils.obterDescricaoValorEdicao(getValor());
	}

	public Integer getDia() {
		return dia;
	}

	public String getDiaDescr() {
		return InterfaceUtils.obterDescricaoValor(dia);
	}

	public void setDia(Integer dia) {
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

	@Override
	public int compareTo(Object another) {
		Movimentacao movimentacao2 = (Movimentacao) another;
		return dia - movimentacao2.getDia();
	}
}
