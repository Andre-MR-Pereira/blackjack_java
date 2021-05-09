package blackjack;
import java.util.Scanner;
public class game {
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
					valid = true;
					i = 0;
				}
				else {		
					System.out.println("no input found");
					}
				
			}				
		
		return 0;
	}
	
	public game() {
		// TODO Auto-generated constructor stub
	}
	
	public void interactivestart(int min_bet, int max_bet,int balance,int shoe,int shuffle) {
		Shoe s = new Shoe(shoe,shuffle);
		Dealer casino = new Dealer();
		Player player1 = new Player(balance,min_bet,max_bet);
		Scanner in = new Scanner(System.in);
		char input;
		int bet =0; 
		boolean in_game= true, in_play = true;
		while(in_game) {
			
			valid = false;
			while(!valid){
				bet=read_String();
				if (bet == -1) {	
					valid = false;
					System.out.println("No bet detected");
				}	
			}
			player1.hands.get(0).setBet(bet);
			casino.hit(s.deal(),0);
			casino.hit(s.deal(),0);
			player1.hit(s.deal(),0);
			player1.hit(s.deal(),0);
			System.out.println("dealer's hand "+ casino.hands.get(0).toString(true));
			System.out.println("player's hand "+ player1.hands.get(0)+" ("+player1.handValue()[0]+")");
			while(in_play) {
				
				input = in.next().charAt(0);
				
				if(input == 'h') {
					player1.hit(s.deal(),0);
					System.out.println("player's hand "+ player1.hands.get(0) + " ("+player1.handValue()[0]+")");
					if(player1.handValue()[0]>21) {
						
						System.out.println("player busts");
						in_play = false;
					}
				}
				else {
					System.out.println("player stands");
					in_play = false;
				}
				
			}
			in_play = true;
			System.out.println("dealer's hand "+ casino.hands.get(0));
			casino.dealerTurn(s);
			
			// Falta dar print ao resultado do jogo (WLD) e ao balance do player
			
			// Reset hands
			casino.resetHand();
			player1.resetHand();
		}
		in.close();	
		
	}
	
}
