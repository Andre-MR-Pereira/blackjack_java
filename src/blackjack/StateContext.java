package blackjack;

import java.util.Scanner;

public class StateContext {
    private State state;
    boolean valid;
    char input;
	int bet, temp_bet,insurance;
	int hand;
    Scanner in = new Scanner(System.in);
    
    public StateContext(int min_bet) {
        state = new Betting_Stage();
        bet = min_bet;
        insurance = 0;
        hand = 0;
        valid = false;
    }
    
    
    
    public void Resolution(StateContext context,Player player1, Dealer casino, Shoe s, Basic b, HiLo hl, AceFive a5) {
    	Card temp = new Card(0, 0);
    	if(hand<player1.hands.size()-1) {
			hand++;
			System.out.println("playing hand nº "+(hand+1)+"...");
			temp = s.deal();
			player1.hit(temp, hand);
			hl.update_counter(temp);
			a5.update_counter(temp);
			System.out.println(player1.handStr(hand));
			setState(new First_Hand_Stage());
		}
    	
    	else {
    		checkInsurance(player1, casino);
    		casino.dealerTurn(s);
			for(int i = 1; i < casino.handSize(0); i++) {
				temp = casino.hands.get(0).cards[i];
				hl.update_counter(temp);
				a5.update_counter(temp);
			}
			int casino_value = casino.handValue(0);
			int player_value = 0;
    		for(int i = 0; i < player1.hands.size(); i++) {
    			player_value  = player1.handValue(i);
    			if ((player_value < casino_value  && casino_value  < 22)||player1.hands.get(i).win == 0) {
    				player1.hands.get(i).setWin(0);
    				player1.update_loss(i);
    				System.out.println(player1.print_win(i));
    			}
    			else if(player_value > casino_value ||casino_value  > 21) {
        			if (player_value == 21 && player1.hands.get(i).ncards==2) player1.update_bj(i);
        			else player1.update_win(i);
					player1.hands.get(i).setWin(1);
					System.out.println(player1.print_win(i));
				}
    			else  if (player_value<21){
    				player1.hands.get(i).setWin(2);
    				System.out.println(player1.print_win(i));
    			}
    			else if (player1.hands.get(i).ncards==2&&player1.hands.get(i).ncards<casino.hands.get(0).ncards){
    				player1.hands.get(i).setWin(1);
					System.out.println(player1.print_win(i));
					if(player1.hands.size()<2||player1.hands.get(i).cards[1].handValue()==1)  player1.update_bj(i);
					else player1.update_win(i);
    			}
    			else if (casino.hands.get(i).ncards==2&&player1.hands.get(i).ncards<casino.hands.get(0).ncards) {
    				player1.hands.get(i).setWin(0);
					System.out.println(player1.print_win(i));
    			}

    			
    			
    		}
    		
    		hand = 0;
 
    		
    		reset(player1, casino, s);
    	}
		
		
	}
    
    
    
    
    public int read_String() {
		valid = false;
		String temp;
		
		input = 'a';
		int i = -1;
			temp = in.nextLine();
			if(temp.length() > 1) {
				input = temp.charAt(0);
				if((temp.charAt(1)) == ' ' && (temp.charAt(0)) == 'b') {
					if(temp.length()>2){
						temp = temp.substring(2,temp.length());
						try {
							i = Integer.parseInt(temp);
							valid = true;
						}
						catch(NumberFormatException e) {
							System.out.println("illegal command");
						}
					}
					else {
						System.out.println("illegal command");
					}
				}
				else if(temp.equals("ad")) {
					input = 'a'; // a for advice
					valid = true;
				}
				else if(temp.equals("st")) {
					input = 't'; // t for statistics
					valid = true;
				}
				else {
					System.out.println("illegal command");
				}
			}
			else {
				if(temp.length()==1) {
					input = temp.charAt(0);
					if(input=='a') {
						System.out.println(input+": illegal command");
						valid = false;
					}
					else if(input == 't') {
						System.out.println(input+": illegal command");
						valid = false;
					}
					else if(input=='q') {
						System.out.println("bye");
						System.exit(0);
					}
					else {
						valid = true;
						i = 0;
					}
				}
				else {
					System.out.println("no input found");
				}
			}
		
		return i;
	}
    /**
     * Set the current state.
     * Normally only called by classes implementing the State interface.
     * @param newState the new state of this context
     */
    void setState(State newState) {
        state = newState;
    }
    void setInsurance(int bet) {
        this.insurance = bet;
    }
    void checkInsurance(Player player1,Dealer casino){
        if(insurance > 0) {
        	if(casino.handValue(0)==21) player1.update_win(insurance);
        	else player1.update_loss(insurance);
        	insurance = 0;
        }
    }
    void setBet(int bet){
    	this.bet=bet;
    }
    void setTempBet(int temp_bet){
    	this.temp_bet=temp_bet;
    }
    
    void setvalid(boolean valid){
    	this.valid=valid;
    }
    
    boolean check_valid() {
    	return this.valid;
    }
    
    void hard_reset(Player player1,Dealer casino,Shoe s,Basic b, HiLo hl, AceFive a5) {
    	Card temp;
    	
    	temp = casino.hands.get(0).cards[1];
		hl.update_counter(temp);
		a5.update_counter(temp);
		System.out.println(casino.handStr(false));
		player1.update_loss(0);
		reset(player1,casino,s);
    }
    
    public void reset(Player player1, Dealer casino, Shoe s)
    {
		s.check();
		// Reset hands
		casino.resetHand();
		player1.resetHand();
		
		setState(new Betting_Stage());
    }
 
    
    public void call_state(Player player1, Dealer casino, Shoe s, Basic b, HiLo hl, AceFive a5) {
    	state.handle_input(this, player1, casino, s, b, hl, a5, hand);
    }
    
    public void handle_input(Player player1, Dealer casino, Shoe s, Basic b, HiLo hl, AceFive a5) {
    	state.handle_input(this, player1, casino, s, b, hl, a5, hand);
    }
    
}