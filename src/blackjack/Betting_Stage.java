package blackjack;

public class Betting_Stage implements State {
	
	public void handle_input(StateContext context,Player player1, Dealer casino, Shoe s, Basic b, HiLo hl, AceFive a5, int hand) {

		if(context.input=='b') {
			
			if (context.temp_bet == 0) {
				context.setTempBet(context.bet);
				a5.update_bet(context.bet);
			}
			
			if (context.temp_bet < player1.min_bet || context.temp_bet > player1.max_bet || context.temp_bet > player1.balance) {
				System.out.println("illegal command");
				
			}
			else {
				context.setBet(context.temp_bet);
				player1.hands.get(hand).setBet(context.bet);
				System.out.println("player is betting "+ context.bet);
				a5.update_bet(context.bet);
				context.setState(new Deal_Stage());
			}
		}
		else if (context.input=='$') {
			System.out.println("player current balance is " + (int) player1.getBalance());

		}
		else if(context.input == 'a') {
			// Ace Five
			a5.advice(null, null, s);

		}
		else if(context.input == 't') {
			// Imprimir estatísticas
			System.out.println("Implementar estatísticas!");

		}
		
		else
			System.out.println(context.input+": illegal command");
	}

}
