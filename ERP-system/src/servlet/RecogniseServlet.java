package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Employee;
import dao.EmployeeDAO;
import factory.DAOFactory;

/**
 * Servlet implementation class RecogniseServlet
 */
@WebServlet("/recognise")
public class RecogniseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// ���ñ��뷽ʽΪutf-8
		request.setCharacterEncoding("utf8");
		// ����ת����ַ
		String dispatcherUrl = "/jsp/recognise.jsp";
		// �����ض����ַ
		String redirectUrl = "jsp/index.jsp";
		// ȡ���ύ��Ա�����
		String employeeID = request.getParameter("employeeID");
		// ȡ���ύ������
		String password = request.getParameter("password");

		// ������Ϊ��
		if (employeeID == null || "".equals(employeeID)) {
			request.setAttribute("error", "����������");
			request.getRequestDispatcher(dispatcherUrl).forward(request, response);
		} else {
			// ��������Ϊ��
			if (password == null || "".equals(password)) {
				request.setAttribute("error", "������������");
				request.getRequestDispatcher(dispatcherUrl).forward(request, response);
			} else {
				// ȡ��employee����
				EmployeeDAO employeeDAO = DAOFactory.getEmployeeDAO();
				// ���ύ��Ա����Ŵ��ַ���ת��Ϊ������ʽ��Ȼ���øñ�Ų��Ҹ�Ա����Ϣ
				Employee employee = employeeDAO.getEmployeeByID(Integer.parseInt(employeeID));

				// �Ҳ�����Ա����Ϣ
				if (employee == null) {
					request.setAttribute("error", "��Ա����Ų�����");
					request.getRequestDispatcher(dispatcherUrl).forward(request, response);
				} else {
					// Ա�������ȷ������������ȷ������ͨ����֤����
					if (password.equals(employee.getPassword())) {
						// ʹ��session����employee�����Ϣ����sessin��
						request.getSession().setAttribute("employee", employee);
						// �ض�����ҳ
						response.sendRedirect(redirectUrl);
					} else {
						request.setAttribute("error", "���벻��ȷ");
						request.getRequestDispatcher(dispatcherUrl).forward(request, response);
					}
				}
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
