package br.com.uniriotec.simonsays.util;

import java.util.Arrays;

/**
 * Classe para representar uma lista de objetos guardados em forma de array
 */
public class ListaDeArray<T> implements Lista<T>{

	private Object[] array;
	private int ultimoIndiceOcupado;

	public ListaDeArray(int tamanhoMaximo) {
		this.array = new Object[tamanhoMaximo];
		ultimoIndiceOcupado = -1;
	}

	@Override
	public void add(T item) {
		ultimoIndiceOcupado++;
		array[ultimoIndiceOcupado] = item;
//		Ou: array[++ultimoIndiceOcupado] = item;
	}

	@Override
	public T get(int indice) {
		if (indice < 0 || indice > ultimoIndiceOcupado) {
			return null;
		} else {
			return (T) array[indice];
		}
	}

	@Override
	public T[] obterArray() {
		int tamanhoArrayRetornado;
		if (ultimoIndiceOcupado < 0) {
			tamanhoArrayRetornado = 0;
		} else {
			tamanhoArrayRetornado = ultimoIndiceOcupado + 1;
		}

		// Retorna uma cópia do array com somente o tamanho utilizado
		return (T[]) Arrays.copyOf(array, tamanhoArrayRetornado);
	}

	@Override
	public int tamanho() {
		return ultimoIndiceOcupado + 1;
	}

	@Override
	public boolean estahCheia() {
		return tamanho() == array.length;
	}

	@Override
	public boolean estahVazia() {
		return tamanho() == 0;
	}

	@Override
	public void limpar() {
		int tamanhoMaximo = array.length;
		this.array = new Object[tamanhoMaximo];
		ultimoIndiceOcupado = -1;
	}
}
