package blackjack;

import java.util.ArrayList;

public abstract class Person implements BlackJackPlayer {
	/**
	 * Cria o objeto de um jogador regular de blackjack
	 */
	ArrayList<Hand> hands= new ArrayList<Hand>();
	
	/**
	 * Adiciona uma carta à mão específica do jogador.
	 * @param c Carta a ser adicionada à mão.
	 * @param hand_number Qual a mão a ser adicionada a carta.
	 */
	public void hit(Card c,int hand_number) {
		hands.get(hand_number).addCard(c);	
	}
		
	/**
	 * Indica o valor mais alto da mão escolhida do jogador.
	 * @param i mão a ser analisada
	 * @return valor mais alto possível da mão
	 */
	public int handValue(int i) {
			return hands.get(i).handTotal();
	}
	
	/**
	 * Remove todas as mãos do jogador e inicializa uma mão nova para
	 * ser jogada uma nova rodada.
	 */
	public void resetHand() {
		hands.removeAll(hands);
		hands.add(new Hand());
	}
	
	// Returns size of hand i
	/**
	 * Retorna as cartas que a mão escolhida têm.
	 * @param i Mão a ser analisada.
	 * @return Quantas cartas têm a mão.
	 */
	public int handSize(int i) {
		if(hands.size() < i) {
			return 0;
		}
		else {
			return hands.get(i).handSize();
		}
	}
}
