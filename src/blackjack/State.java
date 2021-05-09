package blackjack;

interface State {
	void handle_input(StateContext context,Player player1, Dealer casino, Shoe s);
}
