package controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.Commento;
import model.CommentoDAO;
import model.Utente;

import java.io.IOException;

@WebServlet(name = "recuperaCommentoServlet", value = "/recuperaCommentoServlet")
public class RecuperaCommentoServlet extends HttpServlet
{
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String indirizzo="/WEB-INF/results/commenta.jsp";
        HttpSession session= request.getSession();
        Utente u= (Utente) session.getAttribute("utente");
        if(u!=null && u.getRuolo().equalsIgnoreCase("u"))
        {
            int check=1;
            CommentoDAO commentoDAO= new CommentoDAO();
            Commento c= commentoDAO.doRetrieveByIdUtente(u.getId());
            if(c==null)
            {
                c= new Commento();
                c.setCommento("");
                c.setStelle(0);
                check=0;
            }
            request.setAttribute("descrizione", c.getCommento());
            request.setAttribute("star", c.getStelle());
            request.setAttribute("check",check);
        }
        else
        {
            indirizzo="error/accessoNegato.jsp";
        }
        RequestDispatcher dispatcher= request.getRequestDispatcher(indirizzo);
        dispatcher.forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        doGet(request,response);
    }
}
