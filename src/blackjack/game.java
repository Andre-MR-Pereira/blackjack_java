package blackjack;
import java.util.Scanner;
public class game {
	
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
			
			input = in.next().charAt(0);
			bet = in.nextInt();
			
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
