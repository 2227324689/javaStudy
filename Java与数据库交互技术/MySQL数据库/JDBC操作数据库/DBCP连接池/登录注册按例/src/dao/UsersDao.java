package dao;
/**
 * ���û������Ľӿ�
 * @author Administrator
 *
 */
public interface UsersDao {
	//��¼
	public Object[] login(String username,String password);
	//ע��
	public boolean registr(String username,String password);
}
