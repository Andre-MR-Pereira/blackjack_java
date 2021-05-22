package blackjack;

/**
 * Objeto que representa uma mão de blackjack
 * @see Card
 */
public class Hand {
	
	/**
	 * Vetor de cartas da mão
	 */
	Card[] cards;
	
	/**
	 * Aposta da mão em questão
	 */
	double bet;
	
	/**
	 * Número de cartas na mão
	 */
	int ncards;
	
	/**
	 * Aposta em termos de chips
	 */
	Chips chips;
	
	/**
	 * Atributo auxiliar que indica se a mão ganha, perde ou empata
	 * 0 = loss
	 * 1 = win
	 * outro = push
	 */
	int win;
	
	/**
	 * Cria uma mão que serve para o início de cada rodada
	 * Mão regular
	 */
	public Hand() {
        cards = new Card[12];
        ncards = 0;
        bet=0;
        chips= new Chips(0,0,0,0);
        win = -1;
    }
    
	/**
	 * Cria uma mão onde se sabe a primeira carta e a aposta a ser feita
	 * Mão que advêm de um split
	 * @param card carta que faz parte da mão
	 * @param bet aposta que é realizada nessa mão
	 */
    public Hand(Card card, double bet) {    //splitting hands
        cards = new Card[12];
        cards[0]=card;
        ncards = 1;
        this.bet=bet;
        chips= new Chips(0,0,0,0);
        chips.convert_chips(bet);
        win = -1;
    }
    
    /**
     * Cria uma mão onde se sabe as primeiras cartas
     * @param card1 primeira carta
     * @param card2 segunda carta
     */
    public Hand(Card card1,Card card2) {    //opening hands
        cards = new Card[12];
        cards[0]=card1;
        cards[1]=card2;
        ncards = 2;
        win = -1;
    }
	
    /**
     * Guarda apenas a primeira carta como a mão do jogador
     */
    public void splitHand() {
        ncards=1;
    }
	
	/**
	 * Guarda a aposta feita nessa mão
	 * @param b valor a apostar
	 */
	public void setBet(double b) {
		if(chips.validate_bet(b)==true) {
			this.bet = b;
		}
	}
	
	/**
	 * Regista se a mão ganhou à mão do Dealer
	 * @param win se ganhou ou não
	 */
	public void setWin(int win) {
		this.win = win;
	}
	
	/**
	 * Adiciona uma carta à mão
	 * @param c Carta a ser adicionada
	 * @see Card
	 */
	public void addCard(Card c) {
		cards[ncards++] = c;
	}
	
	/**
	 * Retorna uma string a indicar se a mão é melhor,igual ou pior
	 * que a do Dealer
	 * @return String descritiva sobre o estado de vitória da mão
	 */
	public String winStr() {
		if (win == 0) return "loses";

		else if (win == 1 ) return "wins";

		else return "pushes";
	}
	
	/**
	 * Retorna quantas cartas existem numa mão
	 * @return cartas existentes na mão.
	 */
	public int handSize() {
		return ncards;
	}
	
	/**
	 * Indica que tipo de mão se trata para referência das tabelas de estratégias
	 * @return valor que indica que tipo de mão é
	 * @see Table
	 */
	public int handType() {
        int no_aces=1;
        
        if(ncards==2 && cards[0].getCardface().equals(cards[1].getCardface()))
            return 2; //Par
        
        int aux = 0;
        for(int i=0;i<ncards;i++) {
            if(cards[i].getCardface().equals("A")) {
                no_aces=0;
            }
            aux += cards[i].handValue();
        }
        
        if(no_aces == 0 && aux + 10 <= 21) {
            return 1; //mão soft
        } else {
            return 0; //mão hard
        }
    }
	
	/**
	 * Indica o valor mais alto que essa mão possui no blackjack
	 * @return valor da mão
	 */
	public int handTotal() {
		int total = 0, aux;
		boolean ace = false;
		
		for(int i = 0; i < ncards; i++) {
			aux = cards[i].getCardvalue();
			if(aux > 10) {
				aux = 10;
			}
			else if (aux == 1) {
				ace = true;
			}
			
			total += aux;
		}
		
		if(ace == true && total + 10 <= 21)
			total = total + 10;
		
		return total;
	}
	
	/**
	 * Imprime a mão para a consola
	 * @param hideCard indica se a ultima carta deverá ser revelada
	 * @return String que indica a mão conhecida
	 */
	public String toString(boolean hideCard) {
		String res = "";	
		for(int i = 0; i < ncards; i++) {

			if(hideCard && i == ncards-1) {
				
				res += " " + "X";
			}
			else {
				
				res += " " + cards[i];
			}
		}
		
		return res;
	}
	
	/**
	 * Imprime a mão de um jogador que não seja o Dealer.
	 */
	public String toString() {
		return this.toString(false);
	}
	
}
