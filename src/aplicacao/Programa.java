package aplicacao;

import java.util.InputMismatchException;
import java.util.Scanner;

import Xadrez.ExcecaoXadrez;
import Xadrez.PartidaDeXadrez;
import Xadrez.PecaDeXadrez;
import Xadrez.PosicaoXadrez;

public class Programa {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		PartidaDeXadrez partidaDeXadrez = new PartidaDeXadrez();
		
		//Recebe a MATRIZ de peças da minha partida
		while(true) {
			try {
				UI.clearScreen();
				UI.printPartida(partidaDeXadrez);
				System.out.println();
				System.out.print("Origem: ");
				PosicaoXadrez origem = UI.lerPosicaoDoXadrez(sc);
				
				boolean[][] possiveisMovimentos = partidaDeXadrez.possiveisMovimentos(origem);
				UI.clearScreen();
				UI.printTabuleiro(partidaDeXadrez.getPecas(), possiveisMovimentos);
								
				System.out.println();
				System.out.print("Destino: ");
				PosicaoXadrez destino = UI.lerPosicaoDoXadrez(sc);
				
				PecaDeXadrez capturaPeca = partidaDeXadrez.desempenhoMovimentoXadrez(origem, destino);
			}
			catch (ExcecaoXadrez e) {
				System.out.println(e.getMessage());
				sc.nextLine();
			}
			catch (InputMismatchException e) {
				System.out.println(e.getMessage());
				sc.nextLine();
			}
			
		}
		
		
		
	}

}
