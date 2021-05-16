package blackjack;

public interface Strategies {
	
	public int advice(Hand player, Card card_dealer, Shoe shoe, Player p);
	
	public void print_advice(int res);
	
}
