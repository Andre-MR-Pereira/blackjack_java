package blackjack;

class Game_Stage implements State{
	int win;
	public void handle_input(StateContext context,Player player1, Dealer casino, Shoe s) {
		if(context.input == 'h') {
			player1.hit(s.deal(),0);
			System.out.println("player's hand "+ player1.hands.get(0) + " ("+player1.handValue()[0]+")");
			if(player1.handValue()[0]>21) {
				
				System.out.println("player busts");
				System.out.println("dealer's hand "+ casino.hands.get(0));
				win = 0;
				context.Resolution(context,player1,casino,s,win);
			}
		}
		else if (context.input=='$') {
			System.out.println("player current balance is " + player1.getBalance());
			context.setvalid(false);
		}
		else if(context.input == 's'){
			System.out.println("player stands");
			casino.dealerTurn(s);
			if(player1.handValue()[0]>casino.handValue()[0]||casino.handValue()[0]>21) win = 1;
			else if (player1.handValue()[0]<casino.handValue()[0]) win = 0;
			else if (player1.handValue()[0]==21) win = 3;
			else	win = 2;
			context.Resolution(context,player1,casino,s,win);
		}
		else {
			System.out.println(context.input+":invalid input");
			context.setvalid(false);
		}
	}
		

}
