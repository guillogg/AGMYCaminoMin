package Test;

import static org.junit.Assert.fail;

import org.junit.Test;

import Interfaz.AGM;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class TestAGM {

	@Test
	public void tamañoAGM()
	{
		double[][] grafo = {{0,3,2,10,2},{9,3,5,1,8},{5,4,2,3,0},{11,7,2,1,3},{12,7,2,5,2}};
		//double[][] grafo7 = {{0,3,2,10,2,6,9},{9,3,5,1,8,10,3},{5,4,2,3,0,4,21},{11,7,2,1,3,5,9},{12,7,2,5,2,1,1},{11,7,2,1,3,8,3},{12,7,2,5,2,0,2}};
		
		int tamañoMatriz = grafo.length;
		AGM agm = new AGM();
		int tamañoAgm = agm.ConstruirAGM(grafo).size();
		
		assertEquals(tamañoMatriz, tamañoAgm);
		
	 }
	
	
	public void verificarAGM()
	{
		double[][] grafo = {{0,3,2,10,2},
							{9,0,5,1,8},
							{5,4,0,3,0},
							{11,7,2,0,3},
							{12,7,2,5,0}};
		double[][] grafo7 = {{0,3,2,10,2},//0-2, 0-4
							{9,0,5,1,8},//1
							{5,4,0,3,0},//2-3
							{11,7,2,0,3},
							{12,7,2,5,0}};//
		
		int tamañoMatriz = grafo.length;
		AGM agm = new AGM();
		int tamañoAgm = agm.ConstruirAGM(grafo).size();
		
		assertEquals(tamañoMatriz, tamañoAgm);
		
	 }
	
		
}
	
	
	
	

