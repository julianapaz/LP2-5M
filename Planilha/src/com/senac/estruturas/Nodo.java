package com.senac.estruturas;

public class Nodo <T> {
	protected  T dado;
	private Nodo prox;
	private T chave;
	
	
	
	public T getChave() {
		return chave;
	}

	public void setChave(T chave) {
		this.chave = chave;
	}

	public Nodo(T chave, T dado){
		this.dado = dado;
		this.chave = chave;
		prox=null;
	}
	
	public void setDado(T dado){
		this.dado = dado;
	}
	
	public T getDado(){
		return dado;
	}	
	
	public Nodo getprox(){
		return prox;
	}
	
	public boolean hasNodo(){
		return prox!=null;
	}
	
	
	public void setprox(Nodo n){
		this.prox=n;
	}
	
	public String toString(){
		//String res=String.format("\nChave: %d\n",chave+"\nDado: ");
		String res = dado+"\n"; 
		return res;
	}

}

