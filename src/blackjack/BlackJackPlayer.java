package blackjack;

public interface BlackJackPlayer {
	/**
	 * Para existir um jogador de blackjack que seja extens�vel, ser� sempre
	 * necess�rio que seja poss�vel pedir uma carta,saber o valor da sua m�o
	 * e limpar a sua m�o.
	 */
	
	/**
	 * Permite pedir uma carta para a m�o desejada.
	 * @param c carta nova fornecida.
	 * @param hand_number m�o para a qual ser� fornecida a carta.
	 */
	public void hit(Card c, int hand_number);
	
	/**
	 * Retorna o valor mais alto poss�vel da m�o fornecida.
	 * @param i m�o a ser analisada
	 * @return valor mais alto poss�vel da m�o
	 */
	public int handValue(int i);
	
	/**
	 * Limpa as m�os antigas e cria uma m�o para uma nova rodada para o jogador. 
	 */
	public void resetHand();
}
