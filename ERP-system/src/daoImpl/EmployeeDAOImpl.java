package daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bean.Employee;
import dao.EmployeeDAO;
import util.DBUtil;

public class EmployeeDAOImpl implements EmployeeDAO {

	@Override
	public Employee getEmployeeByID(int employeeID) {
		// ��ȡ���ݿ�����
		Connection connection = DBUtil.getConnection();
		// ��ѯ���
		String sql = "select * from employee where employeeID=?";
		/*
		 * �ֱ�����Ԥ������䣬���������ѯ����
		 */
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Employee employee = null;

		try {
			preparedStatement = connection.prepareStatement(sql);
			// ���ò�ѯ����λԱ�����
			preparedStatement.setInt(1, employeeID);
			resultSet = preparedStatement.executeQuery();

			// �õ���ѯ���
			if (resultSet.next()) {
				// ������ѯ����
				employee = new Employee();
				/*
				 * �õõ��Ĳ�ѯ������ֱ����ò�ѯ����ĸ�������
				 */
				employee.setEmployeeID(resultSet.getInt(1));
				employee.setEmployeeName(resultSet.getString(2));
				employee.setEmployeeGender(resultSet.getBoolean(3));
				employee.setEmployeeBirth(resultSet.getDate(4));
				employee.setEmployeePhone(resultSet.getString(5));
				employee.setPassword(resultSet.getString(6));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			// �ر���������
			DBUtil.closeConnection(resultSet, preparedStatement, connection);
		}
		// ���ز�ѯ����
		return employee;
	}

}
