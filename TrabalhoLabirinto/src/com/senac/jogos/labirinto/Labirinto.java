package com.senac.jogos.labirinto;

import static java.lang.System.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;

public class Labirinto {

	private static final Scanner teclado = new Scanner(System.in);
	private static final int TOTALCHAVEAMARELA = 6;
	private static final int TOTALCHAVEAZUL = 6;
	private static final int TOTALCHAVEVERDE = 6;
	private static final int TOTALCHAVEVERMELHO = 6;
	private static final int TOTALADAGA = 8;

	private Sala[] salas;
	private int countSalas;
	private int salaAtual;
	private Jogador jogador;

	private void run() throws Exception
	{
		inicializaLabirinto();

		for (Sala s: salas) {
			if (s == null) break;
			out.println(s);
		}


		//implementar
		while ( ! isGameOver() ) {

			//implementar
			exibeStatus();

			//implementar
			out.println("Digite o comando:");
			executaComando(teclado.nextLine());
			
		}

	}

	private void inicializaLabirinto()
	{
		jogador = new Jogador();
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
		addChave(Cor.AMARELO,TOTALCHAVEAMARELA);
		addChave(Cor.AZUL,TOTALCHAVEAZUL);
		addChave(Cor.VERDE,TOTALCHAVEVERDE);
		addChave(Cor.VERMELHO,TOTALCHAVEVERMELHO);

		//adicionar ARMAS
		//adicionar INIMIGOS
		//sorteiar um nr de 1 a 31 e outro nr de 1 a 6
	}

	public void addChave(Cor cor, int total){
		//Range r = new Range(1,31);
		Random r = new Random();
		for	( int i=0; i<total; i++	)		
			//salas[r.getRandom()].setChave(cor);
			salas[1+r.nextInt(30)].setChave(cor);	
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

	public static void main(String[] args) throws Exception
	{
		(new Labirinto()).run();
	}

	public boolean isGameOver(){
		//jogador sem vida
		return ( !jogador.isAlive() && salaAtual!=0 ); 
		//jogador chegou no fim do labirinto/ganhou
		//quando o jogador nao tem a chave e nem no labirinto da mesma cor
	}

	public void exibeStatus()
	{
		out.print("Sala "+salaAtual);
		out.print("\n"+salas[salaAtual]);

	}

	public void executaComando(String entrada) throws Exception
	{
		Scanner leitura = new Scanner(entrada);
		String direcao;
		String comando = leitura.next();
		if ( comando.equals("mover") )
		{
			direcao = leitura.next();
			move(direcao);
		}

		if (comando.equals("olhar")){
			direcao = leitura.next();
			//aqui
			//System.out.println(salas[salaAtual].conexoes[salas[salaAtual].getDirecaoIndex(direcao)].getArmadilha());
			System.out.println(salas[salaAtual].getArmadilha(direcao));
		}

		if (comando.equals("atacar")){
			direcao = leitura.next();
			combate(direcao);
		}
	}

	public void move(String direcao) throws Exception{
		if ( salas[salaAtual].conexoes[salas[salaAtual].getDirecaoIndex(direcao)].canAtravessar(jogador))
			salaAtual = salas[salaAtual].conexoes[salas[salaAtual].getDirecaoIndex(direcao)].getSala();
		else
			System.out.println("Voce não possui a chave que abre esta porta!");

	}

	public void combate(String direcao) throws Exception{
		int nrSorteado = Range.getPercentual();
		
		/*
		 * No caso de combate entre o jogador e um inimigo ("monstro"), o jogador atacará antes do inimigo. 
		 * O inimigo irá revidar apenas se estiver vivo após o ataque do jogador. 
		 * Existe 80% de chance do jogador conseguir acertar um golpe no inimigo. 
		 * Existe 60% de chance do inimigo acertar um golpe no jogador.
		 * Quando for desarmar uma armadilha, o jogador tem 80% de chance de desarmar uma armadilha
		 */
		if ( nrSorteado<=80 ){		
		
			salas[salaAtual].conexoes[salas[salaAtual].getDirecaoIndex(direcao)].inimigo.setDano(jogador.getAtaque());
			System.out.println("Inimigo foi atacado");
			
		}
		//if ( salas[salaAtual].conexoes[salas[salaAtual].getDirecaoIndex(direcao)].inimigo.isAlive() )
		if(salas[salaAtual].getInimigo(direcao).isAlive())
		{
			nrSorteado = Range.getPercentual();
			if ( nrSorteado<=60)
				//aqui
				jogador.setDano(salas[salaAtual].conexoes[salas[salaAtual].getDirecaoIndex(direcao)].inimigo.ataque);
		}
		else
		{			
			//aqui
			salas[salaAtual].conexoes[salas[salaAtual].getDirecaoIndex(direcao)].setInimigo(null);
			System.out.println("O inimigo foi derrotado!");
		}
	}
}

