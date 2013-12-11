package com.senac.planilha;

import static java.lang.System.out;

import java.util.Scanner;

import com.senac.algoritmos.InvalidOperator;
import com.senac.algoritmos.InvalidToken;
import com.senac.estruturas.Lista;
import com.senac.estruturas.Nodo;

public class Planilha {

	/**
	 * @param args
	 * @throws InvalidOperator 
	 * @throws InvalidToken 
	 */
	public static void main(String[] args)
			throws InvalidOperator, InvalidToken
	{
		Scanner sc = new Scanner(System.in);
		Lista lista = new Lista();
		String linha=null, coluna=null, formula=null, entrada="";
		
		
		int i;
		
		while (sc.hasNextLine())
		{
			
				
			System.out.println("Digite a celula:"); 
			entrada = sc.next();
			if ( entrada.equalsIgnoreCase("fim"))
				break;
				
			for(i=0; i<entrada.length(); i++)
			{
				if ( Character.isDigit (entrada.charAt(i) ) )
					linha += entrada.charAt(i);
				else
					coluna += entrada.charAt(i);
			}
			System.out.println("Digite a formula: ");
				formula = sc.next();
			
		}
		
		lista.insere(coluna, linha, formula);
		
	}
	
}
