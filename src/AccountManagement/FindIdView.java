package AccountManagement;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class FindIdView extends JFrame implements ActionListener{
	
	private String name;
	private String email;
	private String phone;
	
	private JPanel bjpl; // 메인 패널
	private JPanel jpl1; // 타이틀 패널
	private JPanel jpl2; // 이름 패널
	private JPanel jpl3; // 이메일 패널
	private JPanel jpl4; // 전화번호 패널
	private JPanel jpl5; // 아이디 찾기 / 뒤로가기 버튼 패널
	
	private JLabel lb_title;
	private JLabel lb_name;
	private JLabel lb_email;
	private JLabel lb_phone;
	
	private JTextField tf_name;
	private HintTextField tf_email;
	private HintTextField tf_phone;
	
	private JButton btn_find;
	private JButton btn_back;
	
	public FindIdView() {
		super("AccountManagement Program");
		setSize(400, 400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new FlowLayout(FlowLayout.LEFT, 20, 50));
		setLocationRelativeTo(null);
		
		bjpl = new JPanel(new GridLayout(5, 1, 10, 10));
		jpl1 = new JPanel(new GridLayout(1, 1, 10, 10)); // 타이틀 패널
		jpl2 = new JPanel(new GridLayout(1, 2, 10, 10)); // 이름 패널
		jpl3 = new JPanel(new GridLayout(1, 2, 10, 10)); // 이메일 패널
		jpl4 = new JPanel(new GridLayout(1, 2, 10, 10)); // 전화번호 패널
		jpl5 = new JPanel(new GridLayout(1, 2, 10, 10)); // 아이디 찾기 / 뒤로가기 버튼 패널
		
		lb_title = new JLabel("Find ID");
		jpl1.add(lb_title);
		
		lb_name = new JLabel("이름");
		tf_name = new JTextField();
		jpl2.add(lb_name);
		jpl2.add(tf_name);
		
		lb_email = new JLabel("이메일");
		tf_email = new HintTextField("abcde123@naver.com");
		jpl3.add(lb_email);
		jpl3.add(tf_email);
		
		lb_phone = new JLabel("전화번호");
		tf_phone = new HintTextField("010-0000-0000");
		jpl4.add(lb_phone);
		jpl4.add(tf_phone);
		
		btn_find = new JButton("아이디 찾기");
		btn_back = new JButton("뒤로가기");
		jpl5.add(btn_find);
		jpl5.add(btn_back);
		
		btn_find.addActionListener(this);
		btn_back.addActionListener(this);
		
		bjpl.add(jpl1);
		bjpl.add(jpl2);
		bjpl.add(jpl3);
		bjpl.add(jpl4);
		bjpl.add(jpl5);
		add(bjpl);
		setVisible(true);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == btn_find) {
			name = tf_name.getText();
			email = tf_email.getText();
			phone = tf_phone.getText();
			if (checkPhoneException(phone) == true) {
				DB db = new DB();
				db.db_connect();
				String query = "select id from (select * from account natural join member) where username = '" + name + "' and email = '" + email + "' and phone = '" + phone + "'" ;
				String id = db.search_DB(query);
				if(id != null) {
					JOptionPane.showMessageDialog(null, name + "님의 ID는 " + id, "ID 찾기 성공", JOptionPane.INFORMATION_MESSAGE);
					new LoginView();
		            setVisible(false);
				} else {
					JOptionPane.showMessageDialog(null, "ID를 찾을 수 없습니다. 다시 확인해주세요.", "ID 찾기 실패", JOptionPane.INFORMATION_MESSAGE);
				}
			} else {
				JOptionPane.showMessageDialog(null, "휴대폰 번호 형식이 잘못되었습니다. 다시 확인해주세요.", "입력형식 에러", JOptionPane.INFORMATION_MESSAGE);
			}
			
			
		} else if (e.getSource() == btn_back) {
			new LoginView();
            setVisible(false);
		}
	}
	
	public boolean checkPhoneException(String phone) {
		if(phone.length() != 13) {
			return false;
		}
		String desit[] = phone.split("-");
		if(desit.length != 3) {
			return false;
		}
		if(!(desit[0].equals("010") || desit[0].equals("011"))) {
			return false;
		}
		if(desit[1].length() !=4) {
			return false;
		}
		if(desit[2].length() !=4) {
			return false;
		}
		try {
			int mNumber = Integer.parseInt(desit[1]);
			int eNumber = Integer.parseInt(desit[2]);
			
			if(mNumber < 0 || mNumber > 9999 || eNumber < 0 || eNumber > 9999 ) {
				return false;
			}
		} catch (Exception e) {
			return false;
		}
		return true;
	}
}
