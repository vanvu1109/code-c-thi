package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Reader;

public class ReaderDAO {
	private Connection conn;

	public ReaderDAO() {
		conn = ConnectDatabase.getConnection();
	}

	public List<Reader> find() {
		List<Reader> readers = new ArrayList<Reader>();
		// Viết câu truy vấn
		String query = "SELECT * FROM Readers";
		try {
			// Thực thi truy vấn
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				// Lưu dữ liệu truy vấn được vào List<Reader>
				Reader reader = new Reader(rs.getInt("id"), rs.getString("identityCard"), rs.getString("name"),
						rs.getDate("dob"), rs.getString("email"), rs.getString("phone"), rs.getString("address"),
						rs.getDate("startDate"), rs.getDate("endDate"));
				readers.add(reader);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// Trả về ds
		return readers;
	}
	
	public boolean addReader(Reader reader) {
		String query = "INSERT INTO Readers(identityCard, name, dob, email, phone, address, startDate, endDate) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		try (PreparedStatement stmt = conn.prepareStatement(query)) {
			stmt.setString(1, reader.getIdentityCard());
			stmt.setString(2, reader.getName());
			stmt.setDate(3, reader.getDob());
			stmt.setString(4, reader.getEmail());
			stmt.setString(5, reader.getPhone());
			stmt.setString(6, reader.getAddress());
			stmt.setDate(7, reader.getStartDate());
			stmt.setDate(8, reader.getEndDate());
			return stmt.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
}
