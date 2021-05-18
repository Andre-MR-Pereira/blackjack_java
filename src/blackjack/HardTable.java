package blackjack;

public class HardTable extends Table{
	/**
	 * Tabela de opções para uma mao do tipo hard
	 */
	public HardTable() {
        plays = new int[17][10];
        for(int i = 0; i < 17; i++) {
            if(i < 4) {
                int[] buffer = 	{0,0,0,0,0,0,0,0,0,0};
                plays[i] = buffer;
            }
            else if(i > 11) {
                int[] buffer = {1,1,1,1,1,1,1,1,1,1};
                plays[i] = buffer;
            }
            else if(i == 4) {
                int[] buffer = {0,3,3,3,3,0,0,0,0,0};
                plays[i] = buffer;
            }
            else if(i == 5) {
                int[] buffer = {3,3,3,3,3,3,3,3,0,0};
                plays[i] = buffer;
            }
            else if(i == 6) {
                int[] buffer = {3,3,3,3,3,3,3,3,3,0};
                plays[i] = buffer;
            }
            else if(i == 7) {
                int[] buffer = {0,0,1,1,1,0,0,0,0,0};
                plays[i] = buffer;
            }
            else if(i == 8 || i == 9) {
                int[] buffer = {1,1,1,1,1,0,0,0,0,0};
                plays[i] = buffer;
            }
            else if(i == 10) {
                int[] buffer = {1,1,1,1,1,0,0,0,5,0};
                plays[i] = buffer;
            }
            else if(i == 11) {
                int[] buffer = {1,1,1,1,1,0,0,5,5,5};
                plays[i] = buffer;
            }
        }
    }
	
	/**
	 * Indica qual a jogada a ser tomada consoante a mão em causa do jogador
	 * e a carta conhecida do Dealer.
	 * @param player mão a ser analisada do jogador.
	 * @param card_dealer carta conhecida do Dealer.
	 * @return jogada a ser tomada.
	 */
	public int play(Hand player, Card card_dealer) {
        int line, column;
        Hand opening = new Hand(player.cards[0], player.cards[1]);
        
        line = player.handTotal() - 5;
        if(line < 0) {
            return 0;
        }
        
        if(card_dealer.getCardface().equals("A"))
            column = 9;
        else
            column = card_dealer.handValue()-2;

        if(column < 0) {
            return -1;
        }
        
        switch(plays[line][column]) {
        	case 0:
        		return 0;
        	case 1:
        		return 1;
        	case 3:
        		if(opening.handTotal() >= 9 && opening.handTotal() <= 11 && player.handSize() == 2)
        			return 3;
        		else
        			return 0;
        	case 5:
        		if(player.handSize() == 2)
        			return 5;
        		else
        			return 0;
        	default:
        		return -1;
        }
        
    }
	
	/*
	public static void main(String[] args){
        HardTable hard_tb=new HardTable();
        Hand hand=new Hand();
        hand.addCard(new Card(5,1));
        hand.addCard(new Card(5,1));
        hard_tb.play(hand, new Card(9,1));
    }
    */
	
}
