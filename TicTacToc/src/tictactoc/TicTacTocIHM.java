package tictactoc;

import java.awt.event.ActionListener;
import javax.swing.JButton;

/**
 * Interface de l'IHM
 * @author millan
 */
public interface TicTacTocIHM {

    /**
     * Ajoute le même gestionnaire d'évènement sur toute les cases
     * @param al le gestionnaire d'évènements
     */
    void addListener(final ActionListener al);

    /**
     * @return vrai si le joueur est bloqué c'est-à-dire si c'est au joueur
     * distant de jouer.
     */
    boolean estBloque();

    /**
     * Méthode permettant de propager à l'interface graphique la case
     * cliquée par le joueur local
     * @param laCase la case cliquée
     * @return les coordonnées de la case cliquée
     */
    int[] getDernierCoupJoue(final JButton laCase);

 
    /**
     * Méthode permettant de propager la case cochée par le joueur distant
     * @param ligne numéro de la ligne cochée par le joueur distant
     * @param colonne numéro de la colonne cochée par le joueur distant
     */
    void jouerDistant(final int ligne, final int colonne);
    
}
