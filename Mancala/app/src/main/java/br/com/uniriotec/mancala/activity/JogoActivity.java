package br.com.uniriotec.mancala.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import br.com.uniriotec.mancala.R;
import br.com.uniriotec.mancala.interfaceUtils.MensagemUtils;
import br.com.uniriotec.mancala.model.Cavidade;
import br.com.uniriotec.mancala.model.Jogador;
import br.com.uniriotec.mancala.model.LocalDeSementes;
import br.com.uniriotec.mancala.model.Repositorio;


public class JogoActivity extends Activity {

	private static final int NUM_SEMENTES_INICIAL_POR_CAVIDADE  = 4;
	private static final int TOTAL_CAVIDADES_POR_JOGADOR = 6;

	private Jogador jogador1;
	private Jogador jogador2;
	private Jogador jogadorCorrente;

	private LocalDeSementes ultimoLocalOndeSemeou = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_jogo);
	}

	@Override
	protected void onStart() {
		super.onStart();
		armazenarElementosDaTela();
		habilitarCavidadesDoJogador(jogador1, false);
		habilitarCavidadesDoJogador(jogador2, false);
	}

	private void armazenarElementosDaTela() {
		View tabuleiro = findViewById(R.id.tabuleiro);

		Cavidade[] cavidadesJogador1 = new Cavidade[6];
		cavidadesJogador1[0] = new Cavidade((Button) tabuleiro.findViewWithTag("jogador1-cavidade1"));
		cavidadesJogador1[1] = new Cavidade((Button) tabuleiro.findViewWithTag("jogador1-cavidade2"));
		cavidadesJogador1[2] = new Cavidade((Button) tabuleiro.findViewWithTag("jogador1-cavidade3"));
		cavidadesJogador1[3] = new Cavidade((Button) tabuleiro.findViewWithTag("jogador1-cavidade4"));
		cavidadesJogador1[4] = new Cavidade((Button) tabuleiro.findViewWithTag("jogador1-cavidade5"));
		cavidadesJogador1[5] = new Cavidade((Button) tabuleiro.findViewWithTag("jogador1-cavidade6"));
		Repositorio repositorioJogador1 = new Repositorio((Button) findViewById(R.id.repositorioJogador1));

		Cavidade[] cavidadesJogador2 = new Cavidade[6];
		cavidadesJogador2[0] = new Cavidade((Button) tabuleiro.findViewWithTag("jogador2-cavidade6"));
		cavidadesJogador2[1] = new Cavidade((Button) tabuleiro.findViewWithTag("jogador2-cavidade5"));
		cavidadesJogador2[2] = new Cavidade((Button) tabuleiro.findViewWithTag("jogador2-cavidade4"));
		cavidadesJogador2[3] = new Cavidade((Button) tabuleiro.findViewWithTag("jogador2-cavidade3"));
		cavidadesJogador2[4] = new Cavidade((Button) tabuleiro.findViewWithTag("jogador2-cavidade2"));
		cavidadesJogador2[5] = new Cavidade((Button) tabuleiro.findViewWithTag("jogador2-cavidade1"));
		Repositorio repositorioJogador2 = new Repositorio((Button) findViewById(R.id.repositorioJogador2));

		jogador1 = new Jogador();
		jogador1.setId(1);
		jogador1.setNome(getString(R.string.nome_jogador_1));
		jogador1.setCavidades(cavidadesJogador1);
		jogador1.setRepositorio(repositorioJogador1);

		jogador2 = new Jogador();
		jogador2.setId(2);
		jogador2.setNome(getString(R.string.nome_jogador_2));
		jogador2.setCavidades(cavidadesJogador2);
		jogador2.setRepositorio(repositorioJogador2);
	}

	private void habilitarCavidadesDoJogador(Jogador jogador, boolean habilitar) {
		for (Cavidade cavidade : jogador.getCavidades()) {
			if (habilitar) {
				cavidade.habilitar();
			} else {
				cavidade.desabilitar();
			}
		}
	}

	public void onClickButtonIniciar(View view) {
		preencherSementesIniciais();
		mostrarBotaoIniciar(false);
		jogadorCorrente = jogador1;
		informarJogadorCorrente();
		habilitarCavidadesDoJogadorCorrente();
	}

	private void preencherSementesIniciais() {
		preencherSementesIniciaisDoJogador(jogador1);
		preencherSementesIniciaisDoJogador(jogador2);
	}

	private void preencherSementesIniciaisDoJogador(Jogador jogador) {
		for (Cavidade cavidade : jogador.getCavidades()) {
			cavidade.setNumeroDeSementes(NUM_SEMENTES_INICIAL_POR_CAVIDADE);
		}
		jogador.getRepositorio().setNumeroDeSementes(0);
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

	private void habilitarCavidadesDoJogadorCorrente() {
		habilitarCavidadesDoJogador(jogadorCorrente, true);
		habilitarCavidadesDoJogador(getJogadorEmEspera(), false);
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
		Cavidade cavidadeClicada = new Cavidade((Button) view);
		// VALIDA CLIQUE
		if (!cavidadeClicada.temSementes()) {
			MensagemUtils.mostrarMensagemRapida(JogoActivity.this, "Jogador deve clicar em uma cavidade que tenha sementes");
			return;
		}

		// COLHE E SEMEIA
		int numSementes = cavidadeClicada.pegarSementes();
		semear(numSementes, cavidadeClicada);

		// ESPECIAL: pega semente da casa do oponente
		if (ultimoLocalOndeSemeou instanceof Cavidade &&
			jogadorCorrente.ehDonoDaCavidade( (Cavidade)ultimoLocalOndeSemeou ) &&
			ultimoLocalOndeSemeou.getNumeroDeSementes() == 1) {
			// Adicinou por último em uma cavidade do jogador corrente e que estava vazia:
			// Então pega as sementes da cavidade da frente para o seu repositório.

			Cavidade cavidade = (Cavidade) ultimoLocalOndeSemeou;
			int numeroDaCavidadeDaFrente = obterNumeroCavidadeDaFrente(cavidade); // Math.abs = módulo/valor absoluto
			int numSementesCavidadeDaFrente = getJogadorEmEspera().pegarSementesDaCavidade(numeroDaCavidadeDaFrente);
			jogadorCorrente.getRepositorio().adicionarSementes(numSementesCavidadeDaFrente);
		}

		// VALIDA FINAL DA PARTIDA
		if (terminouPartida()) {
			habilitarCavidadesDoJogador(jogador1, false);
			habilitarCavidadesDoJogador(jogador2, false);
			mostrarBotaoIniciar(true);

			Jogador vencedor = getJogadorComMaisPontos();
			String mensagem = "O jogador " + vencedor.getNome() + " venceu com " + vencedor.obterTotalDeSementes() + " sementes.";
			MensagemUtils.mostrarCaixaDialogoSimples(JogoActivity.this, "Resultado", mensagem);

		} // SENÃO: PREPARA PRÓXIMA RODADA (ESPECIAL 2: se semeou por último no próprio repositório, não troca de jogador corrente)
		else if (!ultimoLocalOndeSemeou.equals(jogadorCorrente.getRepositorio())) {
			jogadorCorrente = getJogadorEmEspera();
			informarJogadorCorrente();
			habilitarCavidadesDoJogadorCorrente();
		}

		ultimoLocalOndeSemeou = null;
	}

	private int obterNumeroCavidadeDaFrente(Cavidade cavidade) {
		return Math.abs( cavidade.getNumero() - (TOTAL_CAVIDADES_POR_JOGADOR+1) ); // Math.abs(i : int): retorna o módulo/valor absoluto do número
//      OU:
//		switch (cavidade.getNumero()) {
//			case 1: return 6;
//			case 2: return 5;
//			case 3: return 4;
//			case 4: return 3;
//			case 5: return 2;
//			case 6: return 1;
//			default: Log.e("ERRO", "Informou número de cavidade inválido");
//					 return 0;
//		}
	}

	private Jogador getJogadorComMaisPontos() {
		if (jogador1.obterTotalDeSementes() > jogador2.obterTotalDeSementes()) {
			return jogador1;
		} else {
			return jogador2;
		}
	}

	private boolean terminouPartida() {
		return jogador1.obterTotalSementesDasCavidades() == 0 ||
			   jogador2.obterTotalSementesDasCavidades() == 0;
	}

	private void semear(int numSementes, Cavidade cavidadeClicada) {
		int idJogadorAdicionarSemente = jogadorCorrente.getId();
		ultimoLocalOndeSemeou = cavidadeClicada; // o local anterior ao que vou semear primeiro, é justamente a cavidade clicada..

		while (numSementes > 0) {
			LocalDeSementes localAdicionarSemente;

			if (ultimoLocalOndeSemeou.equals(jogadorCorrente.getRepositorio())) {
				Jogador jogadorEmEspera = getJogadorEmEspera();
				idJogadorAdicionarSemente = jogadorEmEspera.getId();
				localAdicionarSemente = jogadorEmEspera.obterCavidadePeloNumero(1);

			} else {
				Cavidade cavidade = (Cavidade) ultimoLocalOndeSemeou;

				if (cavidade.getNumero() == TOTAL_CAVIDADES_POR_JOGADOR) {
					if (idJogadorAdicionarSemente == jogadorCorrente.getId()) {
						localAdicionarSemente = jogadorCorrente.getRepositorio();
					} else {
						// Pula o repositório do jogador em espera e add semente na 1ª cavidade do jogador corrente
						idJogadorAdicionarSemente = jogadorCorrente.getId();
						localAdicionarSemente = jogadorCorrente.obterCavidadePeloNumero(1);
					}

				} else {
					int proximaCavidade = cavidade.getNumero()+1;
					localAdicionarSemente = obterJogadorPeloId(idJogadorAdicionarSemente).obterCavidadePeloNumero(proximaCavidade);
				}
			}

			numSementes--;
			localAdicionarSemente.adicionarSementes(1);
			ultimoLocalOndeSemeou = localAdicionarSemente;
		}
	}

	private void informarJogadorCorrente() {
		TextView txtNomeJogadorCorrente = (TextView) findViewById(R.id.txtNomeJogadorCorrente);
		txtNomeJogadorCorrente.setText(jogadorCorrente.getNome());
	}
}
