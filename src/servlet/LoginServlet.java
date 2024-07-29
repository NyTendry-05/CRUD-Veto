package servlet;
import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import entities.Admin;
public class LoginServlet extends HttpServlet
{
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        if (req.getParameter("mod") != null) {
            if (req.getParameter("mod").equals("logout"))
            {
                req.getSession().removeAttribute("user");
            }
        }

        res.sendRedirect("pagesAdmin/login.jsp");
	}

    protected void doPost (HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String username=req.getParameter("username");
        String pwd=req.getParameter("pwd");

        Admin admin=null;
        try {
            admin=Admin.login(username, pwd);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (admin==null)
        {
            res.sendRedirect("pagesAdmin/login.jsp?error=Login ou mot de passe erroné!");
        }
        else 
        {
            HttpSession session=req.getSession();
            session.setAttribute("user", admin);
            res.sendRedirect("Sitting");
        }
    }
}