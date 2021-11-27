package frm;

import javax.swing.JFrame;
import javax.swing.JPanel;

import chucNang.Regex;
import chucNang.RoundedPanel;
import dao.KhachHangDao;
import dao.NhaCungCapDao;
//import dao.impl.KhachHangDaoImpl;
//import dao.impl.NhaCungCapDaoImpl;
import entity.KhachHang;
import entity.NhaCungCap;
import entity.NuocSX;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;

import app.App;

import javax.swing.JTextField;
import java.awt.Insets;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.Cursor;
import javax.swing.SwingConstants;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.rmi.Naming;
import java.rmi.RemoteException;

public class FrmThemNCC extends JFrame implements ActionListener, MouseListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5885586881253507338L;
	private JTextField txtTen;
	private JButton btnLuu;
	private JButton btnXoa, btnSua, btnXoaRong;
	private JTextField txtMa;
	private DefaultTableModel tableModel;
	private JTable table;
	private JTextField txtDiaChi;

	/**
	 * Create the frame.
	 */
	public FrmThemNCC() {

//		SecurityManager securityManager=System.getSecurityManager();
//		if(securityManager==null) {
//			System.setProperty("java.security.policy", "policy/policy.policy");
//			System.setSecurityManager(new SecurityManager());
//		}
//		try {
//			//khDao = new KhachHangDaoImpl();
//			nhaCungCapDao=(NhaCungCapDao) Naming.lookup("rmi://192.168.1.7:9999/nhaCungCapDao");
//		} catch (Exception e1) {
//			e1.printStackTrace();
//		}

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				int tl = JOptionPane.showConfirmDialog(null, "Bạn có chắc muốn thoát không?", "Thông báo hủy bỏ",
						JOptionPane.YES_NO_OPTION);
				if (tl == JOptionPane.YES_OPTION) {
					dispose();
				}
			}
		});
		setSize(1016, 839);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		setResizable(false);

		RoundedPanel pnlInputKH = new RoundedPanel();
		pnlInputKH.setBackground(Color.WHITE);
		pnlInputKH.setShady(false);
		pnlInputKH.setBounds(85, 130, 830, 540);
		getContentPane().add(pnlInputKH);
		pnlInputKH.setLayout(null);

		JLabel lblTileInput = new JLabel("Nhập thông tin loại thuốc");
		lblTileInput.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblTileInput.setBounds(20, 10, 320, 40);
		pnlInputKH.add(lblTileInput);

		JPanel pnlInput = new JPanel();
		pnlInput.setBackground(Color.WHITE);
		pnlInput.setBorder(new MatteBorder(2, 0, 0, 0, (Color) new Color(0, 0, 0)));
		pnlInput.setBounds(20, 61, 790, 477);
		pnlInputKH.add(pnlInput);
		pnlInput.setLayout(null);

		JLabel lblTen = new JLabel("Tên nhà cung cấp:");
		lblTen.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblTen.setBounds(0, 87, 175, 40);
		pnlInput.add(lblTen);

		txtTen = new JTextField();
		txtTen.setMargin(new Insets(2, 14, 2, 2));
		txtTen.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtTen.setBounds(185, 88, 594, 40);
		pnlInput.add(txtTen);
		txtTen.setColumns(10);

		JLabel lblMLoiThuc = new JLabel("Mã nhà cung cấp:");
		lblMLoiThuc.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblMLoiThuc.setBounds(0, 22, 175, 40);
		pnlInput.add(lblMLoiThuc);

		txtMa = new JTextField();
		txtMa.setMargin(new Insets(2, 14, 2, 2));
		txtMa.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtMa.setColumns(10);
		txtMa.setBounds(185, 22, 594, 40);
		pnlInput.add(txtMa);

		String column[] = { "Mã nhà cung cấp", "Tên nhà cung cấp", "Địa chỉ" };
		tableModel = new DefaultTableModel(column, 0);
		table = new JTable(tableModel);
		table.setRowHeight(20);
		table.getTableHeader().setFont(new Font("Times New Roman", Font.BOLD, 20));
		table.setRowHeight(40);
		table.setFont(new Font("Times New Roman", Font.BOLD, 20));
		JScrollPane scroll;
		scroll = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scroll.setBounds(10, 218, 769, 249);
		scroll.setBackground(new Color(248, 248, 248));
		scroll.setBorder(BorderFactory.createTitledBorder("Danh sách nhà cung cấp"));
		pnlInput.add(scroll);

		JLabel lblaCh = new JLabel("Địa chỉ:");
		lblaCh.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblaCh.setBounds(0, 156, 175, 40);
		pnlInput.add(lblaCh);

		txtDiaChi = new JTextField();
		txtDiaChi.setMargin(new Insets(2, 14, 2, 2));
		txtDiaChi.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtDiaChi.setColumns(10);
		txtDiaChi.setBounds(185, 156, 594, 40);
		pnlInput.add(txtDiaChi);

		JPanel pnlTrang = new JPanel();
		pnlTrang.setBackground(Color.WHITE);
		pnlTrang.setBounds(0, 400, 1000, 400);
		getContentPane().add(pnlTrang);
		pnlTrang.setLayout(null);

		btnLuu = new JButton("Thêm");
		btnLuu.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnLuu.setForeground(Color.WHITE);
		btnLuu.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnLuu.setBackground(new Color(20, 140, 255));
		btnLuu.setBounds(35, 298, 144, 50);
		pnlTrang.add(btnLuu);

		btnXoa = new JButton("Xóa");
		btnXoa.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnXoa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				xoaRong();
			}
		});
		btnXoa.setForeground(Color.WHITE);
		btnXoa.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnXoa.setBackground(new Color(20, 140, 255));
		btnXoa.setBounds(273, 298, 144, 50);
		pnlTrang.add(btnXoa);

		btnSua = new JButton("Sửa");
		btnSua.setForeground(Color.WHITE);
		btnSua.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnSua.setBackground(new Color(20, 140, 255));
		btnSua.setBounds(527, 298, 158, 50);
		pnlTrang.add(btnSua);

		btnXoaRong = new JButton("Làm mới");
		btnXoaRong.setForeground(Color.WHITE);
		btnXoaRong.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnXoaRong.setBackground(new Color(20, 140, 255));
		btnXoaRong.setBounds(776, 298, 158, 50);
		pnlTrang.add(btnXoaRong);

		JPanel pnlXanh = new JPanel();
		pnlXanh.setBackground(new Color(20, 140, 255));
		pnlXanh.setBounds(0, 0, 1000, 400);
		getContentPane().add(pnlXanh);
		pnlXanh.setLayout(null);

		JLabel lblTitle = new JLabel("Thêm Thông Tin Loại Thuốc");
		lblTitle.setForeground(Color.WHITE);
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 40));
		lblTitle.setBounds(209, 30, 582, 73);
		pnlXanh.add(lblTitle);
//		
//		try {
//			nhaCungCapDao=new NhaCungCapDaoImpl();
//		} catch (RemoteException e1) {
//			e1.printStackTrace();
//		}

		try {
			loadAllNCC();
		} catch (RemoteException e1) {
			e1.printStackTrace();
		}

		table.addMouseListener(this);
		btnLuu.addActionListener(this);
		btnSua.addActionListener(this);
		btnXoa.addActionListener(this);
		btnXoaRong.addActionListener(this);

	}

	/**
	 * Xóa rỗng các JTextField và default selected JButtonRadius và JComboBox
	 */
	public void xoaRong() {
		txtTen.setText("");
		txtMa.setText("");
		txtDiaChi.setText("");
		txtTen.requestFocus();
	}

	/**
	 * Kiem tra du lieu co chinh xac hay khong
	 * 
	 * @return boolean
	 */
	private boolean kiemTraThongTin() {
		Regex r = new Regex();
		if (r.RegexTen(txtTen))
			return false;
		if (r.kiemTraRong(txtTen))
			return false;
		if (r.kiemTraRong(txtDiaChi))
			return false;
		if (r.kiemTraRong(txtDiaChi))
			return false;
		return true;
	}

	/**
	 * Viet hoa chu cai dau tien
	 * 
	 * @param Word
	 * @return String
	 */
	private String capitalizer(String Word) {
		String[] words = Word.split(" ");
		StringBuilder sb = new StringBuilder();
		if (words[0].length() > 0) {
			sb.append(Character.toUpperCase(words[0].charAt(0))
					+ words[0].subSequence(1, words[0].length()).toString().toLowerCase());
			for (int i = 1; i < words.length; i++) {
				sb.append(" ");
				sb.append(Character.toUpperCase(words[i].charAt(0))
						+ words[i].subSequence(1, words[i].length()).toString().toLowerCase());
			}
		}
		return sb.toString();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		int row = table.getSelectedRow();
		txtMa.setText(tableModel.getValueAt(row, 0).toString());
		txtTen.setText(tableModel.getValueAt(row, 1).toString());
		txtDiaChi.setText(tableModel.getValueAt(row, 2).toString());
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
		Object o = e.getSource();
		if (o.equals(btnLuu)) {
			if (kiemTraThongTin()) {
				// String ma=txtMa.getText();
				String ten = txtTen.getText();
				String dc = txtDiaChi.getText();
				NhaCungCap ncc = new NhaCungCap(ten, dc);
				try {
					App.nccDao.themNCC(ncc);
					JOptionPane.showMessageDialog(this, "Thêm thành công");
					clearTable();
					loadAllNCC();
				} catch (RemoteException e1) {
					e1.printStackTrace();
				}

			}

		}
		if (o.equals(btnSua)) {
			if (kiemTraThongTin()) {
				String ma = txtMa.getText();
				String ten = txtTen.getText();
				String dc = txtDiaChi.getText();
				NhaCungCap ncc = new NhaCungCap(ma, ten, dc);
				int tl;
				tl = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn sửa nhà cung cấp này không ?",
						"Cảnh báo", JOptionPane.YES_OPTION);
				if (tl == JOptionPane.YES_OPTION) {
					try {
						App.nccDao.updateNCC(ncc);
						JOptionPane.showMessageDialog(this, "Thông tin nhà cung cấp đã được cập nhật");
						clearTable();
						loadAllNCC();
					} catch (RemoteException e1) {
						e1.printStackTrace();
					}
				} else
					JOptionPane.showMessageDialog(this, "Đã hủy");
			}
		}
		if (o.equals(btnXoa)) {
			if (table.getSelectedRow() == -1) {
				JOptionPane.showMessageDialog(this, "Hãy chọn nhà cung cấp cần xóa");
			} else {
				int tl;
				tl = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xóa nhà cung cấp này không ?",
						"Cảnh báo", JOptionPane.YES_OPTION);
				if (tl == JOptionPane.YES_OPTION) {
					int index = table.getSelectedRow();
					try {
						App.nccDao.xoaNCC(tableModel.getValueAt(index, 0).toString());
						clearTable();
						loadAllNCC();
					} catch (RemoteException e1) {
						e1.printStackTrace();
					}
				}
			}
		}
		if (o.equals(btnXoaRong)) {
			xoaRong();
		}

	}

	private void loadAllNCC() throws RemoteException {
		List<NhaCungCap> ds = App.nccDao.getdsNhaCungCap();
		for (NhaCungCap n : ds) {
			tableModel.addRow(new Object[] { n.getMaNCC(), n.getTenNCC(), n.getDiaChi() });
		}
	}

	private void clearTable() {
		while (table.getRowCount() > 0) {
			tableModel.removeRow(0);
		}
	}
}
