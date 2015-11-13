import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.event.MouseMotionAdapter;

public class Ventana1 {
	/**
	 * 	Constantes
	 */
	//public static final boolean numeracionNodosEmpiezaEnCero= false;
	public static final int frecuenciaDibujo = 10 ;
	public static final int pesoPorDefecto = 10;
	public static final int diametroNodos = 30;
	public static final int diametroBotonesDistancia = 18;
/*
	int i = 0;
	
	public void pintar (Graphics g ){
		i++;
		g.drawString(""+i, 40, 40);
		g.drawLine(4,4,12,12);
		
		
	}
*/	
	
	/**
	 * Mis vars
	 */
	/*
	static Graphics gCanvas;
	static Canvas canv;
	*/
	public static ArrayList <AristaVisible> listaAristasFlechas;
	
	public static ArrayList <BotonCircular> listaBotones;
	public static ArrayList <BotonCircular> listaBotonesDistancia;
	static MotorPanel motor;
	
	static Calculador calc;
	/**
	 * Vars de designer
	 */
	private JFrame frmAlgoritmoDeDijkstra;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		motor = new MotorPanel();
		
		listaBotones= new ArrayList <BotonCircular> ();
		listaBotonesDistancia = new ArrayList <BotonCircular> ();
		calc = new Calculador();
		listaAristasFlechas = new ArrayList <AristaVisible>();
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Throwable e) {
			e.printStackTrace();
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Ventana1 window = new Ventana1();
					window.frmAlgoritmoDeDijkstra.setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public int cogerBotonNodo(int clickX, int clickY){
		int a = -1;
		for ( int i = 0 ; i < listaBotones.size() ; i++){
			if (listaBotones.get(i).puntoEnCirculo(clickX, clickY) && listaBotones.get(i).tipo == BotonCircular.TIPO_NODO)
				a = i;
		}
		return a;
	}

	public int cogerBotonDistancia(int clickX, int clickY){
		int a = -1;
		for ( int i = 0 ; i < listaBotonesDistancia.size() ; i++){
			if (listaBotonesDistancia.get(i).puntoEnCirculo(clickX, clickY) && listaBotonesDistancia.get(i).tipo == BotonCircular.TIPO_DISTANCIA)
				a = i;
		}
		return a;
	}
	/**
	 * Create the application.
	 */
	public Ventana1() {
		initialize();
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmAlgoritmoDeDijkstra = new JFrame();
		frmAlgoritmoDeDijkstra.setTitle("Algoritmo de Dijkstra");
		frmAlgoritmoDeDijkstra.setMinimumSize(new Dimension(800, 400));
		frmAlgoritmoDeDijkstra.setBounds(100, 100, 658, 471);
		frmAlgoritmoDeDijkstra.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmAlgoritmoDeDijkstra.getContentPane().setLayout(new BorderLayout(0, 0));

		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new EmptyBorder(2, 4, 4, 4));
		frmAlgoritmoDeDijkstra.getContentPane().add(panel_1, BorderLayout.SOUTH);
		panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.X_AXIS));
		
		JLabel lblNewLabel = new JLabel("Ruta: ");
		panel_1.add(lblNewLabel);
		
		textField_1 = new JTextField();
		textField_1.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent arg0) {
			}
			@Override
			public void mouseDragged(MouseEvent e) {
			}
		});
		textField_1.setBackground(SystemColor.inactiveCaption);
		textField_1.setEditable(false);
		panel_1.add(textField_1);
		textField_1.setColumns(10);
		
		JPanel panel = new JPanel();
		frmAlgoritmoDeDijkstra.getContentPane().add(panel, BorderLayout.WEST);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		JPanel panel_7 = new JPanel();
		panel_7.setAlignmentY(0.0f);
		panel_7.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Agregar", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.add(panel_7);
		GridBagLayout gbl_panel_7 = new GridBagLayout();
		gbl_panel_7.columnWidths = new int[]{125, 0};
		gbl_panel_7.rowHeights = new int[]{23, 23, 23, 0};
		gbl_panel_7.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_7.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel_7.setLayout(gbl_panel_7);
		
		JButton btnNodo = new JButton("Nodo");
	
		btnNodo.setAlignmentX(Component.CENTER_ALIGNMENT);
		GridBagConstraints gbc_btnNodo = new GridBagConstraints();
		gbc_btnNodo.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnNodo.insets = new Insets(0, 0, 5, 0);
		gbc_btnNodo.gridx = 0;
		gbc_btnNodo.gridy = 0;
		panel_7.add(btnNodo, gbc_btnNodo);
		
		JButton btnArista = new JButton("Arista");
	
		btnArista.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		btnArista.setAlignmentX(Component.CENTER_ALIGNMENT);
		GridBagConstraints gbc_btnArista = new GridBagConstraints();
		gbc_btnArista.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnArista.anchor = GridBagConstraints.SOUTH;
		gbc_btnArista.insets = new Insets(0, 0, 5, 0);
		gbc_btnArista.gridx = 0;
		gbc_btnArista.gridy = 1;
		panel_7.add(btnArista, gbc_btnArista);
		
		JButton btnAristaBid = new JButton("Arista Bidireccional");
		btnAristaBid.setVisible(false);
		btnAristaBid.setAlignmentY(1.0f);
		btnAristaBid.setAlignmentX(Component.CENTER_ALIGNMENT);
		GridBagConstraints gbc_btnAristaBid = new GridBagConstraints();
		gbc_btnAristaBid.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnAristaBid.anchor = GridBagConstraints.SOUTH;
		gbc_btnAristaBid.gridx = 0;
		gbc_btnAristaBid.gridy = 2;
		panel_7.add(btnAristaBid, gbc_btnAristaBid);
		
		JPanel panel_11 = new JPanel();
		panel_11.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Datos", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.add(panel_11);
		GridBagLayout gbl_panel_11 = new GridBagLayout();
		gbl_panel_11.columnWidths = new int[]{43, 62, 0};
		gbl_panel_11.rowHeights = new int[]{11, 8, 0, 0};
		gbl_panel_11.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gbl_panel_11.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel_11.setLayout(gbl_panel_11);
		
		JLabel lblNewLabel_3 = new JLabel("# Aristas: ");
		GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
		gbc_lblNewLabel_3.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_3.gridx = 0;
		gbc_lblNewLabel_3.gridy = 0;
		panel_11.add(lblNewLabel_3, gbc_lblNewLabel_3);
		
		JLabel lblNumeroAristas = new JLabel("0");
		GridBagConstraints gbc_lblNumeroAristas = new GridBagConstraints();
		gbc_lblNumeroAristas.anchor = GridBagConstraints.EAST;
		gbc_lblNumeroAristas.insets = new Insets(0, 0, 5, 0);
		gbc_lblNumeroAristas.gridx = 1;
		gbc_lblNumeroAristas.gridy = 0;
		panel_11.add(lblNumeroAristas, gbc_lblNumeroAristas);
		
		JLabel lblNodos = new JLabel("# Nodos: ");
		GridBagConstraints gbc_lblNodos = new GridBagConstraints();
		gbc_lblNodos.insets = new Insets(0, 0, 5, 5);
		gbc_lblNodos.gridx = 0;
		gbc_lblNodos.gridy = 1;
		panel_11.add(lblNodos, gbc_lblNodos);
		
		JLabel label_1 = new JLabel("0");
		GridBagConstraints gbc_label_1 = new GridBagConstraints();
		gbc_label_1.insets = new Insets(0, 0, 5, 0);
		gbc_label_1.anchor = GridBagConstraints.EAST;
		gbc_label_1.gridx = 1;
		gbc_label_1.gridy = 1;
		panel_11.add(label_1, gbc_label_1);
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.setVisible(false);
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnNewButton.gridwidth = 2;
		gbc_btnNewButton.gridx = 0;
		gbc_btnNewButton.gridy = 2;
		panel_11.add(btnNewButton, gbc_btnNewButton);
		
		JPanel panel_6 = new JPanel();
		panel_6.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Calcular Distancia", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.add(panel_6);
		GridBagLayout gbl_panel_6 = new GridBagLayout();
		gbl_panel_6.columnWidths = new int[]{35, 37, 0};
		gbl_panel_6.rowHeights = new int[]{12, 18, 24, 0, 0};
		gbl_panel_6.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_panel_6.rowWeights = new double[]{0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		panel_6.setLayout(gbl_panel_6);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new EmptyBorder(0, 5, 0, 5));
		GridBagConstraints gbc_panel_3 = new GridBagConstraints();
		gbc_panel_3.insets = new Insets(2, 0, 5, 0);
		gbc_panel_3.gridwidth = 2;
		gbc_panel_3.fill = GridBagConstraints.HORIZONTAL;
		gbc_panel_3.gridx = 0;
		gbc_panel_3.gridy = 0;
		panel_6.add(panel_3, gbc_panel_3);
		panel_3.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel_1 = new JLabel("Nodo Inicial");
		panel_3.add(lblNewLabel_1, BorderLayout.WEST);
		
		JComboBox cmbNodoI = new JComboBox();
		panel_3.add(cmbNodoI, BorderLayout.EAST);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new EmptyBorder(0, 5, 0, 5));
		GridBagConstraints gbc_panel_4 = new GridBagConstraints();
		gbc_panel_4.insets = new Insets(2, 0, 5, 0);
		gbc_panel_4.gridwidth = 2;
		gbc_panel_4.fill = GridBagConstraints.HORIZONTAL;
		gbc_panel_4.gridx = 0;
		gbc_panel_4.gridy = 1;
		panel_6.add(panel_4, gbc_panel_4);
		panel_4.setLayout(new BorderLayout(0, 0));
		
		JComboBox cmbNodoF = new JComboBox();
		panel_4.add(cmbNodoF, BorderLayout.EAST);
		
		JLabel lblNodoFinal = new JLabel("Nodo Destino");
		panel_4.add(lblNodoFinal, BorderLayout.WEST);
		
		JPanel panel_13 = new JPanel();
		panel_13.setBorder(null);
		GridBagConstraints gbc_panel_13 = new GridBagConstraints();
		gbc_panel_13.insets = new Insets(0, 0, 5, 0);
		gbc_panel_13.fill = GridBagConstraints.HORIZONTAL;
		gbc_panel_13.gridwidth = 2;
		gbc_panel_13.gridx = 0;
		gbc_panel_13.gridy = 2;
		panel_6.add(panel_13, gbc_panel_13);
		panel_13.setLayout(new BorderLayout(0, 0));
		
		JButton btnNewButton_2 = new JButton("Calcular");
		
		GridBagConstraints gbc_btnNewButton_2 = new GridBagConstraints();
		gbc_btnNewButton_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnNewButton_2.gridwidth = 2;
		gbc_btnNewButton_2.gridx = 0;
		gbc_btnNewButton_2.gridy = 3;
		panel_6.add(btnNewButton_2, gbc_btnNewButton_2);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		frmAlgoritmoDeDijkstra.getContentPane().add(panel_2, BorderLayout.CENTER);
		panel_2.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_5 = new JPanel();
		panel_5.setToolTipText("");
		panel_5.setBackground(Color.LIGHT_GRAY);
		panel_2.add(panel_5, BorderLayout.NORTH);
		
		JLabel labelPanelDibujo = new JLabel("Grafo");
		labelPanelDibujo.setFont(new Font("Tahoma", Font.PLAIN, 12));
		panel_5.add(labelPanelDibujo);
		
		JPanel panel_8 = new JPanel();
		
		panel_2.add(panel_8, BorderLayout.CENTER);
		
		PanelDibujo panelDibujo = new PanelDibujo();

		panelDibujo.addMouseMotionListener(new MMAPanelDibujo());
		panel_8.setLayout(new BorderLayout(0, 0));

		panelDibujo.setBackground(UIManager.getColor("Button.disabledShadow"));
		panel_8.add(panelDibujo);
		
		//motor.registrarCanvas(canvas);
		motor.registrarPanel(panelDibujo);
		panelDibujo.setLayout(null);
		motor.crearLoop();
		
		JButton btnCancelarAgregacion = new JButton("CANCELAR AGREGACION");
		btnCancelarAgregacion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				RegistradorEstados.agregandoNodo = false;
				RegistradorEstados.agregandoFinalArista = false;
				RegistradorEstados.agregandoInicioArista = false;
				
				labelPanelDibujo.setText("Grafo");
				panel_5.setBackground(Color.lightGray);
				btnCancelarAgregacion.setEnabled(false);
				btnCancelarAgregacion.setVisible(false);
				
				if (RegistradorEstados.agregandoNodo){
					
				}else 
					if(RegistradorEstados.agregandoInicioArista){
						
					}
			}
		});
		btnCancelarAgregacion.setVisible(false);
		btnCancelarAgregacion.setEnabled(false);
		panel_2.add(btnCancelarAgregacion, BorderLayout.SOUTH);
		
		btnNodo.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				
				btnCancelarAgregacion.doClick();
				
				RegistradorEstados.agregandoNodo = true;
				labelPanelDibujo.setText("Hacer click para agregar nodo");
				panel_5.setBackground(new Color(255,100,50));
				btnCancelarAgregacion.setEnabled(true);
				btnCancelarAgregacion.setVisible(true);
			}
		});
		btnArista.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {

				btnCancelarAgregacion.doClick();
				
				RegistradorEstados.agregandoInicioArista = true;
				RegistradorEstados.agregandoFinalArista = false;
				labelPanelDibujo.setText("Hacer click en nodo inicial");
				panel_5.setBackground(new Color(255,100,50));
				btnCancelarAgregacion.setEnabled(true);
				btnCancelarAgregacion.setVisible(true);
			}
		});
		panelDibujo.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
			}
			@Override
			public void mouseDragged(MouseEvent e) {
				PanelDibujo.posicionMoverNodoX = e.getX();
				PanelDibujo.posicionMoverNodoY = e.getY();
			}
		});
		
		panelDibujo.addMouseListener(new MouseAdapter() {


			@Override
			public void mouseReleased(MouseEvent e) {
				RegistradorEstados.moviendoNodo = false;
			}
			@Override
			public void mousePressed(MouseEvent e) {
				
				if (RegistradorEstados.agregandoNodo){
					panelDibujo.agregarNodo(new NodoVisible(e.getX(),e.getY()));
					listaBotones.add(new BotonCircular(e.getX(),e.getY(),diametroNodos/2));
					label_1.setText(""+panelDibujo.cantidadNodos);
					
					String [] s = new String [panelDibujo.cantidadNodos];
					for ( int i = 0 ; i<panelDibujo.cantidadNodos; i++){
						s [i] = panelDibujo.listaNodos.get(i).codigo; 
					
					}
					
					int sel= cmbNodoI.getSelectedIndex();
					cmbNodoI.setModel(new DefaultComboBoxModel(s));
					cmbNodoI.setSelectedIndex(sel);
					
					sel= cmbNodoF.getSelectedIndex();
					cmbNodoF.setModel(new DefaultComboBoxModel(s));
					cmbNodoF.setSelectedIndex(sel);
					
				}else 
					if (RegistradorEstados.agregandoInicioArista){

						if(cogerBotonNodo(e.getX(),e.getY())!=-1){
						
							RegistradorEstados.agregandoInicioArista = false;
							RegistradorEstados.agregandoFinalArista = true;
							
							PanelDibujo.posicionAristaInicialx = (int) listaBotones.get(cogerBotonNodo(e.getX(),e.getY())).x;
							PanelDibujo.posicionAristaInicialy = (int) listaBotones.get(cogerBotonNodo(e.getX(),e.getY())).y;
							
							PanelDibujo.nodoInicialArista =cogerBotonNodo(e.getX(),e.getY()) ;
							
							
							labelPanelDibujo.setText("Hacer click en nodo final");
							
						}else if (cogerBotonDistancia(e.getX(),e.getY())!=-1){
							String valor = JOptionPane.showInputDialog(frmAlgoritmoDeDijkstra, "Digitar distancia nueva");
							System.out.println("2"+valor);
							int dist = Integer.parseInt(valor);
							PanelDibujo.getListaAristas().get(cogerBotonDistancia(e.getX(),e.getY())).peso = dist;
						}
						
					}else 
						if (RegistradorEstados.agregandoFinalArista){

							if(cogerBotonNodo(e.getX(),e.getY())!=-1 && PanelDibujo.nodoInicialArista!=cogerBotonNodo(e.getX(),e.getY())){
								AristaVisible aristaV1 = new AristaVisible(PanelDibujo.nodoInicialArista, cogerBotonNodo(e.getX(),e.getY()), (int)(Math.random()*100)+1);
								//AristaVisible aristaV1 = new AristaVisible(PanelDibujo.nodoInicialArista, cogerBotonNodo(e.getX(),e.getY()), pesoPorDefecto );
								panelDibujo.agregarArista(aristaV1);

								int xi = PanelDibujo.listaNodos.get( aristaV1.nodoI ).x;
								int yi = PanelDibujo.listaNodos.get( aristaV1.nodoI ).y;
								 
								int xf = PanelDibujo.listaNodos.get( aristaV1.nodoF ).x;
								int yf = PanelDibujo.listaNodos.get( aristaV1.nodoF ).y;
								
								 
								double vecX=xf-xi;
								double vecY=-yf+yi;
								 
								//g.fillOval((int)(xf-vecX*0.3f) - 9, (int)( yf +vecY*0.3f) - 9, 18,18);
								
								//listaBotonesDistancia.add(new BotonCircular(e.getX(),e.getY(),diametroBotonesDistancia/2 , BotonCircular.TIPO_DISTANCIA));
								
								BotonCircular b = new BotonCircular(
												(int)(xf-vecX*0.3f) ,
												(int)( yf +vecY*0.3f) ,
												diametroBotonesDistancia/2 , BotonCircular.TIPO_DISTANCIA);
								b.arista=aristaV1;
								listaBotonesDistancia.add(b);
								
								//panelDibujo.agregarArista(new AristaVisible(PanelDibujo.nodoInicialArista, cogerCirculo(e.getX(),e.getY()), pesoPorDefecto));
								
								RegistradorEstados.agregandoInicioArista = true;
								RegistradorEstados.agregandoFinalArista = false;

								lblNumeroAristas.setText(""+panelDibujo.cantidadAristas);
								labelPanelDibujo.setText("Hacer click en nodo inicial");
							
								//label_1.setText(""+panelDibujo.cantidadAristas);
							}
						}else {
							
							if(cogerBotonNodo(e.getX(),e.getY())!=-1){
								RegistradorEstados.moviendoNodo = true;
								panelDibujo.nodoSiendoMovido = cogerBotonNodo(e.getX(),e.getY());

								PanelDibujo.posicionMoverNodoX = e.getX();
								PanelDibujo.posicionMoverNodoY = e.getY();
							}
				
							if (cogerBotonDistancia(e.getX(),e.getY())!=-1){

								String valor = JOptionPane.showInputDialog(frmAlgoritmoDeDijkstra,"Digitar distancia nueva");
								System.out.println("3"+valor);
								int dist = Integer.parseInt(valor);
								PanelDibujo.getListaAristas().get(cogerBotonDistancia(e.getX(),e.getY())).peso = dist;
							}
						}
				}
		});
		


		btnNewButton_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				calc.darAristas(PanelDibujo.getListaAristas());
				calc.darNodos(PanelDibujo.getListaNodos());
				if(cmbNodoI.getSelectedIndex() == -1 || cmbNodoF.getSelectedIndex() == -1){
					JOptionPane.showMessageDialog(frmAlgoritmoDeDijkstra, "Seleccionar nodo Inicial y de Destino");
				}
				else{
					ArrayList <Integer> ruta = calc.calcularCamino(cmbNodoI.getSelectedIndex(), cmbNodoF.getSelectedIndex());
					if (ruta == null){
						textField_1.setBackground(new Color(234,97,93));
						textField_1.setText("IMPOSIBLE LLEGAR AL DESTINO");	
						}
					else
					{
						textField_1.setBackground(SystemColor.inactiveCaption);
						if(cmbNodoI.getSelectedIndex()==cmbNodoF.getSelectedIndex())
							calc.ultimaDistancia=0;
						textField_1.setText(ruta.toString() + "      Distancia total: " + calc.ultimaDistancia);
						listaAristasFlechas.clear();
						for(int i=0 ;i < ruta.size() -1; i++){
							listaAristasFlechas.add(new AristaVisible(ruta.get(i), ruta.get(i +1), 0));
						}
					}
				}
			}
		});
		
		
	}
}
