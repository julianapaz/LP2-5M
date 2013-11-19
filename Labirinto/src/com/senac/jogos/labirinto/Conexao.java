package com.senac.jogos.labirinto;

public class Conexao {
	private int sala;
	private Inimigo inimigo;
	private Cor cor;
	private Arma arma;
	private Chave chave;
	private Armadura armaduraCouro; 
	private Armadura armaduraMetal;
	private Armadura armaduraMithrill;
		
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
		
		sorteia = Range.getPercentual();
		if ( sorteia<=55 )
			this.inimigo = new Inimigo(TipoInimigo.GOBLIN);
		else if ( sorteia>55 && sorteia<=85 )
			this.inimigo = new Inimigo(TipoInimigo.ORC);
		else
			this.inimigo = new Inimigo(TipoInimigo.TROLL);
		
		sorteia = Range.getPercentual();
		
		if ( sorteia<=61 )
			this.arma = new Arma("Adaga", 1);
		else if ( sorteia>61 && sorteia<=92 )
			this.arma = new Arma("Faca", 2);
		else
			this.arma = new Arma("Espada", 4);
		
		sorteia = Range.getPercentual();
		
		if( sorteia<=25 )
			chave = new Chave(cor.AMARELO);
		else if  (sorteia>25 && sorteia<=50 )
			chave = new Chave(cor.AZUL);
		else if ( sorteia>50 && sorteia<=75 )
			chave = new Chave(cor.VERDE);
		else
			chave = new Chave(cor.VERMELHO);
		
		armaduraCouro = new Armadura("Couro", 1);
		armaduraMetal = new Armadura("Metal", 2);
		armaduraMithrill = new Armadura("Mithrill", 3);
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
