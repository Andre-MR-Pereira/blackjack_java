package blackjack;

/**
 * Classe que implementa o stage de primeira m�o, onde ainda se podem utilizar as side rules.
 * @see State
 */
class First_Hand_Stage implements State {
	
	/**
	 * M�todo apenas utilizado para o caso espec�fico em que se faz splitting de �ses.
	 * Foi criado um m�todo apenas para este caso por causa de ser t�o distinto de outros casos de splitting.
	 * Sempre que se faz splitting de �ses o jogador � obrigado a dar stand depois de receber uma carta, excepto se for um �s, onde tamb�m pode decidir fazer splitting, se possivel.
	 * @param context StateContext que cont�m o flow do jogo.
	 * @param player1 Player.
	 * @param casino Dealer.
	 * @param s Shoe.
	 * @param b Estrat�gia B�sica.
	 * @param hl Estrat�gia Hi-Lo.
	 * @param a5 Estrat�gia Ace-Five.
	 * @param hand N�mero de m�os do jogador.
	 * @param debugger Indica se estamos em modo de debug.
	 */
    public void finish_split(StateContext context, Player player1, Dealer casino, Shoe s, Basic b, HiLo hl, AceFive a5, int hand, boolean debugger) {
        
    	Card temp = null;
        boolean breaker = false; 

        do {
            if(context.input == 'p' && player1.hands.get(hand).cards[0].handValue() == player1.hands.get(hand).cards[1].handValue() && player1.hands.size() < 4) {
                player1.splitting(player1.hands.get(hand));
                System.out.println("player is splitting");
            }
            
            else if (player1.hands.get(hand).handSize() == 2) {
                hand++;
                System.out.println("player stands");
            }
            
            if(hand < player1.hands.size()) {
            	System.out.println("playing hand n� " + (hand+1) + "...");
                temp = s.deal();
                player1.hit(temp, hand);
                hl.update_counter(temp);
                a5.update_counter(temp);
                if(temp.handValue() == 1 && player1.hands.size() < 4)
                	breaker = true;
                System.out.println(player1.handStr(hand));    
            }
            
            if(breaker) {
            	if(player1.hands.size() == 4)
            		System.out.println("player stands");
            	break;
            }
            
        } while(hand < player1.hands.size());
        
        context.set_hands(hand);  
        if(hand == player1.hands.size() || hand == 3)
        	context.Resolution(context, player1, casino, s, b, hl, a5, debugger);
    }
	
    /**
	 * Neste stage podem ser utilizados os comandos:
	 * 's' - stand para terminar aquela m�o.
	 * 'p' - para fazer splitting.
	 * 'h' - para pedir outra carta para a m�o em jogo.
	 * '$' - para ver o balance do jogador.
	 * 'st' - para ver as estat�sticas.
	 * '2' - para fazer double.
	 * 'u' - para fazer surrender.
	 * 'i' - para criar uma insurance.
	 * 'ad' - para pedir conselhos �s estrat�gias basic e Hi-Lo.
	 */
	public void handle_input(StateContext context, Player player1, Dealer casino, Shoe s, Basic b, HiLo hl, AceFive a5, int hand, boolean debugger) {
		
		Card temp = null;
		
		if(context.input == 's') {
			if(context.finish_split)
				finish_split(context, player1, casino, s, b, hl, a5, hand, debugger);
			else {
				context.setState(new Game_Stage());
				context.handle_input(player1, casino, s, b, hl, a5, debugger);	
			}
		}
		
		else if (context.input == '$')
			System.out.println("player current balance is " + (int) player1.getBalance());
		
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

		else if(context.finish_split || (context.input == 'p')) {
			
			if(player1.hands.get(hand).cards[0].handValue() == player1.hands.get(hand).cards[1].handValue() && player1.hands.size() < 4) {
				if(player1.hands.get(0).cards[0].handValue() == 1) {
					context.setFinishSplit(true);
					if(context.input != 'p') 
						System.out.println(context.input + ": invalid input");
					else
						finish_split(context, player1, casino, s, b, hl, a5, hand, debugger);
					
				}
				else {
					player1.splitting(player1.hands.get(hand));
					temp = s.deal();
					player1.hit(temp, hand);
					hl.update_counter(temp);
					a5.update_counter(temp);
					System.out.println("player is splitting");
					System.out.println("playing hand n� " + (hand+1) + "...");
					System.out.println(player1.handStr(hand));
				}
					
			}
			else
				System.out.println(context.input + ": invalid input");			
			
		}
		
		else if(context.input == 'h') {
			context.setState(new Game_Stage());
			context.handle_input(player1, casino, s, b, hl, a5, debugger);
		}
		
		else if(context.input == '2' && player1.handValue(hand) > 8 && player1.handValue(hand) < 12) {
			temp = s.deal();
			player1.hit(temp, 0);
			hl.update_counter(temp);
			a5.update_counter(temp);
			player1.doubleDown(player1.hands.get(hand));
			System.out.println(player1.handStr(hand));
			
			if(player1.handValue(hand) > 21) {
				
				System.out.println("player busts");
				if (player1.hands.size() == 1) {
					context.hard_reset(player1, casino, s, b, hl, a5, debugger);
					System.out.println("player loses and his current balance is " + (int) player1.getBalance());
				}
				
				else {
					player1.hands.get(hand).setWin(0);
					context.Resolution(context, player1, casino, s, b, hl, a5, debugger);
				}	
			}
			
			else 
				context.Resolution(context, player1, casino, s,b,hl,a5,debugger);
		}
		
		else if(context.input == 'u') {
			
			System.out.println("player is surrendering");
			player1.surrender(hand);
			if (player1.hands.size() == 1) {
				context.hard_reset(player1, casino, s, b, hl, a5, debugger);
				System.out.println("player's current balance is " + (int) player1.getBalance());
			}
			
			else {
				player1.hands.get(hand).setWin(0);
				context.Resolution(context, player1, casino, s, b, hl, a5, debugger);
			}
		}
		
		else if(context.input == 'i' && player1.hands.size() == 1 && casino.hands.get(0).cards[0].handValue() == 1) {
			System.out.println("player is insuring");
			player1.setInsurance(context.bet);
		}
		
		else
			System.out.println(context.input+": invalid input");
	}

}