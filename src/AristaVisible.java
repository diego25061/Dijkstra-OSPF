
public class AristaVisible {
	public int xf, yf;
	public int peso;

	public int nodoI, nodoF;
	
	/*
	public AristaVisible(int x, int y, int xf, int yf, int peso){
		this.xf = xf;
		this.yf = yf;
		this.peso = peso;
		nodoI = -1;
		nodoF = -1;
	}
	*/
	
	public AristaVisible(int nodoI, int nodoF , int peso){
		this.nodoI = nodoI;
		this.nodoF = nodoF;
		this.peso = peso;
	}
}
