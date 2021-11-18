package app_QLT;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class FrmChinh extends JFrame implements ActionListener, WindowListener {

	private JButton temp;
	private JButton btnQuanLyHoaDon;
	private CardLayout cardLayout;
	private JPanel pnlChange;
	private JButton btnThoat;
	private JButton btnQuanLyTaiKhoan;
	private JButton btnQuanLyThuoc;
	private JButton btnThongKe;
	private JButton btnQuanLyNhanVien;
	
	private FrmNhanVien trangNV = new FrmNhanVien();
	private FrmThongKe trangTK = new FrmThongKe();
	private FrmThuoc trangThuoc = new FrmThuoc();
	private FrmBanHang trangBH = new FrmBanHang();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmChinh frame = new FrmChinh();
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
	public FrmChinh() {
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setSize(1920, 1037);
		setResizable(false);
		setTitle("Ứng dụng quản lý cửa hàng thuốc Long Đúng");
		setIconImage(new ImageIcon("icon/thuoc _navbar.png").getImage());
		getContentPane().setLayout(null);

		// thông báo khi thoát
		addWindowListener(this);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(50, 130, 253));
		panel.setBounds(0, 0, 320, 1029);
		getContentPane().add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setIcon(new ImageIcon("icon/thuoc _navbar.png"));
		lblNewLabel.setBounds(69, 21, 185, 104);
		panel.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("CỬA HÀNG LONG ĐÚNG");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_1.setBounds(9, 147, 294, 27);
		panel.add(lblNewLabel_1);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(0, 200, 320, 807);
		panel.add(panel_1);
		panel_1.setLayout(null);

		/**
		 * các nút menu
		 */
		btnThoat = new JButton("Thoát");
		btnThoat.setFocusPainted(false);
		btnThoat.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnThoat.setIcon(new ImageIcon("icon\\icons8-close-64.png"));
		btnThoat.setBounds(0, 670, 320, 134);
		panel_1.add(btnThoat);
		btnThoat.setForeground(Color.BLACK);
		btnThoat.setFont(new Font("Tahoma", Font.PLAIN, 24));
		btnThoat.setBorder(new LineBorder(new Color(0, 0, 0)));
		btnThoat.setBackground(new Color(255, 255, 255));
		// Sự kiện thoát
		btnThoat.addActionListener(e -> {
			thongBaoThoat();
		});

		btnQuanLyTaiKhoan = new JButton("Quản lý tài khoản");
		btnQuanLyTaiKhoan.setFocusPainted(false);
		btnQuanLyTaiKhoan.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnQuanLyTaiKhoan.setIcon(new ImageIcon("icon/icons8-userSetting-39.png"));
		btnQuanLyTaiKhoan.setBounds(0, 536, 320, 134);
		panel_1.add(btnQuanLyTaiKhoan);
		btnQuanLyTaiKhoan.setIconTextGap(10);
		btnQuanLyTaiKhoan.setForeground(Color.BLACK);
		btnQuanLyTaiKhoan.setFont(new Font("Tahoma", Font.PLAIN, 24));
		btnQuanLyTaiKhoan.setBorder(new LineBorder(new Color(0, 0, 0)));
		btnQuanLyTaiKhoan.setBackground(new Color(255, 255, 255));

		btnQuanLyThuoc = new JButton("Quản lý bán thuốc");
		btnQuanLyThuoc.setFocusPainted(false);
		btnQuanLyThuoc.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnQuanLyThuoc.setIcon(new ImageIcon("icon/thuoc_menu.png"));
		btnQuanLyThuoc.setBounds(0, 0, 320, 134);

		
		
		panel_1.add(btnQuanLyThuoc);
		btnQuanLyThuoc.setIconTextGap(10);
		btnQuanLyThuoc.setForeground(Color.BLACK);
		btnQuanLyThuoc.setFont(new Font("Tahoma", Font.PLAIN, 24));
		btnQuanLyThuoc.setBorder(new LineBorder(new Color(0, 0, 0)));
		btnQuanLyThuoc.setBackground(new Color(255, 255, 255));

		btnThongKe = new JButton("Thống kê thu chi");
		btnThongKe.setFocusPainted(false);
		btnThongKe.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnThongKe.setIcon(new ImageIcon("icon/icons8-chart-39.png"));
		btnThongKe.setBounds(0, 268, 320, 134);
		panel_1.add(btnThongKe);
		btnThongKe.setIconTextGap(0);
		btnThongKe.setForeground(Color.BLACK);
		btnThongKe.setFont(new Font("Tahoma", Font.PLAIN, 24));
		btnThongKe.setBorder(new LineBorder(new Color(0, 0, 0)));
		btnThongKe.setBackground(new Color(255, 255, 255));

		// btnQuanLyLuong.setBounds(0, 230, 320, 115);

		btnQuanLyHoaDon = new JButton("Quản lý hóa đơn");
		btnQuanLyHoaDon.setFocusPainted(false);
		btnQuanLyHoaDon.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnQuanLyHoaDon.setIcon(new ImageIcon("icon/icons8-money-39.png"));
		btnQuanLyHoaDon.setBounds(0, 134, 320, 134);
		panel_1.add(btnQuanLyHoaDon);
		btnQuanLyHoaDon.setIconTextGap(10);
		btnQuanLyHoaDon.setForeground(Color.BLACK);
		btnQuanLyHoaDon.setFont(new Font("Tahoma", Font.PLAIN, 24));
		btnQuanLyHoaDon.setBorder(new LineBorder(new Color(0, 0, 0)));
		btnQuanLyHoaDon.setBackground(new Color(255, 255, 255));
		btnQuanLyHoaDon.setAlignmentX(1.0f);

		btnQuanLyNhanVien = new JButton("Quản lý nhân viên");
		btnQuanLyNhanVien.setFocusPainted(false);
		btnQuanLyNhanVien.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnQuanLyNhanVien.setIconTextGap(10);
		btnQuanLyNhanVien.setIcon(new ImageIcon("icon/icons8-user-39.png"));
		btnQuanLyNhanVien.setBounds(0, 402, 320, 134);
		panel_1.add(btnQuanLyNhanVien);
		btnQuanLyNhanVien.setForeground(Color.BLACK);
		btnQuanLyNhanVien.setFont(new Font("Tahoma", Font.PLAIN, 24));
		btnQuanLyNhanVien.setBorder(new LineBorder(new Color(0, 0, 0)));
		btnQuanLyNhanVien.setBackground(new Color(255, 255, 255));

		btnQuanLyHoaDon.addActionListener(this);
		btnQuanLyNhanVien.addActionListener(this);
		btnQuanLyThuoc.addActionListener(this);
		btnQuanLyTaiKhoan.addActionListener(this);
		btnThongKe.addActionListener(this);

		pnlChange = new JPanel();
		pnlChange.setBounds(320, 0, 1600, 1007);
		getContentPane().add(pnlChange);
		pnlChange.setLayout(new CardLayout(0, 0));

		cardLayout = (CardLayout) pnlChange.getLayout();

		pnlChange.add(trangTK, "btnThongKe");
		pnlChange.add(new FrmTest(), "btnQuanLyHoaDon");
		pnlChange.add(trangNV, "btnQuanLyNhanVien");
		pnlChange.add(trangThuoc, "btnQuanLyThuoc");
		//pnlChange.add(trangTK, "btnQuanLyTaiKhoan");

		// set active khi bắt đầu vào
		temp = btnThongKe;
		activeButton(btnThongKe);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object key = e.getSource();

		activeButton((JButton) key);
		inActiveButton(temp);
		temp = (JButton) key;

		if (key == btnQuanLyHoaDon) {
			cardLayout.show(pnlChange, "btnQuanLyHoaDon");
		} else if (key == btnQuanLyNhanVien)
			cardLayout.show(pnlChange, "btnQuanLyNhanVien");
		else if (key == btnQuanLyThuoc)
			cardLayout.show(pnlChange, "btnQuanLyThuoc");
		else if (key == btnQuanLyTaiKhoan)
			cardLayout.show(pnlChange, "btnQuanLyTaiKhoan");
		else if (key == btnThongKe) {
			cardLayout.show(pnlChange, "btnThongKe");
		}

	}

	/**
	 * đổ màu tab khi active
	 * 
	 * @param buttonActive
	 * 
	 */
	public void activeButton(JButton buttonActive) {
		buttonActive.setEnabled(false);
		buttonActive.setBackground(new Color(230, 230, 230));
		buttonActive.setForeground(Color.black);
	}

	/**
	 * đổ màu lại khi inactive
	 * 
	 * @param buttonInactive
	 */
	public void inActiveButton(JButton buttonInactive) {
		buttonInactive.setEnabled(true);
		buttonInactive.setForeground(Color.black);
		buttonInactive.setBackground(new Color(255, 255, 255));
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

	@Override
	public void windowClosing(WindowEvent e) {
		thongBaoThoat();
	}

	@Override
	public void windowOpened(WindowEvent e) {
	}

	@Override
	public void windowClosed(WindowEvent e) {
	}

	@Override
	public void windowIconified(WindowEvent e) {
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
	}

	@Override
	public void windowActivated(WindowEvent e) {
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
	}
}
