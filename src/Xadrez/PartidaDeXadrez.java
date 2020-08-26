package Xadrez;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import JogoTabuleiro.Peca;
import JogoTabuleiro.Posicao;
import JogoTabuleiro.Tabuleiro;
import Xadrez.pecas.Bispo;
import Xadrez.pecas.Cavalo;
import Xadrez.pecas.Peao;
import Xadrez.pecas.Rei;
import Xadrez.pecas.Torre;

public class PartidaDeXadrez {

	
	private int turno;
	private Cor jogadorAtual;
	private Tabuleiro tabuleiro;
	private boolean check;
	private boolean checkMate;
	
	private List<Peca> pecasNoTabuleiro = new ArrayList<>();
	private List<Peca> pecasCapturadas = new ArrayList<>();

	
	//nesse método tem que colocar o tamanho do tabuleiro de xadrez
	public PartidaDeXadrez() {
		tabuleiro = new Tabuleiro(8,8);
		turno = 1;
		jogadorAtual = Cor.WHITE;
		configuracaoInicial();
		
	}
	
	public int getTurno() {
		return turno;
	}
	
	public Cor getJogadorAtual() {
		return jogadorAtual;
	}
	
	public boolean getCheck() {
		return check;
	}
	
	public boolean getCheckMate() {
		return checkMate;
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
	
	
	//Método para imprimir quais as possiveis posicoes que existem para a peca de origem
	public boolean[][] possiveisMovimentos(PosicaoXadrez posicaoOrigem){
		Posicao posicao = posicaoOrigem.toPosicao();
		validacaoPosicaoOrigem(posicao);
		return tabuleiro.peca(posicao).possiveisMovimentos();
		
	}
	
	
	public PecaDeXadrez desempenhoMovimentoXadrez(PosicaoXadrez posicaoOrigem, PosicaoXadrez posicaoDestino) {
		Posicao origem = posicaoOrigem.toPosicao();
		Posicao destino = posicaoDestino.toPosicao();
		validacaoPosicaoOrigem(origem);
		validacaoPosicaoDestino(origem,destino);
		Peca capturaPeca = fazerMover(origem, destino);
		
		if(testeDeCheck(jogadorAtual)) {
			desfazerMovimento(origem, destino, capturaPeca);
			throw new ExcecaoXadrez("Você não pode se colocar em Check");
		}
		
		check = (testeDeCheck(oponente(jogadorAtual))) ? true : false;
		
		if(testeDeCheckMate(oponente(jogadorAtual))) {
			checkMate = true;
		}
		else {	
		proximoTurno();
		}
		return (PecaDeXadrez)capturaPeca;
	}
	
	public Peca fazerMover(Posicao origem, Posicao destino) {
		PecaDeXadrez p = (PecaDeXadrez)tabuleiro.removerPeca(origem);
		p.incrementarContadorMovimento();
		Peca capturaPeca = tabuleiro.removerPeca(destino);
		tabuleiro.colocarPeca(p, destino);
		
		if (capturaPeca != null) {
			pecasNoTabuleiro.remove(capturaPeca);
			pecasCapturadas.add(capturaPeca);
		}
		return capturaPeca;
	}
	
	//Desfazer o movimento
	private void desfazerMovimento(Posicao origem, Posicao destino, Peca pecaCapturada) {
		PecaDeXadrez p = (PecaDeXadrez)tabuleiro.removerPeca(destino);
		p.decrementarContadorMovimento();
		tabuleiro.colocarPeca(p, origem);
		
		if(pecaCapturada != null) {
			tabuleiro.colocarPeca(pecaCapturada, destino);
			pecasCapturadas.remove(pecaCapturada);
			pecasNoTabuleiro.add(pecaCapturada);
		}
	}
	
	private void validacaoPosicaoOrigem(Posicao posicao) {
		if(!tabuleiro.ExisteAPeca(posicao)) {
			throw new ExcecaoXadrez("Não existe peça na posição de origem");
		}
		if (jogadorAtual != ((PecaDeXadrez)tabuleiro.peca(posicao)).getCor()){
			throw new ExcecaoXadrez("A peca escolhida nao e sua.");
		}
		if(!tabuleiro.peca(posicao).temUmaPossivelMovimento()) {
			throw new ExcecaoXadrez("Não existe movimentos possiveis para a peca escolhida");
		}
	}
	
	private void validacaoPosicaoDestino(Posicao origem, Posicao destino) {
		if (!tabuleiro.peca(origem).possivelMovimento(destino)) {
			throw new ExcecaoXadrez("A peca de origem não pode se mover para a posicao de destino.");
		}
	}
	
	private void proximoTurno() {
		turno++;
		jogadorAtual = (jogadorAtual == Cor.WHITE) ? Cor.BLACK: Cor.WHITE;
		
	}
	
	private Cor oponente(Cor cor) {
		return (cor == Cor.WHITE) ? Cor.BLACK : Cor.WHITE;
	}
	
	
	private PecaDeXadrez rei(Cor cor) {
		List<Peca> lista = pecasNoTabuleiro.stream().filter(x -> ((PecaDeXadrez)x).getCor() == cor).collect(Collectors.toList());
		for(Peca p: lista) {
			if (p instanceof Rei) {
				return (PecaDeXadrez)p;
			}
		}
		throw new IllegalStateException("Nao existe  o rei da cor "+ cor + " no tabuleiro");
	}
	
	//Método para testar se o rei esta em CHECK
	private boolean testeDeCheck(Cor cor) {
		Posicao PosicaoRei = rei(cor).getPosicaoXadrez().toPosicao();
		List<Peca> pecasDoOponente = pecasNoTabuleiro.stream().filter(x -> ((PecaDeXadrez)x).getCor() == oponente(cor)).collect(Collectors.toList());
		for (Peca p : pecasDoOponente) {
			boolean[][] mat = p.possiveisMovimentos();
			if(mat[PosicaoRei.getLinha()][PosicaoRei.getColuna()]) {
				return true;
			}
		}
		return false;
	}
	
	//Método para testar se o rei esta em CHECKMATE
	private boolean testeDeCheckMate(Cor cor) {
		if(!testeDeCheck(cor)) {
			return false;
		}
		
		List<Peca> lista = pecasNoTabuleiro.stream().filter(x -> ((PecaDeXadrez)x).getCor() == cor).collect(Collectors.toList());
		for(Peca p : lista) {
			boolean[][] mat = p.possiveisMovimentos();
			for(int i=0; i< tabuleiro.getLinhas();i++) {
				for(int j=0; j< tabuleiro.getColunas();j++) {
					if(mat[i][j]) {
						Posicao origem = ((PecaDeXadrez)p).getPosicaoXadrez().toPosicao();
						Posicao destino = new Posicao(i,j);
						Peca pecaCapturada = fazerMover(origem,destino);
						boolean testeDeCheck = testeDeCheck(cor);
						desfazerMovimento(origem, destino, pecaCapturada);
						if(!testeDeCheck) {
							return false;
						}
						
					}
				}
			}
		}
		return true;
	}
	
	private void colocarNovaPeca(char coluna,int linha, PecaDeXadrez peca) {
		tabuleiro.colocarPeca(peca,new PosicaoXadrez(coluna, linha).toPosicao());
		pecasNoTabuleiro.add(peca);
	}
	
	private void configuracaoInicial() {
	
		colocarNovaPeca('a', 1, new Torre(tabuleiro, Cor.WHITE));
		colocarNovaPeca('b', 1, new Cavalo(tabuleiro, Cor.WHITE));
		colocarNovaPeca('c', 1, new Bispo(tabuleiro, Cor.WHITE));
		colocarNovaPeca('f', 1, new Bispo(tabuleiro, Cor.WHITE));
		colocarNovaPeca('e', 1, new Rei(tabuleiro, Cor.WHITE));
		colocarNovaPeca('g', 1, new Cavalo(tabuleiro, Cor.WHITE));
		colocarNovaPeca('h', 1, new Torre(tabuleiro, Cor.WHITE));
		colocarNovaPeca('a', 2, new Peao(tabuleiro, Cor.WHITE));
		colocarNovaPeca('b', 2, new Peao(tabuleiro, Cor.WHITE));
		colocarNovaPeca('c', 2, new Peao(tabuleiro, Cor.WHITE));
		colocarNovaPeca('d', 2, new Peao(tabuleiro, Cor.WHITE));
		colocarNovaPeca('e', 2, new Peao(tabuleiro, Cor.WHITE));
		colocarNovaPeca('f', 2, new Peao(tabuleiro, Cor.WHITE));
        colocarNovaPeca('g', 2, new Peao(tabuleiro, Cor.WHITE));
        colocarNovaPeca('h', 2, new Peao(tabuleiro, Cor.WHITE));
        
        colocarNovaPeca('a', 8, new Torre(tabuleiro, Cor.BLACK));
        colocarNovaPeca('b', 8, new Cavalo(tabuleiro, Cor.BLACK));
        colocarNovaPeca('c', 8, new Bispo(tabuleiro, Cor.BLACK));
        colocarNovaPeca('f', 8, new Bispo(tabuleiro, Cor.BLACK));
        colocarNovaPeca('e', 8, new Rei(tabuleiro, Cor.BLACK));	 
        colocarNovaPeca('g', 8, new Cavalo(tabuleiro, Cor.BLACK));
        colocarNovaPeca('h', 8, new Torre(tabuleiro, Cor.BLACK));
        colocarNovaPeca('a', 7, new Peao(tabuleiro, Cor.BLACK));
        colocarNovaPeca('b', 7, new Peao(tabuleiro, Cor.BLACK));
        colocarNovaPeca('c', 7, new Peao(tabuleiro, Cor.BLACK));
        colocarNovaPeca('d', 7, new Peao(tabuleiro, Cor.BLACK));
        colocarNovaPeca('e', 7, new Peao(tabuleiro, Cor.BLACK));
        colocarNovaPeca('f', 7, new Peao(tabuleiro, Cor.BLACK));
        colocarNovaPeca('g', 7, new Peao(tabuleiro, Cor.BLACK));
        colocarNovaPeca('h', 7, new Peao(tabuleiro, Cor.BLACK));
       	}
}
