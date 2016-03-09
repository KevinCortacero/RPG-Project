package metroorchestre;

import com.mxgraph.layout.hierarchical.mxHierarchicalLayout;
import com.mxgraph.model.mxCell;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.util.mxConstants;
import com.mxgraph.view.mxGraph;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 *
 * @author millan
 */
public class MetroSuperviseurIHMImpl extends UnicastRemoteObject 
            implements MetroSuperviseurIHM
{

    private final JFrame fenetre ;
    /**
     * @param args the command line arguments
     */
 
    /** Pour éviter un warning venant du JFrame */
    private static final long serialVersionUID = -8123406571694511514L;
    
    private final mxGraph graph ;
    private final Map<String, Object> stations ;
    private final Map<String, Object[]> voies ;
    private final JButton demarrer ;
    private final JButton arreter ;
    
    /**
     * Permet à partir d'une liste de noms de station d'initialiser l'interface
     * graphique représentant le réseau.
     * <B>Attention : </B> le dépot ne doit pas faire partie de cette liste. 
     * Pour simplifier on considèrera que le dépot est toujours positionné
     * avant la première station.
     * @param stations liste des noms de station triés dans l'ordre où
     * elles doivent être connectées. 
     */
    public void initialiserReseau(final List<String> stations)
    {
        Object parent = this.graph.getDefaultParent();
        String fromNom, toNom ;

        Map<String, Object> style = graph.getStylesheet().getDefaultEdgeStyle();
        style.put(mxConstants.STYLE_ENDARROW, mxConstants.ARROW_OVAL);

        mxHierarchicalLayout  layout = new mxHierarchicalLayout(this.graph,  SwingConstants.WEST); 

        // Espacement entre les cellules...
        layout.setDisableEdgeStyle(true);

        layout.setInterRankCellSpacing(60);
        layout.setInterHierarchySpacing(10);
        layout.setParallelEdgeSpacing(3); 

        this.graph.getModel().beginUpdate();

        try 
        {
          mxGraphComponent graphComponent = new mxGraphComponent(graph);
          Object from = this.graph.insertVertex(parent, null, stations.get(0) + "\n> \n> ", 20, 20, 100, 50) ;
          fromNom = stations.get(0) ;          
          this.stations.put(fromNom, from) ;          
  // Problème pour les stations de bout une des deux voies n'est pas affectée 
          this.voies.put(stations.get(0), new Object[2]) ;
          
          Object depot  = this.graph.insertVertex(parent, null, "D�pot" + "\n> \n> ", 20, 20, 100, 50) ;
          this.stations.put("Depot", depot) ;


          for (int idx = 1 ; idx < stations.size() ; idx++)
          {
              Object to = this.graph.insertVertex(parent, 
                      null, 
                      stations.get(idx) + "\n> \n> ", 20, 20, 100, 50) ;
              
              toNom = stations.get(idx) ;
              this.stations.put(stations.get(idx), to) ;
              Object voiesTo      [] = new Object[2] ;
              Object voiesFrom    [] = this.voies.get(fromNom) ;

              voiesFrom[0] = this.graph.insertEdge(parent, null, "", from, to, 
                      mxConstants.STYLE_STROKEWIDTH+"=6;"+mxConstants.STYLE_STROKECOLOR+"=#00FF00;");
              voiesTo[1] = this.graph.insertEdge(parent, null, "", to, from, 
                      mxConstants.STYLE_STROKEWIDTH+"=6;"+mxConstants.STYLE_STROKECOLOR+"=#00FF00;"); 

              this.voies.put(toNom, voiesTo) ;            
              this.voies.put(fromNom, voiesFrom) ;
              from = to ;
              fromNom = toNom ;
          }
          
          Object voiesDepot      [] = new Object[1] ;
          voiesDepot[0] = this.graph.insertEdge(parent, null, "", depot, this.stations.get(stations.get(0)), 
                      mxConstants.STYLE_STROKEWIDTH+"=6;"+mxConstants.STYLE_STROKECOLOR+"=#00FF00;");
          this.voies.put("Depot", voiesDepot) ;

          this.graph.setCellsMovable(false);
          this.graph.setCellsSelectable(false);
          this.graph.setAllowDanglingEdges(false);

          layout.execute(this.graph.getDefaultParent());
          JPanel panel = new JPanel() ;
          panel.setLayout(new java.awt.BorderLayout());
          panel.add(graphComponent, java.awt.BorderLayout.CENTER) ;
          JPanel panelBoutons = new JPanel() ;
          panelBoutons.setLayout(new java.awt.GridLayout(2, 1));

          panelBoutons.add(this.demarrer) ;
          panelBoutons.add(this.arreter) ;

          panel.add(panelBoutons, java.awt.BorderLayout.SOUTH) ;
          this.fenetre.getContentPane().add(panel);                
        } 
        finally 
        {
          this.graph.getModel().endUpdate();
        }       
        this.fenetre.setSize(1600, 320);
        this.fenetre.setVisible(true); 
    }
    
    /**
     * Constructeur sans paramètre.
     * <B>Attention : </B> le classpath doit contenir la bibliothèque 
     * <I>jgraphx.jar</I>
     * @throws RemoteException 
     */
    public MetroSuperviseurIHMImpl() throws RemoteException
    {
        this.fenetre = new JFrame("Superviseur du reseau") ;
        this.graph = new mxGraph() ;
        this.stations = new HashMap<>() ;
        this.voies = new HashMap<>() ;
        this.demarrer = new javax.swing.JButton("DEMARRER METRO") ;
        this.arreter =  new javax.swing.JButton("ARRETER METRO") ;        
        this.fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    /**
     * Associe le code associé au bouton de <I>DEMARRER METRO</I>
     * @param ac l'écoutteur associé au bouton
     */
    public void setActionListenerDemarrer(final java.awt.event.ActionListener ac)
    {
        this.demarrer.setActionCommand("START") ;
        this.demarrer.addActionListener(ac);
    }

    /**
     * Associe le code associé au bouton de <I>ARRETER METRO</I>
     * @param ac l'écoutteur associé au bouton
     */
    public void setActionListenerArreter(final java.awt.event.ActionListener ac)
    {
        this.arreter.setActionCommand("STOP") ;
        this.arreter.addActionListener(ac);
    }    

    /**
     * Cette interface permet de fournir les informations pour visualiser
     * le déplacement des rames de métro sur la ligne. Cette opération
     * pouvant être appélée depuis toutes les stations elle est synchronisée.
     * @param nomStationDepart nom de la station d'où part la rame
     * @param voieDepart numéro de la voie où se trouve la rame au départ
     * @param nomStationArrivee nom de la station vers laquelle la rame se
     * déplace
     * @param voieArrivee voie sur laquelle la rame se trouvera la rame une 
     * fois arrivée dans la station d'arrivée
     * @param numRame numéro de la rame que l'on veut afficher
     * @throws java.rmi.RemoteException
     */
    @Override
    public void modifierAffichage(
            final String nomStationDepart, 
            final int voieDepart, 
            final String nomStationArrivee, 
            final int voieArrivee,             
            final String numRame) throws RemoteException
    {
        Afficheur afficheur = new Afficheur(
                nomStationDepart,
                voieDepart,
                nomStationArrivee,
                voieArrivee,
                numRame) ;
        javax.swing.SwingUtilities.invokeLater(afficheur);
    }
    
    private class Afficheur implements Runnable
    {
        private final String nomStationDepart ; 
        private final int voieDepart ;
        private final String nomStationArrivee ; 
        private final int voieArrivee ;             
        private final String numRame ;
            
        public Afficheur (
            final String nomStationDepart, 
            final int voieDepart, 
            final String nomStationArrivee, 
            final int voieArrivee,             
            final String numRame)
        {
            this.nomStationArrivee = nomStationArrivee ;
            this.voieDepart = voieDepart ;
            this.voieArrivee = voieArrivee ;
            this.nomStationDepart = nomStationDepart ;
            this.numRame = numRame ;                                        
        }
        
        @Override
        public void run ()
        {
            // Récupération de la station en cours
            Object stationDep = MetroSuperviseurIHMImpl.this.stations.get(
                    this.nomStationDepart) ;        
            Object[] voieDep = MetroSuperviseurIHMImpl.this.voies.get(
                    this.nomStationDepart) ;
            MetroSuperviseurIHMImpl.this.modificationReseau(
                    stationDep, voieDep, this.voieDepart, "=#FF0000;", "");

            // Récupération de la station en cours
            Object stationArr = MetroSuperviseurIHMImpl.this.stations.get(
                    this.nomStationArrivee) ;        
            Object[] voieArr = MetroSuperviseurIHMImpl.this.voies.get(
                    this.nomStationArrivee) ;
            MetroSuperviseurIHMImpl.this.modificationReseau(
                    stationArr, voieArr, this.voieArrivee, "=#00FF00;", 
                    this.numRame);           
        }
    }
    
    

    private void modificationReseau(
            Object station, 
            Object[] voies, 
            final int voie,
            final String couleur,
            final String numeroRame) 
    {
        try
        {
            if (voies[voie - 1] != null)
            {
                this.graph.getModel().setStyle(voies[voie - 1], "fontColor" + couleur +
                    mxConstants.STYLE_STROKEWIDTH+"=6;" +
                    mxConstants.STYLE_STROKECOLOR + couleur) ;
            }
            String nomStationAux = "" ;
            if (station != null)
            {
                mxCell noeud = (mxCell) station ;
                String [] affichage = noeud.getValue().toString().split(">") ;
                affichage[voie] = numeroRame + "\n" ;
                for (int idx = 0 ; idx < affichage.length ; idx++)
                {
                    nomStationAux += affichage[idx] ;
                    if (idx < affichage.length - 1)
                    {
                        nomStationAux += "> " ;
                    }
                }
                noeud.setValue(nomStationAux);
            }
            graph.refresh();
        }
        catch(Exception exc)
        {
            exc.printStackTrace() ; 
        }
        finally 
        {
            this.graph.getModel().endUpdate();
        }
    }
}
