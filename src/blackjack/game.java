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
	
	public void simulationstart(int min_bet, int max_bet, int balance, int shoe, int shuffle, int s_number, String strats) {
		Shoe s = new Shoe(shoe, shuffle);
		Dealer casino = new Dealer();
		Player player1 = new Player(balance, min_bet, max_bet);
		StateContext flow = new StateContext(min_bet);
		Basic basic = new Basic();
		AceFive a5 = new AceFive(min_bet, max_bet);
		HiLo hl = new HiLo();
		
		if(strats.equals("HL-AF") || strats.equals("HL"))
			hl.set_strat(1);
		else
			hl.set_strat(2);
		
		int n_shuffles = 0;
		
		while(!flow.check_valid()) {
			flow.sim_input(strats, a5);
			if(flow.input != 'a')
				System.out.println(flow.input);
			flow.handle_input(player1, casino, s, basic, hl, a5, false);
			if(flow.reset_shuffle()) {
				n_shuffles++;
				if(n_shuffles >= s_number)
					flow.setvalid(true);
			}
		}
		
	}
	
}
