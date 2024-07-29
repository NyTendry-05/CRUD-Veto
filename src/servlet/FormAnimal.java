package servlet;
import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import entities.Animal;
import entities.Proprietaire;
public class FormAnimal extends HttpServlet
{
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        Proprietaire[] listProprietaires=new Proprietaire[0];

        try {
            listProprietaires=Proprietaire.getAll();
        } catch (Exception e ) {
            e.printStackTrace();
        }

        req.setAttribute("listProprietaires", listProprietaires);

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
                    req.setAttribute("entity", Animal.getById(id));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        RequestDispatcher dispatcher=req.getRequestDispatcher("pages/formAnimal.jsp");
        dispatcher.forward(req, res);
	}

    protected void doPost (HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        
    }
}