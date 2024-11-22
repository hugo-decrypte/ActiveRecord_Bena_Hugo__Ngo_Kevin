package activeRecord;

import org.junit.Test;
import static org.junit.Assert.*;
import java.sql.Connection;

public class TestConnection {

    @Test
    public void testConnection() {
        Connection conn1 = DBConnection.getConnection();
        Connection conn2 = DBConnection.getConnection();

        // On verifie que les connections ne sont pas null
        assertNotNull("La première connexion devrait être différent de null", conn1);
        assertNotNull("La deuxième connexion devrait être différent de null", conn2);

        // Vérifie que c'est la même instance
        assertSame("Les connexions devraient être la même instance", conn1, conn2);

        // Vérifie le type de la connexion
        assertTrue("La connexion devrait être de type java.sql.Connection", conn1 instanceof java.sql.Connection);

    }

    @Test
    public void testChangementBaseDeDonnees() {
        //premiere connection
        Connection conn1 = DBConnection.getConnection();

        // On change de base
        DBConnection.setNomDB("testetudiant");

        //deuxieme connection
        Connection conn2 = DBConnection.getConnection();

        // Vérifie que les connexions ne sont pas null
        assertNotNull("La première connexion ne doit pas etre null", conn1);
        assertNotNull("La deuxieme connexion ne doit pas être  null", conn2);

        // instances différentes apres changmeent de base
        assertNotSame("Les connexions doivent être différentes", conn1, conn2);
    }
}