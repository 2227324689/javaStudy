package com.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bean.User;

/**
 * ��¼����
 * @author Administrator
 *
 */
public class LoginFilter implements Filter {

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		
		System.out.println(">>> login filter");
		
		/* 0. ���������Ӧ�ĸ�ʽת����http��ʽ */
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		
		/* �ж�, �Ự���Ƿ��е�¼��Ϣ */
		//��ȡ�Ự
		HttpSession session = request.getSession();
		//��ȡ��¼��Ϣ
		Object loginInfo = session.getAttribute("loginInfo");
//		User loginInfo = (User) session.getAttribute("loginInfo");
		
		/*
		 *  ���ڵ�¼�ɹ�֮ǰ, һ��û�е�¼��Ϣ, 
		 *  ��˷��ʵ�¼ҳ�ͺ�̨��¼���ܵ����������� 
		 */
		/* ��ȡ����Ŀ���·�� */
		//ȫ·��
		String url = request.getRequestURI();
		//��ȡĿ����Դ������(�����ļ���·��)
		String name = url.substring(url.lastIndexOf("/")+1);
		//��ȡĿ����Դ�ĺ�׺
		String sufix = name.substring(name.lastIndexOf(".")+1);
		
		System.out.println("ȫ·��:"+url);
		System.out.println("��Դ��:"+name);
		System.out.println("��׺��:"+sufix);
		
		//�ж�, �Ƿ�Ϊnull
		if(loginInfo != null) {
			//�Ѿ���¼, ����
			chain.doFilter(request, response);
		}else if(name.equals("") || name.equals("login.jsp")
				|| name.equals("login") || sufix.equals("css")
				|| sufix.equals("js")) {
			chain.doFilter(request, response);
		}else {
			//δ��¼, ���¼ʧЧ
			response.sendRedirect("/filter-listener-demo/login.jsp");
		}
		
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
	}

}
