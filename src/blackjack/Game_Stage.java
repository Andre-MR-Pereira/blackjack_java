package blackjack;

/**
 * Classe que implementa o stage de jogo mas em que já não é permitido usar as side rules.
 * @see State
 */
class Game_Stage implements State {
	
	/**
	 * Neste stage podem ser utilizados os comandos:
	 * 's' - stand para terminar aquela mão.
	 * 'h' - para pedir outra carta para a mão em jogo.
	 * '$' - para ver o balance do jogador.
	 * 'st' - para ver as estatísticas.
	 * 'ad' - para pedir conselhos ás estratégias basic e Hi-Lo.
	 */
	public void handle_input(StateContext context, Player player1, Dealer casino, Shoe s, Basic b, HiLo hl, AceFive a5, int hand, boolean debugger) {
		Card temp = null;
		
		if(context.input == 'h') {
			temp = s.deal();
			player1.hit(temp, hand);
			hl.update_counter(temp);
			a5.update_counter(temp);
			System.out.println("player hits");
			System.out.println(player1.handStr(hand));
			
			if(player1.handValue(hand) > 21) {
				System.out.println("player busts");
				if (player1.hands.size() == 1) {
					System.out.println("player loses and his current balance is " + (int) player1.getBalance());
					context.hard_reset(player1, casino, s, b, hl, a5, debugger);
				}
				
				else {
					player1.hands.get(hand).setWin(0);
					context.Resolution(context, player1, casino, s, b, hl, a5, debugger);
				}
			}
		}
		
		else if (context.input == '$')
			System.out.println("player current balance is " + (int) player1.getBalance());
		
		else if(context.input == 's') {
			System.out.println("player stands");
			context.Resolution(context, player1, casino, s, b, hl, a5, debugger);
		}
		
		else if(context.input == 'a') {
			
			if(hl.check_strat() == 0) {
				hl.print_advice(hl.advice(player1.hands.get(hand), casino.knownCard(), s, player1));
				b.print_advice(b.advice(player1.hands.get(hand), casino.knownCard(), s, player1));
			}
			
			else if(hl.check_strat() == 1) {
				char aux;
				aux = hl.make_advice(hl.advice(player1.hands.get(hand), casino.knownCard(), s, player1));
				if(aux != '0')
					context.set_input(aux);
				else
					context.set_input(b.make_advice(b.advice(player1.hands.get(hand), casino.knownCard(), s, player1)));
			}
			
			else if(hl.check_strat() == 2)
				context.set_input(b.make_advice(b.advice(player1.hands.get(hand), casino.knownCard(), s, player1)));
		}
		
		else if(context.input == 't')
			context.stat.print_statistics(player1.getBalance());
		
		else
			System.out.println(context.input + ": invalid input");

	}

}
