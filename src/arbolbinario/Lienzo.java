/**
 * 
 */
package arbolbinario;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.LayoutManager;

import javax.swing.JPanel;

/**
 * 
 * @author Jose Javier Bailon Ortiz
 */
public class Lienzo extends JPanel{

	ArbolBinarioDibujable<Integer> completo;
	ArbolBinarioDibujable<Integer> busqueda;
	Color fondoCompleto=new Color(120,220,120);
	Color lineasCompleto=Color.BLACK;
	Color fondoBusqueda=Color.ORANGE;
	Color lineasBusqueda=Color.RED;
	
	
	public Lienzo() {
		completo=new ArbolBinarioDibujable<Integer>();
	}



	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		int profundidad=0;
		if (completo!=null) {
			profundidad=completo.getProfundidad();
			completo.dibujar((Graphics2D) g, profundidad, this.getWidth(), this.getHeight(),fondoCompleto,lineasCompleto);
		}
		((Graphics2D)g).setStroke(new BasicStroke(3));
		if (busqueda!=null)
			busqueda.dibujar((Graphics2D) g, profundidad, this.getWidth(), this.getHeight(),fondoBusqueda,lineasBusqueda);
	}
	
	
	
	
	public void insertar(Integer v) {
		completo.insertar(v);
		busqueda=null;
		repaint();
	}

	public void eliminar(Integer v) {
		completo.eliminar(v);
		busqueda=null;
		repaint();
	}
	
	
	public boolean buscar(Integer v) {
		busqueda = completo.buscarSubArbol(v);
		repaint();
		return busqueda!=null;
	}



	/**
	 * 
	 */
	public void vaciar() {
		completo.vaciar();
		busqueda=null;
		repaint();
		
	}
	
	
	
}
