package Ecust_library;
import java.sql.* ;  // for standard JDBC programs
import javax.swing.*;


import java.awt.EventQueue;
import java.awt.GraphicsConfiguration;
import java.awt.event.*;


/**
 *  this is for user's login
 */

public class UsrLogin extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Library library;
	public String currentUser;
	
	private JLabel lblUserName, lblPassWord;
	private JTextField txtUserName;
	private JPasswordField pwdPassWord;
	private JButton btnLogin, btnClose;
	private String pwd, user;
	
	
	public boolean check(String code, String password) //登录验证 
	{  
	    String sql="select *from account where code='"+code+"' and password='"+password+"'";  
	    try(  
	        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/user"  
	                + "user=root&password=root&useUnicode=true&characterEncoding=UTF8");  
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


	public UsrLogin(Library l){
		this.library=l;
		//窗口设置
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setTitle("读者登陆");
		this.setLayout(null);
		this.setLocation(250, 40);
		
		lblUserName = new JLabel();
		lblUserName.setText("学工号:");
		lblUserName.setBounds(170, 40, 70, 30);
		
		lblPassWord = new JLabel();
		lblPassWord.setText("密码:");
		lblPassWord.setBounds(170, 100, 70, 30);
		
		txtUserName = new JTextField(20);
		txtUserName.setBounds(240, 40, 200, 30);
		
		pwdPassWord = new JPasswordField(20);
		pwdPassWord.setBounds(240, 100, 200, 30);
		
		btnLogin = new JButton("登陆");
		btnLogin.setBounds(230, 160, 75, 30);
		
		btnClose = new JButton("退出");
		btnClose.setBounds(340, 160, 75, 30);
		
		this.add(lblUserName);
		this.add(txtUserName);
		this.add(lblPassWord);
		this.add(pwdPassWord);
		this.add(btnLogin);
		this.add(btnClose);
		this.setResizable(false);
		this.setSize(600, 280);
		this.setVisible(true);
		
		btnLogin.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae){
				pwd = new String(pwdPassWord.getPassword());
				user = txtUserName.getText();
				if(user.length() == 0){
					JOptionPane.showMessageDialog(UsrLogin.this, "请输入您的学工号.", "登陆失败", JOptionPane.ERROR_MESSAGE);
				} else{
					if(pwd.length() == 0){
						JOptionPane.showMessageDialog(UsrLogin.this, "请输入您的密码.", "登陆失败", JOptionPane.ERROR_MESSAGE);
					} else{
						currentUser = user;
						if(currentUser != null){
							if(check(user,pwd)){
								JOptionPane.showMessageDialog(UsrLogin.this, "Welcome !", "Welcome", JOptionPane.INFORMATION_MESSAGE);
								UsrLogin.this.dispose();
								/** 接入用户界面   new Reader'sInterface(library, currentUser);
								*
								*/
							} else{
								JOptionPane.showMessageDialog(UsrLogin.this, "无效的学工号或密码.", "登陆失败", JOptionPane.ERROR_MESSAGE);
								pwdPassWord.setText("");
							}
						} else{
							JOptionPane.showMessageDialog(UsrLogin.this, "无效的学工号或密码.", "登陆失败", JOptionPane.ERROR_MESSAGE);
							pwdPassWord.setText("");
						}
					}
				}
			}
		});   
		
		btnClose.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae){
				UsrLogin.this.dispose();
			}
		});
	}}


