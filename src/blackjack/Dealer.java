package blackjack;

public class Dealer {

	private Hand hand;
	
	// Dealer Constructor
	public Dealer() {
		this.hand = new Hand();
	}
	
	// Adds a card to the dealer's hand
	public void addCardtoHand(Card c) {
		hand.addCard(c);
	}
	
	// Returns the value of the dealer's hand
	public int handValue() {
		return hand.handTotal();
	}
	
	// Reset the dealer's hand for the next round
	public void resetHand() {
		hand = new Hand();
	}
	
	// Returns the string with the dealer's hand
	public String handStr(boolean hideCard) {
		String s = "dealer's hand:" + hand.toString(hideCard);
	
		return s;
	}
	
	// Dealer's turn can be fully automatic
	public void dealerTurn(Shoe shoe) {
		boolean flag_BJ = false;
		if(hand.handTotal() == 21) {
			flag_BJ = true;
		}
		
		while(hand.handTotal() <= 16) {
			System.out.println(this.handStr(false) + " " + String.valueOf(hand.handTotal()));
			System.out.println("dealer hits");
			hand.addCard(shoe.deal());
		}
		
		if(hand.handTotal() > 21) {
			System.out.println(this.handStr(false) + " " + String.valueOf(hand.handTotal()));
			System.out.println("dealer busts");
		}
		else {
			System.out.println(this.handStr(false) + " " + String.valueOf(hand.handTotal()));
			System.out.println("dealer stands");
			
			if(flag_BJ)
				System.out.println("blackjack!!");
		}
		
	}
	
}
