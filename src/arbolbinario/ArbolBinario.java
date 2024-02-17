/**
 * 
 */
package arbolbinario;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Clase de arbol binario no balanceado.
 * Basado en nodos con enlaces a padre y dos hijos.
 * 
 * 
 * @param <T> Clase de valor que se guarda en el arbol
 * @author Jose Javier Bailon Ortiz
 */

public class ArbolBinario<T extends Comparable<T>> {
	
	/**
	 * Nodo raiz
	 */
	protected Nodo<T> raiz = null;

	/**
	 * Constructor vacio
	 */
	public ArbolBinario() {
		
	}
	
	
	/**
	 * Constructor a partir de una lista
	 * @param lista Lista a partir de la cual construir el arbol
	 */
	public ArbolBinario(List<T> lista) {
		Iterator<T> it = lista.iterator();
		while (it.hasNext()) {
			insertar(it.next());
		}
	}

	
	/**
	 * Devuelve la profundidad maxima del arbol
	 * 
	 * @return La profundidad
	 */
	public int getProfundidad() {
		if (raiz==null)
			return 0;
		return getProfundidad(raiz,1);
	}
	


	/**
	 * Calcula de manera recursiva la profundidad de un arbol
	 * 
	 * @param nodo Nodo a partir del cual sumar la profundidad
	 * @param p profundidad actual
	 * @return La maxima profundidad alcanzada por el subarbol
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

	
	
	/**
	 * Vacia el arbol poniendo su raiz a null
	 */
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
	 * Insertar un nodo como hijo de otro si hay sitio.
	 * Si la posicion hijo que correspondería esta ocupada
	 * entonces ordena la insercion tras ese hijo.
	 * 
	 * Un igual se insertaria como mayor
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
	
	
	
	
	
	/**
	 * Busca un valor en el arbol devolviendo una lista de valores por los que se ha
	 * pasado desde la raiz hasta llegar al valor
	 * 	
	 * @param valor El valor a buscar
	 * 
	 * @return La lista de pasos o null si no existe
	 */
	public List<T> buscar(T valor){
		   return buscar(new ArrayList<T>(),raiz,valor);
	} 
	
	
	/**
	 * Hace una busqueda y devuelve el resultado como arbol en vez de como lista.
	 * 
	 * @param valor El valor a buscar
	 * 
	 * @return El arbol o null si no existe
	 */
	public ArbolBinario<T> buscarSubArbol(T valor){
			List<T> lista = buscar(valor);
			if (lista!=null) 
				return new ArbolBinario<T>(lista); 
			else
				return null;
				
	}
	
	
	
	/**
	 * Busqueda recursiva de un valor. 
	 * @param lista
	 * @param nodo
	 * @param valor
	 * @return Una lista con los valores por los que se ha pasado hasta llegar al buscado o null si no existe
	 */
	private List<T> buscar(ArrayList<T> lista, ArbolBinario<T>.Nodo<T> nodo, T valor) {
		//Caso base 1
		//Si es null es que no esta en esta rama. Devolvemos null y terminamos
		if (nodo==null)
				return null;
		
		//agregar valor a la lista
		lista.add(nodo.valor);
		
		//Caso base 2
		//si el valor es el que se busca devolvemos la lista
		if (valor.equals(nodo.valor))
			return lista;
		
		
		//Casos recursivos
		//en otro caso seguimos buscando recursivamente por el hijo por el que 
		//podría estar el valor buscado devolviendo lo que retorna esa busqueda
		else if(nodo.valor.compareTo(valor)>0) {
			return buscar(lista,nodo.getMenor(),valor);
		}else {
			return buscar(lista,nodo.getMayor(),valor);
		}
	}




	/**
	 * Recorre un subarbol e inserta sus valores.
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
	 * @param nodo Nodo actual del a busqueda recursiva 
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
	 * Clase interna de Nodo para almacenar valor y referencias a padre y dos hijos
	 * 
	 * @param <Y> Clase de valor almacenado en el nodo
	 * 
	 */
	protected class Nodo<Y extends Comparable<Y>> implements Comparable<Nodo<Y>> {
		
		private Nodo<Y> padre;
		private Nodo<Y> menor;
		private Nodo<Y> mayor;
		private Y valor;

		/**
		 * Constructor 
		 * @param valor El valor a guardar
		 */
		public Nodo(Y valor) {
			this.valor = valor;
		}

		/**
		 * Devuelve el padre
		 * 
		 * @return El padre
		 */
		public Nodo<Y> getPadre() {
			return padre;
		}

		/**
		 * Define el padre
		 * @param padre El nodo que sera el padre
		 */
		public void setPadre(Nodo<Y> padre) {
			this.padre = padre;
		}

		/**
		 * Devuelve el hijo menor
		 * 
		 * @return El hijo menor
		 */
		public Nodo<Y> getMenor() {
			return menor;
		}

		/**
		 * Define el hijo menor
		 * @param menor Nodo a definir como menor
		 */
		public void setMenor(Nodo<Y> menor) {
			this.menor = menor;
		}
		
		/**
		 * Devuelve el hijo mayor
		 * 
		 * @return El hijo mayor
		 */
		public Nodo<Y> getMayor() {
			return mayor;
		}
		
		/**
		 * Define el hijo mayor
		 * @param mayor El hijo mayor
		 */
		public void setMayor(Nodo<Y> mayor) {
			this.mayor = mayor;
		}

		/**
		 * Devuelve el valor contenido en el nodo
		 * 
		 * @return El valor
		 */
		public Y getValor() {
			return valor;
		}
		
		/**
		 * Define el valor contenido en el nodo
		 * @param valor Valor a introducior en el nodo
		 */
		public void setValor(Y valor) {
			this.valor = valor;
		}

		/**
		 * Compara el nodo con otro
		 */
		@Override
		public int compareTo(ArbolBinario<T>.Nodo<Y> o) {
			return this.valor.compareTo(o.getValor());
		}
		
		
		/**
		 * Elimina un nodo hijo 
		 * 
		 * @param hijo a eliminar
		 * 
		 * @return True si lo tiene y false si no lo tiene
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

}
