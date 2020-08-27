package aplicacao;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import Xadrez.ExcecaoXadrez;
import Xadrez.PartidaDeXadrez;
import Xadrez.PecaDeXadrez;
import Xadrez.PosicaoXadrez;

public class Programa {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		PartidaDeXadrez partidaDeXadrez = new PartidaDeXadrez();
		List<PecaDeXadrez> captura = new ArrayList<>();
		
		//Recebe a MATRIZ de peças da minha partida
		while(!partidaDeXadrez.getCheckMate()) {
			try {
				UI.clearScreen();
				UI.printPartida(partidaDeXadrez, captura);
				System.out.println();
				System.out.print("Origem: ");
				PosicaoXadrez origem = UI.lerPosicaoDoXadrez(sc);
				
				boolean[][] possiveisMovimentos = partidaDeXadrez.possiveisMovimentos(origem);
				UI.clearScreen();
				UI.printTabuleiro(partidaDeXadrez.getPecas(), possiveisMovimentos);
								
				System.out.println();
				System.out.print("Destino: ");
				PosicaoXadrez destino = UI.lerPosicaoDoXadrez(sc);
				
				//Sempre que efetuar um movimento a pecaCapturada irá ser adicionada a lista captura.
				PecaDeXadrez pecaCapturada = partidaDeXadrez.desempenhoMovimentoXadrez(origem, destino);
				
				if (pecaCapturada != null) {
					captura.add(pecaCapturada);
				}
				
				if(partidaDeXadrez.getPromover() != null) {
					System.out.print("Entre com a peça que será promovida(B/N/R/Q): ");
					String type = sc.nextLine();
					partidaDeXadrez.colocarPecaPromovida(type);
				}
				
				
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
		
		UI.clearScreen();
		UI.printPartida(partidaDeXadrez, captura);
		
	}

}
