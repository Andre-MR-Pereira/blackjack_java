package blackjack;

public class Player {

	private int balance;
	private int bet;
	private Hand hand;
	
	// Player Constructor
	public Player(int balance) {
		this.hand = new Hand();
		this.balance = balance;
		this.bet = 0;
	}
	
	// Gets the current balance
	public int getBalance() {
		return this.balance;
	}
	
	// Sets the player's bet
	public void setBet(int b) {
		this.bet = b;
	}
	
	// Adds a card to the player's hand
	public void addCardtoHand(Card c) {
		hand.addCard(c);
	}
	
	// Returns the value of the player's hand
	public int handValue() {
		return hand.handTotal();
	}
	
	// Updates balance when the player busts and resets the bet
	public void update_loss() {
		balance -= bet;
		bet = 0;
	}
	
	// Updates balance when the player wins and resets the bet
	public void update_win() {
		balance += bet;
		bet = 0;
	}
	
	// Updates balance when the player gets blackjack and resets the bet
	public void update_bj() {
		balance += 1.5*bet;
		bet = 0;
	}
	
	// Resets the bet when player draws (pushes)
	public void update_draw() {
		bet = 0;
	}
	
	// Reset the player's hand for the next round
	public void resetHand() {
		hand = new Hand();
	}
	
	// Returns the string with the player's hand
	public String handStr() {
		String s = "player's hand:" + hand;
		
		return s;
	}
	
}
