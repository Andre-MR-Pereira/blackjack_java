package main;

/**
 * Classe auxiliar para verificar se os argumentos fornecidos est�o dentro dos requisitos.
 */
public class error_catcher {
	
	/**
	 * M�todo para verificar se a aposta m�nima, m�xima e o balance inicial do jogador cumprem os requisitos.
	 * @param min_bet aposta m�nima.
	 * @param max_bet aposta m�xima.
	 * @param balance dinheiro inicial do jogador.
	 */
	public void money_valid(int min_bet, int max_bet, int balance) {
		if (min_bet < 1 || max_bet < 10*min_bet || max_bet > 20*min_bet || balance < 50*min_bet) {
			 System.err.println("error in the paramenteres value");
			 System.exit(1);
		}	
	}
	
	/**
	 * M�todo para verificar se o n�mero de decks do shoe e a percentagem de shuffle cumprem os requisitos.
	 * @param shoe n�mero de decks de 52 cartas do shoe.
	 * @param shuffle percentagem para shuffle.
	 */
	public void shoe_valid(int shoe, int shuffle) {
		if (shoe < 4 || shoe > 8 || shuffle < 10 || shuffle > 100) {
			 System.err.println("error in the paramenteres value");
			 System.exit(1);
		}	
	}
	
	/**
	 * Verificar se as estrat�gias s�o v�lidas.
	 * @param strat String com as estrat�gias a ser utilizadas.
	 */
	public void strategy_valid(String strat) {
		if (strat.equals("HL-AF") || strat.equals("HL") || strat.equals("BS") || strat.equals("BS-AF"))
			return;
		
		System.err.println("error in the paramenteres value");
		System.exit(1);
	}

}
