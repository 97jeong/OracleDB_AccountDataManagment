package AccountManagement;

import java.awt.Checkbox;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class UserView extends JFrame implements ActionListener {
	
	private final String query;
	private int user_id;
	private String id;
	private String password;
	private String nickname;
	private String username;
	private String gender;
	private String birth;
	private String address;
	private String phone;
	private String email;
	
	private JPanel bjpl;
	private JPanel jpl1;
	private JPanel jpl2;
	private JPanel jpl3;
	private JPanel jpl4;
	private JPanel jpl5;
	private JPanel jpl6;
	
	private JLabel lb_title;
	private JLabel lb_birthMessage;
	private JLabel lb_id;
	private JLabel lb_id2;
	private JLabel lb_nick;
	private JLabel lb_name;
	private JLabel lb_name2;
	private JLabel lb_birth;
	private JLabel lb_birth2;
	private JLabel lb_address;
	private JLabel lb_gender;
	private JLabel lb_gender2;
	private JLabel lb_email;
	private JLabel lb_email2;
	private JLabel lb_phone;
	private JLabel lb_phone2;
	
	private JTextField tf_nick;
	private JTextField tf_address;
	
	private JButton btn_update;
	private JButton btn_delete;
	private JButton btn_changePw;
	private JButton btn_logout;
	
	public UserView(String id) {
		
		super("AccountManagement Program");
		setSize(750, 450);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new FlowLayout(FlowLayout.LEFT, 20, 50));
		setLocationRelativeTo(null);
		
		this.query = "select * from account natural join member where id = '" + id + "'";
		init(query);
		
		
		bjpl = new JPanel(new GridLayout(6, 1, 10, 10));
		jpl1 = new JPanel(new GridLayout(1, 2, 10, 10)); // 타이틀
		jpl2 = new JPanel(new GridLayout(1, 4, 10, 10)); // 이름 / 성별
		jpl3 = new JPanel(new GridLayout(1, 4, 10, 10)); // 주소 / 생년월일
		jpl4 = new JPanel(new GridLayout(1, 4, 10, 10)); // 이메일 / 전화번호
		jpl5 = new JPanel(new GridLayout(1, 4, 10, 10)); // 아이디 / 닉네임 
		jpl6 = new JPanel(new GridLayout(1, 3, 10, 10)); // 저장 버튼 / 계정삭제 버튼 / 패스워드 변경 / 로그아웃
		
		lb_title = new JLabel(username + "님의 프로필 정보");
		jpl1.add(lb_title);
		if(isBirthday(birth) == true) {
			lb_birthMessage = new JLabel(username + "의 생일을 축하합니다!");
			jpl1.add(lb_birthMessage);
		}
		
		lb_name = new JLabel("이름");
		lb_name2 = new JLabel(this.username);
		//tf_name = new JTextField(this.username);
		jpl2.add(lb_name);
		jpl2.add(lb_name2);
		//jpl2.add(tf_name);
		
		lb_gender = new JLabel("성별");
		lb_gender2 = new JLabel(this.gender);
		jpl2.add(lb_gender);
		jpl2.add(lb_gender2);
		
		lb_address = new JLabel("주소");
		tf_address = new JTextField(address);
		jpl3.add(lb_address);
		jpl3.add(tf_address);
		
		lb_birth = new JLabel("생년월일");
		lb_birth2 = new JLabel(birth);
		jpl3.add(lb_birth);
		jpl3.add(lb_birth2);
		
		lb_email = new JLabel("이메일");
		lb_email2 = new JLabel(email);
		jpl4.add(lb_email);
		jpl4.add(lb_email2);
		
		lb_phone = new JLabel("핸드폰 번호");
		lb_phone2 = new JLabel(phone);
		jpl4.add(lb_phone);
		jpl4.add(lb_phone2);
		
		lb_id = new JLabel("아이디");
		lb_id2 = new JLabel(id);
		jpl5.add(lb_id);
		jpl5.add(lb_id2);
		
		lb_nick = new JLabel("닉네임");
		tf_nick = new JTextField(nickname);
		jpl5.add(lb_nick);
		jpl5.add(tf_nick);
		
		btn_update = new JButton("프로필 수정");
		btn_changePw = new JButton("비밀번호 변경");
		btn_delete = new JButton("계정 삭제");
		btn_logout = new JButton("Logout");
		jpl6.add(btn_update);
		jpl6.add(btn_changePw);
		jpl6.add(btn_delete);
		jpl6.add(btn_logout);
		
		btn_update.addActionListener(this);
		btn_changePw.addActionListener(this);
		btn_delete.addActionListener(this);
		btn_logout.addActionListener(this);
		
		bjpl.add(jpl1);
		bjpl.add(jpl2);
		bjpl.add(jpl3);
		bjpl.add(jpl4);
		bjpl.add(jpl5);
		bjpl.add(jpl6);
		add(bjpl);
		setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		DB db = new DB();
		if(e.getSource() == btn_delete) {
			int ans = JOptionPane.showConfirmDialog(null, "계정을 삭제하시겠습니까?", "Confirm", JOptionPane.YES_NO_OPTION);
			if(ans == JOptionPane.YES_OPTION) {
				String query = "delete from member where user_id = " + this.user_id;
				db.updateAccount(query);
				new LoginView();
				setVisible(false);
			}
		} else if(e.getSource() == btn_changePw) {
			String old_pw = JOptionPane.showInputDialog("사용자 확인을 위해 현재 PW를 입력하세요.");
			if(this.password.equals(old_pw)) {
				String new_pw = JOptionPane.showInputDialog("변경할 PW를 입력하세요.");
				String query = "update account set password = '" + new_pw + "' where user_id = '" + this.user_id + "'";
				db.updateAccount(query);
				JOptionPane.showMessageDialog(null, "패스워드가 변경되었습니다.", "패스워드 변경 완료", JOptionPane.INFORMATION_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(null, "패스워드가 일치하지 않습니다. 다시 확인해주세요.", "사용자 확인 실패", JOptionPane.INFORMATION_MESSAGE);
			}
		} else if(e.getSource() == btn_update) {
			this.address = tf_address.getText();
			this.nickname = tf_nick.getText();
			String query = "update member set address = '" + this.address + "' where user_id = '" + this.user_id + "'";
			db.updateAccount(query);
			query = "update account set nickname = '" + this.nickname + "' where user_id = '" + this.user_id + "'";
			db.updateAccount(query);
		} else if(e.getSource() == btn_logout) {
			new LoginView();
			setVisible(false);
		}
	}
	
	// 회원 정보 가져오는 메소드
	public void init(String query) {
		try {
			DB db = new DB();
			db.db_connect();
			Statement statement = db.getConnection().createStatement();
			ResultSet result_set = statement.executeQuery(query);
			result_set.next();
			
			this.user_id = result_set.getInt(1);
			this.id = result_set.getString(2);
			this.password = result_set.getString(3);
			this.nickname = result_set.getString(4);
			this.username = result_set.getString(5);
			this.gender = result_set.getString(6);
			this.birth = result_set.getString(7).substring(0, 10);
			this.address = result_set.getString(8);
			this.phone = result_set.getString(9);
			this.email = result_set.getString(10);
			
			result_set.close();
			statement.close();
			db.getConnection().close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("퀴리문 오류 발생");
			e.printStackTrace();
		}
	}
	
	// 회원의 생일과 오늘 날짜 비교
	public boolean isBirthday(String birth) {
		LocalDate now = LocalDate.now();            
		int monthValue = now.getMonthValue();    
		int dayOfMonth = now.getDayOfMonth();
		
		int month = Integer.parseInt(birth.split("-")[1]);
		int day = Integer.parseInt(birth.split("-")[2]);
		
		if(month == monthValue && day == dayOfMonth) {
			return true;
		} else {
			return false;
		}
	}
}
