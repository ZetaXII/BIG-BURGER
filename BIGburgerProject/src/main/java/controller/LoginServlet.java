package controller;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import model.*;

import java.io.IOException;
import java.util.ArrayList;


@WebServlet(name="login", urlPatterns="/login")
public class LoginServlet extends HttpServlet
{
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        doGet(request,response);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String email= request.getParameter("email");
        String psw= request.getParameter("psw");
        String ricordami= request.getParameter("ricordami");
        UtenteDAO service = new UtenteDAO();
        String indirizzo="index.jsp";

        Utente u= service.doRetrieveByEmailPassword(email,psw);

        if(u==null)
        {
            indirizzo="error/accessoNegato.jsp";
        }
        else
        {
            HttpSession session= request.getSession();
            session.setAttribute("utente", u);

            if(u!= null && u.getRuolo().equalsIgnoreCase("u"))
            {
                ArrayList<ElementoCarrello> carrello = null;
                ElementoCarrelloDAO elementoCarrelloDAO= new ElementoCarrelloDAO();
                carrello=elementoCarrelloDAO.doRetrieveByIdUtenteSenzaIdOrdine(u.getId());
                session.setAttribute("carrello", carrello);
            }

            if(ricordami!=null && ricordami.equalsIgnoreCase("si"))
            {
                Cookie[] cookies = null;
                cookies = request.getCookies();

                for(Cookie c : cookies)
                {
                    if(c.getName().equalsIgnoreCase("email"))
                    {
                        Cookie ck= new Cookie("email", null);
                        ck.setMaxAge(0);
                        response.addCookie(ck);
                    }

                    if(c.getName().equalsIgnoreCase("psw"))
                    {
                        Cookie ck= new Cookie("psw", null);
                        ck.setMaxAge(0);
                        response.addCookie(ck);
                    }
                }

                Cookie emailCk= new Cookie("email", email);
                Cookie pswCk= new Cookie("psw", psw);

                response.addCookie(emailCk);
                response.addCookie(pswCk);
            }
        }

        response.sendRedirect(indirizzo);
    }
}
