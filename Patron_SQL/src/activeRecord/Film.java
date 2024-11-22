package activeRecord;

public class Film {
    private String titre;
    private int id, id_real;

    public Film(String t, Personne p){
        this.id = -1;
        this.titre = t;
        this.id_real = p.getId();
    }

    private Film(String t, int id, int id_real){
        this.titre = t;
        this.id = id;
        this.id_real = id_real;
    }
}
