package aplicacao;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import Xadrez.Cor;
import Xadrez.PartidaDeXadrez;
import Xadrez.PecaDeXadrez;
import Xadrez.PosicaoXadrez;

public class UI {

	// https://stackoverflow.com/questions/5762491/how-to-print-color-in-console-using-system-out-println

	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_BLACK = "\u001B[30m";
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_YELLOW = "\u001B[33m";
	public static final String ANSI_BLUE = "\u001B[34m";
	public static final String ANSI_PURPLE = "\u001B[35m";
	public static final String ANSI_CYAN = "\u001B[36m";
	public static final String ANSI_WHITE = "\u001B[37m";

	public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
	public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
	public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
	public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
	public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
	public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
	public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
	public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";
	
	
	//https://stackoverflow.com/questions/2979383/java-clear-the-console
	
		//Limpar tela
	public static void clearScreen() {
		System.out.print("\033[H\033[2J");
		System.out.flush();
	}
	
	
	//Lendo a posi��o do xadrez
	public static PosicaoXadrez lerPosicaoDoXadrez(Scanner sc) {
		try {
		String s = sc.nextLine();
		char coluna = s.charAt(0);
		int linha = Integer.parseInt(s.substring(1));
		return new PosicaoXadrez(coluna, linha);
		}
		catch (RuntimeException e) {
			throw new InputMismatchException("Erro lendo posi��o de xadrez. Valores validos s�o de a1 at� h8");
			
		}
	}
	
	//IMPRIMIR TURNOS E A COR DO JOGADOR
	public static void printPartida(PartidaDeXadrez partidaDeXadrez, List<PecaDeXadrez> captura) {
		printTabuleiro(partidaDeXadrez.getPecas());
		System.out.println();
		printCapturaPecas(captura);
		System.out.println();
		System.out.println("Turno: "+ partidaDeXadrez.getTurno());
		if(!partidaDeXadrez.getCheckMate()) {
			System.out.println("Aguardando jogador: " + partidaDeXadrez.getJogadorAtual());
			if(partidaDeXadrez.getCheck()) {
				System.out.println("CHECK!");
			}
		}
		else {
			System.out.println("CHECKMATE!");
			System.out.println("Vencedor: "+ partidaDeXadrez.getJogadorAtual());
		}
	
	}

	//IMPRIMIR TABULEIRO 
	public static void printTabuleiro(PecaDeXadrez[][] pecas) {
		// percorrer a linha colocando o numero dela
		for (int i = 0; i < pecas.length; i++) {
			System.out.print((8 - i) + " ");

			// percorrer as colunas e colocando a pe�a em cada uma e no final pulando de
			// linha.
			for (int j = 0; j < pecas.length; j++) {
				printPeca(pecas[i][j], false);
			}
			System.out.println();
		}
		// Colocando no final as letras em cada coluna
		System.out.print("  a b c d e f g h");
	}
	
	
	//IMPRIMIR O TABULEIRO COM OS POSSIVEIS MOVIMENTOS
	public static void printTabuleiro(PecaDeXadrez[][] pecas, boolean[][] possiveisMovimentos) {
		// percorrer a linha colocando o numero dela
		for (int i = 0; i < pecas.length; i++) {
			System.out.print((8 - i) + " ");

			// percorrer as colunas e colocando a pe�a em cada uma e no final pulando de
			// linha.
			for (int j = 0; j < pecas.length; j++) {
				printPeca(pecas[i][j], possiveisMovimentos[i][j]);
			}
			System.out.println();
		}
		// Colocando no final as letras em cada coluna
		System.out.print("  a b c d e f g h");
	}

	//a variavel Background usada para colocar cor no fundo 
	private static void printPeca(PecaDeXadrez peca, boolean background) {
		if (background) {
			System.out.print(ANSI_BLUE_BACKGROUND);
		}
		if (peca == null) {
			System.out.print("-" + ANSI_RESET);
		} else {
			if (peca.getCor() == Cor.WHITE) {
				System.out.print(ANSI_WHITE + peca + ANSI_RESET);
			} else {
				System.out.print(ANSI_YELLOW + peca + ANSI_RESET);
			}
		}
		System.out.print(" ");
	}
	
	private static void printCapturaPecas(List<PecaDeXadrez> captura) {
		List<PecaDeXadrez> white = captura.stream().filter(x -> x.getCor() == Cor.WHITE)
				.collect(Collectors.toList());
		List<PecaDeXadrez> black = captura.stream().filter(x -> x.getCor() == Cor.BLACK)
				.collect(Collectors.toList());
		
		System.out.println("Pecas capturadas:");
		System.out.print("White: ");
		System.out.print(ANSI_WHITE);
		System.out.println(Arrays.deepToString(white.toArray()));
		System.out.print(ANSI_RESET);
		
	
		System.out.print("Black: ");
		System.out.print(ANSI_YELLOW);
		System.out.println(Arrays.deepToString(black.toArray()));
		System.out.print(ANSI_RESET);
		
	}

}
