package blackjack;

public class Hand {
	/**
	 * Objeto que guarda a m�o de um jogador
	 * @see Card
	 */
	Card[] cards;
	double bet;
	int ncards;
	Chips chips;
	int win;
	
	/**
	 * Cria uma m�o que serve para o in�cio de cada rodada
	 * M�o regular
	 */
	public Hand() {
        cards = new Card[12];
        ncards = 0;
        bet=0;
        chips= new Chips(0,0,0,0);
        win = -1;
    }
    
	/**
	 * Cria uma m�o onde se sabe a primeira carta e a aposta a ser feita
	 * M�o que adv�m de um split
	 * @param card carta que faz parte da m�o
	 * @param bet aposta que � realizada nessa m�o
	 */
    public Hand(Card card,double bet) {    //splitting hands
        cards = new Card[12];
        cards[0]=card;
        ncards = 1;
        this.bet=bet;
        chips= new Chips(0,0,0,0);
        chips.convert_chips(bet);
        win = -1;
    }
    
    /**
     * Cria uma m�o onde se sabe as primeiras cartas
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
     * Guarda apenas a primeira carta como a m�o do jogador
     */
    public void splitHand() {
        ncards=1;
    }
	
	/**
	 * Guarda a aposta feita nessa m�o
	 * @param b valor a apostar
	 */
	public void setBet(double b) {
		if(chips.validate_bet(b)==true) {
			this.bet = b;
		}
	}
	
	/**
	 * Regista se a m�o ganhou � m�o do Dealer
	 * @param win se ganhou ou n�o
	 */
	public void setWin(int win) {
		this.win = win;
	}
	
	/**
	 * Adiciona uma carta � m�o
	 * @param c Carta a ser adicionada
	 * @see Card
	 */
	public void addCard(Card c) {
		cards[ncards++] = c;
	}
	
	/**
	 * Retorna uma string a indicar se a m�o � melhor,igual ou pior
	 * que a do Dealer
	 * @return String descritiva sobre o estado de vit�ria da m�o
	 */
	public String winStr() {
		if (win == 0) return "loses";

		else if (win == 1 ) return "wins";

		else return "pushes";
	}
	
	/**
	 * Retorna quantas cartas existem numa m�o
	 * @return
	 */
	public int handSize() {
		return ncards;
	}
	
	/**
	 * Indica que tipo de m�o se trata para refer�ncia das tabelas de estrat�gias
	 * @return valor que indica que tipo de m�o �
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
            return 1; //m�o soft
        } else {
            return 0; //m�o hard
        }
    }
	
	/**
	 * Indica o valor mais alto que essa m�o possui no blackjack
	 * @return valor da m�o
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
	 * Imprime a m�o para a consola
	 * @param hideCard indica se a ultima carta dever� ser revelada
	 * @return String que indica a m�o conhecida
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
	 * Imprime a m�o de um jogador que n�o seja o Dealer.
	 */
	public String toString() {
		return this.toString(false);
	}
	
	/*
	public static void main(String[] args){
        Hand hand1=new Hand();
        Hand hand2=new Hand(new Card(1,1),5);
        Hand hand3=new Hand();
        Hand hand4=new Hand();
        hand3.addCard(new Card(1,1));
        hand3.addCard(new Card(1,1));
        hand4.addCard(new Card(7,1));
        hand4.addCard(new Card(7,1));
        hand1.setBet(1);
        hand1.setBet(5);
        hand1.setBet(0.5);
        hand1.addCard(new Card(10,1));
        hand1.addCard(new Card(5,1));
        System.out.println("Tamanho mao");
        System.out.println(hand1.handSize());
        System.out.println(hand2.handSize());
        System.out.println("Valor mao");
        System.out.println(hand1.handTotal());
        System.out.println(hand2.handTotal());
        System.out.println("Split not working");
        hand1.splitHand();
        System.out.println(hand1.handTotal());
        hand2.addCard(new Card(9,1));
        hand2.addCard(new Card(3,1));
        System.out.println(hand2.handTotal());
        System.out.println("Tipos de mao");
        System.out.println(hand1.handType());
        System.out.println(hand2.handType());
        System.out.println(hand3.handType());
        System.out.println(hand4.handType());
        System.out.println("Split working");
        System.out.println(hand4.handTotal());
        hand4.splitHand();
        System.out.println(hand4.handTotal());
    }
    */
}
