package blackjack;

public class HardTable extends Table{
	
	public HardTable() {
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

	public void play() {
		//ver matrix
		
	}

}
