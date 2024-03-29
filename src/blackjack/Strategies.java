package blackjack;

/**
 * Interface que representa qualquer estrat�gia
 */
public interface Strategies {
	/**
	 * Escolhe o conselho a ser fornecido para a jogada em causa
	 * @param player m�o do jogador
	 * @param card_dealer carta conhecida do dealer
	 * @param shoe shoe ativo do jogo
	 * @param p jogador a ser analisado
	 * @return retorna o indicador dessa estrat�gia.
	 */
	public int advice(Hand player, Card card_dealer, Shoe shoe, Player p);
	
	/**
	 * Imprime o conselho para a consola.
	 * @param res indicador do conselho a ser impresso
	 */
	public void print_advice(int res);
}
