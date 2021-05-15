package blackjack;

class First_Hand_Stage implements State {
	
	int win;
	public void finish_split(StateContext context,Player player1, Dealer casino, Shoe s, Basic b, HiLo hl, AceFive a5, int hand) {
		Card temp = null;
		while(hand<player1.hands.size()) {
			if(player1.hands.get(0).cards[0].handValue()==player1.hands.get(0).cards[1].handValue()&& player1.hands.size()<4) {
				player1.splitting(player1.hands.get(hand));
			}
			else {
				hand++;
						
			}
			if(hand<player1.hands.size()) {
				temp = s.deal();
				player1.hit(temp, hand);
				hl.update_counter(temp);
				a5.update_counter(temp);
				System.out.println("player is splitting");
				System.out.println("playing hand nº "+(hand+1)+"...");
				System.out.println(player1.handStr(hand));	
			}
			
		}
	}
	public void handle_input(StateContext context,Player player1, Dealer casino, Shoe s, Basic b, HiLo hl, AceFive a5, int hand,boolean debugger) {
		Card temp = null;
		if(context.input == 'h' || context.input == 's') {
			context.setState(new Game_Stage());
			context.call_state(player1, casino, s, b, hl, a5,debugger);
		}
		else if (context.input=='$') {
			System.out.println("player current balance is " + (int) player1.getBalance());

		}
		else if(context.input == 'a') {
			hl.advice(player1.hands.get(hand), casino.knownCard(), s);
			b.advice(player1.hands.get(hand), casino.knownCard(), s);

		}
		else if(context.input == '2' && player1.handValue(0) > 8 && player1.handValue(0) < 12) {
			temp = s.deal();
			player1.hit(temp, 0);
			hl.update_counter(temp);
			a5.update_counter(temp);
			player1.doubleDown(player1.hands.get(hand));
			System.out.println(player1.handStr(hand));
			if(player1.handValue(hand)>21) {
				
				System.out.println("player busts");
				if (player1.hands.size()==1) {
					context.hard_reset(player1, casino, s, b, hl, a5,debugger);
					System.out.println("player loses and his current balance is " + (int) player1.getBalance());
				}
				else {
					player1.hands.get(hand).setWin(0);
					context.Resolution(context, player1, casino, s,b,hl,a5,debugger);
				}	
			}
			else context.Resolution(context, player1, casino, s,b,hl,a5,debugger);


		}
		else if(context.input == 't') {
			// Imprimir estatísticas
			System.out.println("Implementar estatísticas!");

		}
		else if(context.input == 'u') {
			
			System.out.println("player is surrendering");
			player1.surrender(hand);
			if (player1.hands.size()==1) {
				context.hard_reset(player1, casino, s, b, hl, a5,debugger);
				System.out.println("player's current balance is " + (int) player1.getBalance());
			}
			else {
				player1.hands.get(hand).setWin(0);
				context.Resolution(context, player1, casino, s,b,hl,a5,debugger);
			}
		}
		else if(context.input == 'i'&& player1.hands.size()==1 && casino.hands.get(0).cards[0].handValue()==1) {
			casino.dealerTurn(s);
			System.out.println("player is insuring");
			context.setInsurance(context.bet);
		}
		else if(context.input == 'p' && player1.hands.get(0).cards[0].handValue()==player1.hands.get(0).cards[1].handValue()&& player1.hands.size()<4) {
			if(player1.hands.get(0).cards[0].handValue()==1) {
				finish_split(context, player1, casino, s, b, hl, a5, hand);
				context.Resolution(context, player1, casino, s,b,hl,a5,debugger);
			}
			player1.splitting(player1.hands.get(hand));
			temp = s.deal();
			player1.hit(temp, hand);
			hl.update_counter(temp);
			a5.update_counter(temp);
			System.out.println("player is splitting");
			System.out.println("playing hand nº "+(hand+1)+"...");
			System.out.println(player1.handStr(hand));
			
		}
		else
			System.out.println(context.input+": invalid input");
	}

}