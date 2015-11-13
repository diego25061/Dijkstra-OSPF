import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

public class CanvasPrincipal extends Canvas {
	
	
	public static int x;
	public static int y;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void paint (Graphics graphics){
		
		
		
		BufferedImage bf = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
		Graphics g = bf.getGraphics();
		
		((Graphics2D)g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		g.setColor(Color.white);
		
		g.fillRect(0, 0, this.getWidth(),this.getHeight());

		g.setColor(Color.BLUE);
		g.drawOval(50, 50, 10,10);
		g.drawLine(4, 4, x, y);
		for(int i=0;i<200;i++){
			g.drawOval(50, 50, 10,10);
		}
		
		graphics.drawImage(bf, 0, 0, null);

		
		try {
			Thread.sleep(16);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	
	public void agregarNodo(int x, int y){

	}
}
