package app_QLT;

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
import dao.LoaiThuoc1_DAO;
import entity.LoaiThuoc;
import entity.Regex;
import entity.Regex1;

public class frmThemLoai extends JFrame implements ActionListener, MouseListener{
	
	private DefaultTableModel model;
	private JTable table;
	private JTextField txtmaLoai;
	private JTextField txtTenLoai;
	private JButton btnThem;
	private JButton btnXoaRong;
	private JButton btnXoa;
	private JButton btnSua;
	private LoaiThuoc1_DAO dao_Loai;
	private Regex1 regex;

	public frmThemLoai() {
		// TODO Auto-generated constructor stub
		setTitle("Thêm Loai Thuốc");
		setSize(400,400);
		setLocationRelativeTo(null);
		setResizable(false);
		
		setLayout(null);
		
		JPanel pThongTin = new JPanel(new GridLayout(1,1));
		pThongTin.setBorder(BorderFactory.createTitledBorder("Thông tin loại thuốc"));
		pThongTin.setBounds(0, 0, 385, 180);
		add(pThongTin);
		
		String []header = {"Mã loại", "Tên Loại Thuốc"};
		model = new DefaultTableModel(header, 0);
		table = new JTable(model);
		JScrollPane thanh = new JScrollPane(table);
		pThongTin.add(thanh);
		
		
		
		JPanel pinput = new JPanel();
		pinput.setBorder(BorderFactory.createTitledBorder("Nhập thuốc"));
		pinput.setBounds(0, 180, 385, 180);
		pinput.setLayout(null);
		add(pinput);
		
		JLabel lblMa = new JLabel("Mã Loại:");
		lblMa.setBounds(50, 30, 100, 30);
		pinput.add(lblMa);
		
		JLabel lblTen = new JLabel("Tên loại:");
		lblTen.setBounds(50, 80, 100, 30);
		pinput.add(lblTen);
		
		txtmaLoai = new JTextField();
		txtmaLoai.setBounds(110, 30, 220, 30);
		pinput.add(txtmaLoai);
		txtTenLoai = new JTextField();
		txtTenLoai.setBounds(110, 80, 220, 30);
		pinput.add(txtTenLoai);
		btnThem = new JButton("Thêm");
		btnThem.setBounds(10, 130, 80, 30);
		pinput.add(btnThem);
		btnXoaRong = new JButton("Xóa rổng");
		btnXoaRong.setBounds(100, 130, 90, 30);
		pinput.add(btnXoaRong);
		btnXoa = new JButton("Xóa");
		btnXoa.setBounds(200, 130, 80, 30);
		pinput.add(btnXoa);
		btnSua = new JButton("Sửa");
		btnSua.setBounds(290, 130, 85, 30);
		pinput.add(btnSua);
		
		try {
			ConnectDB.getInstance().connect();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		dao_Loai = new LoaiThuoc1_DAO();
		regex = new Regex1();
		
		ArrayList<LoaiThuoc> ds = dao_Loai.getalltbLoaiThuoc();
		loadHetDuLieuVaoList(ds);
		
		table.addMouseListener(this);
		btnThem.addActionListener(this);
		btnXoaRong.addActionListener(this);
		btnXoa.addActionListener(this);
		btnSua.addActionListener(this);
	}

	private void loadHetDuLieuVaoList(ArrayList<LoaiThuoc> ds) {
		// TODO Auto-generated method stub
		DefaultTableModel tam = (DefaultTableModel) table.getModel();
		tam.getDataVector().removeAllElements();
		for (LoaiThuoc l : ds) {
			String[] row = {l.getMaLoai(), l.getTenLoai()};
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
		txtmaLoai.setText(table.getValueAt(row, 0).toString());
		txtTenLoai.setText(table.getValueAt(row, 1).toString());
		txtmaLoai.setEnabled(false);
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
			if (!regex.RegexTenLoaiThuoc(txtTenLoai.getText())) {
				JOptionPane.showMessageDialog(this, "Lỗi nhập! Vui lòng nhập đúng cú pháp \n Viết Hoa chữ cái đầu (ví dụ: Thuốc cảm lạnh)");
				txtTenLoai.selectAll();
				txtTenLoai.requestFocus();
			} else {
				String maLoai = txtmaLoai.getText();
				String tenLoai = txtTenLoai.getText();
				LoaiThuoc l = new LoaiThuoc(maLoai, tenLoai);
				
				if (dao_Loai.update(l)) {
					ArrayList<LoaiThuoc> ds = dao_Loai.getalltbLoaiThuoc();
					loadHetDuLieuVaoList(ds);
				}
			}
		}
	}

	private void xoaRong() {
		// TODO Auto-generated method stub
		txtmaLoai.setText("");
		txtTenLoai.setText("");
		txtmaLoai.setEnabled(true);
		txtmaLoai.requestFocus();
	}

	private void xoa() {
		// TODO Auto-generated method stub
		if(table.getSelectedRow() == -1) {
			JOptionPane.showMessageDialog(this, "Hãy chọn loại thuốc cần xóa");
		} else {
			int tl = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xóa Loại Thuốc này không ?", "Cảnh báo",
					JOptionPane.YES_OPTION);
			if (tl == JOptionPane.YES_OPTION) {
				while (table.getSelectedRowCount() > 0) {
					int index = table.getSelectedRow();
					dao_Loai.xoa(table.getValueAt(index, 0).toString());
					model.removeRow(index);
				}
			}
		}
	}

	private void Them() {
		// TODO Auto-generated method stub
		if(!txtmaLoai.getText().equals("") && !txtTenLoai.getText().equals("")) {
			if(!regex.RegexMaLoai(txtmaLoai.getText())) {
				JOptionPane.showMessageDialog(this, "Lỗi nhập! Vui lòng nhập đúng cú pháp \n LTXXX (ví dụ: LT000)");
				txtmaLoai.selectAll();
				txtmaLoai.requestFocus();
			} else if (!regex.RegexTenLoaiThuoc(txtTenLoai.getText())) {
				JOptionPane.showMessageDialog(this, "Lỗi nhập! Vui lòng nhập đúng cú pháp \n Viết Hoa chữ cái đầu (ví dụ: Thuốc cảm lạnh)");
				txtTenLoai.selectAll();
				txtTenLoai.requestFocus();
			} else {
				String maLoai = txtmaLoai.getText();
				String tenLoai = txtTenLoai.getText();
				LoaiThuoc l = new LoaiThuoc(maLoai, tenLoai);
				
				if(dao_Loai.create(l)) {
					String []row  = {l.getMaLoai(), l.getTenLoai()};
					model.addRow(row);
				} else {
					JOptionPane.showMessageDialog(this, "Lỗi trùng mã");
				}
			}
		}
	}

}
