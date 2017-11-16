package servlet;

import service.BookService;
import service.BookServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "DeleteBookServlet", urlPatterns = {"/DeleteBookServlet"})
public class DeleteBookServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String idString = request.getParameter("id");
            int id = Integer.parseInt(idString);
            BookService bookService = new BookServiceImpl();
            int result = bookService.deleteBookById(id);
            if (result == 1) {
                request.setAttribute("msg", "delete successfully");
            } else {
                request.setAttribute("msg", "delete failed");
            }
            request.getRequestDispatcher("BookServlet").forward(request, response);
        } catch (Exception e) {
            request.setAttribute("msg", "The id of this user is null");
            request.getRequestDispatcher("BookServlet").forward(request, response);

        }
    }
}