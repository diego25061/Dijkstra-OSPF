import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

public class MMAPanelDibujo extends MouseMotionAdapter {

	int x = 0;
	int y =0;
	
	
	public void mouseMoved(MouseEvent arg0) {

		x=arg0.getX();
		y=arg0.getY();
		
		PanelDibujo.x=x;
		PanelDibujo.y=y;
	}
}
