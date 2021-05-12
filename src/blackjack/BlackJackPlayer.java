package blackjack;

public interface BlackJackPlayer {
	
	public void hit(Card c,int hand_number);
	public int handValue(int i);
	public void resetHand();
}
