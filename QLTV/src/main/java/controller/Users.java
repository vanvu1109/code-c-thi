package controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.User;

import java.io.IOException;
import java.util.List;

import dao.UserDAO;


@WebServlet("/users")
public class Users extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Users() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		tạo một DAO mới cho user 
		UserDAO userDAO = new UserDAO();
//		dùng hàm find() để lấy ds user từ DB 
		List<User> listUsers = userDAO.find();
//		

		response.setContentType("text/html");
//		dùng setatribute để gởi dữ liệu đến view 
		request.setAttribute("listUsers", listUsers);
		request.setAttribute("activePage", "users");
		request.setAttribute("titlePage", "Quản lý nhân viên");
//     hiển thị lên view
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("users.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		// Láy dữ liệu được gửi đến server
		String username = request.getParameter("username");
        String password = request.getParameter("password");
        String name = request.getParameter("name");
        String position = request.getParameter("position");

        // Tạo một đôi tượng user để lưu dữ liệu
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setName(name);
        user.setPosition(position);

        // Tạo UserDAO
        UserDAO userDAO = new UserDAO();
        
        // Gọi hàm addUsser để lưu dữ liệu vào DB
        userDAO.addUser(user);
        
        // Gọi hàm doGet
        doGet(request, response);
	}

}
