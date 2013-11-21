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
	private static final int TOTALCHAVEVEMEOLHA = 6;
	
	private Sala[] salas;
	private int countSalas;
	private int salaAtual;
	protected Jogador jogador;
	
	
	
		
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
		/*
		* APOS INICIALIZAR AS 31 SALAS
		* ADICIONAR AS 24 CHAVES, ENTRE ELAS 6 CORES DE CADA, DISTRIBUIDAS PELAS 31 SALAS
		* ONDE 24 PORTAS TERAO CHAVES E 7 NENHUMA OU UMA SALA PODE TER MAIS DE UMA CHAVE? 
		* SERA REALIZADO UM SORTEIO ENTRE 1 E 31(NUMERO MAX DE SALAS)
		* INSERIR EM CADA SALA[INDICESORTEADO] O NR DE CHAVES DE CADA COR
		* TER UM TOTAL DE CADA CHAVE - UM VALOR ESTATICO
		* CRIAR UM METODO QUE ADICIONA CHAVES RECEBENDO COMO PARAMENTO A COR DA CHAVE E O NR DE CHAVES
		* METODO ADICIONACHAVE(COR cor, TOTALCHAVEcor)
		* DENTRO DESTE METODO EH REALIZADO 6 SORTEIOS DE INDICES QUE RECEBERAM A CHAVE DA COR
		* POREM O SORTEIO DE INDICES PODE REPETIR E A MESMA SALA RECEBER A MESMA OU UMA NOVA COR
		* POSSIVELMENTE NAO TERA EXATAMENTE 24 CHAVES ESPALHADAS PELO LABIRINTO
		* 
		* O MESMO SE FAZ PARA OS INIMIGOS QUE ESTAO NA CONEXAO 
		*/
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
		//salaAtual eh a de nr 0
		//se o jogador nao possuir nenhuma chave da mesma cor de nenhuma porta das direcoes
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
			 *descobrir o nr da sala, se eh possivelAtravesar, salaAtual passar ser este nr
			 */
			
			//trocaSala(direcao);
			direcao = leitura.next();
			move(direcao);
		}
		
		if ( cmd.equals("olhar") )
		{	
			//leitura do "proximo"
			//mostra armadilha no nr da sala na direcao indicada
			System.out.println("olhar");
			direcao = leitura.next();
			salas[salaAtual].conexoes[salas[salaAtual].getDirecaoIndex(direcao)].getArmadilha();
		}
		
		if( cmd.equals("atacar") )
		{
			//Combate()
			System.out.println("atacar");
			direcao = leitura.next();
			combate(direcao);
		}
		
		if( cmd.equals("pegar") )
		{
			//leitura do "proximo"
			//verificar o item que o jogador deseja pegar
			
			//jogador.get....
		
			System.out.println("pegar");
		}
		
		if( cmd.equals("largar") )
		{
			//ler o "proximo" do scanner que sera o item
			//chave
			//arma
			//armadura		
			//jogador.setITEM(null);
		}
		
	 
	public void move(String direcao) throws Exception{
		if( salas[salaAtual].conexoes[salas[salaAtual].getDirecaoIndex(direcao)].canAtravessar(jogador))
			salaAtual = salas[salaAtual].conexoes[salas[salaAtual].getDirecaoIndex(direcao)].getSala();
		else
			System.out.println("Voce não possui a chave que abre esta porta!");
	
	}
	
	public void combate(String direcao) throws Exception{
		int nrSorteado = Range.getPercentual();
		if ( nrSorteado<=80 )
			salas[salaAtual].conexoes[salas[salaAtual].getDirecaoIndex(direcao)].inimigo.setDano(jogador.getAtaque());
		if ( salas[salaAtual].conexoes[salas[salaAtual].getDirecaoIndex(direcao)].inimigo.isAlive() )
		{
			nrSorteado = Range.getPercentual();
			if ( nrSorteado<=60)
				jogador.setDano(salas[salaAtual].conexoes[salas[salaAtual].getDirecaoIndex(direcao)].inimigo.ataque);	
		}
		
		
		
		
	}
	
	public static void main(String[] args)
	{
		(new Labirinto()).run();
	}
	
}
