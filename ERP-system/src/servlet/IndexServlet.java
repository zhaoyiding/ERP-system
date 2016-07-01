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
 * Servlet implementation class IndexServlet
 */
@WebServlet({  })
public class IndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// ȡ�ù���
		MessageDAO messageDAO = DAOFactory.getMessageDAO();
		// ����������Ϣҳ����Ϣ
		Page page = PageUtil.getPage(5, messageDAO.getMessagesCount(), 1);
		// ȡ��������Ϣ�б�
		List<Message> messageList = messageDAO.getMessageList(page);
		/*
		 * �洢������Ϣ�б�ת������ҳ��ʾ
		 */
		request.setAttribute("messageList", messageList);
		request.getRequestDispatcher("/jsp/index.jsp").forward(request, response);
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
