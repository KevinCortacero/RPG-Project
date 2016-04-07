package jeu;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import personnage.FireBall;
import personnage.Héros;

public class Panel extends JPanel {

	Jeu jeu = new Jeu();

	public Jeu getJeu(){
		return this.jeu;
	}
	public void paintComponent(Graphics g){
		g.setColor(new Color(255,255,255));
		g.fillRect(0, 0, this.getWidth(), this.getHeight());

		g.setColor(new Color(0,0,0));
		g.drawString("Element : " + ((Héros)this.getJeu().getHéros()).getElement(), 100, 200);
		g.drawString("Etat : " + ((Héros)this.getJeu().getHéros()).getEtat(), 100, 220);
		g.drawString("Direction : " + this.getJeu().getHéros().direction, 100, 240);
		g.drawString("au sol ? : " + this.getJeu().getHéros().estAuSol, 100, 260);
		g.drawString("vecteur Y : " + this.getJeu().getHéros().vecteurY, 100, 280);
		g.drawString("peut grimper ? : " + ((Héros)this.getJeu().getHéros()).getPeutGrimper(), 100, 300);
		g.drawString("x : " + this.getJeu().getHéros().sprite.coordonnée2D.getX().getComposante(), 100, 320);
		g.drawString("y : " + this.getJeu().getHéros().sprite.coordonnée2D.getY().getComposante(), 100, 340);
		g.drawString("nb fire : " + ((Héros)this.getJeu().getHéros()).fireBalls.size(), 100, 360);

		g.fillRect(this.getJeu().getSol().sprite.coordonnée2D.getX().getComposante(),this.getJeu().getSol().sprite.coordonnée2D.getY().getComposante(),this.getWidth(),this.getJeu().getSol().sprite.hitbox.height);
		g.setColor(new Color(255,0,0));
		g.fillRect(this.getJeu().getMurGauche().sprite.coordonnée2D.getX().getComposante(), this.getJeu().getMurGauche().sprite.coordonnée2D.getY().getComposante(), this.getJeu().getMurGauche().sprite.hitbox.width, this.getJeu().getMurGauche().sprite.hitbox.height);
		g.fillRect(1100, this.getJeu().getMurDroit().sprite.coordonnée2D.getY().getComposante(), this.getJeu().getMurDroit().sprite.hitbox.width, this.getJeu().getMurDroit().sprite.hitbox.height);
		g.setColor(new Color(0,255,0));
		g.fillRect(1050, this.getJeu().getEchelle().sprite.coordonnée2D.getY().getComposante(), this.getJeu().getEchelle().sprite.hitbox.width, this.getJeu().getEchelle().sprite.hitbox.height);
		g.drawImage(this.jeu.getHéros().sprite.image, (int)jeu.getHéros().sprite.coordonnée2D.getX().getComposante(), (int)jeu.getHéros().sprite.coordonnée2D.getY().getComposante(), this);
		g.setColor(new Color(0,0,255));
		g.drawRect(this.getJeu().getHéros().sprite.coordonnée2D.getX().getComposante(), this.getJeu().getHéros().sprite.coordonnée2D.getY().getComposante(), 50, 50);
		for (FireBall f : ((Héros)this.getJeu().getHéros()).fireBalls){
			g.drawImage(f.sprite.image, f.sprite.coordonnée2D.getX().getComposante(), f.sprite.coordonnée2D.getY().getComposante(), this);
		}
		this.afficherCarte(g);
	}

	public void afficherCarte(Graphics g){
		g.setColor(new Color(120,120,120));
		for (int i=0; i <= this.getJeu().getCarte().getTailleMaxX()-1; i++){
			for (int j=0; j <= this.getJeu().getCarte().getTailleMaxY()-1; j++){
				if (this.getJeu().getCarte().getMap()[i][j].getComposante() == 1)
					g.fillRect(20*i, 20*j, 20, 20);
			}
		}
	}
}
