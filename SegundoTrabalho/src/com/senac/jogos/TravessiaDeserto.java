package com.senac.jogos;

public class TravessiaDeserto {

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
	
	
	public int getFuel(){
		return fuel;
	}
	
	public int getPos(){
		return pos;
	}
	
	public int getPosMap(int pos){
		return map[pos];
	}
	
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

	public String getEndMessage() {
		if (isWinner())
			return "Voce GANHOU!";
		else
			return "Voce PERDEU.";
	}

	public boolean isGameOver() {
		if (isWinner())
			return true;
		if (fuel == 0 && map[pos] == 0)
			return true;
		return false;
	}

	public boolean isWinner() {
		return pos == MAP_SIZE;
	}

	public void initGame() {
		pos = 0;
		fuel = MAX_FUEL;
		map = new int[MAP_SIZE];
	}

	public void printStatus() {
		System.out.println(String.format("Voce esta na posicao %d.", pos));
		System.out.println(String.format("Voce possui %d unidades de combustivel.",fuel));
		if (pos > 0)
			System.out.println(String.format("Existem %d unidades de combustivel nessa posicao.", map[pos]));
	}

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

	public void ajuda() {
		System.out.println("Comandos: avancar voltar carregar descarregar ajuda");
	}

	public void descarregar() {
		if (fuel > 0) {
			fuel--;
			map[pos]++;
		}
	}

	public void carregar() {
		if (map[pos] > 0) {
			map[pos]--;
			fuel++;
		}
	}

	public void voltar() {
		if (fuel > 0 && pos > 0) {
			fuel--;
			pos--;
		}
		if (pos == 0)
			fuel = MAX_FUEL;
	}

	public void avancar() {
		if (fuel > 0) {
			fuel--;
			pos++;
		}
	}

}
