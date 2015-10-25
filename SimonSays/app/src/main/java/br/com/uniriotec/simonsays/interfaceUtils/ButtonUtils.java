package br.com.uniriotec.simonsays.interfaceUtils;

import android.os.Handler;
import android.widget.Button;

import br.com.uniriotec.simonsays.util.Lista;

/**
 * Classe de utilidades para botões
 */
public class ButtonUtils {

	private static final int MILLISEGUNDOS_PISCAR_BOTAO = 1000;
	private static final int MILLISEGUNDOS_ESPERAR_PISCAR_BOTAO = 500;

	/**
	 * Pisca os botões na sequência que eles estão na lista 'listaBotoes'
	 * Atenção: Método Assíncrono
	 * @param listaBotoes
	 * @param configuracaoPisque
	 */
	public static void piscarBotoes(Lista<Button> listaBotoes, ConfiguracaoPisqueBotao configuracaoPisque) {
		piscarBotoes(listaBotoes, 0, configuracaoPisque);
	}

	/**
	 * Pisca os botoes a partir do indice "indiceBotaoInicioPiscar" na lista "listaBotoes"
	 * O 'piscar' do botão é executado conforme a configuração 'configuracaoPisque' passada
	 * @param listaBotoes
	 * @param indiceBotaoInicioPiscar
	 * @param configuracaoPisque
	 */
	private static void piscarBotoes(final Lista<Button> listaBotoes, final int indiceBotaoInicioPiscar, final ConfiguracaoPisqueBotao configuracaoPisque) {

		// marca o botão para acender ao final do método
		configuracaoPisque.acender(listaBotoes.get(indiceBotaoInicioPiscar));

		// chama método assíncrono para executar daqui a X milissegundos para apagar o botão e acender os próximos botões
		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				// marca o botão para "apagar" ao final do método
				configuracaoPisque.apagar(listaBotoes.get(indiceBotaoInicioPiscar));

				// Continua piscando os próximos botões ou, se terminou, habilita para o jogador fazer a jogada
				int proximoIndiceBotaoPiscar = indiceBotaoInicioPiscar + 1;
				boolean temMaisBotoesParaPiscar = proximoIndiceBotaoPiscar < listaBotoes.tamanho();

				if (temMaisBotoesParaPiscar) {
					esperarParaPiscar(listaBotoes, proximoIndiceBotaoPiscar, configuracaoPisque);
				} else {
					configuracaoPisque.executarAposPiscarBotoes();
				}
			}
		}, MILLISEGUNDOS_PISCAR_BOTAO);
	}

	/**
	 * Espera um tempo para piscar o próximo botão da lista
	 * @param listaBotoesPiscar
	 * @param indiceBotaoInicioPiscar
	 * @param configuracaoPisque
	 */
	private static void esperarParaPiscar(final Lista<Button> listaBotoesPiscar, final int indiceBotaoInicioPiscar, final ConfiguracaoPisqueBotao configuracaoPisque) {
		new Handler().postDelayed(new Runnable() {
			public void run() {
				piscarBotoes(listaBotoesPiscar, indiceBotaoInicioPiscar, configuracaoPisque);
			}
		}, MILLISEGUNDOS_ESPERAR_PISCAR_BOTAO);
	}
}
