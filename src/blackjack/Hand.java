package blackjack;

public class Hand {
	Card[] cards;
	double bet;
	int ncards;
	Chips chips;
	
	public Hand() {
		cards = new Card[12];
		ncards = 0;
		bet=0;
		chips= new Chips(0,0,0,0);
	}
	
	public Hand(Card card,double bet) {
		cards = new Card[12];
		cards[0]=card;
		ncards = 1;
		this.bet=bet;
		chips= new Chips(0,0,0,0);
		chips.convert_chips(bet);
	}
	
	public void splitHand(Card card) {
		cards= new Card[12];
		cards[0]=card;
		ncards=1;
	}
	
	// Sets the player's bet
	public void setBet(double b) {
		if(chips.validate_bet(b)==true) {
			this.bet = b;
		}
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
	
	public static void main(String[] args){
		Hand hand1=new Hand();
		Hand hand2=new Hand(new Card(1),5);
		hand1.setBet(1);
		hand1.setBet(5);
		hand1.setBet(0.5);
		hand1.addCard(new Card(10));
		hand1.addCard(new Card(5));
		System.out.println(hand1.handSize());
		System.out.println(hand2.handSize());
		System.out.println(hand1.handTotal());
		System.out.println(hand2.handTotal());
		hand1.splitHand(new Card(7));
		System.out.println(hand1.handTotal());
		hand2.addCard(new Card(9));
		hand2.addCard(new Card(3));
		System.out.println(hand2.handTotal());
	}
}
