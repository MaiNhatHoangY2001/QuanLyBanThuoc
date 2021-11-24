package app_QLT;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import chucNang.Regex;
import connectDB.ConnectDB;
import dao.ThemNCC_DAO;
import entity.NhaCungCap;

public class frmThemNCC extends JFrame implements ActionListener, MouseListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private DefaultTableModel dm;
	private JTable tb;
	private JTextField txtMaNCC;
	private JTextField txtTenNCC;
	private JTextField txtDiaChi;
	private JButton btnThem;
	private JButton btnXoaRong;
	private JButton btnXoa;
	private JButton btnSua;
	private Regex regex;
	private ThemNCC_DAO ncc_dao;

	public frmThemNCC() {
		// TODO Auto-generated constructor stub
		setSize(700, 630);
		setTitle("Thêm nhà cung cấp");
		setLocationRelativeTo(null);
		setLayout(null);

		/**
		 * Bảng danh sách nhà cung cấp
		 */
		String truong[] = { "Mã NCC", "Tên NCC", "Địa chỉ" };
		dm = new DefaultTableModel(truong, 0);
		tb = new JTable(dm);
		JScrollPane thanhTruoc = new JScrollPane(tb);
		thanhTruoc.setBounds(20, 30, 645, 250);
		add(thanhTruoc);

		/**
		 * Các lable
		 */
		JLabel lblMaNCC = new JLabel("Mã nhà cung cấp:");
		lblMaNCC.setBounds(100, 330, 120, 30);
		add(lblMaNCC);
		JLabel lblTenNCC = new JLabel("Tên nhà cung cấp:");
		lblTenNCC.setBounds(100, 370, 120, 30);
		add(lblTenNCC);
		JLabel lblDiaChi = new JLabel("Địa chỉ:");
		lblDiaChi.setBounds(100, 410, 120, 20);
		add(lblDiaChi);

		/**
		 * Các JTextField
		 */
		txtMaNCC = new JTextField();
		txtMaNCC.setBounds(220, 330, 360, 30);
		add(txtMaNCC);
		txtTenNCC = new JTextField();
		txtTenNCC.setBounds(220, 370, 360, 30);
		add(txtTenNCC);
		txtDiaChi = new JTextField();
		txtDiaChi.setBounds(220, 410, 360, 30);
		add(txtDiaChi);

		/**
		 * Các button
		 */
		btnThem = new JButton("Thêm");
		btnThem.setBounds(105, 530, 100, 35);
		btnThem.setBackground(new Color(191, 247, 249));
		btnThem.setForeground(Color.DARK_GRAY);
		btnThem.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
		add(btnThem);
		
		btnXoaRong = new JButton("Xóa Rỗng");
		btnXoaRong.setBounds(230, 530, 100, 35);
		btnXoaRong.setBackground(new Color(191, 247, 249));
		btnXoaRong.setForeground(Color.DARK_GRAY);
		btnXoaRong.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
		add(btnXoaRong);
		
		btnXoa = new JButton("Xóa");
		btnXoa.setBounds(355, 530, 100, 35);
		btnXoa.setBackground(new Color(191, 247, 249));
		btnXoa.setForeground(Color.DARK_GRAY);
		btnXoa.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
		add(btnXoa);
		
		btnSua = new JButton("Sửa");
		btnSua.setBounds(480, 530, 100, 35);
		btnSua.setBackground(new Color(191, 247, 249));
		btnSua.setForeground(Color.DARK_GRAY);
		btnSua.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
		add(btnSua);

		/**
		 * set bordertitle cho bảng
		 */
		JPanel pBang = new JPanel();
		pBang.setBorder(BorderFactory.createTitledBorder("Danh sách các nhà cung cấp"));
		pBang.setBounds(10, 10, 665, 280);
		pBang.setBackground(new Color(248,248,248));
		add(pBang);

		JPanel pChucNang = new JPanel();
		pChucNang.setBorder(BorderFactory.createTitledBorder("Chức năng"));
		pChucNang.setBounds(10, 300, 665, 280);
		pChucNang.setBackground(new Color(248,248,248));
		add(pChucNang);

		btnThem.addActionListener(this);
		btnXoaRong.addActionListener(this);
		btnXoa.addActionListener(this);
		btnSua.addActionListener(this);
		tb.addMouseListener(this);

		/**
		 * DataBase
		 */
		try {
			ConnectDB.getInstance().connect();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ncc_dao = new ThemNCC_DAO();
		// Kết nối Database
		DocDuLieuDatabase();
		
		regex = new Regex();
	}

	private void DocDuLieuDatabase() {
		ArrayList<NhaCungCap> list = ncc_dao.getalltbNhaCungCap();
		for (NhaCungCap ncc : list) {
			dm.addRow(new Object[] { ncc.getMaNCC(), ncc.getTenNCC(), ncc.getDiaChi() });
		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o.equals(btnThem)) {
			if (kiemTra()) {
				String maNCC = txtMaNCC.getText();
				String tenNCC = txtTenNCC.getText();
				String diaChi = txtDiaChi.getText();
				NhaCungCap ncc = new NhaCungCap(maNCC, tenNCC, diaChi);
				if (ncc_dao.create(ncc)) {
					dm.addRow(new Object[] { ncc.getMaNCC(), ncc.getTenNCC(), ncc.getDiaChi() });
				} else
					JOptionPane.showMessageDialog(this, "Trùng mã nhà cung cấp");
			}
		} else if (o.equals(btnXoaRong)) {
			txtMaNCC.setText("");
			txtTenNCC.setText("");
			txtDiaChi.setText("");
			txtMaNCC.requestFocus();
		} else if (o.equals(btnXoa)) {
			if (tb.getSelectedRow() == -1) {
				JOptionPane.showMessageDialog(this, "Hãy chọn nhà cung cấp cần xóa");
			} else {
				int tl;
				tl = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xóa nhà cung cấp này không ?",
						"Cảnh báo", JOptionPane.YES_OPTION);
				if (tl == JOptionPane.YES_OPTION) {
					int index = tb.getSelectedRow();
					ncc_dao.xoa(dm.getValueAt(tb.getSelectedRow(), 0).toString());
					dm.removeRow(index);
				}
			}

		} else if (o.equals(btnSua)) {
			if (kiemTra()) {
				String maNCC = txtMaNCC.getText();
				String tenNCC = txtTenNCC.getText();
				String diaChi = txtDiaChi.getText();
				NhaCungCap ncc = new NhaCungCap(maNCC, tenNCC, diaChi);
				if (ncc_dao.update(ncc)) {
					clearTable();
					DocDuLieuDatabase();
				} else
					JOptionPane.showMessageDialog(this, "Trùng mã nhà cung cấp");
			}
		}
	}

	private boolean kiemTra() {
		if (regex.kiemTraRong(txtMaNCC))
			return false;
		if (regex.RegexMaNCC(txtMaNCC))
			return false;
		if (regex.kiemTraRong(txtTenNCC))
			return false;
		if (regex.RegexTen(txtTenNCC))
			return false;
		if (regex.kiemTraRong(txtDiaChi))
			return false;
		if (regex.RegexDiaChi(txtDiaChi))
			return false;
		return true;
	}

	private void clearTable() {
		while (tb.getRowCount() > 0) {
			dm.removeRow(0);
		}

	}

	@Override
	public void mouseClicked(MouseEvent e) {

		int row = tb.getSelectedRow();
		txtMaNCC.setText(dm.getValueAt(row, 0).toString());
		txtTenNCC.setText(dm.getValueAt(row, 1).toString());
		txtDiaChi.setText(dm.getValueAt(row, 2).toString());

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
