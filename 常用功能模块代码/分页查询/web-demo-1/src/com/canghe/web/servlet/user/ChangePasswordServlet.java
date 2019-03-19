package com.canghe.web.servlet.user;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.canghe.dao.UserDaoImpl;
import com.canghe.domain.User;

public class ChangePasswordServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//�õ�ҳ���ύ������
		String newPassword = request.getParameter("newPassword");
		//�õ�session�������е�user
		User user = (User) request.getSession().getAttribute("loginInfo");
		user.setPassword(newPassword);
		//�����õ���passwordȥ�޸�����
		UserDaoImpl userDaoImpl = new UserDaoImpl();
		try {
			int count = userDaoImpl.modify(user);
			if(count>=1) {
				//�޸ĳɹ�
				response.sendRedirect(request.getContextPath()+"/page/user/changePassword.jsp");
				request.getSession().setAttribute("succsize", "�����޸ĳɹ�");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
