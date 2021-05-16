package blackjack;

public interface Strategies {
	/**
	 * Escolhe o conselho a ser fornecido para a jogada em causa
	 * @param player mão do jogador
	 * @param card_dealer carta conhecida do dealer
	 * @param shoe shoe ativo do jogo
	 * @param p jogador a ser analisado
	 * @return
	 */
	public int advice(Hand player, Card card_dealer, Shoe shoe, Player p);
	
	/**
	 * Imprime o conselho para a consola.
	 * @param res indicador do conselho a ser impresso
	 */
	public void print_advice(int res);
}
