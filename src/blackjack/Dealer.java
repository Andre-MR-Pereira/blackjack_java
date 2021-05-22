package blackjack;

/**
 * Objeto que representa o Dealer ao longo do jogo de blackjack.
 * É extendido do objeto Person.
 * @see Person
 */
public class Dealer extends Person {
	/**
	 * Como Dealer é extendido por Person tem como atributos uma ArrayList de hands
	 * Na verdade o Dealer vai apenas utilizar uma mão.
	 * @see Hand
	 */
	
	/**
	 * Construtor do Dealer
	 * Cria a mão do Dealer, adicionando uma nova entrada ao ArrayList de hands
	 */
	public Dealer() {
		hands.add(new Hand());
	}
	
	/**
	 * Método que devolve a mão do dealer como uma String
	 * @see Hand#toString(boolean)
	 * @param hideCard Booleano que determina se a hole card já foi revelada
	 * @return Mão do dealer em String
	 */
	public String handStr(boolean hideCard) {
		String s = "dealer's hand " + hands.get(0).toString(hideCard);
	
		return s;
	}
	
	/**
	 * Método que automatiza o turno do dealer
	 * @param shoe Shoe.
	 * @param stat Objeto das estatísticas para serem atualizadas
	 */
	public void dealerTurn(Shoe shoe, Statistics stat) {
		/**
		 * Variável auxiliar que serve como flag para indicar se o dealer tem blackjack
		 * Isto é logo verificado no ínicio da jogada do dealer
		 */
		boolean flag_BJ = false;
		if(hands.get(0).handTotal() == 21) {
			flag_BJ = true;
		}
		
		/**
		 * O turno do dealer pode ser automatizado da seguinte maneira:
		 * Enquanto o valor da mão for 16 ou menos o dealer faz hit.
		 * Se o valor da mão do dealer for 17 ou mais o dealer faz stand
		 */
		while(hands.get(0).handTotal() <= 16) {
			System.out.println(this.handStr(false) + " (" + this.handValue(0)+")");
			System.out.println("dealer hits");
			hands.get(0).addCard(shoe.deal());
		}
		
		if(hands.get(0).handTotal() > 21) {
			System.out.println(this.handStr(false) + " (" + this.handValue(0) + ")");
			System.out.println("dealer busts");
		}
		else {
			if(flag_BJ) {
				System.out.println(this.handStr(false) + " (" + this.handValue(0) + ")");
				System.out.println("blackjack!!");
				stat.update_blackjack(false);
			}
			else {
				System.out.println(this.handStr(false) + " (" + this.handValue(0) + ")");
				System.out.println("dealer stands");
			}
		}
	}
	
	/**
	 * Método que fornece a primeira carta da mão do dealer
	 * (a que não é a hole card)
	 * @return primeira carta da mão do dealer
	 */
	public Card knownCard() {
        return hands.get(0).cards[0];
    }
	
}
