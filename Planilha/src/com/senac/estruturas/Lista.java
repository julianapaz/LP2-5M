package com.senac.estruturas;

public class Lista <T>{
private Nodo head;
	
	public Lista(){
		this.head=null;
	}	
		
	public void insere(T coluna, T linha, T formula){
		Nodo nodolinha = new Nodo ( linha, formula);
		if (head==null)
			head = new Nodo(coluna, nodolinha);
		
		else
		{
			Nodo nodoColunaAtual = head;
			Nodo nodoColunaAnterior = head.getprox();
		
			while ( nodoColunaAnterior.getprox()!=null )
			{
				if ( coluna == nodoColunaAtual.getChave() ) // a coluna ja existe
				{
					Object nodo = nodoColunaAtual.getDado();
									
					Nodo headLinha = ((Nodo)nodo);

					Nodo nodoLinhaAtual = headLinha;
					Nodo nodoLinhaAnterior = headLinha;
				
					//percorre a linha
					while(nodoLinhaAnterior.getprox()!=null)
					{
						//a linha é a linha digitada, insere a formula
						if( linha == nodoLinhaAtual.getChave() )
						{
							//usar metodo da classe Prof, que recupera resultados e formulas
							nodoLinhaAtual.setDado(formula);
							break;
						}
							
						nodoLinhaAnterior = nodoLinhaAtual;
						nodoLinhaAtual = nodoLinhaAnterior.getprox();
					}
					//a linha ainda n existe, adiciona a linha
					nodoLinhaAtual.setprox(nodolinha);
				}
			}
			//a coluna ainda n existe, insere no final
			nodoColunaAtual.setprox(new Nodo(coluna, nodolinha));
		}
		
	}


	public String toString(){

		Nodo n = head;
		String res="Lista:\n"+n;
		while(n.getprox()!=null){
			res += n.getDado()+"\n";
			n=n.getprox();
		}


		return res;
	}

}
