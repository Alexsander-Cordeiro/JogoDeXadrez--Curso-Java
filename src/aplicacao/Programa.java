package aplicacao;

import Xadrez.PartidaDeXadrez;

public class Programa {

	public static void main(String[] args) {
		
		PartidaDeXadrez partidaDeXadrez = new PartidaDeXadrez();
		
		//Recebe a MATRIZ de pe�as da minha partida
		UI.printTabuleiro(partidaDeXadrez.getPecas());
		
		
	}

}
