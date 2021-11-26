package app_QLT;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import chucNang.RoundedPanel;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class FrmDangNhap1 extends JFrame {

	private JPanel contentPane;
	private final JPanel pnlTrai = new JPanel();
	private JTextField txtTaiKhoang;
	private JTextField txtMatKhau;
	private JButton btnngNhp;
	private JButton btnThot;
	private JLabel lblQuenMatKhau;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmDangNhap1 frame = new FrmDangNhap1();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FrmDangNhap1() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1214, 739);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		pnlTrai.setBounds(0, 0, 600, 700);
		contentPane.add(pnlTrai);
		pnlTrai.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("icon/R.png"));
		lblNewLabel.setBounds(125, 189, 350, 321);
		pnlTrai.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("LongDung");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblNewLabel_1.setBounds(35, 20, 154, 37);
		pnlTrai.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Quầy thuốc tây LongDung hân hạnh phục vụ");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_2.setBounds(102, 521, 395, 30);
		pnlTrai.add(lblNewLabel_2);
		
		JPanel pnlPhai = new JPanel();
		pnlPhai.setBackground(new Color(20, 140, 255));
		pnlPhai.setBounds(598, 0, 600, 700);
		contentPane.add(pnlPhai);
		pnlPhai.setLayout(null);
		
		RoundedPanel panel = new RoundedPanel();
		panel.setBackground(Color.WHITE);
		panel.setShady(false);
		panel.setBounds(104, 196, 400, 400);
		pnlPhai.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_4 = new JLabel("Tài khoảng");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_4.setBounds(30, 62, 100, 25);
		panel.add(lblNewLabel_4);
		
		txtTaiKhoang = new JTextField();
		txtTaiKhoang.setBounds(30, 98, 340, 40);
		panel.add(txtTaiKhoang);
		txtTaiKhoang.setColumns(10);
		
		txtMatKhau = new JTextField();
		txtMatKhau.setColumns(10);
		txtMatKhau.setBounds(30, 196, 340, 40);
		panel.add(txtMatKhau);
		
		JLabel lblNewLabel_4_1 = new JLabel("Mật khẩu");
		lblNewLabel_4_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_4_1.setBounds(30, 160, 100, 25);
		panel.add(lblNewLabel_4_1);
		
		lblQuenMatKhau = new JLabel("Quên mật khẩu?  ");
		lblQuenMatKhau.setForeground(new Color(106, 105, 105));
		lblQuenMatKhau.setHorizontalAlignment(SwingConstants.TRAILING);
		lblQuenMatKhau.setFont(new Font("Tahoma", Font.ITALIC, 20));
		lblQuenMatKhau.setBounds(94, 247, 276, 25);
		panel.add(lblQuenMatKhau);
		
		btnngNhp = new JButton("Đăng nhâp");
		btnngNhp.setForeground(Color.WHITE);
		btnngNhp.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnngNhp.setBackground(new Color(20, 140, 255));
		btnngNhp.setBounds(30, 283, 150, 50);
		panel.add(btnngNhp);
		
		btnThot = new JButton("Thoát");
		btnThot.setForeground(Color.WHITE);
		btnThot.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnThot.setBackground(new Color(248, 96, 96));
		btnThot.setBounds(220, 283, 150, 50);
		panel.add(btnThot);
		
		JLabel lblNewLabel_3 = new JLabel("Xin Chào");
		lblNewLabel_3.setForeground(Color.WHITE);
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 50));
		lblNewLabel_3.setBounds(104, 103, 222, 50);
		pnlPhai.add(lblNewLabel_3);
	}
}
