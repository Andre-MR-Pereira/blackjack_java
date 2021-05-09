package blackjack;

public interface BlackJackPlayer {
	
	public void hit(Card c,int hand_number);
	public int[] handValue();
	public void resetHand();
}
