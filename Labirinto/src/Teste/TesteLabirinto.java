package Teste;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.senac.jogos.labirinto.Conexao;

public class TesteLabirinto {
	Conexao conex;


	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		conex = new Conexao(1);
	}

	@After
	public void tearDown() throws Exception {
		conex = null;
	}

	@Test
	public void testConexao() {
		//fail("Not yet implemented");
		System.out.println(conex.getArma());
		System.out.println(conex.getInimigo());
		System.out.println(conex.getCor());
		System.out.println(conex.getChave());
	}

}
