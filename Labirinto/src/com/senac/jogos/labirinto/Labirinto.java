package com.senac.jogos.labirinto;

import static java.lang.System.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;

public class Labirinto {
	
	private static final Scanner teclado = new Scanner(System.in);
	
	private Sala[] salas;
	private int countSalas;
	private int salaAtual;
	
	
	//armadilha?????
		
	private void run()
	{
		inicializaLabirinto();
		
		for (Sala s: salas) {
			if (s == null) break;
		//out.println(s);
		}
		
		
		//implementar
		while ( ! isGameOver() ) 
		{
		 
			//implementar
			exibeStatus();
			
			//implementar
			executaComando ( teclado.next() );
							
		}
		
	}
	
	private void inicializaLabirinto()
	{
		salas = new Sala[50];
		salas[0] = new Sala();
		countSalas = 1;
		try {
			leLabirinto( new Scanner( new FileInputStream("labirinto.txt") ) );
		} catch (Exception e) {
			err.println(e.getMessage());
			exit(1);
		}
		Random r = new Random();
		salaAtual = 1+r.nextInt(30);
	}
	
	private void leLabirinto( Scanner arquivo ) throws Exception
	{
		String cmd = arquivo.next().toLowerCase();
		while (cmd.equals("room")) {
			int salaId = arquivo.nextInt();
			salas[salaId] = new Sala();
			Sala sala = salas[salaId];
			
			String direcao = arquivo.next();

			do {
				if (arquivo.hasNextInt()) {
					salaId = arquivo.nextInt();
				} else if (arquivo.next().equalsIgnoreCase("EXIT")) {
					salaId = 0;
				} else break;
			
				sala.addConexao(direcao, salaId);
			
				if (!arquivo.hasNext())
					return;
				cmd = arquivo.next().toLowerCase();	
				if (cmd.equals("trap")) {
					sala.setArmadilha(direcao);
					if (!arquivo.hasNext())
						return;
					cmd = arquivo.next();
				}
				direcao = cmd;
			} while (!cmd.equals("room"));
		}
		throw new Exception("Arquivo de descricao do labirinto invalido.");
	}

	
	
	public boolean isGameOver()
	{
		//jogador sem vida
		//salaAtual é a de nr 0		
		return false;
	}
	
	public void exibeStatus()
	{
		out.print("Sala "+salaAtual);
		out.print("\n"+salas[salaAtual]);	
	}
		
	public void executaComando( String entrada ){
		Scanner leitura = new Scanner(entrada);
		String cmd = leitura.next();
		if ( cmd.equals("mover") )
		{
			
			System.out.println("mover");
			
			/*ler o "proximo" - direcao
			 *chama o metodo que troca de sala enviando como parametro a direcao
			 *Metodo trocaSala: troca de sala para a direcao indicada
			 *descobrir o nr da sala, salaAtual passará a ser este nr
			 */
			
			//trocaSala(direcao);
		}
		
		if ( cmd.equals("olhar") )
		{
			//mostra armadilha no nr da sala na direcao indicada
			System.out.println("olhar");
		}
		
		if( cmd.equals("atacar") )
		{
			//Combate(jogador,inimigo)
			System.out.println("atacar");
		}
		
		if( cmd.equals("pegar") )
		{
			//jogador.get....
		
			System.out.println("pegar");
		}
		
		if( cmd.equals("largar") )
		{
			//chave
			//arma
			//armadura		
		}
		
	}
	
	public void trocaSala(String direcao){
		/*
		 * metodo que recebe como paramentro a direcao da proxima sala
		 * percorrer o arquivo/mapa ate encontrar o nr da salaAtual
		 * 		percorrer a linha ate encontrar a direcao
		 * 			encontrar o nr da sala
		 * 	salaAtual sera o nr da sala da direcao informada
		 * 
		 */
		
	}
	
	/*
	 * combate (jogador,inimigo)
	 *
	 *	enquanto(jogador.isAlive e inimigo.isAlive)
	 *		sorteia = Range.getPercentual();
	 *		se (
	 *}
	 */
	
	public static void main(String[] args)
	{
		(new Labirinto()).run();
	}
	
}
