package blackjack;

/**
 * Objeto que representa o jogador ao longo do jogo de blackjack.
 * É extendido do objeto Person.
 * @see Person
 */
public class Player extends Person {

	/**
	 * Aposta mínima que o jogador pode fazer
	 */
	int min_bet;
	
	/**
	 * Aposta máxima que o jogador pode fazer
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
	 * Inicializa a mão do jogador adicionando uma mão ao vetor hands do Player
	 * Insurance também é inicializada a 0, sendo que o jogador não começa logo com uma aposta de insurance
	 * @param balance Valor com o qual o balance é inicializado
	 * @param min_bet Valor com o qual a aposta mínima é inicializado
	 * @param max_bet Valor com o qual a aposta máxima é inicializado
	 */
	public Player(int balance,int min_bet, int max_bet) {
		hands.add(new Hand());
		this.balance = balance;
		this.min_bet = min_bet;
		this.max_bet = max_bet;
		this.insurance = 0;
	}
	
	/**
	 * Faz set à bet de uma hand do jogador
	 * @param i Número da mão onde vai ser alterada a aposta
	 * @param b Aposta que vai ser colocada
	 */
	public void set_bet(int i, int b) {
		hands.get(i).setBet(b);
		balance -= b;
	}
	
	/**
	 * Método que retorna o balance corrente do jogador
	 * @return balance do jogador
	 */
	public double getBalance() {
		return this.balance;
	}
	
	/**
	 * Método que faz Update quando o jogador empata (pushes)
	 * Soma ao balance o valor que foi apostado
	 * @param i mão que empatou (pushed)
	 */
	public void update_draw(int i) {
		balance += hands.get(i).bet;
	}
	
	/**
	 * Método que faz Update quando o jogador ganha
	 * Soma ao balance o dobro do valor apostado
	 * @param i mão que ganhou
	 */
	public void update_win(int i) {
		balance += 2*hands.get(i).bet;
	}
	
	/**
	 * Método que faz update quando o jogador tem blackjack
	 * Soma ao balance o valor apostado mais 1.5x a aposta (bónus de blackjack)
	 * @param i mão com blackjack
	 * Nota: No caso de haverem várias mãos o jogador não ganha o bónus (é considerado apenas win em vez de BJ)
	 */
	public void update_bj(int i) {
		balance += 2.5*hands.get(i).bet;
	}
	
	/**
	 * Método utilizando quando o jogador faz uma insurance
	 * Muda a insurance para um valor igual à aposta utilizada
	 * @param bet Aposta utilizada para insurance
	 */
	public void setInsurance(int bet) {
        this.insurance = bet;
        balance -= bet;
    }
	
	/**
	 * Método que retorna o valor da insurance
	 * @return {@link #insurance}
	 */
	public int check_insurance() {
		return this.insurance;
	}
	
	/**
	 * Método utilizado quando o jogador vence o insurance
	 * Soma ao balance 2x o valor utilizado na insurance
	 */
	public void insurance_win() {
		balance += 2*insurance;
	}
	
	/**
	 * Método que retorna a mão do jogador num formato de String
	 * No caso do jogador ter apenas uma mão, devolve-se a String da mão com o formato normal
	 * Quando o jogador tem múltiplas mãos, significa que deu splitting, pelo que retorna a string no formato so splitting para a mão correspondente
	 * @param i número da mão do jogador
	 * @return String com a mão escolhida
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
	 * Método que computa a string que diz o resultado da mão escolhida
	 * @see Hand#winStr
	 * @param i mão escolhida
	 * @return String com o resultado da mão
	 */
	public String print_win(int i) {
		if(hands.size() == 1) return "player " + hands.get(0).winStr() + " and his current balance is " + balance;
		
		else return "player's hand [" + (i+1) +"] " + hands.get(i).winStr() + " and his current balance is " + balance;
			
		
	}
	
	/**
	 * Método utilizado quando o jogador faz surrender a uma das mãos
	 * Soma ao balance metade do valor apostado para essa mão
	 * @param i mão escolhida
	 */
	public void surrender(int i) {
		balance += hands.get(i).bet/2;
	}
	
	/**
	 * Método que executa a side rule de splitting na mão escolhida
	 * Verifica se o jogador pode dar splitting (porque o máximo são 4 mãos)
	 * Se o jogador puder dar splitting, adiciona uma nova mão ao jogador e aposta uma nova bet (igual à anterior) nessa mão
	 * @param hand Mão que vai levar splitting
	 */
	public void splitting(Hand hand) {
        if(hands.size() < 4) {
            Card[] buffer = hand.cards;
            hand.splitHand();
            hands.add(new Hand(buffer[1], hand.bet));
            balance -= hand.bet;
        } else
            System.out.println("You can´t split more than 4 times");     
    }
	
	/**
	 * Método que executa a side rule de double down na mão do jogador escolhida
	 * Retira do balance a bet anterior (porque duplica-se a bet)
	 * @param hand
	 */
	public void doubleDown(Hand hand){
		balance -= hand.bet;
		hand.setBet(hand.bet*2);
	}

}
