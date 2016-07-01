package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBUtil {
	private static final String driver = "com.mysql.jdbc.Driver";// ����
	private static final String url = "jdbc:mysql://localhost:3306/erpsystem";// ���ݿ�url
	private static final String user = "root";// ���ݿ��û�
	private static final String password = "admin";// ���ݿ�����

	public static Connection getConnection() {
		Connection connection = null;
		try {
			// ��������
			Class.forName(driver);
			// ��ȡ���ݿ�����
			connection = DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connection;
	}

	public static void closeConnection(ResultSet resultSet, PreparedStatement preparedStatement,
			Connection connection) {
		// �رս����
		if (resultSet != null) {
			try {
				resultSet.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		// �ر�Ԥ�������
		if (preparedStatement != null) {
			try {
				preparedStatement.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		// �ر����ݿ�����
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
