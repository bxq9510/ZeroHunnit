package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import DTO.Training_DTO;
import DTO.User_DTO;

public class Manager_DAO {
	private Connection conn = null;
	public static Manager_DAO manger_dao = null;
	public static Manager_DAO getInstance() {
		if (manger_dao == null) {
			manger_dao = new Manager_DAO();
		}
		return manger_dao;
	}
	private Manager_DAO() {
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
	// 회원등록
	public void usrAdd(User_DTO usr) {
		String sql = "insert into customer values (?,?,?,?)";
		try {
			getConnection();
			PreparedStatement psmt = conn.prepareStatement(sql);
			psmt.setString(1, usr.getId());
			psmt.setString(2, usr.getName());
			psmt.setInt(3, usr.getHeight());
			psmt.setString(4, usr.getWeight());
			int k = psmt.executeUpdate();
			System.out.println(k + "건 등록완료");
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
	// 로그인
	public ArrayList<User_DTO> login() {
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

