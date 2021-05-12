package blackjack;

import java.util.ArrayList;

public abstract class Person implements BlackJackPlayer {
	ArrayList<Hand> hands= new ArrayList<Hand>();
	
	// Adds a card to the dealer's hand
	public void hit(Card c,int hand_number) {
		hands.get(hand_number).addCard(c);	
	}
		
	// Returns the value of the player's hand
	public int handValue(int i) {
			return hands.get(i).handTotal();
	}
	
	// Reset the player's hand for the next round
	public void resetHand() {
		hands.removeAll(hands);
		hands.add(new Hand());
	}
	
	// Returns size of hand i
	public int handSize(int i) {
		if(hands.size() < i) {
			return 0;
		}
		else {
			return hands.get(i).handSize();
		}
	}
}
