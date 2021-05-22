package blackjack;

/**
 * Objeto que representa o Dealer ao longo do jogo de blackjack.
 * � extendido do objeto Person.
 * @see Person
 */
public class Dealer extends Person {
	/**
	 * Como Dealer � extendido por Person tem como atributos uma ArrayList de hands
	 * Na verdade o Dealer vai apenas utilizar uma m�o.
	 * @see Hand
	 */
	
	/**
	 * Construtor do Dealer
	 * Cria a m�o do Dealer, adicionando uma nova entrada ao ArrayList de hands
	 */
	public Dealer() {
		hands.add(new Hand());
	}
	
	/**
	 * M�todo que devolve a m�o do dealer como uma String
	 * @see Hand#toString(boolean)
	 * @param hideCard Booleano que determina se a hole card j� foi revelada
	 * @return M�o do dealer em String
	 */
	public String handStr(boolean hideCard) {
		String s = "dealer's hand " + hands.get(0).toString(hideCard);
	
		return s;
	}
	
	/**
	 * M�todo que automatiza o turno do dealer
	 * @param shoe Shoe.
	 * @param stat Objeto das estat�sticas para serem atualizadas
	 */
	public void dealerTurn(Shoe shoe, Statistics stat) {
		/**
		 * Vari�vel auxiliar que serve como flag para indicar se o dealer tem blackjack
		 * Isto � logo verificado no �nicio da jogada do dealer
		 */
		boolean flag_BJ = false;
		if(hands.get(0).handTotal() == 21) {
			flag_BJ = true;
		}
		
		/**
		 * O turno do dealer pode ser automatizado da seguinte maneira:
		 * Enquanto o valor da m�o for 16 ou menos o dealer faz hit.
		 * Se o valor da m�o do dealer for 17 ou mais o dealer faz stand
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
	 * M�todo que fornece a primeira carta da m�o do dealer
	 * (a que n�o � a hole card)
	 * @return primeira carta da m�o do dealer
	 */
	public Card knownCard() {
        return hands.get(0).cards[0];
    }
	
}
