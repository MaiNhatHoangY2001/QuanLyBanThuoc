package app_QLT;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Date;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Properties;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

import org.jdatepicker.DateModel;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.SqlDateModel;

import connectDB.ConnectDB;
import dao.LoaiThuoc_DAO;
import dao.NhaCungCap_Dao;
import dao.NuocSX_DAO;
import dao.TThuocT_Dao;
import entity.DateLabelFormatter;
import entity.LoaiThuoc;
import entity.NhaCungCap;
import entity.NuocSX;
import entity.Regex1;
import entity.Thuoc;

public class FrmThuoc extends JPanel implements ActionListener, MouseListener, TreeSelectionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Các thuộc tính của Phần Nhập thông tin Thuốc
	 */
	private JTextField txtMaThuoc;
	private JTextField txtTenThuoc;
	private JTextField txtDonGia;
	private JTextField txtSLTon;
	private JComboBox<String> cboLoai;
	private JComboBox<String> cboNuoc;
	private SqlDateModel modelNgaySX;
	private Properties p;
	private JDatePanelImpl datePanel;
	private JDatePickerImpl datePicker;
	private SqlDateModel modelNgayHSD;
	private Properties h;
	private JDatePanelImpl datePanelH;
	private JDatePickerImpl datePickerH;
	private JButton btnThemNuoc;
	private JButton btnThemLoai;

	/**
	 * Các thuộc tính của các nút
	 */
	private JButton btnThem;
	private JButton btnXoa;
	private JButton btnSua;
	private JButton btnXoaRong;

	/**
	 * các thuộc tính của bảng ListView
	 */
	private JTable tableThuoc;
	private DefaultTableModel modelThuoc;

	/**
	 * Các thuộc tính trình trạng
	 */
	private JRadioButton radAll;
	private JRadioButton radConHan;
	private JRadioButton radHetHan;

	/**
	 * Các thuôc tính tìm kiếm
	 */
	private JComboBox<String> cboLoaiTimKiem;
	private JTextField txtThongTinTimKiem;
	private JButton btnTim;
	private JButton btnThemNhaCungCap;
	private JButton btnXoaPhong;
	
	/**
	 * cây thư mục
	 */
	private DefaultMutableTreeNode root;
	private JTree tree;
	private ArrayList<DefaultMutableTreeNode> dsNCC;
	
	
	/**
	 * thuộc tính
	 */
	private LoaiThuoc_DAO dao_loai;
	private NhaCungCap_Dao dao_ncc;
	private TThuocT_Dao dao_thuoc;
	private Regex1 regex;
	private NuocSX_DAO dao_Nuoc;
	private Object maNCC;
	private DecimalFormat df = new DecimalFormat("#");
	private JButton btnReset;

	public FrmThuoc() {
		setLayout(null);

		JPanel pTrai = new JPanel();
		pTrai.setLayout(null);

		/**
		 * Phần tree nhà cung cấp
		 */
			// Danh sách nhà cung cấp
		root = new DefaultMutableTreeNode("Nhà cung cấp");
		tree = new JTree(root);
		JScrollPane thanhCuonTree = new JScrollPane(tree);
		thanhCuonTree.setBounds(10, 10, 205, 200);
		pTrai.add(thanhCuonTree);

			// Nút thêm nhà cung cấp
		btnThemNhaCungCap = new JButton("Thêm NCC");
		btnThemNhaCungCap.setBounds(10, 215, 205, 30);
		pTrai.add(btnThemNhaCungCap);

		JPanel pBang = new JPanel(new GridLayout(1, 1));
		pBang.setBorder(BorderFactory.createTitledBorder("Thông tin"));
		/**
		 * Phần listView (Bảng thông tin)
		 */
		String header[] = { "Mã Thuốc", "Tên thuôc","Đơn giá", "NSX", "HSD", "SLT", "Mã NCC", "Mã Loại", "id Nước"};
		modelThuoc = new DefaultTableModel(header, 0);
		tableThuoc = new JTable(modelThuoc);
		JScrollPane thanhCuon = new JScrollPane(tableThuoc);
		thanhCuon.setBounds(10, 10, 525, 200);
		pBang.add(thanhCuon);

		JPanel pinput = new JPanel();
		pinput.setLayout(null);
		/**
		 * phần nhập thông tin thuốc
		 */
			// Phần JLable
		JLabel lblMaThuoc = new JLabel("Mã thuốc:");
		lblMaThuoc.setBounds(10, 20, 100, 30);
		pinput.add(lblMaThuoc);

		JLabel lblTenThuoc = new JLabel("Tên thuốc:");
		lblTenThuoc.setBounds(10, 55, 100, 30);
		pinput.add(lblTenThuoc);

		JLabel lblNgaySX = new JLabel("Ngày sản xuất:");
		lblNgaySX.setBounds(10, 90, 100, 30);
		pinput.add(lblNgaySX);

		JLabel lblHangSD = new JLabel("Hạn sử dụng:");
		lblHangSD.setBounds(10, 125, 100, 30);
		pinput.add(lblHangSD);

		JLabel lblDonGia = new JLabel("Đơn giá:");
		lblDonGia.setBounds(10, 160, 100, 30);
		pinput.add(lblDonGia);

		JLabel lblSLTon = new JLabel("Số lượng tồn:");
		lblSLTon.setBounds(10, 195, 100, 30);
		pinput.add(lblSLTon);

		JLabel lblLoai = new JLabel("Loại:");
		lblLoai.setBounds(10, 230, 100, 30);
		pinput.add(lblLoai);
		
		JLabel lblNuoc = new JLabel("Nước:");
		lblNuoc.setBounds(10, 265, 100, 30);
		pinput.add(lblNuoc);

		// Phần các JTextField + JComboBox
		txtMaThuoc = new JTextField();
		txtMaThuoc.setBounds(100, 20, 290, 30);
		pinput.add(txtMaThuoc);

		txtTenThuoc = new JTextField();
		txtTenThuoc.setBounds(100, 55, 290, 30);
		pinput.add(txtTenThuoc);

		modelNgaySX = new SqlDateModel();
		p = new Properties();
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");
		datePanel = new JDatePanelImpl(modelNgaySX, p);
		datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
		datePicker.setBounds(100, 90, 290, 30);
		LocalDate now = LocalDate.now();
		int ngay = now.getDayOfMonth();
		int thang = now.getMonthValue();
		int nam = now.getYear();
		modelNgaySX.setDate(nam, thang, ngay);
		modelNgaySX.setSelected(true);
		pinput.add(datePicker);

		modelNgayHSD = new SqlDateModel();
		h = new Properties();
		h.put("text.today", "Today");
		h.put("text.month", "Month");
		h.put("text.year", "Year");
		datePanelH = new JDatePanelImpl(modelNgayHSD, h);
		datePickerH = new JDatePickerImpl(datePanelH, new DateLabelFormatter());
		datePickerH.setBounds(100, 125, 290, 30);
		LocalDate now1 = LocalDate.now();
		int ngay1 = now1.getDayOfMonth();
		int thang1 = now1.getMonthValue();
		int nam1 = now1.getYear();
		modelNgayHSD.setDate(nam1, thang1, ngay1);
		modelNgayHSD.setSelected(true);
		pinput.add(datePickerH);

		txtDonGia = new JTextField();
		txtDonGia.setBounds(100, 160, 290, 30);
		pinput.add(txtDonGia);

		txtSLTon = new JTextField();
		txtSLTon.setBounds(100, 195, 290, 30);
		pinput.add(txtSLTon);

		cboLoai = new JComboBox<String>();
		cboLoai.setBounds(100, 230, 170, 30);
		pinput.add(cboLoai);
		
		btnThemLoai = new JButton("Thêm loại");
		btnThemLoai.setBounds(280, 230, 110, 30);
		pinput.add(btnThemLoai);
		
		cboNuoc = new JComboBox<String>();
		cboNuoc.setBounds(100, 265, 170, 30);
		pinput.add(cboNuoc);
		
		btnThemNuoc = new JButton("Thêm Nước");
		btnThemNuoc.setBounds(280, 265, 110, 30);
		pinput.add(btnThemNuoc);
		
		btnReset = new JButton("Reset");
		btnReset.setBounds(170, 300, 110, 30);
		pinput.add(btnReset);

		/**
		 * Phần các Button
		 */
		btnThem = new JButton("Thêm thuốc");
		btnThem.setBounds(160, 350, 105, 35);
		pinput.add(btnThem);

		btnXoa = new JButton("Xóa");
		btnXoa.setBounds(280, 350, 105, 35);
		pinput.add(btnXoa);

		btnXoaRong = new JButton("Xóa Rỗng");
		btnXoaRong.setBounds(400, 350, 105, 35);
		pinput.add(btnXoaRong);

		btnSua = new JButton("Sữa");
		btnSua.setBounds(520, 350, 105, 35);
		pinput.add(btnSua);
		
		
		
		/**
		 * Phần tìm kiếm
		 */
		// rad tình trạng thuốc 
		radAll = new JRadioButton("Tất cả");
		radAll.setSelected(true);
		radAll.setBounds(480, 35, 200, 30);
		pinput.add(radAll);
		radConHan = new JRadioButton("Còn hạn sử dụng");
		radConHan.setBounds(480, 65, 200, 30);
		pinput.add(radConHan);
		radHetHan = new JRadioButton("Hết hạn sử dụng");
		radHetHan.setBounds(480, 95, 200, 30);
		pinput.add(radHetHan);
		ButtonGroup group = new ButtonGroup();
		group.add(radAll);
		group.add(radConHan);
		group.add(radHetHan);
		// Phần label của tìm kiếm
		JLabel lblTimLoai = new JLabel("Chọn loại Tìm kiếm:");
		lblTimLoai.setBounds(440, 190, 200, 30);
		pinput.add(lblTimLoai);
		
		JLabel lblNhapTim = new JLabel("Nhập thông tin cần tìm:");
		lblNhapTim.setBounds(440, 240, 200, 30);
		pinput.add(lblNhapTim);
		
		
		// txt + cbb của tìm kiếm
		String [] strLoaiTim = {"Tìm kiếm theo mã Thuốc", "Tìm kiếm theo tên thuốc"};
		cboLoaiTimKiem = new JComboBox<String>(strLoaiTim);
		cboLoaiTimKiem.setBounds(560, 190, 180, 30);
		
		pinput.add(cboLoaiTimKiem);
		txtThongTinTimKiem = new JTextField();
		txtThongTinTimKiem.setBounds(440, 270, 200, 30);
		pinput.add(txtThongTinTimKiem);
		
		btnTim = new JButton("Tìm");
		btnTim.setBounds(650, 270, 90, 30);
		pinput.add(btnTim);

		/**
		 * thêm khung border title cho các phần
		 */
		// phần Nhập thông tin Thuốc
		JPanel pThuoc = new JPanel();
		pThuoc.setBorder(BorderFactory.createTitledBorder("Nhập thông tin thuốc"));
		pThuoc.setBounds(0, 0, 400, 340);
		pinput.add(pThuoc);
		
		// tình trạng thuốc
		JPanel ptinhTrang = new JPanel();
		ptinhTrang.setBorder(BorderFactory.createTitledBorder("Tình trạng thuốc"));
		ptinhTrang.setBounds(420, 20, 350, 120);
		pinput.add(ptinhTrang);
		
		//  tìm theo mã thuốc
		JPanel pNhapTim = new JPanel();
		pNhapTim.setBorder(BorderFactory.createTitledBorder("tìm theo mã thuốc"));
		pNhapTim.setBounds(420, 150, 350, 170);
		pinput.add(pNhapTim);
		
		// phần tìm kiếm thuốc
		JPanel pTim = new JPanel();
		pTim.setBorder(BorderFactory.createTitledBorder("Tìm thuốc"));
		pTim.setBounds(410, 0, 370, 340);
		pinput.add(pTim);
			
		

		JSplitPane TraiPhai = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
		TraiPhai.setDividerLocation(225);
		TraiPhai.setLeftComponent(pTrai);
		TraiPhai.setRightComponent(pBang);
		
		
		JSplitPane TrenDuoi = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		TrenDuoi.setDividerLocation(255);
		TrenDuoi.setTopComponent(TraiPhai);
		TrenDuoi.setBottomComponent(pinput);
		TrenDuoi.setBounds(0, 0, 800, 800);

		add(TrenDuoi, BorderLayout.CENTER);
		
		btnThemNhaCungCap.addActionListener(this);
		btnThem.addActionListener(this);
		tree.addTreeSelectionListener(this);
		tableThuoc.addMouseListener(this);
		btnXoa.addActionListener(this);
		btnXoaRong.addActionListener(this);
		btnSua.addActionListener(this);
		radAll.addActionListener(this);
		radConHan.addActionListener(this);
		radHetHan.addActionListener(this);
		btnThemLoai.addActionListener(this);
		btnThemNuoc.addActionListener(this);
		btnReset.addActionListener(this);
		
		try {
			ConnectDB.getInstance().connect();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		dao_ncc = new NhaCungCap_Dao();
		dao_loai = new LoaiThuoc_DAO();
		dao_Nuoc = new NuocSX_DAO();
		regex = new Regex1();
		
		ArrayList<NhaCungCap> dsNCC = dao_ncc.getalltbNhaCungCap();
		themNCCVaoTree(dsNCC);
		themLoaiVaoCboLoai();
		themLoaiVaocboNuoc();
	}

	private void themLoaiVaocboNuoc() {
		// TODO Auto-generated method stub
		cboNuoc.removeAllItems();
		ArrayList<NuocSX> ds = dao_Nuoc.getalltbNuoc();
		for (NuocSX nuoc : ds) {
			cboNuoc.addItem(nuoc.getIdNuoc() +  ": " + nuoc.getTenNuoc());
		}
	}

	public void themLoaiVaoCboLoai() {
		// TODO Auto-generated method stub
		cboLoai.removeAllItems();
		ArrayList<LoaiThuoc> dsLoai = dao_loai.getallLoaiThuoc();
		for (LoaiThuoc l : dsLoai) {
			cboLoai.addItem(l.getMaLoai() + ": " + l.getTenLoai());
		}
	}

	private void themNCCVaoTree(ArrayList<NhaCungCap> dsNCC) {
		// TODO Auto-generated method stub
		root.removeAllChildren();
		for (NhaCungCap ncc : dsNCC) {
			root.add(new DefaultMutableTreeNode(ncc.getMaNCC()));
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		int row = tableThuoc.getSelectedRow();
		txtMaThuoc.setText(tableThuoc.getValueAt(row, 0).toString());
		txtMaThuoc.setEditable(false);
		txtTenThuoc.setText(tableThuoc.getValueAt(row, 1).toString());
		txtDonGia.setText(tableThuoc.getValueAt(row, 2).toString());
		modelNgaySX.setValue(Date.valueOf(tableThuoc.getValueAt(row, 3).toString()));
		modelNgayHSD.setValue(Date.valueOf(tableThuoc.getValueAt(row, 4).toString()));
		txtSLTon.setText(tableThuoc.getValueAt(row, 5).toString());
		cboLoai.setSelectedItem(tableThuoc.getValueAt(row, 7).toString());
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

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		if (o.equals(btnThemNhaCungCap)) {
			frmThemNCC frm = new frmThemNCC();
			frm.setVisible(true);
		} if (o.equals(btnThem)) {
			themThuoc();
		} else if (o.equals(btnXoa)) {
			xoaThuoc();
		} else if (o.equals(btnXoaRong)) {
			xoaHetText();
		} else if (o.equals(btnSua)) {
			sua();
			xoaHetText();
		} else if (o.equals(radAll)) {
			if (maNCC != null) {
				ArrayList<Thuoc> dsArrayList = dao_thuoc.getalltbThuoc();
				loadHetDulieuVaoList(dsArrayList);
			}
		} else if (o.equals(radConHan)) {
			loadThuocConHanVaoList();
		} else if (o.equals(radHetHan)) {
			loadThuocHetHanVaoList();
		} else if (o.equals(btnThemLoai)) {
			frmThemLoai frm = new frmThemLoai();
			frm.setVisible(true);
		} else if (o.equals(btnThemNuoc)) {
			FrmNuoc frm = new FrmNuoc();
			frm.setVisible(true);
		} else if (o.equals(btnTim)) {
			tim();
		} else if (o.equals(btnReset)) {
			themLoaiVaoCboLoai();
			themLoaiVaocboNuoc();
			ArrayList<NhaCungCap> ds = dao_ncc.getalltbNhaCungCap();
			themNCCVaoTree(ds);
		}
	}

	private void tim() {
		// TODO Auto-generated method stub
		ArrayList<Thuoc> ds;
		if (cboLoaiTimKiem.getSelectedIndex() == 0) {
			ds = dao_thuoc.getThuocTheoMaThuoc(txtThongTinTimKiem.getText());
		} else {
			ds = dao_thuoc.getThuocTheoTenThuoc(txtThongTinTimKiem.getText());
		}
		
		if (ds.isEmpty()) {
			loadHetDulieuVaoList(ds);
		}
	}

	private void loadThuocHetHanVaoList() {
		// TODO Auto-generated method stub
		if (maNCC != null) {
			if (maNCC.toString().equals("Nhà cung cấp")) { 
				dao_thuoc = new TThuocT_Dao();
				ArrayList<Thuoc> dsThuoc = dao_thuoc.getalltbThuoc();
				ArrayList<Thuoc> tam = new ArrayList<Thuoc>();
				for (Thuoc thuoc : dsThuoc) {
					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
					LocalDate date = LocalDate.parse(thuoc.getHanSuDung().toString(), formatter);
					LocalDate now = LocalDate.now();
					if (now.isAfter(date)) {
						tam.add(thuoc);
					}
				}
				loadHetDulieuVaoList(tam);
			} else {
				dao_thuoc = new TThuocT_Dao();
				ArrayList<Thuoc> dsThuoc = dao_thuoc.getThuocTheoMaNCC(maNCC.toString());
				ArrayList<Thuoc> tam = new ArrayList<Thuoc>();
				for (Thuoc thuoc : dsThuoc) {
					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
					LocalDate date = LocalDate.parse(thuoc.getHanSuDung().toString(), formatter);
					LocalDate now = LocalDate.now();
					if (now.isAfter(date)) {
						tam.add(thuoc);
					}
				}
				loadHetDulieuVaoList(tam);
			}
		}
	}

	private void loadThuocConHanVaoList() {
		// TODO Auto-generated method stub
		if (maNCC != null) {
			if (maNCC.toString().equals("Nhà cung cấp")) { 
				dao_thuoc = new TThuocT_Dao();
				ArrayList<Thuoc> dsThuoc = dao_thuoc.getalltbThuoc();
				ArrayList<Thuoc> tam = new ArrayList<Thuoc>();
				for (Thuoc thuoc : dsThuoc) {
					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
					LocalDate date = LocalDate.parse(thuoc.getHanSuDung().toString(), formatter);
					LocalDate now = LocalDate.now();
					if (date.isAfter(now) || date.isEqual(now)) {
						tam.add(thuoc);
					}
				}
				loadHetDulieuVaoList(tam);
			} else {
				dao_thuoc = new TThuocT_Dao();
				ArrayList<Thuoc> dsThuoc = dao_thuoc.getThuocTheoMaNCC(maNCC.toString());
				ArrayList<Thuoc> tam = new ArrayList<Thuoc>();
				for (Thuoc thuoc : dsThuoc) {
					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
					LocalDate date = LocalDate.parse(thuoc.getHanSuDung().toString(), formatter);
					LocalDate now = LocalDate.now();
					if (date.isAfter(now) || date.isEqual(now)) {
						tam.add(thuoc);
					}
				}
				loadHetDulieuVaoList(tam);
			}
		}
		
	}

	private void sua() {
		// TODO Auto-generated method stub
		if (tableThuoc.getSelectedRow() == -1) {
			if (kiemTraDuLieu()) {
				String ma = txtMaThuoc.getText();
				String ten = txtTenThuoc.getText();
				double dongia = Double.parseDouble(txtDonGia.getText());
				Date ngaysx = (Date) datePicker.getModel().getValue();
				Date ngayHSD = (Date) datePickerH.getModel().getValue();
				int soLuong = Integer.parseInt(txtSLTon.getText());
				String maLoai = catChuoiMa(cboLoai.getSelectedItem().toString())[0];
				LoaiThuoc loai = new LoaiThuoc(maLoai);
				String idNuoc = catChuoiMa(cboNuoc.getSelectedItem().toString())[0];
				NuocSX  nuoc = new NuocSX(idNuoc);
				NhaCungCap ncc = new NhaCungCap(maNCC.toString());
				Thuoc t = new Thuoc(ma, ten, dongia, ngaysx, ngayHSD, soLuong, ncc, loai, nuoc);
				if (dao_thuoc.update(t)) {
					ArrayList<Thuoc> ds = dao_thuoc.getalltbThuoc();
					loadHetDulieuVaoList(ds);
				} else {
					JOptionPane.showMessageDialog(this, "Sửa thất bại");
				}
			}
		}
	}

	private void xoaHetText() {
		// TODO Auto-generated method stub
		txtMaThuoc.setText("");
		txtMaThuoc.setEditable(true);
		txtTenThuoc.setText("");
		txtDonGia.setText("");
		txtSLTon.setText("");
		cboLoai.setSelectedIndex(0);
		cboNuoc.setSelectedIndex(0);
		txtMaThuoc.requestFocus();
	}

	private void xoaThuoc() {
		// TODO Auto-generated method stub
		if (tableThuoc.getSelectedRow() == -1) {
			JOptionPane.showMessageDialog(this, "Hãy chọn Thuốc cần xóa");
		} else {
			int tl;
			tl = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xóa Thuốc này không ?", "Cảnh báo",
					JOptionPane.YES_OPTION);
			if (tl == JOptionPane.YES_OPTION) {
				while (tableThuoc.getSelectedRowCount() > 0) {
					int index = tableThuoc.getSelectedRow();
					dao_thuoc.xoa(modelThuoc.getValueAt(tableThuoc.getSelectedRow(), 0).toString());
					modelThuoc.removeRow(index);
				}
			}
		}
	}

	private void themThuoc() {
		// TODO Auto-generated method stub
		if(maNCC != null) {
			if(kiemTraDuLieu()) {
				String ma = txtMaThuoc.getText();
				String ten = txtTenThuoc.getText();
				double dongia = Double.parseDouble(txtDonGia.getText());
				Date ngaysx = (Date) datePicker.getModel().getValue();
				Date ngayHSD = (Date) datePickerH.getModel().getValue();
				int soLuong = Integer.parseInt(txtSLTon.getText());
				String maLoai = catChuoiMa(cboLoai.getSelectedItem().toString())[0];
				LoaiThuoc loai = new LoaiThuoc(maLoai);
				String idNuoc = catChuoiMa(cboNuoc.getSelectedItem().toString())[0];
				NuocSX  nuoc = new NuocSX(idNuoc);
				NhaCungCap ncc = new NhaCungCap(maNCC.toString());
				Thuoc thuoc = new Thuoc(ma, ten, dongia, ngaysx, ngayHSD, soLuong, ncc, loai, nuoc);
				if (dao_thuoc.create(thuoc)) {
					String []n = {thuoc.getMaThuoc(), thuoc.getTenThuoc(), df.format(thuoc.getDonGia()) + "", thuoc.getNgaySX() + "", thuoc.getHanSuDung() + "", thuoc.getSLTon() + "",thuoc.getNcc().getMaNCC(), thuoc.getLoaiThuoc().getMaLoai(), thuoc.getNuocSX().getIdNuoc()};
					modelThuoc.addRow(n);
				} else {
					JOptionPane.showMessageDialog(this, "Lỗi trùng mã Thuốc");
				}
			}
		}
	}

	private String[] catChuoiMa(String string) {
		// TODO Auto-generated method stub
		return string.split(":");
	}

	private boolean kiemTraDuLieu() {
		// TODO Auto-generated method stub
		if(regex.kiemTraRong(txtMaThuoc))
			return false;
		if(regex.RegexMaThuoc(txtMaThuoc))
			return false;
		if(regex.kiemTraRong(txtTenThuoc))
			return false;
		if(regex.RegexTenThuoc(txtTenThuoc))
			return false;
		if(regex.kiemTraRong(txtDonGia))
			return false;
		if(regex.kiemTraSoDouble(txtDonGia))
			return false;
		if(regex.kiemTraRong(txtSLTon))
			return false;
		if(regex.kiemTraSo(txtSLTon))
			return false;
		if(regex.kiemTraNgaySX(modelNgaySX))
			return false;
		if(regex.kiemTraHanSD(modelNgayHSD))
			return false;
		return true;
	}

	@Override
	public void valueChanged(TreeSelectionEvent e) {
		// TODO Auto-generated method stub
		TreePath chon = e.getNewLeadSelectionPath();
		maNCC =  chon.getLastPathComponent();
		if (maNCC.toString().equals("Nhà cung cấp")) {
			dao_thuoc = new TThuocT_Dao();
			ArrayList<Thuoc> dsThuoc = dao_thuoc.getalltbThuoc();
			loadHetDulieuVaoList(dsThuoc);
		} else {
			dao_thuoc = new TThuocT_Dao();
			ArrayList<Thuoc> dsThuoc = dao_thuoc.getThuocTheoMaNCC(maNCC.toString());
			loadHetDulieuVaoList(dsThuoc);
		}
	}
	
	private void loadHetDulieuVaoList(ArrayList<Thuoc> dsThuoc) {
		DefaultTableModel tam = (DefaultTableModel) tableThuoc.getModel();
		tam.getDataVector().removeAllElements();
			for (Thuoc thuoc : dsThuoc) {
				String []n = {thuoc.getMaThuoc(), thuoc.getTenThuoc(), df.format(thuoc.getDonGia()) + "", thuoc.getNgaySX() + "", thuoc.getHanSuDung() + "", thuoc.getSLTon() + "",thuoc.getNcc().getMaNCC(), thuoc.getLoaiThuoc().getMaLoai(), thuoc.getNuocSX().getIdNuoc()};
				modelThuoc.addRow(n);
			}
	}
}
