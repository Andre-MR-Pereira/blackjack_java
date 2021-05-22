package blackjack;

import java.util.List;

/**
 * Classe que corre o jogo.
 * Dependendo do modo escolhido, corre um dos métodos desta classe.
 * Dentro de qualquer um deles, os objetos essenciais ao jogo de blackjack são criados.
 * Faz-se um ciclo onde corre o flow do jogo.
 * @see StateContext
 * 
 * A única diferença é a maneira como se escolhe o input em cada modo:
 * Modo interativo: O input é lido da consola
 * @see StateContext#read_String
 * Modo de debug: O input é lido do ficheiro
 * @see StateContext#read_File
 * Modo de simulação: O input vem de um advice da estratégia especificada
 * @see StateContext#sim_input
 */

public class Game {
	/**
	 * Começa e chama o estado usando o modo iterativo
	 * @see StateContext
	 * @param min_bet aposta mìnima
	 * @param max_bet aposta màxima
	 * @param balance dinheiro inicial
	 * @param shoe número de decks
	 * @param shuffle qual a percentagem onde se deve dar shuffle
	 */
	public void interactivestart(int min_bet, int max_bet,int balance,int shoe,int shuffle) {
		Shoe s = new Shoe(shoe,shuffle);
		Dealer casino = new Dealer();
		Player player1 = new Player(balance,min_bet,max_bet);
		StateContext flow = new StateContext(min_bet,balance);
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
	
	/**
	 * Começa e chama o estado usando o modo debug
	 * @see StateContext
	 * @param min_bet aposta mìnima
	 * @param max_bet aposta màxima
	 * @param balance dinheiro inicial
	 * @param shoe Deck
	 * @param moves lista de comandos
	 */
	public void debugstart(int min_bet, int max_bet,int balance,Shoe shoe,List<String> moves){
		Dealer casino = new Dealer();
		Player player1 = new Player(balance,min_bet,max_bet);
		StateContext flow = new StateContext(min_bet,balance);
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
	
	/**
	 * Começa e chama o estado usando o modo de simulação.
	 * @see StateContext
	 * @param min_bet aposta mìnima
	 * @param max_bet aposta màxima
	 * @param balance dinheiro inicial
	 * @param shoe número de decks
	 * @param s_number número de shuffles até acabar
	 * @param strats estratégia empregue na simulação
	 * @param shuffle percentagem do shoe para dar shuffle
	 */
	public void simulationstart(int min_bet, int max_bet, int balance, int shoe, int shuffle, int s_number, String strats) {
		Shoe s = new Shoe(shoe, shuffle);
		Dealer casino = new Dealer();
		Player player1 = new Player(balance, min_bet, max_bet);
		StateContext flow = new StateContext(min_bet,balance);
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
