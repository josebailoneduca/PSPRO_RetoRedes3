/**
 * 
 */
package arbolbinario;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.util.List;

/**
 * Extension del arbol binario con metodos que ayudan a su dibujado.
 * 
 * @param <T> Clase de valor que se guarda en el arbol
 * @author Jose Javier Bailon Ortiz
 */

public class ArbolBinarioDibujable<T extends Comparable<T>> extends ArbolBinario<T> {

	/**
	 * x maxima para el dibujado
	 */
	private int xMax;

	/**
	 * Y maxima para el dibujado
	 */
	private int yMax;

	/**
	 * Color a usar para el fondo de los nodos
	 */
	private Color cFondo = Color.GREEN;

	/**
	 * Color a usar para las lineas
	 */
	private Color cLineas = Color.BLACK;

	/**
	 * Desfase en Y para cada nivel de profundidad
	 */
	private int desfaseY = 0;

	/**
	 * Constructor vacio
	 */
	public ArbolBinarioDibujable() {
		super();
	}

	/**
	 * Constructor a partir de una lista
	 * 
	 * @param lista La lista
	 */
	public ArbolBinarioDibujable(List lista) {
		super(lista);
	}

	/**
	 * Devuelve un arbol a partir de una busqueda
	 */
	@Override
	public ArbolBinarioDibujable<T> buscarSubArbol(T valor) {
		List<T> lista = buscar(valor);
		if (lista != null)
			return new ArbolBinarioDibujable<T>(lista);
		else
			return null;

	}

	/**
	 * Dibuja el arbol
	 * 
	 * @param g              Graphics a usar
	 * @param profundidadMax Profundidad maxima sobre la que calcular desfases y
	 *                       radios. Usada para dibujar dos arboles en la misma
	 *                       posicion independientemente de su propia profundidad
	 * @param xMax X maxima a usar
	 * @param yMax Y maxima a usar
	 * @param cFondo Color para el fondo de los nodos
	 * @param cLineas Color para las lineas
	 */
	public void dibujar(Graphics2D g, int profundidadMax, int xMax, int yMax, Color cFondo, Color cLineas) {
		this.cFondo = cFondo;
		this.cLineas = cLineas;
		this.xMax = xMax;
		this.yMax = yMax;
		
		//si no hay raiz terminar
		if (raiz == null)
			return;
		
		
		// poscion X de raiz
		int x = xMax / 2;
		
		//calculo del desfase en Y para cada nivel
		if (profundidadMax > 1)
			desfaseY = (yMax - 100) / (profundidadMax - 1);

		//iniciar el dibujado recursivo de nodos empezando por la raiz
		dibujarNodo(raiz, g, 1, x, 50);

	}

	/**
	 * Dibuja un nodo y recursivamente sus hijos
	 * 
	 * @param nodo Nodo a dibujar
	 * @param g    Graphics Graphics a usar
	 * @param p    Profundidad actual Profundidad actual
	 * @param x    Posicion en X Posicion en X del nodo
	 * @param y    Posicion en Y Posicion en Y del nodo
	 */
	private void dibujarNodo(Nodo<T> nodo, Graphics2D g, int p, int x, int y) {
		
		//Calcular desfase en X de los hijos respecto al padre
		int dx = (int) (xMax / Math.pow(2, p - 1)) / 4;

		//dibujar hijos
		g.setColor(cLineas);
		if (nodo.getMenor() != null) {
			//linea hacia el menor
			g.drawLine(x, y, x - dx, y + desfaseY);
			//lanzar el dibujado del hijo menor
			dibujarNodo(nodo.getMenor(), g, p + 1, x - dx, y + desfaseY);
		}
		
		if (nodo.getMayor() != null) {
			//linea hacia el mayor
			g.drawLine(x, y, x + dx, y + desfaseY);
			//lanzar el dibujado del hijo mayor
			dibujarNodo(nodo.getMayor(), g, p + 1, x + dx, y + desfaseY);
		}
		
		//calculo del radio del nodo actual
		int radio = (int) (xMax / Math.pow(2, p - 1)) / 2;
		if (radio > 40)
			radio = 40;
		//dibujar nodo actual
		dibujarCelda(g, x, y, radio, nodo.getValor());

	}

	/**
	 * Dibuja una celda
	 * @param g Graphics a usar
	 * @param x Posicion en x
	 * @param y Posicion en Y
	 * @param radio Radio a usar para el circulo
	 * @param valor Valor a mostrar
	 */
	private void dibujarCelda(Graphics2D g, int x, int y, int radio, T valor) {

		g.setColor(cFondo);
		g.fillOval(x - radio, y - radio, radio * 2, radio * 2);
		g.setColor(cLineas);
		g.drawOval(x - radio, y - radio, radio * 2, radio * 2);
		Rectangle2D r = g.getFontMetrics().getStringBounds(valor.toString(), g);
		int ancho = (int) r.getWidth();
		int alto = (int) r.getHeight();
		g.drawString(valor.toString(), x - ancho / 2, y + alto / 2);
	}
}
