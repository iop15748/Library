package Com.ecust.library.view.BookManagement;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class BookManagement
{

	private JFrame frame;
	private JTextField textField;
	JTable table;
	String BookName = null;
	String Author = null;
	String Publication = null;
	String Location = null;
	String Code = null;
	int BookNumber = 0;
	int StoreNumber = 0;

	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try
				{
					BookManagement window = new BookManagement();
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
	 */
	public BookManagement()
	{
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize()
	{
		frame = new JFrame();
		frame.setTitle("\u56FE\u4E66\u9986");
		frame.setBounds(100, 100, 700, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel label = new JLabel("\u56FE\u4E66\u7BA1\u7406");
		label.setBounds(14, 13, 211, 42);
		label.setForeground(new Color(3, 71, 136));
		label.setFont(new Font("�����п�", Font.BOLD, 38));
		frame.getContentPane().add(label);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(49, 176, 600, 250);
		frame.getContentPane().add(scrollPane);

		Object a[][];
		Object colname[] =
		{ "�鼮���", "����", "����", "�ڹ�����", "λ��", "����", "������" };
		a = new Object[10][7];
		table = new JTable(a, colname);
		table.getColumnModel().getColumn(0).setPreferredWidth(98);
		table.getColumnModel().getColumn(1).setPreferredWidth(80);
		table.getColumnModel().getColumn(2).setPreferredWidth(49);
		table.getColumnModel().getColumn(3).setPreferredWidth(54);
		table.getColumnModel().getColumn(4).setPreferredWidth(81);
		table.getColumnModel().getColumn(5).setPreferredWidth(70);
		table.getColumnModel().getColumn(6).setPreferredWidth(72);
		table.setRowHeight(32);
		table.setFont(new Font("����", Font.PLAIN, 18));
		scrollPane.setViewportView(table);
		table.setBorder(new LineBorder(new Color(0, 0, 0)));

		JButton button = new JButton("\u67E5\u8BE2");
		button.setBounds(539, 89, 110, 35);
		button.setFont(new Font("����", Font.PLAIN, 24));
		frame.getContentPane().add(button);

		JButton button_1 = new JButton("\u6DFB\u52A0");
		button_1.setBounds(49, 485, 110, 35);
		button_1.setFont(new Font("����", Font.PLAIN, 24));
		frame.getContentPane().add(button_1);

		JButton button_2 = new JButton("\u5220\u9664");
		button_2.setBounds(214, 485, 110, 35);
		button_2.setFont(new Font("����", Font.PLAIN, 24));
		frame.getContentPane().add(button_2);

		JButton button_3 = new JButton("\u8FD4\u56DE");
		button_3.setBounds(539, 485, 110, 35);
		button_3.setFont(new Font("����", Font.PLAIN, 24));
		frame.getContentPane().add(button_3);

		JButton button_4 = new JButton("\u4FEE\u6539");
		button_4.setBounds(378, 485, 110, 35);
		button_4.setFont(new Font("����", Font.PLAIN, 24));
		frame.getContentPane().add(button_4);

		textField = new JTextField();
		textField.setBounds(51, 89, 378, 35);
		textField.setText("������ͼ���š����������߻��������Ϣ");
		textField.setForeground(Color.LIGHT_GRAY);
		textField.setFont(new Font("����", Font.PLAIN, 16));
		textField.setColumns(10);
		frame.getContentPane().add(textField);

		// ����������
		String driver = "com.mysql.jdbc.Driver";
		// URLָ��Ҫ���ʵ����ݿ���
		String url = "jdbc:mysql://localhost:444/test";
		// MySQL����ʱ���û���
		String user = "root";
		// MySQL����ʱ������
		String password = "password123";

		button.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent event)
			{
				// ����Connection����

				for (int x = 0; x < 10; x++)// ���ʼ��
				{
					for (int y = 0; y < 7; y++)
					{
						a[x][y] = "";
					}
				}

				try
				{
					String Bookinfo = textField.getText();
					int i = 0;

					// ������������
					Connection con;
					con = DriverManager.getConnection(url, user, password);
					// ����statement���������ִ��SQL���
					Statement statement = con.createStatement();
					// Ҫִ�е�SQL���
					String sql = "select * from book where BookName like '%" + Bookinfo + "%' or Code like '%"
							+ Bookinfo + "%'or Author like '%" + Bookinfo + "%' or Publication like '%" + Bookinfo
							+ "%'";
					// ResultSet�࣬������Ż�ȡ�Ľ����
					ResultSet rs = statement.executeQuery(sql);

					for (int x = 0; x < 10; x++)// ���ʼ��
					{
						for (int y = 0; y < 6; y++)
						{
							a[x][y] = "";
						}
					}

					while (rs.next())// �����ݿ�������д�����
					{
						a[i][0] = rs.getString("Code");
						a[i][1] = rs.getString("BookName");
						a[i][2] = rs.getInt("BookNumber");
						a[i][3] = rs.getInt("StoreNumber");
						a[i][4] = rs.getString("Location");
						a[i][5] = rs.getString("Author");
						a[i][6] = rs.getString("Publication");
						i++;
						DefaultTableCellRenderer r = new DefaultTableCellRenderer();
						r.setHorizontalAlignment(JLabel.CENTER);
						table.setDefaultRenderer(Object.class, r);// �������
						table.updateUI();// ˢ�±�
					}
					rs.close();
					con.close();
				} catch (SQLException e)
				{
					// ���ݿ�����ʧ���쳣����
					JOptionPane.showMessageDialog(button, "���ݿ�����ʧ��", "��Ǹ��", 0);
					e.printStackTrace();
				} catch (Exception e)
				{
					e.printStackTrace();
				}
				if (a[0][0] == "")
				{
					JOptionPane.showMessageDialog(button, "δ��ѯ������鼮��", "��Ǹ��", 0);
				}
			}
		});

		button_1.addActionListener(new ActionListener()// ��Ӱ�ť
		{
			public void actionPerformed(ActionEvent event)
			{
				PreparedStatement psql;
				// ��ȡ����һ�����ݲ����
				Code = (String) a[0][0];
				BookName = (String) a[0][1];
				if (a[0][2] instanceof String)
				{
					BookNumber = Integer.parseInt((String) a[0][2]);
				} else
				{
					BookNumber = (int) a[0][2];
				}
				if (a[0][3] instanceof String)
				{
					StoreNumber = Integer.parseInt((String) a[0][3]);
				} else
				{
					StoreNumber = (int) a[0][3];
				}
				Location = (String) a[0][4];
				Author = (String) a[0][5];
				Publication = (String) a[0][6];
				try
				{
					Connection con;
					con = DriverManager.getConnection(url, user, password);

					psql = con.prepareStatement(
							"insert into Book (Code,BookName,BookNumber,StoreNumber,Author,Location,Publication) "
									+ "values(?,?,?,?,?,?,?)");
					psql.setString(1, Code);
					psql.setString(2, BookName);
					psql.setInt(3, BookNumber);
					psql.setInt(4, StoreNumber);
					psql.setString(5, Location);
					psql.setString(6, Author);
					psql.setString(7, Publication);
					psql.executeUpdate();

					psql.close();
					con.close();
					JOptionPane.showMessageDialog(button, "��ӳɹ���", "��ʾ", 0);
				} catch (SQLException e)
				{
					// ���ݿ�����ʧ���쳣����
					JOptionPane.showMessageDialog(button, "���ݿ�����ʧ��", "��Ǹ��", 0);
					e.printStackTrace();
				}
			}
		});

		button_2.addActionListener(new ActionListener()// ɾ����ť
		{
			public void actionPerformed(ActionEvent event)
			{
				// ɾ��������ͼ��������Ӧ��¼
				String BookCode = textField.getText();
				try
				{
					PreparedStatement psql;
					Connection con;
					con = DriverManager.getConnection(url, user, password);
					String sql = "delete from Book where Code like '%" + BookCode + "%' ";
					psql = con.prepareStatement(sql);
					psql.executeUpdate();

					psql.close();
					con.close();
					JOptionPane.showMessageDialog(button, "ɾ���ɹ���", "��ʾ", 0);
				} catch (SQLException e)
				{
					// ���ݿ�����ʧ���쳣����
					JOptionPane.showMessageDialog(button, "���ݿ�����ʧ��", "��Ǹ��", 0);
					e.printStackTrace();
				}
			}
		});

		button_4.addActionListener(new ActionListener()// �޸İ�ť
		{
			public void actionPerformed(ActionEvent event)
			{
				PreparedStatement psql;
				// ��ȡ����һ�����ݲ��޸�
				Code = (String) a[0][0];
				BookName = (String) a[0][1];
				if (a[0][2] instanceof String)
				{
					BookNumber = Integer.parseInt((String) a[0][2]);
				} else
				{
					BookNumber = (int) a[0][2];
				}
				if (a[0][3] instanceof String)
				{
					StoreNumber = Integer.parseInt((String) a[0][3]);
				} else
				{
					StoreNumber = (int) a[0][3];
				}
				Location = (String) a[0][4];
				Author = (String) a[0][5];
				Publication = (String) a[0][6];
				try
				{
					Connection con;
					con = DriverManager.getConnection(url, user, password);

					psql = con.prepareStatement(
							"update Book set BookName=?,BookNumber=?,StoreNumber=?,Author=?,Location=?,Publication=? where Code= ?");
					psql.setString(1, BookName);
					psql.setInt(2, BookNumber);
					psql.setInt(3, StoreNumber);
					psql.setString(4, Location);
					psql.setString(5, Author);
					psql.setString(6, Publication);
					psql.setString(7, Code);
					psql.executeUpdate();

					psql.close();
					con.close();
					JOptionPane.showMessageDialog(button, "�޸���ɣ�", "��ʾ", 0);
				} catch (SQLException e)
				{
					// ���ݿ�����ʧ���쳣����
					JOptionPane.showMessageDialog(button, "���ݿ�����ʧ��", "��Ǹ��", 0);
					e.printStackTrace();
				}
			}
		});

		button_3.addActionListener(new ActionListener()// ���ذ�ť
		{
			public void actionPerformed(ActionEvent event)
			{

			}
		});
	}
}
