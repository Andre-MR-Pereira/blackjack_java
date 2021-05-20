package main;
import blackjack.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner; // Import the Scanner class to read text files


public class Main {
	
	public static void main(String[] args) throws FileNotFoundException {
		Game g = new Game();
		error_catcher er = new error_catcher();
		int min_bet, max_bet, balance, shoe, shuffle, s_shuffle;
		Shoe debugShoe=null;
		List<String> moves=new ArrayList<String>();  
		Scanner reader;
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
                
                g.simulationstart(min_bet, max_bet, balance, shoe, shuffle, s_shuffle, args[7]);
            } catch (NumberFormatException e) {
                System.err.println("Arguments " + args[1]+ ", " + args[2]+ ", "+  args[3]+ ", " + args[4]+ ", " + args[5]+ "and " + args[6]+ " must be an integer.");
                System.exit(1);
            }
		}
		else if (args[0].equals("-d")) {
			if (count!=6) {
				System.err.println("Wrong Number of Arguments");
                System.exit(1);
			}

			try {
				min_bet = Integer.parseInt(args[1]);
            	max_bet = Integer.parseInt(args[2]);
            	balance = Integer.parseInt(args[3]);
            	
            	try(FileInputStream shoe_file = new FileInputStream(args[4])){
            		debugShoe=new Shoe(shoe_file);
            	}catch (IOException e) {
            		System.out.println("Couldn´t open shoe file");
                    e.printStackTrace();
                    System.exit(1);
                }
            	er.money_valid(min_bet,max_bet,balance);
            	try(FileInputStream cmd_file = new FileInputStream(args[5])){
            		reader=new Scanner(cmd_file);
            		String data=null;
            		while (reader.hasNextLine()) {
                        data = reader.nextLine();
                        if(data==null) {
                        	System.out.println("cmd file is empty");
        					System.exit(1);
                        }
            		}
            		String[] fields =data.split(" ");
            		for(int i=0;i<fields.length;i++) {
            			moves.add(fields[i]);
            		}
            	}catch (IOException e) {
            		System.out.println("Couldn´t open moves file");
                    e.printStackTrace();
                    System.exit(1);
                }
            	g.debugstart(min_bet, max_bet, balance, debugShoe, moves);

            } catch (Exception e) {
                System.err.println("An error on debug was catched.");
                System.exit(1);
            }
		}
		else {
			System.out.println("invalid mode");
		}
		System.out.println("done");

	}
}
