package activeRecord;


//3.1. En discutant avec votre enseignant, mettez en oeuvre le patron Singleton (couplé à Factory)
//pour limiter la connexion à une instance. La classe correspondante doit s'appeler DBConnection,
//doit disposer d'une méthode getConnection() et doit encapsuler dans des attributs les paramètres
//de connexion. Modifier la méthode main pour utiliser le patron proposé.
public class DBConnection {

    private DBConnection dbconnection;

    private static DBConnection(){

    }


    public DBConnection getInstance() {
        return dbconnection;
    }
}
