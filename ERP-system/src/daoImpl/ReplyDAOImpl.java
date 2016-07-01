package daoImpl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.Reply;
import dao.ReplyDAO;
import util.DBUtil;
import util.Page;

public class ReplyDAOImpl implements ReplyDAO {

	@Override
	public void addReply(Reply reply) {
		// ��ȡ���ݿ�����
		Connection connection = DBUtil.getConnection();
		// ����sql���
		String sql = "insert into reply(replyContent,employeeID,messageID,replyTime) " + "values(?,?,?,?)";
		// ����Ԥ�������
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = connection.prepareStatement(sql);
			/*
			 * ���ø�����ѯ����
			 */
			preparedStatement.setString(1, reply.getReplyContent());
			preparedStatement.setInt(2, reply.getEmployeeID());
			preparedStatement.setInt(3, reply.getMessageID());
			// Date����ʽת��
			preparedStatement.setDate(4, new Date(reply.getReplyTime().getTime()));

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
	public List<Reply> getReplyList(int messageID, Page page) {
		// ��ȡ���ݿ�����
		Connection connection = DBUtil.getConnection();
		// ����sql���
		String sql = "select * from reply where messageID=? limit ?,?";
		/*
		 * ����Ԥ������䣬��ѯ�����
		 */
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<Reply> list = new ArrayList<Reply>();

		try {
			preparedStatement = connection.prepareStatement(sql);
			/*
			 * ���ò�ѯ����
			 */
			preparedStatement.setInt(1, messageID);
			preparedStatement.setInt(2, page.getBeginIndex());
			preparedStatement.setInt(3, page.getEveryPage());
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				Reply reply = new Reply();
				/*
				 * ȡ�ò�ѯ����Ĳ���
				 */
				reply.setReplyID(resultSet.getInt(1));
				reply.setReplyContent(resultSet.getString(2));
				reply.setEmployeeID(resultSet.getInt(3));
				reply.setMessageID(resultSet.getInt(4));
				reply.setReplyTime(resultSet.getDate(5));
				// ����������б�
				list.add(reply);
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
	public int getRepliesCount(int messageID) {
		// ��ȡ���ݿ�����
		Connection connection = DBUtil.getConnection();
		// ����sql���
		String sql = "select count(*) from reply where messageID=?";
		/*
		 * ����Ԥ������䣬��ѯ�����
		 */
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		int repliesCount = 0;

		try {
			preparedStatement = connection.prepareStatement(sql);
			// ���ò��Ҳ���
			preparedStatement.setInt(1, messageID);
			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				/*
				 * ȡ�ò�ѯ����Ĳ���
				 */
				repliesCount = resultSet.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.closeConnection(resultSet, preparedStatement, connection);
		}
		// ���ز�ѯ�б�
		return repliesCount;
	}

}
