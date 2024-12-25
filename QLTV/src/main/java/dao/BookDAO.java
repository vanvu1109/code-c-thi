package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Book;

public class BookDAO {
	private Connection conn;
	
	public BookDAO() {
		conn = ConnectDatabase.getConnection();
	}
	
	// hàm find() Trả về List Book
	public List<Book> find() {
		List<Book> listBooks = new ArrayList<Book>();
		try {
			String sql = "SELECT * FROM Books";
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet rs = st.executeQuery();
			
			while(rs.next()) {
				Book book = new Book();
				book.setId(rs.getInt("id"));
				book.setName(rs.getString("name"));
				book.setAuthor(rs.getString("author"));
				book.setAmount(rs.getInt("amount"));
				book.setDescription(rs.getString("description"));
				book.setCreatedAt(rs.getString("createdAt"));
				book.setCategory(rs.getString("category"));
				
				listBooks.add(book);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return listBooks;
	}
	
	
	public void create(Book book) {
		String sql = "INSERT INTO Books (name, category, image, author, amount, description) VALUES (?, ?, ?, ?, ?, ?)";
		try {
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, book.getName());
			st.setString(2, book.getCategory());
			st.setString(3,	null);
			st.setString(4, book.getAuthor());
			st.setInt(5, book.getAmount());
			st.setString(6, book.getDescription());
			st.execute();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
