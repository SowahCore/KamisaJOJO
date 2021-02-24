// gris 1 #808080
// vert 2 #00FF00
// rouge 3 #FF0000
// jaune 4 #FFFF00
// rose 5 #FF00FF
// violet 6 #800080
// bleu 7 #0000FF
// orange 8 #FFA500
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.Color;

class Model{

  protected int turn;
  protected int [] coords = new int[2];
  protected int [] exCoords = new int[] {0,3};
  protected int[] prochainPionCoords = new int[2];
  protected int pionActuel = 4;
  protected int exPion;
  protected int prochainPion;
  protected String nomGagant;
  protected String[][] tabCoups;
  protected final String PATH = "/Images/Tours/";
  protected final String PATH_COUPS = "/Images/Coups/";
  protected String[][] tours = new String[][]{
		{"B_gris", "B_vert", "B_rouge", "B_jaune", "B_rose", "B_violet", "B_bleu", "B_orange"},
		{"", "", "", "", "", "", "", ""},
		{"", "", "", "", "", "", "", ""},
		{"", "", "", "", "", "", "", ""},
		{"", "", "", "", "", "", "", ""},
		{"", "", "", "", "", "", "", ""},
		{"", "", "", "", "", "", "", ""},
		{"N_orange", "N_bleu", "N_violet", "N_rose", "N_jaune", "N_rouge", "N_vert", "N_gris"}
  };
  protected int[][] position_pions;
  protected final int[][] POSITION_COULEURS = new int[][]{
  	{1, 2, 3, 4, 5, 6, 7, 8},
  	{6, 1, 4, 7, 2, 5, 8, 3},
  	{7, 4, 1, 6, 3, 8, 5, 2},
  	{4, 3, 2, 1, 8, 7, 6, 5},
  	{5, 6, 7, 8, 1, 2, 3, 4},
  	{2, 5, 8, 3, 6, 1, 4, 7},
  	{3, 8, 5, 2, 7, 4, 1, 6},
  	{8, 7, 6, 5, 4, 3, 2, 1}
  };
  protected final String[][] COULEURS = new String[][]{
    {"#808080", "#00FF00", "#FF0000", "#FFFF00", "#FF00FF", "#800080", "#0000FF", "#FFA500"},
    {"#800080", "#808080", "#FFFF00", "#0000FF", "#00FF00", "#FF00FF", "#FFA500", "#FF0000"},
    {"#0000FF", "#FFFF00", "#808080", "#800080", "#FF0000", "#FFA500", "#FF00FF", "#00FF00"},
    {"#FFFF00", "#FF0000", "#00FF00", "#808080", "#FFA500", "#0000FF", "#800080", "#FF00FF"},
    {"#FF00FF", "#800080", "#0000FF", "#FFA500", "#808080", "#00FF00", "#FF0000", "#FFFF00"},
    {"#00FF00", "#FF00FF", "#FFA500", "#FF0000", "#800080", "#808080", "#FFFF00", "#0000FF"},
    {"#FF0000", "#FFA500", "#FF00FF", "#00FF00", "#0000FF", "#FFFF00", "#808080", "#800080"},
    {"#FFA500", "#0000FF", "#800080", "#FF00FF", "#FFFF00", "#FF0000", "#00FF00", "#808080"}
  };

  public Model(){
    newGame();
  }

  public void newGame(){
    this.turn = 1;
    this.pionActuel = 4;
	this.exCoords = new int[]{0,3};
	this.position_pions = new int[][]{
      {1, 2, 3, 4, 5, 6, 7, 8},
      {0, 0, 0, 0, 0, 0, 0, 0},
      {0, 0, 0, 0, 0, 0, 0, 0},
      {0, 0, 0, 0, 0, 0, 0, 0},
      {0, 0, 0, 0, 0, 0, 0, 0},
      {0, 0, 0, 0, 0, 0, 0, 0},
      {0, 0, 0, 0, 0, 0, 0, 0},
      {88, 77, 66, 55, 44, 33, 22, 11}
    };
	this.tabCoups = new String[][]{
  	  {"", "", "", "", "", "", "", ""},
  	  {"", "", "mouv", "mouv", "mouv", "", "", ""},
  	  {"", "mouv", "", "mouv", "", "mouv", "", ""},
  	  {"mouv", "", "", "mouv", "", "", "mouv", ""},
  	  {"", "", "", "mouv", "", "", "", "mouv"},
  	  {"", "", "", "mouv", "", "", "", ""},
  	  {"", "", "", "mouv", "", "", "", ""},
  	  {"", "", "", "", "", "", "", ""}
    };
  }

  public int getPionActuel(){
    return this.pionActuel;
  }
  public void setPionActuel(int n){
    this.pionActuel = n;
  }

  public int getProchainPion(){
    return this.prochainPion;
  }
  public void setProchainPion(int n){
    this.prochainPion = n;
  }

  public String getNomGagnant(){
    return this.nomGagant;
  }

  public int[] getProchainPionCoords(){
	  return this.prochainPionCoords;
  }
  public void setProchainPionCoords(int[] tab){
	  this.prochainPionCoords = tab;
  }

  public void setPositionPion(int i, int j, int val){
	  this.position_pions[i][j] = val;
  }
  public int getPostionPion(int i, int j){
	  return this.position_pions[i][j];
  }

  public int getExPion(){
	  return exPion;
  }
  public void setExPion(int i){
	  this.exPion = i;
  }

  public void mouvementPossibleNoir(){
	  int n = 1;
	  // HAUT
	  while (prochainPionCoords[0]-n >= 0 && position_pions[prochainPionCoords[0]-n][prochainPionCoords[1]] == 0){
		  tabCoups[prochainPionCoords[0]-n][prochainPionCoords[1]] = "mouv";
		  n += 1;
	  }
	  n = 1;
	  // DIAGONALE HAUT-DROITE
	  while (prochainPionCoords[0]-n >= 0 && prochainPionCoords[1]+n <= 7 && position_pions[prochainPionCoords[0]-n][prochainPionCoords[1]+n] == 0){
		  tabCoups[prochainPionCoords[0]-n][prochainPionCoords[1]+n] = "mouv";
		  n += 1;
	  }
	  n = 1;
	  // DIAGONALE HAUT-GAUCHE
	  while (prochainPionCoords[0]-n >= 0 && prochainPionCoords[1]-n >= 0 && position_pions[prochainPionCoords[0]-n][prochainPionCoords[1]-n] == 0){
		  tabCoups[prochainPionCoords[0]-n][prochainPionCoords[1]-n] = "mouv";
		  n += 1;
	  }
  }

	public void mouvementPossibleBlanc(){
		int n = 1;
		// BAS
		while (prochainPionCoords[0]+n <= 7 && position_pions[prochainPionCoords[0]+n][prochainPionCoords[1]] == 0){
			tabCoups[prochainPionCoords[0]+n][prochainPionCoords[1]] = "mouv";
			n += 1;
		}
		n = 1;
		// DIAGONALE BAS-DROITE
		while (prochainPionCoords[0]+n <= 7 && prochainPionCoords[1]+n <= 7 && position_pions[prochainPionCoords[0]+n][prochainPionCoords[1]+n] == 0){
			tabCoups[prochainPionCoords[0]+n][prochainPionCoords[1]+n] = "mouv";
			n += 1;
		}
		n = 1;
		// DIAGONALE BAS-GAUCHE
		while (prochainPionCoords[0]+n <= 7 && prochainPionCoords[1]-n >= 0 && position_pions[prochainPionCoords[0]+n][prochainPionCoords[1]-n] == 0){
			tabCoups[prochainPionCoords[0]+n][prochainPionCoords[1]-n] = "mouv";
			n += 1;
		}
	}


}
