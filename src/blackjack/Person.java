package blackjack;

import java.util.ArrayList;
import java.util.List;

public abstract class Person {
	ArrayList<Hand> hands= new ArrayList<Hand>();
	
	// Adds a card to the dealer's hand
	public void hit(Card c,int hand_number) {
		
			hands.get(hand_number).addCard(c);	
		
	}
		
	// Returns the value of the player's hand
	public int[] handValue() {
		int [] result=new int[hands.size()];
		for(int i=0;i<hands.size();i++) {
			result[i]=hands.get(i).handTotal();
		}
		return result;
	}
	
	// Reset the player's hand for the next round
	public void resetHand() {
		hands.removeAll(hands);
		hands.add(new Hand());
	}
}
