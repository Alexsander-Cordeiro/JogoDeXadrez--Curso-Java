package Xadrez.pecas;

import JogoTabuleiro.Posicao;
import JogoTabuleiro.Tabuleiro;
import Xadrez.Cor;
import Xadrez.PartidaDeXadrez;
import Xadrez.PecaDeXadrez;

public class Peao  extends PecaDeXadrez{
	
	private PartidaDeXadrez partidaDeXadrez;

	public Peao(Tabuleiro tabuleiro, Cor cor,PartidaDeXadrez partidaDeXadrez) {
		super(tabuleiro, cor);
		this.partidaDeXadrez = partidaDeXadrez;
		
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
			
			//Movimento Especial EN PASSANT brancas
			if(posicao.getLinha() == 3) {
				Posicao esquerda = new Posicao(posicao.getLinha(),posicao.getColuna() -1);
				if(getTabuleiro().posicaoExiste(esquerda) && haPecaOpenente(esquerda)
					&& getTabuleiro().peca(esquerda) == partidaDeXadrez.getEnPassantVulneravel()) {
					mat[esquerda.getLinha() -1][esquerda.getColuna()] = true;
				}
			
				Posicao direita = new Posicao(posicao.getLinha(),posicao.getColuna() +1);
				if(getTabuleiro().posicaoExiste(direita) && haPecaOpenente(direita)
						&& getTabuleiro().peca(direita) == partidaDeXadrez.getEnPassantVulneravel()) {
					mat[direita.getLinha() -1][direita.getColuna()] = true;
				}
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
			
			//Movimento Especial EN PASSANT pretas
			if(posicao.getLinha() == 4) {
				Posicao esquerda = new Posicao(posicao.getLinha(),posicao.getColuna() -1);
				if(getTabuleiro().posicaoExiste(esquerda) && haPecaOpenente(esquerda)
					&& getTabuleiro().peca(esquerda) == partidaDeXadrez.getEnPassantVulneravel()) {
					mat[esquerda.getLinha() +1][esquerda.getColuna()] = true;
				}
			
				Posicao direita = new Posicao(posicao.getLinha(),posicao.getColuna() +1);
				if(getTabuleiro().posicaoExiste(direita) && haPecaOpenente(direita)
						&& getTabuleiro().peca(direita) == partidaDeXadrez.getEnPassantVulneravel()) {
					mat[direita.getLinha() +1][direita.getColuna()] = true;
				}
			}
			
		}
		
		return mat;
	}
	
	@Override
	public String toString() {
		return "P";
	}
	
	

}
