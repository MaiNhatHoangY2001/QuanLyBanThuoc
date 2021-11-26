package frm;

import java.awt.Color;
import java.awt.Rectangle;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.swing.JPanel;

import chucNang.ChucNang;
import chucNang.RoundedPanel;
import dao.LoaiThuocDao;
import dao.NhaCungCapDao;
import dao.NuocDao;
import dao.ThuocDao;
import dao.impl.LoaiThuocDaoImpl;
import dao.impl.NhaCungCapDaoImpl;
import dao.impl.NuocDaoImpl;
import dao.impl.ThuocDaoImpl;
import entity.LoaiThuoc;
import entity.NhaCungCap;
import entity.NuocSX;
import entity.Thuoc;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import java.awt.Cursor;
import javax.swing.JTree;
import javax.swing.ListSelectionModel;

import java.awt.Dimension;
import java.awt.Insets;
import javax.swing.SwingConstants;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.rmi.RemoteException;
import java.text.NumberFormat;

import javax.swing.event.TreeSelectionListener;
import javax.swing.event.TreeSelectionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

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
	private NhaCungCapDao nccDao;
	private ThuocDao thuocDao;
	private LoaiThuocDao loaiDao;
	private NuocDao nuocDao;
	private DefaultMutableTreeNode node;
	private List<NhaCungCap> listNCC;
	private List<String> listMaNCC;
	private List<Thuoc> listThuoc;
	private List<String> listMaThuoc;
	private List<LoaiThuoc> listLoai;
	private List<String> listMaLoai;
	private List<NuocSX> listNuoc;
	private List<String> listMaNuoc;
	private DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	private NumberFormat vnFormat = NumberFormat.getInstance(new Locale("vi", "VN"));
	private JComboBox cmbLoai;
	private JComboBox cmbNSX;
	private JComboBox cmbNgaySX;
	private JComboBox cmbNgayHSD;
	private JComboBox cmbThangSX;
	private JComboBox cmbThangHSD;
	private JComboBox cmbNamSX;
	private JComboBox cmbNamHSD;
	private JButton btnThemLoai;
	private JButton btnThemNSX;
	private JButton btnLuu;
	private JButton btnXoaRong;
	private JButton btnSua;
	private JButton btnThemNCC;
	private JTree tree;

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

		String headerTitle[] = { "Tên thuốc", "Ngày sản xuất", "Hạn sử dụng", "Số lượng", "Đơn giá" };
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
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int count = table.getSelectedRowCount();
				if (count != 0) {
					int index = table.getSelectedRow();
					Thuoc thuoc = listThuoc.get(table.getSelectedRow());
					txtTen.setText(thuoc.getTenThuoc());
					int vtLoai = getViTriTrongList(listMaLoai, thuoc.getLoaiThuoc().getMaLoai());
					cmbLoai.setSelectedIndex(vtLoai);
					int vtNuoc = getViTriTrongList(listMaNuoc, thuoc.getNuocSX().getIdNuoc());
					cmbNSX.setSelectedIndex(vtNuoc);
					setDate(cmbNgaySX, cmbThangSX, cmbNamSX, thuoc.getNgaySX());
					setDate(cmbNgayHSD, cmbThangHSD, cmbNamHSD, thuoc.getHanSuDung());
					txtSoLuong.setText(thuoc.getSLTon() + "");
					String dongia = (thuoc.getDonGia() % 1) == 0 ? ((int) thuoc.getDonGia()) + ""
							: thuoc.getDonGia() + "";
					txtDonGia.setText(dongia);
				}
			}

		});
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

		node = new DefaultMutableTreeNode("Nhà cung cấp");
		tree = new JTree(node);
		tree.addTreeSelectionListener(new TreeSelectionListener() {
			public void valueChanged(TreeSelectionEvent arg) {
				TreePath chon = arg.getNewLeadSelectionPath();
				if (chon != null) {
					Object ncc = chon.getLastPathComponent();
					if (ncc.toString().equals("Nhà cung cấp")) {
						try {
							listThuoc = thuocDao.getdsThuoc();
							loadThongTinThuoc(listThuoc);
						} catch (RemoteException e) {
							e.printStackTrace();
						}
					} else {
						int index = tree.getSelectionRows()[0];
						String ma = listMaNCC.get(index - 1);
						try {
							listThuoc = thuocDao.getdsThuocTheoMaNcc(ma);
							loadThongTinThuoc(listThuoc);
						} catch (RemoteException e) {
							e.printStackTrace();
						}
					}
				}
			}
		});
		tree.setFont(new Font("Tahoma", Font.PLAIN, 20));
		tree.setRowHeight(25);
		tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
		scrollPane_1.setViewportView(tree);

		btnThemNCC = new JButton("Thêm nhà cung cấp");
		btnThemNCC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new FrmThemNCC().setVisible(true);
			}
		});
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

		DefaultComboBoxModel<String> ngaySX = new DefaultComboBoxModel<String>();
		for (int i = 1; i <= 31; i++) {
			ngaySX.addElement(i + "");
		}
		cmbNgaySX = new JComboBox();
		cmbNgaySX.setModel(ngaySX);
		cmbNgaySX.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		cmbNgaySX.setFont(new Font("Tahoma", Font.PLAIN, 20));
		cmbNgaySX.setBounds(154, 188, 230, 40);
		panel.add(cmbNgaySX);

		DefaultComboBoxModel<String> thangSX = new DefaultComboBoxModel<String>();
		for (int i = 1; i <= 12; i++) {
			thangSX.addElement("Tháng " + i);
		}
		cmbThangSX = new JComboBox();
		cmbThangSX.setModel(thangSX);
		cmbThangSX.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		cmbThangSX.setFont(new Font("Tahoma", Font.PLAIN, 20));
		cmbThangSX.setBounds(394, 188, 230, 40);
		panel.add(cmbThangSX);

		DefaultComboBoxModel<String> namSX = new DefaultComboBoxModel<String>();
		for (int i = LocalDate.now().getYear(); i >= 2000; i--) {
			namSX.addElement(i + "");
		}
		cmbNamSX = new JComboBox();
		cmbNamSX.setModel(namSX);
		cmbNamSX.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		cmbNamSX.setFont(new Font("Tahoma", Font.PLAIN, 20));
		cmbNamSX.setBounds(634, 188, 230, 40);
		panel.add(cmbNamSX);

		DefaultComboBoxModel<String> ngayHSD = new DefaultComboBoxModel<String>();
		for (int i = 1; i <= 31; i++) {
			ngayHSD.addElement(i + "");
		}
		cmbNgayHSD = new JComboBox();
		cmbNgayHSD.setModel(ngayHSD);
		cmbNgayHSD.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		cmbNgayHSD.setFont(new Font("Tahoma", Font.PLAIN, 20));
		cmbNgayHSD.setBounds(154, 245, 230, 40);
		panel.add(cmbNgayHSD);

		DefaultComboBoxModel<String> thangHSD = new DefaultComboBoxModel<String>();
		for (int i = 1; i <= 12; i++) {
			thangHSD.addElement("Tháng " + i);
		}
		cmbThangHSD = new JComboBox();
		cmbThangHSD.setModel(thangHSD);
		cmbThangHSD.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		cmbThangHSD.setFont(new Font("Tahoma", Font.PLAIN, 20));
		cmbThangHSD.setBounds(394, 245, 230, 40);
		panel.add(cmbThangHSD);

		int nam = LocalDate.now().getYear();
		DefaultComboBoxModel<String> namHSD = new DefaultComboBoxModel<String>();
		for (int i = nam; i <= nam + 10; i++) {
			namHSD.addElement(i + "");
		}
		cmbNamHSD = new JComboBox();
		cmbNamHSD.setModel(namHSD);
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
				if (txtDonGia.getText().length() >= 10)
					e.consume();
				else if (!(Character.isDigit(vChar) || (vChar == KeyEvent.VK_BACK_SPACE)
						|| (vChar == KeyEvent.VK_DELETE) || (vChar == KeyEvent.VK_MINUS)))
					e.consume();
			}
		});
		txtDonGia.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtDonGia.setHorizontalAlignment(SwingConstants.TRAILING);
		txtDonGia.setMargin(new Insets(2, 14, 2, 14));
		txtDonGia.setColumns(10);
		txtDonGia.setBounds(154, 359, 710, 40);
		panel.add(txtDonGia);

		cmbLoai = new JComboBox();
		cmbLoai.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		cmbLoai.setFont(new Font("Tahoma", Font.PLAIN, 20));
		cmbLoai.setBounds(154, 74, 470, 40);
		panel.add(cmbLoai);

		cmbNSX = new JComboBox();
		cmbNSX.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		cmbNSX.setFont(new Font("Tahoma", Font.PLAIN, 20));
		cmbNSX.setBounds(154, 131, 470, 40);
		panel.add(cmbNSX);

		btnThemNSX = new JButton("Thêm nước sản xuất");
		btnThemNSX.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new FrmThemNuoc().setVisible(true);
			}
		});
		btnThemNSX.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnThemNSX.setForeground(Color.WHITE);
		btnThemNSX.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnThemNSX.setBackground(new Color(20, 140, 255));
		btnThemNSX.setBounds(634, 131, 230, 40);
		panel.add(btnThemNSX);

		btnThemLoai = new JButton("Thêm loại thuốc");
		btnThemLoai.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new FrmThemLoaiThuoc().setVisible(true);
			}
		});
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
				if (txtSoLuong.getText().length() >= 8)
					e.consume();
				else if (!(Character.isDigit(vChar) || (vChar == KeyEvent.VK_BACK_SPACE)
						|| (vChar == KeyEvent.VK_DELETE) || (vChar == KeyEvent.VK_MINUS)))
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

		btnLuu = new JButton("Thêm");
		btnLuu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int index = tree.getSelectionRows()[0];
				if (index == 0) {
					JOptionPane.showMessageDialog(null, "Vui lòng chọn nhà cung cấp cần thêm");
				} else {
					Thuoc thuoc = getThuoc();
					if (thuoc != null) {
						try {
							boolean rs = thuocDao.themThuoc(thuoc);
							if (rs) {
								JOptionPane.showMessageDialog(null, "Thêm thành công");
								tree.setSelectionRow(0);
							} else
								JOptionPane.showMessageDialog(null, "Thêm thất bại");
						} catch (RemoteException e1) {
							e1.printStackTrace();
						}
					}
				}
			}
		});
		btnLuu.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnLuu.setBounds(25, 31, 250, 80);
		pnlThongTInNV_2.add(btnLuu);
		btnLuu.setForeground(Color.WHITE);
		btnLuu.setFont(new Font("Tahoma", Font.BOLD, 24));
		btnLuu.setBackground(new Color(20, 140, 255));

		btnXoaRong = new JButton("Xóa Rỗng");
		btnXoaRong.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				xoaRong();
			}
		});
		btnXoaRong.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnXoaRong.setForeground(Color.WHITE);
		btnXoaRong.setFont(new Font("Tahoma", Font.BOLD, 24));
		btnXoaRong.setBackground(new Color(20, 140, 255));
		btnXoaRong.setBounds(25, 142, 250, 80);
		pnlThongTInNV_2.add(btnXoaRong);

		btnSua = new JButton("Sửa");
		btnSua.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int index = tree.getSelectionRows()[0];
				if (index == 0) {
					JOptionPane.showMessageDialog(null, "Vui lòng chọn nhà cung cấp cần sửa");
				} else {
					int count = table.getSelectedRowCount();
					if (count >= 0) {
						Thuoc thuoc = listThuoc.get(table.getSelectedRow());
						Thuoc thuoc1 = getThuoc();
						thuoc.setTenThuoc(thuoc1.getTenThuoc());
						thuoc.setLoaiThuoc(thuoc1.getLoaiThuoc());
						thuoc.setNcc(thuoc1.getNcc());
						thuoc.setNuocSX(thuoc1.getNuocSX());
						thuoc.setNgaySX(thuoc1.getNgaySX());
						thuoc.setHanSuDung(thuoc1.getHanSuDung());
						thuoc.setSLTon(thuoc1.getSLTon());
						thuoc.setDonGia(thuoc1.getDonGia());
						System.out.println(thuoc);
						if (thuoc != null) {
							try {
								boolean rs = thuocDao.capNhatThuoc(thuoc);
								if (rs) {
									JOptionPane.showMessageDialog(null, "Cập nhật thành công");
									tree.setSelectionRow(0);
								} else
									JOptionPane.showMessageDialog(null, "Cập nhật thất bại");
							} catch (RemoteException e1) {
								e1.printStackTrace();
							}
						}
					} else {
						JOptionPane.showMessageDialog(null, "Bạn hãy chọn thuốc cần sửa");
					}
				}
			}
		});
		btnSua.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnSua.setForeground(Color.WHITE);
		btnSua.setFont(new Font("Tahoma", Font.BOLD, 24));
		btnSua.setBackground(new Color(20, 140, 255));
		btnSua.setBounds(25, 253, 250, 80);
		pnlThongTInNV_2.add(btnSua);

		JButton btnLamMoi = new JButton("Làm Mới");
		btnLamMoi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				xoaRong();
				try {
					listNCC = nccDao.getdsNhaCungCap();
					loadNCCVaoTree(listNCC);
					tree.setSelectionRow(0);
					listLoai = loaiDao.getdsLoaiThuoc();
					loadLoaiVaoCmb(listLoai);
					listNuoc = nuocDao.getdsNuocSX();
					loadNuocVaoCmb(listNuoc);
				} catch (RemoteException e1) {
					e1.printStackTrace();
				}

			}
		});
		btnLamMoi.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnLamMoi.setForeground(Color.WHITE);
		btnLamMoi.setFont(new Font("Tahoma", Font.BOLD, 24));
		btnLamMoi.setBackground(new Color(248, 96, 96));
		btnLamMoi.setBounds(25, 364, 250, 80);
		pnlThongTInNV_2.add(btnLamMoi);

		try {
			nccDao = new NhaCungCapDaoImpl();
			listNCC = nccDao.getdsNhaCungCap();
			listMaNCC = new ArrayList<String>();

			thuocDao = new ThuocDaoImpl();
			listMaThuoc = new ArrayList<String>();

			loaiDao = new LoaiThuocDaoImpl();
			listLoai = loaiDao.getdsLoaiThuoc();
			listMaLoai = new ArrayList<String>();

			nuocDao = new NuocDaoImpl();
			listNuoc = nuocDao.getdsNuocSX();
			listMaNuoc = new ArrayList<String>();

			loadNCCVaoTree(listNCC);
			tree.setSelectionRow(0);
			loadLoaiVaoCmb(listLoai);
			loadNuocVaoCmb(listNuoc);

		} catch (RemoteException e1) {
			e1.printStackTrace();
		}
	}

	private void xoaRong() {
		txtTen.setText("");
		txtSoLuong.setText("");
		txtDonGia.setText("");
		cmbLoai.setSelectedIndex(0);
		cmbNSX.setSelectedIndex(0);
		cmbNgaySX.setSelectedIndex(0);
		cmbThangSX.setSelectedIndex(0);
		cmbNamSX.setSelectedIndex(0);
		cmbNgayHSD.setSelectedIndex(0);
		cmbThangHSD.setSelectedIndex(0);
		cmbNamHSD.setSelectedIndex(0);
		txtTen.requestFocus();
	}

	private Thuoc getThuoc() {
		try {
			String ten = txtTen.getText();
			int index = tree.getSelectionRows()[0];
			NhaCungCap ncc = listNCC.get(index - 1);
			LoaiThuoc loai = listLoai.get(cmbLoai.getSelectedIndex());
			NuocSX nuoc = listNuoc.get(cmbNSX.getSelectedIndex());
			LocalDate NSX = getDate(cmbNgaySX, cmbThangSX, cmbNamSX);
			LocalDate HSD = getDate(cmbNgayHSD, cmbThangHSD, cmbNamHSD);
			int soLuong = Integer.parseInt(txtSoLuong.getText());
			double dongia = Double.parseDouble(txtDonGia.getText());
			return new Thuoc(ten, dongia, NSX, HSD, soLuong, ncc, loai, nuoc);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	private LocalDate getDate(JComboBox ngay, JComboBox thang, JComboBox nam) {
		int ng = Integer.parseInt(ngay.getSelectedItem().toString());
		String word[] = thang.getSelectedItem().toString().split(" ");
		int th = Integer.parseInt(word[1]);
		int n = Integer.parseInt(nam.getSelectedItem().toString());
		return LocalDate.of(n, th, ng);
	}

	private void setDate(JComboBox ngay, JComboBox thang, JComboBox nam, LocalDate date) {
		ngay.setSelectedItem(date.getDayOfMonth() + "");
		thang.setSelectedItem("Tháng " + date.getMonthValue());
		nam.setSelectedItem(date.getYear() + "");

	}

	private int getViTriTrongList(List<String> list, String ma) {
		int index = 0;
		for (String str : list) {
			if (str.equals(ma))
				return index;
			index++;
		}
		return -1;
	}

	private void loadNuocVaoCmb(List<NuocSX> list) {
		cmbNSX.removeAllItems();
		for (NuocSX nuoc : list) {
			load1NuocVaoCmb(nuoc);
			listMaNuoc.add(nuoc.getIdNuoc());
		}
	}

	private void load1NuocVaoCmb(NuocSX nuoc) {
		cmbNSX.addItem(nuoc.getTenNuoc());
	}

	private void loadLoaiVaoCmb(List<LoaiThuoc> listLoai2) {
		cmbLoai.removeAllItems();
		for (LoaiThuoc loai : listLoai2) {
			load1LoaiVaoCmb(loai);
			listMaLoai.add(loai.getMaLoai());
		}
	}

	private void load1LoaiVaoCmb(LoaiThuoc loai) {
		cmbLoai.addItem(loai.getTenLoai());
	}

	private void loadThongTinThuoc(List<Thuoc> list) {
		ChucNang.clearDataTable(model);
		for (Thuoc thuoc : list) {
			load1ThongTinThuoc(thuoc);
			listMaThuoc.add(thuoc.getMaThuoc());
		}
	}

	private void load1ThongTinThuoc(Thuoc thuoc) {
		String[] str = { thuoc.getTenThuoc(), dtf.format(thuoc.getNgaySX()), dtf.format(thuoc.getHanSuDung()),
				thuoc.getSLTon() + "", vnFormat.format(thuoc.getDonGia()) };
		model.addRow(str);
	}

	private void loadNCCVaoTree(List<NhaCungCap> list) {
		node.removeAllChildren();
		for (NhaCungCap ncc : list) {
			load1NCCVaoTree(ncc);
			listMaNCC.add(ncc.getMaNCC());
		}
	}

	private void load1NCCVaoTree(NhaCungCap ncc) {
		DefaultMutableTreeNode tam = new DefaultMutableTreeNode(ncc.getTenNCC());
		node.add(tam);
	}
}
