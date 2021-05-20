package blackjack;

public interface BlackJackPlayer {
	/**
	 * Para existir um jogador de blackjack que seja extensível, será sempre
	 * necessário que seja possível pedir uma carta,saber o valor da sua mão
	 * e limpar a sua mão.
	 */
	
	/**
	 * Permite pedir uma carta para a mão desejada.
	 * @param c carta nova fornecida.
	 * @param hand_number mão para a qual será fornecida a carta.
	 */
	public void hit(Card c, int hand_number);
	
	/**
	 * Retorna o valor mais alto possível da mão fornecida.
	 * @param i mão a ser analisada
	 * @return valor mais alto possível da mão
	 */
	public int handValue(int i);
	
	/**
	 * Limpa as mãos antigas e cria uma mão para uma nova rodada para o jogador. 
	 */
	public void resetHand();
}
