package model;

import java.sql.*;
import java.util.ArrayList;

public class UtenteDAO
{
    public Utente doRetrieveByEmailPassword(String email, String psw)
    {
        try (Connection con = ConPool.getConnection())
        {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM utente WHERE email=? AND psw=?");
            ps.setString(1, email);
            ps.setString(2, psw);
            ResultSet rs = ps.executeQuery();
            if (rs.next())
            {
                Utente p = new Utente();
                p.setId(rs.getInt(1));
                p.setNome(rs.getString(2));
                p.setCognome(rs.getString(3));
                p.setTelefono(rs.getString(4));
                p.setEmail(rs.getString(5));
                p.setPsw(rs.getString(6));
                p.setRuolo(rs.getString(7));
                return p;
            }
            return null;
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }

    public Utente doRetrieveById(int id)
    {
        try (Connection con = ConPool.getConnection())
        {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM utente WHERE id=?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next())
            {
                Utente p = new Utente();
                p.setId(rs.getInt(1));
                p.setNome(rs.getString(2));
                p.setCognome(rs.getString(3));
                p.setTelefono(rs.getString(4));
                p.setEmail(rs.getString(5));
                p.setPsw(rs.getString(6));
                p.setRuolo(rs.getString(7));
                return p;
            }
            return null;
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }

    public Utente doRetrieveFattorinoByIdOrdine(int idOrdine, int idFattorino)
    {
        try (Connection con = ConPool.getConnection())
        {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM bigburger.utente INNER JOIN ordine ON utente.id=? WHERE ordine.id=?;");
            ps.setInt(1, idFattorino);
            ps.setInt(2, idOrdine);
            ResultSet rs = ps.executeQuery();
            if (rs.next())
            {
                Utente p = new Utente();
                p.setId(rs.getInt(1));
                p.setNome(rs.getString(2));
                p.setCognome(rs.getString(3));
                p.setTelefono(rs.getString(4));
                p.setEmail(rs.getString(5));
                p.setRuolo(rs.getString(7));
                return p;
            }
            return null;
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Utente> doRetrieveAll(int idUtenteSessione)
    {
        try (Connection con = ConPool.getConnection())
        {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM utente WHERE id!=?");

            ps.setInt(1, idUtenteSessione);

            ResultSet rs = ps.executeQuery();
            ArrayList<Utente> utenti= new ArrayList<>();
            while (rs.next())
            {
                Utente p = new Utente();
                p.setId(rs.getInt(1));
                p.setNome(rs.getString(2));
                p.setCognome(rs.getString(3));
                p.setTelefono(rs.getString(4));
                p.setEmail(rs.getString(5));
                p.setPsw(rs.getString(6));
                p.setRuolo(rs.getString(7));
                utenti.add(p);
            }
            return utenti;
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Utente> doRetrieveAllByRuolo(String ruolo, int idUtenteSessione)
    {
        try (Connection con = ConPool.getConnection())
        {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM utente WHERE ruolo=? AND id!=?");

            ps.setString(1, ruolo);
            ps.setInt(2, idUtenteSessione);

            ResultSet rs = ps.executeQuery();
            ArrayList<Utente> utenti= new ArrayList<>();
            while (rs.next())
            {
                Utente p = new Utente();
                p.setId(rs.getInt(1));
                p.setNome(rs.getString(2));
                p.setCognome(rs.getString(3));
                p.setTelefono(rs.getString(4));
                p.setEmail(rs.getString(5));
                p.setPsw(rs.getString(6));
                p.setRuolo(rs.getString(7));
                utenti.add(p);
            }
            return utenti;
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }

    public void doUpdateUtente(Utente u)
    {
        try (Connection con = ConPool.getConnection())
        {
            Statement st = con.createStatement();
            String query = "UPDATE `bigburger`.`utente` SET `nome` = '"+u.getNome()+"', `cognome` = '"+u.getCognome()+"', `telefono` = '"+u.getTelefono()+"', `email` = '"+u.getEmail()+"', `psw` = '"+u.getPsw()+"', `ruolo` = '"+u.getRuolo()+"' WHERE (`id` = '"+u.getId()+"');";
            st.executeUpdate(query);
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }

    public boolean doCheckEmail(String email)
    {
        try (Connection con = ConPool.getConnection())
        {
            PreparedStatement ps = con.prepareStatement("SELECT COUNT(*) FROM utente WHERE email=?");
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if (rs.next())
            {
                int check=rs.getInt(1);
                if(check==0)
                {
                    return true;
                }
                else
                {
                    return false;
                }
            }
            return false;
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }

    public boolean doCheckEmail(int id, String email)
    {
        try (Connection con = ConPool.getConnection())
        {
            PreparedStatement ps = con.prepareStatement("SELECT COUNT(*) FROM utente WHERE email=? AND id!=?");
            ps.setString(1, email);
            ps.setInt(2, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next())
            {
                int check=rs.getInt(1);
                if(check==0)
                {
                    return true;
                }
                else
                {
                    return false;
                }
            }
            return false;
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }

    public void registraUtente(Utente u)
    {
        try (Connection con = ConPool.getConnection())
        {
            String query = " INSERT INTO `bigburger`.`utente` (`nome`, `cognome`, `telefono`, `email`, `psw`, `ruolo`) VALUES (?, ?, ?, ?, ?, ?);";

            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setString (1, u.getNome());
            preparedStmt.setString (2, u.getCognome());
            preparedStmt.setString (3, u.getTelefono());
            preparedStmt.setString (4, u.getEmail());
            preparedStmt.setString (5, u.getPsw());
            preparedStmt.setString (6, u.getRuolo());

            preparedStmt.execute();
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }


}
