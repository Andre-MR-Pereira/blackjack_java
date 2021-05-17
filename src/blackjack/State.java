package blackjack;

interface State {
	/**
	 * Para existir um estado de jogo, ser� sempre
	 * necess�rio que seja poss�vel receberas diferentes partes que 
	 * contextualizam a jogada em quet�o e partir dela.
	 */
	
	/**
	 * Recebe os par�metros que definem a jogada e toma uma decis�o para avan�ar no jogo.
	 * @param context situa��o do jogo em que estamos a jogar
	 * @see StateContext
	 * @param player1 Jogador a ser analisado
	 * @param casino Dealer da mesa
	 * @param s Shoe a ser usado
	 * @param b Guia para a estrat�gia b�sica
	 * @param hl Guia para a estrat�gia Hi Low
	 * @param a5 Guia para a estrat�gia AceFive
	 * @param hand m�o do jogador a ser analisada
	 * @param debugger se o jogo est� a ser corrido em modo de debug
	 */
	void handle_input(StateContext context, Player player1, Dealer casino, Shoe s, Basic b, HiLo hl, AceFive a5, int hand,boolean debugger);
}
