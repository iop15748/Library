import User.User;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.Color;

import java.awt.EventQueue;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.naming.spi.DirStateFactory.Result;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;

import com.mysql.jdbc.PreparedStatement;

import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.HierarchyListener;
import java.awt.event.HierarchyEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class ReturnBook {
	public JFrame frame;
	private float overmoney; // 计算选中的罚款总额
	private int booknumber; // 初始化获取登录用户的借书数量变量
	private int credit; // 存储用户的信誉积分
	private String code = User.currentUser; // 与登录界面的接口，传递用户卡号
	private String driver = "com.mysql.jdbc.Driver"; // 不同的数据库需要加载不同的参数，这里加载的是MySQL数据库的驱动
	private String url = "jdbc:mysql://localhost:3306/library"; // URL指向要访问的数据库名mySql
	private String user = "root"; // MySQL配置时的用户名
	private String password = "xxxx1998"; // MySQL配置时的密码
	private String[] borrowbookcode = new String[10]; // 设置存储所借书号的数组
	private String[] borrowbooktime = new String[10]; // 设置存储借书时间的数组

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ReturnBook window = new ReturnBook();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ReturnBook() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("\u8FD8\u4E66");
		frame.setBounds(100, 100, 700, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("\u8BF7\u9009\u62E9\u60A8\u8981\u5F52\u8FD8\u7684\u56FE\u4E66");
		lblNewLabel.setForeground(Color.BLUE);
		lblNewLabel.setFont(new Font("微软雅黑", Font.BOLD, 24));
		lblNewLabel.setBounds(210, 15, 240, 36);
		frame.getContentPane().add(lblNewLabel);

		JButton btnNewButton = new JButton("\u786E\u8BA4\u5F52\u8FD8");
		btnNewButton.setFont(new Font("幼圆", Font.BOLD, 20));
		btnNewButton.setBounds(541, 478, 122, 51);
		frame.getContentPane().add(btnNewButton);

		JButton btnNewButton_1 = new JButton("\u8FD4\u56DE");
		btnNewButton_1.setFont(new Font("幼圆", Font.BOLD, 20));
		btnNewButton_1.setBounds(15, 478, 122, 51);
		frame.getContentPane().add(btnNewButton_1);

		JRadioButton rdbtnNewRadioButton = new JRadioButton("New radio button");
		rdbtnNewRadioButton.setFont(new Font("宋体", Font.PLAIN, 20));
		rdbtnNewRadioButton.setBounds(85, 62, 578, 29);
		frame.getContentPane().add(rdbtnNewRadioButton);
		rdbtnNewRadioButton.setVisible(false);

		JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("New radio button");
		rdbtnNewRadioButton_1.setFont(new Font("宋体", Font.PLAIN, 20));
		rdbtnNewRadioButton_1.setBounds(85, 98, 578, 29);
		frame.getContentPane().add(rdbtnNewRadioButton_1);
		rdbtnNewRadioButton_1.setVisible(false);

		JRadioButton rdbtnNewRadioButton_2 = new JRadioButton("New radio button");
		rdbtnNewRadioButton_2.setFont(new Font("宋体", Font.PLAIN, 20));
		rdbtnNewRadioButton_2.setBounds(85, 134, 582, 29);
		frame.getContentPane().add(rdbtnNewRadioButton_2);
		rdbtnNewRadioButton_2.setVisible(false);

		JRadioButton rdbtnNewRadioButton_3 = new JRadioButton("New radio button");
		rdbtnNewRadioButton_3.setFont(new Font("宋体", Font.PLAIN, 20));
		rdbtnNewRadioButton_3.setBounds(85, 170, 582, 29);
		frame.getContentPane().add(rdbtnNewRadioButton_3);
		rdbtnNewRadioButton_3.setVisible(false);

		JRadioButton rdbtnNewRadioButton_4 = new JRadioButton("New radio button");
		rdbtnNewRadioButton_4.setFont(new Font("宋体", Font.PLAIN, 20));
		rdbtnNewRadioButton_4.setBounds(85, 206, 578, 29);
		frame.getContentPane().add(rdbtnNewRadioButton_4);
		rdbtnNewRadioButton_4.setVisible(false);

		JRadioButton rdbtnNewRadioButton_5 = new JRadioButton("New radio button");
		rdbtnNewRadioButton_5.setFont(new Font("宋体", Font.PLAIN, 20));
		rdbtnNewRadioButton_5.setBounds(85, 242, 578, 29);
		frame.getContentPane().add(rdbtnNewRadioButton_5);
		rdbtnNewRadioButton_5.setVisible(false);

		JRadioButton rdbtnNewRadioButton_6 = new JRadioButton("New radio button");
		rdbtnNewRadioButton_6.setFont(new Font("宋体", Font.PLAIN, 20));
		rdbtnNewRadioButton_6.setBounds(85, 278, 578, 29);
		frame.getContentPane().add(rdbtnNewRadioButton_6);
		rdbtnNewRadioButton_6.setVisible(false);

		JRadioButton rdbtnNewRadioButton_7 = new JRadioButton("New radio button");
		rdbtnNewRadioButton_7.setFont(new Font("宋体", Font.PLAIN, 20));
		rdbtnNewRadioButton_7.setBounds(85, 314, 578, 29);
		frame.getContentPane().add(rdbtnNewRadioButton_7);
		rdbtnNewRadioButton_7.setVisible(false);

		JRadioButton rdbtnNewRadioButton_8 = new JRadioButton("New radio button");
		rdbtnNewRadioButton_8.setFont(new Font("宋体", Font.PLAIN, 20));
		rdbtnNewRadioButton_8.setBounds(85, 350, 578, 29);
		frame.getContentPane().add(rdbtnNewRadioButton_8);
		rdbtnNewRadioButton_8.setVisible(false);

		JRadioButton rdbtnNewRadioButton_9 = new JRadioButton("New radio button");
		rdbtnNewRadioButton_9.setFont(new Font("宋体", Font.PLAIN, 20));
		rdbtnNewRadioButton_9.setBounds(85, 386, 582, 29);
		frame.getContentPane().add(rdbtnNewRadioButton_9);
		rdbtnNewRadioButton_9.setVisible(false);

		booknumber = 0; // 初始化借书数目
		overmoney = 0; // 初始化逾期罚款
		String borrowbooks = ""; // 初始化获取登录用户的借书序号字符串
		String borrowbooktimes = ""; // 初始化获取登录用户的借书时间字符串
		String[] borrowbookname = new String[10];// 设置存储所借书名的数组
		// 数据库MySQL
		try {
			Class.forName(driver);// 注册驱动/加载驱动
			Connection conn = DriverManager.getConnection(url, user, password); // 建立连接对象 /连接数据库
			Statement state = conn.createStatement(); // 创建Statement/PreparedStatement对象，用来执行SQL语句
			String Sql = "select * from user where Code = '" + code + "'"; // 要执行的SQL语句
			ResultSet rs = state.executeQuery(Sql);// 结果集
			while (rs.next()) {
				credit = rs.getInt("Credit"); // 获取登录用户的信用积分
				booknumber = rs.getInt("BookNumber"); // 获取登录用户的借书数量
				borrowbooks = rs.getString("BorrowBook"); // 获取登录用户的借书序号
				borrowbooktimes = rs.getString("BorrowBookTime");// 获取登录用户的借书时间
			}
			char[] borrowbookcodeschar = borrowbooks.toCharArray(); // 临时存储所借书号字符的数组
			char[] borrowbookcode_char = new char[10]; // 临时存储各个书号字符的数组
			for (int i = 0; i < booknumber; i++) {// 读每本书的序号
				for (int j = 0; j < 10; j++)// 获得一本书的序号
					borrowbookcode_char[j] = borrowbookcodeschar[i * 11 + j];
				borrowbookcode[i] = String.valueOf(borrowbookcode_char);// 获取存储所借书号的数组
				String sql = "select * from book where Code = '" + borrowbookcode[i] + "'"; // 要执行的SQL语句
				rs = state.executeQuery(sql);
				while (rs.next())// 获取对应的所借书名
					borrowbookname[i] = rs.getString("BookName");
			}
			rs.close();
			state.close();
			conn.close();// 关闭Connection类的实例
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
		if (booknumber == 0)// 没有外借书籍时
		{
			lblNewLabel.setText("您没有在借的书籍！");
			btnNewButton.setVisible(false);
		} else {
			Calendar nowDate = new GregorianCalendar(); // 获取当前系统平台下默认的日期、时间和时区
			boolean[] check = new boolean[10]; // 判断是否选中复选框
			int nowYear = nowDate.get(Calendar.YEAR); // 获取当前系统平台下默认日期的“年”
			int nowMonth = nowDate.get(Calendar.MONTH) + 1; // 获取当前系统平台下默认日期的“月”
			int nowDay = nowDate.get(Calendar.DAY_OF_MONTH); // 获取当前系统平台下默认日期的“日”
			int[] overdays = new int[booknumber];// 设置借阅天数记录量
			int[] year = new int[booknumber]; // 设置借书时间年份的记录量
			int[] month = new int[booknumber]; // 设置借书时间月份的记录量
			int[] day = new int[booknumber]; // 设置借书时间天份的记录量
			char[] borrowbooktimeschar = borrowbooktimes.toCharArray();// 临时存储借书时间字符的数组
			for (int i = 0, temp = 0; i < booknumber; i++) {
				check[i] = false;
				char[] borrowbooktime_char = new char[10];// 临时存储各个借书时间字符的数组
				for (int j = 0; j < 10; j++)// 防止内存不正
					borrowbooktime_char[j] = ' ';
				for (int j = 0; temp < borrowbooktimes.length(); j++)
					if (borrowbooktimeschar[temp] != ';') {
						borrowbooktime_char[j] = borrowbooktimeschar[temp];
						temp++;
					} else {
						temp++;// 保险起见
						break;
					}
				borrowbooktime[i] = String.copyValueOf(borrowbooktime_char);// 获取各个借书时间的字符串
				int[] _location = new int[] { 0, 0, 0 };// 记录_的位置
				for (int j = 0, t = 0; j < 10; j++)// 获取各个借书时间的年月日，如上再次细化
				{
					if (borrowbooktime_char[j] == '-') {
						_location[t] = j;
						t++;
					}
					if (borrowbooktime_char[j] == ' ') {
						_location[t] = j;
						break;
					}
					if (j == 9 && borrowbooktime_char[j] != ' ') {
						_location[t] = 10;
						break;
					}
				}
				year[i] = Integer.parseInt(borrowbooktime[i].substring(0, _location[0]));
				month[i] = Integer.parseInt(borrowbooktime[i].substring(_location[0] + 1, _location[1]));
				day[i] = Integer.parseInt(borrowbooktime[i].substring(_location[1] + 1, _location[2]));
				
			} // 获取年份，月份，天份
			for (int i = 0; i < 10; i++) 
			check[i] = false;
			for (int i = 0; i < booknumber; i++) {
				switch (i) {// 显示对应借书数量的选项（包括书名和借书时间）
				case 0: {
					rdbtnNewRadioButton.setText(borrowbookname[i] + "   " + borrowbooktime[i]);
					rdbtnNewRadioButton.setVisible(true);
				}
					break;
				case 1: {
					rdbtnNewRadioButton_1.setText(borrowbookname[i] + "   " + borrowbooktime[i]);
					rdbtnNewRadioButton_1.setVisible(true);
				}
					break;
				case 2: {
					rdbtnNewRadioButton_2.setText(borrowbookname[i] + "   " + borrowbooktime[i]);
					rdbtnNewRadioButton_2.setVisible(true);
				}
					break;
				case 3: {
					rdbtnNewRadioButton_3.setText(borrowbookname[i] + "   " + borrowbooktime[i]);
					rdbtnNewRadioButton_3.setVisible(true);
				}
					break;
				case 4: {
					rdbtnNewRadioButton_4.setText(borrowbookname[i] + "   " + borrowbooktime[i]);
					rdbtnNewRadioButton_4.setVisible(true);
				}
					break;
				case 5: {
					rdbtnNewRadioButton_5.setText(borrowbookname[i] + "   " + borrowbooktime[i]);
					rdbtnNewRadioButton_5.setVisible(true);
				}
					break;
				case 6: {
					rdbtnNewRadioButton_6.setText(borrowbookname[i] + "   " + borrowbooktime[i]);
					rdbtnNewRadioButton_6.setVisible(true);
				}
					break;
				case 7: {
					rdbtnNewRadioButton_7.setText(borrowbookname[i] + "   " + borrowbooktime[i]);
					rdbtnNewRadioButton_7.setVisible(true);
				}
					break;
				case 8: {
					rdbtnNewRadioButton_8.setText(borrowbookname[i] + "   " + borrowbooktime[i]);
					rdbtnNewRadioButton_8.setVisible(true);
				}
					break;
				case 9: {
					rdbtnNewRadioButton_9.setText(borrowbookname[i] + "   " + borrowbooktime[i]);
					rdbtnNewRadioButton_9.setVisible(true);
				}
					break;
				default:
					break;
				}
				int sumyear = nowYear - year[i]; // 年分之差
				int summonth = nowMonth - month[i];// 月份之差
				int sumday = nowDay - day[i]; // 天份之差
				overdays[i] = 365 * sumyear + 30 * summonth + sumday;
			}

			rdbtnNewRadioButton.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					if (rdbtnNewRadioButton.isSelected()) {
						check[0] = true;// 用数组传递选中状态方便管理
						if (overdays[0] > 30) {
							credit -= 5;
							overmoney += (float) (0.5 * (overdays[0] - 30));
						}
					} else if (booknumber > 0) {
						check[0] = false;
						if (overdays[0] > 30) {
							credit += 5;
							overmoney -= (float) (0.5 * (overdays[0] - 30));
						}
					}
					
				}
			});
			/*
			rdbtnNewRadioButton.addActionListener(new ActionListener()// 选中归还图书事件
			{
				@Override
				public void mouseClicked(MouseEvent arg0) {
					if (rdbtnNewRadioButton.isSelected()) {
						check[0] = true;// 用数组传递选中状态方便管理
						if (overdays[0] > 30) {
							credit -= 5;
							overmoney += (float) (0.5 * (overdays[0] - 30));
						}
					} else if (booknumber > 0) {
						check[0] = false;
						if (overdays[0] > 30) {
							credit += 5;
							overmoney -= (float) (0.5 * (overdays[0] - 30));
						}
					}
				}
			});*/
			rdbtnNewRadioButton_1.addActionListener(new ActionListener()// 选中归还图书事件
			{
				@Override
				public void actionPerformed(ActionEvent e){
					if (rdbtnNewRadioButton_1.isSelected()) {
						check[1] = true;// 用数组传递选中状态方便管理
						if (overdays[1] > 30) {
							credit -= 5;
							overmoney += (float) (0.5 * (overdays[1] - 30));
						}
					} else if (booknumber > 1) {
						check[1] = false;
						if (overdays[1] > 30) {
							credit += 5;
							overmoney -= (float) (0.5 * (overdays[1] - 30));
						}
					}
				}
			});
			rdbtnNewRadioButton_2.addActionListener(new ActionListener()// 选中归还图书事件
			{
				public void actionPerformed(ActionEvent e){
					if (rdbtnNewRadioButton_2.isSelected()) {
						check[2] = true;// 用数组传递选中状态方便管理
						if (overdays[2] > 30) {
							credit -= 5;
							overmoney += (float) (0.5 * (overdays[2] - 30));
						}
					} else if (booknumber > 2) {
						check[2] = false;
						if (overdays[2] > 30) {
							credit += 5;
							overmoney -= (float) (0.5 * (overdays[2] - 30));
						}
					}
				}
			});
			rdbtnNewRadioButton_3.addActionListener(new ActionListener()// 选中归还图书事件
			{
				@Override
				public void actionPerformed(ActionEvent e){
					if (rdbtnNewRadioButton_3.isSelected()) {
						check[3] = true;// 用数组传递选中状态方便管理
						if (overdays[3] > 30) {
							credit -= 5;
							overmoney += (float) (0.5 * (overdays[3] - 30));
						}
					} else if (booknumber > 3) {
						check[3] = false;
						if (overdays[3] > 30) {
							credit += 5;
							overmoney -= (float) (0.5 * (overdays[3] - 30));
						}
					}
				}
			});
			rdbtnNewRadioButton_4.addActionListener(new ActionListener()// 选中归还图书事件
			{
				@Override
				public void actionPerformed(ActionEvent e){
					if (rdbtnNewRadioButton_4.isSelected()) {
						check[4] = true;// 用数组传递选中状态方便管理
						if (overdays[4] > 30) {
							credit -= 5;
							overmoney += (float) (0.5 * (overdays[4] - 30));
						}
					} else if (booknumber > 4) {
						check[4] = false;
						if (overdays[4] > 30) {
							credit += 5;
							overmoney -= (float) (0.5 * (overdays[4] - 30));
						}
					}
				}
			});
			rdbtnNewRadioButton_5.addActionListener(new ActionListener()// 选中归还图书事件
			{
				@Override
				public void actionPerformed(ActionEvent e){
					if (rdbtnNewRadioButton_5.isSelected()) {
						check[5] = true;// 用数组传递选中状态方便管理
						if (overdays[5] > 30) {
							credit -= 5;
							overmoney += (float) (0.5 * (overdays[5] - 30));
						}
					} else if (booknumber > 5) {
						check[5] = false;
						if (overdays[5] > 30) {
							credit += 5;
							overmoney -= (float) (0.5 * (overdays[5] - 30));
						}
					}
				}
			});
			rdbtnNewRadioButton_6.addActionListener(new ActionListener()// 选中归还图书事件
			{
				@Override
				public void actionPerformed(ActionEvent e){
					if (rdbtnNewRadioButton_6.isSelected()) {
						check[6] = true;// 用数组传递选中状态方便管理
						if (overdays[6] > 30) {
							credit -= 5;
							overmoney += (float) (0.5 * (overdays[6] - 30));
						}
					} else if (booknumber > 6) {
						check[6] = false;
						if (overdays[6] > 30) {
							credit += 5;
							overmoney -= (float) (0.5 * (overdays[6] - 30));
						}
					}
				}
			});
			rdbtnNewRadioButton_7.addActionListener(new ActionListener()// 选中归还图书事件
			{
				@Override
				public void actionPerformed(ActionEvent e){
					if (rdbtnNewRadioButton_7.isSelected()) {
						check[7] = true;// 用数组传递选中状态方便管理
						if (overdays[7] > 30) {
							credit -= 5;
							overmoney += (float) (0.5 * (overdays[7] - 30));
						}
					} else if (booknumber > 7) {
						check[7] = false;
						if (overdays[7] > 30) {
							credit += 5;
							overmoney -= (float) (0.5 * (overdays[7] - 30));
						}
					}
				}
			});
			rdbtnNewRadioButton_8.addActionListener(new ActionListener()// 选中归还图书事件
			{
				@Override
				public void actionPerformed(ActionEvent e){
					if (rdbtnNewRadioButton_8.isSelected()) {
						check[8] = true;// 用数组传递选中状态方便管理
						if (overdays[8] > 30) {
							credit -= 5;
							overmoney += (float) (0.5 * (overdays[8] - 30));
						}
					} else if (booknumber > 8) {
						check[8] = false;
						if (overdays[8] > 30) {
							credit += 5;
							overmoney -= (float) (0.5 * (overdays[8] - 30));
						}
					}
				}
			});
			rdbtnNewRadioButton_9.addActionListener(new ActionListener()// 选中归还图书事件
			{
				@Override
				public void actionPerformed(ActionEvent e){
					if (rdbtnNewRadioButton_9.isSelected()) {
						check[9] = true;// 用数组传递选中状态方便管理
						if (overdays[9] > 30) {
							credit -= 5;
							overmoney += (float) (0.5 * (overdays[9] - 30));
						}
					} else if (booknumber > 9) {
						check[9] = false;
						if (overdays[9] > 30) {
							credit += 5;
							overmoney -= (float) (0.5 * (overdays[9] - 30));
						}
					}
				}
			});

			btnNewButton.addActionListener(new ActionListener()// 确认还书事件
			{
				public void actionPerformed(ActionEvent arg0) {
					boolean choosestate = false; // 判断有无选中图书
					boolean error = false; // 判断更新数据是否成功
					String updateborrowbooks = ""; // 更新后的用户借书序号存放空间
					String updateborrowbooktimes = "";// 更新后的用户借书时间存放空间
					int updatebooknumber = booknumber;// 更新后的用户借书数量存放空间
					for (int i = 0, temp = 0; i < booknumber; i++)
						if (check[i])// 选择勾选的图书
						{
							choosestate = true;
							updatebooknumber--; // 更新借书数量
							int storenumber = 0;// 设置存储对应图书的在馆数量
							try {// 获取图书在馆数量
								Class.forName(driver);
								String Sql = "select * from book where Code = '" + borrowbookcode[i] + "'";
								Connection conn = DriverManager.getConnection(url, user, password);
								Statement state = conn.createStatement();
								ResultSet rs = state.executeQuery(Sql);
								while (rs.next())
									storenumber = rs.getInt("StoreNumber");
								rs.close();
								state.close();
								conn.close();
							} catch (Exception e) {
								e.printStackTrace();
							}
							try {// 更新每本图书在馆数量
								Class.forName(driver);
								Connection conn = DriverManager.getConnection(url, user, password);
								String Sql = "update book set StoreNumber='" + (++storenumber) + "'where Code='"
										+ borrowbookcode[i] + "'";
								Statement pstmt = conn.createStatement();
								pstmt.executeUpdate(Sql);// 更新数据的SQL语句执行
								pstmt.close();
								conn.close();
							} catch (Exception e) {
								e.printStackTrace();
								error = true;
							}
						} else// 未选择的图书
						{// 获取并整合更新后的用户借阅信息（图书名和借阅时间）
							temp++;
							if (temp == 1) {
								updateborrowbooks = updateborrowbooks.concat(borrowbookcode[i]);
								updateborrowbooktimes = updateborrowbooktimes.concat(borrowbooktime[i]);
							} else {
								updateborrowbooks = updateborrowbooks.concat(";".concat(borrowbookcode[i]));
								updateborrowbooktimes = updateborrowbooktimes.concat(";".concat(borrowbooktime[i]));
							}
						}
					if (choosestate)// 有图书被选中时
						try {// 更新用户的借阅信息
							Class.forName(driver);
							Connection conn = DriverManager.getConnection(url, user, password);
							String Sql = "update user set Credit = '" + credit + "',BookNumber = '" + updatebooknumber
									+ "',BorrowBook = '" + updateborrowbooks + "',BorrowBookTime = '"
									+ updateborrowbooktimes + "' where Code='" + code + "'";
							Statement pstmt = conn.createStatement();
							pstmt.executeUpdate(Sql);// 更新数据的SQL语句执行
							pstmt.close();
							conn.close();
						} catch (Exception e) {
							e.printStackTrace();
							error = true;
						}
					if (!choosestate)
						JOptionPane.showMessageDialog(null, "请选择图书！", "操作提示", JOptionPane.ERROR_MESSAGE);
					else if (error)
						JOptionPane.showMessageDialog(null, "未知错误[!]：请再尝试一次！", "还书失败提示", JOptionPane.ERROR_MESSAGE);
					else {
						if (overmoney <= 0)
							JOptionPane.showMessageDialog(null, "您已成功归还选中图书！\n您的信用积分为" + credit, "还书成功提示",
									JOptionPane.ERROR_MESSAGE);
						else
							JOptionPane.showMessageDialog(null,
									"您已成功归还选中图书！\n书本逾期费用为 " + overmoney + " RMB\n您的信用积分为" + credit, "还书成功提示",
									JOptionPane.ERROR_MESSAGE);
						// 返回菜单代码

						frame.setVisible(false);
						menu window = new menu();
						window.frame.setVisible(true);
						

					}

				}
			});
		}
		
		btnNewButton_1.addActionListener(new ActionListener()// 返回事件
				{
					public void actionPerformed(ActionEvent arg0) {
						
							frame.setVisible(false);
							menu window = new menu();
							window.frame.setVisible(true);
					}
				});
		
	}
}