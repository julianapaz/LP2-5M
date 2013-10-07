package com.senac.jogos;

public class TravessiaDeserto {
/**
 * Variaveis contantes
 */
	
	public static final int MAP_SIZE = 10;
	public static final int MAX_FUEL = 6;

	public static final
		java.util.Scanner sc = new java.util.Scanner(System.in);
	
	public static final int AVANCAR = 0;
	public static final int VOLTAR = 1;
	public static final int CARREGAR = 2;
	public static final int DESCARREGAR = 3;
	public static final int AJUDA = 4;
	public static final int ERROR = -1;
	
	private int[] map;
	private int fuel;
	private int pos;
	
	
	/**
	 * Executa metodo que retorna a quantidade de combustivel
	 */
	public int getFuel(){
		return fuel;
	}
	
	/**
	 * Executa metodo que retorna posicao 
	 * 
	 */
	public int getPos(){
		return pos;
	}
	
	
	/**
	 * Metodo que retorn o valor armazenado na posicao do mapa
	 * @param pos
	 * @return 
	 */
	public int getPosMap(int pos){
		return map[pos];
	}
	
	/**
	 * Metodo que retorna o mapa
	 * @return
	 */
	public int[] getMapa(){
		return map;
	}
	
	public static void main(String[] args) {
		(new TravessiaDeserto()).run();
	}

	public void run() {
		initGame();
		do {
			printStatus();
			int cmd = translateCommand( sc.next() );
			processCommand( cmd );
		} while (!isGameOver());
		
		System.out.println(getEndMessage());
	}

	/**
	 * Exibe mensagem no fim do jogo
	 * @return
	 */
	public String getEndMessage() {
		if (isWinner())
			return "Voce GANHOU!";
		else
			return "Voce PERDEU.";
	}

	/**
	 * Executa o metodo que testa se o jogo acabou
	 */
	public boolean isGameOver() {
		if (isWinner())
			return true;
		if (fuel == 0 && map[pos] == 0)
			return true;
		return false;
	}

	/**
	 * Metodo que verifica se o jogador ganhou
	 * @return
	 */
	public boolean isWinner() {
		return pos == MAP_SIZE;
	}

	/**
	 * Metodo que incia o jogo
	 */
	public void initGame() {
		pos = 0;
		fuel = MAX_FUEL;
		map = new int[MAP_SIZE];
	}
	/**
	 * Imprime a posicao, combustivel armazenado no mapa e combustivel no caminhao
	 */

	public void printStatus() {
		System.out.println(String.format("Voce esta na posicao %d.", pos));
		System.out.println(String.format("Voce possui %d unidades de combustivel.",fuel));
		if (pos > 0)
			System.out.println(String.format("Existem %d unidades de combustivel nessa posicao.", map[pos]));
	}

	/** 
	 * Metodo que verifica o comando digitado
	 * @param command
	 * @return
	 */
	public int translateCommand(String command) {
		String cmd = command.toLowerCase();
		if (cmd.equals("avancar"))
			return AVANCAR;
		if (cmd.equals("voltar"))
			return VOLTAR;
		if (cmd.equals("carregar"))
			return CARREGAR;
		if (cmd.equals("descarregar"))
			return DESCARREGAR;
		if (cmd.equals("ajuda"))
			return AJUDA;
		return ERROR;
	}

	
	/**
	 * Método que executa um metodo conforme a opcao digitada
	 * @param command
	 */
	public void processCommand(int command) {
		switch (command) {
			case AVANCAR:
				avancar();
				break;
			case VOLTAR:
				voltar();
				break;
			case CARREGAR:
				carregar();
				break;
			case DESCARREGAR:
				descarregar();
				break;
			case AJUDA:
				ajuda();
				break;
			default:
				System.err.println("Comando invalido.");
		}
	}

	/**
	 * Exibe os comandos
	 */
	public void ajuda() {
		System.out.println("Comandos: avancar voltar carregar descarregar ajuda");
	}

	/**
	 * Metodo que descarrega uma unidade de combustivel
	 */
	public void descarregar() {
		if (fuel > 0) {
			fuel--;
			map[pos]++;
		}
	}

	/**
	 * Metodo que carrega uma unidade de combustivel
	 */
	public void carregar() {
		if (map[pos] > 0) {
			map[pos]--;
			fuel++;
		}
	}

	/**
	 * Metodo que volta uma posicao no mapa
	 */
	public void voltar() {
		if (fuel > 0 && pos > 0) {
			fuel--;
			pos--;
		}
		if (pos == 0)
			fuel = MAX_FUEL;
	}

	/**
	 * Metodo que avanca uma posicao no mapa
	 */
	public void avancar() {
		if (fuel > 0) {
			fuel--;
			pos++;
		}
	}

}
