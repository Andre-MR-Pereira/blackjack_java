package blackjack;

public class Card {
	
	private int value; // From 1 to 13: 1=ACE and 13=KING
	private int suit; // Naipe da carta
	
	public Card(int face, int suit) {
		this.value = face;
		this.suit = suit;
	}
	
	public String getCardface() {

		String name = "?";

		if (this.value == 1) {		
			name = "A";
		}
		else if (this.value == 11) {
			name = "J";
		}
		else if (this.value == 12) {
			name = "Q";
		}
		else if (this.value == 13) {
			name = "K";
		}
		else {
			name = String.valueOf(this.value);
		}
		
		return name;
	}
	
	public String getCardsuit() {

		String name = "?";

		if (this.suit == 1) {		
			name = "H";
		}
		else if (this.suit == 2) {
			name = "S";
		}
		else if (this.suit == 3) {
			name = "C";
		}
		else if (this.suit == 4) {
			name = "D";
		}
		
		return name;
	}
	
	public int getCardvalue() {
		return this.value;
	}
	
	public String toString() {
		return String.format(this.getCardface() + this.getCardsuit());
	}
	
}
