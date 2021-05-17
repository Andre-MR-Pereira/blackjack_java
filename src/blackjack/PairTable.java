package blackjack;

public class PairTable extends Table{

	/**
	 * Tabela de opções para uma mao do tipo pair
	 */
	public PairTable() {
        plays= new int[10][10];
        for(int i = 0; i < 10; i++) {
            if(i < 2) {
                int[] buffer = {0,0,2,2,2,2,0,0,0,0};
                plays[i] = buffer;
            }
            else if(i == 2) {
                int[] buffer = {0,0,0,0,0,0,0,0,0,0};
                plays[i] = buffer;
            }
            else if(i == 3) {
                int[] buffer = {3,3,3,3,3,3,3,3,0,0};
                plays[i] = buffer;
            }
            else if(i == 4) {
                int[] buffer = {0,2,2,2,2,0,0,0,0,0};
                plays[i] = buffer;
            }
            else if(i == 5) {
                int[] buffer = {2,2,2,2,2,2,0,0,0,0};
                plays[i] = buffer;
            }
            else if(i == 6 || i == 9) {
                int[] buffer = {2,2,2,2,2,2,2,2,2,2};
                plays[i] = buffer;
            }
            else if(i == 7) {
                int[] buffer = {2,2,2,2,2,1,2,2,1,1};
                plays[i] = buffer;
            }
            else if(i == 8) {
                int[] buffer = {1,1,1,1,1,1,1,1,1,1};
                plays[i] = buffer;
            }
        }
    }
	
	/**
	 * Indica qual a jogada a ser tomada consoante a mão em causa do jogador
	 * e a carta conhecida do Dealer
	 * @param player mão a ser analisada do jogador
	 * @param card_dealer carta conhecida do Dealer
	 * @return jogada a ser tomada
	 */
	public int play(Hand player, Card card_dealer) {
        int line, column;
        Hand opening= new Hand(player.cards[0], player.cards[1]);
        
        if(player.cards[0].getCardface().equals("A"))
            line = 9;
        else
            line = player.cards[0].handValue() - 2;
        
        if(line < 0)
            return -1;
        
        if(card_dealer.getCardface().equals("A"))
            column = 9;
        else
            column = card_dealer.handValue() - 2;
        
        if(column < 0)
        	return -1;
        
        switch(plays[line][column]) {
        	case 0:
        		return 0;
        	case 1:
        		return 1;
        	case 2:
        		if(true)
        			return 2;
        	case 3:
        		if(opening.handTotal() >= 9 && opening.handTotal() <= 11 && player.handSize() == 2)
        			return 3;
        		else
        			return 0;
        	default:
        		return -1;
        }
        
    }
	
	/*
	public static void main(String[] args){
        PairTable pair_tb=new PairTable();
        Hand hand=new Hand();
        hand.addCard(new Card(1,1));
        hand.addCard(new Card(1,1));
        pair_tb.play(hand, new Card(1,1));
    }
    */

}
