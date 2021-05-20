package blackjack;

/**
 * Objeto que representa o jogador ao longo do jogo de blackjack.
 * � extendido do objeto Person.
 * @see Person
 */
public class Player extends Person {

	/**
	 * Aposta m�nima que o jogador pode fazer
	 */
	int min_bet;
	
	/**
	 * Aposta m�xima que o jogador pode fazer
	 */
	int max_bet;
	
	/**
	 * Balance do jogador
	 */
	int balance;
	
	/**
	 * Valor que o jogar aposta em Insurance
	 */
	int insurance;

	/**
	 * Construtor do jogador
	 * Inicializa a m�o do jogador adicionando uma m�o ao vetor hands do Player
	 * Insurance tamb�m � inicializada a 0, sendo que o jogador n�o come�a logo com uma aposta de insurance
	 * @param balance Valor com o qual o balance � inicializado
	 * @param min_bet Valor com o qual a aposta m�nima � inicializado
	 * @param max_bet Valor com o qual a aposta m�xima � inicializado
	 */
	public Player(int balance,int min_bet, int max_bet) {
		hands.add(new Hand());
		this.balance = balance;
		this.min_bet = min_bet;
		this.max_bet = max_bet;
		this.insurance = 0;
	}
	
	/**
	 * Faz set � bet de uma hand do jogador
	 * @param i N�mero da m�o onde vai ser alterada a aposta
	 * @param b Aposta que vai ser colocada
	 */
	public void set_bet(int i, int b) {
		hands.get(i).setBet(b);
		balance -= b;
	}
	
	/**
	 * M�todo que retorna o balance corrente do jogador
	 * @return balance do jogador
	 */
	public double getBalance() {
		return this.balance;
	}
	
	/**
	 * M�todo que faz Update quando o jogador empata (pushes)
	 * Soma ao balance o valor que foi apostado
	 * @param i m�o que empatou (pushed)
	 */
	public void update_draw(int i) {
		balance += hands.get(i).bet;
	}
	
	/**
	 * M�todo que faz Update quando o jogador ganha
	 * Soma ao balance o dobro do valor apostado
	 * @param i m�o que ganhou
	 */
	public void update_win(int i) {
		balance += 2*hands.get(i).bet;
	}
	
	/**
	 * M�todo que faz update quando o jogador tem blackjack
	 * Soma ao balance o valor apostado mais 1.5x a aposta (b�nus de blackjack)
	 * @param i m�o com blackjack
	 * Nota: No caso de haverem v�rias m�os o jogador n�o ganha o b�nus (� considerado apenas win em vez de BJ)
	 */
	public void update_bj(int i) {
		balance += 2.5*hands.get(i).bet;
	}
	
	/**
	 * M�todo utilizando quando o jogador faz uma insurance
	 * Muda a insurance para um valor igual � aposta utilizada
	 * @param bet Aposta utilizada para insurance
	 */
	public void setInsurance(int bet) {
        this.insurance = bet;
        balance -= bet;
    }
	
	/**
	 * M�todo que retorna o valor da insurance
	 * @return {@link #insurance}
	 */
	public int check_insurance() {
		return this.insurance;
	}
	
	/**
	 * M�todo utilizado quando o jogador vence o insurance
	 * Soma ao balance 2x o valor utilizado na insurance
	 */
	public void insurance_win() {
		balance += 2*insurance;
	}
	
	/**
	 * M�todo que retorna a m�o do jogador num formato de String
	 * No caso do jogador ter apenas uma m�o, devolve-se a String da m�o com o formato normal
	 * Quando o jogador tem m�ltiplas m�os, significa que deu splitting, pelo que retorna a string no formato so splitting para a m�o correspondente
	 * @param i n�mero da m�o do jogador
	 * @return String com a m�o escolhida
	 */
	public String handStr(int i) {
		String s=new String();
		if(hands.size() == 1) {
			s = "player's hand" + hands.get(0) + " (" + this.handValue(0) + ")";
		}
		else {
				s = "player's hand [" + (i+1) +"]" + hands.get(i) + " (" + this.handValue(i) + ")";
		}
		return s;
	}
	
	/**
	 * M�todo que computa a string que diz o resultado da m�o escolhida
	 * @see Hand#winStr
	 * @param i m�o escolhida
	 * @return String com o resultado da m�o
	 */
	public String print_win(int i) {
		if(hands.size() == 1) return "player " + hands.get(0).winStr() + " and his current balance is " + balance;
		
		else return "player's hand [" + (i+1) +"] " + hands.get(i).winStr() + " and his current balance is " + balance;
			
		
	}
	
	/**
	 * M�todo utilizado quando o jogador faz surrender a uma das m�os
	 * Soma ao balance metade do valor apostado para essa m�o
	 * @param i m�o escolhida
	 */
	public void surrender(int i) {
		balance += hands.get(i).bet/2;
	}
	
	/**
	 * M�todo que executa a side rule de splitting na m�o escolhida
	 * Verifica se o jogador pode dar splitting (porque o m�ximo s�o 4 m�os)
	 * Se o jogador puder dar splitting, adiciona uma nova m�o ao jogador e aposta uma nova bet (igual � anterior) nessa m�o
	 * @param hand M�o que vai levar splitting
	 */
	public void splitting(Hand hand) {
        if(hands.size() < 4) {
            Card[] buffer = hand.cards;
            hand.splitHand();
            hands.add(new Hand(buffer[1], hand.bet));
            balance -= hand.bet;
        } else
            System.out.println("You can�t split more than 4 times");     
    }
	
	/**
	 * M�todo que executa a side rule de double down na m�o do jogador escolhida
	 * Retira do balance a bet anterior (porque duplica-se a bet)
	 * @param hand
	 */
	public void doubleDown(Hand hand){
		balance -= hand.bet;
		hand.setBet(hand.bet*2);
	}

}
