package Com.ecust.library.view.UserManagement;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import java.awt.Font;
import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class UserManagement
{

	public JFrame frame;
    JTextField textField;
    JTable table;
	String Code = null;
	String Password = null;
	String Name = null;
	String Class = null;
	String BorrowBook = null;
	int Credit = 0;
	int BookNumber = 0;
	
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try
				{
					UserManagement window = new UserManagement();
					window.frame.setVisible(true);
				} catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * 
	 * @return
	 */
	public UserManagement()
	{
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	public void initialize()
	{
		frame = new JFrame();
		frame.setTitle("\u56FE\u4E66\u9986");
		frame.setBounds(100, 100, 700, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel label = new JLabel("\u7528\u6237\u7BA1\u7406");
		label.setBounds(14, 13, 211, 42);
		label.setForeground(new Color(3, 71, 136));
		label.setFont(new Font("华文行楷", Font.BOLD, 38));
		frame.getContentPane().add(label);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(49, 176, 600, 250);
		frame.getContentPane().add(scrollPane);

		Object a[][];
		Object colname[] =
		{ "账号", "密码", "名字", "班级", "信用分", "在借数目", "在借书目" };
		a = new Object[10][7];
		table = new JTable(a, colname);
		table.getColumnModel().getColumn(0).setPreferredWidth(98);
		table.getColumnModel().getColumn(1).setPreferredWidth(80);
		table.setRowHeight(32);
		table.setFont(new Font("宋体", Font.PLAIN, 18));
		scrollPane.setViewportView(table);
		table.setBorder(new LineBorder(new Color(0, 0, 0)));

		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(518, 425, 131, 24);
		comboBox.setModel(new DefaultComboBoxModel(new String[]
		{ "\u9875\u6570\uFF1A1" }));
		frame.getContentPane().add(comboBox);

		JLabel label_1 = new JLabel("\u8BF7\u8F93\u5165\u67E5\u8BE2\u5361\u53F7");
		label_1.setBounds(49, 92, 209, 27);
		label_1.setForeground(Color.DARK_GRAY);
		label_1.setFont(new Font("宋体", Font.PLAIN, 26));
		frame.getContentPane().add(label_1);

		textField = new JTextField();
		textField.setFont(new Font("宋体", Font.PLAIN, 20));
		textField.setColumns(10);
		textField.setBounds(272, 94, 145, 27);
		frame.getContentPane().add(textField);

		JButton button = new JButton("\u67E5\u8BE2");
		button.setFont(new Font("宋体", Font.PLAIN, 24));
		button.setBounds(443, 89, 110, 35);
		frame.getContentPane().add(button);

		JButton button_1 = new JButton("\u6DFB\u52A0");
		button_1.setFont(new Font("宋体", Font.PLAIN, 24));
		button_1.setBounds(49, 485, 110, 35);
		frame.getContentPane().add(button_1);

		JButton button_2 = new JButton("\u5220\u9664");
		button_2.setFont(new Font("宋体", Font.PLAIN, 24));
		button_2.setBounds(214, 485, 110, 35);
		frame.getContentPane().add(button_2);

		JButton button_3 = new JButton("\u8FD4\u56DE");
		button_3.setFont(new Font("宋体", Font.PLAIN, 24));
		button_3.setBounds(539, 485, 110, 35);
		frame.getContentPane().add(button_3);

		JButton button_4 = new JButton("\u4FEE\u6539");
		button_4.setFont(new Font("宋体", Font.PLAIN, 24));
		button_4.setBounds(378, 485, 110, 35);
		frame.getContentPane().add(button_4);

		// 驱动程序名
		String driver = "com.mysql.jdbc.Driver";
		// URL指向要访问的数据库名
		String url = "jdbc:mysql://localhost:444/test";
		// MySQL配置时的用户名
		String user = "root";
		// MySQL配置时的密码
		String password = "password123";

		button.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent event)
			{
				// 声明Connection对象

				for (int x = 0; x < 10; x++)// 表初始化
				{
					for (int y = 0; y < 7; y++)
					{
						a[x][y] = "";
					}
				}

				try
				{
					String UserCode = textField.getText();
					int i = 0;

					// 加载驱动程序
					Connection con;
					con = DriverManager.getConnection(url, user, password);
					// 2.创建statement类对象，用来执行SQL语句！！
					Statement statement = con.createStatement();
					// 要执行的SQL语句
					String sql = "select * from User where Code like '%" + UserCode + "%' ";
					// 3.ResultSet类，用来存放获取的结果集！！
					ResultSet rs = statement.executeQuery(sql);
					
					for(int x=0;x<10;x++)//表初始化
					{
						for(int y=0;y<6;y++)
						{
							a[x][y]="";
						}
					}
					
					while (rs.next())// 将数据库中数据写入表中
					{
						a[i][0] = rs.getString("Code");
						a[i][1] = rs.getString("Password");
						a[i][2] = rs.getString("Name");
						a[i][3] = rs.getString("Class");
						a[i][4] = rs.getInt("Credit");
						a[i][5] = rs.getInt("BookNumber");
						a[i][6] = rs.getString("BorrowBook");
						i++;
						DefaultTableCellRenderer r = new DefaultTableCellRenderer();
						r.setHorizontalAlignment(JLabel.CENTER);
						table.setDefaultRenderer(Object.class, r);// 字体居中
						table.updateUI();// 刷新表
					}
					rs.close();
					con.close();
				} catch (SQLException e)
				{
					// 数据库连接失败异常处理
					JOptionPane.showMessageDialog(button, "数据库连接失败", "抱歉！", 0);
					e.printStackTrace();
				} catch (Exception e)
				{
					e.printStackTrace();
				}
				if(a[0][0]=="")
	        	{
	        		JOptionPane.showMessageDialog(button, "未查询到相关信息", "抱歉！", 0);
	        	}
			}
		});

		button_1.addActionListener(new ActionListener()// 添加按钮
		{
			public void actionPerformed(ActionEvent event)
			{
				PreparedStatement psql;
				// 读取表格第一行数据并添加
				Code =       (String) a[0][0];
				Password =   (String) a[0][1];
				Name =       (String) a[0][2];
				Class =      (String) a[0][3];
				Credit =Integer.parseInt((String) a[0][4]);        
				BookNumber =Integer.parseInt((String) a[0][5]);  
				BorrowBook = (String) a[0][6];
				try
				{
					Connection con;
					con = DriverManager.getConnection(url, user, password);
					
					psql = con.prepareStatement("insert into User (Code,Password,Name,Class,BookNumber,Credit,BorrowBook) "
					        + "values(?,?,?,?,?,?,?)");
					psql.setString(1, Code);
					psql.setString(2, Password);
					psql.setString(3, Name);
					psql.setString(4, Class);
					psql.setInt(5, Credit);
					psql.setInt(6, BookNumber);
					psql.setString(7, BorrowBook);
					psql.executeUpdate();
					
					psql.close();
					con.close();
					JOptionPane.showMessageDialog(button, "添加成功！", "提示", 0);
				} catch (SQLException e)
				{
					// 数据库连接失败异常处理
					JOptionPane.showMessageDialog(button, "数据库连接失败", "抱歉！", 0);
					e.printStackTrace();
				}
			}
		});

		button_2.addActionListener(new ActionListener()// 删除按钮
		{
			public void actionPerformed(ActionEvent event)
			{
				//删除所输入账号所对应记录
				String UserCode = textField.getText();
				try
				{
					PreparedStatement psql;
					Connection con;
					con = DriverManager.getConnection(url, user, password);
					String sql = "delete from User where Code like '%" + UserCode + "%' ";
					psql = con.prepareStatement(sql);
					psql.executeUpdate();
					
					psql.close();
					con.close();
					JOptionPane.showMessageDialog(button, "删除完成！", "提示", 0);
				} catch (SQLException e)
				{
					// 数据库连接失败异常处理
					JOptionPane.showMessageDialog(button, "数据库连接失败", "抱歉！", 0);
					e.printStackTrace();
				}
			}
		});

		button_4.addActionListener(new ActionListener()// 修改按钮
		{
			public void actionPerformed(ActionEvent event)
			{
				PreparedStatement psql;
				// 读取表格第一行数据并修改
				Code =       (String) a[0][0];
				Password =   (String) a[0][1];
				Name =       (String) a[0][2];
				Class =      (String) a[0][3];
				if (a[0][4] instanceof String)
				{
				Credit =Integer.parseInt((String) a[0][4]);  }
				else {
					Credit = (int) a[0][4];
				}
				if (a[0][5] instanceof String)
				{
				BookNumber =Integer.parseInt((String) a[0][5]); }
				else {
					BookNumber = (int) a[0][5];
				}
				BorrowBook = (String) a[0][6];
				try
				{
					Connection con;
					con = DriverManager.getConnection(url, user, password);
					
					psql = con.prepareStatement("update User set Password=?,Name=?,Class=?,Credit=?,BookNumber=?,BorrowBook=? where Code= ?");
					psql.setString(1, Password);
					psql.setString(2, Name);
					psql.setString(3, Class);
					psql.setInt(4, Credit);
					psql.setInt(5, BookNumber);
					psql.setString(6, BorrowBook);
					psql.setString(7, Code);
					psql.executeUpdate();
					
					psql.close();
					con.close();
					JOptionPane.showMessageDialog(button, "修改完成！", "提示", 0);
				} catch (SQLException e)
				{
					// 数据库连接失败异常处理
					JOptionPane.showMessageDialog(button, "数据库连接失败", "抱歉！", 0);
					e.printStackTrace();
				}
			}
		});

		button_3.addActionListener(new ActionListener()// 返回按钮
		{
			public void actionPerformed(ActionEvent event)
			{
				
			}
		});

	}
}
