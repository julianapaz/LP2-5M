package com.senac.jogos.labirinto;

public class Sala {
	private Chave chave;
	private Arma arma;
	
	Conexao[] conexoes = new Conexao[6];
	
	
	public void setInimigo(Inimigo inimigo, String direcao) throws Exception{
		conexoes[getDirecaoIndex(direcao)].setInimigo(inimigo);
	}
	
	public Inimigo getArmadilha(String direcao) throws Exception{
		return conexoes[getDirecaoIndex(direcao)].getArmadilha();
	}
	
	
	public int getDirecaoIndex(String direcao) throws Exception
	{
		Direcao dir = Direcao.converte(direcao);
		return dir.getIndex();
	}
	
	public void setArmadilha(String direcao) throws Exception
	{
		conexoes[getDirecaoIndex(direcao)].setArmadilha();
	}
			
	public void addConexao(String direcao, int sala) throws Exception
	{
		int ndx = getDirecaoIndex(direcao);
		if (conexoes[ndx] != null)
			throw new Exception("Conexao ja existente.");
		
		conexoes[ndx] = new Conexao(sala);
	}
		
	
	public String toString()
	{	
		try {
			String res =  "A sala tem saida nas direcoes:\n";
			
			for (int i = 0; i < 6; i++)
				if (conexoes[i] != null)
				{
					res += "\t" + Direcao.converte(i) + " " + conexoes[i] + "\n";
				}
			
			res += "\n\t" +getChave()+ "\n\n";
			return res;
		} catch (Exception e) {
			return "SALA COM ESTADO INVALIDO!";
		}
		
	}
	
	public void setChave(Cor cor){
		this.chave = new Chave(cor);
	}
	
	public Chave getChave(){
		return chave;
	}
	
	public Inimigo getInimigo(String direcao) throws Exception{
		return conexoes[getDirecaoIndex(direcao)].getInimigo();
	}
	
	
	
	
	
}
