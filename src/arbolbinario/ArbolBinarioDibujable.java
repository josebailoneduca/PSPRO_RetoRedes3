/**
 * 
 */
package arbolbinario;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.util.List;

/**
 * 
 * @author Jose Javier Bailon Ortiz
 */
public class ArbolBinarioDibujable<T extends Comparable<T>>extends ArbolBinario <T> {

	int xMax;
	int yMax;
	
	Color cFondo=Color.GREEN;
	Color cLineas=Color.BLACK;
	
	int desfaseY=0;
	int radio =0;
	
	public ArbolBinarioDibujable() {
		super();
	}
	
	/**
	 * @param lista
	 */
	public ArbolBinarioDibujable(List lista) {
		super(lista);
	}
	
	
	@Override
	public ArbolBinarioDibujable<T> buscarSubArbol(T valor){
		List<T> lista = buscar(valor);
		if (lista!=null) 
			return new ArbolBinarioDibujable<T>(lista); 
		else
			return null;
			
}
	

	public void dibujar (Graphics2D g, int profundidadMax, int xMax, int yMax, Color cFondo, Color cLineas){
		this.cFondo=cFondo;
		this.cLineas=cLineas;
		this.xMax=xMax;
		this.yMax=yMax;
		if (raiz==null)
			return;
		//x de raiz
		int x = xMax/2;
		
		if (profundidadMax>1)
			desfaseY = (yMax-100)/(profundidadMax-1);
		
		dibujarNodo(raiz,g,1,x,50);	
		
	}

	
	/**
	 * @param raiz
	 * @param g Graphics
	 * @param p Profundidad actual
	 * @param x Posicion en X
	 * @param j Posicion en Y
	 */
	private void dibujarNodo(Nodo<T> nodo, Graphics2D g, int p, int x, int y) {
		
		
		g.setColor(cLineas);
		int dx= (int) (xMax/ Math.pow(2, p-1))/4;
		if (nodo.getMenor()!=null) {
			g.drawLine(x, y, x-dx, y+desfaseY);
			dibujarNodo(nodo.getMenor(),g,p+1,x-dx,y+desfaseY);
		}
		if (nodo.getMayor()!=null) {
			g.drawLine(x, y, x+dx, y+desfaseY);
			dibujarNodo(nodo.getMayor(),g,p+1,x+dx,y+desfaseY);
		}

		
		radio = (int) (xMax/Math.pow(2, p-1))/2;
		if (radio>40)
			radio=40;
		dibujarCelda(g,x,y,nodo.getValor());
		
	}

	/**
	 * @param g
	 * @param x
	 * @param y
	 * @param valor
	 */
	private void dibujarCelda(Graphics2D g, int x, int y, T valor) {
		
		g.setColor(cFondo);
		g.fillOval(x-radio, y-radio, radio*2, radio*2);
		g.setColor(cLineas);
		g.drawOval(x-radio, y-radio, radio*2, radio*2);
		Rectangle2D r = g.getFontMetrics().getStringBounds(valor.toString(), g);
		int ancho = (int) r.getWidth();
		int alto= (int) r.getHeight();
		g.drawString(valor.toString(), x-ancho/2, y+alto/2);
	}
}
