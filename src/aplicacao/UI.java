package aplicacao;

import Xadrez.PecaDeXadrez;

public class UI {
	
	public static void printTabuleiro(PecaDeXadrez[][] pecas) {
		//percorrer a linha colocando o numero dela
		for(int i=0; i< pecas.length;i++) {
			System.out.print((8-i) + " ");
			
			//percorrer as colunas e colocando a peça em cada uma e no final pulando de linha.
			for(int j=0; j<pecas.length; j++) {
				printPeca(pecas[i][j]);
			}
			System.out.println();
		}
		//Colocando no final as letras em cada coluna
		System.out.print("  a b c d e f g h");
	}
	
	private static void printPeca(PecaDeXadrez peca) {
		if(peca == null) {
			System.out.print("-");
		}
		else {
			System.out.print(peca);
		}
		System.out.print(" ");
	}

}
