package blackjack;

/**
 * Tabela de opções para uma mao do tipo soft
 * (Tem um ás que vale 10 pontos)
 */
public class SoftTable extends Table {

	/**
	 * Construtor da SoftTable.
	 * Constroi a tabela de acordo com a indicada no enunciado.
	 */
	public SoftTable() {
        plays= new int[9][10];
        for(int i = 0; i < 9; i++) {
            if(i > 5) {
                int[] buffer = {1,1,1,1,1,1,1,1,1,1};
                plays[i] = buffer;
            }
            else if(i < 2) {
                int[] buffer = {0,0,0,3,3,0,0,0,0,0};
                plays[i] = buffer;
            }
            else if(i < 4) {
                int[] buffer = {0,0,3,3,3,0,0,0,0,0};
                plays[i] = buffer;
            }
            else if(i == 4) {
                int[] buffer = {0,3,3,3,3,0,0,0,0,0};
                plays[i] = buffer;
            }
            else if(i == 5) {
                int[] buffer = {1,4,4,4,4,1,1,0,0,0};
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
        
        line = player.handTotal() - 13;
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
    		case 3:
    			if(opening.handTotal() >= 9 && opening.handTotal() <= 11 && player.handSize() == 2)
    				return 3;
    			else
    				return 0;
    		case 4:
    			if(opening.handTotal() >= 9 && opening.handTotal() <= 11 && player.handSize() == 2)
    				return 3;
    			else
    				return 1;
    		default:
    			return -1;
        }
        
    }
	
}
