package com.sample;

import com.sample.model.BookType;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@WebServlet(
        name = "selectbookservlet",
        urlPatterns = "/SelectBook"
)
public class SelectBookServlet extends HttpServlet {
	 @Override
	    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	        String bookType = req.getParameter("Type");

	        BookService BookService = new BookService();
	        BookType l = BookType.valueOf(bookType);

	        List Bookgenres= BookService.getAvailablegeners(l);

	        req.setAttribute("genres", Bookgenres);
	        RequestDispatcher view = req.getRequestDispatcher("result.jsp");
	        view.forward(req, resp);

	    }
}
