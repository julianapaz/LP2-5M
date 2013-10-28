package com.senac.algoritmos;

import java.util.Scanner;
import static java.lang.System.*;
import com.senac.estruturas.PilhaCheia;
import com.senac.estruturas.PilhaOperador;
import com.senac.estruturas.PilhaOperando;
import com.senac.estruturas.PilhaVazia;

public class AvaliadorRPN {
	public static double avalia(String expressao) throws PilhaCheia,
	PilhaVazia, InvalidOperator {
		PilhaOperando pilha = new PilhaOperando(50);

		Scanner sc = new Scanner(expressao);

		while (sc.hasNext())
		{
			if (sc.hasNextInt())
			{
				pilha.push(sc.nextInt());
			}
			
			else 
			{
				String op = sc.next();
				double rhs = pilha.pop();
				double lhs = pilha.pop();
				pilha.push(executaOperador(op.charAt(0), lhs, rhs));
			}
		}

		return pilha.pop();
	}

	public static double inversorPosFixo(String expressao)throws PilhaCheia, PilhaVazia, InvalidOperator{
		Scanner entrada= new Scanner(expressao);

		PilhaOperador pilha = new PilhaOperador(50);
		String saida = "";

		while (entrada.hasNext())
		{

			if (entrada.hasNextInt())
			{
				saida +=" " + entrada.next();
			}

			else 
			{
				String opLido = entrada.next();

				if ( ehFechaParentes(opLido) )
				{
					while ( !ehAbreParenteses(pilha.peek()) )
					{

						saida += " " + pilha.pop();

					}
					
					pilha.pop();
				}

				else 
				{				
					if ( pilha.isEmpty() )
					{		
						pilha.push(opLido);
					}

					else if ( prioridade(opLido)>prioridade(pilha.peek()) )
					{
						pilha.push(opLido);
					}

					else				
					{
						while ( !pilha.isEmpty() && prioridade(opLido)<prioridade(pilha.peek()) && !ehAbreParenteses(pilha.peek()) )
						{
							saida +=" "+pilha.pop();
						}

						pilha.push(opLido);
					}
				}
			}
		}
		while ( !pilha.isEmpty() )
		{
			saida += " "+pilha.pop();
		}	

		return avalia(saida);
	}

	public static boolean ehAbreParenteses(String op) {
		return op.equals("(");
	}

	public static boolean ehFechaParentes(String op) {
		return op.equals(")");

	}

	public static int prioridade(String operador) throws InvalidOperator {

		switch (operador.charAt(0)) {
		case '(':
			return 3;

		case '*':
			return 2;
			
		case '/':
			return 2;
			
		case '+':
			return 1;
			
		case '-':
			return 1;
			
		default:
			throw new InvalidOperator(operador.charAt(0));
		}

	}

	private static double executaOperador(char op, double lhs, double rhs)
			throws InvalidOperator {
		switch (op) {
		case '+':
			return lhs + rhs;
		case '-':
			return lhs - rhs;
		case '*':
			return lhs * rhs;
		case '/':
			return lhs / rhs;
		default:
			throw new InvalidOperator(op);
		}
	}
}
