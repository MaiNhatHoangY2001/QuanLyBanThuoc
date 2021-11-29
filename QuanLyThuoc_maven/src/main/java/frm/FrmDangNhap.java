package frm;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;

import chucNang.RoundedPanel;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Cursor;

public class FrmDangNhap extends JFrame {

	private JPanel contentPane;
	private final JPanel pnlTrai = new JPanel();
	private JTextField txtTaiKhoan;
	private JTextField txtMatKhau;
	private JButton btnDangNhap;
	private JButton btnThoat;
	private FrmChinh chinh = new FrmChinh();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmDangNhap frame = new FrmDangNhap();
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
	public FrmDangNhap() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1214, 739);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		pnlTrai.setBounds(0, 0, 600, 700);
		contentPane.add(pnlTrai);
		pnlTrai.setLayout(null);
		setLocationRelativeTo(null);
		setIconImage(new ImageIcon("icon/thuoc _navbar.png").getImage());
		setTitle("Phần mềm quản lý bán thuốc cửa hàng Hoàng Long");

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("icon/R.png"));
		lblNewLabel.setBounds(125, 189, 350, 321);
		pnlTrai.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Hoàng Long");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblNewLabel_1.setBounds(35, 20, 555, 37);
		pnlTrai.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Quầy thuốc tây Hoàng Long hân hạnh phục vụ");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_2.setBounds(10, 521, 580, 30);
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

		JLabel lblNewLabel_4 = new JLabel("Tài khoản");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_4.setBounds(30, 62, 100, 25);
		panel.add(lblNewLabel_4);

		txtTaiKhoan = new JTextField("long");
		txtTaiKhoan.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtTaiKhoan.setBounds(30, 98, 340, 40);
		panel.add(txtTaiKhoan);
		txtTaiKhoan.setColumns(10);

		txtMatKhau = new JPasswordField("long123");
		txtMatKhau.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtMatKhau.setColumns(10);
		txtMatKhau.setBounds(30, 196, 340, 40);
		panel.add(txtMatKhau);

		JLabel lblNewLabel_4_1 = new JLabel("Mật khẩu");
		lblNewLabel_4_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_4_1.setBounds(30, 160, 100, 25);
		panel.add(lblNewLabel_4_1);

		btnDangNhap = new JButton("Đăng nhâp");
		btnDangNhap.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnDangNhap.setForeground(Color.WHITE);
		btnDangNhap.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnDangNhap.setBackground(new Color(20, 140, 255));
		btnDangNhap.setBounds(30, 283, 150, 50);
		panel.add(btnDangNhap);
		btnDangNhap.addActionListener(e -> {
			String ten = txtTaiKhoan.getText();
			String mk = txtMatKhau.getText().trim();
			if (!(KiemTraRongText(txtTaiKhoan))) {
				if (ten.equals("long")) {
					if (mk.equals("long123")) {
						 chinh.setTaiKhoan(ten, true);
						 chinh.setVisible(true);
						 setVisible(false);
					} else {
						JOptionPane.showMessageDialog(this, "Mật khẩu không chính xác");
					}
				} else {
					if (ten.equals("nhanvien")) {
						if (mk.equals("nhanvien123")) {
							chinh.setTaiKhoan(ten, false);
							chinh.setVisible(true);
							setVisible(false);
						} else {
							JOptionPane.showMessageDialog(this, "Mật khẩu không chính xác");
						}
					} else
						JOptionPane.showMessageDialog(this, "Tên tài khoản không tồn tại");
				}

			} else {
				JOptionPane.showMessageDialog(this, "Tên tài khoản không được bỏ trống");
			}
		});

		btnThoat = new JButton("Thoát");
		btnThoat.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnThoat.setForeground(Color.WHITE);
		btnThoat.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnThoat.setBackground(new Color(248, 96, 96));
		btnThoat.setBounds(220, 283, 150, 50);
		panel.add(btnThoat);
		btnThoat.addActionListener(e -> {
			thongBaoThoat();
		});

		JLabel lblNewLabel_3 = new JLabel("Xin Chào");
		lblNewLabel_3.setForeground(Color.WHITE);
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 50));
		lblNewLabel_3.setBounds(104, 103, 222, 50);
		pnlPhai.add(lblNewLabel_3);
	}

	/**
	 * Thông báo khi muốn thoát ứng dụng
	 */
	public void thongBaoThoat() {
		JOptionPane jOptionPane = new JOptionPane();
		jOptionPane.setBackground(new Color(255, 255, 255));
		int tl = JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn thoát không", "Thông báo thoát",
				JOptionPane.YES_NO_OPTION);
		if (tl == JOptionPane.YES_OPTION) {
			System.exit(0);
		}
	}

	public boolean KiemTraRongText(JTextField txt) {
		return txt.getText().equals("") ? true : false;
	}
}
