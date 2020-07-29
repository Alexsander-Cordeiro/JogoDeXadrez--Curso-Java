package Xadrez;

import JogoTabuleiro.Posicao;
import JogoTabuleiro.Tabuleiro;
import Xadrez.pecas.Rei;
import Xadrez.pecas.Torre;

public class PartidaDeXadrez {

	private Tabuleiro tabuleiro;
	
	//nesse método tem que colocar o tamanho do tabuleiro de xadrez
	public PartidaDeXadrez() {
		tabuleiro = new Tabuleiro(8,8);
		configuracaoInicial();
		
	}
	
	//Retornar uma MATRIZ peça de xadrez correspondente essa partida de xadrez
	public PecaDeXadrez[][] getPecas(){
		PecaDeXadrez[][] mat = new PecaDeXadrez[tabuleiro.getLinhas()][tabuleiro.getColunas()];
		
		for (int i =0;i<tabuleiro.getLinhas();i++) {
			for (int j=0; j<tabuleiro.getColunas();j++) {
				//fazendo um downCast falando que é um PecaDeXadrez e não um PECA
				mat[i][j] = (PecaDeXadrez) tabuleiro.peca(i, j);
			}
		}
		return mat;
	}	
	
	private void configuracaoInicial() {
		tabuleiro.ColocarPeca(new Torre(tabuleiro,Cor.WHITE), new Posicao(2,1));
		tabuleiro.ColocarPeca(new Rei(tabuleiro,Cor.BLACK), new Posicao(0,4));
		tabuleiro.ColocarPeca(new Rei(tabuleiro,Cor.WHITE), new Posicao(7,4));
	}
}
