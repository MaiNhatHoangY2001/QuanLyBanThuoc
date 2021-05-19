package app_QLT;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
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
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultMutableTreeNode;

public class FrmThuoc extends JPanel implements ActionListener, MouseListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Các thuộc tính của Phần Nhập thông tin Thuốc
	 */
	private JTextField txtMaThuoc;
	private JTextField txtTenThuoc;
	private JTextField txtNgaySX;
	private JTextField txtHangSD;
	private JTextField txtDonGia;
	private JTextField txtSLTon;
	private JComboBox<String> cboLoai;

	/**
	 * Các thuộc tính của phần Nhập thông tin nhà cung cấp
	 */
	private JTextField txtMaNCC;
	private JTextField txtTenNCC;
	private JTextField txtDiaChi;
	private JTextField txtIDNuocSX;
	private JTextField txtTenNuocSX;

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
	 * Các thuộc tính của phần tổng số thuốc
	 */
	private JTextField txtTongSoThuoc;

	/**
	 * Các thuộc tính trình trạng
	 */
	private JRadioButton radAll;
	private JRadioButton radConHang;
	private JRadioButton radHetHang;

	/**
	 * Các thuôc tính tìm kiếm
	 */
	private JComboBox<String> cboLoaiTimKiem;
	private JTextField txtThongTinTimKiem;
	private JButton btnTim;
	private JButton btnThemPhong;
	private JButton btnXoaPhong;
	private DefaultMutableTreeNode root;
	private JTree tree;
	private ArrayList<DefaultMutableTreeNode> dsNCC;

	public FrmThuoc() {
		setLayout(null);

		JPanel pTrai = new JPanel();
		pTrai.setLayout(null);

		root = new DefaultMutableTreeNode("Nhà cung cấp");
		tree = new JTree(root);
		JScrollPane thanhCuonTree = new JScrollPane(tree);
		thanhCuonTree.setBounds(10, 10, 205, 610);
		pTrai.add(thanhCuonTree);
		dsNCC = new ArrayList<DefaultMutableTreeNode>();
		dsNCC.add(new DefaultMutableTreeNode("Nhà cung cấp 1"));
		for (DefaultMutableTreeNode dmt : dsNCC) {
			root.add(dmt);
		}

		JPanel pBang = new JPanel(new GridLayout(1, 1));
		pBang.setBorder(BorderFactory.createTitledBorder("Thông tin"));
		/**
		 * Phần listView (Bảng thông tin)
		 */
		String header[] = { "Mã Thuốc", "Tên thuôc", "NSX", "HSD", "Đơn giá", "SLT", "Loại" };
		modelThuoc = new DefaultTableModel(header, 0);
		tableThuoc = new JTable(modelThuoc);
		JScrollPane thanhCuon = new JScrollPane(tableThuoc);
		thanhCuon.setBounds(10, 10, 525, 200);
		pBang.add(thanhCuon);

		btnThemPhong = new JButton("Thêm NCC");
		btnThemPhong.setBounds(10, 620, 100, 30);
		pTrai.add(btnThemPhong);
		btnXoaPhong = new JButton("Xóa NCC");
		btnXoaPhong.setBounds(115, 620, 100, 30);
		pTrai.add(btnXoaPhong);

		JPanel pinput = new JPanel();
		pinput.setLayout(null);

		/**
		 * phần nhập thông tin thuốc
		 */
		// Phần JLable
		JLabel lblMaThuoc = new JLabel("Mã thuốc:");
		lblMaThuoc.setBounds(30, 20, 100, 30);
		pinput.add(lblMaThuoc);

		JLabel lblTenThuoc = new JLabel("Tên thuốc:");
		lblTenThuoc.setBounds(30, 55, 100, 30);
		pinput.add(lblTenThuoc);

		JLabel lblNgaySX = new JLabel("Ngày sản xuất:");
		lblNgaySX.setBounds(30, 90, 100, 30);
		pinput.add(lblNgaySX);

		JLabel lblHangSD = new JLabel("Hạng sữ dụng:");
		lblHangSD.setBounds(30, 125, 100, 30);
		pinput.add(lblHangSD);

		JLabel lblDonGia = new JLabel("Đơn giá:");
		lblDonGia.setBounds(30, 160, 100, 30);
		pinput.add(lblDonGia);

		JLabel lblSLTon = new JLabel("Số lượng tồn:");
		lblSLTon.setBounds(30, 195, 100, 30);
		pinput.add(lblSLTon);

		JLabel lblLoai = new JLabel("Loại:");
		lblLoai.setBounds(30, 230, 100, 30);
		pinput.add(lblLoai);

		// Phần các JTextField + JComboBox
		txtMaThuoc = new JTextField();
		txtMaThuoc.setBounds(140, 20, 375, 30);
		pinput.add(txtMaThuoc);

		txtTenThuoc = new JTextField();
		txtTenThuoc.setBounds(140, 55, 375, 30);
		pinput.add(txtTenThuoc);

		txtNgaySX = new JTextField();
		txtNgaySX.setBounds(140, 90, 375, 30);
		pinput.add(txtNgaySX);

		txtHangSD = new JTextField();
		txtHangSD.setBounds(140, 125, 375, 30);
		pinput.add(txtHangSD);

		txtDonGia = new JTextField();
		txtDonGia.setBounds(140, 160, 375, 30);
		pinput.add(txtDonGia);

		txtSLTon = new JTextField();
		txtSLTon.setBounds(140, 195, 375, 30);
		pinput.add(txtSLTon);

		cboLoai = new JComboBox<String>();
		cboLoai.setBounds(140, 230, 375, 30);
		pinput.add(cboLoai);

		/**
		 * Phần các Button
		 */
		btnThem = new JButton("Thêm thuốc");
		btnThem.setBounds(40, 275, 105, 35);
		pinput.add(btnThem);

		btnXoa = new JButton("Xóa");
		btnXoa.setBounds(160, 275, 105, 35);
		pinput.add(btnXoa);

		btnXoaRong = new JButton("Xóa Rỗng");
		btnXoaRong.setBounds(280, 275, 105, 35);
		pinput.add(btnXoaRong);

		btnSua = new JButton("Sữa");
		btnSua.setBounds(400, 275, 105, 35);
		pinput.add(btnSua);

		/**
		 * thêm khung border title cho các phần
		 */
		// phần Nhập thông tin Thuốc
		JPanel pThuoc = new JPanel();
		pThuoc.setBorder(BorderFactory.createTitledBorder("Nhập thông tin thuốc"));
		pThuoc.setBounds(0, 0, 545, 330);
		pinput.add(pThuoc);

		JSplitPane TrenDuoi = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		TrenDuoi.setDividerLocation(300);
		TrenDuoi.setTopComponent(pBang);
		TrenDuoi.setBottomComponent(pinput);

		JSplitPane TraiPhai = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
		TraiPhai.setDividerLocation(225);
		TraiPhai.setLeftComponent(pTrai);
		TraiPhai.setRightComponent(TrenDuoi);
		TraiPhai.setBounds(0, 0, 800, 800);

		add(TraiPhai, BorderLayout.CENTER);
		
		btnThemPhong.addActionListener(this);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
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
		if (o.equals(btnThemPhong)) {
			frmThemNCC frm = new frmThemNCC();
			frm.setVisible(true);
		}
	}
}
