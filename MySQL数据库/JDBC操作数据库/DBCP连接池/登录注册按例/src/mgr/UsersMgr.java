package mgr;

import java.util.Scanner;

import service.UsersService;
import service.impl.UsersServiceImpl;

//�û�������
public class UsersMgr {
	Scanner input = new Scanner(System.in);
	UsersService usersService = new UsersServiceImpl();
	private String username;
	private String password;

	/**
	 * ���˵�
	 */
	public void menu() {
		boolean flag = true;
		do {
			System.out.println("*****��ӭ����Ժεĵ�¼ϵͳ*****");
			System.out.println("1����¼");
			System.out.println("2��ע��");
			System.out.println("3���˳�");
			System.out.print("��ѡ��");
			int option = input.nextInt();
			switch (option) {
			case 1:
				login();
				break;
			case 2:
				register();
				break;
			case 3:
				System.exit(0);
			default:
				flag = false;
				break;
			}
		} while (flag);

	}

	public void login() {
		System.out.print("�����������û�����");
		username = input.next();
		System.out.print("�������������룺");
		password = input.next();
		boolean success = usersService.login(username, password);
		if (success) {
			System.out.println("��¼�ɹ�����ӭ����");
		} else {
			System.out.println("�û������������");
		}
	}

	public void register() {
		System.out.print("�����������û�����");
		username = input.next();
		System.out.print("�������������룺");
		password = input.next();
		boolean success = usersService.register(username, password);
		if(success) {
			System.out.println("ע��ɹ�");
		}else {
			System.out.println("�û����Ѵ��ڣ�������ע��");
		}
	}
}
