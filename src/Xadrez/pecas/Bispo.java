package Xadrez.pecas;

import JogoTabuleiro.Posicao;
import JogoTabuleiro.Tabuleiro;
import Xadrez.Cor;
import Xadrez.PecaDeXadrez;

public class Bispo extends PecaDeXadrez{

	

	public Bispo(Tabuleiro tabuleiro, Cor cor) {
		super(tabuleiro, cor);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "B";
	}
	
	@Override
	public boolean[][] possiveisMovimentos() {
		boolean[][] mat = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];
		
		Posicao p = new Posicao(0,0);
		
		//Noroeste
		p.setValores(posicao.getLinha() -1, posicao.getColuna() -1);
		while(getTabuleiro().posicaoExiste(p) && !getTabuleiro().ExisteAPeca(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
			p.setValores(p.getLinha() -1,p.getColuna() -1);
		}
		
		if(getTabuleiro().posicaoExiste(p) && haPecaOpenente(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		
		//Nordeste
		p.setValores(posicao.getLinha() -1, posicao.getColuna() +1); 
		while(getTabuleiro().posicaoExiste(p) && !getTabuleiro().ExisteAPeca(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
			p.setValores(p.getLinha() -1,p.getColuna() +1);
		}
		
		if(getTabuleiro().posicaoExiste(p) && haPecaOpenente(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		
		//Suldeste
		p.setValores(posicao.getLinha() +1, posicao.getColuna() +1);
		while(getTabuleiro().posicaoExiste(p) && !getTabuleiro().ExisteAPeca(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
			p.setValores(p.getLinha() +1,p.getColuna() +1);
		}
		
		if(getTabuleiro().posicaoExiste(p) && haPecaOpenente(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		
		
		//Suldoeste
		p.setValores(posicao.getLinha() +1, posicao.getColuna() -1 );
		while(getTabuleiro().posicaoExiste(p) && !getTabuleiro().ExisteAPeca(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
			p.setValores(p.getLinha() +1,p.getColuna() -1);
		}	
		
		if(getTabuleiro().posicaoExiste(p) && haPecaOpenente(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		
		
		return mat;
	}
}
