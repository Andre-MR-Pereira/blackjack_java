package blackjack;

public class game {
	
	public void interactivestart(int min_bet, int max_bet,int balance,int shoe,int shuffle) {
		Shoe s = new Shoe(shoe,shuffle);
		Dealer casino = new Dealer();
		Player player1 = new Player(balance,min_bet,max_bet);
		StateContext flow = new StateContext(min_bet);
		
		while(true) {
			flow.handle_input(player1,casino, s);
		}
	}
	
	
}
