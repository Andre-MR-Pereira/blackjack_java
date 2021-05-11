package blackjack;

public class Basic implements Strategies{
    HardTable hard_tb;
    SoftTable soft_tb;
    PairTable pair_tb;
    
    public Basic() {
        this.hard_tb = new HardTable();
        this.soft_tb = new SoftTable();
        this.pair_tb = new PairTable();
    }
    
    public void advice(Hand player,Card card_dealer, Shoe shoe) {
        if(player.handType()==2) {
            System.out.println("Checked Pair table");
            pair_tb.play(player, card_dealer);
        } else if(player.handType()==1) {
            System.out.println("Checked Soft table");
            soft_tb.play(player, card_dealer);
        } else if(player.handType()==0) {
            System.out.println("Checked Hard table");
            hard_tb.play(player, card_dealer);
        } else {
            System.out.println("The hand provided doesn´t seem to be of any basic type");
        }
    }
}