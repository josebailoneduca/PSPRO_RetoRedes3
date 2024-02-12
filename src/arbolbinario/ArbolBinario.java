/**
 * 
 */
package arbolbinario;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 
 * @author Jose Javier Bailon Ortiz
 */
public class ArbolBinario<T extends Comparable<T>> {
	protected class Nodo<Y extends Comparable<Y>> implements Comparable<Nodo<Y>> {

		private Nodo<Y> padre;
		private Nodo<Y> menor;
		private Nodo<Y> mayor;
		private Y valor;

		public Nodo(Y valor) {
			this.valor = valor;
		}

		public Nodo<Y> getPadre() {
			return padre;
		}

		public void setPadre(Nodo<Y> padre) {
			this.padre = padre;
		}

		public Nodo<Y> getMenor() {
			return menor;
		}

		public void setMenor(Nodo<Y> menor) {
			this.menor = menor;
		}

		public Nodo<Y> getMayor() {
			return mayor;
		}

		public void setMayor(Nodo<Y> mayor) {
			this.mayor = mayor;
		}

		public Y getValor() {
			return valor;
		}

		public void setValor(Y valor) {
			this.valor = valor;
		}

		@Override
		public int compareTo(ArbolBinario<T>.Nodo<Y> o) {
			return this.valor.compareTo(o.getValor());
		}
		
		
		/**
		 * Elimina un nodo hijo dado un padre
		 * @param hijo
		 * @return
		 */
		public boolean quitarHijo(Nodo<Y> hijo) {
			if (mayor==hijo) {
				mayor=null;
				return true;
			}
			else if(menor==hijo) {
				menor=null;
				return true;
			}
			else
				return false;
		}
	}

	// ATRIBUTOS
	protected Nodo<T> raiz = null;

	
	public ArbolBinario() {
		
	}
	
	
	/**
	 * Constructor a partir de una lista
	 */
	public ArbolBinario(List<T> lista) {
		Iterator<T> it = lista.iterator();
		
		while (it.hasNext()) {
			insertar(it.next());
		}
	}

	
	
	public int getProfundidad() {
		if (raiz==null)
			return 0;
		return getProfundidad(raiz,1);
	}
	


	/**
	 * @param nodo Raiz del subarbol
	 * @param p profundidad actual
	 * @return la maxima profundidad alcanzada por el subarbol
	 */
	private int getProfundidad(ArbolBinario<T>.Nodo<T> nodo, int p) {
		
		int profMenores=p;
		int profMayores=p;
		
		if (nodo.getMenor()!=null)
			profMenores=getProfundidad(nodo.getMenor(),p+1);
		
		if (nodo.getMayor()!=null)
			profMayores=getProfundidad(nodo.getMayor(),p+1);
		
		
		if (profMenores>profMayores)
			return profMenores;
		else 
			return profMayores;
	}

	public void vaciar() {
		raiz=null;
	}


	/**
	 * Insertar un valor en el arbol
	 * 
	 * @param valor El valor a insertar
	 */
	public void insertar(T valor) {
		Nodo<T> hijo = new Nodo<T>(valor);
		if (raiz == null)
			raiz = hijo;
		else
			insertar(raiz, hijo);
	}

	

	
	/**
	 * Borra una ocurrencia de un valor en el arbol
	 * 
	 * @param valor El valor a borrar
	 * 
	 * @return True si lo ha borrado, False si no lo ha hecho
	 */
	public boolean eliminar(T valor) {
			Nodo<T> nodoEliminar = buscarNodo(raiz, valor);
			//no existe
			if (nodoEliminar==null)
				return false;
			else {
				//guardar referencias a padre e hijos
				Nodo<T> menores = nodoEliminar.getMenor();
				Nodo<T> mayores = nodoEliminar.getMayor();
				Nodo<T> padre = nodoEliminar.getPadre();

				//si el padre es null es que es la raiz la cual ponemos a null
				if (padre==null)
					raiz=null;
				
				//en otro caso eliminamos el enlace con el padre
				else 
					padre.quitarHijo(nodoEliminar);
				
				//eliminar referencia ascendente con padre
				nodoEliminar.setPadre(null);

				//recorrer y agregar hijos
				insertarSubarbol(menores);
				insertarSubarbol(mayores);
				
				return true;
			}
		}
	
	
	
	
	
		
	public List<T> buscar(T valor){
		   return buscar(new ArrayList<T>(),raiz,valor);
	} 
	
	public ArbolBinario<T> buscarSubArbol(T valor){
			List<T> lista = buscar(valor);
			if (lista!=null) 
				return new ArbolBinario<T>(lista); 
			else
				return null;
				
	}
	
	
	
	/**
	 * @param arrayList
	 * @param raiz2
	 * @param valor
	 * @return
	 */
	private List<T> buscar(ArrayList<T> lista, ArbolBinario<T>.Nodo<T> nodo, T valor) {
		//si es null es que no esta. Devolvemos null y terminamos
		if (nodo==null)
				return null;
		
		//agregar valor a la lista
		lista.add(nodo.valor);
		//si el valor es el que se busca devolvemos la lista
		if (valor.equals(nodo.valor))
			return lista;
		//en otro caso seguimos buscando
		else if(nodo.valor.compareTo(valor)>0) {
			return buscar(lista,nodo.getMenor(),valor);
		}else {
			return buscar(lista,nodo.getMayor(),valor);
		}
	}




	/**
	 * Recorre un subarbol y lo inserta
	 * 
	 * @param nodo La raiz del subarbol
	 */
	private void insertarSubarbol(ArbolBinario<T>.Nodo<T> nodo) {
		if (nodo!=null) {
		insertar(nodo.getValor());
		if (nodo.getMenor()!=null)
			insertarSubarbol(nodo.getMenor());
		if (nodo.getMayor()!=null)
			insertarSubarbol(nodo.getMayor());
		}
	}

	

	/**
	 * Busca un nodo con el valor determinado usando recursividad
	 * 
	 * @param valor El valor a buscar
	 * @return El nodo si esta o null si no existe
	 */
	private ArbolBinario<T>.Nodo<T> buscarNodo(Nodo<T> nodo, T valor) {
		if (nodo==null)
			return null;
		else if (nodo.getValor().equals(valor))
			return nodo;
		else if (nodo.getValor().compareTo(valor)>0)
			return buscarNodo(nodo.getMenor(), valor);
		else
			return buscarNodo(nodo.getMayor(),valor);
	}
	
	
	/**
	 * Insertar tras un nodo
	 * 
	 * @param nodoPadre El nodo tras el que insertar
	 * @param hijo      El hijo a insertar
	 */
	private void insertar(Nodo<T> nodoPadre, Nodo<T> hijo) {

		if (nodoPadre.compareTo(hijo) > 0) {
			if (nodoPadre.getMenor() == null) {
				nodoPadre.setMenor(hijo);
				hijo.setPadre(nodoPadre);
			}

			else {
				insertar(nodoPadre.getMenor(), hijo);
			}
		} else {
			if (nodoPadre.getMayor() == null) {
				nodoPadre.setMayor(hijo);
				hijo.setPadre(nodoPadre);
			} else {
				insertar(nodoPadre.getMayor(), hijo);
			}
		}
	}//fin insertar

	
}
