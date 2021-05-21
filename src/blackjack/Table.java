package blackjack;

/**
 * Classe abstata que representa qualquer tabela de jogadas
 */
public abstract class Table {
	
	/**
	 * Matriz que guarda a jogada a ser efetuada para cada caso, de cada estratégia.
	 */
	int[][] plays;
	
	/**
	 * Indica qual a jogada a ser tomada consoante a mão em causa do jogador
	 * e a carta conhecida do Dealer.
	 * @param player mão a ser analisada do jogador.
	 * @param card_dealer carta conhecida do Dealer.
	 * @return jogada a ser tomada.
	 */
	public abstract int play(Hand player, Card card_dealer);
}
