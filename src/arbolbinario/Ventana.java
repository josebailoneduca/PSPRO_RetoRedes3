/**
 * 
 */
package arbolbinario;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import javax.swing.BoxLayout;
import javax.swing.JTextField;
import java.awt.GridBagConstraints;
import javax.swing.JButton;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.awt.Color;
import java.awt.FlowLayout;

/**
 * Ventana y punto de inicio del ejemplo con arbol binario
 * 
 * @author Jose Javier Bailon Ortiz
 */
public class Ventana extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	/**
	 * Campo de introduccion de numeros
	 */
	private JTextField inputInsertar;

	/**
	 * Campo de eliminacion de numeros
	 */
	private JTextField inputEliminar;

	/**
	 * Campo de busqueda
	 */
	private JTextField inputBuscar;

	/**
	 * Lienzo de dibujado
	 */
	private Lienzo lienzo;

	/**
	 * Constructor
	 */
	public Ventana() {
		crearElementosInterfaz();
	}

	/**
	 * Crea los elementos de la interfaz poniendo una botonera en la zona superior y
	 * un panel de dibujado en el resto de la ventana
	 */
	private void crearElementosInterfaz() {
		//accion por defecto al pulsar el cierre de la ventana
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Arbol binario - Jose J. Bailón Ortiz");
		//configuracion del panel contenedor
		setBounds(100, 100, 1030, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		//panel de herramientas
		JPanel panelHerramientas = new JPanel();
		contentPane.add(panelHerramientas, BorderLayout.NORTH);
		panelHerramientas.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		//boton de insertar
		JButton btnInsertar = new JButton("Insertar");
		btnInsertar.setActionCommand("insertar");
		btnInsertar.addActionListener(this);

		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new EmptyBorder(0, 20, 0, 0));
		panelHerramientas.add(panel_2);
		panelHerramientas.add(btnInsertar);
		
		//campo de entrada de numeros a insertar
		inputInsertar = new JTextField();
		panelHerramientas.add(inputInsertar);
		inputInsertar.setColumns(10);

		JPanel panel = new JPanel();
		panel.setBorder(new EmptyBorder(0, 20, 0, 0));
		panelHerramientas.add(panel);

		//boton de buscar
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.setActionCommand("buscar");
		btnBuscar.addActionListener(this);
		panelHerramientas.add(btnBuscar);
		
		//campo de entrada de numeros a buscar
		inputBuscar = new JTextField();
		panelHerramientas.add(inputBuscar);
		inputBuscar.setColumns(10);
		
		//boton de eliminar
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.setActionCommand("eliminar");
		btnEliminar.addActionListener(this);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new EmptyBorder(0, 20, 0, 0));
		panelHerramientas.add(panel_1);
		panelHerramientas.add(btnEliminar);
		
		//campo de entrada de numero a eliminar
		inputEliminar = new JTextField();
		panelHerramientas.add(inputEliminar);
		inputEliminar.setColumns(10);
		
		//boton de vaciar el arbol
		JButton btnVaciar = new JButton("Vaciar");
		btnVaciar.setActionCommand("vaciar");
		btnVaciar.addActionListener(this);
		panelHerramientas.add(btnVaciar);

		//lienzo de dibujado
		lienzo = new Lienzo();
		lienzo.setBackground(new Color(255, 255, 255));
		contentPane.add(lienzo, BorderLayout.CENTER);

	}

	/**
	 * Listener de pulsaciones de botones
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		String ac = e.getActionCommand();
		switch (ac) {
		case "insertar":
			insertar();
			break;
		case "eliminar":
			eliminar();
			break;
		case "buscar":
			buscar();
			break;
		case "vaciar":
			lienzo.vaciar();

		}

	}

	/**
	 * Accion de insertar. Recoge el texto del campo inputInsertar
	 * y lo divide por comas para extraer varios numeros si los hubiera
	 * Luego los inserta en el arbol en el orden en el que estan escritos
	 */
	private void insertar() {
		String texto = inputInsertar.getText();
		texto = texto.replaceAll(" ", "");
		List<String> lista = Arrays.asList(texto.split(","));
		Iterator<String> it = lista.iterator();
		while (it.hasNext()) {
			try {
				Integer v = Integer.parseInt(it.next());
				lienzo.insertar(v);
			} catch (NumberFormatException ex) {

			}
		}
	}

	/**
	 * Busca en el arbol el numero introducido en el campo inputBuscar.
	 * En caso de estar se marcará en ventana.
	 * Si no está se informará con un JOptionPane
	 */
	private void buscar() {
		String texto = inputBuscar.getText();
		texto = texto.replaceAll(" ", "");
		try {
			Integer v = Integer.parseInt(texto);
			if (!lienzo.buscar(v))
				JOptionPane.showMessageDialog(this, "El numero " + v + " no se encuentra en el arbol");
		} catch (NumberFormatException ex) {
		}
	}

	/**
	 * Accion de eliminar. Recoge el texto del campo inputEliminar
	 * y lo divide por comas para extraer varios numeros si los hubiera
	 * Luego los elmina del arbol en el orden en el que estan escritos
	 */
	private void eliminar() {
		String texto = inputEliminar.getText();
		texto = texto.replaceAll(" ", "");
		List<String> lista = Arrays.asList(texto.split(","));
		Iterator<String> it = lista.iterator();
		while (it.hasNext()) {
			try {
				Integer v = Integer.parseInt(it.next());
				lienzo.eliminar(v);
			} catch (NumberFormatException ex) {

			}
		}

	}

	/**
	 * Main de la aplicacion.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Ventana frame = new Ventana();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
