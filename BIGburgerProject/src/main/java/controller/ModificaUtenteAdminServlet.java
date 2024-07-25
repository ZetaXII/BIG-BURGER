package controller;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Utente;
import model.UtenteDAO;
import java.io.IOException;


@WebServlet(name="modificaUtenteAdminServlet", urlPatterns="/modificaUtenteAdminServlet")
public class ModificaUtenteAdminServlet extends HttpServlet
{
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        doGet(request,response);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String indirizzo="";
        HttpSession session= request.getSession();
        Utente u= (Utente) session.getAttribute("utente");
        if(u==null || !(u.getRuolo().equalsIgnoreCase("a")))
        {
            indirizzo="error/accessoNegato.jsp";
        }
        else
        {
            int id= Integer.parseInt(request.getParameter("id"));
            String nome= request.getParameter("nome");
            String cognome= request.getParameter("cognome");
            String ruolo= request.getParameter("ruolo");

            UtenteDAO service = new UtenteDAO();
            Utente uDB= service.doRetrieveById(id);
            Utente utente= new Utente(id,nome,cognome,uDB.getTelefono(),uDB.getEmail(),uDB.getPsw(),ruolo);
            service.doUpdateUtente(utente);

            indirizzo="/WEB-INF/results/mostraInfoUtente.jsp";

            request.setAttribute("utente", utente);

        }

        RequestDispatcher dispatcher= request.getRequestDispatcher(indirizzo);
        dispatcher.forward(request,response);

    }
}
