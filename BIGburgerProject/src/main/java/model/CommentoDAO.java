package model;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class CommentoDAO
{

    public Commento doRetrieveById(int id)
    {
        try (Connection con = ConPool.getConnection())
        {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM commento WHERE id=?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next())
            {
                UtenteDAO utenteDAO= new UtenteDAO();
                Commento c= new Commento();
                c.setId(rs.getInt(1));
                c.setUtente(utenteDAO.doRetrieveById(rs.getInt(2)));
                c.setCommento(rs.getString(3));
                c.setStelle(rs.getInt(4));
                c.setData(rs.getString(5));
                return c;
            }
            return null;
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }

    public Commento doRetrieveByIdUtente(int id)
    {
        try (Connection con = ConPool.getConnection())
        {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM commento WHERE utente=?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next())
            {
                UtenteDAO utenteDAO= new UtenteDAO();
                Commento c= new Commento();
                c.setId(rs.getInt(1));
                c.setUtente(utenteDAO.doRetrieveById(rs.getInt(2)));
                c.setCommento(rs.getString(3));
                c.setStelle(rs.getInt(4));
                c.setData(rs.getString(5));
                return c;
            }
            return null;
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Commento> doRetrieveAll()
    {
        try (Connection con = ConPool.getConnection())
        {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM commento ORDER BY id DESC");
            ResultSet rs = ps.executeQuery();
            UtenteDAO utenteDAO= new UtenteDAO();
            ArrayList<Commento> commenti= new ArrayList<>();
            while (rs.next())
            {
                Commento c= new Commento();
                c.setId(rs.getInt(1));
                c.setUtente(utenteDAO.doRetrieveById(rs.getInt(2)));
                c.setCommento(rs.getString(3));
                c.setStelle(rs.getInt(4));
                c.setData(rs.getString(5));
                commenti.add(c);
            }
            con.close();
            return commenti;
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }

    public List<Commento> doRetrieveFirstThree()
    {
        try (Connection con = ConPool.getConnection())
        {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM commento ORDER BY id DESC LIMIT 3");
            ResultSet rs = ps.executeQuery();
            UtenteDAO utenteDAO= new UtenteDAO();
            ArrayList<Commento> commenti= new ArrayList<>();
            while (rs.next())
            {
                Commento c= new Commento();
                c.setId(rs.getInt(1));
                c.setUtente(utenteDAO.doRetrieveById(rs.getInt(2)));
                c.setCommento(rs.getString(3));
                c.setStelle(rs.getInt(4));
                c.setData(rs.getString(5));
                commenti.add(c);
            }
            con.close();
            return commenti;
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }

    public int[] doRetrieveTotalePerStella()
    {
        int totale[] = new int[5];
        try (Connection con = ConPool.getConnection())
        {
            PreparedStatement ps = con.prepareStatement("SELECT COUNT(*) FROM commento WHERE stelle=?");
            for(int i=1; i<6; i++)
            {
                ps.setInt(1, i);
                ResultSet rs = ps.executeQuery();
                if (rs.next())
                {
                    totale[i-1]=rs.getInt(1);
                }
            }
            return totale;
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }

    public void doInsertCommento(Commento c)
    {
        try (Connection con = ConPool.getConnection())
        {
            DateTimeFormatter data= DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDateTime now = LocalDateTime.now();

            String query = " INSERT INTO commento (utente,commento,stelle,data) VALUES (?, ?, ?, ?)";

            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setInt (1, c.getUtente().getId());
            preparedStmt.setString (2, c.getCommento());
            preparedStmt.setInt (3, c.getStelle());
            preparedStmt.setString(4, data.format(now));

            preparedStmt.execute();
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }

    public void doUpdateCommento(Commento c)
    {
        try (Connection con = ConPool.getConnection())
        {
            DateTimeFormatter data= DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDateTime now = LocalDateTime.now();
            Statement st = con.createStatement();
            String query = "UPDATE `bigburger`.`commento` SET `utente` = '"+c.getUtente().getId()+"', `commento` = ?, `stelle` = '"+c.getStelle()+"', `data` = '"+data.format(now)+"' WHERE (`utente` = '"+c.getUtente().getId()+"');";
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setString (1, c.getCommento());

            preparedStmt.execute();
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }

    public void doDeleteCommento(int idUtente)
    {
        try (Connection con = ConPool.getConnection())
        {
            String query = "DELETE FROM `bigburger`.`commento` WHERE utente = ?";
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setInt(1, idUtente);

            preparedStmt.execute();
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }
}
