package Ecust_library;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*; //javax.swing.JFrame;


import java.io.*;

import java.util.Date;
public class LibraryLogin extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	protected static final GraphicsConfiguration L = null;

	
	JLabel label1 = new JLabel("��ӭ��������˹ͼ���!");
	JLabel label2 = new JLabel("����һλ:  ");
	JButton btnCustomer = new JButton("����");
	JButton btnAdmin = new JButton("����Ա");
	JButton btnExit = new JButton("�˳�");

	
	public LibraryLogin(){
		

		
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		this.setSize(600, 600);
		this.setLocation(350, 50);
		this.setVisible(true);
		this.setTitle("����˹ͼ���ϵͳ");//Optional		
		getContentPane().setLayout(null);

		
		label1.setBounds(147, -115, 700, 600);
		label1.setForeground(Color.blue);
		label1.setFont(new Font("Dialog", Font.BOLD, 25));
		getContentPane().add(label1);
		label2.setFont(new Font("����", Font.PLAIN, 18));
		
		label2.setBounds(63, 72, 700, 600);
		getContentPane().add(label2);		

		btnCustomer.setVisible(true);
		btnCustomer.setBounds(168, 359, 120, 30);
		getContentPane().add(btnCustomer);

		btnAdmin.setVisible(true);
		btnAdmin.setBounds(323, 359, 120, 30);
		getContentPane().add(btnAdmin);

		btnExit.setVisible(true);
		btnExit.setBounds(450, 520, 120, 30);
		getContentPane().add(btnExit);
		

		
		btnAdmin.addActionListener(new java.awt.event.ActionListener() {
	    public void actionPerformed(java.awt.event.ActionEvent evt) {
	    	AdminLogin AdminLogin = new AdminLogin();
	    	}
		});
		
		btnExit.addActionListener(new java.awt.event.ActionListener() {
		    public void actionPerformed(java.awt.event.ActionEvent evt) {
		       System.exit(0);
		    }
		});	
		
		btnCustomer.addActionListener(new java.awt.event.ActionListener() {
		    public void actionPerformed(java.awt.event.ActionEvent evt) {
		    	Library l = null;
		    	UsrLogin UsrLogin = new UsrLogin(l);
		    }
		});
		
		
	}
	
	
	public static void main(String[] a){
		new LibraryLogin();
	}
}
