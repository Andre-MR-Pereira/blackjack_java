package blackjack;

class Game_Stage implements State {
	
	int win;
	
	public void handle_input(StateContext context,Player player1, Dealer casino, Shoe s, Basic b, HiLo hl, AceFive a5) {
		Card temp = null;
		
		if(context.input == 'h') {
			temp = s.deal();
			player1.hit(temp, 0);
			hl.update_counter(temp);
			a5.update_counter(temp);
			System.out.println(player1.handStr()[0]);
			
			if(player1.handValue()[0]>21) {
				
				System.out.println("player busts");
				System.out.println(casino.handStr(false));
				win = 0;
				context.Resolution(context, player1, casino, s, win);
			}
		}
		else if (context.input=='$') {
			System.out.println("player current balance is " + (int) player1.getBalance());
			context.setvalid(false);
		}
		else if(context.input == 's'){
			System.out.println("player stands");
			casino.dealerTurn(s);
			for(int i = 1; i < casino.handSize(0); i++) {
				temp = casino.hands.get(0).cards[i];
				hl.update_counter(temp);
				a5.update_counter(temp);
			}
			
			if(player1.handValue()[0] > casino.handValue()[0] || casino.handValue()[0] > 21) win = 1;
			else if (player1.handValue()[0] < casino.handValue()[0]) win = 0;
			else if (player1.handValue()[0] == 21) win = 3;
			else win = 2;
			context.Resolution(context, player1, casino, s, win);
		}
		else if(context.input == 'a') {
			hl.advice(player1.hands.get(0), casino.knownCard(), s);
			b.advice(player1.hands.get(0), casino.knownCard(), s);
			context.setvalid(false);
		}
		else if(context.input == 't') {
			// Imprimir estatísticas
			System.out.println("Implementar estatísticas!");
			context.setvalid(false);
		}
		else {
			System.out.println(context.input+": invalid input");
			context.setvalid(false);
		}
	}

}
