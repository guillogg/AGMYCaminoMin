package Interfaz;

import java.awt.FileDialog;
import java.awt.Frame;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.openstreetmap.gui.jmapviewer.Coordinate;




public class Grafo {
	
	private  ArrayList<Nodo> vertices = new ArrayList<Nodo>();
	private List<Arista> aristas = new ArrayList<>();
	private  double[][] grafoCompleto; 
	private ArrayList<Coordinate> coordenadas = new ArrayList<Coordinate>();
	private AGM agm; 
	
	
	

	
	public void main(String[] args)  {
		
		
	}
	
	
	public double[][] grafoCompleto(ArrayList<Nodo> vertices) {
		
		this.grafoCompleto = new double[vertices.size()][vertices.size()];
		
		for (int i=0; i<tamañoNodos(); i++) {
			for (int j=0; j< tamañoNodos(); j++) {
				
				if (i==j) {
					setGrafoCompleto(i,j,0);
				}
				else {
					double valor = Funciones.valorArista(vertices.get(i), vertices.get(j));
					setGrafoCompleto(i,j,valor);
				}					
			}
		}
		return this.grafoCompleto;
	}
	
	public List<Arista> generarAGM(double[][] grafo) {
		agm = new AGM();
		return agm.ConstruirAGM(grafo);	
		
	}
	
	
	public List<Arista> crearClusters(int cantidad, double[][] grafo) {
		
		agm = new AGM();
		List<Arista> clusters = new ArrayList<Arista>(agm.ConstruirAGM(grafo));
		int cont = 1;
		double valorMaximo = Double.MIN_VALUE;
		int indice = 0;
		while (cont < cantidad) {
			
			for(int i=0; i<clusters.size();i++) {
				int indice1 = clusters.get(i).getIndiceNodo1();
				int indice2 = clusters.get(i).getIndiceNodo2();
				double valor = getGrafoCompleto()[indice1][indice2];
				if (valor> valorMaximo) {
					valorMaximo = valor;
					indice = i;
				}
			}
			
			clusters.remove(indice);
			valorMaximo = Double.MIN_VALUE;
			cont++;
		
		}
		
		return clusters;		
	}
	
	

	public void agregarNodo(double x, double y) {
		
		Nodo nodo = new Nodo(x,y);
		getVertices().add(nodo);	
		
	}
	
	
	
	public ArrayList<Nodo> LeerArchivo() throws IOException {

		BufferedReader br  = new BufferedReader(new BufferedReader(new FileReader(ArchivoElegido())));
		String linea = "";
		 while((linea = br.readLine())!=null) {
			String [] t= linea.split(" ");
			double x = Double.valueOf(t[0].trim());
			double y = Double.valueOf(t[1].trim());
			agregarNodo(x,y);
			
		}
		return vertices;
	}
	
	private File ArchivoElegido() throws FileNotFoundException{
		FileDialog dialog =new FileDialog((Frame) null,"Seleccionar Archivo de coordenadas");
		dialog.setMode(FileDialog.LOAD);
		dialog.setVisible(true);
		File[] file = dialog.getFiles();
		return file[0];
	}
	
	
	
	

	
	/////////////////////// metodos auxiliares /////////////////////////////

	public ArrayList<Nodo> getVertices() {
		return vertices;
	}
	
	public ArrayList<Coordinate> getCoordenadas() {
		return coordenadas;
	}
	
	public int tamañoGrafo() {
		return grafoCompleto.length;
	}
	
	public double[][] getGrafoCompleto() {
		return grafoCompleto;
	}
	
	public void setGrafoCompleto(int i, int j, double valor) {
		 	grafoCompleto[i][j] = valor;
	}

	
	public int tamañoNodos() {
		return getVertices().size();
	}
	
	public List<Arista> getAristas() {
		return aristas;
	}
	

}
