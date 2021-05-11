package blackjack;

public abstract class Table {
	int[][] plays;
	
	public abstract void play(Hand player,Card card_dealer);
}
