package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import DAO.User_DAO;
import DTO.Info_DTO;
import DTO.User_DTO;

public class Users_GUI extends JFrame implements ActionListener {
	// 로그인 한 User_DTO 불러오기
	private User_DTO myU = new User_DTO();
	// User_DAO 불러오기
	private User_DAO U_DAO = null;
	// 개인 기록 조회 ArrayList
	private ArrayList<Info_DTO> ulist = new ArrayList<Info_DTO>();
	// 사용자 간 랭킹 조회 ArrayList
	private ArrayList<Info_DTO> rlist = new ArrayList<Info_DTO>();
	// 생성자
	public Users_GUI(User_DTO u) {
		this.myU = u;
		U_DAO = User_DAO.getInstance();
		init();
		menu_Panel();
		addListener();
		record_Panel();
		insert_Panel();
		rank_Panel();
		setFont();
		loadRecord();
		loadRank1();
	}
	// 개인 기록 로드
	private void loadRecord() {
		myRecord.removeAll();
		rlist.clear();
		ulist = U_DAO.infoOne(myU);
		System.out.println(ulist.size());
		System.out.println(ulist.size());
		for (int i = 0; i < ulist.size(); i++) {
			myRecord.add(ulist.get(i).prtRecord());
		}
	}
	// 전체 랭킹 로드
	private void loadRank1() {
		Rnk_List.removeAll();
		rlist.clear();
		rlist = U_DAO.rank();
		System.out.println(rlist.size());
		for (int i = 0; i < rlist.size(); i++) {
			Rnk_List.add(rlist.get(i).prtRank(i));
		}
	}
	// 체급별 랭킹 로드
	private void loadRank2(String wgt) {
		Rnk_List.removeAll();
		rlist = U_DAO.wRank(wgt);
		System.out.println(rlist.size());
		for (int i = 0; i < rlist.size(); i++) {
			Rnk_List.add(rlist.get(i).prtRank(i));
		}
	}
	// 폰트 생성
	Font TitleFont = new Font("맑은 고딕", Font.BOLD, 30);
	Font EngFont = new Font("Consolas", Font.BOLD, 20);
	Font KorFont = new Font("맑은 고딕", Font.BOLD, 20);
	// 폰트 적용
	private void setFont() {
		// 상단 버튼
		record_Btn.setFont(KorFont);
		insert_Btn.setFont(KorFont);
		rank_Btn.setFont(KorFont);
		logout_Btn.setFont(KorFont);
		// 중앙 컴포넌트
		// 기록 조회
		R_Main_lb.setFont(KorFont);
		// 기록 추가
		I_Main_lb.setFont(TitleFont);
		I_Name_lb.setFont(KorFont);
		I_Wt_lb.setFont(KorFont);
		I_Submit_Btn.setFont(KorFont);
		// 랭킹 보기
		Rnk_Main_lb.setFont(KorFont);
		Rnk_Btn1.setFont(KorFont);
		Rnk_Btn2.setFont(KorFont);
		Rnk_Btn3.setFont(KorFont);
		Rnk_Btn4.setFont(KorFont);
		// 하단 회사명
		lb_S.setFont(TitleFont);
	}
	// 기본 GUI 설정
	private void init() {
		this.setLayout(new BorderLayout());
		this.add("North", menu_P);
		this.add("Center", record_P);
		lb_S = new JLabel("Zero Hunnit " + myU.getName() + "님 안녕하세요!");
		this.add("South", lb_S);
		this.setBounds(100, 100, 500, 700);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	// 상단 메뉴
	private JPanel menu_P = new JPanel();
	private JButton record_Btn = new JButton("누적기록");
	private JButton insert_Btn = new JButton("기록추가");
	private JButton rank_Btn = new JButton("랭킹보기");
	private JButton logout_Btn = new JButton("로그아웃");
	// 중앙
	private JPanel record_P = new JPanel();
	private JPanel insert_P = new JPanel();
	private JPanel rank_P = new JPanel();
	// 하단
	private JLabel lb_S = null;
	// 메뉴 버튼 설정
	private void menu_Panel() {
		menu_P.setLayout(new GridLayout(1, 4));
		menu_P.add(record_Btn);
		record_Btn.setBackground(Color.decode("#4e71ba"));
		menu_P.add(insert_Btn);
		insert_Btn.setBackground(Color.decode("#4e71ba"));
		menu_P.add(rank_Btn);
		rank_Btn.setBackground(Color.decode("#4e71ba"));
		menu_P.add(logout_Btn);
		logout_Btn.setBackground(Color.decode("#4e71ba"));
	}
	// 이벤트 리스너
	private void addListener() {
		record_Btn.addActionListener(this);
		insert_Btn.addActionListener(this);
		rank_Btn.addActionListener(this);
		I_Submit_Btn.addActionListener(this);
		logout_Btn.addActionListener(this);
		Rnk_Btn1.addActionListener(this);
		Rnk_Btn2.addActionListener(this);
		Rnk_Btn3.addActionListener(this);
		Rnk_Btn4.addActionListener(this);
	}
	// 개인 기록 조회
	private JLabel R_Main_lb = new JLabel("개 인 기 록 조 회");
	private List myRecord = new List(15);

	// 개인 기록 조회 디자인
	private void record_Panel() {
		record_P.setBackground(Color.decode("#4e71ba"));
		record_P.setLayout(new BorderLayout());
		record_P.add("North", R_Main_lb);
		record_P.add("Center", myRecord);
	}
	// 기록 화면
	private JLabel I_Main_lb = new JLabel("기 록 추 가");
	private JLabel I_Name_lb = new JLabel("종 목 입 력");
	private JTextField I_Name_tf = new JTextField(8);
	private JLabel I_Wt_lb = new JLabel("무 게 입 력");
	private JTextField I_Wt_tf = new JTextField(8);
	private JButton I_Submit_Btn = new JButton("추가");
	// 기록 화면 디자인
	private void insert_Panel() {
		insert_P.setBackground(Color.decode("#4e71ba"));
		I_Submit_Btn.setBackground(Color.decode("#4e71ba"));
		insert_P.setLayout(new GridLayout(6, 1));
		insert_P.add(I_Main_lb);
		insert_P.add(I_Name_lb);
		insert_P.add(I_Name_tf);
		insert_P.add(I_Wt_lb);
		insert_P.add(I_Wt_tf);
		insert_P.add(I_Submit_Btn);
	}
	// 랭킹보기
	private JLabel Rnk_Main_lb = new JLabel("랭 킹 보 기");
	private List Rnk_List = new List();
	private JPanel Rnk_Menu_P = new JPanel();
	private JButton Rnk_Btn1 = new JButton("전체");
	private JButton Rnk_Btn2 = new JButton("라이트급");
	private JButton Rnk_Btn3 = new JButton("미들급");
	private JButton Rnk_Btn4 = new JButton("헤비급");
	// 랭킹보기 디자인
	private void rank_Panel() {
		rank_P.setBackground(Color.decode("#4e71ba"));
		rank_P.setLayout(new BorderLayout());
		rank_P.add("North", Rnk_Main_lb);
		rank_P.add("Center", Rnk_List);
		rank_P.add("South", Rnk_Menu_P);
		Rnk_Menu_P.setLayout(new GridLayout(1, 4));
		Rnk_Menu_P.add(Rnk_Btn1);
		Rnk_Btn1.setBackground(Color.decode("#4e71ba"));
		Rnk_Menu_P.add(Rnk_Btn2);
		Rnk_Btn2.setBackground(Color.decode("#4e71ba"));
		Rnk_Menu_P.add(Rnk_Btn3);
		Rnk_Btn3.setBackground(Color.decode("#4e71ba"));
		Rnk_Menu_P.add(Rnk_Btn4);
		Rnk_Btn4.setBackground(Color.decode("#4e71ba"));
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// 기록 조회 버튼 (메뉴)
		if (e.getSource().equals(record_Btn)) {
			this.remove(insert_P);
			this.remove(rank_P);
			this.add("Center", record_P);
			this.setVisible(false);
			this.setVisible(true);
			// 기록 추가 버튼 (메뉴)
		} else if (e.getSource().equals(insert_Btn)) {
			this.remove(record_P);
			this.remove(rank_P);
			this.add("Center", insert_P);
			this.setVisible(false);
			this.setVisible(true);
			I_Name_tf.setText("");
			I_Wt_tf.setText("");
			// 랭킹보기 버튼 (메뉴)
		} else if (e.getSource().equals(rank_Btn)) {
			this.remove(insert_P);
			this.remove(record_P);
			this.add("Center", rank_P);
			this.setVisible(false);
			this.setVisible(true);
			// 로그아웃 버튼
		} else if (e.getSource().equals(logout_Btn)) {
			int result = JOptionPane.showConfirmDialog(null, "정말 로그아웃 하시겠습니까?", "경고", JOptionPane.YES_NO_OPTION,
					JOptionPane.WARNING_MESSAGE);
			if (result == 0) {
				new Manager_GUI();
				this.setVisible(false);
			}
			// 기록추가 버튼
		} else if (e.getSource().equals(I_Submit_Btn)) {
			if (I_Name_tf.getText().equals("") || I_Wt_tf.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "모든 항목을 기입해 주십시오", "경고", JOptionPane.WARNING_MESSAGE);
			} else {
				Info_DTO U_Rec = new Info_DTO();
				U_Rec.setC_name(this.myU.getName());
				U_Rec.setT_name(I_Name_tf.getText());
				U_Rec.setLiftWeight(Integer.parseInt(I_Wt_tf.getText()));
				U_DAO.infoAdd(U_Rec);
				JOptionPane.showMessageDialog(null, "기록 완료");
				I_Name_tf.setText("");
				I_Wt_tf.setText("");
				loadRecord();
				loadRank1();
			}
		} else if (e.getSource().equals(Rnk_Btn1)) {
			loadRank1();
		} else if (e.getSource().equals(Rnk_Btn2)) {
			String wgt = "라이트";
			loadRank2(wgt);
		} else if (e.getSource().equals(Rnk_Btn3)) {
			String wgt = "미들";
			loadRank2(wgt);
		} else if (e.getSource().equals(Rnk_Btn4)) {
			String wgt = "헤비";
			loadRank2(wgt);
		}
	}
}
