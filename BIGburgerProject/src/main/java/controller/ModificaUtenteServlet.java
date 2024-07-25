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


@WebServlet(name="modificaUtenteServlet", urlPatterns="/modificaUtenteServlet")
public class ModificaUtenteServlet extends HttpServlet
{
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        doGet(request,response);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String indirizzo="infoUtente.jsp";
        HttpSession session= request.getSession();
        Utente u= (Utente) session.getAttribute("utente");
        if(u==null)
        {
            indirizzo="error/accessoNegato.jsp";
        }
        else
        {
            int id= Integer.parseInt(request.getParameter("id"));
            String nome= request.getParameter("nome");
            String cognome= request.getParameter("cognome");
            String telefono= request.getParameter("tel");
            String email= request.getParameter("email");
            String psw= request.getParameter("psw");
            String ruolo= request.getParameter("ruolo");
            UtenteDAO service = new UtenteDAO();

            if(service.doCheckEmail(id, email))
            {
                Utente utente = new Utente(id, nome, cognome, telefono, email, psw, ruolo);
                service.doUpdateUtente(utente);
                session.setAttribute("utente", utente);
                indirizzo="infoUtente.jsp";
            }
            else
            {
                indirizzo="error/emailEsistente.jsp";
            }
        }

        response.sendRedirect(indirizzo);
    }
}
