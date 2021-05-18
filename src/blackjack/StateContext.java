package blackjack;

import java.util.List;
import java.util.Scanner;

public class StateContext {
	String holder;
    private State state;
    boolean valid, sim_ad, shuffle_flag;
    char input;
	int bet, temp_bet;
	int hand;
	Scanner in = new Scanner(System.in);
	Statistics stat;
    boolean finish_split;
    public StateContext(int min_bet,int balance) {
        state = new Betting_Stage();
        bet = min_bet;
        temp_bet = 0;
        hand = 0;
        valid = false;
        sim_ad = true;
        shuffle_flag = false;
        holder = null;
        stat = new Statistics(balance);
        finish_split=false;
    }
    
    public void set_hands(int h) {
    	this.hand = h;
    }
    
    public void sim_input(String strats, AceFive a5) {
    	
    	if(state instanceof Betting_Stage) {
    		input = 'b';
    		if(strats.equals("BS-AF") || strats.equals("HL-AF"))
    			temp_bet = a5.make_advice(a5.advice(null, null, null, null));
    	}
    	
    	else if(state instanceof Deal_Stage)
    		input = 'd';
    	
    	else {
    		if(sim_ad) {
    			input = 'a';
    			sim_ad = false;
    		}
    		else
    			sim_ad = true;
    	}
    }
    
    public void set_input(char in) {
    	this.input = in;
    }
    
    public boolean reset_shuffle() {
    	boolean flag = this.shuffle_flag;
    	this.shuffle_flag = false;
    	return flag;
    }
    
    public void Resolution(StateContext context,Player player1, Dealer casino, Shoe s, Basic b, HiLo hl, AceFive a5, boolean debugger) {
    	Card temp = new Card(0, 0);
    	
    	if(hand < player1.hands.size() - 1) {
			hand++;
			System.out.println("playing hand nº " + (hand+1) + "...");
			temp = s.deal();
			player1.hit(temp, hand);
			hl.update_counter(temp);
			a5.update_counter(temp);
			System.out.println(player1.handStr(hand));
			setState(new First_Hand_Stage());
		}
    	
    	else {
    		casino.dealerTurn(s,stat);
    		checkInsurance(player1, casino);
			for(int i = 1; i < casino.handSize(0); i++) {
				temp = casino.hands.get(0).cards[i];
				hl.update_counter(temp);
				a5.update_counter(temp);
			}
			
			int casino_value = casino.handValue(0);
			int player_value = 0;
			
    		for(int i = 0; i < player1.hands.size(); i++) {
    			player_value  = player1.handValue(i);
    			
    			if ((player_value < casino_value  && casino_value  < 22) || player1.hands.get(i).win == 0) {
    				player1.hands.get(i).setWin(0);
    				stat.update_game_result(0);
    				System.out.println(player1.print_win(i));
    				
    				if(bet - a5.min_bet < a5.min_bet)
    					temp_bet = a5.min_bet;
    				else
    					temp_bet = bet - a5.min_bet;
    			}
    			
    			else if(player_value > casino_value || casino_value  > 21) {
        			if (player_value == 21 && player1.hands.get(i).ncards == 2 && (player1.hands.size() < 2 || player1.hands.get(i).cards[1].handValue() == 1)) 
        				player1.update_bj(i);
        			else 
        				player1.update_win(i);
					player1.hands.get(i).setWin(1);
					System.out.println(player1.print_win(i));
					stat.update_game_result(1);
					if(bet + a5.min_bet > a5.max_bet)
    					temp_bet = a5.max_bet;
    				else
    					temp_bet = bet + a5.min_bet;
				}
    			
    			else  if (player_value < 21) {
    				player1.hands.get(i).setWin(2);
    				player1.update_draw(i);
    				System.out.println(player1.print_win(i));
    				stat.update_game_result(2);
    			}
    			
    			else if (player1.hands.get(i).ncards == 2 && player1.hands.get(i).ncards < casino.hands.get(0).ncards) {
    				player1.hands.get(i).setWin(1);
					System.out.println(player1.print_win(i));
					if(player1.hands.size() < 2 || player1.hands.get(i).cards[1].handValue() == 1)  
						player1.update_bj(i);
					else 
						player1.update_win(i);
					stat.update_game_result(1);
					if(bet + a5.min_bet > a5.max_bet)
    					temp_bet = a5.max_bet;
    				else
    					temp_bet = bet + a5.min_bet;
    			}
    			
    			else if (casino.hands.get(0).ncards == 2 && player1.hands.get(i).ncards < casino.hands.get(0).ncards) {
    				player1.hands.get(i).setWin(0);
					System.out.println(player1.print_win(i));
					stat.update_game_result(0);
					if(bet - a5.min_bet < a5.min_bet)
    					temp_bet = a5.min_bet;
    				else
    					temp_bet = bet - a5.min_bet;
    			}
    			
    			else {
                    player1.hands.get(i).setWin(2);
                    player1.update_draw(i);
                    System.out.println(player1.print_win(i));
                    stat.update_game_result(2);
                }
    		}
    		
    		hand = 0;
    		
    		shuffle_flag = reset(player1, casino, s, hl, a5, debugger);
    	}
		
		
	}
    
    public int read_File(List<String> moves){
		valid = false;	
		input = 'a';
		int i = -1;
		
		if(!moves.isEmpty()) {
			if(moves.get(0).equals("ad")) {
				input = 'a'; // a for advice
				valid = true;
			}
			else if(moves.get(0).equals("st")) {
				input = 't'; // t for statistics
				valid = true;
			}else if(moves.get(0).length()==1) {
				input = moves.get(0).charAt(0);
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
				}else if(input=='b') {
					valid = true;
					try {
						i = Integer.parseInt(moves.get(1));
					}
					catch(NumberFormatException e) {
						i=0;
					}
				}
				else {
					valid = true;
					i = 0;
				}
			}else {
				System.out.println(moves.get(0)+": illegal command");
			}
			System.out.println(input);
			moves.remove(0);
			if(i>0) {
				moves.remove(0);
			}
		}
		
		return i;
		
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
						System.out.println("b " + temp +": illegal command");
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
				System.out.println(temp + ": illegal command");
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
    public void setState(State newState) {
        state = newState;
    }
    
    public void checkInsurance(Player player1, Dealer casino) {
        if(player1.check_insurance() > 0) {
        	if(casino.handValue(0) == 21 && casino.handSize(0) == 2) {
        		player1.insurance_win();
        		System.out.println("player wins insurance");
        	}
        		
        	
        	player1.setInsurance(0);
        }
    }
    
    public void setBet(int bet){
    	this.bet=bet;
    }
    public void setTempBet(int temp_bet){
    	this.temp_bet=temp_bet;
    }
    
    public void setvalid(boolean valid){
    	this.valid=valid;
    }
    
    public void setFinishSplit(boolean finish_split){
    	this.finish_split=finish_split;
    }
    
    public boolean check_valid() {
    	return this.valid;
    }
    
    public void hard_reset(Player player1,Dealer casino,Shoe s,Basic b, HiLo hl, AceFive a5, boolean debugger) {
    	Card temp;
    	
    	temp = casino.hands.get(0).cards[1];
		hl.update_counter(temp);
		a5.update_counter(temp);
		System.out.println(casino.handStr(false));
		reset(player1, casino, s, hl, a5, debugger);
    }
    
    public boolean reset(Player player1, Dealer casino, Shoe s, HiLo hl, AceFive a5, boolean debugger) {
    	boolean res = false;
    	
    	if(!debugger) {
    		if(s.check()) {
    			hl.reset_count();
    			a5.reset_count();
    			res = true;
    		}
    	}
    	
		// Reset hands
		casino.resetHand();
		player1.resetHand();
		setFinishSplit(false);
		setState(new Betting_Stage());
		
		return res;
    }
    
    public void handle_input(Player player1, Dealer casino, Shoe s, Basic b, HiLo hl, AceFive a5,boolean debugger) {
    	state.handle_input(this, player1, casino, s, b, hl, a5, hand, debugger);
    }
    
}