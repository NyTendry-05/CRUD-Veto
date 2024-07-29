package servlet;
import java.io.*;
import java.sql.Timestamp;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import entities.Sitting;
import entities.Animal;
import entities.Garde;
import util.Util;
public class SittingServlet extends HttpServlet
{
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        Sitting[] list=null;
        Animal[] listAnimaux=new Animal[0];

        try {
            listAnimaux=Animal.getAll();
        } catch (Exception e) {
            e.printStackTrace();
        }

        req.setAttribute("listAnimaux", listAnimaux);

        Garde[] listGardes=new Garde[0];

        try {
            listGardes=Garde.getAll();
        } catch (Exception e) {
            e.printStackTrace();
        }

        req.setAttribute("listGardes", listGardes);

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
                        Sitting.delete(id);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        try {
            list=Sitting.getAll();
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (req.getParameter("idAnimal")!=null)
        {
            try {
                list = Sitting.getAll(req.getParameter("idAnimal"), req.getParameter("idGarde"), req.getParameter("debut"), req.getParameter("fin"));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        req.setAttribute("list", list);

        RequestDispatcher dispatcher=req.getRequestDispatcher("pages/listSitting.jsp");
        dispatcher.forward(req, res);
	}

    protected void doPost (HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        
        //s'il ne reçoit pas d'Id donc insert
        if (req.getParameter("id")==null)
        {
            int idG=0;
            int idA=0;
            Timestamp debut = null;
            Timestamp fin = null;
            String destination = "Sitting";

            try {
                idG=Integer.parseInt(req.getParameter("idGarde"));
                idA=Integer.parseInt(req.getParameter("idAnimal"));
                debut = Util.stringToTimestamp(req.getParameter("debut"));
                fin = Util.stringToTimestamp(req.getParameter("fin"));

            } catch (Exception e) {
                e.printStackTrace();
            }

            if (idG!=0 && idA!=0)
            {
                try {
                    new Sitting(0 , idA, idG, debut, fin).insert();
                } catch (Exception e) {
                    destination = "FormSitting?error="+e.getMessage();
                    e.printStackTrace();
                }
            }

            res.sendRedirect(destination);
        }
        else 
        {
            int id=0;
            int idG=0;
            int idA=0;
            Timestamp debut = null;
            Timestamp fin = null;

            try {
                id=Integer.parseInt(req.getParameter("id"));
                idG=Integer.parseInt(req.getParameter("idGarde"));
                idA=Integer.parseInt(req.getParameter("idAnimal"));
                debut = Util.stringToTimestamp(req.getParameter("debut"));
                fin = Util.stringToTimestamp(req.getParameter("fin"));

            } catch (Exception e) {
                e.printStackTrace();
            }

            if (idG!=0 && idA!=0)
            {
                try {
                    new Sitting(id , idA, idG, debut, fin).update();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            res.sendRedirect("Sitting");
        }
    }
}