package controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Book;

import java.io.IOException;
import java.util.List;

import dao.BookDAO;


@WebServlet("/books")
public class Books extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public Books() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html");
		request.setAttribute("activePage", "books");
		request.setAttribute("titlePage", "Quản lý sách");
		
		// Tạo một đối tượng DAO
		BookDAO bookDAO = new BookDAO();
		List<Book> listBooks;
		
		// Gọi hàm find() của BookDAO
		// Hàm find() này lấy tất cả các sách trong cơ sở dữ liệu, có kiểu trả về là List<Book>
		listBooks = bookDAO.find();
		
		// Truyền danh sách Book vùa lấy từ DB vào view
		// Tức là ở view có thể dùng dữ liệu List Book này
		request.setAttribute("listBooks", listBooks);

		// Hiển thị View
		RequestDispatcher dispatcher = request.getRequestDispatcher("books.jsp");
		dispatcher.forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
