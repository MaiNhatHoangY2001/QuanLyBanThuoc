package frm;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.DisplayMode;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import chucNang.ChucNang;

public class FrmChinh extends JFrame implements ActionListener, WindowListener {

	private JButton temp;
	private JButton btnQuanLyHoaDon;
	private CardLayout cardLayout;
	private JPanel pnlChange;
	private JButton btnThoat;
	private JButton btnQuanLyThuoc;
	private JButton btnThongKe;
	private JButton btnQuanLyNhanVien;

	private FrmNhanVien trangNV = new FrmNhanVien();
	private FrmThongKe trangTK = new FrmThongKe();
	private FrmQuanLyThuoc trangThuoc = new FrmQuanLyThuoc();
	private FrmBanThuoc trangBH = new FrmBanThuoc();
	private JLabel lblNgay;
	private JLabel lblGio;
	private JLabel lblNewLabel_2;
	private JLabel lblHead;
	protected JLabel lblTenDN;
	private JPanel pnlHeadMenu;
	private JPanel pnlMenu;

	/**
	 * Create the frame.
	 */
	public FrmChinh() {

		Rectangle r = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();

		ChucNang.setTableAlternateRow();
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setResizable(false);
		setUndecorated(false);
		setSize(r.width, r.height);
		setTitle("Ứng dụng quản lý cửa hàng thuốc Long Đúng");
		setIconImage(new ImageIcon("icon/thuoc _navbar.png").getImage());
		getContentPane().setLayout(null);

		// thông báo khi thoát
		addWindowListener(this);

		pnlHeadMenu = new JPanel();
		pnlHeadMenu.setBackground(new Color(50, 130, 253));
		pnlHeadMenu.setBounds(0, 0, 320, 1029);
		getContentPane().add(pnlHeadMenu);
		pnlHeadMenu.setLayout(null);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setIcon(new ImageIcon("icon/thuoc _navbar.png"));
		lblNewLabel.setBounds(69, 21, 185, 104);
		pnlHeadMenu.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("CỬA HÀNG LONG ĐÚNG");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_1.setBounds(9, 147, 294, 27);
		pnlHeadMenu.add(lblNewLabel_1);

		pnlMenu = new JPanel();
		pnlMenu.setBackground(Color.WHITE);
		pnlMenu.setBounds(0, 200, 320, 829);
		pnlHeadMenu.add(pnlMenu);
		pnlMenu.setLayout(null);

		JPanel pnlHead = new JPanel();
		pnlHead.setBackground(new Color(68, 142, 255));
		pnlHead.setBounds(320, 0, 1600, 91);
		getContentPane().add(pnlHead);
		pnlHead.setLayout(null);

		lblHead = new JLabel("QUẢN LÝ HÓA ĐƠN");
		lblHead.setHorizontalAlignment(SwingConstants.CENTER);
		lblHead.setForeground(Color.WHITE);
		lblHead.setFont(new Font("Tahoma", Font.BOLD, 50));
		lblHead.setBounds(460, 0, 614, 80);
		pnlHead.add(lblHead);

		/**
		 * set Ngày giờ
		 */
		lblNgay = new JLabel("New label");
		lblNgay.setForeground(Color.WHITE);
		lblNgay.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblNgay.setBounds(0, 16, 233, 33);
		pnlHead.add(lblNgay);

		lblGio = new JLabel("New label");
		lblGio.setForeground(Color.WHITE);
		lblGio.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblGio.setBounds(0, 50, 201, 30);
		pnlHead.add(lblGio);

		setGio(lblGio, lblNgay);

		/**
		 * Tên đăng nhập
		 */

		lblTenDN = new JLabel("Mai Ngọc Long");
		lblTenDN.setHorizontalTextPosition(SwingConstants.LEFT);
		lblTenDN.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTenDN.setForeground(Color.WHITE);
		lblTenDN.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblTenDN.setBounds(1272, 11, 260, 33);
		pnlHead.add(lblTenDN);

		JLabel lblIconUser = new JLabel("");
		Image imgUser = new ImageIcon("icon/userNho.png").getImage();
		lblIconUser.setIcon(new ImageIcon(imgUser));
		lblIconUser.setBounds(1536, 10, 38, 30);
		pnlHead.add(lblIconUser);

		/**
		 * Đăng xuất
		 */
		JLabel lblDangXuat = new JLabel("Đăng xuất");
		lblDangXuat.setForeground(Color.WHITE);
		lblDangXuat.setFont(new Font("Arial", Font.PLAIN, 24));
		lblDangXuat.setBounds(1419, 55, 110, 24);
		pnlHead.add(lblDangXuat);

		JLabel lblIconDX = new JLabel("");
		Image imgDX = new ImageIcon("icon/thoatNho.png").getImage();
		lblIconDX.setIcon(new ImageIcon(imgDX));
		lblIconDX.setBounds(1536, 50, 38, 30);
		pnlHead.add(lblIconDX);
		lblDangXuat.setCursor(new Cursor(Cursor.HAND_CURSOR));

		lblDangXuat.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {

			}

			@Override
			public void mousePressed(MouseEvent e) {
				int temp = JOptionPane.showConfirmDialog(null, "Bạn có muốn đăng xuất", "Thông báo",
						JOptionPane.YES_NO_OPTION);
				if (temp == JOptionPane.YES_OPTION) {
					new FrmDangNhap().setVisible(true);
					setVisible(false);
				}

			}

			@Override
			public void mouseExited(MouseEvent e) {

			}

			@Override
			public void mouseEntered(MouseEvent e) {

			}

			@Override
			public void mouseClicked(MouseEvent e) {

			}
		});

		/**
		 * các nút menu
		 */
		btnThoat = new JButton("Thoát");
		btnThoat.setFocusPainted(false);
		btnThoat.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnThoat.setIcon(new ImageIcon("icon\\icons8-close-64.png"));
		btnThoat.setBounds(0, 645, 320, 162);
		pnlMenu.add(btnThoat);
		btnThoat.setForeground(Color.BLACK);
		btnThoat.setFont(new Font("Tahoma", Font.PLAIN, 24));
		btnThoat.setBorder(new LineBorder(new Color(0, 0, 0)));
		btnThoat.setBackground(new Color(255, 255, 255));
		// Sự kiện thoát
		btnThoat.addActionListener(e -> {
			thongBaoThoat();
		});

		btnQuanLyThuoc = new JButton("Quản lý bán thuốc");
		btnQuanLyThuoc.setFocusPainted(false);
		btnQuanLyThuoc.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnQuanLyThuoc.setIcon(new ImageIcon("icon/thuoc_menu.png"));
		btnQuanLyThuoc.setBounds(0, 1, 320, 160);

		pnlMenu.add(btnQuanLyThuoc);
		btnQuanLyThuoc.setIconTextGap(10);
		btnQuanLyThuoc.setForeground(Color.BLACK);
		btnQuanLyThuoc.setFont(new Font("Tahoma", Font.PLAIN, 24));
		btnQuanLyThuoc.setBorder(new LineBorder(new Color(0, 0, 0)));
		btnQuanLyThuoc.setBackground(new Color(255, 255, 255));

		btnThongKe = new JButton("Thống kê thu chi");
		btnThongKe.setFocusPainted(false);
		btnThongKe.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnThongKe.setIcon(new ImageIcon("icon/icons8-chart-39.png"));
		btnThongKe.setBounds(0, 323, 320, 160);
		pnlMenu.add(btnThongKe);
		btnThongKe.setIconTextGap(0);
		btnThongKe.setForeground(Color.BLACK);
		btnThongKe.setFont(new Font("Tahoma", Font.PLAIN, 24));
		btnThongKe.setBorder(new LineBorder(new Color(0, 0, 0)));
		btnThongKe.setBackground(new Color(255, 255, 255));

		btnQuanLyHoaDon = new JButton("Quản lý hóa đơn");
		btnQuanLyHoaDon.setFocusPainted(false);
		btnQuanLyHoaDon.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnQuanLyHoaDon.setIcon(new ImageIcon("icon/icons8-money-39.png"));
		btnQuanLyHoaDon.setBounds(0, 162, 320, 160);
		pnlMenu.add(btnQuanLyHoaDon);
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
		btnQuanLyNhanVien.setBounds(0, 484, 320, 160);
		pnlMenu.add(btnQuanLyNhanVien);
		btnQuanLyNhanVien.setForeground(Color.BLACK);
		btnQuanLyNhanVien.setFont(new Font("Tahoma", Font.PLAIN, 24));
		btnQuanLyNhanVien.setBorder(new LineBorder(new Color(0, 0, 0)));
		btnQuanLyNhanVien.setBackground(new Color(255, 255, 255));

		btnQuanLyHoaDon.addActionListener(this);
		btnQuanLyNhanVien.addActionListener(this);
		btnQuanLyThuoc.addActionListener(this);
		btnThongKe.addActionListener(this);

		pnlChange = new JPanel();
		pnlChange.setBounds(320, 89, 1600, 928);
		getContentPane().add(pnlChange);
		pnlChange.setLayout(new CardLayout(0, 0));

		cardLayout = (CardLayout) pnlChange.getLayout();

		pnlChange.add(trangBH, "btnQuanLyHoaDon");
		pnlChange.add(trangTK, "btnThongKe");
		pnlChange.add(trangNV, "btnQuanLyNhanVien");
		pnlChange.add(trangThuoc, "btnQuanLyThuoc");
		// pnlChange.add(trangTK, "btnQuanLyTaiKhoan");

		// set active khi bắt đầu vào
		temp = btnQuanLyHoaDon;
		activeButton(btnQuanLyHoaDon);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object key = e.getSource();

		activeButton((JButton) key);
		inActiveButton(temp);
		temp = (JButton) key;

		if (key == btnQuanLyHoaDon) {
			cardLayout.show(pnlChange, "btnQuanLyHoaDon");
			lblHead.setText("QUẢN LÝ HÓA ĐƠN");
		} else if (key == btnQuanLyNhanVien) {
			cardLayout.show(pnlChange, "btnQuanLyNhanVien");
			lblHead.setText("QUẢN LÝ NHÂN VIÊN");
		} else if (key == btnQuanLyThuoc) {
			cardLayout.show(pnlChange, "btnQuanLyThuoc");
			lblHead.setText("QUẢN LÝ THUỐC");
		} else if (key == btnThongKe) {
			cardLayout.show(pnlChange, "btnThongKe");
			lblHead.setText("THỐNG KÊ");
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

	/**
	 * chỉnh giờ cho lable
	 * 
	 * @param lblGio
	 */
	public void setGio(JLabel lblGio, JLabel lblNgay) {
		TimerTask timerTask = new TimerTask() {
			@SuppressWarnings("deprecation")
			@Override
			public void run() {
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
				lblNgay.setText(formatter.format(LocalDate.now()));
				lblGio.setText((new Date().getHours() >= 10 ? "" : "0") + (new Date().getHours())
						+ ((new Date().getSeconds() % 2) != 0 ? " " : ":")
						+ ((new Date().getMinutes() >= 10 ? "" : "0") + (new Date().getMinutes())));
			}

		};
		long delay = 1000L;
		Timer timer = new Timer("Timer");
		timer.schedule(timerTask, 0, delay);
	}

	public void setTaiKhoan(String ten, boolean fullAccess) {
		lblTenDN.setText(ten);

		if (!fullAccess) {
			pnlMenu.remove(btnQuanLyNhanVien);
			btnThoat.setBounds(0, 484, 320, 160);
		}

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
