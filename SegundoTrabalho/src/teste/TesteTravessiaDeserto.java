package teste;
import static java.lang.System.*;
import static org.junit.Assert.*;

import java.io.ObjectInputStream.GetField;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


import com.senac.jogos.TravessiaDeserto;

public class TesteTravessiaDeserto {
	
	private TravessiaDeserto jogo = null; 
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		jogo = new TravessiaDeserto();
	}

	@After
	public void tearDown() throws Exception {
		jogo = null;
	}
	
	@Test 
	public void testInitGame(){
		System.out.println("TESTE INITGAME");
		jogo.initGame();
			
		assertEquals("Testando combustivel", jogo.MAX_FUEL,jogo.getFuel(),0);
		assertEquals("Testando tamanho do mapa", jogo.MAP_SIZE, jogo.getMapa().length);	
		
		for(int i=0;i<jogo.MAP_SIZE;i++)
			assertEquals("Testando carga inicial em cada posicao do mapa!", 0, jogo.getPosMap(i));
	}
	
	@Test
	public void testTranslateCommand(){
		System.out.println("TESTE TRANSLATECOMMAND");
		jogo.initGame();
		String cmd = "avancar";
		assertEquals("Testando retorno TRANSLATECOMMAND - AVANCAR",jogo.AVANCAR,jogo.translateCommand(cmd),0);
		
		cmd = "voltar";
		assertEquals("Testando retorno TRANSLATECOMMAND - VOLTAR",jogo.VOLTAR,jogo.translateCommand(cmd),0);
		
		cmd = "descarregar";
		assertEquals("Testando retorno TRANSLATECOMMAND - DESCARREGAR",jogo.DESCARREGAR,jogo.translateCommand(cmd),0);
		
		cmd = "carregar";
		assertEquals("Testando retorno TRANSLATECOMMAND - CARREGAR", jogo.CARREGAR, jogo.translateCommand(cmd));
		
		cmd = "ajuda";
		assertEquals("Testando retorno TRANSLATECOMMAND - AJUDA", jogo.AJUDA, jogo.translateCommand(cmd));
		
		cmd = "erro";
		assertEquals("Testando retorno TRANSLATECOMMAND - ERRO", jogo.ERROR, jogo.translateCommand(cmd));
	}
	@Test	
	public void testCarregar(){
		System.out.println("TESTE CARREGAR");
		//inicia o jogo
		jogo.initGame();
		
		//posicao 0 - pode carregar
		assertTrue(jogo.getPos()==0);//posicao == 0
		jogo.carregar();// carrega na posicao 0 
		assertTrue(jogo.getFuel()==jogo.MAX_FUEL);
		
		
		jogo.avancar();
		//posicao 1 map[1]
		//System.out.println("Avanca posicao "+jogo.getPos());
		//System.out.println("Combustivel: "+jogo.getFuel());
		
		//verifica se tem combustivel armazenado na posicao atual
		assertFalse(jogo.getPosMap(jogo.getPos())>0);
		
		//recebe o combustivel acumulado na posicao 1 do mapa, que é zero		
		int combPosMapa = jogo.getPosMap(jogo.getPos());
		
		//executa o carregar
		jogo.carregar();
		
		//verifica se carregou, combustivel acumulado na posicao 1 tem que ser zero
		assertFalse(jogo.getPosMap(jogo.getPos())==combPosMapa+1);
		
				
		//vai p posicao 0
		jogo.voltar();
		
		//vai p posicao 1
		jogo.avancar();
		
		combPosMapa = jogo.getPosMap(jogo.getPos());
		//System.out.println(combPosMapa);
		//combustivel atual
		int combustivel = jogo.getFuel();
		//descarrega uma unidade na posicao 1
		jogo.descarregar();
		jogo.descarregar();
		//System.out.println(jogo.getPosMap(jogo.getPos()));
		//verifica se combustivel é menor que o maximo de combustivel
		assertTrue(jogo.getFuel()<jogo.MAX_FUEL);
		//verifica se na posicao 1 do mapa tem combustivel
		assertTrue(jogo.getPosMap(jogo.getPos())>0);
		//executa o carregar
		jogo.carregar();
		
		
		assertTrue(jogo.getFuel()==combustivel-1);
		//verifica se a quantidade de combustivel acumulado na posicao 1 voltou ao estado anterior.	
		assertTrue(jogo.getPosMap(jogo.getPos())==combPosMapa+1);	
		
	}
	
	public void testProcessCommand(){
		/*testInitGame();
		String cmd = "avancar";
		jogo.processCommand(jogo.translateCommand(cmd));*/
	}

	@Test
	public void testAvancar() {
		System.out.println("TESTE AVANCAR");
		
		jogo.initGame();
		int combustivel = jogo.getFuel();
		int posicao = jogo.getPos();
		assertTrue(jogo.getFuel()>0);
		
		jogo.avancar();
		assertEquals("Testando decremento de combustivel.",combustivel - 1, jogo.getFuel() ,0);
		assertEquals("Incremento de posicao", posicao+1, jogo.getPos());
		
		
		for(int i=0;i<combustivel;i++)
			jogo.avancar();
		
		posicao = jogo.getPos();
		jogo.avancar();
		jogo.avancar();
		assertTrue(jogo.getFuel()==0);
		assertTrue(posicao==jogo.getPos());
		
	
	}
	@Test 
	public void testVoltar(){
		System.out.println("TESTE VOLTAR");
		jogo.initGame();
		jogo.avancar();
		jogo.avancar();
		assertTrue(jogo.getPos()>0);
		assertTrue(jogo.getFuel()>0);
		
		int posicao = jogo.getPos();
		int combustivel = jogo.getFuel();
		jogo.voltar();
		
		assertTrue(combustivel-1==jogo.getFuel());
		assertTrue(posicao-1==jogo.getPos());
		
				
		jogo.voltar();
		jogo.voltar();
		
		assertTrue(jogo.getPos()==0);
		assertTrue(jogo.getFuel()==jogo.MAX_FUEL);
	
		
	}
	
	@Test
	public void testDescarregar(){
		System.out.println("TESTE DESCARREGAR");
		jogo.initGame();
		
		assertTrue(jogo.getFuel()>0);
		
		int combustivel = jogo.getFuel();
		int combPosMapa = jogo.getPosMap(jogo.getPos());
		
		jogo.descarregar();
		
		assertTrue(jogo.getFuel()==combustivel-1);
		assertTrue(jogo.getPosMap(jogo.getPos())==combPosMapa+1);
		
		combustivel = jogo.getFuel();
		
		while(jogo.getFuel()>0)
			jogo.descarregar();
		
		assertTrue(jogo.getFuel()==0);			
		jogo.descarregar();
		assertTrue(jogo.getFuel()==0);
		
		
	}

	@Test
	public void testIsGameOver(){
		jogo.initGame();
		// perdendo o jogo.
		while (jogo.getFuel() > 0)
			jogo.avancar();
		assertTrue(jogo.isGameOver());
		
		// ganhando o jogo
		jogo.initGame();
		ganharJogo();
		assertTrue(jogo.isGameOver());
	
	}

	private void avancaDuplo()
	{
		jogo.avancar();	
		jogo.avancar();
	}
	
	private void descarregaDuplo(){
		jogo.descarregar();
		jogo.descarregar();
	}
	private void voltaDuplo(){
		jogo.voltar();
		jogo.voltar();
	}
	
	private void carregaDuplo(){
		jogo.carregar();
		jogo.carregar();
	}
	
	private void avancaDescarregaVolta()
	{
		avancaDuplo();
		descarregaDuplo();
		voltaDuplo();
	}
	
	private void avancaDuploCarregaDuplo(){
		avancaDuplo();
		carregaDuplo();
	}
	
	private void ganharJogo() {
		
		//aaddvv
		avancaDescarregaVolta();
		//aaddvv
		avancaDescarregaVolta();
		//aaddvv
		avancaDescarregaVolta();
		
		//aaddvv
		avancaDescarregaVolta();
				
		
		///aacc
		avancaDuploCarregaDuplo();
		
		//aaddvv
		avancaDescarregaVolta();
		//cccccc
		carregaDuplo();
		carregaDuplo();
		carregaDuplo();
		
		//aacc
		avancaDuploCarregaDuplo();
		
		//aaaa
		avancaDuplo();
		avancaDuplo();
		
		//aa
		avancaDuplo();
		
		
	}
	
	
	@Test
	public void testIsWinner(){
		jogo.initGame();
		ganharJogo();
		assertTrue(jogo.isWinner());
	}
	
}
