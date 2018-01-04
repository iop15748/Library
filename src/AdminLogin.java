
import java.sql.* ;  // for standard JDBC programs
import javax.swing.*;


/**
 * this is for administrator's login       
 */
public class AdminLogin extends JFrame {

    
	private static final long serialVersionUID = 1L;
	// FrmAdministratorLog frmAdminLog = new FrmAdministratorLog();

	private Library library;
	private JTextField loginName;
	private JPasswordField loginPassWord;
	
	public AdminLogin() {
		this(null);
	}
	public boolean check(String code, String password) //登录验证 
	{  
	    String sql="select *from admin where code='"+code+"' and password='"+password+"'";  
	    try(  
	        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/library?"  
	                + "user=root&password=xxxx1998&useUnicode=true&characterEncoding=UTF8");  
	        Statement pstmt = conn.createStatement();  
	            ResultSet rs=pstmt.executeQuery(sql))  
	      
	    {   //如果查询的ResultSet里有超过一条的记录，则登录成功  
	            if (rs.next())  
	            {  
	                return true;  
	            }  
	          
	    }  
	    catch(Exception e)  
	    {  
	        e.printStackTrace();  
	    }  
	    return false;  
	}
	
	
	public AdminLogin(Library lib) {
		this.library = lib;
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setSize(600, 280);
		this.setLocation(250, 40);
		this.setVisible(true);
		this.setTitle("管理员登陆");
		getContentPane().setLayout(null);

		JLabel label1 = new JLabel("管理员学工号: ");
		label1.setBounds(47, 54, 123, 120);
		getContentPane().add(label1);

		JLabel label2 = new JLabel("密码: ");
		label2.setBounds(100, 85, 120, 120);
		getContentPane().add(label2);

		// Administrator Log Name
		loginName = new JTextField();
		loginName.setBounds(180, 100, 290, 25);
		getContentPane().add(loginName);

		// Administrator Log PassWord
		loginPassWord = new JPasswordField();
		loginPassWord.setBounds(180, 131, 290, 25);
		getContentPane().add(loginPassWord);

		JButton BtnLogin = new JButton("登陆");
		BtnLogin.setBounds(380, 190, 90, 25);
		getContentPane().add(BtnLogin);
		

		BtnLogin.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				// Correct Info. provided
				String userName = loginName.getText();
				String password = new String(loginPassWord.getPassword());
				
				
				
				  // back door
				boolean accessBackDoor = userName.equals("diwuzu")
						&& password.equals("diwuzu");
				
				// normal login
			//	User usr = library.login(userName, password);
			//	boolean isSuccessLogin = false;
			//	if (usr!=null) {
			//		if (usr.isAdmin())
			//			isSuccessLogin=true;
			//	}
				
				// success login
				if (accessBackDoor || check(userName,password)) {
					JOptionPane.showMessageDialog(AdminLogin.this, "登陆成功");
					AdminLogin.this.dispose();
					menu1 window = new menu1();
					window.frame.setVisible(true);
				} else {
					// Wrong info. provided
					JOptionPane.showMessageDialog(AdminLogin.this, "对不起,输入错误!");
					

				}
			}
		});
	}

}
