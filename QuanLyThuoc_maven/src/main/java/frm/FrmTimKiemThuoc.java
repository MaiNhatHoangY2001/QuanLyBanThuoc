package frm;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import connectDB.ConnectDB;
import dao.TThuocT_Dao;
import dao.Thuoc_DAO;
import entity.Thuoc;

public class FrmTimKiemThuoc extends JFrame implements ActionListener {

	private DefaultTableModel modelThuoc;
	private JTable tableThuoc;
	private JRadioButton radAll;
	private JRadioButton radConHan;
	private JRadioButton radHetHan;
	private JComboBox<String> cboLoaiTimKiem;
	private JTextField txtThongTinTimKiem;
	private JButton btnTim;
	private Thuoc_DAO dao_Thuoc;
	private DecimalFormat df = new DecimalFormat("#");
	private TThuocT_Dao thuoc;

	public FrmTimKiemThuoc() {
		// TODO Auto-generated constructor stub
		setTitle("Tim kiếm thuốc");
		setSize(800, 450);
		setLocationRelativeTo(null);
		setResizable(false);

		/**
		 * Phần Bảng danh sách thuốc
		 */
		JPanel pBang = new JPanel(new GridLayout(1, 1));
		pBang.setBorder(BorderFactory.createTitledBorder("Danh sách thuốc"));
		String header[] = { "Mã Thuốc", "Tên thuốc", "Đơn giá", "NSX", "HSD", "SLT", "Mã NCC", "Mã Loại", "Id Nước" };
		modelThuoc = new DefaultTableModel(header, 0);
		tableThuoc = new JTable(modelThuoc);

		JScrollPane thanhCuon = new JScrollPane(tableThuoc);
		thanhCuon.setBackground(Color.white);
		pBang.add(thanhCuon);

		/**
		 * Phần tìm kiếm
		 */
		JPanel ptim = new JPanel();
		ptim.setLayout(null);
		ptim.setBorder(BorderFactory.createTitledBorder("Các chức năng"));

		/**
		 * Tình trạng thuốc
		 */
		JPanel ptinhTrang = new JPanel();
		ptinhTrang.setLayout(null);
		ptinhTrang.setBorder(BorderFactory.createTitledBorder("Tình trạng thuốc"));
		ptinhTrang.setBackground(new Color(248, 248, 248));
		ptinhTrang.setBounds(60, 20, 200, 140);
		ptim.add(ptinhTrang);

		radAll = new JRadioButton("Tất cả");
		radAll.setSelected(true);
		radAll.setBounds(20, 20, 150, 30);
		radAll.setBackground(new Color(248, 248, 248));
		ptinhTrang.add(radAll);

		radConHan = new JRadioButton("Còn hạn sử dụng");
		radConHan.setBounds(20, 60, 150, 30);
		radConHan.setBackground(new Color(248, 248, 248));
		ptinhTrang.add(radConHan);

		radHetHan = new JRadioButton("Hết hạn sử dụng");
		radHetHan.setBounds(20, 100, 150, 30);
		radHetHan.setBackground(new Color(248, 248, 248));
		ptinhTrang.add(radHetHan);

		ButtonGroup group = new ButtonGroup();
		group.add(radAll);
		group.add(radConHan);
		group.add(radHetHan);

		/**
		 * Phần nhập thông tin tim kiếm
		 */
		JPanel pInput = new JPanel();
		pInput.setLayout(null);
		pInput.setBorder(BorderFactory.createTitledBorder("Tình trạng thuốc"));
		pInput.setBackground(new Color(248, 248, 248));
		pInput.setBounds(280, 20, 450, 140);
		ptim.add(pInput);

		// lbl Tìm kiếm
		JLabel lblTimLoai = new JLabel("Chọn loại Tìm kiếm:");
		lblTimLoai.setBounds(40, 20, 200, 30);
		pInput.add(lblTimLoai);

		JLabel lblNhapTim = new JLabel("Nhập thông tin cần tìm:");
		lblNhapTim.setBounds(40, 60, 150, 30);
		pInput.add(lblNhapTim);

		// lbl chức năng
		String[] strLoaiTim = { "Tìm kiếm theo mã Thuốc", "Tìm kiếm theo tên thuốc" };
		cboLoaiTimKiem = new JComboBox<String>(strLoaiTim);
		cboLoaiTimKiem.setBounds(200, 20, 200, 30);
		pInput.add(cboLoaiTimKiem);

		txtThongTinTimKiem = new JTextField();
		txtThongTinTimKiem.setBounds(200, 60, 200, 30);
		pInput.add(txtThongTinTimKiem);

		btnTim = new JButton("Tìm");
		btnTim.setBounds(180, 100, 90, 30);
		btnTim.setBackground(new Color(191, 247, 249));
		btnTim.setForeground(Color.DARK_GRAY);
		btnTim.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
		pInput.add(btnTim);

		// Phần phân chia frm
		JSplitPane TrenDuoi = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		TrenDuoi.setDividerLocation(230);
		TrenDuoi.setTopComponent(pBang);
		TrenDuoi.setBottomComponent(ptim);
		add(TrenDuoi);

		/**
		 * sự kiện
		 */
		
		try {
			ConnectDB.getInstance().connect();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		thuoc = new TThuocT_Dao();
		
		dao_Thuoc = new Thuoc_DAO();
		ArrayList<Thuoc> ds = dao_Thuoc.getAllThuoc();
		loadHetDuLieuVaoBang(ds);
		
		radAll.addActionListener(this);
		radConHan.addActionListener(this);
		radHetHan.addActionListener(this);
		btnTim.addActionListener(this);
	}

	private void loadHetDuLieuVaoBang(ArrayList<Thuoc> dsThuoc) {
		// TODO Auto-generated method stub
		DefaultTableModel tam = (DefaultTableModel) tableThuoc.getModel();
		tam.getDataVector().removeAllElements();
			for (Thuoc thuoc : dsThuoc) {
				String []n = {thuoc.getMaThuoc(), thuoc.getTenThuoc(), df.format(thuoc.getDonGia()) + "", thuoc.getNgaySX() + "", thuoc.getHanSuDung() + "", thuoc.getSLTon() + "", thuoc.getLoaiThuoc().getMaLoai(), thuoc.getNcc().getMaNCC(),thuoc.getNuocSX().getIdNuoc()};
				modelThuoc.addRow(n);
			}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		if (o.equals(radAll)) {
			ArrayList<Thuoc> dsThuoc = dao_Thuoc.getAllThuoc();
			loadHetDuLieuVaoBang(dsThuoc);
		} else if (o.equals(radConHan)) {
			loadThuocConHanVaoBang();
		} else if (o.equals(radHetHan)) {
			loadThuocHetHanVaoBang();
		} else if (o.equals(btnTim)) {
			timThuoc();
		}
	}

	private void timThuoc() {
		// TODO Auto-generated method stub
		ArrayList<Thuoc> ds;
		if (cboLoaiTimKiem.getSelectedIndex() == 0) {
			ds = thuoc.getThuocTheoMaThuoc(txtThongTinTimKiem.getText());
		} else {
			ds = thuoc.getThuocTheoTenThuoc(txtThongTinTimKiem.getText());
		}
		
		if (!ds.isEmpty()) {
			loadHetDuLieuVaoBang(ds);
		} else {
			JOptionPane.showMessageDialog(this, "Không tìm thấy");
			ArrayList<Thuoc> dst = dao_Thuoc.getAllThuoc();
			loadHetDuLieuVaoBang(dst);
		}
	}

	private void loadThuocHetHanVaoBang() {
		// TODO Auto-generated method stub
		ArrayList<Thuoc> dsThuoc = dao_Thuoc.getAllThuoc();
		ArrayList<Thuoc> tam = new ArrayList<Thuoc>();
		for (Thuoc thuoc : dsThuoc) {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDate date = LocalDate.parse(thuoc.getHanSuDung().toString(), formatter);
			LocalDate now = LocalDate.now();
			if (now.isAfter(date)) {
				tam.add(thuoc);
			}
		}
		loadHetDuLieuVaoBang(tam);
	}

	private void loadThuocConHanVaoBang() {
		// TODO Auto-generated method stub
		ArrayList<Thuoc> dsThuoc = dao_Thuoc.getAllThuoc();
		ArrayList<Thuoc> tam = new ArrayList<Thuoc>();
		for (Thuoc thuoc : dsThuoc) {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDate date = LocalDate.parse(thuoc.getHanSuDung().toString(), formatter);
			LocalDate now = LocalDate.now();
			if (date.isAfter(now) || date.isEqual(now)) {
				tam.add(thuoc);
			}
		}
		loadHetDuLieuVaoBang(tam);
	}

}
