package blackjack;

public class Deal_Stage implements State{

	public void handle_input(StateContext context,Player player1, Dealer casino, Shoe s, Basic b, HiLo hl, AceFive a5, int hand,boolean debugger) {
		if(context.input=='d') {
			Card temp = null;
			
			temp = s.deal();
			casino.hit(temp, 0);
			hl.update_counter(temp);
			a5.update_counter(temp);
			
			casino.hit(s.deal(), 0);
			// Hole Card, so we don't update strategy counters
			
			temp = s.deal();
			player1.hit(temp, 0);
			hl.update_counter(temp);
			a5.update_counter(temp);
			
			temp = s.deal();
			player1.hit(temp, 0);
			hl.update_counter(temp);
			a5.update_counter(temp);
			
			context.setState(new First_Hand_Stage());
			System.out.println(casino.handStr(true));
			System.out.println(player1.handStr(0));
			if(player1.handValue(0)==21) {
				System.out.println("blackjack!!");
				context.stat.update_blackjack(true);
			}
		}
		else if (context.input=='$') {
			System.out.println("player current balance is " + (int) player1.getBalance());

		}
		else if(context.input == 't') {
			context.stat.print_statistics(player1.getBalance());

		}
		
		else if(context.input == 'a')
			System.out.println("ad: illegal command");
			
		else
			System.out.println(context.input + ": illegal command");

	}
}
