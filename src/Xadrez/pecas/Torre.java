package Xadrez.pecas;

import JogoTabuleiro.Posicao;
import JogoTabuleiro.Tabuleiro;
import Xadrez.Cor;
import Xadrez.PecaDeXadrez;

public class Torre extends PecaDeXadrez{

	public Torre(Tabuleiro tabuleiro, Cor cor) {
		super(tabuleiro, cor);
		
	}

	@Override
	public String toString() {
		return "R";
	}
	
	@Override
	public boolean[][] possiveisMovimentos() {
		boolean[][] mat = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];
		
		Posicao p = new Posicao(0,0);
		
		//verificar ACIMA da minha peça
		p.setValores(posicao.getLinha() -1, posicao.getColuna() );
		while(getTabuleiro().posicaoExiste(p) && !getTabuleiro().ExisteAPeca(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
			p.setLinha(p.getLinha() - 1);
		}
		
		if(getTabuleiro().posicaoExiste(p) && haPecaOpenente(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		
		//verificar a ESQUERDA da minha peça
		p.setValores(posicao.getLinha(), posicao.getColuna() -1);
		while(getTabuleiro().posicaoExiste(p) && !getTabuleiro().ExisteAPeca(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
			p.setColuna(p.getColuna() -1);;
		}
		
		if(getTabuleiro().posicaoExiste(p) && haPecaOpenente(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		
		//verificar a DIREITA da minha peça
		p.setValores(posicao.getLinha(), posicao.getColuna() +1);
		while(getTabuleiro().posicaoExiste(p) && !getTabuleiro().ExisteAPeca(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
			p.setColuna(p.getColuna() +1);;
		}
		
		if(getTabuleiro().posicaoExiste(p) && haPecaOpenente(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		
		
		//verificar BAIXO da minha peça
		p.setValores(posicao.getLinha() +1, posicao.getColuna() );
		while(getTabuleiro().posicaoExiste(p) && !getTabuleiro().ExisteAPeca(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
			p.setLinha(p.getLinha() + 1);
		}	
		
		if(getTabuleiro().posicaoExiste(p) && haPecaOpenente(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		
		
		return mat;
	}
}
