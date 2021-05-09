package blackjack;
import java.lang.Math;

public class Chips {
	int blacks;
	int greens;
	int reds;
	int whites;
	
	public Chips(int blacks,int greens, int reds,int whites) {
		this.blacks=blacks;
		this.greens=greens;
		this.reds=reds;
		this.whites=whites;
	}
	
	public void convert_chips(double money) {
		this.blacks=(int) Math.floor(money/100);
		this.greens=(int) Math.floor((money-100*this.blacks)/25);
		this.reds=(int) Math.floor((money-100*this.blacks-25*this.greens)/5);
		this.whites=(int)money-100*this.blacks-25*this.greens-5*this.reds;
	}
	
	public boolean validate_bet(double bet) {
		int blacks=(int)Math.floor(bet/100);
		int greens=(int) Math.floor((bet-100*blacks)/25);
		int reds=(int)Math.floor((bet-100*blacks-25*greens)/5);
		int whites=(int)bet-100*blacks-25*greens-5*reds;
		if(bet==100*blacks+25*greens+5*reds+whites) {
			return true;
		}else {
			System.out.println(bet);
			System.out.println(100*blacks+25*greens+5*reds+whites);
			return false;
		}
		
	}
	
	public String toString() {
		return "Blacks="+this.blacks+"|Greens="+this.greens+"|Reds="+this.reds+"|Whites="+this.whites;
	}
	
	public static void main(String[] args){
		Chips pocket=new Chips(0,0,0,0);
		pocket.convert_chips(131);
		System.out.println(pocket.toString());
		pocket.validate_bet(4.6);
	}
}
