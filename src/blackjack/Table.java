package blackjack;

public abstract class Table {
	int[][] plays;
	
	public abstract void play();
	public void hit(Player player,Shoe shoe) {
		/*System.out.println(player.handStr() + " " + String.valueOf(player.handValue()));
		System.out.println("You should hit");
		player.addCardtoHand(shoe.deal());*/
	}
	public void stand(Player player) {
		//System.out.println(player.handStr() + " " + String.valueOf(player.handValue()));
		//System.out.println("You should stand");
	}
	public void split(Player player) {
		
	}
	public void Dh(Player player) {
		
	}
	public void Ds(Player player) {
		
	}
	public void Rh(Player player) {
		
	}
}
