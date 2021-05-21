package blackjack;
import java.lang.Math;

/**
 * Objeto para traduzir um balanço monetário
 * em fichas de jogo do casino
 */
public class Chips {
	
	/**
	 * Número de fichas pretas.
	 * Cada ficha preta vale 100$.
	 */
	int blacks;
	
	/**
	 * Número de fichas verde.
	 * Cada ficha verde vale 25$.
	 */
	int greens;
	
	/**
	 * Número de fichas vermelhas.
	 * Cada ficha vermelha vale 5$.
	 */
	int reds;
	
	/**
	 * Número de fichas brancas.
	 * Cada ficha branca vale 1$.
	 */
	int whites;
	
	/**
	 * Construtor que fornece as fichas a serem fornecidas
	 * @param blacks fichas pretas de valor 100
	 * @param greens fichas verdes de valor 25
	 * @param reds fichas vermelhas de valor 5
	 * @param whites fichas brancas de valor 1
	 */
	public Chips(int blacks,int greens, int reds,int whites) {
		this.blacks = blacks;
		this.greens = greens;
		this.reds = reds;
		this.whites = whites;
	}
	
	/**
	 * Converte o valor monetário para fichas
	 * @param money dinheiro a ser convertido
	 */
	public void convert_chips(double money) {
		this.blacks = (int) Math.floor(money/100);
		this.greens = (int) Math.floor((money - 100*this.blacks)/25);
		this.reds = (int) Math.floor((money - 100*this.blacks - 25*this.greens)/5);
		this.whites = (int) money - 100*this.blacks - 25*this.greens - 5*this.reds;
	}
	
	/**
	 * Verifica se uma bet fornecida é convertível em chips
	 * @param bet dinheiro a ser verificado
	 * @return boleano que diz se a aposta é válida
	 */
	public boolean validate_bet(double bet) {
		int blacks=(int)Math.floor(bet/100);
		int greens=(int) Math.floor((bet-100*blacks)/25);
		int reds=(int)Math.floor((bet-100*blacks-25*greens)/5);
		int whites=(int)bet-100*blacks-25*greens-5*reds;
		
		if(bet == 100*blacks + 25*greens + 5*reds + whites)
			return true;
		else
			return false;	
	}
	
	/**
	 * Imprime quantas fichas estão disponíveis
	 * @return Descrição das fichas disponíveis
	 */
	public String toString() {
		return "Blacks="+this.blacks+"|Greens="+this.greens+"|Reds="+this.reds+"|Whites="+this.whites;
	}
}
