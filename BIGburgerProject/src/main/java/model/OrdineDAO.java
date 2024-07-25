package model;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class OrdineDAO
{
    public void createOrdine(int idUtente, double totale, String indirizzoSpedizione)
    {
        try (Connection con = ConPool.getConnection())
        {
            DateTimeFormatter data= DateTimeFormatter.ofPattern("dd/MM/yyyy");
            DateTimeFormatter ora= DateTimeFormatter.ofPattern("HH:mm");
            LocalDateTime now = LocalDateTime.now();

            String query = " INSERT INTO ordine (totale, dataOrdinato, oraOrdinato, stato, idUtente, indirizzoSpedizione) VALUES (?, ?, ?, ?, ?, ?)";

            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setDouble (1, totale);
            preparedStmt.setString (2, data.format(now));
            preparedStmt.setString   (3, ora.format(now));
            preparedStmt.setString(4, "Ordinato");
            preparedStmt.setInt    (5, idUtente);
            preparedStmt.setString(6, indirizzoSpedizione);

            preparedStmt.execute();
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }

    public Ordine doRetrieveLastByIdUtente(int id)
    {
        try (Connection con = ConPool.getConnection())
        {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM ordine WHERE idUtente=? ORDER BY id DESC LIMIT 1;");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next())
            {
                Ordine o= new Ordine();
                o.setId(rs.getInt(1));
                o.setTotale(rs.getDouble(2));
                o.setDataOrdinato(rs.getString(3));
                o.setOraOrdinato(rs.getString(4));
                o.setOraConsegnato(rs.getString(5));
                o.setIndirizzo(rs.getString(6));
                o.setStato(rs.getString(7));
                o.setIdUtente(rs.getInt(8));
                return o;
            }
            return null;
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Ordine> doRetrieveByIdUtente(int id)
    {
        try (Connection con = ConPool.getConnection())
        {
            ArrayList<Ordine> lista= new ArrayList<>();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM ordine WHERE idUtente=? ORDER BY id DESC");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next())
            {
                Ordine o= new Ordine();
                o.setId(rs.getInt(1));
                o.setTotale(rs.getDouble(2));
                o.setDataOrdinato(rs.getString(3));
                o.setOraOrdinato(rs.getString(4));
                o.setOraConsegnato(rs.getString(5));
                o.setIndirizzo(rs.getString(6));
                o.setStato(rs.getString(7));
                o.setIdUtente(rs.getInt(8));
                lista.add(o);
            }
            return lista;
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Ordine> doRetrieveByIdFattorinoConsegnati(int id)
    {
        try (Connection con = ConPool.getConnection())
        {
            ArrayList<Ordine> lista= new ArrayList<>();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM ordine WHERE idFattorino=? AND stato='Consegnato' ORDER BY id DESC");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next())
            {
                Ordine o= new Ordine();
                o.setId(rs.getInt(1));
                o.setTotale(rs.getDouble(2));
                o.setDataOrdinato(rs.getString(3));
                o.setOraOrdinato(rs.getString(4));
                o.setOraConsegnato(rs.getString(5));
                o.setIndirizzo(rs.getString(6));
                o.setStato(rs.getString(7));
                o.setIdUtente(rs.getInt(8));
                lista.add(o);
            }
            return lista;
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Ordine> doRetrieveByIdFattorinoInSpedizione(int id)
    {
        try (Connection con = ConPool.getConnection())
        {
            ArrayList<Ordine> lista= new ArrayList<>();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM ordine WHERE idFattorino=? AND stato='In spedizione' ORDER BY id DESC");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next())
            {
                Ordine o= new Ordine();
                o.setId(rs.getInt(1));
                o.setTotale(rs.getDouble(2));
                o.setDataOrdinato(rs.getString(3));
                o.setOraOrdinato(rs.getString(4));
                o.setOraConsegnato(rs.getString(5));
                o.setIndirizzo(rs.getString(6));
                o.setStato(rs.getString(7));
                o.setIdUtente(rs.getInt(8));
                lista.add(o);
            }
            return lista;
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }

    public Ordine doRetrieveById(int id)
    {
        try (Connection con = ConPool.getConnection())
        {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM ordine WHERE id=?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next())
            {
                Ordine o= new Ordine();
                o.setId(rs.getInt(1));
                o.setTotale(rs.getDouble(2));
                o.setDataOrdinato(rs.getString(3));
                o.setOraOrdinato(rs.getString(4));
                o.setOraConsegnato(rs.getString(5));
                o.setIndirizzo(rs.getString(6));
                o.setStato(rs.getString(7));
                o.setIdUtente(rs.getInt(8));
                o.setIdFattorino(rs.getInt(9));
                return o;
            }
            return null;
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }


    public ArrayList<Ordine> doRetrieveByData(String data)
    {
        try (Connection con = ConPool.getConnection())
        {
            ArrayList<Ordine> lista= new ArrayList<>();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM ordine WHERE dataOrdinato=? ORDER BY id DESC");
            ps.setString(1, data);
            ResultSet rs = ps.executeQuery();
            while (rs.next())
            {
                Ordine o= new Ordine();
                o.setId(rs.getInt(1));
                o.setTotale(rs.getDouble(2));
                o.setDataOrdinato(rs.getString(3));
                o.setOraOrdinato(rs.getString(4));
                o.setOraConsegnato(rs.getString(5));
                o.setIndirizzo(rs.getString(6));
                o.setStato(rs.getString(7));
                o.setIdUtente(rs.getInt(8));
                lista.add(o);
            }
            return lista;
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Ordine> doRetrieveByDataAndStato(String data, String stato)
    {
        try (Connection con = ConPool.getConnection())
        {
            ArrayList<Ordine> lista= new ArrayList<>();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM ordine WHERE dataOrdinato=? AND stato=? ORDER BY id DESC");
            ps.setString(1, data);
            ps.setString(2,stato);

            ResultSet rs = ps.executeQuery();

            while (rs.next())
            {
                Ordine o= new Ordine();
                o.setId(rs.getInt(1));
                o.setTotale(rs.getDouble(2));
                o.setDataOrdinato(rs.getString(3));
                o.setOraOrdinato(rs.getString(4));
                o.setOraConsegnato(rs.getString(5));
                o.setIndirizzo(rs.getString(6));
                o.setStato(rs.getString(7));
                o.setIdUtente(rs.getInt(8));
                lista.add(o);
            }
            return lista;
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }

    public void doUpdateStatoOrdine(int idOrdine, String stato, int idFattorino)
    {
        try (Connection con = ConPool.getConnection())
        {
            Statement st = con.createStatement();
            String query="";
            if(stato.equalsIgnoreCase("Consegnato"))
            {
                DateTimeFormatter ora= DateTimeFormatter.ofPattern("HH:mm");
                LocalDateTime now = LocalDateTime.now();
                query = "UPDATE `bigburger`.`ordine` SET `stato` = '"+stato+"',`oraConsegnato` = '"+ora.format(now)+"',`idFattorino` = '"+idFattorino+"' WHERE (`id` = '"+idOrdine+"');";
            }
            else if(stato.equalsIgnoreCase("In spedizione"))
            {
                query = "UPDATE `bigburger`.`ordine` SET `stato` = '"+stato+"',`idFattorino` = '"+idFattorino+"' WHERE (`id` = '"+idOrdine+"');";
            }
            else
            {
                query = "UPDATE `bigburger`.`ordine` SET `stato` = '"+stato+"' WHERE (`id` = '"+idOrdine+"');";
            }

            st.executeUpdate(query);
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }
}
