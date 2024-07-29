package servlet;
import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import entities.Garde;
import entities.Animal;
import entities.Sitting;
public class FormSitting extends HttpServlet
{
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        Garde[] listGardes=new Garde[0];
        Animal[] listAnimaux=new Animal[0];

        try {
            listGardes=Garde.getAll();
            listAnimaux=Animal.getAll();
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (req.getParameter("id")!=null)
        {
            int id=0;

            try {
                id=Integer.parseInt(req.getParameter("id"));
            } catch (Exception e) {
                e.printStackTrace();
            }

            if (id!=0)
            {
                try {
                    req.setAttribute("entity", Sitting.getById(id));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        if (req.getParameter("error") != null) {
            req.setAttribute("error", req.getParameter("error"));
        }
        
        req.setAttribute("listGardes", listGardes);
        req.setAttribute("listAnimaux", listAnimaux);
        
        RequestDispatcher dispatcher=req.getRequestDispatcher("pages/formSitting.jsp");
        dispatcher.forward(req, res);
	}

    protected void doPost (HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        
    }
}