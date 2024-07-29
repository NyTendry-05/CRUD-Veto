package servlet;
import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import entities.Garde;
public class FormGarde extends HttpServlet
{
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        RequestDispatcher dispatcher=req.getRequestDispatcher("pages/formGarde.jsp");

        if (req.getParameter("id")!=null)
        {
            int id=0;

            try {
                id=Integer.parseInt(req.getParameter("id"));

                if (id!=0)
                {   
                    req.setAttribute("entity", Garde.getById(id));
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        dispatcher.forward(req, res);
	}

    protected void doPost (HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        
    }
}