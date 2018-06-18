package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jacques
 */
public class GestionDB {

    private Connection conn;
    private Statement stmt;
    private PreparedStatement pstmt;
    private ResultSet rs;
    private Employe emp;
    private ArrayList<Employe> listeEmp;

    //Attributs liés à la gestion de la base de données
    public static final String DB_URL = "jdbc:derby://localhost:1527/solutec";
    public static final String DB_USER = "solutec";
    public static final String DB_MDP = "solutec";
    private final String REQ_SELECT_TOUS = "SELECT * FROM EMPLOYES";
    private final String REQ_MAJ_EMPL_TEST = "UPDATE EMPLOYES SET NOM='Clooney', PRENOM='Georges', EMAIL='gclooney@solutec.fr' WHERE PRENOM='Bart'";
    private final String REQ_DYN_MAJ_EMPL = "UPDATE EMPLOYES SET NOM=?, PRENOM=?, EMAIL=? WHERE PRENOM=?";

    public Connection initConnexion(String urlBase, String userBase, String mdpBase) {
        try {
            if (conn == null) {
                conn = DriverManager.getConnection(urlBase, userBase, mdpBase);
            }
        } catch (SQLException ex) {
            Logger.getLogger(GestionDB.class.getName()).log(Level.SEVERE, ex.toString(), ex);
        }

        return conn;
    }

    public void fermerConnexion(Connection conn) {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(GestionDB.class.getName()).log(Level.SEVERE, ex.toString(), ex);
        }
    }

    public void fermerStatement(Statement stmt) {
        try {
            if (stmt != null) {
                stmt.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(GestionDB.class.getName()).log(Level.SEVERE, ex.toString(), ex);
        }
    }

    public void fermerResultSet(ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(GestionDB.class.getName()).log(Level.SEVERE, ex.toString(), ex);
        }
    }

    public ArrayList<Employe> recupererEmployes(Connection conn) {
        listeEmp = new ArrayList<>();
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(REQ_SELECT_TOUS);

            while (rs.next()) {
                emp = new Employe();
                emp.setNom(rs.getString("NOM"));
                emp.setPrenom(rs.getString("PRENOM"));
                emp.setId(rs.getInt("ID"));
                emp.setAdresse(rs.getString("ADRESSE"));
                emp.setCodepostal(rs.getString("CODEPOSTAL"));
                emp.setTeldom(rs.getString("TELDOM"));
                emp.setTelport(rs.getString("TELPORT"));
                emp.setTelpro(rs.getString("TELPRO"));
                emp.setVille(rs.getString("VILLE"));
                emp.setEmail(rs.getString("EMAIL"));
                listeEmp.add(emp);
            }
        } catch (SQLException ex) {
            Logger.getLogger(GestionDB.class.getName()).log(Level.SEVERE, ex.toString(), ex);
        }

        return listeEmp;
    }

    public void afficherEmployes(ArrayList<Employe> listeEmp) {
        if (listeEmp != null) {
            for (int i = 0; i < listeEmp.size(); i++) {
                System.out.println("Employé : #" + (i + 1));
                System.out.println("Nom     : " + listeEmp.get(i).getNom());
                System.out.println("Prenom  : " + listeEmp.get(i).getPrenom());
                System.out.println("Email    : " + listeEmp.get(i).getEmail());
                System.out.println("Ville   : " + listeEmp.get(i).getVille());
                System.out.println("____________________");
            }
        }

    }

    public void mettreAJourEmploye(Connection conn) {
        try {
            Scanner s = new Scanner(System.in);

            System.out.println("Veuillez saisir le prénom de l'employé à mofidier :");
            String clePrenom = s.nextLine();
            System.out.println("Veuillez saisir le nouveau nom :");
            String nouvNom = s.nextLine();
            System.out.println("Veuillez saisir le nouveau prénom :");
            String nouvPrenom = s.nextLine();
            System.out.println("Veuillez saisir la nouvelle adresse mail :");
            String nouvEmail = s.nextLine();

            if (conn != null) {
                pstmt = conn.prepareStatement(REQ_DYN_MAJ_EMPL);
                pstmt.setString(1, nouvNom);
                pstmt.setString(2, nouvPrenom);
                pstmt.setString(3, nouvEmail);
                pstmt.setString(4, clePrenom);
                pstmt.executeUpdate();
            }
            // Mise à jour "en dur"
            /*    
            stmt = conn.createStatement();
            stmt.executeUpdate(REQ_MAJ_EMPL_TEST);
             */

        } catch (SQLException ex) {
            Logger.getLogger(GestionDB.class.getName()).log(Level.SEVERE, ex.toString(), ex);
        }

    }
}
