package AccountManagement;

import java.sql.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DB {
	
	private Connection connection;
	private Statement statement;
	private ResultSet result_set;
	private String oracle_url;
	private String user_id;
	private String user_password;
	
	public DB() {
		this.oracle_url = "jdbc:oracle:thin:@localhost:1521:xe";
		this.user_id = "members";
		this.user_password = "1234";
	}
	
	public void db_connect() {
		try { 
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch(ClassNotFoundException e) {
			System.out.println("JDBC 드라이버가 없습니다.");
			e.printStackTrace();
		}
		
		/*
		 * DB 연결
		 */
		System.out.println("DB connection is ready!");
		try {
			this.connection = DriverManager.getConnection(this.oracle_url, this.user_id, this.user_password);
			System.out.println("DB connection is successful!");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("DB 연결 실패");
			e.printStackTrace();
		}
	}
	
	public boolean checkOverlap(String tablename, String column, String domain) {
		boolean checked = true;
		String query = "select * from " + tablename + " where " + column + "= '" + domain + "'";
		db_connect();
		try {
			statement = connection.createStatement();
			result_set = statement.executeQuery(query);
			if (result_set.next() == false) {
				checked = true;
			} else {
				checked = false;
			}
			result_set.close();
			statement.close();
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("퀴리문 오류 발생");
			e.printStackTrace();
		}
		return checked;
	}
	
	public void insert_member(int user_id, String username, String gender, String birth, String address, String phone, String email) {
		String query = "insert into member(user_id, username, gender, birth, address, phone, email) values(?, ?, ?, to_date(?, 'yyyy-mm-dd'), ?, ?, ?)";
		PreparedStatement preparedStatement = null;
		db_connect();
		try {
			preparedStatement = connection.prepareStatement(query);
			// Oracle 준비되었음.
			preparedStatement.setInt(1,user_id);
			preparedStatement.setString(2,username);
			preparedStatement.setString(3,gender);
			preparedStatement.setString(4, birth);
			preparedStatement.setString(5, address);
			preparedStatement.setString(6, phone);
			preparedStatement.setString(7, email);
			preparedStatement.executeUpdate(); // 완료
			preparedStatement.close();
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void insert_account(String id, String password, String nickname, int user_id) {
		String query = "insert into account(id, password, nickname, user_id) values(?, ?, ?, ?)";
		PreparedStatement preparedStatement = null;
		db_connect();
		try {
			preparedStatement = connection.prepareStatement(query);
			// Oracle 준비되었음.
			preparedStatement.setString(1, id);
			preparedStatement.setString(2, password);
			preparedStatement.setString(3, nickname);
			preparedStatement.setInt(4, user_id);
			preparedStatement.executeUpdate(); // 완료
			preparedStatement.close();
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void updateAccount(String query) {
		PreparedStatement preparedStatement = null;
		db_connect();
		try{
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.executeUpdate(); // 완료
			preparedStatement.close();
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("퀴리문 오류 발생");
			e.printStackTrace();
		}
	}
	
	public boolean checkOverlap(String tablename, String column, int domain) {
		boolean checked = true;
		String query = "select * from " + tablename + " where " + column + "= " + domain;
		db_connect();
		try {
			statement = connection.createStatement();
			result_set = statement.executeQuery(query);
			if (result_set.next() == false) {
				checked = true;
			} else {
				checked = false;
			}
			result_set.close();
			statement.close();
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("퀴리문 오류 발생");
			e.printStackTrace();
		}
		return checked;
	}

	public String search_DB(String query) {
		String result = null;
		try {
			statement = connection.createStatement();
			result_set = statement.executeQuery(query);
			if(result_set.next()) {
				result = result_set.getString(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("퀴리문 오류 발생");
			e.printStackTrace();
		}
		return result;
	}
	
	public ArrayList<PersonalInfo> search_admin(String query) {
		ArrayList<PersonalInfo> list = new ArrayList<>();
		try {
			statement = connection.createStatement();
			result_set = statement.executeQuery(query);
			while(result_set.next()) {
				list.add(new PersonalInfo(result_set.getString(1), result_set.getString(2), result_set.getString(3), result_set.getString(4), result_set.getString(5), result_set.getString(6).substring(0,10), result_set.getString(7), result_set.getString(8), result_set.getString(9)));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("퀴리문 오류 발생");
			e.printStackTrace();
		}
		return list ;
	}
	
	public Statement getStatement() {
		return statement;
	}

	public void setStatement(Statement statement) {
		this.statement = statement;
	}

	public ResultSet getResult_set() {
		return result_set;
	}

	public void setResult_set(ResultSet result_set) {
		this.result_set = result_set;
	}

	public Connection getConnection() {
		return connection;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	public String getOracle_url() {
		return oracle_url;
	}

	public void setOracle_url(String oracle_url) {
		this.oracle_url = oracle_url;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getUser_password() {
		return user_password;
	}

	public void setUser_password(String user_password) {
		this.user_password = user_password;
	}
}
