import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;

public abstract class Control {
    Model model;
    Vue f;

    public Control(Model model, Vue f) {
    	this.model = model;
    	this.f = f;
    }

	protected void newGame(){
		model.newGame();
		initCases();
		initTours();
		initMouvements();
    }


	protected void update(){
		if(verif(model.coords)){
			updateTower(model.exCoords, model.coords);
			prochainPion();
			supressionMouvements();
			updateMouvement();
			if (winner() != null) {
				supressionMouvements();
				partieFin(winner());
				return;
			}
			model.turn += 1;
			model.pionActuel = model.prochainPion;
		}
	}

	protected void updateTower(int[] exCoords, int[] coords){
		f.getBouton(coords[0], coords[1]).setIcon(f.getBouton(exCoords[0], exCoords[1]).getIcon());
		f.getBouton(exCoords[0], exCoords[1]).setIcon(null);
		model.setPositionPion(model.coords[0], model.coords[1], model.getPostionPion(model.exCoords[0], model.exCoords[1]));
		model.setPositionPion(model.exCoords[0], model.exCoords[1], 0);


	}

	protected void updateMouvement(){

		if (model.getProchainPion() < 10) {
			model.mouvementPossibleBlanc();
			for (int i = 0; i < 8; i ++) {
				for (int j = 0; j < 8; j ++) {
					if (model.tabCoups[i][j] == "mouv")
						f.getBouton(i, j).setIcon(new ImageIcon(getClass().getResource(model.PATH_COUPS + "B_coup.png")));
				}
			}
		}
		else{
			model.mouvementPossibleNoir();
			for (int i = 0; i < 8; i ++) {
				for (int j = 0; j < 8; j ++) {
					if (model.tabCoups[i][j] == "mouv")
						f.getBouton(i, j).setIcon(new ImageIcon(getClass().getResource(model.PATH_COUPS + "N_coup.png")));
				}
			}
		}
	}

	protected void supressionMouvements(){
		for (int i = 0; i < 8; i ++) {
			for (int j = 0; j < 8; j ++) {
				if(model.tabCoups[i][j] == "mouv" && model.getPostionPion(i, j) != model.getExPion())
					f.getBouton(i, j).setIcon(null);
			}
		}
		model.tabCoups = new String[8][8];
	}

	protected void prochainPion(){
		int prochaine_couleur;
		prochaine_couleur = model.POSITION_COULEURS[model.coords[0]][model.coords[1]];
		model.setExPion(model.getPionActuel());
		if (model.pionActuel < 10){
			model.setProchainPion(prochaine_couleur * 11);
		}
		else{
			model.setProchainPion(prochaine_couleur);
		}
		int[] coordsProchainPion = new int[2];

		for (int i = 0; i < 8; i ++) {
			for (int j = 0; j < 8; j ++) {
				if (model.getProchainPion() == model.getPostionPion(i, j)) {
					model.getProchainPionCoords()[0] = i;
					model.getProchainPionCoords()[1] = j;
					model.exCoords = model.getProchainPionCoords();
				}
			}
		}
	}

	protected boolean verif(int[] tab){
		if(model.tabCoups[tab[0]][tab[1]] == "mouv")
			return true;
		else
			return false;
	}

	protected void initCases(){
		for (int i = 0; i < 8; i ++) {
			for (int j = 0; j < 8; j ++) {
				f.getBouton(i, j).setBackground(Color.decode(model.COULEURS[i][j]));
				f.getBouton(i, j).setIcon(null);
			}
		}
	}

	public void initTours(){
		for (int i = 0; i < 8; i ++) {
				f.getBouton(0, i).setIcon(new ImageIcon(getClass().getResource(model.PATH + model.tours[0][i]+".png")));
				f.getBouton(7, i).setIcon(new ImageIcon(getClass().getResource(model.PATH + model.tours[7][i]+".png")));
		}
	}

	protected String winner(){
		for (int j = 0; j < 8; j++) {
			if (model.getPostionPion(7, j) < 10 && model.getPostionPion(7, j) != 0)
				return "shiro";
			if (model.getPostionPion(0, j) > 10 && model.getPostionPion(0, j) != 0)
				return "kuro";
		}
		return null;
	}

	protected void partieFin(String win){
		f.creerDialog("Yare yare daze, "+ win +" kachi da !");
	}

	protected void initMouvements(){
		for (int i = 0; i < 8; i ++) {
			for (int j = 0; j < 8; j ++) {
				if (model.tabCoups[i][j] == "mouv")
					f.getBouton(i, j).setIcon(new ImageIcon(getClass().getResource(model.PATH_COUPS + "B_coup.png")));
			}
		}
	}

}
