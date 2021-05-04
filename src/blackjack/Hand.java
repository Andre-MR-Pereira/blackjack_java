package blackjack;

public class Hand {

	Card[] cards;
	int bet;
	int ncards;
	
	public Hand() {
		cards = new Card[12];
		ncards = 0;
		bet=0;
	}
	
	public Hand(Card card,int bet) {
		cards = new Card[12];
		cards[0]=card;
		ncards = 1;
		this.bet=bet;
	}
	
	public void splitHand(Card card) {
		cards= new Card[12];
		cards[0]=card;
		ncards=1;
	}
	
	// Sets the player's bet
	public void setBet(int b) {
		this.bet = b;
	}
		
	public void addCard(Card c) {
		cards[ncards++] = c;
	}
	
	public int handSize() {
		return ncards;
	}
	
	public int handTotal() {
		int total = 0, aux;
		boolean ace = false;
		
		for(int i = 0; i < ncards; i++) {
			aux = cards[i].getCardvalue();
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
				res += " " + cards[i];
			}
		}
		
		return res;
	}
	
	public String toString() {
		return this.toString(false);
	}
}
