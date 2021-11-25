package app_QLT;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JYearChooser;

import connectDB.ConnectDB;
import dao.CTHoaDon_DAO;
import dao.KhachHang_DAO;
import dao.Thuoc_DAO;
import dao.impl.CTHoaDonImpl;
import entity.KhachHang;
import entity.Thuoc;
import chucNang.RoundedPanel;
import java.awt.Font;
import javax.swing.SwingConstants;

public class FrmThongKe1 extends JPanel implements ActionListener, MouseListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JComboBox<Integer> cbNgay, cbThang, cbNam;
	private DefaultTableModel dfKhachHang, dfThuoc;
	private JTable tableKhachHang, tableThuoc;
	private JTextField txtTongKH, txtTongSoThuoc, txtMaKH;
	private JButton btnThongKe, btnThongKeAll;
	private KhachHang_DAO kh_dao;
	private Thuoc_DAO thuoc_dao;
	private CTHoaDon_DAO ctHD_dao;
	private DecimalFormat df = new DecimalFormat("#,###.0");
	private JYearChooser spnYear;
	private JLabel lblTongTienThu;
	private JLabel lblTongTienChi;
	private JLabel lblTongDoanhThu;

	public FrmThongKe1() {
		setLayout(null);
		setSize(1600, 933);
		setBackground(new Color(248, 248, 248));
//		for (int i = 1; i <= 31; i++) {
//
//			cbNgay.addItem(i);
//		}
//		for (int i = 1; i <= 12; i++) {
//
//			cbThang.addItem(i);
//		}
//		for (int i = LocalDate.now().getYear(); i >= LocalDate.now().getYear() - 10; i--) {
//
//			cbNam.addItem(i);
//		}

		// bang thong tin khach hang
		String[] headernv = { "Mã khách hàng", "Tên khách hàng", "Ngày sinh", "Giới tính", "Địa chỉ", "Số điện thoại" };
		dfKhachHang = new DefaultTableModel(headernv, 0);
		tableKhachHang = new JTable(dfKhachHang);
		tableKhachHang.setRowHeight(20);
		JScrollPane scroll;
		scroll = new JScrollPane(tableKhachHang, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scroll.setBorder(BorderFactory.createTitledBorder("Thông tin khách hàng"));
		scroll.setBounds(0, 231, 1585, 293);
		scroll.setBackground(new Color(248, 248, 248));
		add(scroll);

		// bang thong tin thuoc ma khach hang da mua
		String[] headerthuoc = { "Mã thuốc", "Tên thuốc", "Đơn giá", "Số lượng", "Tên nhà cung cấp", "Loại thuốc",
				"Nước sản xuất" };
		dfThuoc = new DefaultTableModel(headerthuoc, 0);
		tableThuoc = new JTable(dfThuoc);
		tableThuoc.setRowHeight(20);
		JScrollPane scrollthuoc;
		scrollthuoc = new JScrollPane(tableThuoc, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollthuoc.setBorder(BorderFactory.createTitledBorder("Thông tin thuốc khách hàng đã mua"));
		scrollthuoc.setBounds(0, 575, 1585, 347);
		scrollthuoc.setBackground(new Color(248, 248, 248));
		add(scrollthuoc);

		// phan tong so luong khach hang
		JLabel lbtongkh = new JLabel("Tổng số khách hàng");
		lbtongkh.setHorizontalAlignment(SwingConstants.RIGHT);
		lbtongkh.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lbtongkh.setBounds(1280, 190, 190, 30);
		add(lbtongkh);
		txtTongKH = new JTextField();
		txtTongKH.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtTongKH.setBounds(1480, 186, 105, 39);
		add(txtTongKH);

		// phan tong so thuoc ban duoc
		JLabel lbtongsothuoc = new JLabel("Tổng số thuốc");
		lbtongsothuoc.setHorizontalAlignment(SwingConstants.RIGHT);
		lbtongsothuoc.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lbtongsothuoc.setBounds(1330, 535, 140, 39);
		add(lbtongsothuoc);
		txtTongSoThuoc = new JTextField();
		txtTongSoThuoc.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtTongSoThuoc.setBounds(1480, 535, 105, 39);
		add(txtTongSoThuoc);
		tableKhachHang.addMouseListener(this);

		RoundedPanel panel_2 = new RoundedPanel();
		panel_2.setLayout(null);
		panel_2.setBackground(Color.white);
		panel_2.setBounds(55, 83, 428, 92);
		add(panel_2);

		JLabel lblNewLabel_1 = new JLabel("Tổng tiền thu");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(0, 11, 428, 22);
		panel_2.add(lblNewLabel_1);

		lblTongTienThu = new JLabel("10.000.000đ");
		lblTongTienThu.setHorizontalAlignment(SwingConstants.CENTER);
		lblTongTienThu.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblTongTienThu.setBounds(0, 40, 428, 37);
		panel_2.add(lblTongTienThu);

		RoundedPanel panel_2_1 = new RoundedPanel();
		panel_2_1.setLayout(null);
		panel_2_1.setBackground(Color.white);
		panel_2_1.setBounds(562, 83, 428, 92);
		add(panel_2_1);

		JLabel lblNewLabel_1_1 = new JLabel("Tổng tiền chi");
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_1_1.setBounds(0, 11, 428, 22);
		panel_2_1.add(lblNewLabel_1_1);

		lblTongTienChi = new JLabel("10.000.000đ");
		lblTongTienChi.setHorizontalAlignment(SwingConstants.CENTER);
		lblTongTienChi.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblTongTienChi.setBounds(0, 44, 428, 37);
		panel_2_1.add(lblTongTienChi);

		RoundedPanel panel_2_2 = new RoundedPanel();
		panel_2_2.setLayout(null);
		panel_2_2.setBackground(Color.white);
		panel_2_2.setBounds(1069, 83, 428, 92);
		add(panel_2_2);

		JLabel lblNewLabel_1_2 = new JLabel("Tổng doanh thu");
		lblNewLabel_1_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_1_2.setBounds(10, 11, 408, 22);
		panel_2_2.add(lblNewLabel_1_2);

		lblTongDoanhThu = new JLabel("10.000.000đ");
		lblTongDoanhThu.setHorizontalAlignment(SwingConstants.CENTER);
		lblTongDoanhThu.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblTongDoanhThu.setBounds(20, 44, 398, 37);
		panel_2_2.add(lblTongDoanhThu);

		JPanel panel_1_1 = new JPanel();
		panel_1_1.setPreferredSize(new Dimension(1600, 72));
		panel_1_1.setBackground(new Color(50, 109, 174));
		panel_1_1.setBounds(0, 0, 1600, 72);
		add(panel_1_1);
		panel_1_1.setLayout(null);

		// lb ngay, combobox ngay
		JLabel lbngay = new JLabel("Ngày");
		lbngay.setForeground(Color.WHITE);
		lbngay.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lbngay.setBounds(401, 11, 70, 39);
		panel_1_1.add(lbngay);

		// them du lieu vao combobox ngay
		cbNgay = new JComboBox<Integer>();
		cbNgay.setBounds(470, 11, 70, 39);
		panel_1_1.add(cbNgay);
		cbNgay.setSelectedItem(27);

		// lb thang,combox thang
		JLabel lbthang = new JLabel("Tháng");
		lbthang.setForeground(Color.WHITE);
		lbthang.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lbthang.setBounds(550, 11, 70, 39);
		panel_1_1.add(lbthang);

		// them du lieu vao combobox thang
		cbThang = new JComboBox<Integer>();
		cbThang.setBounds(620, 11, 70, 39);
		panel_1_1.add(cbThang);
		cbThang.setSelectedItem(5);

		// lb nam, combobox nam
		JLabel lbnam = new JLabel("Năm");
		lbnam.setForeground(Color.WHITE);
		lbnam.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lbnam.setBounds(700, 11, 50, 39);
		panel_1_1.add(lbnam);

		// them du lieu vao combobox nam
		cbNam = new JComboBox<Integer>();
		cbNam.setBounds(761, 11, 70, 39);
		panel_1_1.add(cbNam);
		cbNam.setSelectedItem(2021);

		// button thong ke theo ngay
		btnThongKe = new JButton("Thống kê theo ngày");
		btnThongKe.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnThongKe.setBounds(10, 11, 177, 39);
		panel_1_1.add(btnThongKe);
		btnThongKe.setToolTipText("Thống kê thông tin khách hàng và tổng doanh thu trong ngày");

		btnThongKe.setBackground(new Color(191, 247, 249));
		btnThongKe.setForeground(Color.WHITE);
		btnThongKe.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

		// button thong ke theo ngay
		btnThongKeAll = new JButton("Thống kê tất cả");
		btnThongKeAll.setBounds(197, 11, 130, 39);
		panel_1_1.add(btnThongKeAll);
		btnThongKeAll.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnThongKeAll.setToolTipText("Thống kê tất cả khách hàng đã mua thuốc và tổng doanh thu");

		btnThongKeAll.setBackground(new Color(191, 247, 249));
		btnThongKeAll.setForeground(Color.WHITE);
		btnThongKeAll.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
		btnThongKeAll.addActionListener(this);

		// Them su kien
		btnThongKe.addActionListener(this);

		/**
		 * DataBase
		 */
		try {
			ConnectDB.getInstance().connect();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		kh_dao = new KhachHang_DAO();
		thuoc_dao = new Thuoc_DAO();
		try {
			ctHD_dao = new CTHoaDonImpl();
		} catch (RemoteException e) {
			e.printStackTrace();
		}

		txtMaKH = new JTextField(10);// luu ma khach hang
	}

	// xu li su kien
	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
//		if(o.equals(btnTimKiem)) {
//			clearTableThuoc();
//			timKH();
//			txtTongKH.setText(""+dfKhachHang.getRowCount());
//			if (cbTimKiem.getSelectedIndex() == 1) {
//				txtTongSoThuoc.setText(""+thuoc_dao.getTongSoLuongThuocTheoMa(txttimKiem.getText()));
//				txtDoanhThu.setText(""+df.format(ctHD_dao.getTongDoanhTheoMa(txttimKiem.getText())));
//			}
//			else {
//				if (cbTimKiem.getSelectedIndex() == 0) {
//					txtTongSoThuoc.setText(""+thuoc_dao.getTongSoLuongThuocTheoTen(txttimKiem.getText()));
//					txtDoanhThu.setText(""+df.format(ctHD_dao.getTongDoanhTheoTen(txttimKiem.getText())));
//				}
//				else {
//					txtTongSoThuoc.setText(""+thuoc_dao.getTongSoLuongThuocTheoSDT(txttimKiem.getText()));
//					txtDoanhThu.setText(""+df.format(ctHD_dao.getTongDoanhTheoSDT(txttimKiem.getText())));
//				}
//				
//			}
//			
//		}
		// thong ke theo ngay
		if (o.equals(btnThongKe)) {
			clearTableThuoc();
			timKHTheoNgay();
			txtTongKH.setText("" + dfKhachHang.getRowCount());
			txtTongSoThuoc.setText("" + tongThuocTheoNgay());

			try {
				lblTongDoanhThu.setText("" + df.format(tongDoanhThuTheoNgay()));
			} catch (RemoteException e1) {
				e1.printStackTrace();
			}

		}
		if (o.equals(btnThongKeAll)) {
			clearTableThuoc();
			thongKeTatCaKH();
			txtTongKH.setText("" + dfKhachHang.getRowCount());
			txtTongSoThuoc.setText("" + tongThuocBan());
			try {
				lblTongTienThu.setText("" + df.format(tongDoanhThu()));
			} catch (RemoteException e1) {
				e1.printStackTrace();
			}

		}
	}

	// Thong ke khach hang theo ngay
	private void timKHTheoNgay() {
		ArrayList<KhachHang> dskh;
		dskh = kh_dao.getKhachHangDaMuaThuocTheoNgay((int) cbNgay.getSelectedItem(), (int) cbThang.getSelectedItem(),
				(int) cbNam.getSelectedItem());
		clearTable();// xóa bảng
		if (!dskh.isEmpty()) {
			for (KhachHang kh : dskh) {
				dfKhachHang.addRow(new Object[] { kh.getMaKH(), kh.getHoTen(), kh.getNgaySinh(),
						strGioiTinh(kh.isGioiTinh()), kh.getDiaChi(), kh.getSDT() });
			}
		} else {
			JOptionPane.showMessageDialog(this, "Không có khách hàng nào theo thông tin tìm");
			return;
		}
	}

	// Thong ke tat ca khach hang da mua thuoc
	private void thongKeTatCaKH() {
		ArrayList<KhachHang> dskh;
		dskh = kh_dao.getAllKhachHang();
		clearTable();// xóa bảng
		for (KhachHang kh : dskh) {
			dfKhachHang.addRow(new Object[] { kh.getMaKH(), kh.getHoTen(), kh.getNgaySinh(),
					strGioiTinh(kh.isGioiTinh()), kh.getDiaChi(), kh.getSDT() });
		}
	}

	// Xoa bang khach hang
	private void clearTable() {
		while (tableKhachHang.getRowCount() > 0) {
			dfKhachHang.removeRow(0);
		}
	}

	// Xoa bang thuoc
	private void clearTableThuoc() {
		while (tableThuoc.getRowCount() > 0) {
			dfThuoc.removeRow(0);
		}
	}

	private String strGioiTinh(Boolean nv) {
		if (nv) {
			return "Nam";
		}
		return "Nữ";
	}

	// Tong so thuoc da ban
	private int tongThuocBan() {
		int i = 0;
		i = thuoc_dao.getTongSoLuongThuoc();
		return i;
	}

	// So thuoc da ban theo ngay
	private int tongThuocTheoNgay() {
		int i = 0;
		i = thuoc_dao.getTongSoLuongThuocTheoNgay((int) cbNgay.getSelectedItem(), (int) cbThang.getSelectedItem(),
				(int) cbNam.getSelectedItem());
		return i;
	}

	// Thong ke tong doanh thu
	private double tongDoanhThu() throws RemoteException {
		double i = 0;
		i = ctHD_dao.getTongDoanhThuThuoc();
		return i;
	}

//	 Thong ke tong doanh thu theo ngay
	private double tongDoanhThuTheoNgay() throws RemoteException {
		double i = 0;
		i = ctHD_dao.getTongDoanhThuThuocTheoNgay((int) cbNgay.getSelectedItem(), (int) cbThang.getSelectedItem(),
				(int) cbNam.getSelectedItem());
		return i;
	}

	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		if (o.equals(tableKhachHang)) {
			int row = tableKhachHang.getSelectedRow();
			txtMaKH.setText(dfKhachHang.getValueAt(row, 0).toString());
			ArrayList<Thuoc> dsthuoc;
			dsthuoc = thuoc_dao.getThuocKhachHangDaMua(txtMaKH.getText());
			clearTableThuoc();// xóa bảng
			for (Thuoc thuoc : dsthuoc) {
				dfThuoc.addRow(new Object[] { thuoc.getMaThuoc(), thuoc.getTenThuoc(), df.format(thuoc.getDonGia()),
						thuoc.getSLTon(), thuoc.getNcc().getMaNCC(), thuoc.getLoaiThuoc().getMaLoai(),
						thuoc.getNuocSX().getIdNuoc() });
			}
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}
}
