package blackjack;

public class Hand {
	Card[] cards;
	double bet;
	int ncards;
	Chips chips;
	int win;
	
	public Hand() {        //regular hand
        cards = new Card[12];
        ncards = 0;
        bet=0;
        chips= new Chips(0,0,0,0);
        win = -1;
    }
    
    public Hand(Card card,double bet) {    //splitting hands
        cards = new Card[12];
        cards[0]=card;
        ncards = 1;
        this.bet=bet;
        chips= new Chips(0,0,0,0);
        chips.convert_chips(bet);
        win = -1;
    }
    
    public Hand(Card card1,Card card2) {    //opening hands
        cards = new Card[12];
        cards[0]=card1;
        cards[1]=card2;
        ncards = 2;
        win = -1;
    }
	
    public void splitHand() {
        ncards=1;
    }
	
	// Sets the player's bet
	public void setBet(double b) {
		if(chips.validate_bet(b)==true) {
			this.bet = b;
		}
	}
	
	public void setWin(int win) {
		this.win = win;
	}
	
	public void addCard(Card c) {
		cards[ncards++] = c;
	}
	
	public String winStr() {
		if (win == 0) return "loses";

		else if (win == 1 ) return "wins";

		else return "pushes";
	}
	
	public int handSize() {
		return ncards;
	}
	
	public int handType() {
        int no_aces=1;
        
        if(ncards==2 && cards[0].getCardface().equals(cards[1].getCardface())) {
            if(cards[0].getCardface().equals("A")) {
                return -2;
            }
            return 2; //pair hand
        }
        
        int aux = 0;
        for(int i=0;i<ncards;i++) {
            if(cards[i].getCardface().equals("A")) {
                no_aces=0;
            }
            aux += cards[i].handValue();
        }
        
        if(no_aces==0 && aux + 10 <= 21) {
            return 1; //soft hand
        } else {
            return 0; //hard hand
        }
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
	
	/*
	public static void main(String[] args){
        Hand hand1=new Hand();
        Hand hand2=new Hand(new Card(1,1),5);
        Hand hand3=new Hand();
        Hand hand4=new Hand();
        hand3.addCard(new Card(1,1));
        hand3.addCard(new Card(1,1));
        hand4.addCard(new Card(7,1));
        hand4.addCard(new Card(7,1));
        hand1.setBet(1);
        hand1.setBet(5);
        hand1.setBet(0.5);
        hand1.addCard(new Card(10,1));
        hand1.addCard(new Card(5,1));
        System.out.println("Tamanho mao");
        System.out.println(hand1.handSize());
        System.out.println(hand2.handSize());
        System.out.println("Valor mao");
        System.out.println(hand1.handTotal());
        System.out.println(hand2.handTotal());
        System.out.println("Split not working");
        hand1.splitHand();
        System.out.println(hand1.handTotal());
        hand2.addCard(new Card(9,1));
        hand2.addCard(new Card(3,1));
        System.out.println(hand2.handTotal());
        System.out.println("Tipos de mao");
        System.out.println(hand1.handType());
        System.out.println(hand2.handType());
        System.out.println(hand3.handType());
        System.out.println(hand4.handType());
        System.out.println("Split working");
        System.out.println(hand4.handTotal());
        hand4.splitHand();
        System.out.println(hand4.handTotal());
    }
    */
}
