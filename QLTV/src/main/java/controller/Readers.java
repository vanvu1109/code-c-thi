package controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Reader;

import java.io.IOException;
import java.util.List;

import dao.ReaderDAO;

@WebServlet("/readers")
public class Readers extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Readers() {
        super();
    }
//   Controller => DAO => View 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		tạo một đối tượng DAO
		ReaderDAO readerDAO = new ReaderDAO();
		//hàm find() trả về Danh sách reader lấy được từ DB
		List<Reader> readers = readerDAO.find();
		response.setContentType("text/html");
		// setAttribute() gui dũ liệu lên view
		// Gửi ds vào view
		request.setAttribute("readers", readers);
		
		request.setAttribute("activePage", "readers");
		request.setAttribute("titlePage", "Quản lý thành viên");
// hiển thị lên trang readers
        RequestDispatcher dispatcher = request.getRequestDispatcher("readers.jsp");
        dispatcher.forward(request, response);
	}

//	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");	
		// Lấy dữ liệu từ tu request
        String identityCard = request.getParameter("identityCard");
        String name = request.getParameter("name");
        String dob = request.getParameter("dob");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");
        String startDate = request.getParameter("startDate");
        String endDate = request.getParameter("endDate");

        // Tạo đối tượng Reader de luu thong tin vua lay duoc
        Reader reader = new Reader();
        reader.setIdentityCard(identityCard);
        reader.setName(name);
        reader.setDob(dob != null && !dob.isEmpty() ? java.sql.Date.valueOf(dob) : null);
        reader.setEmail(email);
        reader.setPhone(phone);
        reader.setAddress(address);
        reader.setStartDate(java.sql.Date.valueOf(startDate));
        reader.setEndDate(endDate != null && !endDate.isEmpty() ? java.sql.Date.valueOf(endDate) : null);

        // Gọi DAO để thêm Reader vào cơ sở dữ liệu
        ReaderDAO readerDAO = new ReaderDAO();
        boolean isAdded = readerDAO.addReader(reader);

        // goi lai ham doGet
        doGet(request, response);
    }

}
