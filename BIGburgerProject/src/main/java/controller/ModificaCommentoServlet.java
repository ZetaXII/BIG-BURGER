package controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.Commento;
import model.CommentoDAO;
import model.Utente;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "modificaCommentoServlet", value = "/modificaCommentoServlet")
public class ModificaCommentoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String indirizzo="recuperaCommentoServlet";
        HttpSession session= request.getSession();
        Utente u= (Utente) session.getAttribute("utente");
        if(u!=null && u.getRuolo().equalsIgnoreCase("u"))
        {
            int stelle= Integer.parseInt(request.getParameter("star"));
            String descrizione= (String) request.getParameter("descrizione");
            CommentoDAO commentoDAO= new CommentoDAO();
            Commento c= new Commento(0, u, descrizione, stelle, "");
            commentoDAO.doUpdateCommento(c);
            List<Commento> commenti= commentoDAO.doRetrieveFirstThree();
            getServletContext().setAttribute("commenti", commenti);
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
