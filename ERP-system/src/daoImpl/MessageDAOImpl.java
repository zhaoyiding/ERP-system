package daoImpl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.Message;
import dao.MessageDAO;
import util.DBUtil;
import util.Page;

public class MessageDAOImpl implements MessageDAO {

	@Override
	public void addMessage(Message message) {
		// ��ȡ���ݿ�����
		Connection connection = DBUtil.getConnection();
		// ����sql���
		String sql = "insert into message(messageTitle,messageContent,employeeID,publishTime) " + "values(?,?,?,?)";
		// ����Ԥ�������
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = connection.prepareStatement(sql);
			/*
			 * ���ø�����ѯ����
			 */
			preparedStatement.setString(1, message.getMessageTitle());
			preparedStatement.setString(2, message.getMessageContent());
			preparedStatement.setInt(3, message.getEmployeeID());
			// Date����ʽת��
			preparedStatement.setDate(4, new Date(message.getPublishTime().getTime()));

			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			// �رձ���
			DBUtil.closeConnection(null, preparedStatement, connection);
		}

	}

	@Override
	public List<Message> getMessageList(Page page) {
		// ��ȡ���ݿ�����
		Connection connection = DBUtil.getConnection();
		// ����sql���
		String sql = "select * from message order by publishTime desc limit ?,?";
		/*
		 * ����Ԥ������䣬��ѯ�����
		 */
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<Message> list = new ArrayList<Message>();

		try {
			preparedStatement = connection.prepareStatement(sql);
			/*
			 * ���ò�ѯ����
			 */
			preparedStatement.setInt(1, page.getBeginIndex());
			preparedStatement.setInt(2, page.getEveryPage());
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				Message message = new Message();
				/*
				 * ȡ�ò�ѯ����Ĳ���
				 */
				message.setMessageID(resultSet.getInt(1));
				message.setMessageTitle(resultSet.getString(2));
				message.setMessageContent(resultSet.getString(3));
				message.setEmployeeID(resultSet.getInt(4));
				message.setPublishTime(resultSet.getDate(5));
				// ����������б�
				list.add(message);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.closeConnection(resultSet, preparedStatement, connection);
		}
		// ���ز�ѯ�б�
		return list;
	}

	@Override
	public Message getMessage(int messageID) {
		// ��ȡ���ݿ�����
		Connection connection = DBUtil.getConnection();
		// ����sql���
		String sql = "select * from message where messageID=?";
		/*
		 * ����Ԥ������䣬��ѯ�����
		 */
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Message message = null;

		try {
			preparedStatement = connection.prepareStatement(sql);
			/*
			 * ���ò�ѯ����
			 */
			preparedStatement.setInt(1, messageID);
			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				message = new Message();
				/*
				 * ȡ�ò�ѯ����Ĳ���
				 */
				message.setMessageID(resultSet.getInt(1));
				message.setMessageTitle(resultSet.getString(2));
				message.setMessageContent(resultSet.getString(3));
				message.setEmployeeID(resultSet.getInt(4));
				message.setPublishTime(resultSet.getDate(5));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.closeConnection(resultSet, preparedStatement, connection);
		}
		// ���ز�ѯ�б�
		return message;
	}

	@Override
	public int getMessagesCount() {
		// ��ȡ���ݿ�����
		Connection connection = DBUtil.getConnection();
		// ����sql���
		String sql = "select count(*) from message";
		/*
		 * ����Ԥ������䣬��ѯ�����
		 */
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		int messagesCount = 0;

		try {
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				/*
				 * ȡ�ò�ѯ����Ĳ���
				 */
				messagesCount = resultSet.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.closeConnection(resultSet, preparedStatement, connection);
		}
		// ���ز�ѯ�б�
		return messagesCount;
	}

}
