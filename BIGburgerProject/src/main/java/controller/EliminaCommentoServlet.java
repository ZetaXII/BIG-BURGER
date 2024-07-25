package controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.Commento;
import model.CommentoDAO;
import model.Utente;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "eliminaCommentoServlet", value = "/eliminaCommentoServlet")
public class EliminaCommentoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String indirizzo="mostraCommentiServlet";
        HttpSession session= request.getSession();
        Utente u= (Utente) session.getAttribute("utente");
        if(u!=null && u.getRuolo().equalsIgnoreCase("u"))
        {
            CommentoDAO commentoDAO= new CommentoDAO();
            commentoDAO.doDeleteCommento(u.getId());
            List<Commento> commenti= commentoDAO.doRetrieveFirstThree();
            getServletContext().setAttribute("commenti", commenti);
        }
        else if(u!=null && u.getRuolo().equalsIgnoreCase("a"))
        {
            int idUtente= Integer.parseInt(request.getParameter("idUtente"));
            CommentoDAO commentoDAO= new CommentoDAO();
            commentoDAO.doDeleteCommento(idUtente);
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
