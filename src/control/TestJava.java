/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.sql.Connection;
import java.util.ArrayList;
import model.Employe;
import model.GestionDB;



/**
 *
 * @author Jacques
 */
public class TestJava {
    
    /**
     * Point d'entrée de l'application
     * @param args arguments de la ligne de commande (console)
     */
    public static void main(String[] args) {
        
        GestionDB gdb = new GestionDB();
        ArrayList<Employe> listeEmp;
        Connection conn = gdb.initConnexion(GestionDB.DB_URL,GestionDB.DB_USER , GestionDB.DB_MDP );
        
        System.out.println("AVANT MAJ");
        listeEmp = gdb.recupererEmployes(conn);
        gdb.afficherEmployes(listeEmp);
        
        
        System.out.println("APRES MAJ");
        gdb.mettreAJourEmploye(conn);
        listeEmp.clear();  // Reset de la liste
        listeEmp = gdb.recupererEmployes(conn);
        gdb.afficherEmployes(listeEmp);
                
        // On n'oublie pas de fermer la connexion pour libérer la mémoire
        gdb.fermerConnexion(conn);
    }
}
