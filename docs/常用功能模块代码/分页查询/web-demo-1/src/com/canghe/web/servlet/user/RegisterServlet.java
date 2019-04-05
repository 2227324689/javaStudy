package com.canghe.web.servlet.user;
/**
 * �û�ע��
 */
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.canghe.dao.UserDaoImpl;
import com.canghe.domain.User;

public class RegisterServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//��ȡҳ�����
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		int gender =Integer.valueOf( request.getParameter("gender"));
		//�����ݷ�װ��javabean����
		//�õ���ǰ����
		Date date = new Date();
		//����ת������
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		//������ת����ָ����ʽ���ַ���
		String registeTime = format.format(date);
		//�����趨�Ĺ��캯������javabean����
		User user = new User(userName,password,gender,registeTime);
		//�����õ��Ĳ�����������������ݿ�
		UserDaoImpl userDaoImpl = new UserDaoImpl();
		try {
			int count = userDaoImpl.add(user);
			//�ж��Ƿ�ע��ɹ�
			if(count>=1) {
				//ע��ɹ�������ת��ҳ,������Ϣ����session������
				request.getSession().setAttribute("user", user);
				response.sendRedirect(request.getContextPath()+"/login.jsp");
			}else {
				//ע��ʧ�ܡ�����ת����ע��,ͨ������ע��ʧ�� 
				response.sendRedirect("/page/user/register.jsp");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
