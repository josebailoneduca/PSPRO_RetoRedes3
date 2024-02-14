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
 * Panel de dibujado. Contiene dos arboles binarios.
 * Uno con el arbol completo y otro para mostrar 
 * el camino hasta un elemento buscado.
 * 
 *  Conforme se van insertando elementos se hace al arbol del atributo "completo".
 *  
 *  Cuando se realiza una busqueda se extrae el subarbol que lleva hasta el elemento buscado.
 *  Ese arbol extraido se guarda en el atributo "busqueda".
 *  
 *  Cuando este componente se repinta se dibuja el arbol completo con unos colores
 *  y luego el arbol de la busqueda con otros colores.
 * 
 * @author Jose Javier Bailon Ortiz
 */
public class Lienzo extends JPanel{

	/**
	 * Arbol binario completo
	 */
	private ArbolBinarioDibujable<Integer> completo;
	
	/**
	 * Arbol binaro de busqueda
	 */
	private ArbolBinarioDibujable<Integer> busqueda;
	
	/**
	 * Color de fondo de los nodos del arbol completo
	 */
	private Color fondoCompleto=new Color(120,220,120);
	
	/**
	 * Color de linea de l arbol completo
	 */
	private Color lineasCompleto=Color.BLACK;
	
	/**
	 * Color de fondo de los nodos del arbol de busqueda
	 */
	private Color fondoBusqueda=Color.ORANGE;
	
	/**
	 * Color de linea del arbol de busqueda
	 */
	private Color lineasBusqueda=Color.RED;
	
	
	
	
	/**
	 * Constructor. Inicializa el arbol completo a un arbol binario de Integer vacio.
	 */
	public Lienzo() {
		completo=new ArbolBinarioDibujable<Integer>();
	}



	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		int profundidad=0;
		//si hay arbol completo se dibuja
		if (completo!=null) {
			profundidad=completo.getProfundidad();
			completo.dibujar((Graphics2D) g, profundidad, this.getWidth(), this.getHeight(),fondoCompleto,lineasCompleto);
		}
		((Graphics2D)g).setStroke(new BasicStroke(3));
		//si hay arbol de busqueda se dibuja
		if (busqueda!=null)
			busqueda.dibujar((Graphics2D) g, profundidad, this.getWidth(), this.getHeight(),fondoBusqueda,lineasBusqueda);
	}
	
	
	
	/**
	 * Inserta un elemento en el arbol completo
	 * 
	 * @param v El elemento
	 */
	public void insertar(Integer v) {
		completo.insertar(v);
		busqueda=null;
		repaint();
	}

	
	/**
	 * Elimina un elemento del arbol completo
	 * 
	 * @param v El elemento
	 */
	public void eliminar(Integer v) {
		completo.eliminar(v);
		busqueda=null;
		repaint();
	}
	
	
	/**
	 * Busca un elemento en el arbol completo.
	 * 
	 * La busqueda genera un subarbol que es asignado al atributo "busqueda"
	 * lo que servira para dibujarlo.
	 * 
	 * @param v El elemento a bucar
	 * 
	 * @return True si se ha encontrado, false si no se ha encontrado
	 */
	public boolean buscar(Integer v) {
		busqueda = completo.buscarSubArbol(v);
		repaint();
		return busqueda!=null;
	}



	/**
	 * Vacia el arbol completo y elimina el arbol de busqueda
	 */
	public void vaciar() {
		completo.vaciar();
		busqueda=null;
		repaint();
		
	}
	
	
	
}
