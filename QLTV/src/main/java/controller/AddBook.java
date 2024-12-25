package controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Book;

import java.io.IOException;

import dao.BookDAO;

@WebServlet("/add-book")
public class AddBook extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AddBook() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Nếu là get request thì hiển thị giao diênn thêm sách
		response.setContentType("text/html");
		request.setAttribute("activePage", "books");
		request.setAttribute("titlePage", "Thêm sách");
		
		// 
		RequestDispatcher dispatcher = request.getRequestDispatcher("add_book.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Nếu là post request thì
		request.setCharacterEncoding("UTF-8");
		
		// Lấy dữ liệu từ request
		String name = request.getParameter("name");
		String category = request.getParameter("category");
		String author = request.getParameter("author");
		int amount = Integer.parseInt(request.getParameter("amount"));
		String description = request.getParameter("description");
		
		// Tạo một Book với dữ liệu lấy dc
		Book book = new Book();
		book.setName(name);
		book.setAuthor(author);
		book.setAmount(amount);
		book.setDescription(description);
		book.setCategory(category);
		
		// Tạo đối tượng BookDAO
		BookDAO bookDAO = new BookDAO();
		
		// Gọi gàm create(book) : hàm này có tác dụng lưu dữ liệu vào DB
		bookDAO.create(book);
		
		// Là gọi lại hàm doGet
		doGet(request, response);
	}

}
