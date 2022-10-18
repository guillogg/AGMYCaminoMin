package Interfaz;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import org.openstreetmap.gui.jmapviewer.Coordinate;
import org.openstreetmap.gui.jmapviewer.JMapViewer;
import org.openstreetmap.gui.jmapviewer.MapMarkerDot;
import org.openstreetmap.gui.jmapviewer.MapPolygonImpl;
import org.openstreetmap.gui.jmapviewer.interfaces.MapMarker;
import org.openstreetmap.gui.jmapviewer.interfaces.MapPolygon;


import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.JOptionPane;
import java.awt.SystemColor;
import java.awt.Rectangle;

public class Mapa {

	
	private JFrame frameInicio;
	private static JMapViewer mapa;
	private JTextField txtCluster;
	private JButton btnSalir;
	private JButton btnGenerarGrafo;
	private List<Coordinate> coordenadas = new ArrayList<Coordinate>();
	private Grafo grafo = new Grafo();	
	private DefaultListModel lista;
	private JList list;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Mapa window = new Mapa();
					window.frameInicio.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Mapa() {
		inicio();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	
private void inicio() {
		
		frameInicio = new JFrame();
		frameInicio.getContentPane().setBackground(SystemColor.inactiveCaption);
		frameInicio.setBackground(Color.gray);
		frameInicio.getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 14));
		frameInicio.setBounds(100, 100, 846, 590);
		frameInicio.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frameInicio.getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("Ingresar coordenadas");
		lblNewLabel.setFont(new Font("Trebuchet MS", Font.BOLD, 16));
		lblNewLabel.setBounds(25, 10, 183, 34);
		frameInicio.getContentPane().add(lblNewLabel);
		
		JScrollPane scrollLista = new JScrollPane();
		scrollLista.setBounds(25, 54,223, 262);
		frameInicio.getContentPane().add(scrollLista);
		
		list = new JList();
		scrollLista.setViewportView(list);
		
		JLabel clusterLavel = new JLabel("Clusters");
		clusterLavel.setFont(new Font("Trebuchet MS", Font.BOLD, 18));
		clusterLavel.setBounds(35, 391, 76, 22);
		frameInicio.getContentPane().add(clusterLavel);
		
		txtCluster = new JTextField();
		txtCluster.setFont(new Font("Trebuchet MS", Font.PLAIN, 18));
		txtCluster.setColumns(10);
		txtCluster.setBounds(121, 387, 125, 31);
		frameInicio.getContentPane().add(txtCluster);
		
		btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frameInicio.dispose();
			}
		});
		btnSalir.setHorizontalTextPosition(SwingConstants.CENTER);
		btnSalir.setFont(new Font("Trebuchet MS", Font.BOLD, 15));
		btnSalir.setBackground(SystemColor.scrollbar);
		btnSalir.setBounds(46, 487, 105, 31);
		frameInicio.getContentPane().add(btnSalir);
		
		JButton btnSeleccionarArchivo = new JButton("Seleccionar archivo");
		btnSeleccionarArchivo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					
					
					lista = new DefaultListModel();
					lista.addAll(grafo.LeerArchivo());
					list.setModel(lista);
					list.getLayoutOrientation();
					cargarCoordenadas(grafo.getVertices());
					mostrarPuntosEnElMapa(getCoordenadas());	
					
					
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnSeleccionarArchivo.setHorizontalTextPosition(SwingConstants.CENTER);
		btnSeleccionarArchivo.setFont(new Font("Trebuchet MS", Font.BOLD, 15));
		btnSeleccionarArchivo.setBackground(SystemColor.scrollbar);
		btnSeleccionarArchivo.setBounds(46, 326, 184, 31);
		frameInicio.getContentPane().add(btnSeleccionarArchivo);
		
		



		btnGenerarGrafo = new JButton("Generar clusters");
		btnGenerarGrafo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				 if (txtCluster.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Debe ingresar la cantidad de cluster","", JOptionPane.INFORMATION_MESSAGE);
				}
		
				if (list.getModel().getSize()==0 ) {
					JOptionPane.showMessageDialog(null, "Debe ingresar las coordenadas","", JOptionPane.INFORMATION_MESSAGE);
				}
				
				if(txtCluster.getText()!=null && list.getModel().getSize()!=0) {
					
					int cantidadCluster =  Integer.valueOf(txtCluster.getText());
					grafo.grafoCompleto(grafo.getVertices());
					List <Arista>cluster = new ArrayList<Arista>(grafo.crearClusters(cantidadCluster, grafo.getGrafoCompleto()));
				
					mapa.removeAllMapPolygons();
					
					for (int i=0; i< cluster.size(); i++) {
					
						int nodoOrigen = cluster.get(i).getIndiceNodo1();
						int nodoDestino = cluster.get(i).getIndiceNodo2();
			
						mostrarAristas(coordenadas.get(nodoOrigen), coordenadas.get(nodoDestino));
				
			
					}
				
				}
			}
		});
		btnGenerarGrafo.setHorizontalTextPosition(SwingConstants.CENTER);
		btnGenerarGrafo.setFont(new Font("Trebuchet MS", Font.BOLD, 15));
		btnGenerarGrafo.setBackground(SystemColor.scrollbar);
		btnGenerarGrafo.setBounds(651, 487, 150, 31);
		frameInicio.getContentPane().add(btnGenerarGrafo);
		
		JButton btnGenerarGrafoCompleto = new JButton("Grafo Completo");
		btnGenerarGrafoCompleto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mapa.removeAllMapPolygons();
				grafo.grafoCompleto(grafo.getVertices());
				for (int i=0; i<grafo.getGrafoCompleto().length;i++) {
					for (int j=0; j<grafo.getGrafoCompleto()[0].length; j++) {
						if(i!=j) {
							mostrarAristas(coordenadas.get(i),coordenadas.get(j));
						}
							
					}
				}
			}
		});
		btnGenerarGrafoCompleto.setHorizontalTextPosition(SwingConstants.CENTER);
		btnGenerarGrafoCompleto.setFont(new Font("Trebuchet MS", Font.BOLD, 15));
		btnGenerarGrafoCompleto.setBackground(SystemColor.scrollbar);
		btnGenerarGrafoCompleto.setBounds(279, 487, 150, 31);
		frameInicio.getContentPane().add(btnGenerarGrafoCompleto);
		
		JButton btnAgm = new JButton("AGM");
		btnAgm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mapa.removeAllMapPolygons();
				grafo.grafoCompleto(grafo.getVertices());
				
				for (int i=0; i<grafo.generarAGM(grafo.getGrafoCompleto()).size() ;i++) {
					int nodo1 = grafo.generarAGM(grafo.getGrafoCompleto()).get(i).getIndiceNodo1();
					int nodo2 = grafo.generarAGM(grafo.getGrafoCompleto()).get(i).getIndiceNodo2();
					if(nodo1!=nodo2) {
						mostrarAristas(coordenadas.get(nodo1),coordenadas.get(nodo2));
						
					}
				}
			}
		});
		btnAgm.setHorizontalTextPosition(SwingConstants.CENTER);
		btnAgm.setFont(new Font("Trebuchet MS", Font.BOLD, 15));
		btnAgm.setBackground(SystemColor.scrollbar);
		btnAgm.setBounds(467, 487, 150, 31);
		frameInicio.getContentPane().add(btnAgm);
		
		
		mapa = new JMapViewer();		
		frameInicio.getContentPane().add(mapa);
		mapa.setBounds(new Rectangle(280, 10, 519, 461));
		mapa.setZoomContolsVisible(true);
		
		JButton btnLimpiar = new JButton("Limpiar");
		btnLimpiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mapa.removeAllMapMarkers();
				mapa.removeAllMapPolygons();
				lista.removeAllElements();
			}
		});
		btnLimpiar.setHorizontalTextPosition(SwingConstants.CENTER);
		btnLimpiar.setFont(new Font("Trebuchet MS", Font.BOLD, 15));
		btnLimpiar.setBackground(SystemColor.scrollbar);
		btnLimpiar.setBounds(46, 443, 105, 31);
		frameInicio.getContentPane().add(btnLimpiar);
	
	}
	
	
	////////////////// Metodos auxiliares ////////////////////////////
	
	private List<Coordinate> cargarCoordenadas(ArrayList<Nodo> nodos){
		
		for(int i=0; i<nodos.size(); i++) {
			coordenadas.add(new Coordinate(nodos.get(i).getPosX(),nodos.get(i).getPosY()));	
		}		
		return coordenadas;
		
	}
	
	
	private static void mostrarPuntosEnElMapa(List<Coordinate> lista) {
		for(int i=0; i<lista.size(); i++) {
			MapMarker marker = new MapMarkerDot(lista.get(i));
			marker.getStyle().setBackColor(Color.blue);
			marker.getStyle().setColor(Color.blue);
			mapa.addMapMarker(marker);
			mapa.setDisplayPosition(lista.get(i), 12);
			
		}
			
	}
	

	
	private static void mostrarAristas(Coordinate c1,Coordinate c2) {
		
		List<Coordinate> parCoordenadas = new ArrayList<Coordinate>();
		parCoordenadas.add(c1);
		parCoordenadas.add(c2);
		parCoordenadas.add(c2);
		MapPolygon poligono = new MapPolygonImpl(parCoordenadas);
		
		poligono.getStyle().setColor(Color.RED);;
		mapa.addMapPolygon(poligono);
		
	}
	
	public List<Coordinate> getCoordenadas() {
		return coordenadas;
	}
}
