package model;

import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;

public class ElementoCarrelloDAO
{
    public void salvaElementoCarrello(ElementoCarrello el)
    {
        try (Connection con = ConPool.getConnection())
        {
            String query = " INSERT INTO carrello (idUtente, idProdotto, quantita) VALUES (?, ?, ?)";

            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setInt (1, el.getIdUtente());
            preparedStmt.setInt (2, el.getIdProdotto());
            preparedStmt.setInt(3, el.getQuantita());

            preparedStmt.execute();
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<ElementoCarrello> doRetrieveByIdUtenteSenzaIdOrdine(int idUtente)
    {
        ArrayList<ElementoCarrello> carrello = new ArrayList<>();
        Statement st;
        ResultSet rs;

        try (Connection con = ConPool.getConnection())
        {
            st = con.createStatement();
            rs = st.executeQuery("SELECT * FROM carrello WHERE idUtente='"+idUtente+"' AND idOrdine IS NULL;");

            while(rs.next())
            {
                ProdottoDAO prodottoDAO= new ProdottoDAO();
                Prodotto p= prodottoDAO.doRetrieveById(rs.getInt(3));
                ElementoCarrello el = new ElementoCarrello(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4), rs.getInt(5), p);
                carrello.add(el);
            }
            con.close();
            return carrello;
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<ElementoCarrello> doRetrieveByIdOrdine(int idOrdine)
    {
        ArrayList<ElementoCarrello> carrello = new ArrayList<>();
        Statement st;
        ResultSet rs;

        try (Connection con = ConPool.getConnection())
        {
            st = con.createStatement();
            rs = st.executeQuery("SELECT * FROM carrello WHERE idOrdine='"+idOrdine+"';");

            while(rs.next())
            {
                ProdottoDAO prodottoDAO= new ProdottoDAO();
                Prodotto p= prodottoDAO.doRetrieveById(rs.getInt(3));
                ElementoCarrello el = new ElementoCarrello(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4), rs.getInt(5), p);
                carrello.add(el);
            }
            con.close();
            return carrello;
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }

    public void ordinaElementoDelCarrello(int idElementoCarrello, int idOrdine)
    {
        try (Connection con = ConPool.getConnection())
        {
            Statement st = con.createStatement();
            String query = "UPDATE `bigburger`.`carrello` SET `idOrdine` = '"+idOrdine+"' WHERE (`id` = '"+idElementoCarrello+"');";
            st.executeUpdate(query);
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }

    public void doUpdateQuantita(ElementoCarrello el)
    {
        try (Connection con = ConPool.getConnection())
        {
            Statement st = con.createStatement();
            String query = "UPDATE `bigburger`.`carrello` SET `quantita` = '"+el.getQuantita()+"' WHERE (`id` = '"+el.getId()+"');";
            st.executeUpdate(query);
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }

    public void doDropElementoById(ElementoCarrello el)
    {
        try (Connection con = ConPool.getConnection())
        {
            String query = "DELETE FROM carrello where id = ?";
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setInt(1, el.getId());

            preparedStmt.execute();
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }
}
