package blackjack;

public class Hand {

	private Card[] hand;
	private int ncards;
	
	public Hand() {
		hand = new Card[12];
		ncards = 0;
	}
	
	public void addCard(Card c) {
		hand[ncards++] = c;
	}
	
	public int handTotal() {
		int total = 0, aux;
		boolean ace = false;
		
		for(int i = 0; i < ncards; i++) {
			aux = hand[i].getCardvalue();
			if(aux > 10) {
				aux = 10;
			}
			else if (aux == 1) {
				ace = true;
			}
			
			total += aux;
		}
		
		if(ace == true && total + 10 <= 21)
			total = total + 10;
		
		return total;
	}
	
	public String toString(boolean hideCard) {
		String res = "";	
		
		for(int i = 0; i < ncards; i++) {
			if(hideCard && i == ncards-1) {
				res += " " + "X";
			}
			else {
				res += " " + hand[i];
			}
		}
		
		return res;
	}
	
	public String toString() {
		return this.toString(false);
	}
}
