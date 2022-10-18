package Interfaz;


public class Funciones {
	

	public static double trinomio(double num1, double num2) {
		double respuesta= Math.pow(num1,2) - (2*num1*num2) + Math.pow(num2, 2);
		return respuesta;
	}
	
	public static double ValorArista(double x1, double y1, double x2, double y2) {
		double respuesta =trinomio(x2,x1) + trinomio (y2,y1);
		double total = Math.sqrt(respuesta);
		return total;
		
	}
	
	public static double valorArista(Nodo n1, Nodo n2) {
		return ValorArista(n1.getPosX(),n1.getPosY(), n2.getPosX(), n2.getPosY());
			
		
	}
	
	
	
}
