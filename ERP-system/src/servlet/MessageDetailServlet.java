package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Message;
import bean.Reply;
import dao.MessageDAO;
import dao.ReplyDAO;
import factory.DAOFactory;
import util.Page;
import util.PageUtil;

/**
 * Servlet implementation class MessageDetailServlet
 */
@WebServlet("/messageDetail")
public class MessageDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// ����ת����ַ
		String dispatcherUrl = "/jsp/messageDetail.jsp";
		// Ĭ�����õ�ǰҳΪ1
		int currentPage = 1;
		// ��ȡ��ǰҳ����
		String currentPageStr = request.getParameter("currentPage");
		// ������ڸò�����������Ϊ��ǰҳ
		if (currentPageStr != null) {
			currentPage = Integer.parseInt(currentPageStr);
		}
		/*
		 * ȡ����Ϣ��ţ�����������ת��
		 */
		String messageIDStr = request.getParameter("messageID");
		int messageID = Integer.parseInt(messageIDStr);
		
		// ȡ����Ϣ����
		MessageDAO messageDAO = DAOFactory.getMessageDAO();
		// ���ݱ�Ų�����Ϣ
		Message message = messageDAO.getMessage(messageID);
		// �洢��Ϣ
		request.setAttribute("message", message);
		
		//ȡ�ûظ�����
		ReplyDAO replyDAO=DAOFactory.getReplyDAO();
		//����ҳ����Ϣ
		Page page=PageUtil.getPage(5, replyDAO.getRepliesCount(messageID), currentPage);
		//������Ϣ��š�ҳ����Ϣȡ��һ�������Ļظ�
		List<Reply> replyList=replyDAO.getReplyList(messageID, page);
		/*
		 * �洢����
		 */
		request.setAttribute("page", page);
		request.setAttribute("replyList", replyList);
		
		//ת������Ϣ����ҳ��
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
