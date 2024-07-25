package model;

import java.util.ArrayList;

public class Carrello
{
    private ArrayList<ElementoCarrello> elementi;
    private double totale;

    public Carrello() {
    }

    public Carrello(ArrayList<ElementoCarrello> elementi, double totale) {
        this.elementi = elementi;
        this.totale = totale;
    }

    public ArrayList<ElementoCarrello> getElementi() {
        return elementi;
    }

    public void setElementi(ArrayList<ElementoCarrello> elementi) {
        this.elementi = elementi;
    }

    public double getTotale() {
        return totale;
    }

    public void setTotale(double totale) {
        this.totale = totale;
    }

    public void aggiungiAlCarrello(ElementoCarrello e)
    {
        elementi.add(e);
        totale= totale+(e.getQuantita()*e.getProdotto().getPrezzo());
    }

    public void eliminaDalCarrello(Prodotto p)
    {
        int i=0;
        for(ElementoCarrello el : elementi)
        {
            Prodotto elP= el.getProdotto();
            if(elP.getId()==p.getId())
            {
                elementi.remove(i);
                totale= totale-(el.getQuantita()*elP.getPrezzo());
                break;
            }
            i++;
        }
    }

    public void modificaDalCarrello(ElementoCarrello e, int quantita)
    {

    }
}
