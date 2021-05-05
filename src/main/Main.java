package main;
import blackjack.*;



public class Main {
	
	public static void main(String[] args){
		game g = new game();
		error_catcher er = new error_catcher();
		int min_bet, max_bet, balance, shoe, shuffle,s_shuffle;
		int count = args.length;
		if (args[0].equals("-i")){
			if (count!=6) {
				System.err.println("Wrong Number of Arguments");
                System.exit(1);
			}
            try {
            	min_bet = Integer.parseInt(args[1]);
            	max_bet = Integer.parseInt(args[2]);
            	balance = Integer.parseInt(args[3]);
            	shoe = Integer.parseInt(args[4]);
            	shuffle = Integer.parseInt(args[5]);
            	er.money_valid(min_bet,max_bet,balance);
            	er.shoe_valid(shoe, shuffle);
            	
            	g.interactivestart(min_bet, max_bet, balance, shoe, shuffle);
                
            } catch (NumberFormatException e) {
                System.err.println("Arguments " + args[1]+ ", " + args[2]+ ", "+  args[3]+ ", " + args[4]+ "and " + args[5]+ " must be an integer.");
                System.exit(1);
            }
            
            

        }
		else if (args[0].equals("-s")){

			if (count!=6) {
				System.err.println("Wrong Number of Arguments");
                System.exit(1);
			}
			try {
            	min_bet = Integer.parseInt(args[1]);
            	max_bet = Integer.parseInt(args[2]);
            	balance = Integer.parseInt(args[3]);
            	er.money_valid(min_bet,max_bet,balance);
                
            } catch (NumberFormatException e) {
                System.err.println("Arguments " + args[1]+ ", " + args[2]+ ", "+  args[3]+ ", "+ " must be an integer.");
                System.exit(1);
            }
		}
		else if (args[0].equals("-d")) {
			if (count!=8) {
				System.err.println("Wrong Number of Arguments");
                System.exit(1);
			}

			try {
				min_bet = Integer.parseInt(args[1]);
            	max_bet = Integer.parseInt(args[2]);
            	balance = Integer.parseInt(args[3]);
            	shoe = Integer.parseInt(args[4]);
            	shuffle = Integer.parseInt(args[5]);
            	s_shuffle = Integer.parseInt(args[6]);
            	er.money_valid(min_bet,max_bet,balance);
            	er.shoe_valid(shoe, shuffle);
            	er.strategy_valid(args[7]);
            } catch (NumberFormatException e) {
                System.err.println("Arguments " + args[1]+ ", " + args[2]+ ", "+  args[3]+ ", " + args[4]+ ", " + args[5]+ "and " + args[6]+ " must be an integer.");
                System.exit(1);
            }
		}
		else {
			System.out.println("invalid mode");
		}
		System.out.println("done");

	}
}
