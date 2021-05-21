package blackjack;

import java.util.Arrays;
import java.io.FileInputStream;
import java.util.Scanner; // Import the Scanner class to read text files
import java.util.Collections;
import java.util.List;

/**
 * Classe que representa o Shoe
 * É uma das partes essenciais de um jogo de blackjack.
 */
public class Shoe {

	/**
	 * Um Shoe é representado por um vetor de cartas
	 */
	private Card[] shoe;
	
	/**
	 * Número de decks de 52 cartas que o shoe contém
	 */
	private int ndecks;
	
	/**
	 * Variável que indica em que posição do vetor de cartas se está.
	 * Indica portanto qual o índice da carta do topo do deck.
	 * Toma o valor 0 sempre que o shoe é baralhado.
	 */
	private int currCard;
	
	/**
	 * Percentagem do Shoe na qual este é baralhado e se recomeça no número de cartas original (52*ndecks).
	 */
	private int percentage;
	
	/**
	 * Construtor do shoe. Cria todas as cartas consoante o número de decks que é proposto.
	 * Ainda baralha o Shoe para que fique pronto a jogar.
	 * @param ndecks número de decks de 52 cartas que o shoe vai conter.
	 * @param percentage percentagem do shoe em que é baralhado.
	 */
	public Shoe(int ndecks,int percentage) {
		this.ndecks = ndecks;
		
		shoe = new Card[52*ndecks];
		
		int i = 0;
		for (int j = 0; j < ndecks; j++)
			for(int suit = 1; suit < 5; suit++)
				for(int face = 1; face <= 13; face++)
					shoe[i++] = new Card(face, suit);
		
		shuffle();
		
		this.percentage=percentage;
	}
	
	/**
	 * Construtor do Shoe para o modo de debug.
	 * Neste caso o Shoe é lido de um ficheiro e nunca pode ser baralhado.
	 * @param shoe_file ficheiro que contém o shoe deste modo
	 */
	public Shoe(FileInputStream shoe_file) {
		Scanner reader;
		String data = null;
		reader = new Scanner(shoe_file);
		
        while (reader.hasNextLine()) {
        	data = reader.nextLine();
        	if(data == null) {
        		System.out.println("Shoe file is empty");
				System.exit(1);
            }
                
        	String[] fields = data.split(" ");
            this.ndecks = (int) Math.ceil((double)fields.length/(double)52);
            if(this.ndecks < 1 || this.ndecks > 8) {
                System.out.println("The amount of decks in the shoe isn´t correct");
				System.exit(1);
            }
            
            this.shoe = new Card[fields.length];
            for(int i = 0; i < fields.length; i++) {
            	char c_value = ' ';
                char c_suit = ' ';
    			String[] splited = fields[i].split("");
    				
    			if(splited.length < 2 || splited.length > 3) {
    				System.out.println("A card is not formated correctly");
					System.exit(1);
    			}
    			else if(splited.length == 2) {
    				c_value = splited[0].charAt(0);
                    c_suit = splited[1].charAt(0);
    			}
    			else if(splited.length == 3) {
    				c_value = splited[0].charAt(0);
    				c_suit = splited[2].charAt(0);
    			}
                	
                int value = -1;
                int suit = -1;
                if(c_value == 'A')
                	value = 1;
                else if(c_value == 'J')
                	value = 11;
                else if(c_value == 'Q')
                	value = 12;
                else if(c_value == 'K')
                	value = 13;
                else {
                	try {
                		if(splited.length == 3)
                			value = Integer.parseInt(splited[0].concat(splited[1]));
                		else
                			value = Integer.parseInt(splited[0]);
                	}
                	catch(Exception e) {
                		System.out.println("A card is not formated correctly");
    					System.exit(1);
                	}
                }
                	
                if(c_suit == 'H')
                	suit = 1;
                else if(c_suit == 'S')
                	suit = 2;
                else if(c_suit == 'C')
                	suit = 3;
                else if(c_suit == 'D')
                	suit = 4;

                if(value < 1 || value > 13) {
    				System.out.println("A card doesn´t have a valid suit");
    				System.exit(1);
    			}
    			if(suit == -1) {
    				System.out.println("A card doesn´t have a valid suit");
    				System.exit(1);
    			}
    			Card card = new Card(value, suit);
    			this.shoe[i] = card;
    		}
        }
        	reader.close();
        	this.currCard = 0;
	}
	
	/**
	 * Método que dà carta do topo do shoe
	 * @return Carta que foi dada
	 */
	public Card deal() {
		try {
			Card curr = shoe[currCard++];
			return(curr);
		}
		catch(Exception e) {
			System.out.println("Out of cards.");
			System.exit(1);
		}
		return(null);
	}
	
	/**
	 * Método que verifica se o shoe precisa de ser baralhado
	 * @return True se foi baralhado, False se não foi baralhado
	 */
	public boolean check() {
		if ((double)(currCard*100)/((double)52*ndecks) > percentage) {
			shuffle();
			return true;
		}
		return false;
	}
	
	/**
	 * Método que verifica quantos decks ainda restam no shoe
	 * @return Número de decks que ainda restam no shoe
	 */
	public double decks_left() {
		return shoe.length/52;
	}
	
	/**
	 * Método que baralha o shoe
	 * Faz reset ao currCard para 0, indicando que se volta à carta do topo.
	 */
	public void shuffle() {
		
		System.out.println("shuffling the shoe...");
		
		List<Card> bufferList = Arrays.asList(shoe);

		Collections.shuffle(bufferList);

		bufferList.toArray(this.shoe);

		currCard = 0;
	}
}
