package servlet;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Employee;
import bean.Message;
import dao.MessageDAO;
import factory.DAOFactory;

/**
 * Servlet implementation class MessagePublishServlet
 */
@WebServlet("/messagePublish")
public class MessagePublishServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// ���ñ��뷽ʽΪutf-8
		request.setCharacterEncoding("utf8");
		/*
		 * ������Ϣ�ύ�ɹ���ʧ��ʱ��ת����ַ
		 */
		String successUrl = "/messageList";
		String failureUrl = "/jsp/messagePublish.jsp";
		/*
		 * ȡ���ύ�ı��������
		 */
		String messageTitle = request.getParameter("messageTitle");
		String messageContent = request.getParameter("messageContent");
		// ȡ�ô�ʱ�����ĸ�Ա���ڷ�����Ϣ
		Employee employee = (Employee) request.getSession().getAttribute("employee");

		// ���Ա��û�е�¼������
		if (employee == null) {
			request.setAttribute("error", "������Ϣ�����ȵ�¼");
			request.getRequestDispatcher(failureUrl).forward(request, response);
		} else {
			// �����Ϣ����Ϊ�գ�����
			if (messageTitle == null || "".equals(messageTitle)) {
				request.setAttribute("error", "�����������");
				request.getRequestDispatcher(failureUrl).forward(request, response);
			} else {
				Message message = new Message();
				// ����Ҫ��������Ϣ����
				message.setMessageTitle(messageTitle);
				message.setMessageContent(messageContent);
				message.setEmployeeID(employee.getEmployeeID());
				message.setPublishTime(new Date());

				// ȡ����Ϣ����
				MessageDAO messageDAO = DAOFactory.getMessageDAO();
				// �����Ϣ
				messageDAO.addMessage(message);
				// ת������Ϣ�б����
				request.getRequestDispatcher(successUrl).forward(request, response);
			}
		}
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
