package com.servlet.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * �û��˳�
 * @author Administrator
 *
 */
public class Logoff extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		System.out.println(">>> user logoff");
		
		/* ���ٻỰ */
		//��ȡ�Ự
		HttpSession session = request.getSession();
		//����
		session.invalidate();
		
		/* ��ת����¼ҳ */
		response.sendRedirect("login.jsp");
	}

}











