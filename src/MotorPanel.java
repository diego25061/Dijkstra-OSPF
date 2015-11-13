import javax.swing.Timer;

public class MotorPanel {
	
	PanelDibujo panel;
	
	public void registrarPanel(PanelDibujo panel){
		this.panel = panel;
	}
	
	public void crearLoop(){
         Timer timer = new Timer( Ventana1.frecuenciaDibujo , new ActionListenerPanelDibujo(this.panel));
         timer.start();
	}
	
}
