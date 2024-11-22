package activeRecord;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Personne {
    private int id;
    private String nom;
    private String prenom;

    public Personne(String nom, String prenom) {
        this.id = -1;
        this.nom = nom;
        this.prenom = prenom;
    }

    public int getId() {
        return id;
    }

    /*
     * 7.1. Écrire une méthode findAll chargée de retourner l'ensemble des tuples de la table personne
     * sous la forme d'objets.
     */
    public static List<Personne> findAll() throws SQLException {
        // Creation d'une liste de Personne et d'une connection
        List<Personne> listepersonne = new ArrayList<>();
        Connection c = DBConnection.getConnection();

        //Preparation de la requete SQL pour avoir toutes les personnes de la tables
        PreparedStatement preparedStatement = c.prepareStatement("SELECT * FROM personne");
        ResultSet resultat = preparedStatement.executeQuery();


        //Création et ajout d'objets Personne à la liste en fonction des données dans la bd
        while(resultat.next()){
            Personne personne = new Personne(resultat.getString("nom"), resultat.getString("prenom"));
            personne.id = resultat.getInt("id");
            listepersonne.add(personne);
        }

        return listepersonne;
    }




    /*
     * 7.2. Écrire la méthode findById qui retourne l'objet Personne correspondant au tuple avec l'id
     * passé en paramètre (null si l'objet n'existe pas).
     */
    public static Personne findById(int i) throws SQLException{
        Personne res = null;
        Connection c = DBConnection.getConnection();
        PreparedStatement prep = c.prepareStatement("SELECT * FROM Personne WHERE id = ?;");
        prep.setInt(1, i);
        prep.execute();
        ResultSet resultat = prep.getResultSet();
        if(resultat.next()) {
            res = new Personne(resultat.getString("nom"), resultat.getString("prenom"));
            res.id = i;
        }
        return res;
    }



     /*
     * 7.3. Écrire une méthode findByName qui retourne la liste des objets Personne correspondant aux
     * tuples dont le nom est passé en paramètre.
     *
     */
    public static List<Personne> findByName(String nom) throws SQLException{
        List<Personne> res = new ArrayList<>();
        Connection c = DBConnection.getConnection();
        PreparedStatement prep = c.prepareStatement("SELECT * FROM Personne WHERE nom = ?;");
        prep.setString(1, nom);
        prep.executeQuery();
        ResultSet resultat = prep.getResultSet();
        while(resultat.next()){
            Personne personne = new Personne(resultat.getString("nom"), resultat.getString("prenom"));
            personne.id = resultat.getInt("id");
            res.add(personne);
        }
        return res;
    }



     /*
     * 7.4. Réfléchir à la manière dont la gestion de la connexion a permis de simplifier l’accès à la base
      * de données.
     *
     *
     */


    public static void createTable() throws SQLException {
        Connection c = DBConnection.getConnection();
        PreparedStatement prep = c.prepareStatement("CREATE TABLE personne (\n" +
                "  `id` int(11) NOT NULL,\n" +
                "  `nom` varchar(40) NOT NULL,\n" +
                "  `prenom` varchar(40) NOT NULL\n");

        prep.execute();

    }

    public static void deleteTable() throws SQLException{
        Connection c = DBConnection.getConnection();
        PreparedStatement prep = c.prepareStatement("DROP TABLE Personne;");
        prep.execute();
    }

    public void delete() throws SQLException{
        Connection c = DBConnection.getConnection();
        PreparedStatement prep = c.prepareStatement("DELETE FROM Personne WHERE id = ? ;");
        prep.setInt(1, this.id);
        prep.execute();
        this.id = -1;
    }





    public void save() throws SQLException {
        Connection c = DBConnection.getConnection();

        // Cas d'ajout dans la table
        if (this.id == -1) {
            saveNew(c);
        }
        //Cas d'update dans la table
        else{
            update(c);
        }
    }

    private  void saveNew(Connection c) throws SQLException {
        String SQLPrep = "INSERT INTO Personne (nom, prenom) VALUES (?,?);";
        PreparedStatement prep;

        //l'option RETURN_GENERATED_KEYS permet de recuperer l'id
        prep = c.prepareStatement(SQLPrep, Statement.RETURN_GENERATED_KEYS);
        prep.setString(1, this.nom);
        prep.setString(2, this.prenom);
        prep.executeUpdate();

        //Recup la clé générée
        ResultSet rs = prep.getGeneratedKeys();
        if (rs.next()) {
            this.id = rs.getInt(1);
        }

    }


    private void update(Connection c) throws SQLException {

        String SQLprep = "update Personne set nom=?, prenom=? where id=?;";
        PreparedStatement prep;
        prep = c.prepareStatement(SQLprep);
        prep.setString(1, this.nom);
        prep.setString(2, this.prenom);
        prep.setInt(3, this.id);
        prep.executeUpdate();

        }
    










}