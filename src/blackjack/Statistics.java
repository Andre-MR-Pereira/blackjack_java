package blackjack;

public class Statistics {
	/**
	 * Classe que guarda as estatisticas
	 * @param n_victories número vitórias
	 * @param n_defeats número derrotas  
	 * @param n_pushes número de empates
	 * @param n_player_blackjacks número total de blackjacks do player
	 * @param n_dealer_blackjacks número total de blackjacks do dealer
	 * @param initial_balance dinheiro inicial
	 */
	int n_pushes;
	int n_victories;
	int n_defeats;
	int n_player_blackjacks;
	int n_dealer_blackjacks;
	int initial_balance;
	
	public Statistics(int initial_balance) {
		n_pushes=0;
		n_victories=0;
		n_defeats=0;
		n_player_blackjacks=0;
		n_dealer_blackjacks=0;
		this.initial_balance=initial_balance;
	}
	
	public void update_game_result(int win){
		if(win ==0 ) {
			n_defeats++;
		}else if (win ==1) {
			n_victories++;
		}else {
			n_pushes++;
		}
	}
	public void update_blackjack(boolean player) {
		if(player) {
			n_player_blackjacks++;
		}
		else {
			n_dealer_blackjacks++;
		}
	}
	public void print_statistics(double balance) {
		System.out.println("BJ P/D		 "+n_player_blackjacks+"/"+n_dealer_blackjacks);
		System.out.println("Win 		  "+n_victories);
		System.out.println("Lose		  "+n_defeats);
		System.out.println("Push		  "+n_pushes);
		System.out.println("Balance	      "+(int)balance+"("+(int)(balance/initial_balance*100)+"%)");
	}
}
