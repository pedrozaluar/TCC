package br.com.uniriotec.controlefinanceiro.interfaceUtils;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.widget.Toast;

/**
 * Classe de utilidades para mensagens
 */
public class MensagemUtils {

	 /**
	 * Abre um diálogo simples na tela e um botão "OK", que quando pressionado faz o diálogo sumir
	 * OBS: Método assíncrono. Ou seja, após abrir o diálogo continua executando as próximas linhas de código.
	 * @param context - Activity que chamou o diálogo (ex: MinhaActivity.this)
	 * @param titulo - Titulo do diálogo
	 * @param mensagem - Mensagem do diálogo
	 */
	public static void mostrarCaixaDialogoSimples(Context context, String titulo, String mensagem) {

		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		builder.setTitle(titulo);
		builder.setMessage(mensagem);
		builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int id) {
				dialog.dismiss();
			}
		});

		AlertDialog alertDialog = builder.create();
		alertDialog.show();
	}

	/**
	 * Mostra uma mensagem por um curto período na tela e depois desaparece
	 * @param context - MyActivity.class
	 * @param mensagem
	 */
	public static void mostrarMensagemRapida(Context context, String mensagem) {
		Toast.makeText(context, mensagem, Toast.LENGTH_SHORT).show();
	}
}
