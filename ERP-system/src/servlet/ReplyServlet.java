package servlet;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Employee;
import bean.Reply;
import dao.ReplyDAO;
import factory.DAOFactory;

/**
 * Servlet implementation class ReplyServlet
 */
@WebServlet("/reply")
public class ReplyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// ���ñ��뷽ʽ
		request.setCharacterEncoding("utf8");
		// ��ȡ��Ϣ��ţ������и�ʽת��
		int messageID = Integer.parseInt(request.getParameter("messageID"));
		// ��ȡ�ظ�����
		String replyContent = request.getParameter("replyContent");
		// �õ���ʱ����һ��Ա���ڻظ�
		Employee employee = (Employee) request.getSession().getAttribute("employee");
		// ���Ա��û�е�¼
		if (employee == null) {
			request.setAttribute("error", "�ظ�ǰ�����ȵ�¼");
		} else {
			if (replyContent == null || "".equals(replyContent)) {
				request.setAttribute("error", "��������ظ�����");
			} else {
				// ��ȡ����
				ReplyDAO replyDAO = DAOFactory.getReplyDAO();
				//
				Reply reply = new Reply();
				/*
				 * ���øûظ�������
				 */
				reply.setReplyContent(replyContent);
				reply.setEmployeeID(employee.getEmployeeID());
				reply.setMessageID(messageID);
				reply.setReplyTime(new Date());
				// ��ӻظ�
				replyDAO.addReply(reply);
			}
		}
		// Я����Ϣ��Ų�����ת����
		request.getRequestDispatcher("/messageDetail?messageID=" + messageID).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
