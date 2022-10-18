package Test;

import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import Interfaz.AGM;
import Interfaz.Funciones;
import Interfaz.Grafo;
import Interfaz.Nodo;
import Interfaz.Arista;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class TestGrafo {
	
	
	@Test
	//verifica que se haya completado el grafo
	public void grafoCompleto() {
		
		ArrayList<Nodo> vertices = new ArrayList<Nodo>();
		
		vertices.add(0,new Nodo(3,5));
		vertices.add(1,new Nodo(4,7));
		vertices.add(2,new Nodo(6,2));
		vertices.add(3,new Nodo(0,9));
		
		
		Grafo f = new Grafo();
		double[][] otroGrafo = f.grafoCompleto(vertices);
		int cont = 0;
		for (int i=0;i<otroGrafo.length;i++) {
			for (int j=0;j<otroGrafo[0].length;j++) {
				if(i==j) {
					cont++;
				}
			}
			
		}
		
		assertTrue(otroGrafo.length==cont);
		
	}
	
	
	
	
	@Test
	public void cantidadCluster() {
		
		ArrayList<Nodo> vertices = new ArrayList<Nodo>();
		
		vertices.add(new Nodo(0,1));
		vertices.add(new Nodo(0,2));
		vertices.add(new Nodo(0,3));
		vertices.add(new Nodo(1,0));
		vertices.add(new Nodo(1,2));
		vertices.add(new Nodo(1,3));
		vertices.add(new Nodo(2,0));
		vertices.add(new Nodo(2,1));
		vertices.add(new Nodo(2,3));
		vertices.add(new Nodo(3,0));
		vertices.add(new Nodo(3,1));
		vertices.add(new Nodo(3,2));
		
		
		Grafo f = new Grafo();
		double[][] grafo = f.grafoCompleto(vertices);
		f.crearClusters(3, grafo);
		assertEquals(grafo.length-2, f.crearClusters(3, grafo).size());
		
		
	}
	
	
	

}
