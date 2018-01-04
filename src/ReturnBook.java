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
	private float overmoney; // ����ѡ�еķ����ܶ�
	private int booknumber; // ��ʼ����ȡ��¼�û��Ľ�����������
	private int credit; // �洢�û�����������
	private String code = User.currentUser; // ���¼����Ľӿڣ������û�����
	private String driver = "com.mysql.jdbc.Driver"; // ��ͬ�����ݿ���Ҫ���ز�ͬ�Ĳ�����������ص���MySQL���ݿ������
	private String url = "jdbc:mysql://localhost:3306/library"; // URLָ��Ҫ���ʵ����ݿ���mySql
	private String user = "root"; // MySQL����ʱ���û���
	private String password = "xxxx1998"; // MySQL����ʱ������
	private String[] borrowbookcode = new String[10]; // ���ô洢������ŵ�����
	private String[] borrowbooktime = new String[10]; // ���ô洢����ʱ�������

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
		lblNewLabel.setFont(new Font("΢���ź�", Font.BOLD, 24));
		lblNewLabel.setBounds(210, 15, 240, 36);
		frame.getContentPane().add(lblNewLabel);

		JButton btnNewButton = new JButton("\u786E\u8BA4\u5F52\u8FD8");
		btnNewButton.setFont(new Font("��Բ", Font.BOLD, 20));
		btnNewButton.setBounds(541, 478, 122, 51);
		frame.getContentPane().add(btnNewButton);

		JButton btnNewButton_1 = new JButton("\u8FD4\u56DE");
		btnNewButton_1.setFont(new Font("��Բ", Font.BOLD, 20));
		btnNewButton_1.setBounds(15, 478, 122, 51);
		frame.getContentPane().add(btnNewButton_1);

		JRadioButton rdbtnNewRadioButton = new JRadioButton("New radio button");
		rdbtnNewRadioButton.setFont(new Font("����", Font.PLAIN, 20));
		rdbtnNewRadioButton.setBounds(85, 62, 578, 29);
		frame.getContentPane().add(rdbtnNewRadioButton);
		rdbtnNewRadioButton.setVisible(false);

		JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("New radio button");
		rdbtnNewRadioButton_1.setFont(new Font("����", Font.PLAIN, 20));
		rdbtnNewRadioButton_1.setBounds(85, 98, 578, 29);
		frame.getContentPane().add(rdbtnNewRadioButton_1);
		rdbtnNewRadioButton_1.setVisible(false);

		JRadioButton rdbtnNewRadioButton_2 = new JRadioButton("New radio button");
		rdbtnNewRadioButton_2.setFont(new Font("����", Font.PLAIN, 20));
		rdbtnNewRadioButton_2.setBounds(85, 134, 582, 29);
		frame.getContentPane().add(rdbtnNewRadioButton_2);
		rdbtnNewRadioButton_2.setVisible(false);

		JRadioButton rdbtnNewRadioButton_3 = new JRadioButton("New radio button");
		rdbtnNewRadioButton_3.setFont(new Font("����", Font.PLAIN, 20));
		rdbtnNewRadioButton_3.setBounds(85, 170, 582, 29);
		frame.getContentPane().add(rdbtnNewRadioButton_3);
		rdbtnNewRadioButton_3.setVisible(false);

		JRadioButton rdbtnNewRadioButton_4 = new JRadioButton("New radio button");
		rdbtnNewRadioButton_4.setFont(new Font("����", Font.PLAIN, 20));
		rdbtnNewRadioButton_4.setBounds(85, 206, 578, 29);
		frame.getContentPane().add(rdbtnNewRadioButton_4);
		rdbtnNewRadioButton_4.setVisible(false);

		JRadioButton rdbtnNewRadioButton_5 = new JRadioButton("New radio button");
		rdbtnNewRadioButton_5.setFont(new Font("����", Font.PLAIN, 20));
		rdbtnNewRadioButton_5.setBounds(85, 242, 578, 29);
		frame.getContentPane().add(rdbtnNewRadioButton_5);
		rdbtnNewRadioButton_5.setVisible(false);

		JRadioButton rdbtnNewRadioButton_6 = new JRadioButton("New radio button");
		rdbtnNewRadioButton_6.setFont(new Font("����", Font.PLAIN, 20));
		rdbtnNewRadioButton_6.setBounds(85, 278, 578, 29);
		frame.getContentPane().add(rdbtnNewRadioButton_6);
		rdbtnNewRadioButton_6.setVisible(false);

		JRadioButton rdbtnNewRadioButton_7 = new JRadioButton("New radio button");
		rdbtnNewRadioButton_7.setFont(new Font("����", Font.PLAIN, 20));
		rdbtnNewRadioButton_7.setBounds(85, 314, 578, 29);
		frame.getContentPane().add(rdbtnNewRadioButton_7);
		rdbtnNewRadioButton_7.setVisible(false);

		JRadioButton rdbtnNewRadioButton_8 = new JRadioButton("New radio button");
		rdbtnNewRadioButton_8.setFont(new Font("����", Font.PLAIN, 20));
		rdbtnNewRadioButton_8.setBounds(85, 350, 578, 29);
		frame.getContentPane().add(rdbtnNewRadioButton_8);
		rdbtnNewRadioButton_8.setVisible(false);

		JRadioButton rdbtnNewRadioButton_9 = new JRadioButton("New radio button");
		rdbtnNewRadioButton_9.setFont(new Font("����", Font.PLAIN, 20));
		rdbtnNewRadioButton_9.setBounds(85, 386, 582, 29);
		frame.getContentPane().add(rdbtnNewRadioButton_9);
		rdbtnNewRadioButton_9.setVisible(false);

		booknumber = 0; // ��ʼ��������Ŀ
		overmoney = 0; // ��ʼ�����ڷ���
		String borrowbooks = ""; // ��ʼ����ȡ��¼�û��Ľ�������ַ���
		String borrowbooktimes = ""; // ��ʼ����ȡ��¼�û��Ľ���ʱ���ַ���
		String[] borrowbookname = new String[10];// ���ô洢��������������
		// ���ݿ�MySQL
		try {
			Class.forName(driver);// ע������/��������
			Connection conn = DriverManager.getConnection(url, user, password); // �������Ӷ��� /�������ݿ�
			Statement state = conn.createStatement(); // ����Statement/PreparedStatement��������ִ��SQL���
			String Sql = "select * from user where Code = '" + code + "'"; // Ҫִ�е�SQL���
			ResultSet rs = state.executeQuery(Sql);// �����
			while (rs.next()) {
				credit = rs.getInt("Credit"); // ��ȡ��¼�û������û���
				booknumber = rs.getInt("BookNumber"); // ��ȡ��¼�û��Ľ�������
				borrowbooks = rs.getString("BorrowBook"); // ��ȡ��¼�û��Ľ������
				borrowbooktimes = rs.getString("BorrowBookTime");// ��ȡ��¼�û��Ľ���ʱ��
			}
			char[] borrowbookcodeschar = borrowbooks.toCharArray(); // ��ʱ�洢��������ַ�������
			char[] borrowbookcode_char = new char[10]; // ��ʱ�洢��������ַ�������
			for (int i = 0; i < booknumber; i++) {// ��ÿ��������
				for (int j = 0; j < 10; j++)// ���һ��������
					borrowbookcode_char[j] = borrowbookcodeschar[i * 11 + j];
				borrowbookcode[i] = String.valueOf(borrowbookcode_char);// ��ȡ�洢������ŵ�����
				String sql = "select * from book where Code = '" + borrowbookcode[i] + "'"; // Ҫִ�е�SQL���
				rs = state.executeQuery(sql);
				while (rs.next())// ��ȡ��Ӧ����������
					borrowbookname[i] = rs.getString("BookName");
			}
			rs.close();
			state.close();
			conn.close();// �ر�Connection���ʵ��
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
		if (booknumber == 0)// û������鼮ʱ
		{
			lblNewLabel.setText("��û���ڽ���鼮��");
			btnNewButton.setVisible(false);
		} else {
			Calendar nowDate = new GregorianCalendar(); // ��ȡ��ǰϵͳƽ̨��Ĭ�ϵ����ڡ�ʱ���ʱ��
			boolean[] check = new boolean[10]; // �ж��Ƿ�ѡ�и�ѡ��
			int nowYear = nowDate.get(Calendar.YEAR); // ��ȡ��ǰϵͳƽ̨��Ĭ�����ڵġ��ꡱ
			int nowMonth = nowDate.get(Calendar.MONTH) + 1; // ��ȡ��ǰϵͳƽ̨��Ĭ�����ڵġ��¡�
			int nowDay = nowDate.get(Calendar.DAY_OF_MONTH); // ��ȡ��ǰϵͳƽ̨��Ĭ�����ڵġ��ա�
			int[] overdays = new int[booknumber];// ���ý���������¼��
			int[] year = new int[booknumber]; // ���ý���ʱ����ݵļ�¼��
			int[] month = new int[booknumber]; // ���ý���ʱ���·ݵļ�¼��
			int[] day = new int[booknumber]; // ���ý���ʱ����ݵļ�¼��
			char[] borrowbooktimeschar = borrowbooktimes.toCharArray();// ��ʱ�洢����ʱ���ַ�������
			for (int i = 0, temp = 0; i < booknumber; i++) {
				check[i] = false;
				char[] borrowbooktime_char = new char[10];// ��ʱ�洢��������ʱ���ַ�������
				for (int j = 0; j < 10; j++)// ��ֹ�ڴ治��
					borrowbooktime_char[j] = ' ';
				for (int j = 0; temp < borrowbooktimes.length(); j++)
					if (borrowbooktimeschar[temp] != ';') {
						borrowbooktime_char[j] = borrowbooktimeschar[temp];
						temp++;
					} else {
						temp++;// �������
						break;
					}
				borrowbooktime[i] = String.copyValueOf(borrowbooktime_char);// ��ȡ��������ʱ����ַ���
				int[] _location = new int[] { 0, 0, 0 };// ��¼_��λ��
				for (int j = 0, t = 0; j < 10; j++)// ��ȡ��������ʱ��������գ������ٴ�ϸ��
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
				
			} // ��ȡ��ݣ��·ݣ����
			for (int i = 0; i < 10; i++) 
			check[i] = false;
			for (int i = 0; i < booknumber; i++) {
				switch (i) {// ��ʾ��Ӧ����������ѡ����������ͽ���ʱ�䣩
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
				int sumyear = nowYear - year[i]; // ���֮��
				int summonth = nowMonth - month[i];// �·�֮��
				int sumday = nowDay - day[i]; // ���֮��
				overdays[i] = 365 * sumyear + 30 * summonth + sumday;
			}

			rdbtnNewRadioButton.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					if (rdbtnNewRadioButton.isSelected()) {
						check[0] = true;// �����鴫��ѡ��״̬�������
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
			rdbtnNewRadioButton.addActionListener(new ActionListener()// ѡ�й黹ͼ���¼�
			{
				@Override
				public void mouseClicked(MouseEvent arg0) {
					if (rdbtnNewRadioButton.isSelected()) {
						check[0] = true;// �����鴫��ѡ��״̬�������
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
			rdbtnNewRadioButton_1.addActionListener(new ActionListener()// ѡ�й黹ͼ���¼�
			{
				@Override
				public void actionPerformed(ActionEvent e){
					if (rdbtnNewRadioButton_1.isSelected()) {
						check[1] = true;// �����鴫��ѡ��״̬�������
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
			rdbtnNewRadioButton_2.addActionListener(new ActionListener()// ѡ�й黹ͼ���¼�
			{
				public void actionPerformed(ActionEvent e){
					if (rdbtnNewRadioButton_2.isSelected()) {
						check[2] = true;// �����鴫��ѡ��״̬�������
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
			rdbtnNewRadioButton_3.addActionListener(new ActionListener()// ѡ�й黹ͼ���¼�
			{
				@Override
				public void actionPerformed(ActionEvent e){
					if (rdbtnNewRadioButton_3.isSelected()) {
						check[3] = true;// �����鴫��ѡ��״̬�������
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
			rdbtnNewRadioButton_4.addActionListener(new ActionListener()// ѡ�й黹ͼ���¼�
			{
				@Override
				public void actionPerformed(ActionEvent e){
					if (rdbtnNewRadioButton_4.isSelected()) {
						check[4] = true;// �����鴫��ѡ��״̬�������
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
			rdbtnNewRadioButton_5.addActionListener(new ActionListener()// ѡ�й黹ͼ���¼�
			{
				@Override
				public void actionPerformed(ActionEvent e){
					if (rdbtnNewRadioButton_5.isSelected()) {
						check[5] = true;// �����鴫��ѡ��״̬�������
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
			rdbtnNewRadioButton_6.addActionListener(new ActionListener()// ѡ�й黹ͼ���¼�
			{
				@Override
				public void actionPerformed(ActionEvent e){
					if (rdbtnNewRadioButton_6.isSelected()) {
						check[6] = true;// �����鴫��ѡ��״̬�������
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
			rdbtnNewRadioButton_7.addActionListener(new ActionListener()// ѡ�й黹ͼ���¼�
			{
				@Override
				public void actionPerformed(ActionEvent e){
					if (rdbtnNewRadioButton_7.isSelected()) {
						check[7] = true;// �����鴫��ѡ��״̬�������
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
			rdbtnNewRadioButton_8.addActionListener(new ActionListener()// ѡ�й黹ͼ���¼�
			{
				@Override
				public void actionPerformed(ActionEvent e){
					if (rdbtnNewRadioButton_8.isSelected()) {
						check[8] = true;// �����鴫��ѡ��״̬�������
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
			rdbtnNewRadioButton_9.addActionListener(new ActionListener()// ѡ�й黹ͼ���¼�
			{
				@Override
				public void actionPerformed(ActionEvent e){
					if (rdbtnNewRadioButton_9.isSelected()) {
						check[9] = true;// �����鴫��ѡ��״̬�������
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

			btnNewButton.addActionListener(new ActionListener()// ȷ�ϻ����¼�
			{
				public void actionPerformed(ActionEvent arg0) {
					boolean choosestate = false; // �ж�����ѡ��ͼ��
					boolean error = false; // �жϸ��������Ƿ�ɹ�
					String updateborrowbooks = ""; // ���º���û�������Ŵ�ſռ�
					String updateborrowbooktimes = "";// ���º���û�����ʱ���ſռ�
					int updatebooknumber = booknumber;// ���º���û�����������ſռ�
					for (int i = 0, temp = 0; i < booknumber; i++)
						if (check[i])// ѡ��ѡ��ͼ��
						{
							choosestate = true;
							updatebooknumber--; // ���½�������
							int storenumber = 0;// ���ô洢��Ӧͼ����ڹ�����
							try {// ��ȡͼ���ڹ�����
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
							try {// ����ÿ��ͼ���ڹ�����
								Class.forName(driver);
								Connection conn = DriverManager.getConnection(url, user, password);
								String Sql = "update book set StoreNumber='" + (++storenumber) + "'where Code='"
										+ borrowbookcode[i] + "'";
								Statement pstmt = conn.createStatement();
								pstmt.executeUpdate(Sql);// �������ݵ�SQL���ִ��
								pstmt.close();
								conn.close();
							} catch (Exception e) {
								e.printStackTrace();
								error = true;
							}
						} else// δѡ���ͼ��
						{// ��ȡ�����ϸ��º���û�������Ϣ��ͼ�����ͽ���ʱ�䣩
							temp++;
							if (temp == 1) {
								updateborrowbooks = updateborrowbooks.concat(borrowbookcode[i]);
								updateborrowbooktimes = updateborrowbooktimes.concat(borrowbooktime[i]);
							} else {
								updateborrowbooks = updateborrowbooks.concat(";".concat(borrowbookcode[i]));
								updateborrowbooktimes = updateborrowbooktimes.concat(";".concat(borrowbooktime[i]));
							}
						}
					if (choosestate)// ��ͼ�鱻ѡ��ʱ
						try {// �����û��Ľ�����Ϣ
							Class.forName(driver);
							Connection conn = DriverManager.getConnection(url, user, password);
							String Sql = "update user set Credit = '" + credit + "',BookNumber = '" + updatebooknumber
									+ "',BorrowBook = '" + updateborrowbooks + "',BorrowBookTime = '"
									+ updateborrowbooktimes + "' where Code='" + code + "'";
							Statement pstmt = conn.createStatement();
							pstmt.executeUpdate(Sql);// �������ݵ�SQL���ִ��
							pstmt.close();
							conn.close();
						} catch (Exception e) {
							e.printStackTrace();
							error = true;
						}
					if (!choosestate)
						JOptionPane.showMessageDialog(null, "��ѡ��ͼ�飡", "������ʾ", JOptionPane.ERROR_MESSAGE);
					else if (error)
						JOptionPane.showMessageDialog(null, "δ֪����[!]�����ٳ���һ�Σ�", "����ʧ����ʾ", JOptionPane.ERROR_MESSAGE);
					else {
						if (overmoney <= 0)
							JOptionPane.showMessageDialog(null, "���ѳɹ��黹ѡ��ͼ�飡\n�������û���Ϊ" + credit, "����ɹ���ʾ",
									JOptionPane.ERROR_MESSAGE);
						else
							JOptionPane.showMessageDialog(null,
									"���ѳɹ��黹ѡ��ͼ�飡\n�鱾���ڷ���Ϊ " + overmoney + " RMB\n�������û���Ϊ" + credit, "����ɹ���ʾ",
									JOptionPane.ERROR_MESSAGE);
						// ���ز˵�����

						frame.setVisible(false);
						menu window = new menu();
						window.frame.setVisible(true);
						

					}

				}
			});
		}
		
		btnNewButton_1.addActionListener(new ActionListener()// �����¼�
				{
					public void actionPerformed(ActionEvent arg0) {
						
							frame.setVisible(false);
							menu window = new menu();
							window.frame.setVisible(true);
					}
				});
		
	}
}