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
	protected File currentFile;
	protected int[][] mapFile;
	protected List<Tile> map;

	protected int backgroundNum;
	protected Tile tileSize;
	protected FileWriter fileWriter;
	private PanelChoixObjetsCréateur panelChoixObjetsCréateur;

	public MapFile(Map map, File fileMap){
		this.map = new ArrayList<Tile>();
		this.mapFile = new int[1000][1000];
		this.setCurrentFile(fileMap);
		this.mapPrincipale = map;
		this.panelChoixObjetsCréateur = PanelChoixObjetsCréateur.getPanel();
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

					int xMatrice = 0;
					int x = 0;

					while (x < ligne.length()-1){
						String numéro = String.valueOf(ligne.charAt(x));
						while (ligne.charAt(x+1) != ' '){
							numéro += String.valueOf(ligne.charAt(x+1));	
							x ++;
						}
						if (Integer.parseInt(numéro) <= this.panelChoixObjetsCréateur.listeImageNuméro.size())
							this.mapFile[xMatrice][y] = Integer.parseInt(numéro);
						else
							this.mapFile[xMatrice][y] = 0;
						xMatrice ++;
						x += 3;
					}
					if (y == 0){
						this.mapFile[xMatrice+1][y] = Integer.parseInt(String.valueOf(ligne.charAt(ligne.length()-1)));
						if (this.mapFile[xMatrice+1][y] != 0)
							this.mapPrincipale.setBackground(this.getTileMatrice( this.mapFile[xMatrice+1][y]).getImage(), this.mapFile[xMatrice+1][y]);
						else
							this.mapPrincipale.setBackground(null,0);
					}

					y ++;
					this.tileSize = new Tile(1,1,new ImageIcon("images\\1.utilitaires\\angleMax.jpg"), 1);
					this.tileSize.setX(xMatrice);
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