
public class NodoVisible extends ElementoVisible{
	public String codigo;
	//public final int diametro = 40;
	public int diametro;
	public int id;
	
	private static int nodoActual;
	//private String[] listaLetras = new String[]{"A","B","C","D","E","F","G"};
	
	public NodoVisible( int x, int y){
		super (x,y);
		diametro = Ventana1.diametroNodos;

		codigo = "N" + nodoActual;
		id = nodoActual;
		nodoActual++;	
	}
}
