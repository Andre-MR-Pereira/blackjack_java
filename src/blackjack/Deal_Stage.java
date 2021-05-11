package blackjack;

public class Deal_Stage implements State{

	public void handle_input(StateContext context,Player player1, Dealer casino, Shoe s) {
		if(context.input=='d') {
			casino.hit(s.deal(),0);
			casino.hit(s.deal(),0);
			player1.hit(s.deal(),0);
			player1.hit(s.deal(),0);
			context.setState(new Game_Stage());
			System.out.println("dealer's hand "+ casino.hands.get(0).toString(true));
			System.out.println("player's hand "+ player1.hands.get(0)+" ("+player1.handValue()[0]+")");
			if(player1.handValue()[0]==21) System.out.println("blackjack!!");
		}
		else if (context.input=='$') {
			System.out.println("player current balance is " + (int) player1.getBalance());
			context.setvalid(false);
		}
		else {
			System.out.println(context.input+":invalid input");
			context.setvalid(false);
		}
	}
}
