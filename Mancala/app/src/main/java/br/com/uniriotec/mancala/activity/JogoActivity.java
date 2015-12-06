package br.com.uniriotec.mancala.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import br.com.uniriotec.mancala.R;
import br.com.uniriotec.mancala.interfaceUtils.MensagemUtils;
import br.com.uniriotec.mancala.model.Jogador;


public class JogoActivity extends ActionBarActivity {

	private static final int NUM_SEMENTES_INICIAL_POR_CAVIDADE  = 4;
	private static final int TOTAL_CAVIDADES_POR_JOGADOR = 6;

	private Jogador jogador1;
	private Jogador jogador2;
	private Jogador jogadorCorrente;

	private Button ultimoLocalOndeSemeou = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_jogo);
	}

	@Override
	protected void onStart() {
		super.onStart();
		armazenarElementosDaTela();
		habilitarBotoes(jogador1.getCavidades(), false);
		habilitarBotoes(jogador2.getCavidades(), false);
	}

	private void armazenarElementosDaTela() {
		View tabuleiro = findViewById(R.id.tabuleiro);

		Button[] cavidadesJogador1 = new Button[6];
		cavidadesJogador1[0] = (Button) tabuleiro.findViewWithTag("jogador1-cavidade1");
		cavidadesJogador1[1] = (Button) tabuleiro.findViewWithTag("jogador1-cavidade2");
		cavidadesJogador1[2] = (Button) tabuleiro.findViewWithTag("jogador1-cavidade3");
		cavidadesJogador1[3] = (Button) tabuleiro.findViewWithTag("jogador1-cavidade4");
		cavidadesJogador1[4] = (Button) tabuleiro.findViewWithTag("jogador1-cavidade5");
		cavidadesJogador1[5] = (Button) tabuleiro.findViewWithTag("jogador1-cavidade6");

		Button[] cavidadesJogador2 = new Button[6];
		cavidadesJogador2[0] = (Button) tabuleiro.findViewWithTag("jogador2-cavidade1");
		cavidadesJogador2[1] = (Button) tabuleiro.findViewWithTag("jogador2-cavidade2");
		cavidadesJogador2[2] = (Button) tabuleiro.findViewWithTag("jogador2-cavidade3");
		cavidadesJogador2[3] = (Button) tabuleiro.findViewWithTag("jogador2-cavidade4");
		cavidadesJogador2[4] = (Button) tabuleiro.findViewWithTag("jogador2-cavidade5");
		cavidadesJogador2[5] = (Button) tabuleiro.findViewWithTag("jogador2-cavidade6");

		jogador1 = new Jogador();
		jogador1.setId(1);
		jogador1.setNome(getString(R.string.nome_jogador_1));
		jogador1.setCavidades(cavidadesJogador1);
		jogador1.setRepositorio((Button) findViewById(R.id.repositorioJogador1));

		jogador2 = new Jogador();
		jogador2.setId(2);
		jogador2.setNome(getString(R.string.nome_jogador_2));
		jogador2.setCavidades(cavidadesJogador2);
		jogador2.setRepositorio((Button) findViewById(R.id.repositorioJogador2));
	}

	private void habilitarBotoes(Button[] botoes, boolean habilitar) {
		for (Button botao : botoes) {
			botao.setEnabled(habilitar);
		}
	}

	public void onClickButtonIniciar(View view) {
		preencherSementesIniciais();
		mostrarBotaoIniciar(false);
		jogadorCorrente = jogador1;
		informarJogadorCorrente();
		habilitarBotoesDoJogadorCorrente();
	}

	private void preencherSementesIniciais() {
		preencherSementesIniciaisDoJogador(jogador1);
		preencherSementesIniciaisDoJogador(jogador2);
	}

	private void preencherSementesIniciaisDoJogador(Jogador jogador) {
		for (Button cavidade : jogador.getCavidades()) {
			cavidade.setText(String.valueOf(NUM_SEMENTES_INICIAL_POR_CAVIDADE));
		}
		jogador.getRepositorio().setText("0");
	}

	private void mostrarBotaoIniciar(boolean mostrar) {
		Button botaoIniciar = (Button) findViewById(R.id.botao_iniciar);
		TextView txtInfoJogadorCorrente = (TextView) findViewById(R.id.txtInfoJogadorCorrente);
		TextView txtNomeJogadorCorrente = (TextView) findViewById(R.id.txtNomeJogadorCorrente);

		mostrarElementoDaTela(botaoIniciar, mostrar);
		mostrarElementoDaTela(txtInfoJogadorCorrente, !mostrar);
		mostrarElementoDaTela(txtNomeJogadorCorrente, !mostrar);
	}

	private void mostrarElementoDaTela(View elementoDaTela, boolean mostrar) {
		if (mostrar)
			elementoDaTela.setVisibility(View.VISIBLE);
		else
			elementoDaTela.setVisibility(View.GONE);
	}

	private void habilitarBotoesDoJogadorCorrente() {
		habilitarBotoes(jogadorCorrente.getCavidades(), true);
		habilitarBotoes(getJogadorEmEspera().getCavidades(), false);
	}

	private Jogador getJogadorEmEspera() {
		if (jogadorCorrente.getId() == jogador1.getId()) {
			return jogador2;
		} else {
			return jogador1;
		}
	}

	private Jogador obterJogadorPeloId(int id) {
		if (id == jogador1.getId()) {
			return jogador1;
		} else if (id == jogador2.getId()) {
			return jogador2;
		} else {
			// Não deve ocorrer. Erro na lógica do programa.
			Log.e("ERRO", "Tentou obter jogador com ID inválido");
			return null;
		}
	}

	public void onClickButtonCavidade(View view) {
		Button cavidadeClicada = (Button) view;
		// VALIDA CLIQUE
		if (cavidadeClicada.getText().equals("0")) {
			MensagemUtils.mostrarMensagemRapida(JogoActivity.this, "Jogador deve clicar em uma cavidade que tenha sementes");
			return;
		}

		// COLHE E SEMEIA
		int numeroDaCavidadeClicada = obterNumeroDaCavidade(cavidadeClicada);
		int numSementes = jogadorCorrente.pegarSementesDaCavidade(numeroDaCavidadeClicada);
		semear(numSementes, cavidadeClicada);

		// ESPECIAL: pega semente da casa do oponente
		if (ultimoLocalOndeSemeou.getTag() != null &&
			ultimoLocalOndeSemeou.getTag().toString().startsWith("jogador"+jogadorCorrente.getId()+"-cavidade") &&
			ultimoLocalOndeSemeou.getText().equals("1")) {
			// Adicinou por último em uma cavidade do jogador corrente e que estava vazia:
			// Então pega as sementes da cavidade da frente para o seu repositório.

			int numeroDaCavidade = obterNumeroDaCavidade(ultimoLocalOndeSemeou);
			int numSementesCavidadeDaFrente = getJogadorEmEspera().pegarSementesDaCavidade(numeroDaCavidade);
			addSementes(jogadorCorrente.getRepositorio(), numSementesCavidadeDaFrente);
		}

		//VALIDA FINAL DA PARTIDA
		if (terminouPartida()) {
			habilitarBotoes(jogador1.getCavidades(), false);
			habilitarBotoes(jogador2.getCavidades(), false);
			mostrarBotaoIniciar(true);

			Jogador vencedor = getJogadorComMaisPontos();
			String mensagem = "O jogador " + vencedor.getNome() + " venceu com " + vencedor.getTotalSementes() + " sementes.";
			MensagemUtils.mostrarCaixaDialogoSimples(JogoActivity.this, "Resultado", mensagem);

		} // SENÃO: PREPARA PRÓXIMA RODADA
		else if (!ultimoLocalOndeSemeou.equals(jogadorCorrente.getRepositorio())) {
			jogadorCorrente = getJogadorEmEspera();
			informarJogadorCorrente();
			habilitarBotoesDoJogadorCorrente();
		}

		ultimoLocalOndeSemeou = null;
	}

	private Jogador getJogadorComMaisPontos() {
		if (jogador1.getTotalSementes() > jogador2.getTotalSementes()) {
			return jogador1;
		} else {
			return jogador2;
		}
	}

	private boolean terminouPartida() {
		return jogador1.getTotalSementesCavidades() == 0 || jogador2.getTotalSementesCavidades() == 0;
	}

	private void semear(int numSementes, Button cavidadeClicada) {
		int idJogadorAdicionarSemente = jogadorCorrente.getId();
		ultimoLocalOndeSemeou = cavidadeClicada; // o local anterior ao que vou semear primeiro, é justamente a cavidade clicada..

		while (numSementes > 0) {
			Button localAdicionarSemente;

			if (ultimoLocalOndeSemeou.equals(jogadorCorrente.getRepositorio())) {
				Jogador jogadorEmEspera = getJogadorEmEspera();
				idJogadorAdicionarSemente = jogadorEmEspera.getId();
				localAdicionarSemente = obterPrimeiraCavidadeDoJogador(jogadorEmEspera);

			} else if (isUltimaCavidadeDoJogador(idJogadorAdicionarSemente, obterNumeroDaCavidade(ultimoLocalOndeSemeou))) {
				if (idJogadorAdicionarSemente == jogadorCorrente.getId()) {
					localAdicionarSemente = jogadorCorrente.getRepositorio();
				} else {
					// Pula o repositório do jogador em espera e add semente na 1ª cavidade do jogador corrente
					idJogadorAdicionarSemente = jogadorCorrente.getId();
					localAdicionarSemente = obterPrimeiraCavidadeDoJogador(jogadorCorrente);
				}

			} else {
				int numeroCavidadeAddSemente = obterProximaCavidade(idJogadorAdicionarSemente, obterNumeroDaCavidade(ultimoLocalOndeSemeou));
				int indiceCavidadeAddSemente = numeroCavidadeAddSemente - 1;
				localAdicionarSemente = obterJogadorPeloId(idJogadorAdicionarSemente).getCavidades()[indiceCavidadeAddSemente];
			}

			numSementes--;
			addSementes(localAdicionarSemente, 1);
			ultimoLocalOndeSemeou = localAdicionarSemente;
		}
	}

	private Button obterPrimeiraCavidadeDoJogador(Jogador jogador) {
		if (jogador.getId() == jogador1.getId()) {
			return jogador.getCavidades()[0];
		} else {
			return jogador.getCavidades()[TOTAL_CAVIDADES_POR_JOGADOR-1];
		}
	}

	private int obterNumeroDaCavidade(Button cavidade) {
		String tag = cavidade.getTag().toString();
		return Character.getNumericValue(tag.charAt(tag.length()-1));
//      OU:
//		String textoTagCavidade = cavidade.getTag().toString().split("-")[1];
//		String strNumeroCavidade = textoTagCavidade.replace("cavidade", "");
//		return Integer.parseInt(strNumeroCavidade);
	}

	private int obterProximaCavidade(int idJogador, int numeroDaCavidade) {
		if (idJogador == jogador1.getId()) {
			return numeroDaCavidade+1;
		} else {
			return numeroDaCavidade-1;
		}
	}

	private boolean isUltimaCavidadeDoJogador(int idJogador, int numeroDaCavidade) {
		return (idJogador == jogador1.getId() && numeroDaCavidade == TOTAL_CAVIDADES_POR_JOGADOR) ||
			   (idJogador == jogador2.getId() && numeroDaCavidade == 1);
	}

	private void addSementes(Button local, int numSementesAdicionar) {
		int numSementes = Integer.parseInt(local.getText().toString());
		numSementes += numSementesAdicionar;
		local.setText(String.valueOf(numSementes));
	}

	private void informarJogadorCorrente() {
		TextView txtNomeJogadorCorrente = (TextView) findViewById(R.id.txtNomeJogadorCorrente);
		txtNomeJogadorCorrente.setText(jogadorCorrente.getNome());
	}
}
