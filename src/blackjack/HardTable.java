package blackjack;

public class HardTable extends Table{
	
	public HardTable() {
        plays= new int[17][10];
        for(int i=0;i<17;i++) {
            if(i<4) {
                int[] buffer={0,0,0,0,0,0,0,0,0,0};
                plays[i]= buffer;
            }else if(i>11) {
                int[] buffer={1,1,1,1,1,1,1,1,1,1};
                plays[i]= buffer;
            }else if(i==4) {
                int[] buffer={0,3,3,3,3,0,0,0,0,0};
                plays[i]= buffer;
            }else if(i==5) {
                int[] buffer={3,3,3,3,3,3,3,3,0,0};
                plays[i]= buffer;
            }else if(i==6) {
                int[] buffer={3,3,3,3,3,3,3,3,3,0};
                plays[i]= buffer;
            }else if(i==7) {
                int[] buffer={0,0,1,1,1,0,0,0,0,0};
                plays[i]= buffer;
            }else if(i==8 || i==9) {
                int[] buffer={1,1,1,1,1,0,0,0,0,0};
                plays[i]= buffer;
            }else if(i==10) {
                int[] buffer={1,1,1,1,1,0,0,0,5,0};
                plays[i]= buffer;
            }else if(i==11) {
                int[] buffer={1,1,1,1,1,0,0,5,5,5};
                plays[i]= buffer;
            }
        }
    }

	public void play(Hand player,Card card_dealer) {
        int line,column;
        Hand opening= new Hand(player.cards[0],player.cards[1]);
        
        line=player.handTotal()-5;
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
            System.out.println("basic	hit");
            break;
        case 1:
            System.out.println("basic	stand");
            break;
        case 3:
            if(opening.handTotal()>=9 && opening.handTotal()<=11) {
                System.out.println("basic	double");
            }else {
                System.out.println("basic	hit");
            }
            break;
        case 5:
            if(player.handSize()==2) {
                System.out.println("basic	surrender");
            }else {
                System.out.println("basic	hit");
            }
            break;
        default:
            System.out.println("No play recommended");
        }
    }
	
	/*
	public static void main(String[] args){
        HardTable hard_tb=new HardTable();
        Hand hand=new Hand();
        hand.addCard(new Card(11,1));
        hand.addCard(new Card(8,1));
        hard_tb.play(hand, new Card(7,1));
    }
	*/
}
