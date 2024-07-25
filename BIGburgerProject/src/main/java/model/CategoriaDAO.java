package model;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class CategoriaDAO
{
    public List<Categoria> doRetrieveAll()
    {
        ArrayList<Categoria> categorie = new ArrayList<>();
        Statement st;
        ResultSet rs;

        try (Connection con = ConPool.getConnection())
        {
            st = con.createStatement();
            rs = st.executeQuery("SELECT * FROM categoria ORDER BY nome DESC");

            while(rs.next())
            {
                Categoria c = new Categoria(rs.getString(1));
                categorie.add(c);
            }
            con.close();
            return categorie;
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }
}
