package com.senac.jogos.labirinto;

public class Conexao {
	private int sala;
	protected Inimigo inimigo;
	private Cor cor;
	
		
	public Conexao(int sala)
	{
		this.sala = sala;
		//this.cor = Cor.MARROM;//sorteia
		
		//fica ou coloca em outro metodo?
		
		int sorteia = Range.getPercentual();
		
		if ( sorteia<=60)
			this.cor = Cor.MARROM;
		else if ( sorteia<=70 )
			this.cor = Cor.AMARELO;
		else if ( sorteia<=80 )
			this.cor = Cor.AZUL;
		else if ( sorteia<=90 )
			this.cor = Cor.VERDE;
		else
			this.cor = Cor.VERMELHO;
	}	
	
	public int getSala() {
		return sala;
	}

	public void setSala(int sala) {
		this.sala = sala;
	}

	public Inimigo getInimigo() {
		return inimigo;
	}

	public void setInimigo(Inimigo inimigo) {
		this.inimigo = inimigo;
	}
	
	public void setArmadilha() {
		this.inimigo = new Armadilha();
	}
	
	public Arma getArma(){
		return arma;
	}
	
	public Cor getCor(){
		 return cor;
	}
	
	public Chave getChave(){
		return chave;
	}
	 
	 
	
	public void setCor(Cor cor) {
		if (cor != null)
			this.cor = cor;
	}
	
	public boolean canAtravessar(Jogador jogador)
	{
		if (cor != Cor.MARROM) {
			Chave c = jogador.getChave();
			if (c != null)
				return c.getCor() == cor;
			else
				return false;
		}
		
		if (inimigo != null) {
			if (! (inimigo instanceof Armadilha))
				return !inimigo.isAlive();
		}
		
		return true;
	}

	public String toString()
	{
		String res = "Porta " + cor;
		if (inimigo != null && !(inimigo instanceof Armadilha))
			res += " guardada por um " + inimigo;
		return res;
	}
}
