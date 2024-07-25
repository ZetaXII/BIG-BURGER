package controller;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.Prodotto;
import model.ProdottoDAO;


import java.io.IOException;

@WebServlet(name="mostraProdottoServlet", urlPatterns="/mostraProdottoServlet")
public class MostraProdottoServlet extends HttpServlet
{
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        ProdottoDAO prodottoDAO = new ProdottoDAO();
        String indirizzo="/WEB-INF/results/mostraInfoProdotto.jsp";
        int id= Integer.parseInt(request.getParameter("id"));
        Prodotto p=null;
        p= prodottoDAO.doRetrieveById(id);

        if(p==null)
        {
            indirizzo="error/prodottoNonTrovato.jsp";
        }

        request.setAttribute("prodotto", p);

        RequestDispatcher dispatcher= request.getRequestDispatcher(indirizzo);
        dispatcher.forward(request,response);
    }
}