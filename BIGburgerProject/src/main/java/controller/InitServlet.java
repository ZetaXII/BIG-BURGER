package controller;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.*;

import java.util.List;

@WebServlet(name="MyInit", urlPatterns="/MyInit", loadOnStartup=0)
public class InitServlet extends HttpServlet
{
    @Override
    public void init() throws ServletException
    {
        ProdottoDAO prodottoDAO = new ProdottoDAO();
        CategoriaDAO categoriaDAO= new CategoriaDAO();
        CommentoDAO commentoDAO= new CommentoDAO();
        List<Prodotto> prodotti= prodottoDAO.doRetrieveFirstSix();
        List<Categoria> categorie= categoriaDAO.doRetrieveAll();
        List<Commento> commenti= commentoDAO.doRetrieveFirstThree();

        getServletContext().setAttribute("prodotti", prodotti);
        getServletContext().setAttribute("categorie", categorie);
        getServletContext().setAttribute("commenti", commenti);

        super.init();
    }

    public void destroy()
    {
        System.out.println(".........destroy method invoked.......");
    }
}