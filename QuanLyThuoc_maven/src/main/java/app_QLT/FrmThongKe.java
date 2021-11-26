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
import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import com.toedter.calendar.JYearChooser;

import connectDB.ConnectDB;
import dao.CTHoaDon_DAO;
import dao.KhachHangDao;
import dao.KhachHang_DAO;
import dao.ThuocDao;
import dao.Thuoc_DAO;
import dao.impl.CTHoaDonImpl;
import dao.impl.KhachHangDaoImpl;
import dao.impl.ThuocDaoImpl;
import entity.KhachHang;
import entity.Thuoc;
import chucNang.ChucNang;
import chucNang.RoundedPanel;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Cursor;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.DefaultComboBoxModel;

public class FrmThongKe extends JPanel implements ActionListener, MouseListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JComboBox<Integer> cboNgay, cboThang, cboNam;
	private DefaultTableModel modelKH, modelThuoc;
	private JTable tblKhachHang, tblThuoc;
	private JTextField txtTongKH, txtTongSoThuoc;
	private JButton btnThongKe;
	private KhachHangDao kh_dao;
	private ThuocDao thuoc_dao;
	private CTHoaDon_DAO ctHD_dao;
	private DecimalFormat df = new DecimalFormat("#,###.0");
	private JLabel lblSLThuoc;
	private JLabel lblTongDoanhThu;
	// Format tiền theo VND
	private NumberFormat vnFormat = NumberFormat.getInstance(new Locale("vi", "VN"));
	private JComboBox<Integer> cboLoc;

	public FrmThongKe() {
		setLayout(null);
		setSize(1600, 933);
		setBackground(Color.WHITE);

		// bang thong tin khach hang

		String[] colsKH = { "Mã khách hàng", "Tên khách hàng", "Ngày sinh", "Giới tính", "Địa chỉ", "Số điện thoại" };
		new DefaultTableModel(colsKH, 0);
		tblKhachHang = new JTable(
				new DefaultTableModel(new Object[][] { { null, null, null, null, null, null, null, null, null },
						{ null, null, null, null, null, null, null, null, null },
						{ null, null, null, null, null, null, null, null, null } }, colsKH)) {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int col) {
				return false;
			}
		};
		tblKhachHang.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblKhachHang.setRowMargin(5);
		tblKhachHang.setRowHeight(30);
		tblKhachHang.setFont(new Font("Tahoma", Font.PLAIN, 24));
		tblKhachHang.setToolTipText("Bảng thông tin của các khách hàng");
		JTableHeader headerTableKH = tblKhachHang.getTableHeader();
		headerTableKH.setFont(new Font("Tahoma", Font.PLAIN, 24));
		headerTableKH.setBackground(new Color(248, 198, 153));
		JScrollPane thanhCuonKH = new JScrollPane(tblKhachHang);
		thanhCuonKH.setBorder(new TitledBorder(null, "Th\u00F4ng tin kh\u00E1ch h\u00E0ng", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		thanhCuonKH.setBounds(10, 231, 1575, 293);
		thanhCuonKH.setFont(new Font("Tahoma", Font.PLAIN, 20));
		thanhCuonKH.setToolTipText("Bảng thông tin của các khách hàng");

		add(thanhCuonKH);
		modelKH = (DefaultTableModel) tblKhachHang.getModel();

		// bang thong tin thuoc ma khach hang da mua
		String[] colsthuoc = { "Mã thuốc", "Tên thuốc", "Đơn giá", "Số lượng", "Tên nhà cung cấp", "Loại thuốc",
				"Nước sản xuất" };
		new DefaultTableModel(colsthuoc, 0);
		tblThuoc = new JTable(
				new DefaultTableModel(new Object[][] { { null, null, null, null, null, null, null, null, null },
						{ null, null, null, null, null, null, null, null, null },
						{ null, null, null, null, null, null, null, null, null } }, colsthuoc)) {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int col) {
				return false;
			}
		};
		tblThuoc.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblThuoc.setRowMargin(5);
		tblThuoc.setRowHeight(30);
		tblThuoc.setFont(new Font("Tahoma", Font.PLAIN, 24));
		tblThuoc.setToolTipText("Bảng thông tin của thuốc");
		JTableHeader headerTableThuoc = tblThuoc.getTableHeader();
		headerTableThuoc.setFont(new Font("Tahoma", Font.PLAIN, 24));
		headerTableThuoc.setBackground(new Color(248, 198, 153));
		JScrollPane thanhCuonThuoc = new JScrollPane(tblThuoc);
		thanhCuonThuoc.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
				"Th\u00F4ng tin thu\u1ED1c kh\u00E1ch h\u00E0ng mua", TitledBorder.LEADING, TitledBorder.TOP, null,
				new Color(0, 0, 0)));
		thanhCuonThuoc.setBounds(10, 583, 1575, 339);
		thanhCuonThuoc.setFont(new Font("Tahoma", Font.PLAIN, 20));
		thanhCuonThuoc.setToolTipText("Bảng thông tin của thuốc");

		add(thanhCuonThuoc);
		modelThuoc = (DefaultTableModel) tblThuoc.getModel();

		// Căn chữ của cột sang phải
		int[] listCanPhaiTblThuoc = { 2, 3 };
		ChucNang.setRightAlignmentTable(listCanPhaiTblThuoc, tblThuoc);

		// phan tong so luong khach hang
		JLabel lbtongkh = new JLabel("Tổng số khách hàng");
		lbtongkh.setHorizontalAlignment(SwingConstants.RIGHT);
		lbtongkh.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lbtongkh.setBounds(1280, 190, 190, 30);
		add(lbtongkh);
		txtTongKH = new JTextField();
		txtTongKH.setEditable(false);
		txtTongKH.setHorizontalAlignment(SwingConstants.CENTER);
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
		txtTongSoThuoc.setHorizontalAlignment(SwingConstants.CENTER);
		txtTongSoThuoc.setEditable(false);
		txtTongSoThuoc.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtTongSoThuoc.setBounds(1480, 535, 105, 39);
		add(txtTongSoThuoc);
		tblKhachHang.addMouseListener(this);

		RoundedPanel panel_2 = new RoundedPanel();
		panel_2.setLayout(null);
		panel_2.setBackground(Color.white);
		panel_2.setBounds(275, 83, 428, 92);
		add(panel_2);

		JLabel lblNewLabel_1 = new JLabel("Số lượng thuốc đã bán");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(0, 11, 428, 22);
		panel_2.add(lblNewLabel_1);

		lblSLThuoc = new JLabel("0");
		lblSLThuoc.setHorizontalAlignment(SwingConstants.CENTER);
		lblSLThuoc.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblSLThuoc.setBounds(0, 40, 428, 37);
		panel_2.add(lblSLThuoc);

		RoundedPanel panel_2_2 = new RoundedPanel();
		panel_2_2.setLayout(null);
		panel_2_2.setBackground(Color.white);
		panel_2_2.setBounds(847, 83, 428, 92);
		add(panel_2_2);

		JLabel lblNewLabel_1_2 = new JLabel("Doanh thu");
		lblNewLabel_1_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_1_2.setBounds(10, 11, 408, 22);
		panel_2_2.add(lblNewLabel_1_2);

		lblTongDoanhThu = new JLabel("0đ");
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
		lbngay.setHorizontalAlignment(SwingConstants.RIGHT);
		lbngay.setForeground(Color.WHITE);
		lbngay.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lbngay.setBounds(549, 9, 70, 50);
		panel_1_1.add(lbngay);

		// them du lieu vao combobox ngay
		cboNgay = new JComboBox<Integer>();
		cboNgay.setFont(new Font("Tahoma", Font.PLAIN, 20));
		cboNgay.setBounds(629, 9, 70, 50);
		panel_1_1.add(cboNgay);
		cboNgay.setSelectedItem(27);

		// lb thang,combox thang
		JLabel lbthang = new JLabel("Tháng");
		lbthang.setHorizontalAlignment(SwingConstants.RIGHT);
		lbthang.setForeground(Color.WHITE);
		lbthang.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lbthang.setBounds(709, 9, 70, 50);
		panel_1_1.add(lbthang);

		// them du lieu vao combobox thang
		cboThang = new JComboBox<Integer>();
		cboThang.setFont(new Font("Tahoma", Font.PLAIN, 20));
		cboThang.setBounds(789, 9, 70, 50);
		panel_1_1.add(cboThang);
		cboThang.setSelectedItem(5);

		// lb nam, combobox nam
		JLabel lbnam = new JLabel("Năm");
		lbnam.setHorizontalAlignment(SwingConstants.RIGHT);
		lbnam.setForeground(Color.WHITE);
		lbnam.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lbnam.setBounds(869, 9, 50, 50);
		panel_1_1.add(lbnam);

		// them du lieu vao combobox nam
		cboNam = new JComboBox<Integer>();
		cboNam.setFont(new Font("Tahoma", Font.PLAIN, 20));
		cboNam.setBounds(929, 9, 70, 50);
		panel_1_1.add(cboNam);
		cboNam.setSelectedItem(2021);

		// button thong ke theo ngay
		btnThongKe = new JButton("Thống kê");
		btnThongKe.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnThongKe.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnThongKe.setBounds(10, 9, 177, 50);
		panel_1_1.add(btnThongKe);
		btnThongKe.setToolTipText("Thống kê thông tin khách hàng và tổng doanh thu trong ngày");

		btnThongKe.setBackground(new Color(20, 140, 255));
		btnThongKe.setForeground(Color.WHITE);
		btnThongKe.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

		cboLoc = new JComboBox<Integer>();
		cboLoc.setFont(new Font("Tahoma", Font.PLAIN, 20));
		cboLoc.setModel(new DefaultComboBoxModel(new String[] { "Theo ngày", "Theo tháng", "Theo năm" }));
		cboLoc.setBounds(1420, 9, 142, 50);
		panel_1_1.add(cboLoc);

		JLabel lblLc = new JLabel("Lọc:");
		lblLc.setHorizontalAlignment(SwingConstants.RIGHT);
		lblLc.setForeground(Color.WHITE);
		lblLc.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblLc.setBounds(1340, 9, 70, 50);
		panel_1_1.add(lblLc);

		for (int i = 1; i <= 31; i++)
			cboNgay.addItem(i);

		for (int j = 1; j <= 12; j++) {

			cboThang.addItem(j);
		}
		for (int k = LocalDate.now().getYear(); k >= LocalDate.now().getYear() - 10; k--) {

			cboNam.addItem(k);
		}
		LocalDate hientai = LocalDate.now();

		cboNgay.setSelectedItem(hientai.getDayOfMonth());
		cboThang.setSelectedItem(hientai.getMonthValue());
		cboNam.setSelectedItem(hientai.getYear());

		// Them su kien
		btnThongKe.addActionListener(this);

		try {
			kh_dao = new KhachHangDaoImpl();
			ctHD_dao = new CTHoaDonImpl();
			thuoc_dao = new ThuocDaoImpl();
		} catch (RemoteException e) {
			e.printStackTrace();
		}

		ChucNang.addNullDataTable(modelKH);
		ChucNang.addNullDataTable(modelThuoc);

		// Sự kiện cho combobox lọc
		cboLoc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switch (cboLoc.getSelectedIndex()) {
				case 0:
					cboNgay.setEnabled(true);
					cboThang.setEnabled(true);
					break;
				case 1:
					cboNgay.setEnabled(false);
					cboThang.setEnabled(true);
					break;
				case 2:
					cboNgay.setEnabled(false);
					cboThang.setEnabled(false);
					break;
				}
			}
		});

	}

	// xu li su kien
	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		try {
			int ngay = 0;
			int thang = 0;
			int nam = 0;
			// thong ke theo ngay
			if (o.equals(btnThongKe)) {
				List<KhachHang> dskh = new ArrayList<KhachHang>();
				clearTableThuoc();
				switch (cboLoc.getSelectedIndex()) {
				case 0:
					ngay = (int) cboNgay.getSelectedItem();
					thang = (int) cboThang.getSelectedItem();
					nam = (int) cboNam.getSelectedItem();

					dskh = kh_dao.getKhachHangDaMuaThuocTheoNgay(ngay, thang, nam);
					txtTongSoThuoc.setText("" + thuoc_dao.getTongLoaiThuocTheoNgay(ngay, thang, nam));
					lblTongDoanhThu
							.setText(vnFormat.format(ctHD_dao.getTongDoanhThuThuocTheoNgay(ngay, thang, nam)) + "đ");
					lblSLThuoc.setText("" + thuoc_dao.getTongSoLuongThuocTheoNgay(ngay, thang, nam));
					break;
				case 1:
					thang = (int) cboThang.getSelectedItem();
					nam = (int) cboNam.getSelectedItem();

					dskh = kh_dao.getKhachHangDaMuaThuocTheoThang(thang, nam);
					txtTongSoThuoc.setText("" + thuoc_dao.getTongLoaiThuocTheoThang(thang, nam));
					lblTongDoanhThu.setText(vnFormat.format(ctHD_dao.getTongDoanhThuThuocTheoThang(thang, nam)) + "đ");
					lblSLThuoc.setText("" + thuoc_dao.getTongLoaiThuocTheoThang(thang, nam));
					break;

				case 2:
					nam = (int) cboNam.getSelectedItem();

					dskh = kh_dao.getKhachHangDaMuaThuocTheoNam(nam);
					txtTongSoThuoc.setText("" + thuoc_dao.getTongLoaiThuocTheoNam(nam));
					lblTongDoanhThu.setText(vnFormat.format(ctHD_dao.getTongDoanhThuThuocTheoNam(nam)) + "đ");
					lblSLThuoc.setText("" + thuoc_dao.getTongLoaiThuocTheoNam(nam));
					break;

				}

				timKHTheoNgay(dskh);
				txtTongKH.setText("" + modelKH.getRowCount());
				ChucNang.addNullDataTable(modelThuoc);
				ChucNang.addNullDataTable(modelKH);
			}

		} catch (RemoteException e1) {
			e1.printStackTrace();
		}

	}

	// Thong ke khach hang theo ngay
	private void timKHTheoNgay(List<KhachHang> dskh) throws RemoteException {
		clearTable();// xóa bảng
		if (!dskh.isEmpty()) {
			for (KhachHang kh : dskh) {
				modelKH.addRow(new Object[] { kh.getMaKH(), kh.getHoTen(), kh.getNgaySinh(),
						strGioiTinh(kh.isGioiTinh()), kh.getDiaChi(), kh.getSDT() });
			}
		} else {
			JOptionPane.showMessageDialog(this, "Không có khách hàng nào theo thông tin tìm");
			return;
		}
	}

	// Xoa bang khach hang
	private void clearTable() {
		while (tblKhachHang.getRowCount() > 0) {
			modelKH.removeRow(0);
		}
	}

	// Xoa bang thuoc
	private void clearTableThuoc() {
		while (tblThuoc.getRowCount() > 0) {
			modelThuoc.removeRow(0);
		}
	}

	private String strGioiTinh(Boolean nv) {
		if (nv) {
			return "Nam";
		}
		return "Nữ";
	}

	public void mouseClicked(MouseEvent e) {
		Object o = e.getSource();
		if (o.equals(tblKhachHang)) {
			int row = tblKhachHang.getSelectedRow();
			Object maKH = modelKH.getValueAt(row, 0);
			List<Thuoc> dsthuoc = new ArrayList<Thuoc>();
			try {
				if (maKH != null) {
					dsthuoc = thuoc_dao.getThuocKhachHangDaMua(maKH.toString());
				}

			} catch (RemoteException e1) {
				e1.printStackTrace();
			}
			clearTableThuoc();// xóa bảng
			for (Thuoc thuoc : dsthuoc) {
				modelThuoc.addRow(new Object[] { thuoc.getMaThuoc(), thuoc.getTenThuoc(),
						vnFormat.format(thuoc.getDonGia()), thuoc.getSLTon(), thuoc.getNcc().getMaNCC(),
						thuoc.getLoaiThuoc().getMaLoai(), thuoc.getNuocSX().getIdNuoc() });
			}
			ChucNang.addNullDataTable(modelThuoc);
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}
}
