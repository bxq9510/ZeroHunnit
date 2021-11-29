package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import DTO.Info_DTO;
import DTO.Training_DTO;
import DTO.User_DTO;

public class User_DAO {
	private Connection conn = null;
	public static User_DAO user_dao = null;
	public static User_DAO getInstance() {
		if (user_dao == null) {
			user_dao = new User_DAO();
		}
		return user_dao;
	}
	private User_DAO() {
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
	// 기록등록
	public void infoAdd(Info_DTO usr) {
		String sql = "insert into info values (?,?,default,?)";
		try {
			getConnection();
			PreparedStatement psmt = conn.prepareStatement(sql);
			psmt.setString(1, usr.getC_name());
			psmt.setString(2, usr.getT_name());
			psmt.setInt(3, usr.getLiftWeight());
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
	// 누적기록 확인
	public ArrayList<Info_DTO> infoOne(User_DTO usr) {
		String sql = "select * from info where c_name=?";
		ResultSet rs = null;
		Info_DTO returnDTO = null;
		ArrayList<Info_DTO> tlist = new ArrayList<>();
		try {
			getConnection();
			PreparedStatement psmt = conn.prepareStatement(sql);
			psmt.setString(1, usr.getName());
			rs = psmt.executeQuery();
			while (rs.next()) {
				returnDTO = new Info_DTO();
				returnDTO.setC_name(rs.getString("c_name"));
				returnDTO.setT_name(rs.getString("t_name"));
				returnDTO.setLiftWeight(rs.getInt("liftWeight"));
				returnDTO.setT_date(rs.getString("tdate"));
				tlist.add(returnDTO);
			}
			return tlist;
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
	// 전체 랭킹
	public ArrayList<Info_DTO> rank() {
		String sql = "select c_name, sum(liftweight) from info group by c_name order by sum(liftweight) desc";
		ResultSet rs = null;
		Info_DTO returnDTO = null;
		ArrayList<Info_DTO> tlist = new ArrayList<>();
		try {
			getConnection();
			Statement p = conn.createStatement();
			rs = p.executeQuery(sql);
			while (rs.next()) {
				returnDTO = new Info_DTO();
				returnDTO.setC_name(rs.getString("c_name"));
				returnDTO.setLiftWeight(rs.getInt("sum(liftweight)"));
				tlist.add(returnDTO);
			}
			return tlist;
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
	// 체급별 랭킹
		public ArrayList<Info_DTO> wRank(String wgt) {
			String sql = "select c_name, sum(liftweight) from (select customer.name as c_name,"
					+ "info.liftweight as liftweight from info join customer on c_name=name and customer.weight=?)"
					+ "group by c_name order by sum(liftweight) desc";
			ResultSet rs = null;
			Info_DTO returnDTO = null;
			ArrayList<Info_DTO> tlist = new ArrayList<>();
			try {
				getConnection();
				PreparedStatement psmt = conn.prepareStatement(sql);
				psmt.setString(1, wgt);
				rs = psmt.executeQuery();
				while (rs.next()) {
					returnDTO = new Info_DTO();
					returnDTO.setC_name(rs.getString("c_name"));
					returnDTO.setLiftWeight(rs.getInt("sum(liftweight)"));
					tlist.add(returnDTO);
				}
				return tlist;
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

