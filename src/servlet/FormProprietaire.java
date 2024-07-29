package servlet;
import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;

import entities.Proprietaire;
public class FormProprietaire extends HttpServlet
{
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        RequestDispatcher dispatcher=req.getRequestDispatcher("pages/formProprietaire.jsp");
        
        if (req.getParameter("id")!=null)
        {
            int id=0;

            try {
                id=Integer.parseInt(req.getParameter("id"));

                if (id!=0)
                {   
                    req.setAttribute("entity", Proprietaire.getById(id));
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