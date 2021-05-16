package blackjack;

public class Basic implements Strategies {
    HardTable hard_tb;
    SoftTable soft_tb;
    PairTable pair_tb;
    
    public Basic() {
        this.hard_tb = new HardTable();
        this.soft_tb = new SoftTable();
        this.pair_tb = new PairTable();
    }
    
    public int advice(Hand player, Card card_dealer, Shoe shoe, Player p) {
    	
        if(player.handType() == 2) {
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
    
    /*
	 * 0 -- hit
	 * 1 -- stand
	 * 2 -- split
	 * 3 -- Double // Hit
	 * 4 -- Double // Stand
	 * 5 -- Surrender
	 */
    
    public void print_advice(int res) {
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
    
    public char make_advice(int res) {
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