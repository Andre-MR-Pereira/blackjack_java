package blackjack;

import java.util.List;
import java.util.Scanner;

/**
 * Classe que representa o contexto do jogo / o flow do jogo.
 * É neste objeto que se ligam os estados do jogo ás outras classes que vão influenciar os estados.
 * É o objeto mais extenso devido a todas as ligações que são feitas.
 */
public class StateContext {
	
	/**
	 * Estado corrente do jogo.
	 * Vai sendo alterado ao longo do processo de jogo.
	 * @see State
	 */
    private State state;
    
    /**
     * Valid é um booleano que indica se o input está correto, permitindo ou não a entrada no estado corrente.
     */
    boolean valid;
    
    /**
     * Booleano auxiliar ao modo de simulação.
     * Serve para indicar se o último comando a ser utilizado foi o advice.
     */
    boolean sim_ad;
    
    /**
     * Flag para indicar se o shoe foi baralhado ou não, para contagem.
     */
    boolean shuffle_flag;
    
    /**
     * input que decide o comando a ser utilizado que vai para cada estado.
     * Foi decidido ser apenas um caracter e foram criadas exceções para os comandos 'ad' e 'st', os únicos com 2 caracteres.
     */
    char input;
    
    /**
     * Aposta feita pelo jogador nesta rodada de blackjack
     */
	int bet;
	
	/**
	 * Tentativa de aposta feita pelo jogador.
	 * (só é traduzida para bet se for válida)
	 */
	int temp_bet;
	
	/**
	 * Número de mãos do jogador nesta rodada de blackjack.
	 * (é atualizado quando se faz split)
	 */
	int hand;
	
	/**
	 * Scanner que recebe os comandos da consola.
	 */
	Scanner in = new Scanner(System.in);
	
	/**
	 * Estatísticas de jogo
	 */
	Statistics stat;
	
	/**
	 * Flag auxiliar para quando há splitting de áses
	 */
    boolean finish_split;
    
    /**
     * Construtor do state context
     * Inicializa o estado no betting state (sempre o primeiro estado) e todas os outros atributos.
     * @param min_bet aposta mínima que jogador pode fazer.
     * @param balance dinheiro inicial do jogador.
     */
    public StateContext(int min_bet, int balance) {
        state = new Betting_Stage();
        bet = min_bet;
        temp_bet = 0;
        hand = 0;
        valid = false;
        sim_ad = true;
        shuffle_flag = false;
        stat = new Statistics(balance);
        finish_split=false;
    }
    
    /**
     * Atualiza o número corrente de mãos que o jogador possui.
     * (utilizado quando se faz splitting)
     * @param h número novo de maõs
     */
    public void set_hands(int h) {
    	this.hand = h;
    }
    
    /**
     * Método que atribui um input utilizado quando se está no modo de simulação.
     * O funcionamento é simples:
     * Quando se está no betting state, o input tem que ser sempre 'b', e atribui-se uma bet no temp_bet, que depois é verificada.
     * Quando se está do deal state, o input é sempre 'd'.
     * Quando se está num dos outros states, o input pode variar por isso primeiro manda-se um 'ad', que no modo de simulação muda o input para o sugerido pela estratégia indicada.
     * @param strats String com as estratégias a ser utilizadas
     * @param a5 estratégia ace-Five
     */
    public void sim_input(String strats, AceFive a5) {
    	
    	if(state instanceof Betting_Stage) {
    		input = 'b';
    		if(strats.equals("BS-AF") || strats.equals("HL-AF"))
    			temp_bet = a5.make_advice(a5.advice(null, null, null, null));
    	}
    	
    	else if(state instanceof Deal_Stage)
    		input = 'd';
    	
    	else {
    		if(sim_ad) {
    			input = 'a';
    			sim_ad = false;
    		}
    		else
    			sim_ad = true;
    	}
    }
    
    /**
     * Atribui um input
     * @param in input a ser atribuido
     */
    public void set_input(char in) {
    	this.input = in;
    }
    
    /**
     * Faz reset à flag de shuffle para false.
     * @return retorna o valor lógico da flag ANTES do reset.
     */
    public boolean reset_shuffle() {
    	boolean flag = this.shuffle_flag;
    	this.shuffle_flag = false;
    	return flag;
    }
    
    /**
     * Método utilizado para terminar uma rodada de blackjack.
     * Depois de se ter passado por todo o processo do turno do jogador,
     * este método faz uma série de verificações para determinar qual o resultado do jogo e imprime o necessário.
     * Também é de notar que a Standard Betting strategy é implementada neste método. Sempre que o jogo termina,
     * temp_bet é atribuido consoante o resultado e de acordo com esta estratégia.
     * @param context Contexto do jogo para se avaliar o resultado.
     * @param player1 Jogador.
     * @param casino Dealer.
     * @param s Shoe.
     * @param b Estratégia Básica.
     * @param hl Estratégia Hi-Lo.
     * @param a5 Estratégia Ace-Five.
     * @param debugger Booleano para indicar se está em modo de debug ou não.
     */
    public void Resolution(StateContext context, Player player1, Dealer casino, Shoe s, Basic b, HiLo hl, AceFive a5, boolean debugger) {
    	Card temp = new Card(0, 0);
    	
    	if(hand < player1.hands.size() - 1) {
			hand++;
			System.out.println("playing hand nº " + (hand+1) + "...");
			temp = s.deal();
			player1.hit(temp, hand);
			hl.update_counter(temp);
			a5.update_counter(temp);
			System.out.println(player1.handStr(hand));
			setState(new First_Hand_Stage());
		}
    	
    	else {
    		casino.dealerTurn(s, stat);
    		checkInsurance(player1, casino);
			for(int i = 1; i < casino.handSize(0); i++) {
				temp = casino.hands.get(0).cards[i];
				hl.update_counter(temp);
				a5.update_counter(temp);
			}
			
			int casino_value = casino.handValue(0);
			int player_value = 0;
			
    		for(int i = 0; i < player1.hands.size(); i++) {
    			player_value = player1.handValue(i);
    			
    			if ((player_value < casino_value && casino_value  < 22) || player1.hands.get(i).win == 0) {
    				player1.hands.get(i).setWin(0);
    				stat.update_game_result(0);
    				System.out.println(player1.print_win(i));
    				
    				if(bet - a5.min_bet < a5.min_bet)
    					temp_bet = a5.min_bet;
    				else
    					temp_bet = bet - a5.min_bet;
    			}
    			
    			else if(player_value > casino_value || casino_value  > 21) {
        			if (player_value == 21 && player1.hands.get(i).ncards == 2 && (player1.hands.size() < 2 || player1.hands.get(i).cards[1].handValue() == 1)) 
        				player1.update_bj(i);
        			else 
        				player1.update_win(i);
					player1.hands.get(i).setWin(1);
					System.out.println(player1.print_win(i));
					stat.update_game_result(1);
					
					if(bet + a5.min_bet > a5.max_bet)
    					temp_bet = a5.max_bet;
    				else
    					temp_bet = bet + a5.min_bet;
				}
    			
    			else  if (player_value < 21) {
    				player1.hands.get(i).setWin(2);
    				player1.update_draw(i);
    				System.out.println(player1.print_win(i));
    				stat.update_game_result(2);
    			}
    			
    			else if (player1.hands.get(i).ncards == 2 && 2 < casino.hands.get(0).ncards) {
    				player1.hands.get(i).setWin(1);
					if(player1.hands.size() < 2 || player1.hands.get(i).cards[1].handValue() == 1)  
						player1.update_bj(i);
					else 
						player1.update_win(i);
					System.out.println(player1.print_win(i));
					stat.update_game_result(1);
					
					if(bet + a5.min_bet > a5.max_bet)
    					temp_bet = a5.max_bet;
    				else
    					temp_bet = bet + a5.min_bet;
    			}
    			
    			else if (casino.hands.get(0).ncards == 2 && player1.hands.get(i).ncards > 2) {
    				player1.hands.get(i).setWin(0);
					System.out.println(player1.print_win(i));
					stat.update_game_result(0);
					
					if(bet - a5.min_bet < a5.min_bet)
    					temp_bet = a5.min_bet;
    				else
    					temp_bet = bet - a5.min_bet;
    			}
    			
    			else {
                    player1.hands.get(i).setWin(2);
                    player1.update_draw(i);
                    System.out.println(player1.print_win(i));
                    stat.update_game_result(2);
                }
    		}
    		
    		hand = 0;
    		
    		shuffle_flag = reset(player1, casino, s, hl, a5, debugger);
    	}
		
		
	}
    
    /**
     * Método que atribui um valor ao input que é utilizado no modo de debug.
     * Lê uma lista de comandos e atribui um input dependendo do comando.
     * @param moves Lista de moves criada a partir do ficheiro fornecido
     * @return Retorna um inteiro que é a bet escolhida (no caso do comando ser 'b').
     */
    public int read_File(List<String> moves) {
		valid = false;	
		input = 'a';
		int i = -1;
		
		if(!moves.isEmpty()) {
			if(moves.get(0).equals("ad")) {
				input = 'a'; // a for advice
				valid = true;
			}
			
			else if(moves.get(0).equals("st")) {
				input = 't'; // t for statistics
				valid = true;
			}
			
			else if(moves.get(0).length() == 1) {
				input = moves.get(0).charAt(0);
				if(input == 'a') {
					System.out.println(input + ": illegal command");
					valid = false;
				}
				
				else if(input == 't') {
					System.out.println(input + ": illegal command");
					valid = false;
				}
				
				else if(input == 'q') {
					System.out.println("bye");
					System.exit(0);
				}
				
				else if(input == 'b') {
					valid = true;
					try {
						i = Integer.parseInt(moves.get(1));
					}
					catch(NumberFormatException e) {
						i = 0;
					}
				}
				else {
					valid = true;
					i = 0;
				}
			}
			
			else
				System.out.println(moves.get(0)+": illegal command");
			
			System.out.println(input);
			moves.remove(0);
			
			if(i > 0)
				moves.remove(0);
		}
		
		return i;
	}
 
    /**
     * Método que atribui um valor ao input que é utilizado no modo interativo.
     * Lê a String escrita na consola pelo utilizador e analisa se é um comando válido.
     * @return Retorna um inteiro que é a bet escolhida (no caso do comando ser 'b').
     */
    public int read_String() {
		valid = false;
		String temp;
		
		input = 'a';
		int i = -1;
		
		temp = in.nextLine();
		if(temp.length() > 1) {
			input = temp.charAt(0);
			
			if((temp.charAt(1)) == ' ' && (temp.charAt(0)) == 'b') {
				if(temp.length() > 2) {
					temp = temp.substring(2,temp.length());
					try {
						i = Integer.parseInt(temp);
						valid = true;
					}
					catch(NumberFormatException e) {
						System.out.println("b " + temp +": illegal command");
					}
				}
				
				else
					System.out.println("illegal command");
			}
			
			else if(temp.equals("ad")) {
				input = 'a'; // a for advice
				valid = true;
			}
			
			else if(temp.equals("st")) {
				input = 't'; // t for statistics
				valid = true;
			}
			
			else
				System.out.println(temp + ": illegal command");
		}
		
		else {
			if(temp.length() == 1) {
				input = temp.charAt(0);
				
				if(input == 'a') {
					System.out.println(input + ": illegal command");
					valid = false;
				}
				
				else if(input == 't') {
					System.out.println(input + ": illegal command");
					valid = false;
				}
				
				else if(input == 'q') {
					System.out.println("bye");
					System.exit(0);
				}
				
				else {
					valid = true;
					i = 0;
				}
			}
			
			else
				System.out.println("no input found");
		}
		
		return i;
	}
    
    /**
     * Set the current state.
     * Normally only called by classes implementing the State interface.
     * @param newState the new state of this context.
     */
    public void setState(State newState) {
        state = newState;
    }
    
    /**
     * Verifica se o jogador fez uma insurance, e em caso afirmativo indica se o jogador ganha ou não a insurance.
     * @param player1 Jogador que fez a insurance.
     * @param casino Dealer para verificar se tem BlackJack de mão.
     */
    public void checkInsurance(Player player1, Dealer casino) {
        if(player1.check_insurance() > 0) {
        	if(casino.handValue(0) == 21 && casino.handSize(0) == 2) {
        		player1.insurance_win();
        		System.out.println("player wins insurance");
        	}
        	else
        		System.out.println("player looses insurance");
        		
        	
        	player1.setInsurance(0);
        }
    }
    
    /**
     * Atribui uma bet,
     * @param bet Valor a ser atribuido,
     */
    public void setBet(int bet){
    	this.bet = bet;
    }
    
    /**
     * Atribui uma temp_bet.
     * @param temp_bet Valor a ser atribuido.
     */
    public void setTempBet(int temp_bet){
    	this.temp_bet=temp_bet;
    }
    
    /**
     * Atribui um valor booleano ao valid.
     * @param valid Valor lógico a ser atribuido.
     */
    public void setvalid(boolean valid){
    	this.valid = valid;
    }
    
    /**
     * Atribui um valor booleano ao finish_split.
     * @param finish_split valor lógico a ser atribuido.
     */
    public void setFinishSplit(boolean finish_split){
    	this.finish_split=finish_split;
    }
    
    /**
     * Método para verificar o valid.
     * @return Retorna o valor lógico do valid.
     */
    public boolean check_valid() {
    	return this.valid;
    }
    
    /**
     * Este método é utilizado quando o jogo acaba sem o dealer precisar de jogar.
     * (p/ ex, quando se rebentam os 21 pontos ou quando se faz surrender)
     * @param player1 Jogador.
     * @param casino Dealer.
     * @param s Shoe.
     * @param b Estratégia Básica.
     * @param hl Estratégia Hi-Lo.
     * @param a5 Estratégia Ace-Five.
     * @param debugger Indica se estamos em debug mode.
     */
    public void hard_reset(Player player1, Dealer casino, Shoe s, Basic b, HiLo hl, AceFive a5, boolean debugger) {
    	Card temp;
    	
    	temp = casino.hands.get(0).cards[1];
		hl.update_counter(temp);
		a5.update_counter(temp);
		System.out.println(casino.handStr(false));
		checkInsurance(player1, casino);
		reset(player1, casino, s, hl, a5, debugger);
    }
    
    /**
     * Método que faz reset à ronda de blackjack.
     * Verifica se é necessário baralhar, em caso afirmativo faz reset aos counters das estratégias.
     * Também faz reset ás mãos e volta a colocar o state como betting_stage.
     * @param player1 Jogador.
     * @param casino Dealer.
     * @param s Shoe.
     * @param hl Hi-Lo.
     * @param a5 Ace-Five.
     * @param debugger Indica se estamos em debug mode.
     * @return Retorna um booleano que indica se o shoe foi baralhado para motivos de contagem.
     */
    public boolean reset(Player player1, Dealer casino, Shoe s, HiLo hl, AceFive a5, boolean debugger) {
    	boolean res = false;
    	
    	if(!debugger) {
    		if(s.check()) {
    			hl.reset_count();
    			a5.reset_count();
    			res = true;
    		}
    	}
    	
		// Reset hands
		casino.resetHand();
		player1.resetHand();
		setFinishSplit(false);
		setState(new Betting_Stage());
		
		return res;
    }
    
    /**
     * Método que manda faz o state processar o input escolhido e fazer ações.
     * (depende do state em que se está presente).
     * @param player1 Jogador.
     * @param casino Dealer.
     * @param s Shoe.
     * @param b Estratégia básica.
     * @param hl Estratégia Hi-Lo.
     * @param a5 Estratégia Ace-Five.
     * @param debugger Indica se estamos em debug mode.
     * @see State
     */
    public void handle_input(Player player1, Dealer casino, Shoe s, Basic b, HiLo hl, AceFive a5,boolean debugger) {
    	state.handle_input(this, player1, casino, s, b, hl, a5, hand, debugger);
    }
    
}