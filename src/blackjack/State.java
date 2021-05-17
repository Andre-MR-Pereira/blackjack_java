package blackjack;

interface State {
	/**
	 * Para existir um estado de jogo, será sempre
	 * necessário que seja possível receberas diferentes partes que 
	 * contextualizam a jogada em quetão e partir dela.
	 */
	
	/**
	 * Recebe os parâmetros que definem a jogada e toma uma decisão para avançar no jogo.
	 * @param context situação do jogo em que estamos a jogar
	 * @see StateContext
	 * @param player1 Jogador a ser analisado
	 * @param casino Dealer da mesa
	 * @param s Shoe a ser usado
	 * @param b Guia para a estratégia básica
	 * @param hl Guia para a estratégia Hi Low
	 * @param a5 Guia para a estratégia AceFive
	 * @param hand mão do jogador a ser analisada
	 * @param debugger se o jogo está a ser corrido em modo de debug
	 */
	void handle_input(StateContext context, Player player1, Dealer casino, Shoe s, Basic b, HiLo hl, AceFive a5, int hand,boolean debugger);
}
