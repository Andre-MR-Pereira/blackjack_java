package blackjack;

public class SoftTable extends Table{

	public SoftTable() {
		for(int i=0;i<8;i++) {
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
	
	public void play() {
		// ver matrix
		
	}

}
