package blackjack;

public class game {
	
	public void interactivestart(int min_bet, int max_bet,int balance,int shoe,int shuffle) {
		Shoe s = new Shoe(shoe,shuffle);
		Dealer casino = new Dealer();
		Player player1 = new Player(balance,min_bet,max_bet);
		StateContext flow = new StateContext(min_bet);
		
		while(true) {
			flow.handle_input(player1,casino, s);
		}
	}
	/*
	String temp;
	char input;
	boolean valid;
	
	public int read_String() {
		valid = false;
		Scanner in = new Scanner(System.in);
		input = 'a';
		int i = -1;
			temp = in.nextLine();
			if(temp.length()>1) {
				input = temp.charAt(0);
				if((temp.charAt(1)) == ' ' && (temp.charAt(0)) == 'b') {
					if(temp.length()>2){
						temp=temp.substring(2,temp.length());
						try {
							i = Integer.parseInt(temp);
							valid = true;
						}
						catch(NumberFormatException e) {
							System.out.println("invalid input");
						}
					}
					else {
						System.out.println("too small for int input");
					}
					
				}else {
					System.out.println("not a valid input to search for a int");
				}
			}else {
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

	
	
	public void interactivestart(int min_bet, int max_bet,int balance,int shoe,int shuffle) {
		Shoe s = new Shoe(shoe,shuffle);
		Dealer casino = new Dealer();
		Player player1 = new Player(balance,min_bet,max_bet);
		Scanner in = new Scanner(System.in);
		int win = 0;
		int bet =min_bet, temp_bet=0; 
		boolean in_game= true, in_play = true;
		while(in_game) {
			in_play = true;
			valid = false;
			while(!valid){
				temp_bet=read_String();
				if(valid) {
					if(input=='b') {
						
						if (temp_bet == 0) {
							 temp_bet=bet;
						}
						if (temp_bet < min_bet || temp_bet> max_bet|| temp_bet> balance) {
							valid = false;
							System.out.println("invalid bet ammount ");
							
						}
						else {
							bet =temp_bet;
							player1.hands.get(0).setBet(bet);
							System.out.println("player is betting "+bet);
						}
					}
					else if (input=='$') {
						System.out.println("player current balance is " + player1.getBalance());
						valid = false;
					}
					else {
						System.out.println(input+":invalid input");
						valid = false;
					}
				}
			}
			valid = false;
			while(!valid){
				bet=read_String();
				if(valid) {
					if(input=='d') {
						casino.hit(s.deal(),0);
						casino.hit(s.deal(),0);
						player1.hit(s.deal(),0);
						player1.hit(s.deal(),0);
					}
					else if (input=='$') {
						System.out.println("player current balance is " + player1.getBalance());
						valid = false;
					}
					else {
						System.out.println(input+":invalid input");
						valid = false;
					}
				}
			}
			
			
			System.out.println("dealer's hand "+ casino.hands.get(0).toString(true));
			System.out.println("player's hand "+ player1.hands.get(0)+" ("+player1.handValue()[0]+")");
			if(player1.handValue()[0]==21) System.out.println("blackjack!!");
			
			while(in_play) {
				valid = false;
				while(!valid){
					bet=read_String();
					if(valid) {
						if(input == 'h') {
							player1.hit(s.deal(),0);
							System.out.println("player's hand "+ player1.hands.get(0) + " ("+player1.handValue()[0]+")");
							if(player1.handValue()[0]>21) {
								
								System.out.println("player busts");
								System.out.println("dealer's hand "+ casino.hands.get(0));
								in_play = false;
								win = 0;
							}
						}
						else if (input=='$') {
							System.out.println("player current balance is " + player1.getBalance());
							valid = false;
						}
						else if(input == 's'){
							System.out.println("player stands");
							casino.dealerTurn(s);
							in_play = false;
							if(player1.handValue()[0]>casino.handValue()[0]||casino.handValue()[0]>21) win = 1;
							else if (player1.handValue()[0]<casino.handValue()[0]) win = 0;
							else if (player1.handValue()[0]==21) win = 3;
							else	win = 2;
						}
						else {
							System.out.println(input+":invalid input");
							valid = false;
							
						}
					}
				}
							
			}
			in_play = true;
			
			if(win == 1) {
				player1.update_win(0);
				System.out.println("player wins and his current balance is " + player1.getBalance());
				
			}
			else if(win == 0) {
				player1.update_loss(0);
				System.out.println("player loses and his current balance is " + player1.getBalance());
				
			}
			else if(win == 3) {
				player1.update_bj(0);
				System.out.println("player wins and his current balance is " + player1.getBalance());
				
			}
			else
				System.out.println("player pushes and his current balance is " + player1.getBalance());
			
			s.check();
			// Reset hands
			casino.resetHand();
			player1.resetHand();
		}
		in.close();	
	}*/
	
}
