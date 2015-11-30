package br.com.uniriotec.simonsays.interfaceUtils;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

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
}
