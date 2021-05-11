package blackjack;

public class SoftTable extends Table{

	public SoftTable() {
        plays= new int[9][10];
        for(int i=0;i<9;i++) {
            if(i>5) {
                int[] buffer={1,1,1,1,1,1,1,1,1,1};
                plays[i]= buffer;
            }else if(i<2) {
                int[] buffer={0,0,0,3,3,0,0,0,0,0};
                plays[i]= buffer;
            }else if(i<4) {
                int[] buffer={0,0,3,3,3,0,0,0,0,0};
                plays[i]= buffer;
            }else if(i==4) {
                int[] buffer={0,3,3,3,3,0,0,0,0,0};
                plays[i]= buffer;
            }else if(i==5) {
                int[] buffer={1,4,4,4,4,1,1,0,0,0};
                plays[i]= buffer;
            }
        }
    }
	
	public void play(Hand player,Card card_dealer) {
        int line,column;
        Hand opening= new Hand(player.cards[0],player.cards[1]);
        
        line=player.handTotal()-13;
        if(line<0) {
            System.out.println("No play recommended. Wrong hand provided");
            return;
        }
        if(card_dealer.getCardface().equals("A")) {
            column=9;
        }else {
            column=card_dealer.handValue()-2;
        }
        
        if(column<0) {
            System.out.println("No play recommended. Wrong hand provided");
            return;
        }
        
        switch(plays[line][column]) {
        case 0:
            System.out.println("You should hit!");
            break;
        case 1:
            System.out.println("You should stand!");
            break;
        case 3:
            if(opening.handTotal()>=9 && opening.handTotal()<=11) {
                System.out.println("You should double!");
            }else {
                System.out.println("You should hit!");
            }
            break;
        case 4:
            if(opening.handTotal()>=9 && opening.handTotal()<=11) {
                System.out.println("You should double!");
            }else {
                System.out.println("You should stand!");
            }
            break;
        default:
            System.out.println("No play recommended");
        }
    }

	/*
	public static void main(String[] args){
        SoftTable soft_tb=new SoftTable();
        Hand hand=new Hand();
        hand.addCard(new Card(10,1));
        hand.addCard(new Card(1,1));
        soft_tb.play(hand, new Card(1,1));
    }
    */
	
}
