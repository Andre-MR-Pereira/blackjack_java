package blackjack;

import java.util.Scanner;

public class StateContext {
    private State state;
    boolean valid;
    char input;
	int bet, temp_bet;
	
    Scanner in = new Scanner(System.in);
    
    public StateContext(int min_bet) {
        state = new Betting_Stage();
        bet = min_bet;
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
							System.out.println("invalid input");
						}
					}
					else {
						System.out.println("too small for bet input");
					}
				}
				else if(temp.equals("ad"))
					input = 'a'; // a for advice
				else if(temp.equals("st"))
					input = 't'; // t for statistics
				else {
					System.out.println("not a valid input to search for a int");
				}
			}
			else {
				if(temp.length()==1) {
					input = temp.charAt(0);
					if(input=='q') {
						System.out.println("bye");
						System.exit(0);
					}
					valid = true;
					i = 0;
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
    
    void setBet(int bet){
    	this.bet=bet;
    }
    void setTempBet(int temp_bet){
    	this.temp_bet=temp_bet;
    }
    
    void setvalid(boolean valid){
    	this.valid=valid;
    }
    

    public void Resolution(StateContext context,Player player1, Dealer casino, Shoe s, int win) {
		if(win == 1) {
			player1.update_win(0);
			System.out.println("player wins and his current balance is " + (int) player1.getBalance());
			
		}
		else if(win == 0) {
			player1.update_loss(0);
			System.out.println("player loses and his current balance is " + (int) player1.getBalance());
			
		}
		else if(win == 3) {
			player1.update_bj(0);
			System.out.println("player wins and his current balance is " + (int) player1.getBalance());
			
		}
		else
			System.out.println("player pushes and his current balance is " + (int) player1.getBalance());
		
		s.check();
		// Reset hands
		casino.resetHand();
		player1.resetHand();
		
		setState(new Betting_Stage());
		
	}
    
    public void handle_input(Player player1, Dealer casino, Shoe s) {
    	valid = false;
		while(!valid){
			temp_bet=read_String();
			if(valid) {	
				state.handle_input(this, player1, casino, s);
			}
		}
        
    }
}