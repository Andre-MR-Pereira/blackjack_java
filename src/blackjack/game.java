package blackjack;

public class game {
	
	public game() {
		// TODO Auto-generated constructor stub
	}
	
	public void interactivestart(int min_bet, int max_bet,int balance,int shoe,int shuffle) {
		Shoe s = new Shoe(shoe,shuffle);
		Dealer casino = new Dealer();
		Player player1 = new Player(balance,min_bet,max_bet);
		boolean in_game= true, in_play = true;
		while(in_game) {
			
			while(in_play) {
				
			}
		}
		
	}

}
