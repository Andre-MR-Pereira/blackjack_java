package blackjack;

public abstract class Table {
	int[][] plays;
	
	public abstract int play(Hand player, Card card_dealer);
}
