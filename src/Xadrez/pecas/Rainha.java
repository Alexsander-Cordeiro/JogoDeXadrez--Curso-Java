package Xadrez.pecas;

import JogoTabuleiro.Posicao;
import JogoTabuleiro.Tabuleiro;
import Xadrez.Cor;
import Xadrez.PecaDeXadrez;

public class Rainha extends PecaDeXadrez{

	public Rainha(Tabuleiro tabuleiro, Cor cor) {
		super(tabuleiro, cor);
		
	}

	@Override
	public String toString() {
		return "N";
	}
	
	@Override
	public boolean[][] possiveisMovimentos() {
		boolean[][] mat = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];
		
		Posicao p = new Posicao(0,0);
		
		//ACIMA 
		p.setValores(posicao.getLinha() -1, posicao.getColuna() );
		while(getTabuleiro().posicaoExiste(p) && !getTabuleiro().ExisteAPeca(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
			p.setLinha(p.getLinha() - 1);
		}
		
		if(getTabuleiro().posicaoExiste(p) && haPecaOpenente(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		
		// ESQUERDA
		p.setValores(posicao.getLinha(), posicao.getColuna() -1);
		while(getTabuleiro().posicaoExiste(p) && !getTabuleiro().ExisteAPeca(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
			p.setColuna(p.getColuna() -1);;
		}
		
		if(getTabuleiro().posicaoExiste(p) && haPecaOpenente(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		
		// DIREITA
		p.setValores(posicao.getLinha(), posicao.getColuna() +1);
		while(getTabuleiro().posicaoExiste(p) && !getTabuleiro().ExisteAPeca(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
			p.setColuna(p.getColuna() +1);;
		}
		
		if(getTabuleiro().posicaoExiste(p) && haPecaOpenente(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		
		
		//BAIXO
		p.setValores(posicao.getLinha() +1, posicao.getColuna() );
		while(getTabuleiro().posicaoExiste(p) && !getTabuleiro().ExisteAPeca(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
			p.setLinha(p.getLinha() + 1);
		}	
		
		if(getTabuleiro().posicaoExiste(p) && haPecaOpenente(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		
		//NOROESTE
		p.setValores(posicao.getLinha() -1, posicao.getColuna() -1);
		while(getTabuleiro().posicaoExiste(p) && !getTabuleiro().ExisteAPeca(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
			p.setValores(p.getLinha() -1,p.getColuna() -1);
		}
		
		if(getTabuleiro().posicaoExiste(p) && haPecaOpenente(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		
		//NORDESTE
		p.setValores(posicao.getLinha() -1, posicao.getColuna() +1); 
		while(getTabuleiro().posicaoExiste(p) && !getTabuleiro().ExisteAPeca(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
			p.setValores(p.getLinha() -1,p.getColuna() +1);
		}
		
		if(getTabuleiro().posicaoExiste(p) && haPecaOpenente(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		
		//SULDESTE
		p.setValores(posicao.getLinha() +1, posicao.getColuna() +1);
		while(getTabuleiro().posicaoExiste(p) && !getTabuleiro().ExisteAPeca(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
			p.setValores(p.getLinha() +1,p.getColuna() +1);
		}
		
		if(getTabuleiro().posicaoExiste(p) && haPecaOpenente(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		
		
		//SULDOESTE
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
