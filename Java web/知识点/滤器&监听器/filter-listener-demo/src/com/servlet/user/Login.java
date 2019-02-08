package com.servlet.user;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bean.User;
import com.dao.UserData;

/**
 * �û���¼
 * @author Administrator
 *
 */
public class Login extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		System.out.println(">>> user login");
		
		/* ��ȡ��¼��Ϣ */
		//�û���
		String userName = request.getParameter("userName");
		//����
		String password = request.getParameter("password");
		
		/* ��ѯ */
		//user���ݲ�������
		UserData ud = new UserData();
		//sql
		String sql = "select * from t_user where u_userName = ? and u_password = ?";
		//��ѯ
		List<User> list = ud.query(sql, userName, password);
		
		/* �жϵ�¼��� */
		/* ��ȡ��������� */
		//�Ự
		HttpSession session = request.getSession();
		//Ӧ�ó���
		ServletContext application = session.getServletContext();
		
		if(list.size() == 1) {
			//��¼�ɹ�
			//���õ�¼ʱЧ
			session.setMaxInactiveInterval(10);
			//�����¼�û������ݵ��Ự
			session.setAttribute("loginInfo", list.get(0));
			//��ת����ҳ
			response.sendRedirect("page/main.jsp");
		}else {
			//��¼ʧ��
			//����ʧ����Ϣ���Ự
			session.setAttribute("errMsg", "�û������������!!!");
			//��ת����¼ҳ
			response.sendRedirect("login.jsp");
		}
		
	}

}













