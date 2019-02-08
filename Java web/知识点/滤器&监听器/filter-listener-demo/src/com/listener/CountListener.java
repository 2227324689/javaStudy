package com.listener;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * ������������
 * @author Administrator
 *
 */
public class CountListener implements HttpSessionListener {

	@Override
	public void sessionCreated(HttpSessionEvent event) {
		
		System.out.println(">>> session create");
		
		/* ��ȡ�Ự����ǰ���������� */
		//��ȡapplication
		ServletContext application = event.getSession().getServletContext();
		//��������, ��������
		int count = 0;
		//��ȡ��������
		Object obj = application.getAttribute("count");
		//�ж�, obj�Ƿ�Ϊnull
		if(obj != null) {
			//������������ֵ
			count = Integer.parseInt(obj+"");
		}
		
		/* ��������+1 */
		count++;
		
		/* �����application */
		application.setAttribute("count", count);
		
		//��ʾ��ǰ��������
		System.out.println("��������+1, ��ǰ����" + count + "��");
		
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent event) {
		
		System.out.println(">>> session destroy");
		
		/* ��ȡ�Ự����ǰ���������� */
		//��ȡapplication
		ServletContext application = event.getSession().getServletContext();
		//��������, ��������
		int count = Integer.parseInt(application.getAttribute("count")+"");
		
		/* ��������-1 */
		count--;
		
		/* �����application */
		application.setAttribute("count", count);
		
		//��ʾ��ǰ��������
		System.out.println("��������-1, ��ǰ����" + count + "��");
	}

}
