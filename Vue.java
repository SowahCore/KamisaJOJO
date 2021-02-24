import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Vue extends JFrame {

	protected Model model;

	// panels
	protected JPanel pTout;
	protected JPanel pSousTout;
	protected JPanel pBouton;

	//bouton
	protected JButton[][] tabBouton;

	//menu
	protected JMenuBar barMenu;
	protected JMenu menu;
	protected JMenuItem mNouvellePartie;
	protected JMenuItem mAPropos;

	public Vue(Model model) {
		this.model = model;

		initAttribut();
		creerMenu();
		creerJeu();
		pack();
		setVisible(true);
		setResizable(false);
		setTitle("KamisaJOJO");
		setIconImage(Toolkit.getDefaultToolkit().getImage("Images/logo.png"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);

	}

	public void initAttribut(){


		barMenu = new JMenuBar();
		menu = new JMenu("Option");
		mNouvellePartie = new JMenuItem("Nouvelle Partie");
		mAPropos = new JMenuItem("À Propos");
		initGrille();
	}

	public void creerJeu(){
		pTout = new JPanel();
		pSousTout = new JPanel();
		pBouton = new JPanel();

		pSousTout.setLayout(new BoxLayout(pSousTout, BoxLayout.Y_AXIS));
		pBouton.setLayout(new GridLayout(8,8));

		pSousTout.add(pBouton);
		pTout.add(pSousTout);
		for (JButton[] listeBouton : this.tabBouton) {
			for (JButton btn : listeBouton) {
				pBouton.add(btn);
			}
		}

		setContentPane(pTout);
	}

	public void initGrille(){
		tabBouton = new JButton[8][8];
		for(int i = 0; i < 8; i++){
			for (int j = 0; j < 8; j++) {
				tabBouton[i][j] = new JButton();
				tabBouton[i][j].setPreferredSize(new Dimension(64, 64));
				tabBouton[i][j].setBorder(BorderFactory.createEmptyBorder());
				// tabBouton[i][j].setBackground(Color.decode(model.COULEURS[i][j]));
			}
		}
	}

	public void creerMenu(){
		barMenu.add(menu);
		menu.add(mNouvellePartie);
		menu.addSeparator();
		menu.add(mAPropos);

		setJMenuBar(barMenu);
	}

	public void setMenuControler(ActionListener listener){
		mNouvellePartie.addActionListener(listener);
		mAPropos.addActionListener(listener);
	}

	public void setButtonControler(ActionListener listener){
		for(int i = 0; i < 8; i++){
			for (int j = 0; j < 8; j++) {
				tabBouton[i][j].addActionListener(listener);
			}
		}
	}

	public void creerDialog(String msg){
		JOptionPane dialog = new JOptionPane();
		dialog.showMessageDialog(this, msg);
		JDialog fenDialog = dialog.createDialog(this, msg);
	}

	public JButton getBouton(int i, int j) {
		return tabBouton[i][j];
	}
	public JMenuItem getNouvellePartie() {
		return mNouvellePartie;
	}
	public JMenuItem getAPropos() {
		return mAPropos;
	}
}
