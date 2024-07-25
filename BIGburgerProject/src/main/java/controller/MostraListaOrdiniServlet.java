package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.*;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

@WebServlet(name="mostraListaOrdiniServlet", urlPatterns="/mostraListaOrdiniServlet")
public class MostraListaOrdiniServlet extends HttpServlet
{
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
    {
        doGet(request,response);
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
    {
        String indirizzo="/WEB-INF/results/mostraListaOrdini.jsp";
        HttpSession session = request.getSession();
        Utente u= (Utente) session.getAttribute("utente");

        if(u!= null && u.getRuolo().equalsIgnoreCase("u"))
        {
            OrdineDAO ordineDAO = new OrdineDAO();

            ArrayList<Ordine> o= ordineDAO.doRetrieveByIdUtente(u.getId());
            request.setAttribute("ordini", o);
        }
        else if(u!= null && u.getRuolo().equalsIgnoreCase("f"))
        {
            if(request.getParameter("consegnati")!=null && request.getParameter("consegnati").equalsIgnoreCase("si"))
            {
                OrdineDAO ordineDAO = new OrdineDAO();

                ArrayList<Ordine> o= ordineDAO.doRetrieveByIdFattorinoConsegnati(u.getId());
                request.setAttribute("ordini", o);
            }
            else if(request.getParameter("consegnati")!=null && request.getParameter("consegnati").equalsIgnoreCase("no"))
            {
                indirizzo="/WEB-INF/results/mostraListaOrdini.jsp?consegnati=no";
                OrdineDAO ordineDAO = new OrdineDAO();

                ArrayList<Ordine> o= ordineDAO.doRetrieveByIdFattorinoInSpedizione(u.getId());
                request.setAttribute("ordini", o);
            }
            else
            {
                OrdineDAO ordineDAO = new OrdineDAO();
                DateTimeFormatter data= DateTimeFormatter.ofPattern("dd/MM/yyyy");
                LocalDateTime now = LocalDateTime.now();
                String dataStr= data.format(now);

                ArrayList<Ordine> o= ordineDAO.doRetrieveByDataAndStato(dataStr, "In preparazione");
                request.setAttribute("ordini", o);
            }

        }
        else if(u!= null && u.getRuolo().equalsIgnoreCase("a"))
        {
            String dataStr="";
            String dataReq= request.getParameter("data");
            String statoReq= request.getParameter("statoSrc");
            OrdineDAO ordineDAO = new OrdineDAO();

            ArrayList<Ordine> o= new ArrayList<>();

            if(dataReq==null || dataReq.equals(""))
            {
                DateTimeFormatter data= DateTimeFormatter.ofPattern("dd/MM/yyyy");
                LocalDateTime now = LocalDateTime.now();
                dataStr= data.format(now);
            }
            else
            {
                String giorno= ""+dataReq.charAt(8)+dataReq.charAt(9);
                String mese= ""+dataReq.charAt(5)+dataReq.charAt(6);
                String anno= ""+dataReq.charAt(0)+dataReq.charAt(1)+dataReq.charAt(2)+dataReq.charAt(3);

                dataStr= giorno+"/"+mese+"/"+anno;
            }

            if(statoReq==null || statoReq.equalsIgnoreCase(""))
            {
                o= ordineDAO.doRetrieveByData(dataStr);
            }
            else
            {
                o= ordineDAO.doRetrieveByDataAndStato(dataStr, statoReq);
            }

            request.setAttribute("ordini", o);
        }
        else
        {
            indirizzo="error/accessoNegato.jsp";
        }

        RequestDispatcher dispatcher= request.getRequestDispatcher(indirizzo);
        dispatcher.forward(request,response);
    }
}
