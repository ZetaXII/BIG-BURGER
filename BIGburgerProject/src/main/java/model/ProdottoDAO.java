package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProdottoDAO
{
    public List<Prodotto> doRetrieveAll()
    {
        ArrayList<Prodotto> prodotti = new ArrayList<>();
        Statement st;
        ResultSet rs;

        try (Connection con = ConPool.getConnection())
        {
            st = con.createStatement();
            rs = st.executeQuery("SELECT * FROM prodotto ORDER BY categoria DESC , prezzo ASC;");

            while(rs.next())
            {
                Prodotto p = new Prodotto(rs.getInt(1), rs.getString(2), rs.getString(3).substring(0, 1).toUpperCase() + rs.getString(3).substring(1), rs.getDouble(4), rs.getString(5), rs.getString(6));
                prodotti.add(p);
            }
            con.close();
            return prodotti;
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }

    public List<Prodotto> doRetrieveFirstSix()
    {
        ArrayList<Prodotto> prodotti = new ArrayList<>();
        Statement st;
        ResultSet rs;

        try (Connection con = ConPool.getConnection())
        {
            st = con.createStatement();
            rs = st.executeQuery("SELECT * FROM prodotto order by id desc limit 6");

            while(rs.next())
            {
                Prodotto p = new Prodotto(rs.getInt(1), rs.getString(2), rs.getString(3).substring(0, 1).toUpperCase() + rs.getString(3).substring(1), rs.getDouble(4), rs.getString(5), rs.getString(6));
                prodotti.add(p);
            }
            con.close();
            return prodotti;
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }

    public Prodotto doRetrieveById(int id)
    {
        Statement st;
        ResultSet rs;
        Prodotto p = null;

        try (Connection con = ConPool.getConnection())
        {
            st = con.createStatement();
            rs = st.executeQuery("SELECT * FROM prodotto WHERE id='"+id+"';");

            if(rs.next())
            {
                p = new Prodotto(rs.getInt(1), rs.getString(2), rs.getString(3).trim(), rs.getDouble(4), rs.getString(5), rs.getString(6));
                return p;
            }
            con.close();
            return null;
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Prodotto> doRetrieveByCategoria(String categoria)
    {
        ArrayList<Prodotto> prodotti = new ArrayList<>();
        Statement st;
        ResultSet rs;

        try (Connection con = ConPool.getConnection())
        {
            st = con.createStatement();
            rs = st.executeQuery("SELECT * FROM prodotto WHERE categoria='"+categoria+"';");

            while(rs.next())
            {
                Prodotto p = new Prodotto(rs.getInt(1), rs.getString(2), rs.getString(3).substring(0, 1).toUpperCase() + rs.getString(3).substring(1), rs.getDouble(4), rs.getString(5), rs.getString(6));
                prodotti.add(p);
            }
            con.close();
            return prodotti;
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }

    public List<Prodotto> doRetrieveByIdOrdine()
    {
        ArrayList<Prodotto> prodotti = new ArrayList<>();
        Statement st;
        ResultSet rs;

        try (Connection con = ConPool.getConnection())
        {
            st = con.createStatement();
            rs = st.executeQuery("SELECT prodotto.id, prodotto.nome FROM prodotto INNER JOIN carrello ON prodotto.id=idProdotto INNER JOIN ordine ON idOrdine=ordine.id WHERE ordine.idUtente=? AND idOrdine=?;");

            while(rs.next())
            {
                Prodotto p = new Prodotto(rs.getInt(1), rs.getString(2), rs.getString(3).substring(0, 1).toUpperCase() + rs.getString(3).substring(1), rs.getDouble(4), rs.getString(5), rs.getString(6));
                prodotti.add(p);
            }
            con.close();
            return prodotti;
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }

    public void doUpdateProdotto(Prodotto p)
    {
        try (Connection con = ConPool.getConnection())
        {
            String query = "UPDATE `bigburger`.`prodotto` SET `nome` = ?, `descrizione` = ?, `prezzo` = ?, `categoria` = ?, `immagine` = ? WHERE (`id` = ?);";

            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setString (1, p.getNome());
            preparedStmt.setString (2, p.getDescrizione());
            preparedStmt.setDouble(3, p.getPrezzo());
            preparedStmt.setString (4, p.getCategoria());
            preparedStmt.setString (5, p.getImmagine());
            preparedStmt.setInt (6, p.getId());

            preparedStmt.execute();

        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }

    public void doAddProdotto(Prodotto p)
    {
        try (Connection con = ConPool.getConnection())
        {
            String query = " INSERT INTO prodotto (nome, descrizione, prezzo, categoria, immagine) VALUES (?, ?, ?, ?, ?)";

            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setString (1, p.getNome());
            preparedStmt.setString (2, p.getDescrizione());
            preparedStmt.setDouble(3, p.getPrezzo());
            preparedStmt.setString (4, p.getCategoria());
            preparedStmt.setString (5, p.getImmagine());

            preparedStmt.execute();
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }

    public void doDeleteProdotto(int idProdotto)
    {
        try (Connection con = ConPool.getConnection())
        {
            Statement st = con.createStatement();
            String query = "DELETE FROM `bigburger`.`carrello` WHERE (`idProdotto` = '"+idProdotto+"');";
            st.executeUpdate(query);
            query = "DELETE FROM `bigburger`.`prodotto` WHERE (`id` = '"+idProdotto+"');";
            st.executeUpdate(query);
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }
}
