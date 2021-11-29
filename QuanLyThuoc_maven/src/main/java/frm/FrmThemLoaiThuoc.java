package frm;

import javax.swing.JFrame;
import javax.swing.JPanel;

import chucNang.Regex;
import chucNang.RoundedPanel;
import dao.KhachHangDao;
import dao.LoaiThuocDao;
//import dao.impl.KhachHangDaoImpl;
//import dao.impl.LoaiThuocDaoImpl;
import entity.KhachHang;
import entity.LoaiThuoc;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
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

public class FrmThemLoaiThuoc extends JFrame implements ActionListener, MouseListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5885586881253507338L;
	private JTextField txtTen;
	private JButton btnLuu;
	private JButton btnXoa, btnSua, btnXoaRong;
	private DefaultTableModel modelLoaiThuoc;
	private JTable tableLoaiThuoc;

	/**
	 * Create the frame.
	 */
	public FrmThemLoaiThuoc() {
//		SecurityManager securityManager=System.getSecurityManager();
//		if(securityManager==null) {
//			System.setProperty("java.security.policy", "policy/policy.policy");
//			System.setSecurityManager(new SecurityManager());
//		}
//		try {
//			//khDao = new KhachHangDaoImpl();
//			//loaiThuocDao=new LoaiThuocDaoImpl();
//			loaiThuocDao=(LoaiThuocDao) Naming.lookup("rmi://192.168.1.7:9999/loaiThuocDao");
//			khDao=(KhachHangDao) Naming.lookup("rmi://192.168.1.7:9999/khachHangDao");
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
		setTitle("Ứng dụng quản lý cửa hàng thuốc Hoàng Long");
		setIconImage(new ImageIcon("icon/thuoc _navbar.png").getImage());
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

		JLabel lblTen = new JLabel("Tên loại thuốc:");
		lblTen.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblTen.setBounds(0, 45, 175, 40);
		pnlInput.add(lblTen);

		txtTen = new JTextField();
		txtTen.setMargin(new Insets(2, 14, 2, 2));
		txtTen.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtTen.setBounds(185, 45, 605, 40);
		pnlInput.add(txtTen);
		txtTen.setColumns(10);

		String column[] = { "Mã loại thuốc", "Tên loại thuốc" };
		modelLoaiThuoc = new DefaultTableModel(column, 0);
		tableLoaiThuoc = new JTable(modelLoaiThuoc);
		tableLoaiThuoc.setRowHeight(20);
		tableLoaiThuoc.getTableHeader().setFont(new Font("Times New Roman", Font.BOLD, 20));
		tableLoaiThuoc.setRowHeight(40);
		tableLoaiThuoc.setFont(new Font("Times New Roman", Font.BOLD, 20));
		JScrollPane scrollLoaiThuoc;
		scrollLoaiThuoc = new JScrollPane(tableLoaiThuoc, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollLoaiThuoc.setBounds(0, 130, 790, 301);
		scrollLoaiThuoc.setBackground(new Color(248, 248, 248));
		scrollLoaiThuoc.setBorder(BorderFactory.createTitledBorder("Danh sách loại thuốc"));
		pnlInput.add(scrollLoaiThuoc);

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

		tableLoaiThuoc.addMouseListener(this);
		btnLuu.addActionListener(this);
		btnSua.addActionListener(this);
		btnXoa.addActionListener(this);
		btnXoaRong.addActionListener(this);

		try {
			loadAllLoaiThuoc();
		} catch (RemoteException e1) {
			e1.printStackTrace();
		}
	}

	/**
	 * Xóa rỗng các JTextField và default selected JButtonRadius và JComboBox
	 */
	public void xoaRong() {
		txtTen.setText("");
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
		int row = tableLoaiThuoc.getSelectedRow();
		txtTen.setText(modelLoaiThuoc.getValueAt(row, 1).toString());

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
				String tenLoai = txtTen.getText();
				LoaiThuoc loaiThuoc = new LoaiThuoc(tenLoai);
				try {
					App.loaiDao.themLoaiThuoc(loaiThuoc);
					JOptionPane.showMessageDialog(this, "Thêm thành công");
					clearTable();
					loadAllLoaiThuoc();
				} catch (RemoteException e1) {
					e1.printStackTrace();
				}

			}

		}
		if (o.equals(btnSua)) {
			if (kiemTraThongTin()) {
				String tenLoai = txtTen.getText();
				LoaiThuoc loaiThuoc = new LoaiThuoc(tenLoai);
				int tl;
				tl = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn sửa loại thuốc này không ?", "Cảnh báo",
						JOptionPane.YES_OPTION);
				if (tl == JOptionPane.YES_OPTION) {
					try {
						App.loaiDao.updateloaiThuoc(loaiThuoc);
						JOptionPane.showMessageDialog(this, "Thông tin loại thuốc đã được cập nhật");
						clearTable();
						loadAllLoaiThuoc();
					} catch (RemoteException e1) {
						e1.printStackTrace();
					}
				} else
					JOptionPane.showMessageDialog(this, "Đã hủy");
			}
		}
		if (o.equals(btnXoa)) {
			if (tableLoaiThuoc.getSelectedRow() == -1) {
				JOptionPane.showMessageDialog(this, "Hãy chọn loại thuốc cần xóa");
			} else {
				int tl;
				tl = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xóa loại thuốc này không ?", "Cảnh báo",
						JOptionPane.YES_OPTION);
				if (tl == JOptionPane.YES_OPTION) {
					int index = tableLoaiThuoc.getSelectedRow();
					try {
						App.loaiDao.xoaLoaiThuoc(modelLoaiThuoc.getValueAt(index, 0).toString());
						clearTable();
						loadAllLoaiThuoc();
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

	private void clearTable() {
		while (tableLoaiThuoc.getRowCount() > 0) {
			modelLoaiThuoc.removeRow(0);
		}
	}

	private void loadAllLoaiThuoc() throws RemoteException {
		List<LoaiThuoc> ds = App.loaiDao.getdsLoaiThuoc();
		for (LoaiThuoc loaiThuoc : ds) {
			modelLoaiThuoc.addRow(new Object[] { loaiThuoc.getMaLoai(), loaiThuoc.getTenLoai() });
		}
	}
}
