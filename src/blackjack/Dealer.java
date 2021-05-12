package blackjack;

public class Dealer extends Person{
	
	// Dealer Constructor
	public Dealer() {
		hands.add(new Hand());
	}
	
	// Returns the string with the dealer's hand
	public String handStr(boolean hideCard) {
		String s = "dealer's hand " + hands.get(0).toString(hideCard);
	
		return s;
	}
	
	// Dealer's turn can be fully automatic
	public void dealerTurn(Shoe shoe) {
		boolean flag_BJ = false;
		if(hands.get(0).handTotal() == 21) {
			flag_BJ = true;
		}
		
		while(hands.get(0).handTotal() <= 16) {
			System.out.println(this.handStr(false) + " (" + this.handValue()[0]+")");
			System.out.println("dealer hits");
			hands.get(0).addCard(shoe.deal());
		}
		
		if(hands.get(0).handTotal() > 21) {
			System.out.println(this.handStr(false) + " (" + this.handValue()[0]+")");
			System.out.println("dealer busts");
		}
		else {
			if(flag_BJ)
				System.out.println("blackjack!!");
			else {
			System.out.println(this.handStr(false) + " (" + this.handValue()[0]+")");
			System.out.println("dealer stands");
			}
			
		}
	}
	
	public Card knownCard() {
        return hands.get(0).cards[0];
    }
	
}
