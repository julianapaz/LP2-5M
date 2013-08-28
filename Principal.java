import java.util.Scanner;
public class Principal {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TravessiaDoDeserto travessia = new TravessiaDoDeserto();

		Scanner entrada = new Scanner(System.in);
		String opcao;

		while(!travessia.jogoAcabou()){

			System.out.println(travessia+"\nDIGITE O COMANDO:");

			if(travessia.podeAvancar())
				System.out.println("A - AVANCAR");
			if(travessia.podeRetroceder())
				System.out.println("R - RETROCEDER");
			if(travessia.podeCarregar())
				System.out.println("C - CARREGAR");
			if(travessia.podeDescarregar())
				System.out.println("D - DESCARREGAR");
			System.out.println("S - SAIR");
			opcao = entrada.next().toUpperCase();

			if(opcao.equals("AVANCAR") || opcao.equals("A"))
				travessia.avancar();
			else if(opcao.equals("RETROCEDER") || opcao.equals("R"))
				travessia.retroceder();
			else if(opcao.equals("DESCARREGAR") || opcao.equals("D"))
				travessia.descarregar();
			else if(opcao.equals("CARREGAR") || opcao.equals("C"))
				travessia.carregar();
			else if(opcao.equals("SAIR") || opcao.equals("S")){
				System.out.println("Voce saiu do jogo!");
				System.exit(0);
			}

			else
				System.out.println("OPÇÃO INVALIDA!");			
		}
		System.out.println(travessia);
		System.out.println("Fim do Jogo!");
		if(travessia.jogadorGanhou())
			System.out.println("Voce Ganhou!");
		else
			System.out.println("Voce Perdeu!");
		entrada.close();

	}

}
