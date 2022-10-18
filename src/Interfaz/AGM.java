package Interfaz;

import java.util.Arrays;
import java.util.List;



public class AGM {
	
	private List<Arista> agm;
	
//funcion para encontrar el vertice de menor key  de los vertices que no fueron incluidos en el AGM
	public int MinArista(double key[],boolean AGMSet[], int tamaño) {
		double minAuxiliar=Integer.MAX_VALUE;
		int minIndice=-1;
		
		for (int v=0; v<tamaño; v++ ) {
			if (AGMSet[v]==false && key[v]<minAuxiliar) {
				minAuxiliar=key[v];
				minIndice=v;
			}
		}
		return minIndice;
	}
	
	public List<Arista> ConstruirAGM (double[][] grafo) {
		//constructor del agm
		
		List<Arista> agm = Arrays.asList(new Arista[grafo.length]);
		//Valores de Key que se usan para ir guardando los valores minimos de la arista
		//en su indice como vertice 
		double key[]= new double [grafo.length]; //aristasMinimas
		//representa los vertices  incluidos en el AGM
		boolean AGMSet[]= new boolean [grafo.length];
		
		// inicializo en infinito todas las posiciones de key y falso el array de agm
		for (int i = 0; i < grafo.length; i++) {
	        key[i] = Integer.MAX_VALUE;
	        AGMSet[i] = false;
	    }
		//siempre incluyo el primer vertice en el AGM
		key[0]=0;
		//Agarro el primer vertice
		agm.set(0,new Arista(0,0)); // lo pongo como raiz en el agm
		
		for (int cont=0; cont <grafo.length-1; cont++){
			//agarro el minimo key vertice de los vertices no visitados
			int u=MinArista(key,AGMSet,grafo.length);
			//al vertice agregado lo pongo como visitado
			AGMSet[u]=true;
			
			for (int i=0; i<grafo.length;i++) {
				//reviso que no sea un bucle, que no lo haya visitado, y si lo que hay en grafo[u][i] es menor que key[i]
				if ( AGMSet[i]==false  && grafo[u][i]<key[i]) {

					
					agm.set(i, new Arista(u, i));
					key[i]= grafo[u][i];
					
					
				}
			}
			
		}
		
		return agm;
	} 

	
	 public List<Arista> getAgm() {
			return agm;
		}
	
	
	
}
