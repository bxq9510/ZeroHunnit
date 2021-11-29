package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import DTO.User_DTO;

public class Admin_DAO {
	private Connection conn = null;
	public static Admin_DAO admin_DAO = null;
	public static Admin_DAO getInstance() {
		if (admin_DAO == null) {
			admin_DAO = new Admin_DAO();
		}
		return admin_DAO;
	}
	private Admin_DAO() {
		init();
	}
	private void init() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("클래스 로드 성공");
		} catch (ClassNotFoundException e) {
			System.out.println("클래스 로드 실패");
		}
	}
	private void getConnection() {
		try {
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "system", "1111");
			System.out.println("DB 접속 성공");
		} catch (SQLException e) {
			System.out.println("DB 접속 실패");
			e.printStackTrace();
		}
	}
	// 회원 수정
	public void usrEdit(User_DTO usr) {
		String sql = "update customer set weight=? where name=?";
		try {
			getConnection();
			PreparedStatement psmt = conn.prepareStatement(sql);
			psmt.setString(1, usr.getWeight());
			psmt.setString(2, usr.getName());
			int k = psmt.executeUpdate();
			System.out.println(k + "건 수정완료");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	// 회원삭제
	public void usrDel(User_DTO usr) {
		String sql = "delete from customer where name=?";
		try {
			getConnection();
			PreparedStatement psmt = conn.prepareStatement(sql);
			psmt.setString(1, usr.getName());
			int k = psmt.executeUpdate();
			System.out.println(k + "건 삭제완료");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	// 회원전체보기
	public ArrayList<User_DTO> usrAll() {
		String sql = "select * from customer";
		ResultSet rs = null;
		User_DTO returnDTO = null;
		ArrayList<User_DTO> ulist = new ArrayList<>();
		try {
			getConnection();
			Statement p = conn.createStatement();
			rs = p.executeQuery(sql);
			while (rs.next()) {
				returnDTO = new User_DTO();
				returnDTO.setId(rs.getString("id"));
				returnDTO.setName(rs.getString("name"));
				returnDTO.setHeight(rs.getInt("height"));
				returnDTO.setWeight(rs.getString("weight"));
				ulist.add(returnDTO);
			}
			return ulist;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
}

