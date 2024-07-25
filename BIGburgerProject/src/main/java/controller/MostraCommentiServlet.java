package controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.Commento;
import model.CommentoDAO;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "mostraCommentiServlet", value = "/mostraCommentiServlet")
public class MostraCommentiServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String indirizzo="/WEB-INF/results/mostraCommenti.jsp";
        CommentoDAO commentoDAO= new CommentoDAO();
        ArrayList<Commento> commenti= commentoDAO.doRetrieveAll();
        int totPerStella[]=commentoDAO.doRetrieveTotalePerStella();
        int voti=0;
        double totaleVoto=0;

        for(int i=1; i<6; i++)
        {
            int totSingolaStella= totPerStella[i-1]*i;
            voti=voti+totPerStella[i-1];
            totaleVoto= totaleVoto+totSingolaStella;
        }

        request.setAttribute("commenti", commenti);
        request.setAttribute("totPerStella", totPerStella);
        request.setAttribute("totaleVoto", totaleVoto/voti);

        RequestDispatcher dispatcher= request.getRequestDispatcher(indirizzo);
        dispatcher.forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        doGet(request,response);
    }
}
