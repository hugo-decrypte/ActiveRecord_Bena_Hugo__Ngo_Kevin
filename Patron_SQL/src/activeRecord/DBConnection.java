package activeRecord;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnection {
    private static Connection connect = null;
    private String userName;
    private String password;
    private String serverName;
   private String portNumber;
    private String tableName;
    private String dbName;


    private DBConnection(){
        this.userName = "root";
        this.password = "";
        this.serverName = "127.0.0.1";
        this.portNumber = "3306";
        this.tableName = "personne";
        this.dbName = "testpersonne";
        try {
            Properties connectionProps = new Properties();
            connectionProps.put("user", userName);
            connectionProps.put("password", password);
            String urlDB = "jdbc:mysql://" + serverName + ":";
            urlDB += portNumber + "/" + dbName;
            connect = DriverManager.getConnection(urlDB, connectionProps);
            System.out.println(urlDB);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static Connection getConnection() {
        if(connect == null){
            new DBConnection();
        }
        return connect;
    }


    /**
     *Après chaque appel de setNomDB, une nouvelle connexion devra être créée à l'appel
     * de getConnection. Ceci permet de pouvoir choisir la base dans laquelle effectuer les opérations
     * (base de test ou base de l’application)
     */

    public void setNomDB(String nomDB){
        this.dbName = nomDB;
    }

}
