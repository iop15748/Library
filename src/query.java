import java.awt.Color;

import java.awt.EventQueue;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;

import javax.swing.JTable;

import javax.swing.border.LineBorder;



import javax.swing.table.DefaultTableCellRenderer;


import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class query {

	public JFrame frame;
	private JTextField textField_1;
	private JTable table;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					query window = new query();
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
	public query() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		String driver = "com.mysql.jdbc.Driver";
		try {
		Class.forName(driver);
		}
		 catch (Exception e)
	    {
	          e.printStackTrace();
	    }
		frame = new JFrame();
		frame.setTitle("\u56FE\u4E66\u9986");
		frame.setBounds(100, 100, 700, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel label = new JLabel("\u56FE\u4E66\u67E5\u8BE2");
		label.setBounds(14, 13, 211, 42);
		label.setForeground(new Color(3, 71, 136));
		label.setFont(new Font("华文行楷", Font.BOLD, 38));
		frame.getContentPane().add(label);
		
		textField_1 = new JTextField();
		textField_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				textField_1.setText("");
				textField_1.setForeground(Color.BLACK);
			}
		});
		textField_1.setForeground(Color.LIGHT_GRAY);
		textField_1.setText("\u8BF7\u5728\u6B64\u8F93\u5165\u56FE\u4E66\u4FE1\u606F\uFF1A\u56FE\u4E66\u540D\u79F0\uFF0C\u4F5C\u8005\u6216\u51FA\u7248\u5546");
		textField_1.setBounds(51, 98, 378, 35);
		textField_1.setFont(new Font("宋体", Font.PLAIN, 16));
		textField_1.setColumns(10);
		frame.getContentPane().add(textField_1);
		
		JButton button_1 = new JButton("\u8FD4\u56DE");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				menu window = new menu();
				window.frame.setVisible(true);
			}
		});
		button_1.setFont(new Font("宋体", Font.PLAIN, 24));
		button_1.setBounds(541, 479, 110, 37);
		frame.getContentPane().add(button_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(51, 180, 600, 250);
		frame.getContentPane().add(scrollPane);
		
		Object a[][];
		Object colname[]= {"\u56FE\u4E66\u7F16\u53F7", "\u56FE\u4E66\u540D\u79F0", "位置", "\u4F5C\u8005", "\u51FA\u7248\u5546", "\u501F\u9605\u60C5\u51B5"};
		a=new Object[10][6];
		table = new JTable(a,colname);
		table.setEnabled(false);
		table.getColumnModel().getColumn(0).setPreferredWidth(60);
		table.getColumnModel().getColumn(1).setPreferredWidth(97);
		table.getColumnModel().getColumn(2).setPreferredWidth(28);
		table.getColumnModel().getColumn(3).setPreferredWidth(55);
		table.getColumnModel().getColumn(4).setPreferredWidth(150);
		table.setRowHeight(32);
		table.setFont(new Font("宋体", Font.PLAIN, 18));
		scrollPane.setViewportView(table);
		table.setBorder(new LineBorder(new Color(0, 0, 0)));
	

		JButton button = new JButton("\u67E5\u8BE2");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {//查找书目
				for(int x=0;x<10;x++)//表初始化
				{
					for(int y=0;y<6;y++)
					{
						a[x][y]="";
					}
				}
				try
				{
					int i=0;
					String keyword=textField_1.getText();
					String sql="select * from book where BookName like '%"+keyword+"%' or Author like '%"+keyword+"%' or Publication like '%"+keyword+"%'";
					//模糊查询sql语句
					Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Library?"  
			                + "user=root&password=xxxx1998&useUnicode=true&characterEncoding=UTF8");  
			        Statement pstmt = conn.createStatement();  
			        ResultSet rs=pstmt.executeQuery(sql);//数据库连接和执行sql语句
			       	        
			        while(rs.next())//将数据库中数据写入表中
			        {
			        	a[i][0]=rs.getString("Code");
			        	a[i][1]=rs.getString("BookName");
			        	a[i][2]=rs.getString("Location");
			        	a[i][3]=rs.getString("Author");
			        	a[i][4]=rs.getString("Publication");
			        	a[i][5]="剩余"+rs.getString("StoreNumber")+"本";
			        	i++;
			        	DefaultTableCellRenderer r   = new DefaultTableCellRenderer();   
			        	r.setHorizontalAlignment(JLabel.CENTER);   
			        	table.setDefaultRenderer(Object.class, r);//字体居中
			        	table.updateUI();//刷新表
			        }
				}
				catch(Exception e)  
	    {  
	        e.printStackTrace();  
	    } 
				if(a[0][0]=="")
	        	{
	        		JOptionPane.showMessageDialog(button, "未查询到相关书籍！", "抱歉！", 0);
	        	}
			}
		});
		button.setBounds(484, 98, 110, 35);
		button.setFont(new Font("宋体", Font.PLAIN, 24));
		frame.getContentPane().add(button);
	}
}
