package model;

public class ElementoCarrello
{
    private int id;
    private int idUtente;
    private int idProdotto;
    private int idOrdine;
    private int quantita;
    private Prodotto prodotto;

    public ElementoCarrello()
    {
    }

    public ElementoCarrello(int id, int idUtente, int idProdotto, int idOrdine, int quantita, Prodotto prodotto) {
        this.id = id;
        this.idUtente = idUtente;
        this.idProdotto = idProdotto;
        this.idOrdine = idOrdine;
        this.quantita = quantita;
        this.prodotto = prodotto;
    }

    public ElementoCarrello(int id, int idUtente, int idProdotto, int idOrdine, int quantita) {
        this.id = id;
        this.idUtente = idUtente;
        this.idProdotto = idProdotto;
        this.idOrdine = idOrdine;
        this.quantita = quantita;
        this.prodotto = prodotto;
    }

    public ElementoCarrello(int id, int idUtente, int idProdotto, int quantita, Prodotto prodotto)
    {
        this.id = id;
        this.idUtente = idUtente;
        this.idProdotto = idProdotto;
        this.idOrdine = 0;
        this.quantita = quantita;
        this.prodotto = prodotto;
    }

    public ElementoCarrello(int idUtente, int idProdotto, int quantita, Prodotto prodotto)
    {
        this.idUtente = idUtente;
        this.idProdotto = idProdotto;
        this.idOrdine = 0;
        this.quantita = quantita;
        this.prodotto = prodotto;
    }

    public ElementoCarrello(int id, int idUtente, int idProdotto, int quantita)
    {
        this.id = id;
        this.idUtente = idUtente;
        this.idProdotto = idProdotto;
        this.idOrdine = Integer.parseInt(null);
        this.quantita = quantita;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdUtente() {
        return idUtente;
    }

    public void setIdUtente(int idUtente) {
        this.idUtente = idUtente;
    }

    public int getIdProdotto() {
        return idProdotto;
    }

    public void setIdProdotto(int idProdotto) {
        this.idProdotto = idProdotto;
    }

    public int getQuantita() {
        return quantita;
    }

    public void setQuantita(int quantita) {
        this.quantita = quantita;
    }

    public Prodotto getProdotto() {return prodotto;}

    public void setProdotto(Prodotto prodotto) { this.prodotto = prodotto; }

    public int getIdOrdine() { return idOrdine; }

    public void setIdOrdine(int idOrdine) { this.idOrdine = idOrdine; }

}
