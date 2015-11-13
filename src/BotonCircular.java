
public class BotonCircular {
	public float x,y;
	public float radio;
	public int tipo;
	
	
	public static final int TIPO_NODO=0;
	public static final int TIPO_DISTANCIA=0;
	
	public BotonCircular(float x, float y, float radio){	
		this.x= x; this.y =y; this.radio= radio;
		tipo = BotonCircular.TIPO_NODO;
	}
	
	public BotonCircular(float x, float y, float radio, int tipo){
		
		this.x= x; this.y =y; this.radio= radio;
		this. tipo = tipo;
	}
	
	public boolean puntoEnCirculo(float px, float py){
		return Math.sqrt(Math.pow((px - x ),2)+Math.pow((py -y),2) ) <= radio;
	}	
	

	public AristaVisible arista;
	
}
