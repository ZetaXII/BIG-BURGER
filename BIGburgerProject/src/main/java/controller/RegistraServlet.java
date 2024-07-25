package controller;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Utente;
import model.UtenteDAO;

import java.io.IOException;
import java.io.PrintWriter;



@WebServlet(name="registra", urlPatterns="/registra")
public class RegistraServlet extends HttpServlet
{
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        doGet(request,response);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        HttpSession session= request.getSession();
        Utente u= new Utente();
        String indirizzo="index.jsp";

        String nome = request.getParameter("nome");
        String cognome = request.getParameter("cognome");
        String telefono = request.getParameter("tel");
        String email = request.getParameter("email");
        String psw = request.getParameter("psw");
        String ruolo = "U";

        UtenteDAO service = new UtenteDAO();
        if(service.doCheckEmail(email))
        {
            u = new Utente(0, nome, cognome, telefono, email, psw, ruolo);
            service.registraUtente(u);
            session.setAttribute("utente", u);
            indirizzo="infoUtente.jsp";
        }
        else
        {
            indirizzo="error/emailEsistente.jsp";
        }

        response.sendRedirect(indirizzo);
    }
}
