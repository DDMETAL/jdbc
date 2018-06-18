package day03;

import java.util.List;
import java.util.Scanner;

/**
 * ����û�������ҵ���߼���
 * @author NiCo
 *
 */
public class userinfoService {
	private userinfoDAO userinfoDAO=new userinfoDAO();
	public static void main(String[] args) {
		userinfoService service=new userinfoService();
//		service.reg();
//		service.find();
		service.findAll();
//		service.update();
	}
	/**
	 * ɾ���û���Ϣ
	 * Ҫ���û�����Ҫɾ�����û�����Ȼ�󽫸��û�ɾ��
	 */
	private void delete() {
		
	}
	/**
	 * �޸��û���Ϣ
	 * Ҫ���û�����Ҫ��ĵ��û����û���
	 * Ȼ������Ҫ�޸ĵ��û�������Ϣ
	 */
	private void update() {
		try {
			Scanner scan=new Scanner(System.in);
			System.out.println("������Ҫ�޸ĵ��û�����");
			String username=scan.nextLine();
			System.out.println("�����������룺");
			String password=scan.nextLine();
			System.out.println("�����������䣺");;
			String email=scan.nextLine();
			System.out.println("���������ǳƣ�");
			String nickname=scan.nextLine();
			System.out.println("��������account��");
			double account=Double.parseDouble(scan.nextLine());
			userinfo userinfo=new userinfo();
			
			userinfo.setUsername(username);
			userinfo.setPassword(password);
			userinfo.setEmail(email);
			userinfo.setNickname(nickname);
			userinfo.setAccount(account);
			userinfoDAO.update(userinfo);
			if(userinfoDAO.update(userinfo)) {
				System.out.println("�޸ĳɹ�");
			}else {
				System.out.println("�޸�ʧ��");
			}
			
				
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * ��ѯ�����û���Ϣ
	 * �������û���Ϣ��ѯ�������������
	 */
	private void findAll() {
		try {
			List<userinfo> userinfo=userinfoDAO.findAll();
			if(userinfo!=null) {
				System.out.println(userinfo);
			}else {
				System.out.println("����Ϣ");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * ��ѯ�û���Ϣ
	 */
	@SuppressWarnings("unused")
	private void find() {
		try {
			Scanner scan=new Scanner(System.in);
			System.out.println("������Ҫ��ѯ���û�����");
			String username=scan.nextLine();
			
			userinfo userinfo=userinfoDAO.findByUserName(username);
			if(userinfo !=null) {
				System.out.println(userinfo);
			}else {
				System.out.println("���޴���");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * ע��
	 */
	private void reg() {
		try {
			Scanner scanner = new Scanner(System.in);
			System.out.println("��ӭʹ��");
			System.out.println("�������û�����");
			String username=scanner.nextLine();
			System.out.println("���������룺");
			String password=scanner.nextLine();
			System.out.println("���������䣺");
			String email=scanner.nextLine();
			System.out.println("�������ǳƣ�");
			String nickname=scanner.nextLine();
			
			userinfo userinfo=new userinfo();
			userinfo.setUsername(username);
			userinfo.setPassword(password);
			userinfo.setEmail(email);
			userinfo.setNickname(nickname);
			userinfo.setAccount(5000);
			
			userinfoDAO.save(userinfo);
			
			System.out.println("ע��ɹ�");
			
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("ע��ʧ��");
		}
	}
}
