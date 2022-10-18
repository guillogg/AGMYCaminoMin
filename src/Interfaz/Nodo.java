package Interfaz;

public class Nodo {
	
	private double posX;
	private double posY;

	public Nodo(double posX, double posY) {
		this.posX = posX;
		this.posY = posY;
		
	}

	public double getPosX() {
		return posX;
	}
	
	public double getPosY() {
		return posY;
	}
	
	public String toString() {
		return "("+posX+"; "+posY+")";
		
	}
	
	
	
}
