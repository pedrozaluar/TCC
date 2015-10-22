package br.com.uniriotec.simonsays.util;

/**
 * Classe para representar uma lista de objetos
 */
public interface Lista<T> {

	/**
	 * Adiciona um item na lista
	 * @param item
	 */
	public void add(T item);

	/**
	 * Retorna o item da lista que está no indice 'indice' da lista
	 * @param indice
	 * @return T
	 */
	public T get(int indice);

	/**
	 * Obtem a lista em forma de array
	 * @return
	 */
	public T[] obterArray();

	/**
	 * Retorna o número de itens da lista
	 * @return tamanho da lista
	 */
	public int tamanho();

	/**
	 * Confere se a lista está cheia
	 * @return true, se a lista estiver cheia, senão false
	 */
	public boolean estahCheia();

	/**
	 * Confere se a lista está vazia
	 * @return true, se a lista estiver vazia, senão false
	 */
	public boolean estahVazia();

	/**
	 * Limpa a lista, removendo todos os seus itens
	 */
	public void limpar();
}
