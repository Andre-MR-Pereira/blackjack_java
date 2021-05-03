package blackjack;

public class Card {
	
	private int value; // From 1 to 13: 1=ACE and 13=KING
	// private String suit; (Se for preciso meter naipes)
	
	public Card(int face) {
		this.value = face;
	}
	
	public String getCardname() {

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
	
	public int getCardvalue() {
		return this.value;
	}
	
	public String toString() {
		return this.getCardname();
	}
	
}
