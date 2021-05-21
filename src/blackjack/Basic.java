package blackjack;

/**
 * Objeto que implementa a estratégia básica.
 */
public class Basic implements Strategies {
	
	/**
	 * Variáveis que implementam cada uma das tabelas possíveis
	 * a consultar durante o processo de escolha da estratégia
	 * básica.
	 * @see HardTable
	 * @see SoftTable
	 * @see PairTable
	 */
    HardTable hard_tb;
    SoftTable soft_tb;
    PairTable pair_tb;
    
    /**
     * Cria um novo guia da estratégia básica. Este construtor
     * apenas inicializa as tabelas de tácticas a serem consultadas.
     * @see HardTable
	 * @see SoftTable
	 * @see PairTable
     */
    public Basic() {
        this.hard_tb = new HardTable();
        this.soft_tb = new SoftTable();
        this.pair_tb = new PairTable();
    }
    
    /**
     * Escolhe o conselho a ser fornecido para a jogada em causa, consoante
     * a estratégia básica.
     * @param player mão do jogador.
     * @param card_dealer carta conhecida do Dealer.
     * @param shoe shoe ativo no jogo.
     * @param p jogador->Serve para saber quantas maos o jogador tem em jogo.
     * @return indicador para qual o conselho a ser fornecido.
     * @see HardTable#play
	 * @see SoftTable#play
	 * @see PairTable#play
     */
    public int advice(Hand player, Card card_dealer, Shoe shoe, Player p) {
    	/**
    	 * Avalia qual o tipo da mão do jogador
    	 * @see Hand#handType
    	 */
        if(player.handType() == 2) {
        	/**
        	 * Quando o jogador tem um par, se não for possível dar mais split, verifica-se a HardTable em vez da PairTable
        	 */
        	if(p.hands.size() < 4)
        		return pair_tb.play(player, card_dealer);
        	else
        		return hard_tb.play(player, card_dealer);
        }
        
        else if(player.handType() == 1)
            return soft_tb.play(player, card_dealer);
        
        else if(player.handType() == 0)
            return hard_tb.play(player, card_dealer);
        
        else
            return -1;
    }
    
    
    /**
     * Imprime o conselho para a consola.
     * @param res indicador do conselho a ser impresso.
     */
    public void print_advice(int res) {
    	/*
    	 * 0 -- hit
    	 * 1 -- stand
    	 * 2 -- split
    	 * 3 -- Double // Hit
    	 * 4 -- Double // Stand
    	 * 5 -- Surrender
    	 */
		switch(res) {
			case 0:
				System.out.println("basic	hit");
				break;
			case 1:
				System.out.println("basic	stand");
				break;
			case 2:
				System.out.println("basic	split");
			  	break;
			case 3:
				System.out.println("basic	double");
			  	break;
			case 5:
				System.out.println("basic	surrender");
			  	break;
		  	default:
		  		System.out.println("You shoudn't be here! (Basic Strat Error)");
		}
	}
    
    /**
     * Fornece o caracter para a jogada ser automatizada.
     * @param res indicador da jogada a ser escolhida
     * @return caracter para jogar
     */
    public char make_advice(int res) {
    	/*
    	 * h -- hit
    	 * s -- stand
    	 * p -- split
    	 * 2 -- Double
    	 * u -- Surrender
    	 */
		switch(res) {
			case 0:
				return 'h';
			case 1:
				return 's';
			case 2:
				return 'p';
			case 3:
				return '2';
			case 5:
				return 'u';
		  	default:
		  		return '0';
		}
	}
    
}