public class TravessiaDoDeserto {
	private int TAMANHO=10;
	private int TANQUE=6;
	private int posicao=0;
	private int combustivel;
	private int[] mapa;


	public TravessiaDoDeserto(){
		mapa = new int[TAMANHO];
		combustivel=TANQUE;
	}

	public boolean podeAvancar(){
		if(combustivel>0 && posicao<TAMANHO-1)
			return true;
		else
			return false;
	}
	public void avancar(){
		if(podeAvancar()){
			posicao++;
			combustivel--;
		}
	}
	public boolean podeRetroceder(){
		if(posicao>0 && combustivel>0)
			return true;
		else
			return false;
	}

	public void retroceder(){
		if(podeRetroceder()){
			posicao--;
			if(posicao==0){			
				combustivel=TANQUE;
			}
			else
				combustivel--;	
		}

	}

	public boolean podeCarregar(){
		if(mapa[posicao]>0 && combustivel<TANQUE)
			return true;
		else
			return false;
	}

	public void carregar(){
		if(podeCarregar()){
			mapa[posicao]--;
			combustivel++;
		}
	}


	public boolean podeDescarregar(){
		if(combustivel>0 && posicao>0)
			return true;
		else
			return false;
	}

	public void descarregar(){
		if(podeDescarregar()){
			mapa[posicao]++;
			combustivel--;
		}
	}


	public boolean jogoAcabou(){
		if((posicao>0 && combustivel==0 && mapa[posicao]==0)
				|| (posicao==TAMANHO-1 && combustivel>=0))
			return true;	

		else
			return false;
	}

	public boolean jogadorGanhou(){
		if(posicao==TAMANHO-1 && combustivel>=0)
			return true;
		else
			return false;
	}

	public String toString(){
		char[] mostraCaminhao = new char[TAMANHO];	
		String saida = "STATUS\n";
		int i;
		mostraCaminhao[posicao]='C';
		for(i=0;i<TAMANHO;i++)
			saida = saida + " "+ mapa[i];
		saida = saida +"\n";
		for(i=0;i<TAMANHO;i++)
			saida = saida + " " + mostraCaminhao[i];

		saida = saida +"\nTanque: "+combustivel+" Posição: "+(posicao+1);
		return saida;
	}

}
