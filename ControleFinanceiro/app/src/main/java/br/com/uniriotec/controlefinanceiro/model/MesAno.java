package br.com.uniriotec.controlefinanceiro.model;

import java.io.Serializable;
import java.util.Calendar;

import br.com.uniriotec.controlefinanceiro.fixo.Mes;

/**
 * Classe que representa um mês de determinado ano
 */
public class MesAno implements Serializable {

	private Mes mes;
	private int ano;

	public MesAno(Mes mes, int ano) {
		this.mes = mes;
		this.ano = ano;
	}

	public Mes getMes() {
		return mes;
	}

	public int getAno() {
		return ano;
	}

	@Override
	public String toString() {
		return mes.getDescricao() + "/" + ano;
	}

	public static MesAno obterMesAnoAtual() {
		Calendar dataAtual = Calendar.getInstance();
		int numeroMesAtual = dataAtual.get(Calendar.MONTH)+1;
		Mes mesAtual = Mes.obterMesPeloNumero(numeroMesAtual);
		int anoAtual = dataAtual.get(Calendar.YEAR);
		return new MesAno(mesAtual, anoAtual);
	}

	public static MesAno obterProximoMesAno(MesAno mesAno) {
		Mes proximoMes = mesAno.getMes().obterMesSeguinte();
		int anoProximoMes = mesAno.getAno();
		if (proximoMes == Mes.JANEIRO) {
			anoProximoMes++;
		}
		return new MesAno(proximoMes, anoProximoMes);
	}
}
