package AccountManagement;


import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.PopupMenu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;

import javax.swing.*;

public class LoginView extends JFrame implements ActionListener{
	private JPanel bjpl;
	private JPanel jpl1;
	private JPanel jpl2;
	private JPanel jpl2_1;
	private JPanel jpl2_2;
	private JPanel jpl3;
	
	private JLabel lb_title;
	private JLabel lb_id;
	private JLabel lb_pw;
	private JTextField tf_id;
	private JPasswordField pf_pw;
	private JButton btn_login;
	private JButton btn_signup;
	private JButton btn_findid;
	private JButton btn_findpw;
	
	
	private String id;
	private String password;
	
	public LoginView() {
		super("AccountManagement Program");
		setSize(500, 400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new FlowLayout(FlowLayout.LEFT, 20, 50));
		setLocationRelativeTo(null);
		
		bjpl = new JPanel(new GridLayout(3, 1, 10, 10)); // 메인 패널
		jpl1 = new JPanel(new GridLayout(1, 1)); // title 패널
		jpl2 = new JPanel(new GridLayout(1, 2)); // 로그인 패널
		jpl2_1 = new JPanel(new GridLayout(2, 2)); // 아이디 / 비밀번호
		jpl2_2 = new JPanel(new GridLayout(1, 2)); // 로그인 버튼
		jpl3 = new JPanel(new GridLayout(1, 3, 10, 10)); // 아이디 찾기, 비밀번호 찾기, 회원가입 패널
		
		lb_title = new JLabel("Program Login");
		jpl1.add(lb_title);
		
		lb_id = new JLabel("아이디");
		tf_id = new JTextField();
		jpl2_1.add(lb_id);
		jpl2_1.add(tf_id);
		
		lb_pw = new JLabel("비밀번호");
		pf_pw = new JPasswordField();
		jpl2_1.add(lb_pw);
		jpl2_1.add(pf_pw);
		
		btn_login = new JButton("로그인");
		jpl2_2.add(new JPanel());
		jpl2_2.add(btn_login);
		jpl2.add(jpl2_1);
		jpl2.add(jpl2_2);
		
		btn_signup = new JButton("회원가입");
		btn_findid = new JButton("아이디 찾기");
		btn_findpw = new JButton("패스워드 찾기");
		jpl3.add(btn_findid);
		jpl3.add(btn_findpw);
		jpl3.add(btn_signup);
		
		btn_login.addActionListener(this);
		btn_signup.addActionListener(this);
		btn_findid.addActionListener(this);
		btn_findpw.addActionListener(this);
		
		bjpl.add(jpl1);
		bjpl.add(jpl2);
		bjpl.add(jpl3);
		add(bjpl);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		boolean checkedAccount = false;
		
		if (e.getSource() == btn_login) {
			DB db = new DB();
			id = tf_id.getText();
			password = pf_pw.getText();
			
			String query = "select id, password from Account where id = '" + id + "'" + " and password = '" + password + "'";
			db.db_connect();
			try {
				Statement statement = db.getConnection().createStatement();
				ResultSet result_set = statement.executeQuery(query);
				if (result_set.next() == true) {
					checkedAccount = true;
				}
				result_set.close();
				statement.close();
				db.getConnection().close();
				
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				System.out.println("퀴리문 오류 발생");
				e1.printStackTrace();
			}
			if(checkedAccount == true) {
				if(id.equals("admin")) {
					new AdminView();
					setVisible(false);
				} else {
					new UserView(id);
					setVisible(false);	
				}
			} else {
				JOptionPane.showMessageDialog(null, "로그인 정보를 다시 확인하세요.", "로그인 실패", JOptionPane.INFORMATION_MESSAGE);
			}
			
		} else if(e.getSource() == btn_signup) {
			new SignUpView();
            setVisible(false); 
		} else if(e.getSource() == btn_findid) {
			new FindIdView();
			setVisible(false);
		} else if(e.getSource() == btn_findpw) {
			new FindPwView();
			setVisible(false);
		}
	}
	
}
