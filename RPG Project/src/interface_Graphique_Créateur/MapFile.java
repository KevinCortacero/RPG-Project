package interface_Graphique_Créateur;

import java.awt.Image;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

public class MapFile {

	private Map mapPrincipale;
	protected int backgroundNum;
	protected Tile tileSize;
	protected List<Tile> map;
	protected FileWriter fileWriter;
	protected File currentFile;
	protected int[][] mapFile;
	protected PanelChoixObjetsCréateur panelChoixObjetsCréateur;

	public MapFile(Map map){
		this.map = new ArrayList<Tile>();
		this.mapFile = new int[1000][1000];
		this.setCurrentFile(new File("cartes/test.txt"));
		this.mapPrincipale = map;
	}

	public void setCurrentFile(File currentFile) {
		this.currentFile = currentFile;
	}

	public void sauvegarder() throws IOException{
		// on écrase et on resauvegarde
		this.fileWriter = new FileWriter(this.currentFile);

		// on retranscrie notre matrice
		for(int y = 0; y < this.tileSize.getY() ; y ++){
			for(int x = 0; x < this.tileSize.getX() ; x ++){
				this.fileWriter.write(Integer.toString(this.mapFile[x][y]) + "  ");
			}
			// si on est sur la première ligne, on rajoute l'information du background
			if (y == 0)
				this.fileWriter.write(Integer.toString(this.backgroundNum));
			this.fileWriter.write("\r\n");
		}
		this.fileWriter.close();
	}

	public void remplirMatrice(){
		// remplissage de la matrice
		for(int y = 0; y < this.tileSize.getY() ; y ++){
			for(int x = 0; x < this.tileSize.getX(); x ++){
				this.mapFile[x][y] = this.getTile(x,y).getNuméro();
			}
		}
	}

	public void chargerCarteActuelle(){
		// on recrée la matrice avec les chiffres
		try {
			InputStream is = new FileInputStream(this.currentFile);
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);

			try {
				String ligne;
				int y = 0;
				while ((ligne = br.readLine()) != null ){
					for (int x = 0; x <= ligne.length() -1; x+=3){
//						String numéro = String.valueOf(ligne.charAt(x));
//						if (ligne.charAt(x+1) != ' ' ){
//							numéro += String.valueOf(ligne.charAt(x+1));
//						}
						// this.mapFile[x/3][y] = Integer.parseInt(numéro) ;
						this.mapFile[x/3][y] = Character.getNumericValue(ligne.charAt(x));
					}
					if (y == 0)
						this.mapPrincipale.setBackground(this.getTileMatrice(Character.getNumericValue(ligne.charAt(ligne.length()-1))).getImage(), Character.getNumericValue(ligne.charAt(ligne.length()-1)));
					
					y ++;
					this.tileSize = new Tile((ligne.length()+1)/3 ,1,new ImageIcon("images\\1.utilitaires\\angleMax.jpg"), 1);
				}

				this.tileSize.setY(y);
				this.map.add(this.tileSize);

			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		for(int y = 0; y < this.tileSize.getY() ; y ++){
			for(int x = 0; x < this.tileSize.getX(); x ++){
				if (this.mapFile[x][y] != 0)
					this.map.add(new Tile(x,y,this.getTileMatrice(this.mapFile[x][y]), this.mapFile[x][y]));
			}
		}
	}

	public Tile getTile(int x, int y){
		Tile resultat = new Tile(0);
		if (this.map != null){
			for (Tile tile : this.map){
				if (tile.getX() == x && tile.getY() == y)
					resultat = tile;
			}
		}
		return resultat;
	}

	public ImageIcon getTileMatrice(int numéro){
		return new ImageIcon(this.panelChoixObjetsCréateur.listeImageNuméro.get(numéro).getImage().getScaledInstance(ObjetIcone.tailleImageJeu,ObjetIcone.tailleImageJeu, Image.SCALE_SMOOTH));
	}
}