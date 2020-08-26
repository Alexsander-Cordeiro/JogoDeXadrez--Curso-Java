package Xadrez.pecas;

import JogoTabuleiro.Posicao;
import JogoTabuleiro.Tabuleiro;
import Xadrez.Cor;
import Xadrez.PecaDeXadrez;

public class Peao  extends PecaDeXadrez{

	public Peao(Tabuleiro tabuleiro, Cor cor) {
		super(tabuleiro, cor);
		
	}

	@Override
	public boolean[][] possiveisMovimentos() {
		boolean[][] mat = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];
		
		Posicao p = new Posicao(0,0);
		
		if(getCor() == Cor.WHITE) {
			//Condição para verificar se pode fazer um movimento para FRENTE
			p.setValores(posicao.getLinha()-1, posicao.getColuna());
			if(getTabuleiro().posicaoExiste(p) && !getTabuleiro().ExisteAPeca(p)) {
				mat[p.getLinha()][p.getColuna()] = true;
			}
			//Condição para verificar se pode fazer o movimento de duas casas a FRENTE ou UMA.
			p.setValores(posicao.getLinha()-2, posicao.getColuna());
			Posicao p2 = new Posicao(posicao.getLinha()-1, posicao.getColuna());
			if(getTabuleiro().posicaoExiste(p) && !getTabuleiro().ExisteAPeca(p) && getTabuleiro()
					.posicaoExiste(p2) && !getTabuleiro().ExisteAPeca(p2) && getContarMovimento() == 0) {
				mat[p.getLinha()][p.getColuna()] = true;
			}
			
			//Condição para verificar se pode fazer um movimento para DIACONAL ESQUERDA
			p.setValores(posicao.getLinha()-1, posicao.getColuna()-1);
			if(getTabuleiro().posicaoExiste(p) && haPecaOpenente(p)) {
				mat[p.getLinha()][p.getColuna()] = true;
		
			}
			
			//Condição para verificar se pode fazer um movimento para DIACONAL DIREITA
			p.setValores(posicao.getLinha()-1, posicao.getColuna()+1);
			if(getTabuleiro().posicaoExiste(p) && haPecaOpenente(p)) {
				mat[p.getLinha()][p.getColuna()] = true;
		
			}
		}
		else {
			//Condição para verificar se pode fazer um movimento para FRENTE
			p.setValores(posicao.getLinha()+1, posicao.getColuna());
			if(getTabuleiro().posicaoExiste(p) && !getTabuleiro().ExisteAPeca(p)) {
				mat[p.getLinha()][p.getColuna()] = true;
			}
			//Condição para verificar se pode fazer o movimento de duas casas a FRENTE ou UMA.
			p.setValores(posicao.getLinha()+2, posicao.getColuna());
			Posicao p2 = new Posicao(posicao.getLinha()+1, posicao.getColuna());
			if(getTabuleiro().posicaoExiste(p) && !getTabuleiro().ExisteAPeca(p) && getTabuleiro()
					.posicaoExiste(p2) && !getTabuleiro().ExisteAPeca(p2) && getContarMovimento() == 0) {
				mat[p.getLinha()][p.getColuna()] = true;
			}
			
			//Condição para verificar se pode fazer um movimento para DIACONAL ESQUERDA
			p.setValores(posicao.getLinha()+1, posicao.getColuna()-1);
			if(getTabuleiro().posicaoExiste(p) && haPecaOpenente(p)) {
				mat[p.getLinha()][p.getColuna()] = true;
		
			}
			
			//Condição para verificar se pode fazer um movimento para DIACONAL DIREITA
			p.setValores(posicao.getLinha()+1, posicao.getColuna()+1);
			if(getTabuleiro().posicaoExiste(p) && haPecaOpenente(p)) {
				mat[p.getLinha()][p.getColuna()] = true;
		
			}
			
		}
		
		return mat;
	}
	
	@Override
	public String toString() {
		return "P";
	}
	
	

}
