package blackjack;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class game {
	
	public void interactivestart(int min_bet, int max_bet,int balance,int shoe,int shuffle) {
		Shoe s = new Shoe(shoe,shuffle);
		Dealer casino = new Dealer();
		Player player1 = new Player(balance,min_bet,max_bet);
		StateContext flow = new StateContext(min_bet);
		Basic basic = new Basic();
		HiLo hl = new HiLo();
		AceFive a5 = new AceFive(min_bet, max_bet);
		
		while(!flow.check_valid()) {
			flow.setTempBet(flow.read_String());
			if(flow.check_valid()) {
				flow.handle_input(player1, casino, s, basic, hl, a5,false);
				flow.setvalid(false);
			}
		}
	}
	
	public void debugstart(int min_bet, int max_bet,int balance,Shoe shoe,List<String> moves){
		Dealer casino = new Dealer();
		Player player1 = new Player(balance,min_bet,max_bet);
		StateContext flow = new StateContext(min_bet);
		Basic basic = new Basic();
		HiLo hl = new HiLo();
		AceFive a5 = new AceFive(min_bet, max_bet);
		
		while(!flow.check_valid()) {
			flow.setTempBet(flow.read_File(moves));
			if(flow.check_valid()) {
				flow.handle_input(player1, casino, shoe, basic, hl, a5,true);
				flow.setvalid(false);
			}
		}
	}
	
}
