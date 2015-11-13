import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ActionListenerPanelDibujo implements ActionListener {
	PanelDibujo panelDibujo;
	
	public ActionListenerPanelDibujo(PanelDibujo panelDib){
		panelDibujo = panelDib;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg) {
		
		panelDibujo.repaint();
        //System.out.println("Hello");

	}

}
