package servlet;
import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import entities.Animal;
public class AnimalServlet extends HttpServlet
{
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        Animal[] list=null;

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
                        Animal.delete(id);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        try {
            list=Animal.getAll();
        } catch (Exception e) {
            e.printStackTrace();
        }

        req.setAttribute("list", list);

        RequestDispatcher dispatcher=req.getRequestDispatcher("pages/listAnimal.jsp");
        dispatcher.forward(req, res);
	}

    protected void doPost (HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        
        //s'il ne reçoit pas d'Id donc insert
        if (req.getParameter("id")==null)
        {
            int idP=0;

            try {
                idP=Integer.parseInt(req.getParameter("idProprietaire"));
            } catch (Exception e) {
                e.printStackTrace();
            }

            if (idP!=0)
            {
                try {
                    new Animal(0,req.getParameter("nom"),idP).insert();;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            res.sendRedirect("Animal");
        }
        else 
        {
            int id=0;
            int idP=0;

            try {
                id=Integer.parseInt(req.getParameter("id"));
                idP=Integer.parseInt(req.getParameter("idProprietaire"));
            } catch (Exception e) {
                e.printStackTrace();
            }

            if (idP!=0&&id!=0)
            {
                try {
                    new Animal(id,req.getParameter("nom"),idP).update();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            res.sendRedirect("Animal");
        }
    }
}