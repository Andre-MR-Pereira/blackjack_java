package blackjack;

/**
 * Objeto que implementa a estrat�gia Ace-Five
 */
public class AceFive implements Strategies {

	/**
	 * Contador da estrat�gia em quest�o.
	 * Vai sendo atualizado ao longo da execu��o
	 */
	private int a5_counter;
	
	/**
	 * Aposta m�nima que pode ser realizada pelo jogador
	 */
	int min_bet;
	
	/**
	 * Aposta m�xima que pode ser realizada pelo jogador
	 */
	int max_bet;
	
	/**
	 * Valor apostado pelo jogador na ronda a ser jogada
	 * (ou aposta mais recente feita pelo jogador)
	 */
	int curr_bet;
	
	/**
     * Construtor da estrat�gia Ace-Five.
     * Inicializa todos os atributos.
     * @param min aposta m�nima.
     * @param max aposta m�xima.
     */
	public AceFive(int min, int max) {
		this.a5_counter = 0;
		this.min_bet = min;
		this.max_bet = max;
		this.curr_bet = min;
	}
	
	/**
	 * M�todo que faz reset ao contador da estrat�gia em quest�o (ace-five)
	 * Normalmente utilizado quando o shoe � baralhado.
	 */
	public void reset_count() {
		this.a5_counter = 0;
	}
	
	/**
	 * M�todo que atualiza o contador da estrat�gia Ace-Five
	 * Utilizado sempre que o jogador tem informa��o de uma nova carta.
	 * @param c Carta com a qual o contador � atualizado.
	 */
	public void update_counter(Card c) {
		/**
		 * Quando a carta revelada � um 5, soma-se um ao contador
		 */
		if(c.getCardvalue() == 5)
			a5_counter++;
		
		/**
		 * Quando a carta revelada � um �s, subtrai-se um ao contador
		 */
		else if(c.getCardvalue() == 1)
			a5_counter--;
	}
	
	/**
	 * M�todo que atualiza a aposta mais recente do jogador.
	 * @param b Valor que vai ser atribuido ao atributo curr_bet.
	 */
	public void update_bet(int b) {
		this.curr_bet = b;
	}
	
	/**
	 * Decide qual o valor que o jogador deve apostar, consoante a estrat�gia Ace-Five e consoante o contador
	 * (nenhum dos par�metros do m�todo � utilizado, pelo que normalmente se utiliza null)
	 * @param player m�o do jogador.
     * @param card_dealer carta conhecida do Dealer.
     * @param shoe shoe ativo no jogo.
     * @param p jogador->Serve para saber quantas maos o jogador tem em jogo.
     * @return indicador para qual o conselho a ser fornecido.
	 */
	public int advice(Hand player, Card card_dealer, Shoe shoe, Player p) {
		int res = 0;
		
		/**
		 * Quando o contador � maior ou igual que 2, duplica-se a aposta (at� ao m�ximo)
		 */
		if(a5_counter >= 2) {
			if(curr_bet*2 >= max_bet)
				res = 2;
			else
				res = 1;
		}
		
		/**
		 * Quando o contador � menor ou igual a 1, aposta-se o m�nimo
		 */
		else if(a5_counter <= 1)
			res = -1;
				
		return res;
	}
	
	/**
     * Imprime o conselho da estrat�gia ace-five para a consola.
     * (utilizado no modo interativo)
     * @param res indicador do conselho a ser impresso.
     */
	public void print_advice(int res) {
		switch(res) {
			case 2:
				System.out.println("ace-five	bet " + max_bet);
				break;
			case 1:
				System.out.println("ace-five	bet " + curr_bet*2);
				break;
			case -1:
			  	System.out.println("ace-five	bet " + min_bet);
			  	break;
		  	default:
		  		System.out.println("You shoudn't be here! (A5 advice Error)");
		}
	}
	
	/**
     * Em vez de dar print, retorna a aposta que deve ser realizada (segundo o contador da estrat�gia)
     * (utilizado no modo de simula��o)
     * @param res indicador da jogada a ser escolhida
     * @return aposta que deve ser tomada
     */
	public int make_advice(int res) {
		int value = 0;
		
		switch(res) {
			case 2:
				value = max_bet;
				break;
			case 1:
				value = curr_bet*2;
				break;
			case -1:
				value = min_bet;
				break;
			default:
				System.out.println("You shoudn't be here! (A5 Error)");
		}
		
		return value;
	}
	

}
