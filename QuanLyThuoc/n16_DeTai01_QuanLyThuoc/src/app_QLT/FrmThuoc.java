package app_QLT;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import java.sql.Date;
import java.sql.SQLException;
import java.text.DecimalFormat;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Properties;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

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
	 * Các thuôc tính tìm kiếm
	 */
	private JComboBox<String> cboLoaiTimKiem;
	private JTextField txtThongTinTimKiem;
	private JButton btnTim;
	private JButton btnThemNhaCungCap;
	
	/**
	 * cây thư mục
	 */
	private DefaultMutableTreeNode root;
	private JTree tree;	
	
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
		thanhCuonTree.setBounds(10, 20, 200, 280);
		pTrai.setBackground(new Color(248,248,248));
		pTrai.add(thanhCuonTree);

			// Nút thêm nhà cung cấp
		btnThemNhaCungCap = new JButton("Thêm NCC");
		btnThemNhaCungCap.setBounds(10, 300, 200, 30);
		btnThemNhaCungCap.setBackground(new Color(191, 247, 249));
		btnThemNhaCungCap.setForeground(Color.DARK_GRAY);
		btnThemNhaCungCap.setBorder(BorderFactory.createLineBorder(Color.lightGray));
		pTrai.add(btnThemNhaCungCap);

		JPanel pBang = new JPanel(new GridLayout(1, 1));
		
		pBang.setBorder(BorderFactory.createTitledBorder("Thông tin"));
		pBang.setBackground(new Color(248,248,248));
		/**
		 * Phần listView (Bảng thông tin)
		 */
		String header[] = { "Mã Thuốc", "Tên thuốc","Đơn giá", "NSX", "HSD", "SLT", "Mã Loại", "Id Nước"};
		modelThuoc = new DefaultTableModel(header, 0);
		tableThuoc = new JTable(modelThuoc);
		
		JScrollPane thanhCuon = new JScrollPane(tableThuoc);
		thanhCuon.setBounds(10, 10, 525, 200);
		thanhCuon.setBackground(Color.white);
		pBang.add(thanhCuon);

		JPanel pinput = new JPanel();
		pinput.setLayout(null);
		
		JPanel pThuoc = new JPanel();
		pThuoc.setLayout(null);
		/**
		 * phần nhập thông tin thuốc
		 */
			// Phần JLable
		JLabel lblMaThuoc = new JLabel("Mã thuốc:");
		lblMaThuoc.setBounds(50, 20, 100, 30);
		pThuoc.add(lblMaThuoc);

		JLabel lblTenThuoc = new JLabel("Tên thuốc:");
		lblTenThuoc.setBounds(50, 60, 100, 30);
		pThuoc.add(lblTenThuoc);

		JLabel lblNgaySX = new JLabel("Ngày sản xuất:");
		lblNgaySX.setBounds(50, 100, 100, 30);
		pThuoc.add(lblNgaySX);

		JLabel lblHangSD = new JLabel("Hạn sử dụng:");
		lblHangSD.setBounds(50, 140, 100, 30);
		pThuoc.add(lblHangSD);

		JLabel lblDonGia = new JLabel("Đơn giá:");
		lblDonGia.setBounds(50, 180, 100, 30);
		pThuoc.add(lblDonGia);

		JLabel lblSLTon = new JLabel("Số lượng tồn:");
		lblSLTon.setBounds(50, 220, 100, 30);
		pThuoc.add(lblSLTon);

		JLabel lblLoai = new JLabel("Loại:");
		lblLoai.setBounds(50, 260, 100, 30);
		pThuoc.add(lblLoai);
		
		JLabel lblNuoc = new JLabel("Nước:");
		lblNuoc.setBounds(50, 300, 100, 30);
		pThuoc.add(lblNuoc);

		// Phần các JTextField + JComboBox
		txtMaThuoc = new JTextField();
		txtMaThuoc.setBounds(150, 20, 330, 30);
		pThuoc.add(txtMaThuoc);

		txtTenThuoc = new JTextField();
		txtTenThuoc.setBounds(150, 60, 330, 30);
		pThuoc.add(txtTenThuoc);

		modelNgaySX = new SqlDateModel();
		p = new Properties();
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");
		datePanel = new JDatePanelImpl(modelNgaySX, p);
		datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
		datePicker.setBounds(150, 100, 330, 30);
		datePicker.setBackground(new Color(248,248,248));
		LocalDate now = LocalDate.now();
		int ngay = now.getDayOfMonth();
		int thang = now.getMonthValue();
		int nam = now.getYear();
		modelNgaySX.setDate(nam, thang, ngay);
		modelNgaySX.setSelected(true);
		pThuoc.add(datePicker);

		modelNgayHSD = new SqlDateModel();
		h = new Properties();
		h.put("text.today", "Today");
		h.put("text.month", "Month");
		h.put("text.year", "Year");
		datePanelH = new JDatePanelImpl(modelNgayHSD, h);
		datePickerH = new JDatePickerImpl(datePanelH, new DateLabelFormatter());
		datePickerH.setBounds(150, 140, 330, 30);
		datePickerH.setBackground(new Color(248,248,248));
		LocalDate now1 = LocalDate.now();
		int ngay1 = now1.getDayOfMonth();
		int thang1 = now1.getMonthValue();
		int nam1 = now1.getYear();
		modelNgayHSD.setDate(nam1, thang1, ngay1);
		modelNgayHSD.setSelected(true);
		pThuoc.add(datePickerH);

		txtDonGia = new JTextField();
		txtDonGia.setBounds(150, 180, 330, 30);
		pThuoc.add(txtDonGia);

		txtSLTon = new JTextField();
		txtSLTon.setBounds(150, 220, 330, 30);
		pThuoc.add(txtSLTon);

		cboLoai = new JComboBox<String>();
		cboLoai.setBounds(150, 260, 210, 30);
		pThuoc.add(cboLoai);
		
		btnThemLoai = new JButton("Thêm loại");
		btnThemLoai.setBounds(370, 260, 110, 30);
		btnThemLoai.setBackground(new Color(191, 247, 249));
		btnThemLoai.setForeground(Color.DARK_GRAY);
		btnThemLoai.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
		pThuoc.add(btnThemLoai);
		
		cboNuoc = new JComboBox<String>();
		cboNuoc.setBounds(150, 300, 210, 30);
		pThuoc.add(cboNuoc);
		
		btnThemNuoc = new JButton("Thêm nước");
		btnThemNuoc.setBounds(370, 300, 110, 30);
		btnThemNuoc.setBackground(new Color(191, 247, 249));
		btnThemNuoc.setForeground(Color.DARK_GRAY);
		btnThemNuoc.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
		pThuoc.add(btnThemNuoc);

		/**
		 * Phần các Button
		 */
		btnThem = new JButton("Thêm thuốc");
		btnThem.setBounds(100, 360, 105, 35);
		btnThem.setBackground(new Color(191, 247, 249));
		btnThem.setForeground(Color.DARK_GRAY);
		btnThem.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
		pinput.add(btnThem);

		btnXoa = new JButton("Xóa");
		btnXoa.setBounds(220, 360, 105, 35);
		btnXoa.setBackground(new Color(191, 247, 249));
		btnXoa.setForeground(Color.DARK_GRAY);
		btnXoa.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
		pinput.add(btnXoa);

		btnXoaRong = new JButton("Làm mới");
		btnXoaRong.setBounds(340, 360, 105, 35);
		btnXoaRong.setBackground(new Color(191, 247, 249));
		btnXoaRong.setForeground(Color.DARK_GRAY);
		btnXoaRong.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
		pinput.add(btnXoaRong);

		btnSua = new JButton("Sửa");
		btnSua.setBounds(460, 360, 105, 35);
		btnSua.setBackground(new Color(191, 247, 249));
		btnSua.setForeground(Color.DARK_GRAY);
		btnSua.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
		pinput.add(btnSua);
		
		btnTim = new JButton("Tìm");
		btnTim.setBounds(580, 360, 105, 35);
		btnTim.setBackground(new Color(191, 247, 249));
		btnTim.setForeground(Color.DARK_GRAY);
		btnTim.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
		pinput.add(btnTim);
		

		/**
		 * thêm khung border title cho các phần
		 */
		
			// Phần Tree
		pTrai.setBorder(BorderFactory.createTitledBorder("Danh sách nhà cung cấp"));
		pTrai.setBounds(10, 10, 220, 340);
		pinput.add(pTrai);
		
			// phần Nhập thông tin Thuốc
		pThuoc.setBorder(BorderFactory.createTitledBorder("Nhập thông tin thuốc"));
		pThuoc.setBounds(240, 10, 530, 340);
		pThuoc.setBackground(new Color(248,248,248));
		pinput.add(pThuoc);
		
			
		JSplitPane TrenDuoi = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		TrenDuoi.setDividerLocation(255);
		TrenDuoi.setTopComponent(pBang);
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
		btnThemLoai.addActionListener(this);
		btnThemNuoc.addActionListener(this);
		btnTim.addActionListener(this);
		
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
		((DefaultTreeModel)tree.getModel()).nodeStructureChanged((DefaultMutableTreeNode)root);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		int row = tableThuoc.getSelectedRow();
		txtMaThuoc.setText(modelThuoc.getValueAt(row, 0).toString());
		txtMaThuoc.setEditable(false);
		txtTenThuoc.setText(modelThuoc.getValueAt(row, 1).toString());
		txtDonGia.setText(modelThuoc.getValueAt(row, 2).toString());
		modelNgaySX.setValue(Date.valueOf(modelThuoc.getValueAt(row, 3).toString()));
		modelNgayHSD.setValue(Date.valueOf(modelThuoc.getValueAt(row, 4).toString()));
		txtSLTon.setText(modelThuoc.getValueAt(row, 5).toString());
		cboLoai.setSelectedItem(modelThuoc.getValueAt(row, 6).toString());
		cboNuoc.setSelectedItem(modelThuoc.getValueAt(row, 7).toString());
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
			themLoaiVaoCboLoai();
			themLoaiVaocboNuoc();
			tree.clearSelection();
			ArrayList<NhaCungCap> ds = dao_ncc.getalltbNhaCungCap();
			themNCCVaoTree(ds);
			xoaHetText();
		} else if (o.equals(btnSua)) {
			sua();
			xoaHetText();
		} else if (o.equals(btnThemLoai)) {
			frmThemLoai frm = new frmThemLoai();
			frm.setVisible(true);
		} else if (o.equals(btnThemNuoc)) {
			FrmNuoc frm = new FrmNuoc();
			frm.setVisible(true);
		} else if (o.equals(btnTim)) {
			FrmTimKiemThuoc frm = new FrmTimKiemThuoc();
			frm.setVisible(true);
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
			tl = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xóa thuốc này không ?", "Cảnh báo",
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
					String []n = {thuoc.getMaThuoc(), thuoc.getTenThuoc(), df.format(thuoc.getDonGia()) + "", thuoc.getNgaySX() + "", thuoc.getHanSuDung() + "", thuoc.getSLTon() + "", thuoc.getLoaiThuoc().getMaLoai(), thuoc.getNuocSX().getIdNuoc()};
					modelThuoc.addRow(n);
				} else {
					JOptionPane.showMessageDialog(this, "Lỗi trùng mã thuốc");
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
		if(chon != null) {
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
	}
	
	private void loadHetDulieuVaoList(ArrayList<Thuoc> dsThuoc) {
		DefaultTableModel tam = (DefaultTableModel) tableThuoc.getModel();
		tam.getDataVector().removeAllElements();
			for (Thuoc thuoc : dsThuoc) {
				String []n = {thuoc.getMaThuoc(), thuoc.getTenThuoc(), df.format(thuoc.getDonGia()) + "", thuoc.getNgaySX() + "", thuoc.getHanSuDung() + "", thuoc.getSLTon() + "", thuoc.getLoaiThuoc().getMaLoai(), thuoc.getNuocSX().getIdNuoc()};
				modelThuoc.addRow(n);
			}
	}
}
