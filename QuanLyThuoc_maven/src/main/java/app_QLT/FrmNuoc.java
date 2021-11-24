package app_QLT;

import java.awt.Color;
import java.awt.GridLayout;
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

import connectDB.ConnectDB;
import dao.NuocSX_DAO;
import entity.NuocSX;
import entity.Regex1;

public class FrmNuoc extends JFrame implements ActionListener, MouseListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private DefaultTableModel model;
	private JTable table;
	private JTextField txtMaNuoc;
	private JTextField txtTenNuoc;
	private JButton btnThem;
	private JButton btnXoaRong;
	private JButton btnXoa;
	private JButton btnSua;
	private NuocSX_DAO dao_nuoc;
	private Regex1 regex;

	public FrmNuoc() {
		// TODO Auto-generated constructor stub
		setTitle("Thêm Loai Thuốc");
		setSize(400,400);
		setLocationRelativeTo(null);
		setResizable(false);
		
		setLayout(null);
		
		JPanel pThongTin = new JPanel(new GridLayout(1,1));
		pThongTin.setBorder(BorderFactory.createTitledBorder("Thông tin nước sản xuất"));
		pThongTin.setBackground(new Color(248,248,248));
		pThongTin.setBounds(0, 0, 385, 180);
		add(pThongTin);
		
		String []header = {"Mã Nước", "Tên Nước"};
		model = new DefaultTableModel(header, 0);
		table = new JTable(model);
		JScrollPane thanh = new JScrollPane(table);
		pThongTin.add(thanh);
		
		
		
		JPanel pinput = new JPanel();
		pinput.setBorder(BorderFactory.createTitledBorder("Nhập Nước"));
		pinput.setBounds(0, 180, 385, 180);
		pinput.setBackground(new Color(248,248,248));
		pinput.setLayout(null);
		add(pinput);
		
		JLabel lblMa = new JLabel("Mã Nước:");
		lblMa.setBounds(50, 30, 100, 30);
		pinput.add(lblMa);
		
		JLabel lblTen = new JLabel("Tên Nước:");
		lblTen.setBounds(50, 80, 100, 30);
		pinput.add(lblTen);
		
		txtMaNuoc = new JTextField();
		txtMaNuoc.setBounds(110, 30, 220, 30);
		pinput.add(txtMaNuoc);
		txtTenNuoc = new JTextField();
		txtTenNuoc.setBounds(110, 80, 220, 30);
		pinput.add(txtTenNuoc);
		
		btnThem = new JButton("Thêm");
		btnThem.setBounds(10, 130, 80, 30);
		btnThem.setBackground(new Color(191, 247, 249));
		btnThem.setForeground(Color.DARK_GRAY);
		btnThem.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
		pinput.add(btnThem);
		
		btnXoaRong = new JButton("Xóa rỗng");
		btnXoaRong.setBounds(100, 130, 90, 30);
		btnXoaRong.setBackground(new Color(191, 247, 249));
		btnXoaRong.setForeground(Color.DARK_GRAY);
		btnXoaRong.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
		pinput.add(btnXoaRong);
		
		btnXoa = new JButton("Xóa");
		btnXoa.setBounds(200, 130, 80, 30);
		btnXoa.setBackground(new Color(191, 247, 249));
		btnXoa.setForeground(Color.DARK_GRAY);
		btnXoa.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
		pinput.add(btnXoa);
		
		btnSua = new JButton("Sửa");
		btnSua.setBounds(290, 130, 85, 30);
		btnSua.setBackground(new Color(191, 247, 249));
		btnSua.setForeground(Color.DARK_GRAY);
		btnSua.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
		pinput.add(btnSua);
		
		try {
			ConnectDB.getInstance().connect();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		dao_nuoc = new  NuocSX_DAO();
		regex = new Regex1();
		
		ArrayList<NuocSX> ds = dao_nuoc.getalltbNuoc();
		loadHetNuocVaoList(ds);
		
		table.addMouseListener(this);
		btnThem.addActionListener(this);
		btnXoaRong.addActionListener(this);
		btnXoa.addActionListener(this);
		btnSua.addActionListener(this);
	}

	private void loadHetNuocVaoList(ArrayList<NuocSX> ds) {
		// TODO Auto-generated method stub
		DefaultTableModel tam = (DefaultTableModel) table.getModel();
		tam.getDataVector().removeAllElements();
		for (NuocSX n : ds) {
			String[] row = {n.getIdNuoc(), n.getTenNuoc()};
			model.addRow(row);
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		int row = table.getSelectedRow();
		txtMaNuoc.setText(table.getValueAt(row, 0).toString());
		txtTenNuoc.setText(table.getValueAt(row, 1).toString());
		txtMaNuoc.setEnabled(false);
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
		if (o.equals(btnThem)) {
			Them();
		} else if (o.equals(btnXoaRong)) {
			xoaRong();
		} else if (o.equals(btnXoa)) {
			xoa();
			xoaRong();
		} else if (o.equals(btnSua)) {
			sua();
			xoaRong();
		}
	}

	private void sua() {
		// TODO Auto-generated method stub
		if(table.getSelectedRow() == -1) {
			JOptionPane.showMessageDialog(this, "Hãy chọn loại thuốc cần sửa");
		} else {
			if (!regex.RegexTenNuoc(txtTenNuoc.getText())) {
				JOptionPane.showMessageDialog(this, "Lỗi nhập! Vui lòng nhập đúng cú pháp \n Viết Hoa chữ cái đầu (ví dụ: Bỉ)");
				txtTenNuoc.selectAll();
				txtTenNuoc.requestFocus();
			} else {
				String maNuoc = txtMaNuoc.getText();
				String tenNuoc = txtTenNuoc.getText();
//				NuocSX n = new NuocSX(maNuoc, tenNuoc);
				
//				if (dao_nuoc.update(n)) {
//					ArrayList<NuocSX> ds = dao_nuoc.getalltbNuoc();
//					loadHetNuocVaoList(ds);
//				}
			}
		}
	}

	private void xoa() {
		// TODO Auto-generated method stub
		if(table.getSelectedRow() == -1) {
			JOptionPane.showMessageDialog(this, "Hãy chọn Nước cần xóa");
		} else {
			int tl = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xóa Nước này không ?", "Cảnh báo",
					JOptionPane.YES_OPTION);
			if (tl == JOptionPane.YES_OPTION) {
				while (table.getSelectedRowCount() > 0) {
					int index = table.getSelectedRow();
					dao_nuoc.xoa(table.getValueAt(index, 0).toString());
					model.removeRow(index);
				}
			}
		}
	}

	private void xoaRong() {
		// TODO Auto-generated method stub
		txtMaNuoc.setText("");
		txtTenNuoc.setText("");
		txtMaNuoc.setEnabled(true);
		txtMaNuoc.requestFocus();
	}

	private void Them() {
		// TODO Auto-generated method stub
		if(!txtMaNuoc.getText().equals("") && !txtTenNuoc.getText().equals("")) {
			if(!regex.RegexMaNuoc(txtMaNuoc.getText())) {
				JOptionPane.showMessageDialog(this, "Lỗi nhập! Vui lòng nhập đúng cú pháp \n LTXXX (ví dụ: LT000)");
				txtMaNuoc.selectAll();
				txtMaNuoc.requestFocus();
			} else if (!regex.RegexTenNuoc(txtTenNuoc.getText())) {
				JOptionPane.showMessageDialog(this, "Lỗi nhập! Vui lòng nhập đúng cú pháp \n Viết Hoa chữ cái đầu (ví dụ: Mỹ)");
				txtTenNuoc.selectAll();
				txtTenNuoc.requestFocus();
			} else {
				String maLoai = txtMaNuoc.getText();
				String tenLoai = txtTenNuoc.getText();
//				NuocSX n = new NuocSX(maLoai, tenLoai);
//				
//				if(dao_nuoc.create(n)) {
//					String []row  = {n.getIdNuoc(), n.getTenNuoc()};
//					model.addRow(row);
//				} else {
//					JOptionPane.showMessageDialog(this, "Lỗi trùng mã");
//				}
			}
		}
	}

}
