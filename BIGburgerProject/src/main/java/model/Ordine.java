package model;

public class Ordine
{
    private int id;
    private double totale;
    private String dataOrdinato;
    private String oraOrdinato;
    private String oraConsegnato;
    private String stato;
    private String indirizzo;
    private int idUtente;
    private int idFattorino;

    public Ordine()
    {

    }

    public Ordine(int id, double totale, String dataOrdinato, String oraOrdinato, String oraConsegnato, String stato, String indirizzo, int idUtente, int idFattorino)
    {
        this.id = id;
        this.totale = totale;
        this.dataOrdinato = dataOrdinato;
        this.oraOrdinato = oraOrdinato;
        this.oraConsegnato = oraConsegnato;
        this.stato = stato;
        this.indirizzo = indirizzo;
        this.idUtente = idUtente;
        this.idFattorino = idFattorino;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getTotale() {
        return totale;
    }

    public void setTotale(double totale) {
        this.totale = totale;
    }

    public String getDataOrdinato() {
        return dataOrdinato;
    }

    public void setDataOrdinato(String dataOrdinato) {
        this.dataOrdinato = dataOrdinato;
    }

    public String getOraOrdinato() {
        return oraOrdinato;
    }

    public void setOraOrdinato(String oraOrdinato) {
        this.oraOrdinato = oraOrdinato;
    }

    public String getOraConsegnato() {
        return oraConsegnato;
    }

    public void setOraConsegnato(String oraConsegnato) {
        this.oraConsegnato = oraConsegnato;
    }

    public String getStato() {
        return stato;
    }

    public void setStato(String stato) {
        this.stato = stato;
    }

    public int getIdUtente() {
        return idUtente;
    }

    public void setIdUtente(int idUtente) {
        this.idUtente = idUtente;
    }

    public int getIdFattorino() {
        return idFattorino;
    }

    public void setIdFattorino(int idFattorino) {
        this.idFattorino = idFattorino;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }
}
