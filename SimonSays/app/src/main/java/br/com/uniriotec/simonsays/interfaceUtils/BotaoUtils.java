package br.com.uniriotec.simonsays.interfaceUtils;

import android.os.Handler;
import android.widget.Button;

import br.com.uniriotec.simonsays.util.Lista;

/**
 * Classe de utilidades para botões
 */
public class BotaoUtils {

	private static final int MILLISEGUNDOS_PISCAR_BOTAO = 1000;
	private static final int MILLISEGUNDOS_ESPERAR_PISCAR_BOTAO = 500;

	/**
	 * Pisca os botões na sequência que eles estão na lista 'listaBotoes'
	 * Atenção: Método Assíncrono
	 * @param arrayBotoes
	 * @param configuracaoPisque
	 */
	public static void piscarBotoes(Button[] arrayBotoes, ConfiguracaoPisqueBotao configuracaoPisque) {
		piscarBotoes(arrayBotoes, 0, configuracaoPisque);
	}

	/**
	 * Pisca os botoes a partir do indice "indiceBotaoInicioPiscar" na lista "listaBotoes"
	 * O 'piscar' do botão é executado conforme a configuração 'configuracaoPisque' passada
	 * @param arrayBotoes
	 * @param indiceBotaoInicioPiscar
	 * @param configuracaoPisque
	 */
	private static void piscarBotoes(final Button[] arrayBotoes, final int indiceBotaoInicioPiscar, final ConfiguracaoPisqueBotao configuracaoPisque) {

		// marca o botão para acender ao final do método
		configuracaoPisque.acender(arrayBotoes[indiceBotaoInicioPiscar]);

		// chama método assíncrono para executar daqui a X milissegundos para apagar o botão e acender os próximos botões
		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				// marca o botão para "apagar" ao final do método
				configuracaoPisque.apagar(arrayBotoes[indiceBotaoInicioPiscar]);

				// Continua piscando os próximos botões ou, se terminou, habilita para o jogador fazer a jogada
				int proximoIndiceBotaoPiscar = indiceBotaoInicioPiscar + 1;
				boolean temMaisBotoesParaPiscar = proximoIndiceBotaoPiscar < arrayBotoes.length;

				if (temMaisBotoesParaPiscar) {
					esperarParaPiscar(arrayBotoes, proximoIndiceBotaoPiscar, configuracaoPisque);
				} else {
					configuracaoPisque.executarAposPiscarBotoes();
				}
			}
		}, MILLISEGUNDOS_PISCAR_BOTAO);
	}

	/**
	 * Espera um tempo para piscar o próximo botão da lista
	 * @param arrayBotoes
	 * @param indiceBotaoInicioPiscar
	 * @param configuracaoPisque
	 */
	private static void esperarParaPiscar(final Button[] arrayBotoes, final int indiceBotaoInicioPiscar, final ConfiguracaoPisqueBotao configuracaoPisque) {
		new Handler().postDelayed(new Runnable() {
			public void run() {
				piscarBotoes(arrayBotoes, indiceBotaoInicioPiscar, configuracaoPisque);
			}
		}, MILLISEGUNDOS_ESPERAR_PISCAR_BOTAO);
	}
}
