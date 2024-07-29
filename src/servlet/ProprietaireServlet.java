package servlet;
import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import entities.Proprietaire;
public class ProprietaireServlet extends HttpServlet
{
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        Proprietaire[] list=null;

        if (req.getParameter("mod")!=null)
        {
            if ("d".equals(req.getParameter("mod")))
            {
                int id=0;

                try {
                    id=Integer.parseInt(req.getParameter("id"));
                } catch (Exception e) {
                    e.printStackTrace();
                }

                if (id!=0) {
                    try {
                        Proprietaire.delete(id);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        try {
            list=Proprietaire.getAll();
        } catch (Exception e) {
            e.printStackTrace();
        }

        req.setAttribute("list", list);

        RequestDispatcher dispatcher=req.getRequestDispatcher("pages/listProprietaire.jsp");
        dispatcher.forward(req, res);
	}

    protected void doPost (HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        
        //s'il ne reçoit pas d'Id donc insert
        if (req.getParameter("id")==null)
        {
            try {
                new Proprietaire(0,req.getParameter("nom"),req.getParameter("coordonnees")).insert();
            } catch (Exception e) {
                e.printStackTrace();
            }

            res.sendRedirect("Proprietaire");
        }
        else 
        {
            int id=0;

            try {
                id=Integer.parseInt(req.getParameter("id"));

                new Proprietaire(id,req.getParameter("nom"),req.getParameter("coordonnees")).update();
            } catch (Exception e) {
                e.printStackTrace();
            }

            res.sendRedirect("Proprietaire");
        }
    }
}