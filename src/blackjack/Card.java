package blackjack;

/**
 * Objeto que representa uma carta jogável
 *
 */
public class Card {
	
	/**
	 * Valor da carta 
	 * (pode variar entre 1=Ás até 13=Rei)
	 */
	private int value;
	
	/**
	 * Naipe da carta 
	 * (1=Hearts, 2=Spades, 3=Clubs, 4=Diamonds)
	 */
	private int suit; 
	
	/**
	 * Construtor do objeto 'Carta'
	 * @param face Valor da carta que vai ser criada
	 * @param suit Naipe da carta que vai ser criada
	 */
	public Card(int face, int suit) {
		this.value = face;
		this.suit = suit;
	}
    
	/**
	 * Método para saber o valor da carta em blackjack
	 * (se for um Rei, Dama ou Valete, vale 10,
	 *  se for qualquer outra carta vale o próprio value)
	 *  Nota: Considera-se que o Ás vale 1
	 * @return valor da carta numa mão de blackjack
	 */
    public int handValue() {
        if(value > 10) {
            return 10;
        }
        return value;
    }    
	
    /**
     * Método para obter a string da face da carta, consoante o seu atributo value
     * @return String com a face da carta
     */
	public String getCardface() {

		String name = "?";

		if (this.value == 1) {		
			name = "A";
		}
		else if (this.value == 11) {
			name = "J";
		}
		else if (this.value == 12) {
			name = "Q";
		}
		else if (this.value == 13) {
			name = "K";
		}
		else {
			name = String.valueOf(this.value);
		}
		
		return name;
	}
	
	/**
     * Método para obter a string do naipe da carta, consoante o seu atributo value
     * @return String com o naipe da carta
     */
	public String getCardsuit() {

		String name = "?";

		if (this.suit == 1) {		
			name = "H";
		}
		else if (this.suit == 2) {
			name = "S";
		}
		else if (this.suit == 3) {
			name = "C";
		}
		else if (this.suit == 4) {
			name = "D";
		}
		
		return name;
	}
	
	/**
	 * Método para obter o valor da carta
	 * (diferente para Rei, Dama e Valete do método handValue)
	 * @return Valor da carta
	 * @see Card#handValue
	 */
	public int getCardvalue() {
		return this.value;
	}
	
	/**
	 * Faz override do método toString da classe primária Object
	 * Uma carta aparece então em String como, por exemplo 2S
	 */
	public String toString() {
		return String.format(this.getCardface() + this.getCardsuit());
	}
	
}
