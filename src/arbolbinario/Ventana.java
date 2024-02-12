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
 * 
 * @author Jose Javier Bailon Ortiz
 */
public class Ventana extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField inputInsertar;
	private JTextField inputEliminar;
	private JTextField inputBuscar;
	
	private Lienzo lienzo;

	/**
	 * Launch the application.
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

	/**
	 * Create the frame.
	 */
	public Ventana() {
		crearElementosInterfaz();
	}

	/**
	 * 
	 */
	private void crearElementosInterfaz() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1030, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel panelHerramientas = new JPanel();
		contentPane.add(panelHerramientas, BorderLayout.NORTH);
		panelHerramientas.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JButton btnInsertar = new JButton("Insertar");
		btnInsertar.setActionCommand("insertar");
		btnInsertar.addActionListener(this);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new EmptyBorder(0, 20, 0, 0));
		panelHerramientas.add(panel_2);
		panelHerramientas.add(btnInsertar);

		inputInsertar = new JTextField();
		panelHerramientas.add(inputInsertar);
		inputInsertar.setColumns(10);

		JPanel panel = new JPanel();
		panel.setBorder(new EmptyBorder(0, 20, 0, 0));
		panelHerramientas.add(panel);

		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.setActionCommand("buscar");
		btnBuscar.addActionListener(this);
		panelHerramientas.add(btnBuscar);

		inputBuscar = new JTextField();
		panelHerramientas.add(inputBuscar);
		inputBuscar.setColumns(10);
						
								JButton btnEliminar = new JButton("Eliminar");
								btnEliminar.setActionCommand("eliminar");
								btnEliminar.addActionListener(this);
								
										JPanel panel_1 = new JPanel();
										panel_1.setBorder(new EmptyBorder(0, 20, 0, 0));
										panelHerramientas.add(panel_1);
								panelHerramientas.add(btnEliminar);
		
				inputEliminar = new JTextField();
				panelHerramientas.add(inputEliminar);
				inputEliminar.setColumns(10);
				
				JButton btnVaciar = new JButton("Vaciar");
				btnVaciar.setActionCommand("vaciar");
				btnVaciar.addActionListener(this);
				panelHerramientas.add(btnVaciar);

		lienzo = new Lienzo();
		lienzo.setBackground(new Color(255, 255, 255));
		contentPane.add(lienzo, BorderLayout.CENTER);

	}

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
	 * 
	 */
	private void insertar() {
		String texto = inputInsertar.getText();
		texto=texto.replaceAll(" ", "");
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
	 * 
	 */
	private void buscar() {
		String texto = inputBuscar.getText();
		texto=texto.replaceAll(" ", "");
			try {
				Integer v = Integer.parseInt(texto);
				if(!lienzo.buscar(v))
					JOptionPane.showMessageDialog(this, "El numero "+v+" no se encuentra en el arbol");
			} catch (NumberFormatException ex) {
			}
	}

	/**
	 * 
	 */
	private void eliminar() {
		String texto = inputEliminar.getText();
		texto=texto.replaceAll(" ", "");
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

}
