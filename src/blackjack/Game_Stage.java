package blackjack;

class Game_Stage implements State {
	
	int win;
	
	public void handle_input(StateContext context,Player player1, Dealer casino, Shoe s, Basic b, HiLo hl, AceFive a5, int hand,boolean debugger) {
		Card temp = null;
		
		if(context.input == 'h') {
			temp = s.deal();
			player1.hit(temp, hand);
			hl.update_counter(temp);
			a5.update_counter(temp);
			System.out.println("player hits");
			System.out.println(player1.handStr(hand));
			if(player1.handValue(hand)>21) {
				System.out.println("player busts");
				if (player1.hands.size()==1) {

					context.hard_reset(player1, casino, s,b,hl,a5,debugger);
					System.out.println("player loses and his current balance is " + (int) player1.getBalance());
				}
				else {
					player1.hands.get(hand).setWin(0);
					context.Resolution(context, player1, casino, s,b,hl,a5,debugger);
				}

				
			}
		}
		else if (context.input=='$') {
			System.out.println("player current balance is " + (int) player1.getBalance());
		}
		else if(context.input == 's'){
			System.out.println("player stands");
			context.Resolution(context, player1, casino, s,b,hl,a5,debugger);
		}
		else if(context.input == 'a') {
			hl.advice(player1.hands.get(hand), casino.knownCard(), s);
			b.advice(player1.hands.get(hand), casino.knownCard(), s);
		}
		else if(context.input == 't') {
			// Imprimir estatísticas
			System.out.println("Implementar estatísticas!");
		}
		else
			System.out.println(context.input+": invalid input");
	}

}
