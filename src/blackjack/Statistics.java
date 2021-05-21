package blackjack;

/**
 * Classe que guarda as estatisticas
 *
 */
public class Statistics {
	
	/**
	 * N�mero de empates
	 */
	int n_pushes;
	
	/**
	 * N�mero de vit�rias
	 */
	int n_victories;
	
	/**
	 * N�mero de derrotas
	 */
	int n_defeats;
	
	/**
	 * N�mero de blackjacks do player
	 */
	int n_player_blackjacks;
	
	/**
	 * N�mero de blackjacks do dealer
	 */
	int n_dealer_blackjacks;
	
	/**
	 * Balance com que o jogador come�a o jogo
	 */
	int initial_balance;
	
	/**
	 * Construtor das estat�sticas.
	 * Coloca todos os atributos a zero, excepto o initial_balance que coloca com o valor que foi parametrizado.
	 * @param initial_balance valor monet�rio inicial do jogador
	 */
	public Statistics(int initial_balance) {
		n_pushes = 0;
		n_victories = 0;
		n_defeats = 0;
		n_player_blackjacks = 0;
		n_dealer_blackjacks = 0;
		this.initial_balance = initial_balance;
	}
	
	/**
	 * Atualiza as estat�sticas no fim de uma qualquer m�o.
	 * @param win flag que indica se foi uma vit�ria, derrota ou empate
	 */
	public void update_game_result(int win) {
		if(win == 0)
			n_defeats++;
		else if (win == 1)
			n_victories++;
		else
			n_pushes++;
	}
	
	/**
	 * M�todo que atualiza o n�mero de blackjacks.
	 * @param player True se o player tiver blackjack, False se for o Dealer.
	 */
	public void update_blackjack(boolean player) {
		if(player)
			n_player_blackjacks++;
		else
			n_dealer_blackjacks++;
	}
	
	/**
	 * M�todo que faz print no terminal das estat�sticas.
	 * @param balance Dinheiro corrente do jogador.
	 */
	public void print_statistics(double balance) {
		System.out.println("BJ P/D		 " + n_player_blackjacks + "/" + n_dealer_blackjacks);
		System.out.println("Win 		  " + n_victories);
		System.out.println("Lose		  " + n_defeats);
		System.out.println("Push		  " + n_pushes);
		System.out.println("Balance	      " + (int) balance + "(" + (int) (balance/initial_balance*100) + "%)");
	}
}
