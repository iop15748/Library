package Ecust_library;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class menu {

	public JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					menu window = new menu();
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
	public menu() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("\u56FE\u4E66\u9986");
		frame.setBounds(100, 100, 700, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel label = new JLabel("\u670D\u52A1\u9009\u62E9");
		label.setBounds(251, 32, 160, 42);
		label.setForeground(new Color(3, 71, 136));
		label.setFont(new Font("华文行楷", Font.BOLD, 38));
		frame.getContentPane().add(label);
		
		JButton button = new JButton("\u56FE\u4E66\u67E5\u8BE2");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				SearchBook window = new SearchBook();
				window.frame.setVisible(true);
			}
		});
		button.setBounds(251, 121, 160, 55);
		button.setFont(new Font("宋体", Font.PLAIN, 24));
		frame.getContentPane().add(button);
		
		JButton button_1 = new JButton("\u56FE\u4E66\u501F\u9605");
		button_1.setBounds(251, 221, 160, 55);
		button_1.setFont(new Font("宋体", Font.PLAIN, 24));
		frame.getContentPane().add(button_1);
		
		JButton button_2 = new JButton("\u56FE\u4E66\u5F52\u8FD8");
		button_2.setFont(new Font("宋体", Font.PLAIN, 24));
		button_2.setBounds(251, 321, 160, 55);
		frame.getContentPane().add(button_2);
		
		JButton button_3 = new JButton("\u9000\u51FA");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();
			}
		});
		button_3.setFont(new Font("宋体", Font.PLAIN, 24));
		button_3.setBounds(251, 421, 160, 55);
		frame.getContentPane().add(button_3);
	}
}



















