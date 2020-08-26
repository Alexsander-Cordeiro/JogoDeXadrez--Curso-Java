package Xadrez;

import JogoTabuleiro.Peca;
import JogoTabuleiro.Posicao;
import JogoTabuleiro.Tabuleiro;

public abstract class PecaDeXadrez extends Peca{
	

	private Cor cor;
	private int contarMovimento;

	public PecaDeXadrez(Tabuleiro tabuleiro, Cor cor) {
		super(tabuleiro);
		this.cor = cor;
	}

	public Cor getCor() {
		return cor;
	}
	
	public int getContarMovimento() {
		return contarMovimento;
	}
	
	public void incrementarContadorMovimento() {
		contarMovimento++;
	}
	
	public void decrementarContadorMovimento() {
		contarMovimento--;
	}
	
	public PosicaoXadrez getPosicaoXadrez() {
		return PosicaoXadrez.fromPosicao(posicao);
		
	}

	protected boolean haPecaOpenente(Posicao posicao) {
		PecaDeXadrez p =(PecaDeXadrez) getTabuleiro().peca(posicao);
		return p != null && p.getCor() != cor;
	}


}
