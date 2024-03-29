package blackjack;

import java.util.ArrayList;

/**
 * Objeto abstrato que representa uma pessoa no jogo de blackjack.
 * Cont�m assim tudo em comum entre o dealer e o jogador em si.
 */
public abstract class Person implements BlackJackPlayer {
	/**
	 * ArrayList de M�os.
	 * No caso do Dealer s� vai ter uma m�o.
	 * Enquanto o jogador pode ter at� 4 m�os.
	 */
	ArrayList<Hand> hands= new ArrayList<Hand>();
	
	/**
	 * Adiciona uma carta � m�o espec�fica do jogador.
	 * @param c Carta a ser adicionada � m�o.
	 * @param hand_number Qual a m�o a ser adicionada a carta.
	 */
	public void hit(Card c,int hand_number) {
		hands.get(hand_number).addCard(c);	
	}
		
	/**
	 * Indica o valor mais alto da m�o escolhida do jogador.
	 * @param i m�o a ser analisada
	 * @return valor mais alto poss�vel da m�o
	 */
	public int handValue(int i) {
			return hands.get(i).handTotal();
	}
	
	/**
	 * Remove todas as m�os do jogador e inicializa uma m�o nova para
	 * ser jogada uma nova rodada.
	 */
	public void resetHand() {
		hands.removeAll(hands);
		hands.add(new Hand());
	}
	
	/**
	 * Retorna as cartas que a m�o escolhida t�m.
	 * @param i M�o a ser analisada.
	 * @return Quantas cartas t�m a m�o.
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
