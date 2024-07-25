package model;

public class Commento
{
    private int id;
    private Utente utente;
    private String commento;
    private int stelle;
    private String data;

    public Commento(int id, Utente utente, String commento, int stelle, String data) {
        this.id = id;
        this.utente = utente;
        this.commento = commento;
        this.stelle = stelle;
        this.data = data;
    }

    public Commento() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Utente getUtente() {
        return utente;
    }

    public void setUtente(Utente utente) {
        this.utente = utente;
    }

    public String getCommento() {
        return commento;
    }

    public void setCommento(String commento) {
        this.commento = commento;
    }

    public int getStelle() {
        return stelle;
    }

    public void setStelle(int stelle) {
        this.stelle = stelle;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Commento{" +
                "id=" + id +
                ", utente=" + utente +
                ", commento='" + commento + '\'' +
                ", stelle=" + stelle +
                ", data='" + data + '\'' +
                '}';
    }
}
