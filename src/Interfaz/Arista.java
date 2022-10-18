package Interfaz;

public class Arista {
	
	
	private int indiceNodo1;
	private int indiceNodo2;
	
	public Arista(int nodo1, int nodo2) {
		
		this.indiceNodo1 = nodo1;
		this.indiceNodo2 = nodo2;
	}
	
	


	public int getIndiceNodo1() {
		return indiceNodo1;
	}


	public void setIndiceNodo1(int indiceNodo1) {
		this.indiceNodo1 = indiceNodo1;
	}


	public int getIndiceNodo2() {
		return indiceNodo2;
	}


	public void setIndiceNodo2(int indiceNodo2) {
		this.indiceNodo2 = indiceNodo2;
	}

	

}
