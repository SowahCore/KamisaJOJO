import java.awt.event.*;
import javax.swing.*;

public class ControlButton extends Control implements ActionListener {

	public ControlButton(Model model, Vue f) {
		super(model, f);
		f.setButtonControler(this);
		super.newGame();
	}

	public void actionPerformed(ActionEvent event) {
    model.coords = checkEvent(event);
	update();
	}

	protected int[] checkEvent(ActionEvent event){
	    for (int i = 0; i < 8; i++) {
	        for (int j = 0; j < 8; j++) {
	            if (f.getBouton(i,j) == event.getSource())
	                 return new int[]{i,j};
	        }
	    }
	    return null;
	}

}
