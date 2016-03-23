/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoc;

//import java.awt.GridLayout;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author millan
 */
public class TicTacTocIHMImpl extends javax.swing.JFrame implements TicTacTocIHM
{
    private final JButton [][] boutons  ;
    private int nbCoup ;
    private final char symbole  ;
    private boolean bloque ;
    
    /**
     * Constructeur de la classe
     * @param premierAJouer initialise l'état global du système en lui indiquant
     * s'il est le premier joueur à jouer ou pas
     */
    public TicTacTocIHMImpl(final boolean premierAJouer) 
    {
        this.symbole = (premierAJouer?'X':'O') ;
        this.boutons = new JButton [3][3] ;
        this.nbCoup = 0 ;

        this.bloque = !premierAJouer ;
        
        JPanel grille = new JPanel() ;
        
        grille.setLayout(new java.awt.GridLayout(3, 3));
        for (int idx = 0 ; idx <3 ; idx++)
        {
            for (int idy = 0 ; idy < 3; idy++)
            {
                this.boutons[idx][idy] = new JButton() ;
                grille.add(this.boutons[idx][idy]) ;
            }
        } 
        this.add(grille) ;
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setTitle("Tic Tac Toc");
        this.setResizable(false);
        this.setSize(300, 300);
        this.setLocation(50, 50);
        this.setVisible(true);
    }
    
    /**
     * Ajoute le même gestionnaire d'évènement sur toute les cases
     * @param al le gestionnaire d'évènement
     */
    @Override
    public void addListener(final ActionListener al)
    {
        for (int idx = 0 ; idx <3 ; idx++)
        {
            for (int idy = 0 ; idy < 3; idy++)
            {
                this.boutons[idx][idy].addActionListener(al);
            }
        }         
    }

    /**
     * @param symbole joueur dont on veut savoir s'il a gagné ou pas
     * @return vrai si le joueur a gagné faut sinon
     */
    private boolean aGagne(final char symbole)
    {
        String symSt = Character.toString(symbole) ;        
        boolean res  = false ;
        int idx = 0 ;
        while (!res && idx <3)
        {
           res = true ;
           boolean res1 = true ;
           for (int idy = 0 ; idy <3 ; idy++)
           {
               res =    res &&  symSt.equals(this.boutons[idx][idy].getText()) ;
               res1 =   res1 && symSt.equals(this.boutons[idy][idx].getText()) ;
        }
           idx++ ;
           res = res || res1 ;
    }
        if (!res)
        {
            res = true ;
            boolean res1 = true ;
    
            for (idx = 0 ; idx <3 ; idx++)
            {
               res =    res &&  symSt.equals(this.boutons[idx][idx].getText()) ;
               res1 =   res1 && symSt.equals(this.boutons[2 - idx][idx].getText()) ;               
            }  
            res = res || res1 ;
        }
        return res ;
    }
    
    /**
     * Recherche de la position d'une case dans la grille
     * @param laCase case dont on cherche la position
     * @return un tableau de deux entiers dont le premier est la ligne où se
     * trouve la case et le second la colonne
     */
    private int [] rechercherBouton(final JButton laCase)
    {
        int [] res = null ;
        boucle : for (int idx = 0 ; idx < 3 ; idx++)
        {
            for (int idy = 0 ; idy < 3 ; idy++)
            {
                if (laCase == this.boutons[idx][idy])
                {
                    res = new int [2] ;
                    res [0] = idx ;
                    res [1] = idy ;
                    break boucle ;
                }
            }            
        }
        return res ;
    }
    
    /**
     * Calcule le gagnant de la partie s'il y en a un et affiche un message
     */
    private void gererFinDePartie ()
    {
        if (this.aGagne(this.symbole))
        {
            JOptionPane.showMessageDialog(this, 
                    "GAGNE",       
                    "GAGNE", JOptionPane.INFORMATION_MESSAGE);                        
        }
        else if (this.aGagne(TicTacTocIHMImpl.this.symbole == 'X'?'O':'X'))
        {
            JOptionPane.showMessageDialog(this, 
                    "PERDU",       
                    "PERDU", JOptionPane.INFORMATION_MESSAGE);                 
        }
        else if (this.nbCoup == 9)
        {
            JOptionPane.showMessageDialog(this, 
                    "MATCH NUL",       
                    "MATCH NUL", JOptionPane.INFORMATION_MESSAGE);                 
        }
    }
    
    /**
     * Méthode permettant de propager à l'interface graphique la case
     * cliquée par le joueur local
     * @param laCase la case cliquée
     * @return les coordonnées de la case cliquée
     */
    @Override
    public int[] getDernierCoupJoue(final JButton laCase)
    {
        laCase.setText(Character.toString(TicTacTocIHMImpl.this.symbole));
        int [] res = TicTacTocIHMImpl.this.rechercherBouton(laCase) ;                     
        this.nbCoup++ ;
        
        this.bloque = true ;   
        this.gererFinDePartie () ;
        return res ;
    }
    
    /**
     * @return vrai si le joueur est bloqué c'est-à-dire si c'est au joueur
     * distant de jouer.
     */
    @Override
    public boolean estBloque()
    {
        return this.bloque ;
    }
    
    /**
     * Méthode permettant de propager la case cochée par le joueur distant
     * @param ligne numéro de la ligne cochée par le joueur distant
     * @param colonne numéro de la colonne cochée par le joueur distant
     */
    @Override
    public void jouerDistant(final int ligne, final int colonne)
    {
        this.boutons[ligne][colonne].setText(Character.toString(TicTacTocIHMImpl.this.symbole == 'X'?'O':'X')) ;
        this.bloque = false ;
        this.nbCoup++ ;
        this.gererFinDePartie () ;
    }
}
