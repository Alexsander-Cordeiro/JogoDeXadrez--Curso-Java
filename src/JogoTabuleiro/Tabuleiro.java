package JogoTabuleiro;

public class Tabuleiro {
	
	private int linhas;
	private int colunas;
	private Peca[][] pecas;
	
	
	public Tabuleiro(int linhas, int colunas) {
		if(linhas <1 || colunas < 1) {
			throw new ExcecaoTabuleiro("Erro ao criar o tabuleiro:deve haver pelo menos 1 linha e 1 coluna");
		}
		
		this.linhas = linhas;
		this.colunas = colunas;
		pecas = new Peca[linhas][colunas];
	}


	public int getLinhas() {
		return linhas;
	}


	public int getColunas() {
		return colunas;
	}

	
	//Retornar a peça pela linha e coluna
	public Peca peca(int linha, int coluna) {
		if(!posicaoExiste(linha, coluna)) {
			throw new ExcecaoTabuleiro("Posição não existe no tabuleiro");
		}
		return pecas[linha][coluna];
	}
	
	//Retornar a peça pela a posição
	public Peca peca(Posicao posicao) {
		if(!posicaoExiste(posicao)) {
			throw new ExcecaoTabuleiro("Posição não existe no tabuleiro");
		}
		return pecas[posicao.getLinha()][posicao.getColuna()];
	}
	
	public void colocarPeca(Peca peca, Posicao posicao) {
		if(ExisteAPeca(posicao)) {
			throw new ExcecaoTabuleiro("já existe uma peça na posição" + posicao);
		}
		pecas[posicao.getLinha()][posicao.getColuna()] = peca;
		peca.posicao = posicao;
	}
	
	public boolean posicaoExiste(int linha, int coluna) {
		return linha >= 0 && linha < linhas && coluna >= 0 && coluna < colunas;
	}
	
	
	//verifica se existe esta posiçao no tabuleiro
	public boolean posicaoExiste (Posicao posicao) {
		return posicaoExiste(posicao.getLinha(), posicao.getColuna());
		
	}
	
	//verificando se existe uma peca nesta posiçao
	public boolean ExisteAPeca (Posicao posicao) {
		if(!posicaoExiste(posicao)) {
			throw new ExcecaoTabuleiro("Posição não existe no tabuleiro");
		}
	return peca(posicao) != null;
	}
	
	
	

}
