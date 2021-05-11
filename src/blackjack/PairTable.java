package blackjack;

public class PairTable extends Table{

	public PairTable() {
        plays= new int[10][10];
        for(int i=0;i<10;i++) {
            if(i<2) {
                int[] buffer={0,0,2,2,2,2,0,0,0,0};
                plays[i]= buffer;
            }else if(i==2) {
                int[] buffer={0,0,0,0,0,0,0,0,0,0};
                plays[i]= buffer;
            }else if(i==3) {
                int[] buffer={3,3,3,3,3,3,3,3,0,0};
                plays[i]= buffer;
            }else if(i==4) {
                int[] buffer={0,2,2,2,2,0,0,0,0,0};
                plays[i]= buffer;
            }else if(i==5) {
                int[] buffer={2,2,2,2,2,2,0,0,0,0};
                plays[i]= buffer;
            }else if(i==6 || i==9) {
                int[] buffer={2,2,2,2,2,2,2,2,2,2};
                plays[i]= buffer;
            }else if(i==7) {
                int[] buffer={2,2,2,2,2,1,2,2,1,1};
                plays[i]= buffer;
            }else if(i==8) {
                int[] buffer={1,1,1,1,1,1,1,1,1,1};
                plays[i]= buffer;
            }
        }
    }
	
	public void play(Hand player,Card card_dealer) {
        int line,column;
        Hand opening= new Hand(player.cards[0],player.cards[1]);
        
        if(player.cards[0].getCardface().equals("A")) {
            line=9;
        }else {
            line=player.cards[0].getCardvalue()-2;
        }
        
        if(line<0) {
            System.out.println("No play recommended. Wrong hand provided");
            return;
        }
        
        if(card_dealer.getCardface().equals("A")) {
            column=9;
        }else {
            column=card_dealer.getCardvalue()-2;
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
        case 2:
            System.out.println("You should split!");
            break;
        case 3:
            if(opening.handTotal()>=9 && opening.handTotal()<=11) {
                System.out.println("You should double!");
            }else {
                System.out.println("You should hit!");
            }
            break;
        default:
            System.out.println("No play recommended");
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
