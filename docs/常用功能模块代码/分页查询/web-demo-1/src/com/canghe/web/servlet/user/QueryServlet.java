package com.canghe.web.servlet.user;

/**
 * �û���ѯ
 */
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.canghe.dao.UserDaoImpl;
import com.canghe.domain.User;
import com.canghe.util.PageAssitant;

public class QueryServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println(">>> user query");

		/* 0. ������Ҫ�ĸ������� */
		// �û����ݲ�������
		// ���ú�̨��ѯ�����û���Ϣ�ķ������������ݸ���jspҳ��
		UserDaoImpl userDaoImpl = new UserDaoImpl();

		/* 1. ��ȡҳ����� */
		// ��ǰҳҳ��
		String currPage = request.getParameter("currPage");
		// ҳ���С
		String pageSize = request.getParameter("pageSize");

		/* 2. ����ҳ�����ֵ��������� */
		// ʵ��
		PageAssitant pa = new PageAssitant();
		// �����ܼ�¼��
		try {
			pa.setCount(userDaoImpl.queryCount());
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		// ���õ�ǰҳ��
		// �ж�, �Ƿ�Ϊ��
		if (currPage != null) {
			pa.setCurrPage(Integer.parseInt(currPage));
		}
		// ����ҳ���С
		// �ж�, �Ƿ�Ϊ��
		if (pageSize != null) {
			pa.setPageSize(Integer.parseInt(pageSize));
		}
		// �������, ��ҳ���뵱ǰҳ�Ĺ�ϵ
		if (pa.getTotalPage() < pa.getCurrPage()) {
			// Ĭ��ȥ��ѯ���һҳ
			pa.setCurrPage(pa.getTotalPage());
		}

		System.out.println(pa);
		try {
			//��ѯ
			List<User> userList = userDaoImpl.queryByPage(pa.getStart(), pa.getPageSize());
			// �жϲ鵽�Ľ��
			if (userList.size() >= 1) {
				// �鵽��
				// ����session������
				request.getSession().setAttribute("userList", userList);//ҳ������
				request.getSession().setAttribute("pa", pa); //ҳ������
				// ��ת����ʾҳ��
				request.getRequestDispatcher("page/user/list.jsp").forward(request, response);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
