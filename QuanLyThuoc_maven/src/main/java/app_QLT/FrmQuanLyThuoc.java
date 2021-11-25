package app_QLT;

import java.awt.Color;
import java.awt.Rectangle;
import java.time.LocalDate;

import javax.swing.JPanel;

import chucNang.RoundedPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import java.awt.Font;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import java.awt.Cursor;
import javax.swing.JTree;
import javax.swing.ListSelectionModel;

import java.awt.Dimension;
import java.awt.Insets;
import javax.swing.SwingConstants;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class FrmQuanLyThuoc extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8255834269618634150L;
	private JTable table;
	private JTextField txtTen;
	private JTextField txtDonGia;
	private JTextField txtSoLuong;
	private DefaultTableModel model;

	/**
	 * Create the panel.
	 */
	public FrmQuanLyThuoc() {
		setSize(1600, 935);
		setBackground(Color.LIGHT_GRAY);
		setLayout(null);

		/*
		 * JPanel Main
		 */
		JPanel pnlNgang = new JPanel();
		pnlNgang.setBounds(new Rectangle(10, 10, 10, 10));
		pnlNgang.setLayout(null);
		pnlNgang.setBounds(0, 0, 1600, 935);
		add(pnlNgang);

		RoundedPanel pnlChonSP = new RoundedPanel();
		pnlChonSP.setShady(false);
		pnlChonSP.setBackground(Color.WHITE);
		pnlChonSP.setBounds(10, 11, 1564, 400);
		pnlNgang.add(pnlChonSP);
		pnlChonSP.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 50, 1544, 339);
		pnlChonSP.add(scrollPane);

		String headerTitle[] = { "Tên thuốc", "Loại thuốc", "Nước sản xuất", "Ngày sản xuất", "Hạn sử dụng", "Số lượng", "Đơn giá" };
		// Model Table
		model = new DefaultTableModel(headerTitle, 50) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int col) {
				switch (col) {
				default:
					return false;
				}
			}

		};
		// Table
		table = new JTable(model);
		table.setRowHeight(35); // set height items
		table.setFont(new Font("Tahoma", Font.PLAIN, 20));
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		// Set Font title table
		JTableHeader headerTable = table.getTableHeader();
		headerTable.setFont(new Font("Tahoma", Font.PLAIN, 24));
		headerTable.setBackground(new Color(248, 198, 153));
		scrollPane.setViewportView(table);
		scrollPane.setEnabled(false);
		scrollPane.setViewportView(table);

		JLabel lblDanhSchThuc = new JLabel("Danh sách thuốc");
		lblDanhSchThuc.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblDanhSchThuc.setBounds(10, 0, 280, 50);
		pnlChonSP.add(lblDanhSchThuc);

		RoundedPanel pnlThongTInNV = new RoundedPanel();
		pnlThongTInNV.setShady(false);
		pnlThongTInNV.setLayout(null);
		pnlThongTInNV.setBackground(Color.WHITE);
		pnlThongTInNV.setBounds(10, 422, 350, 488);
		pnlNgang.add(pnlThongTInNV);

		JLabel lblNewLabel = new JLabel("Danh sách nhà cung cấp");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setBounds(10, 0, 247, 50);
		pnlThongTInNV.add(lblNewLabel);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 50, 330, 370);
		pnlThongTInNV.add(scrollPane_1);

		DefaultMutableTreeNode node = new DefaultMutableTreeNode("Project");
		DefaultMutableTreeNode node1 = new DefaultMutableTreeNode("App");
		DefaultMutableTreeNode node2 = new DefaultMutableTreeNode("Website");
		DefaultMutableTreeNode node3 = new DefaultMutableTreeNode("WebApp");
		node.add(node1);
		node.add(node2);
		node.add(node3);
		DefaultMutableTreeNode one = new DefaultMutableTreeNode("Learning website");
		DefaultMutableTreeNode two = new DefaultMutableTreeNode("Business website");
		DefaultMutableTreeNode three = new DefaultMutableTreeNode("News publishing website");
		DefaultMutableTreeNode four = new DefaultMutableTreeNode("Android app");
		DefaultMutableTreeNode five = new DefaultMutableTreeNode("iOS app");
		DefaultMutableTreeNode six = new DefaultMutableTreeNode("Editor WebApp");
		node1.add(one);
		node1.add(two);
		node1.add(three);
		node2.add(four);
		node2.add(five);
		node3.add(six);
		JTree tree = new JTree(node);
		tree.setFont(new Font("Tahoma", Font.PLAIN, 20));
		tree.setRowHeight(25);
		scrollPane_1.setViewportView(tree);

		JButton btnThemNCC = new JButton("Thêm nhà cung cấp");
		btnThemNCC.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnThemNCC.setForeground(Color.WHITE);
		btnThemNCC.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnThemNCC.setBackground(new Color(20, 140, 255));
		btnThemNCC.setBounds(10, 426, 330, 40);
		pnlThongTInNV.add(btnThemNCC);

		RoundedPanel pnlThongTInNV_1 = new RoundedPanel();
		pnlThongTInNV_1.setLayout(null);
		pnlThongTInNV_1.setShady(false);
		pnlThongTInNV_1.setBackground(Color.WHITE);
		pnlThongTInNV_1.setBounds(370, 422, 894, 488);
		pnlNgang.add(pnlThongTInNV_1);

		JLabel lblNhpThngTin = new JLabel("Nhập thông tin thuốc");
		lblNhpThngTin.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNhpThngTin.setBounds(10, 0, 214, 50);
		pnlThongTInNV_1.add(lblNhpThngTin);

		JPanel panel = new JPanel();
		panel.setBorder(new MatteBorder(2, 0, 0, 0, (Color) new Color(0, 0, 0)));
		panel.setBackground(Color.WHITE);
		panel.setBounds(10, 50, 874, 416);
		pnlThongTInNV_1.add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("Tên thuốc:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(10, 17, 134, 40);
		panel.add(lblNewLabel_1);

		JLabel lblNewLabel_1_1 = new JLabel("Loại thuốc:");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1_1.setBounds(10, 74, 134, 40);
		panel.add(lblNewLabel_1_1);

		JLabel lblNewLabel_1_1_1 = new JLabel("Nước sản xuất:");
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1_1_1.setBounds(10, 131, 134, 40);
		panel.add(lblNewLabel_1_1_1);

		JLabel lblNewLabel_1_1_1_1 = new JLabel("Ngày sản xuất:");
		lblNewLabel_1_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1_1_1_1.setBounds(10, 188, 134, 40);
		panel.add(lblNewLabel_1_1_1_1);

		JLabel lblNewLabel_1_1_1_1_1 = new JLabel("Hạn sử dụng:");
		lblNewLabel_1_1_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1_1_1_1_1.setBounds(10, 245, 134, 40);
		panel.add(lblNewLabel_1_1_1_1_1);

		JLabel lblNewLabel_1_1_1_1_1_1 = new JLabel("Đơn giá:");
		lblNewLabel_1_1_1_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1_1_1_1_1_1.setBounds(10, 359, 134, 40);
		panel.add(lblNewLabel_1_1_1_1_1_1);

		DefaultComboBoxModel<String> modelNgay = new DefaultComboBoxModel<String>();
		for (int i = 1; i <= 31; i++) {
			modelNgay.addElement(i + "");
		}

		DefaultComboBoxModel<String> modelThang = new DefaultComboBoxModel<String>();
		for (int i = 1; i <= 12; i++) {
			modelThang.addElement("Tháng " + i);
		}

		JComboBox cmbNgaySX = new JComboBox();
		cmbNgaySX.setModel(modelNgay);
		cmbNgaySX.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		cmbNgaySX.setFont(new Font("Tahoma", Font.PLAIN, 20));
		cmbNgaySX.setBounds(154, 188, 230, 40);
		panel.add(cmbNgaySX);

		JComboBox cmbThangSX = new JComboBox();
		cmbThangSX.setModel(modelThang);
		cmbThangSX.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		cmbThangSX.setFont(new Font("Tahoma", Font.PLAIN, 20));
		cmbThangSX.setBounds(394, 188, 230, 40);
		panel.add(cmbThangSX);

		DefaultComboBoxModel<String> modelNam = new DefaultComboBoxModel<String>();
		for (int i = LocalDate.now().getYear(); i >= 2000; i--) {
			modelNam.addElement(i + "");
		}
		JComboBox cmbNamSX = new JComboBox();
		cmbNamSX.setModel(modelNam);
		cmbNamSX.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		cmbNamSX.setFont(new Font("Tahoma", Font.PLAIN, 20));
		cmbNamSX.setBounds(634, 188, 230, 40);
		panel.add(cmbNamSX);

		JComboBox cmbNgayHSD = new JComboBox();
		cmbNgayHSD.setModel(modelNgay);
		cmbNgayHSD.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		cmbNgayHSD.setFont(new Font("Tahoma", Font.PLAIN, 20));
		cmbNgayHSD.setBounds(154, 245, 230, 40);
		panel.add(cmbNgayHSD);

		JComboBox cmbThangHSD = new JComboBox();
		cmbThangHSD.setModel(modelThang);
		cmbThangHSD.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		cmbThangHSD.setFont(new Font("Tahoma", Font.PLAIN, 20));
		cmbThangHSD.setBounds(394, 245, 230, 40);
		panel.add(cmbThangHSD);

		int nam = LocalDate.now().getYear();
		DefaultComboBoxModel<String> modelNam1 = new DefaultComboBoxModel<String>();
		for (int i = nam; i <= nam + 10; i++) {
			modelNam1.addElement(i + "");
		}
		JComboBox cmbNamHSD = new JComboBox();
		cmbNamHSD.setModel(modelNam1);
		cmbNamHSD.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		cmbNamHSD.setFont(new Font("Tahoma", Font.PLAIN, 20));
		cmbNamHSD.setBounds(634, 245, 230, 40);
		panel.add(cmbNamHSD);

		txtTen = new JTextField();
		txtTen.setMargin(new Insets(2, 14, 2, 2));
		txtTen.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtTen.setBounds(154, 17, 710, 40);
		panel.add(txtTen);
		txtTen.setColumns(10);

		txtDonGia = new JTextField();
		txtDonGia.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char vChar = e.getKeyChar();
				if (!(Character.isDigit(vChar) || (vChar == KeyEvent.VK_BACK_SPACE) || (vChar == KeyEvent.VK_DELETE)
						|| (vChar == KeyEvent.VK_PERIOD))) {
					e.consume();
				}
			}
		});
		txtDonGia.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtDonGia.setHorizontalAlignment(SwingConstants.TRAILING);
		txtDonGia.setMargin(new Insets(2, 14, 2, 14));
		txtDonGia.setColumns(10);
		txtDonGia.setBounds(154, 359, 710, 40);
		panel.add(txtDonGia);

		JComboBox cmbLoai = new JComboBox();
		cmbLoai.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		cmbLoai.setFont(new Font("Tahoma", Font.PLAIN, 20));
		cmbLoai.setBounds(154, 74, 470, 40);
		panel.add(cmbLoai);

		JComboBox cmbNSX = new JComboBox();
		cmbNSX.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		cmbNSX.setFont(new Font("Tahoma", Font.PLAIN, 20));
		cmbNSX.setBounds(154, 131, 470, 40);
		panel.add(cmbNSX);

		JButton btnThemNSX = new JButton("Thêm nước sản xuất");
		btnThemNSX.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnThemNSX.setForeground(Color.WHITE);
		btnThemNSX.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnThemNSX.setBackground(new Color(20, 140, 255));
		btnThemNSX.setBounds(634, 131, 230, 40);
		panel.add(btnThemNSX);

		JButton btnThemLoai = new JButton("Thêm loại thuốc");
		btnThemLoai.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnThemLoai.setForeground(Color.WHITE);
		btnThemLoai.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnThemLoai.setBackground(new Color(20, 140, 255));
		btnThemLoai.setBounds(634, 74, 230, 40);
		panel.add(btnThemLoai);

		txtSoLuong = new JTextField();
		txtSoLuong.setBounds(154, 302, 710, 40);
		panel.add(txtSoLuong);
		txtSoLuong.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char vChar = e.getKeyChar();
				if (!(Character.isDigit(vChar) || (vChar == KeyEvent.VK_BACK_SPACE) || (vChar == KeyEvent.VK_DELETE)))
					e.consume();
			}
		});
		txtSoLuong.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtSoLuong.setHorizontalAlignment(SwingConstants.TRAILING);
		txtSoLuong.setMargin(new Insets(2, 14, 2, 14));
		txtSoLuong.setColumns(10);

		JLabel lblNewLabel_1_1_1_1_1_1_1 = new JLabel("Số Lượng:");
		lblNewLabel_1_1_1_1_1_1_1.setBounds(10, 302, 134, 40);
		panel.add(lblNewLabel_1_1_1_1_1_1_1);
		lblNewLabel_1_1_1_1_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 20));

		RoundedPanel pnlThongTInNV_2 = new RoundedPanel();
		pnlThongTInNV_2.setLayout(null);
		pnlThongTInNV_2.setShady(false);
		pnlThongTInNV_2.setBackground(Color.WHITE);
		pnlThongTInNV_2.setBounds(1274, 422, 300, 488);
		pnlNgang.add(pnlThongTInNV_2);

		JButton btnLuu = new JButton("Lưu");
		btnLuu.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnLuu.setBounds(25, 31, 250, 80);
		pnlThongTInNV_2.add(btnLuu);
		btnLuu.setForeground(Color.WHITE);
		btnLuu.setFont(new Font("Tahoma", Font.BOLD, 24));
		btnLuu.setBackground(new Color(20, 140, 255));

		JButton btnXoaRong = new JButton("Xóa Rỗng");
		btnXoaRong.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnXoaRong.setForeground(Color.WHITE);
		btnXoaRong.setFont(new Font("Tahoma", Font.BOLD, 24));
		btnXoaRong.setBackground(new Color(20, 140, 255));
		btnXoaRong.setBounds(25, 142, 250, 80);
		pnlThongTInNV_2.add(btnXoaRong);

		JButton btnSua = new JButton("Sửa");
		btnSua.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnSua.setForeground(Color.WHITE);
		btnSua.setFont(new Font("Tahoma", Font.BOLD, 24));
		btnSua.setBackground(new Color(20, 140, 255));
		btnSua.setBounds(25, 253, 250, 80);
		pnlThongTInNV_2.add(btnSua);

		JButton btnLamMoi = new JButton("Làm Mới");
		btnLamMoi.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnLamMoi.setForeground(Color.WHITE);
		btnLamMoi.setFont(new Font("Tahoma", Font.BOLD, 24));
		btnLamMoi.setBackground(new Color(248, 96, 96));
		btnLamMoi.setBounds(25, 364, 250, 80);
		pnlThongTInNV_2.add(btnLamMoi);
	}
}
