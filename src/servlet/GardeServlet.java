package servlet;
import java.io.*;
import java.sql.Timestamp;
import util.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import entities.Garde;
public class GardeServlet extends HttpServlet
{
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        Garde[] list=null;

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
                        Garde.delete(id);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        try {
            list=Garde.getAll();
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (req.getParameter("debut") != null && req.getParameter("fin") != null) {
            if (!req.getParameter("debut").isEmpty() && !req.getParameter("fin").isEmpty()) {
                Timestamp debut = Util.stringToTimestamp(req.getParameter("debut"));
                Timestamp fin = Util.stringToTimestamp(req.getParameter("fin"));
                
                if (debut != null && fin != null) {
                    try {
                        list=Garde.getAvailableGarde(debut, fin);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    req.setAttribute("error", "Verifier le format de vos dates!");
                }
            }
        }

        req.setAttribute("list", list);

        RequestDispatcher dispatcher=req.getRequestDispatcher("pages/listGarde.jsp");
        dispatcher.forward(req, res);
	}

    protected void doPost (HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        
        //s'il ne reçoit pas d'Id donc insert
        if (req.getParameter("id")==null)
        {
            try {
                new Garde(0,req.getParameter("nom")).insert();
            } catch (Exception e) {
                e.printStackTrace();
            }

            res.sendRedirect("Garde");
        }
        else 
        {
            int id=0;

            try {
                id=Integer.parseInt(req.getParameter("id"));

                new Garde(id,req.getParameter("nom")).update();
            } catch (Exception e) {
                e.printStackTrace();
            }

            res.sendRedirect("Garde");
        }
    }
}