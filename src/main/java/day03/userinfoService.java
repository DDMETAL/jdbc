package day03;

import java.util.List;
import java.util.Scanner;

/**
 * 针对用户操作的业务逻辑层
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
	 * 删除用户信息
	 * 要求用户输入要删除的用户名，然后将该用户删除
	 */
	private void delete() {
		
	}
	/**
	 * 修改用户信息
	 * 要求用户输入要求改的用户的用户名
	 * 然后输入要修改的用户其他信息
	 */
	private void update() {
		try {
			Scanner scan=new Scanner(System.in);
			System.out.println("请输入要修改的用户名：");
			String username=scan.nextLine();
			System.out.println("请输入新密码：");
			String password=scan.nextLine();
			System.out.println("请输入新邮箱：");;
			String email=scan.nextLine();
			System.out.println("请输入新昵称：");
			String nickname=scan.nextLine();
			System.out.println("请输入新account：");
			double account=Double.parseDouble(scan.nextLine());
			userinfo userinfo=new userinfo();
			
			userinfo.setUsername(username);
			userinfo.setPassword(password);
			userinfo.setEmail(email);
			userinfo.setNickname(nickname);
			userinfo.setAccount(account);
			userinfoDAO.update(userinfo);
			if(userinfoDAO.update(userinfo)) {
				System.out.println("修改成功");
			}else {
				System.out.println("修改失败");
			}
			
				
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 查询所有用户信息
	 * 将所有用户信息查询出来并按行输出
	 */
	private void findAll() {
		try {
			List<userinfo> userinfo=userinfoDAO.findAll();
			if(userinfo!=null) {
				System.out.println(userinfo);
			}else {
				System.out.println("无信息");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 查询用户信息
	 */
	@SuppressWarnings("unused")
	private void find() {
		try {
			Scanner scan=new Scanner(System.in);
			System.out.println("请输入要查询的用户名：");
			String username=scan.nextLine();
			
			userinfo userinfo=userinfoDAO.findByUserName(username);
			if(userinfo !=null) {
				System.out.println(userinfo);
			}else {
				System.out.println("查无此人");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 注册
	 */
	private void reg() {
		try {
			Scanner scanner = new Scanner(System.in);
			System.out.println("欢迎使用");
			System.out.println("请输入用户名：");
			String username=scanner.nextLine();
			System.out.println("请输入密码：");
			String password=scanner.nextLine();
			System.out.println("请输入邮箱：");
			String email=scanner.nextLine();
			System.out.println("请输入昵称：");
			String nickname=scanner.nextLine();
			
			userinfo userinfo=new userinfo();
			userinfo.setUsername(username);
			userinfo.setPassword(password);
			userinfo.setEmail(email);
			userinfo.setNickname(nickname);
			userinfo.setAccount(5000);
			
			userinfoDAO.save(userinfo);
			
			System.out.println("注册成功");
			
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("注册失败");
		}
	}
}
