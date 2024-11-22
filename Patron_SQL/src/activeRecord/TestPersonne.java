package activeRecord;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import java.sql.SQLException;

public class TestPersonne {



    @BeforeEach
    public void setup() throws SQLException {
        Personne.createTable();

        Personne personne1 = new Personne("Spielberg", "Steven");
        personne1.save();

        Personne personne2 = new Personne("Ridley", "Scott");
        personne1.save();

        Personne personne3 = new Personne("Stanley", "Kubrick");
        personne1.save();

        Personne personne4 = new Personne("Fincher", "David");
        personne1.save();

        Personne personne5 = new Personne("Gedagedi", "Pipotam");
        personne1.save();




    }

    //------------Corps de tests-------------







    //---------------------------------------


    @AfterEach
    public void end() throws SQLException{
        Personne.deleteTable();
    }
}
