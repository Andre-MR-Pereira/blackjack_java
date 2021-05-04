package blackjack;

public class PairTable extends Table{

	public PairTable() {
		for(int i=0;i<9;i++) {
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
	
	public void play() {
		//ver matrix
		
	}

}
