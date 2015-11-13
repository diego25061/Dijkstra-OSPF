import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.UIManager;

public class PanelDibujo extends JPanel {

	private static final long serialVersionUID = -4446317340707731121L;
	
	public static int x;
	public static int y;
	
	int contador;

	public int cantidadNodos;
	public int cantidadAristas;
	public static int nodoInicialArista;
	public static int posicionMoverNodoX, posicionMoverNodoY;
	public static int posicionAristaInicialx, posicionAristaInicialy;
	
	public static int nodoSiendoMovido;
	
	public static int botonAristaSiendoMovido;
	
	static ArrayList <NodoVisible> listaNodos;
	static ArrayList <AristaVisible> listaAristas;
	
	
	public static ArrayList <NodoVisible> getListaNodos(){
		return listaNodos;
	}
	
	public static ArrayList <AristaVisible> getListaAristas(){
		return listaAristas;
	}
	
	
	
	public PanelDibujo(){
		listaNodos = new ArrayList<NodoVisible>();
		listaAristas = new ArrayList<AristaVisible>();
	}
	 
	 public void agregarNodo(NodoVisible nodo){
		 listaNodos.add(nodo);
		 cantidadNodos++;
	 }
	 
	 public void agregarArista(AristaVisible arista){
		 listaAristas.add(arista);
		 cantidadAristas++;
	 }
	 
	 
	 public static float tiempoTotal;

	 
	 public void paint(Graphics g) {
		 if(contador==0){
		 }contador++;
		 tiempoTotal += Ventana1.frecuenciaDibujo/1000.0f;
		 
		 setDoubleBuffered(true);
		 Graphics2D g2 = (Graphics2D)g;
		 g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		 //super.paintComponent(g);
		 //--------------------------------

		 //dibujando fondo
		 g.setColor(new Color(215,240,247));
		 g.fillRect(0, 0, this.getWidth(), this.getHeight());

		 //dibujando aristas
		 for (int n =0 ; n<listaAristas.size(); n++){
			 //int xi = listaAristas.get(n).x;
			 //int yi = listaAristas.get(n).y;

			 int xi = listaNodos.get( listaAristas.get(n).nodoI ).x;
			 int yi = listaNodos.get( listaAristas.get(n).nodoI ).y;
			 
			 int xf = listaNodos.get( listaAristas.get(n).nodoF ).x;
			 int yf = listaNodos.get( listaAristas.get(n).nodoF ).y;
			 
			 int xTexto = xi+ (xf-xi)/2;
			 int yTexto = yi+ (yf-yi)/2;


			 g.setColor(Color.black);
			 
			 g.drawLine(xi, yi, xf ,yf);
			 
			 double vecX=xf-xi;
			 double vecY=-yf+yi;
			 
			 double vecUX = vecX / Math.sqrt(  Math.pow(vecX, 2)  +  Math.pow(vecY, 2) );
			 double vecUY = vecY / Math.sqrt(  Math.pow(vecX, 2)  +  Math.pow(vecY, 2) );
			 
			 double factor = 20;

			 g.drawLine(xi, yi, xf ,yf);
			 
			 //g.drawOval((int)(xf-vecX*0.20f) -5, (int)( yf +vecY*0.20f) - 5, 10,10);
			 
			 //=========
			 //g.fillOval((int)(xf - vecUX * factor) -5, (int)( yf + vecUY * factor) - 5, 10,10);
			 
			 
			 //g.fillRect((int)(xf - vecUX * factor) , (int)( yf + vecUY * factor) ,	 25 ,25	 );

			 String textoNumerico = ""+listaAristas.get(n).peso; 
			 float anchoTexto = textoNumerico.length() * 7;
			 float altoTexto = 9;

			 
			 g.setColor( new Color( 215, 240, 247) );
			 //g.setColor( new Color( 195, 220, 227) );
			 g.fillOval((int)(xf-vecX*0.3f) - 9, (int)( yf +vecY*0.3f) - 9, 18,18);
			 
			 g.setColor(Color.black);			 
			 //g.drawOval((int)(xf-vecX*0.50f) - 9, (int)( yf +vecY*0.50f) - 9, 18,18);
			 //g.drawString(textoNumerico, (int) (xTexto - anchoTexto/2) , (int) (yTexto + altoTexto/2));
			 
			 
			 
			 //g.drawString(textoNumerico, (int) (xf-vecX*0.3 - anchoTexto/2) , (int) (yf +vecY*0.3 + altoTexto/2));
			 
			 xf = (int)(xf - vecUX * 16);
			 yf = (int)( yf + vecUY * 16);
			 //omg
			 
			 double angulo = Math.atan2( yf -yi, xf - xi);
			 //double mod = Math.sqrt( Math.pow(xf - xi, 2) + Math.pow(yf - yi, 2));
			 double mod = 15;
			 
			 //double anguloOffset = 45 + contador;
			 double anguloOffset = 30 + contador*0.33f % 15;
			 anguloOffset = 25;
			 double distOffset = 0;
			 
			 double v1x,v1y ,v2x,v2y;
			 v1x = (mod + distOffset)*Math.cos( Math.toRadians(Math.toDegrees(angulo) + anguloOffset ) );
			 v1y = (mod + distOffset)*Math.sin( Math.toRadians(Math.toDegrees(angulo) + anguloOffset ) );
			 
			 v2x = (mod + distOffset)*Math.cos( Math.toRadians(Math.toDegrees(angulo) - anguloOffset ) );
			 v2y = (mod + distOffset)*Math.sin( Math.toRadians(Math.toDegrees(angulo) - anguloOffset ) );
			 
			 g.drawLine( (int) (xf - v1x) ,(int) ( yf - v1y) , (int)xf , (int)yf);
			 
			 g.drawLine( (int) (xf - v2x) ,(int) ( yf - v2y) , (int)xf , (int)yf);
			 
			 /*
			 g.setColor(Color.red);
			 g.drawLine( (int) (xi) ,(int) ( yi) , (int)(xi + v1x), (int)(yi + v1y));
			 g.setColor(Color.blue);
			 g.drawLine( (int) (xf) ,(int) ( yf) , (int)(xf- v1x), (int)(yf -v1y));
			 */
		 }
		 
		 //dibujando flechas
		 for (int n =0 ; n<Ventana1.listaAristasFlechas.size(); n++){
			 //int xi = listaAristas.get(n).x;
			 //int yi = listaAristas.get(n).y;

			 int xi = listaNodos.get( Ventana1.listaAristasFlechas.get(n).nodoI ).x;
			 int yi = listaNodos.get( Ventana1.listaAristasFlechas.get(n).nodoI ).y;
			 
			 int xf = listaNodos.get( Ventana1.listaAristasFlechas.get(n).nodoF ).x;
			 int yf = listaNodos.get( Ventana1.listaAristasFlechas.get(n).nodoF ).y;

             g2.setStroke(new BasicStroke(3));
			 
			 g.setColor(Color.red);
			 
			 g.drawLine(xi, yi, xf ,yf);
			 
		 }

		 //dibujando texto
		 for (int n =0 ; n<listaAristas.size(); n++){
			 int xi = listaNodos.get( listaAristas.get(n).nodoI ).x;
			 int yi = listaNodos.get( listaAristas.get(n).nodoI ).y;
			 
			 int xf = listaNodos.get( listaAristas.get(n).nodoF ).x;
			 int yf = listaNodos.get( listaAristas.get(n).nodoF ).y;

			 double vecX=xf-xi;
			 double vecY=-yf+yi;
			 String textoNumerico = ""+listaAristas.get(n).peso; 
			 float anchoTexto = textoNumerico.length() * 7;
			 float altoTexto = 9;
			 g.setColor(Color.black);			 
			 g.drawString(textoNumerico, (int) (xf-vecX*0.3 - anchoTexto/2) , (int) (yf +vecY*0.3 + altoTexto/2));
			 
		 }
		 //agregando arista
		 if (RegistradorEstados.agregandoFinalArista){
             g2.setStroke(new BasicStroke(3));
			 g.setColor(new Color(0,0,0,120));
			 
			 //g.drawLine(posicionAristaInicialx, posicionAristaInicialy, x, y);
			 g.drawLine(listaNodos.get(nodoInicialArista).x, listaNodos.get(nodoInicialArista).y, x, y);
		 }

		 //moviendo nodo
		 if (RegistradorEstados.moviendoNodo){
			 Ventana1.listaBotones.get(nodoSiendoMovido).x=posicionMoverNodoX;
			 Ventana1.listaBotones.get(nodoSiendoMovido).y=posicionMoverNodoY;
			 
			 listaNodos.get(PanelDibujo.nodoSiendoMovido).x = posicionMoverNodoX;
			 listaNodos.get(PanelDibujo.nodoSiendoMovido).y = posicionMoverNodoY;
			 
			 
			 for(int i = 0; i< Ventana1.listaBotonesDistancia.size();i++){
				 
				 int xi = PanelDibujo.listaNodos.get( Ventana1.listaBotonesDistancia.get(i).arista.nodoI ).x;
					int yi = PanelDibujo.listaNodos.get( Ventana1.listaBotonesDistancia.get(i).arista.nodoI ).y;
					 
					int xf = PanelDibujo.listaNodos.get( Ventana1.listaBotonesDistancia.get(i).arista.nodoF ).x;
					int yf = PanelDibujo.listaNodos.get( Ventana1.listaBotonesDistancia.get(i).arista.nodoF ).y;
					
					double vecX=xf-xi;
					double vecY=-yf+yi;
				 Ventana1.listaBotonesDistancia.get(i).x  =  (int)(xf-vecX*0.3f) ;
				 Ventana1.listaBotonesDistancia.get(i).y  =  (int)( yf +vecY*0.3f)  ;
			 }
		 }
		 
		 //dibujando nodos
		 for (int n =0 ; n<listaNodos.size(); n++){
             g2.setStroke(new BasicStroke(1));
			 int x = listaNodos.get(n).x;
			 int y = listaNodos.get(n).y;
			 int diametro = listaNodos.get(n).diametro;
			 
			 //el ancho solo es aproximado
			 float ancho = listaNodos.get(n).codigo.length() * 7;
			 float alto = 9;
			 
			 g.setColor(new Color(210,210,120,255));
			 g.fillOval((int) (x-diametro/2),y-diametro/2,diametro,diametro);

			 g.setColor(Color.BLACK);
			 g.drawOval(x-diametro/2,y-diametro/2,diametro,diametro);
			 
			 g.drawString(listaNodos.get(n).codigo, (int) (x - ancho/2), (int) (y + alto/2));
		 }
		 
		 //dibujando nodo sombreado

		 if (RegistradorEstados.agregandoNodo){
			 int diametro = Ventana1.diametroNodos;
			 g.setColor(new Color(210,210,120,255));
			 g.fillOval((int) (x-diametro/2),y-diametro/2,diametro,diametro);

			 g.setColor(Color.BLACK);
			 g.drawOval(x-diametro/2,y-diametro/2,diametro,diametro);
			 
		 }
	  
	 }
	 
}
