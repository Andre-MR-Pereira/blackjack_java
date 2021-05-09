package blackjack;

public class Betting_Stage implements State{
	public void handle_input(StateContext context,Player player1, Dealer casino, Shoe s) {

		if(context.input=='b') {
			
			if (context.temp_bet == 0) {
				context.setTempBet(context.bet);
			}
			if (context.temp_bet < player1.min_bet || context.temp_bet> player1.max_bet|| context.temp_bet> player1.balance) {
				context.setvalid(false);
				System.out.println("invalid bet ammount ");
				
			}
			else {
				context.setBet(context.temp_bet);
				player1.hands.get(0).setBet(context.bet);
				System.out.println("player is betting "+context.bet);
				context.setState(new Deal_Stage());
			}
		}
		else if (context.input=='$') {
			System.out.println("player current balance is " + player1.getBalance());
			context.setvalid(false);
		}
		else {
			System.out.println(context.input+":invalid input");
			context.setvalid(false);
		}
	}

}
