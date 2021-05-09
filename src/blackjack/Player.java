package blackjack;

public class Player extends Person{
	private int balance;
	int min_bet, max_bet;
	// Player Constructor
	public Player(int balance,int min_bet, int max_bet) {
		hands.add(new Hand());
		this.balance = balance;
		this.min_bet = min_bet;
		this.max_bet = max_bet;
	}
	
	// Gets the current balance
	public double getBalance() {
		return this.balance;
	}
	
	// Updates balance when the player busts and resets the bet
	public void update_loss(int i) {
		balance -= hands.get(i).bet;
	}
	
	// Updates balance when the player wins and resets the bet
	public void update_win(int i) {
		balance += hands.get(i).bet;
	}
	
	// Updates balance when the player gets blackjack and resets the bet
	public void update_bj(int i) {
		balance += 1.5*hands.get(i).bet;
	}
	
	
	// Returns the string with the player's hand
	public String[] handStr() {
		String[] s=new String[hands.size()];
		for(int i=0;i<hands.size();i++) {
			s[i] = "player's hand" + (i+1) +":" + hands.get(i);
		}
		return s;
	}
	
	public void insurance(){
		//verificar se se pode fazer
		//int insurance_bet=hands.get(0).bet;
		//problema de ver o rumo do jogo
	}
	
	public void surrender(int i){
		//verificar se se pode fazer
		balance -= hands.get(i).bet/2;
		resetHand();
	}
	
	public void splitting(Hand hand) {
		if(hands.size()>3) {
			Card[] buffer=hand.cards;
			hand.splitHand(buffer[0]);
			hands.add(new Hand(buffer[1],hand.bet));
			//problema de ver o rumo do jogo
		}else {
			System.out.println("You can´t split more than 4 times");
		}
		
	}
	
	public void doubleDown(Hand hand,float amount){
		if(amount<hand.bet && 9<=hand.handTotal() && 11>=hand.handTotal()) {
			hand.setBet(hand.bet+amount);
		}else {
			System.out.println("You can only double down up to the amount of the original bet");
		}
		//problema de ver o rumo do jogo
	}
	
	/* Tentativa de main
	public static void main(String[] args){
		Player Andre= new Player(1000,0,0);
		Player Joo= new Player(505.75,0,0);
	} */
}
