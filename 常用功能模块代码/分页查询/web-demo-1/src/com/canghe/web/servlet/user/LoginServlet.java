package com.canghe.web.servlet.user;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.canghe.dao.UserDaoImpl;
import com.canghe.domain.User;

public class LoginServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println(">>> user login");
		//������������
		request.setCharacterEncoding("utf-8");
		/*��ȡ��¼��Ϣ*/
		//�û���
		String userName = request.getParameter("userName");
		//����
		String password = request.getParameter("password");
		/*���ú�̨������ѯ*/
		//UserDaoImpl��������
		UserDaoImpl userDaoImpl = new UserDaoImpl();
		try {
			//������ز�ѯ���� 
			User queryUser = userDaoImpl.queryByNamePsd(userName, password);
			//�жϵ�¼���
			if(queryUser!=null) {
				//��¼�ɹ�
				//�����û���Ϣ���Ự����
				request.getSession().setAttribute("loginInfo", queryUser);
				//��ת����ҳ
				response.sendRedirect("page/main.jsp");
			}else {
				//��¼ʧ��
				//���������Ϣ��������
				request.getSession().setAttribute("errMsg", "�û������������!");
				//��ת����¼Ҳ
				response.sendRedirect("login.jsp");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
