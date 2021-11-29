package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import DAO.Manager_DAO;
import DTO.Admin_DTO;
import DTO.User_DTO;

public class Manager_GUI extends JFrame implements ActionListener {
	// Manager DAO 선언
	Manager_DAO M_DAO = null;
	// Admin_DTO & GUI 불러오기
	Admin_DTO A_DTO = new Admin_DTO();
	// User_DTO & GUI 불러오기
	User_DTO U_DTO = new User_DTO();
	// 생성자
	public Manager_GUI() {
		M_DAO = Manager_DAO.getInstance();
		init();
		menu();
		Register_Panel();
		Login_Panel();
		Admin_Panel();
		setFont();
		addListener();
	}
	// 폰트 생성
	Font TitleFont = new Font("맑은 고딕", Font.BOLD, 30);
	Font EngFont = new Font("Consolas", Font.BOLD, 20);
	Font KorFont = new Font("맑은 고딕", Font.BOLD, 20);
	// 폰트 적용
	private void setFont() {
		// 상단 버튼
		register_Btn.setFont(KorFont);
		login_Btn.setFont(KorFont);
		admin_Btn.setFont(KorFont);
// 각 중앙 컴포넌트
		// 회원가입
		R_Main_lb.setFont(TitleFont);
		R_id_lb.setFont(EngFont);
		R_name_lb.setFont(EngFont);
		R_ht_lb.setFont(EngFont);
		R_wt_lb.setFont(EngFont);
		submit_Btn.setFont(TitleFont);
		// 로그인
		L_Main_lb.setFont(TitleFont);
		L_id_lb.setFont(EngFont);
		L_pw_lb.setFont(EngFont);
		logging_Btn.setFont(TitleFont);
		// 관리자
		A_Main_lb.setFont(TitleFont);
		A_id_lb.setFont(EngFont);
		A_pw_lb.setFont(EngFont);
		logging_Btn2.setFont(TitleFont);
		// 하단 회사명
		lb_S.setFont(TitleFont);
	}
	// 기본 GUI 설정
	private void init() {
		this.setLayout(new BorderLayout());
		this.add("North", menu_P);
		this.add("Center", register_P);
		this.add("South", lb_S);
		this.setBounds(100, 100, 500, 700);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	// 상단 메뉴
	private JPanel menu_P = new JPanel();
	private JButton register_Btn = new JButton("회원가입");
	private JButton login_Btn = new JButton("로그인");
	private JButton admin_Btn = new JButton("관리자");
	// 중앙
	private JPanel register_P = new JPanel();
	private JPanel login_P = new JPanel();
	private JPanel admin_P = new JPanel();
	// 하단
	private JLabel lb_S = new JLabel("Zero Hunnit");
	// 회원가입
	private JLabel R_Main_lb = new JLabel("회 원 가 입");
	private JLabel R_id_lb = new JLabel("ID");
	private JTextField R_id_tf = new JTextField(8);
	private JLabel R_name_lb = new JLabel("Name");
	private JTextField R_name_tf = new JTextField(8);
	private JLabel R_ht_lb = new JLabel("Height");
	private JTextField R_ht_tf = new JTextField(8);
	private JLabel R_wt_lb = new JLabel("Weight");
	private JTextField R_wt_tf = new JTextField(8);
	private JButton submit_Btn = new JButton("SUBMIT");
	// 회원가입 화면 디자인
	private void Register_Panel() {
		register_P.setBackground(Color.decode("#4e71ba"));
		submit_Btn.setBackground(Color.decode("#4e71ba"));
		register_P.setLayout(new GridLayout(10, 1));
		register_P.add(R_Main_lb);
		register_P.add(R_id_lb);
		register_P.add(R_id_tf);
		register_P.add(R_name_lb);
		register_P.add(R_name_tf);
		register_P.add(R_ht_lb);
		register_P.add(R_ht_tf);
		register_P.add(R_wt_lb);
		register_P.add(R_wt_tf);
		register_P.add(submit_Btn);
	}
	// 로그인 화면
	private JLabel L_Main_lb = new JLabel("유 저 로 그 인");
	private JLabel L_id_lb = new JLabel("ID");
	private JTextField L_id_tf = new JTextField(8);
	private JLabel L_pw_lb = new JLabel("Weight = your pw");
	private JTextField L_pw_tf = new JTextField(8);
	private JButton logging_Btn = new JButton("LOGIN");
	// 로그인 화면 디자인
	private void Login_Panel() {
		login_P.setBackground(Color.decode("#4e71ba"));
		logging_Btn.setBackground(Color.decode("#4e71ba"));
		login_P.setLayout(new GridLayout(6, 1));
		login_P.add(L_Main_lb);
		login_P.add(L_id_lb);
		login_P.add(L_id_tf);
		login_P.add(L_pw_lb);
		login_P.add(L_pw_tf);
		login_P.add(logging_Btn);
	}
	// 관리자 화면
	private JLabel A_Main_lb = new JLabel("관 리 자 로 그 인");
	private JLabel A_id_lb = new JLabel("ID");
	private JTextField A_id_tf = new JTextField(8);
	private JLabel A_pw_lb = new JLabel("PW");
	private JTextField A_pw_tf = new JTextField(8);
	private JButton logging_Btn2 = new JButton("LOGIN");
	// 관리자 화면 디자인
	private void Admin_Panel() {
		admin_P.setBackground(Color.decode("#4e71ba"));
		logging_Btn2.setBackground(Color.decode("#4e71ba"));
		admin_P.setLayout(new GridLayout(6, 1));
		admin_P.add(A_Main_lb);
		admin_P.add(A_id_lb);
		admin_P.add(A_id_tf);
		admin_P.add(A_pw_lb);
		admin_P.add(A_pw_tf);
		admin_P.add(logging_Btn2);
	}
	// 메뉴 버튼 설정
	private void menu() {
		menu_P.setLayout(new GridLayout());
		menu_P.add(register_Btn);
		register_Btn.setBackground(Color.decode("#4e71ba"));
		menu_P.add(login_Btn);
		login_Btn.setBackground(Color.decode("#4e71ba"));
		menu_P.add(admin_Btn);
		admin_Btn.setBackground(Color.decode("#4e71ba"));
	}
	// 각 component Action Listener
	private void addListener() {
		register_Btn.addActionListener(this);
		login_Btn.addActionListener(this);
		admin_Btn.addActionListener(this);
		logging_Btn.addActionListener(this);
		logging_Btn2.addActionListener(this);
		submit_Btn.addActionListener(this);
	}
	// 상단 버튼 별 이벤트 리스너
	@Override
	public void actionPerformed(ActionEvent e) {
		// 회원가입 버튼 (메뉴)
		if (e.getSource().equals(register_Btn)) {
			this.remove(admin_P);
			this.remove(login_P);
			this.add(register_P);
			this.setVisible(false);
			this.setVisible(true);
			// 로그인 버튼 (메뉴)
		} else if (e.getSource().equals(login_Btn)) {
			this.remove(register_P);
			this.remove(admin_P);
			this.add(login_P);
			this.setVisible(false);
			this.setVisible(true);
			// 관리자 버튼 (메뉴)
		} else if (e.getSource().equals(admin_Btn)) {
			this.remove(login_P);
			this.remove(register_P);
			this.add(admin_P);
			this.setVisible(false);
			this.setVisible(true);
			// 관리자 로그인 버튼
		} else if (e.getSource().equals(logging_Btn2)) {
			if (A_id_tf.getText().equals(A_DTO.getAdm_id()) && A_pw_tf.getText().equals(A_DTO.getAdm_pw())) {
				JOptionPane.showMessageDialog(null, "관리자 모드로 진입합니다.");
				this.setVisible(false);
				new Admin_GUI();
			}else {
				JOptionPane.showMessageDialog(null, "다시 입력하십시오", "경고", JOptionPane.WARNING_MESSAGE);
			}
			// 유저 로그인 버튼
		} else if (e.getSource().equals(logging_Btn)) { // 예외(NullPointerException 잡기
			ArrayList<User_DTO> uList = M_DAO.login();
			boolean chk = true;
			for (int i = 0; i < uList.size(); i++) {
				if (L_id_tf.getText().equals(uList.get(i).getId()) && L_pw_tf.getText().equals(uList.get(i).getWeight())) {
					chk = false;
					JOptionPane.showMessageDialog(null, "유저 모드로 진입합니다.");
					this.setVisible(false);
					U_DTO.setName(uList.get(i).getName());
					new Users_GUI(U_DTO);
				} 
			}
			if (chk) {
				JOptionPane.showMessageDialog(null, "다시 입력하십시오", "경고", JOptionPane.WARNING_MESSAGE);
			}
			// 회원가입 submit 버튼
		} else if (e.getSource().equals(submit_Btn)) {
			if (R_id_tf.getText().equals("") || R_name_tf.getText().equals("") || R_ht_tf.getText().equals("") || R_wt_tf.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "모든 항목을 기입해 주십시오", "경고", JOptionPane.WARNING_MESSAGE);
			}else {
				User_DTO newU = new User_DTO();
				newU.setId(R_id_tf.getText());
				newU.setName(R_name_tf.getText());
				newU.setHeight(Integer.parseInt(R_ht_tf.getText()));
				newU.setWeight(R_wt_tf.getText());
				M_DAO.usrAdd(newU);
				JOptionPane.showMessageDialog(null, (newU.getName() + "님! 가입을 축하드립니다!"));
			}
		}
	}
}
