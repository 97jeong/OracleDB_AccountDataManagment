package AccountManagement;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class SignUpView extends JFrame implements ActionListener{
	private String id;
	private String nickname;
	private String pw;
	private String pw2;
	private String name;
	private String birth;
	private String address;
	private String gender;
	private String email;
	private String phone;
	
	private JPanel bjpl;
	private JPanel jpl1;
	private JPanel jpl2;
	private JPanel jpl3;
	private JPanel jpl4;
	private JPanel jpl5;
	private JPanel jpl6;
	private JPanel jpl7;
	private JPanel jpl8;
	private JPanel jpl9;
	private JPanel jpl10;
	private JPanel jpl11;
	private JPanel jpl12;
	
	private JLabel lb_title;
	private JLabel lb_id;
	private JLabel lb_nick;
	private JLabel lb_pw;
	private JLabel lb_pw2;
	private JLabel lb_name;
	private JLabel lb_birth;
	private JLabel lb_adress;
	private JLabel lb_gender;
	private JLabel lb_email;
	private JLabel lb_phone;
	
	private JTextField tf_id;
	private JTextField tf_nick;
	private JTextField tf_name;
	private HintTextField tf_birth;
	private JTextField tf_address;
	private HintTextField tf_gender;
	private HintTextField tf_email;
	private HintTextField tf_phone;
	
	private JPasswordField pf_pw;
	private JPasswordField pf_pw2;
	
	private JButton btn_idchk;
	private JButton btn_nickchk;
	private JButton btn_signup;
	private JButton btn_back;
	
	private boolean checkedID = false;
	private boolean checkedNick = false;
	
	public SignUpView() {
		super("AccountManagement Program");
		setSize(400, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new FlowLayout(FlowLayout.LEFT, 20, 50));
		setLocationRelativeTo(null);
		
		
		bjpl = new JPanel(new GridLayout(12, 1, 10, 10));
		jpl1 = new JPanel(new GridLayout(1, 1, 10, 10)); // 타이틀
		jpl2 = new JPanel(new GridLayout(1, 3, 10, 10)); // 아이디
		jpl3 = new JPanel(new GridLayout(1, 3, 10, 10)); // 닉네임
		jpl4 = new JPanel(new GridLayout(1, 2, 10, 10)); // 비밀번호
		jpl5 = new JPanel(new GridLayout(1, 2, 10, 10)); // 비밀번호 재확인
		jpl6 = new JPanel(new GridLayout(1, 2, 10, 10)); // 이름
		jpl7 = new JPanel(new GridLayout(1, 4, 10, 10)); // 생년월일
		jpl8 = new JPanel(new GridLayout(1, 2, 10, 10)); // 주소
		jpl9 = new JPanel(new GridLayout(1, 2, 10, 10)); // 성별
		jpl10 = new JPanel(new GridLayout(1, 2, 10, 10)); // 이메일
		jpl11 = new JPanel(new GridLayout(1, 3, 10, 10)); // 전화번호
		jpl12 = new JPanel(new GridLayout(1, 2, 10, 10)); // 가입 버튼 / 뒤로가기 버튼
		
		lb_title = new JLabel("SignUp");
		jpl1.add(lb_title);
		
		lb_id = new JLabel("아이디 *");
		tf_id = new JTextField();
		btn_idchk = new JButton("중복확인");
		jpl2.add(lb_id);
		jpl2.add(tf_id);
		jpl2.add(btn_idchk);
		
		lb_nick = new JLabel("닉네임 *");
		tf_nick = new JTextField();
		btn_nickchk = new JButton("중복확인");
		jpl3.add(lb_nick);
		jpl3.add(tf_nick);
		jpl3.add(btn_nickchk);
		
		lb_pw = new JLabel("비밀번호 *");
		pf_pw = new JPasswordField();
		jpl4.add(lb_pw);
		jpl4.add(pf_pw);
		
		lb_pw2 = new JLabel("비밀번호 재확인 *");
		pf_pw2 = new JPasswordField();
		jpl5.add(lb_pw2);
		jpl5.add(pf_pw2);
		
		lb_name = new JLabel("이름 *");
		tf_name = new JTextField();
		jpl6.add(lb_name);
		jpl6.add(tf_name);
		
		lb_birth = new JLabel("생년월일 *");
		tf_birth = new HintTextField("yyyy-mm-dd");
		jpl7.add(lb_birth);
		jpl7.add(tf_birth);
		
		lb_adress = new JLabel("주소");
		tf_address = new JTextField();
		jpl8.add(lb_adress);
		jpl8.add(tf_address);
		
		lb_gender = new JLabel("성별");
		tf_gender = new HintTextField("M or F");
		jpl9.add(lb_gender);
		jpl9.add(tf_gender);
		
		lb_email = new JLabel("이메일 *");
		tf_email = new HintTextField("abcde123@naver.com");
		jpl10.add(lb_email);
		jpl10.add(tf_email);
		
		lb_phone = new JLabel("핸드폰 번호 *");
		tf_phone = new HintTextField("010-0000-0000");
		jpl11.add(lb_phone);
		jpl11.add(tf_phone);
		
		btn_signup = new JButton("가입하기");
		jpl12.add(btn_signup);
		
		btn_back = new JButton("뒤로가기");
		jpl12.add(btn_back);
		
		btn_idchk.addActionListener(this);
		btn_nickchk.addActionListener(this);
		btn_signup.addActionListener(this);
		btn_back.addActionListener(this);
		
		bjpl.add(jpl1);
		bjpl.add(jpl2);
		bjpl.add(jpl3);
		bjpl.add(jpl4);
		bjpl.add(jpl5);
		bjpl.add(jpl6);
		bjpl.add(jpl7);
		bjpl.add(jpl8);
		bjpl.add(jpl9);
		bjpl.add(jpl10);
		bjpl.add(jpl11);
		bjpl.add(jpl12);
		add(bjpl);
		setVisible(true);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		DB db = new DB();
		boolean checkedID = false;
		boolean checkedNick = false;
		if (e.getSource() == btn_idchk) {
			id = tf_id.getText();
			if(id.equals("")) {
				JOptionPane.showMessageDialog(null, "아이디를 입력하세요.", "필수 정보 누락", JOptionPane.INFORMATION_MESSAGE);
			} else {
				checkedID = db.checkOverlap("account","id", id);
				this.checkedID = checkedID;
				if(checkedID == true) {
					JOptionPane.showMessageDialog(null, "사용 가능한 아이디입니다.", "사용 가능", JOptionPane.INFORMATION_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(null, "이미 사용 중인 아이디입니다.", "아이디 중복", JOptionPane.INFORMATION_MESSAGE);
				}	
			}
		} else if(e.getSource() == btn_nickchk) {
			nickname = tf_nick.getText();
			if(nickname.equals("")) {
				JOptionPane.showMessageDialog(null, "아이디를 입력하세요.", "필수 정보 누락", JOptionPane.INFORMATION_MESSAGE);
			} else {
				checkedNick = db.checkOverlap("account","nickname", nickname);
				this.checkedNick = checkedNick;
				if(checkedNick == true) {
					JOptionPane.showMessageDialog(null, "사용 가능한 닉네임입니다.", "사용 가능", JOptionPane.INFORMATION_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(null, "이미 사용 중인 닉네임입니다.", "닉네임 중복", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		} else if (e.getSource() == btn_signup){
			id = tf_id.getText();
			pw = pf_pw.getText();
			pw2 = pf_pw2.getText();
			nickname = tf_nick.getText();
			name = tf_name.getText();
			birth = tf_birth.getText();
			address = tf_address.getText();
			gender = tf_gender.getText();
			email = tf_email.getText();
			phone = tf_phone.getText();
			if(checkConstraint(id, pw, pw2, name, birth, gender, email, phone) == true) {
				int uid = hashCode();
				if(db.checkOverlap("member", "user_id", uid) == true) {
					db.insert_member(uid, name, gender, birth, address, phone, email);
					db.insert_account(id, pw, nickname, uid);
				} else {
					do {
						uid = hashCode();
					} while(db.checkOverlap("member", "user_id", uid) == true);
				}
				JOptionPane.showMessageDialog(null, "회원가입이 완료되었습니다.", "회원가입 완료", JOptionPane.INFORMATION_MESSAGE);
				new LoginView();
	            setVisible(false);
			}
		} else if (e.getSource() == btn_back) {
			 new LoginView();
             setVisible(false);
		}
	}
	
	boolean checkConstraint(String id, String pw, String pw2, String name, String birth, String gender, String email, String phone) {
		DB db = new DB();
		
		if(checkedID == false) {
			JOptionPane.showMessageDialog(null, "아이디 중복을 확인해세요.", "중복 검사 요청", JOptionPane.INFORMATION_MESSAGE);
			return false;
		}
		if(checkedNick == false) {
			JOptionPane.showMessageDialog(null, "닉네임 중복을 확인해세요.", "중복 검사 요청", JOptionPane.INFORMATION_MESSAGE);
			return false;
		}
		if(id.equals("")) {
			JOptionPane.showMessageDialog(null, "아이디 입력하세요.", "필수 정보 누락", JOptionPane.INFORMATION_MESSAGE);
			return false;
		}
		if (pw.equals("") || pw2.equals("")) {
			JOptionPane.showMessageDialog(null, "패스워드를 입력하세요.", "필수 정보 누락", JOptionPane.INFORMATION_MESSAGE);
		}
		if(name.equals("")) {
			JOptionPane.showMessageDialog(null, "이름을 입력하세요.", "필수 정보 누락", JOptionPane.INFORMATION_MESSAGE);
			return false;
		}
		if(email.equals("abcde123@naver.com")) {
			JOptionPane.showMessageDialog(null, "이메일을 입력하세요.", "필수 정보 누락", JOptionPane.INFORMATION_MESSAGE);
			return false;
		}
		if(phone.equals("000-0000-0000")) {
			JOptionPane.showMessageDialog(null, "휴대폰 번호를 입력하세요.", "필수 정보 누락", JOptionPane.INFORMATION_MESSAGE);
			return false;
		}
		if (pw.equals(pw2) == false) {
			JOptionPane.showMessageDialog(null, "패스워드를 다시 확인해주세요.", "패스워드 불일치", JOptionPane.INFORMATION_MESSAGE);
			return false;
		}
		if(gender.equals("M or F") == true) {
			this.gender = null;
		} else {
			if(!(gender.equals("M") || gender.equals("F"))) {
				System.out.println(gender);
				JOptionPane.showMessageDialog(null, "성별 입력 형식을 확인해주세요.", "성별 입력 오류", JOptionPane.INFORMATION_MESSAGE);
				return false;
			}
		}
		
		if(checkBirthException(birth) == false) {
			JOptionPane.showMessageDialog(null, "날짜 형식이 잘못되었습니다. 다시 확인해주세요.", "입력형식 에러", JOptionPane.INFORMATION_MESSAGE);
			return false;
		}
		
		if(checkPhoneException(phone) == false) {
			JOptionPane.showMessageDialog(null, "휴대폰 번호 형식이 잘못되었습니다. 다시 확인해주세요.", "입력형식 에러", JOptionPane.INFORMATION_MESSAGE);
			return false;
		}
		
		if(db.checkOverlap("member", "email", email) == false) {
			JOptionPane.showMessageDialog(null, "이미 등록된 이메일입니다.", "이메일 중복", JOptionPane.INFORMATION_MESSAGE);
			return false;
		}
		
		if(db.checkOverlap("member", "phone", phone) == false) {
			JOptionPane.showMessageDialog(null, "이미 등록된 휴대폰 번호입니다.", "휴대폰 번호 중복", JOptionPane.INFORMATION_MESSAGE);
			return false;
		}
		
		try {
			db.getResult_set().close();
			db.getStatement().close();
			db.getConnection().close();
		} catch (Exception e) {}
		
		return true;
	}
	
	public boolean checkBirthException(String birth) {
		String[] date;
		String year;
		String month;
		String day;
		int iyear;
		int imonth;
		int iday;
		if(birth.length() != 10) {
			return false;
		}
		try {
			date = birth.split("-");
			year = date[0];
			month = date[1];
			day = date[2];
			iyear = 0;
			imonth = 0;
			iday = 0;	
		} catch(Exception e) {
			return false;
		}
		try {
			iyear = Integer.parseInt(year);
			imonth = Integer.parseInt(month);
			iday = Integer.parseInt(day);
		} catch (Exception e) {
			return false;
		}
		if(iyear > 2022) {
			return false;
		}
		if(imonth > 12 || imonth < 1) {
			return false;
		}
		if(iday > 31 || iday < 1) {
			return false;
		}
		if(imonth == 2) {
			if(iday > 28) {
				return false;
			}
		} else if (imonth > 7) {
			if (imonth % 2 == 1) {
				if(iday > 30) {
					return false;
				}
			}
		} else {
			if(imonth % 2 == 0) {
				if(iday > 30) {
					return false;
				}
			}
		}
		return true;
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
