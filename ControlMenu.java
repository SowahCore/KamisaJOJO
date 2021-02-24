import java.awt.event.*;
import javax.swing.*;

public class ControlMenu extends Control implements ActionListener{

    public ControlMenu(Model model, Vue f) {
		super(model, f);
		f.setMenuControler(this);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == f.getNouvellePartie()) { super.newGame(); }
		if (e.getSource() == f.getAPropos()) { f.creerDialog("Kamisado remastered style JOJO's Bizzard Adventure par Noah Sochandamandon S2B2"); }
    }
}
