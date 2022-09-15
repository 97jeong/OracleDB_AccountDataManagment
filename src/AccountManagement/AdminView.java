package AccountManagement;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class AdminView extends JFrame implements ActionListener{

	JPanel bjpl;
	JButton btn_showAll;
	JButton btn_searchName;
	JButton btn_searchID;
	JButton btn_back;
	JButton btn_logout;
	
	public AdminView() {
		super("AccountManagement Program");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new FlowLayout(FlowLayout.LEFT, 20, 50));
		init();
	}
	
	public void init() {
		setSize(500, 800);
		setLocationRelativeTo(null);
		bjpl = new JPanel(new GridLayout(5, 1, 10, 10)); // 메인 패널
		JPanel jpl1 = new JPanel(new GridLayout(1, 1, 10, 10));
		JPanel jpl2 = new JPanel(new GridLayout(1, 1, 20, 20));
		JPanel jpl3 = new JPanel(new GridLayout(1, 1, 20, 20));
		JPanel jpl4 = new JPanel(new GridLayout(1, 1, 20, 20));
		JPanel jpl5 = new JPanel(new GridLayout(1, 1, 20, 20));
		
		JLabel lb_title = new JLabel("관리자 접속");
		jpl1.add(lb_title);
		
		btn_showAll = new JButton("모든 회원 정보");
		btn_searchName = new JButton("이름으로 회원 검색");
		btn_searchID = new JButton("ID로 회원 검색");
		btn_logout = new JButton("Logout");
		jpl2.add(btn_showAll);
		jpl3.add(btn_searchName);
		jpl4.add(btn_searchID);
		jpl5.add(btn_logout);
		
		btn_showAll.addActionListener(this);
		btn_searchName.addActionListener(this);
		btn_searchID.addActionListener(this);
		btn_logout.addActionListener(this);
		
		bjpl.add(jpl1);
		bjpl.add(jpl2);
		bjpl.add(jpl3);
		bjpl.add(jpl4);
		bjpl.add(jpl5);
		add(bjpl);
		setVisible(false);
		setVisible(true);
	}
	
	public void showSearch(String query) {
		DB db = new DB();
		db.db_connect();
		ArrayList<PersonalInfo> list = db.search_admin(query);
		
		setSize(1600, 800);
		setLocationRelativeTo(null);
		bjpl = new JPanel(new GridLayout(list.size() + 3, 9, 10, 10));
		
		JLabel lb_title = new JLabel("회원 정보 열람");
		
		for(int i = 0; i < 9; i++) {
			if(i == 4) {
				bjpl.add(lb_title);
			} else {
				bjpl.add(new JLabel());
			}
		}
		bjpl.add(new JLabel("ID"));
		bjpl.add(new JLabel("PASSWORD"));
		bjpl.add(new JLabel("NICKNAME"));
		bjpl.add(new JLabel("USERNAME"));
		bjpl.add(new JLabel("GENDER"));
		bjpl.add(new JLabel("BIRTH"));
		bjpl.add(new JLabel("ADDRESS"));
		bjpl.add(new JLabel("PHONE"));
		bjpl.add(new JLabel("EMAIL"));
		
		for(int i = 0; i < list.size(); i++) {
			bjpl.add(new JLabel(list.get(i).getId()));
			bjpl.add(new JLabel(list.get(i).getPassword()));
			bjpl.add(new JLabel(list.get(i).getNickname()));
			bjpl.add(new JLabel(list.get(i).getUsername()));
			bjpl.add(new JLabel(list.get(i).getGender()));
			bjpl.add(new JLabel(list.get(i).getBirth()));
			bjpl.add(new JLabel(list.get(i).getAddress()));
			bjpl.add(new JLabel(list.get(i).getPhone()));
			bjpl.add(new JLabel(list.get(i).getEmail()));
		}
		
		btn_back = new JButton("뒤로가기");
		for(int i = 0; i < 9; i++) {
			if(i == 4) {
				bjpl.add(btn_back);
			} else {
				bjpl.add(new JLabel());
			}
		}
		btn_back.addActionListener(this);
		
		add(bjpl);
		setVisible(false);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == btn_showAll) {
			remove(bjpl);
			String query = "select id, password, nickname, username, gender, birth, address, phone, email from account natural join member order by id";
			showSearch(query);
		} else if(e.getSource() == btn_searchName) {
			String username = JOptionPane.showInputDialog("이름을 입력하세요");
			remove(bjpl);
			String query = "select id, password, nickname, username, gender, birth, address, phone, email from account natural join member " + "where username = '" + username +"' order by id";
			showSearch(query);
		} else if(e.getSource() == btn_searchID) {
			String id = JOptionPane.showInputDialog("ID를 입력하세요");
			remove(bjpl);
			String query = "select id, password, nickname, username, gender, birth, address, phone, email from account natural join member " + "where id = '" + id +"' order by id";
			showSearch(query);
		} else if(e.getSource() == btn_back) {
			remove(bjpl);
			init();
		} else if(e.getSource() == btn_logout) {
			new LoginView();
			setVisible(false);
		}
	}
}
