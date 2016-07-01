package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Message;
import dao.MessageDAO;
import factory.DAOFactory;
import util.Page;
import util.PageUtil;

/**
 * Servlet implementation class MessageListServlet
 */
@WebServlet("/messageList")
public class MessageListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// ����ת����ַ
		String dispatcherUrl = "/jsp/messageList.jsp";
		// Ĭ�����õ�ǰҳΪ1
		int currentPage = 1;
		// ��ȡ��ǰҳ����
		String currentPageStr = request.getParameter("currentPage");
		// ������ڸò�����������Ϊ��ǰҳ
		if (currentPageStr != null) {
			currentPage = Integer.parseInt(currentPageStr);
		}

		// ��ȡ����
		MessageDAO messageDAO = DAOFactory.getMessageDAO();
		// ����ҳ����Ϣ
		Page page = PageUtil.getPage(5, messageDAO.getMessagesCount(), currentPage);
		// ����ҳ����Ϣȡ��һ����������Ϣ
		List<Message> messageList = messageDAO.getMessageList(page);

		/*
		 * �洢��Ϣ�б��ҳ����Ϣ��ת������Ϣ�б�
		 */
		request.setAttribute("list", messageList);
		request.setAttribute("page", page);
		request.getRequestDispatcher(dispatcherUrl).forward(request, response);
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
