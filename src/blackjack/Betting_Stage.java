package blackjack;

/**
 * Classe que implementa o stage de apostas: o stage inicial do jogo.
 * @see State
 */
public class Betting_Stage implements State {
	
	/**
	 * Neste stage podem ser utilizados os comandos:
	 * 'b' - para realizar uma bet com um certo valor, que é verificado e passar ao próximo stage.
	 * '$' - para ver o balance do jogador.
	 * 'ad' - para pedir conselhos ao ace-five.
	 * 'st' - para ver as estatísticas.
	 */
	public void handle_input(StateContext context, Player player1, Dealer casino, Shoe s, Basic b, HiLo hl, AceFive a5, int hand, boolean debugger) {
		
		if(context.input == 'b') {
			
			if (context.temp_bet == 0) {
				context.setTempBet(context.bet);
				a5.update_bet(context.bet);
			}
			
			if (context.temp_bet < player1.min_bet || context.temp_bet > player1.max_bet)
				System.out.println("b" + context.temp_bet + ": illegal command");
			
			else {
				context.setBet(context.temp_bet);
				player1.set_bet(0, context.bet);
				System.out.println("player is betting " + context.bet);
				a5.update_bet(context.bet);
				context.setState(new Deal_Stage());
			}
		}
		
		else if (context.input == '$')
			System.out.println("player current balance is " + (int) player1.getBalance());

		else if(context.input == 'a') {
			// Ace Five
			a5.print_advice(a5.advice(null, null, null, null));
		}
		else if(context.input == 't') {
			context.stat.print_statistics(player1.getBalance());

		}
		
		else
			System.out.println(context.input + ": illegal command");

	}

}
