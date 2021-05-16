package main;

public class error_catcher {
	
	public error_catcher() {
		// TODO Auto-generated constructor stub
	}
	
	public void money_valid(int min_bet, int max_bet, int balance) {
		if (min_bet<1||max_bet<10*min_bet||max_bet>20*min_bet||balance<50*min_bet) {
			 System.err.println("error in the paramenteres value");
			 System.exit(1);
		}
			
	}
	
	public void shoe_valid(int shoe, int shuffle) {
		if (shoe<4||shoe>8||shuffle<10||shuffle>100) {
			 System.err.println("error in the paramenteres value");
			 System.exit(1);
		}
			
	}
	public void strategy_valid(String strat) {
		if (strat.equals("HL-AF")||strat.equals("HL")||strat.equals("BS")||strat.equals("BS-AF")) {
			return;
		}
		 System.err.println("error in the paramenteres value");
		 System.exit(1);
	}

}
