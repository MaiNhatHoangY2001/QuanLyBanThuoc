package app_QLT;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Label;
import java.time.LocalDate;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;



public class FrmThongKe extends JPanel{
	
	private JComboBox<Integer> cbNgay,cbThang,cbNam;
	private JPanel ptren,pduoi;
	private JLabel lbDoanhThu,lbTienGoc,lbLoiNhuan,lbNam,lbThang,lbNgay;
	private DefaultTableModel dfNhanVien,dfThuoc;
	private JTable tableNhanVien,tableThuoc;
	private JTextField txtDoanhThu,txtLoiNhuan,txtTienGoc;
	private JButton btnThongKe;
	public FrmThongKe() {
		setLayout(new BorderLayout());

		Box b,b1,b2,b3,b4;
		b=Box.createVerticalBox();
		b1=Box.createHorizontalBox();
		b2=Box.createHorizontalBox();
		b3=Box.createHorizontalBox();
		b4=Box.createHorizontalBox();
		ptren=new JPanel();
		pduoi=new JPanel(new BorderLayout());

		cbNgay=new JComboBox<Integer>() ;
		for(int i=1;i<=31;i++) {
			
			cbNgay.addItem(i);
		}
		cbThang=new JComboBox<Integer>() ;
		for(int i=1;i<=12;i++) {
			
			cbThang.addItem(i);
		}
		cbNam=new JComboBox<Integer>() ;
		for(int i=LocalDate.now().getYear();i>=LocalDate.now().getYear()-10;i--) {
			
			cbNam.addItem(i);
		}
		ptren.add(lbNgay=new JLabel("Ngày"));
		ptren.add(cbNgay);
		ptren.add(lbThang=new JLabel("Tháng"));
		ptren.add(cbThang);
		ptren.add(lbNam=new JLabel("Năm"));
		ptren.add(cbNam);
		ptren.add(btnThongKe=new JButton("Thống kê"));
		b1.add(ptren,BorderLayout.NORTH);
		
		String [] headernv={"Mã khách hàng","Tên khách hàng","Địa chỉ","Ngày sinh","Số điện thoại","Giới tính"};
		dfNhanVien=new DefaultTableModel(headernv,0);
		tableNhanVien=new JTable(dfNhanVien);
		tableNhanVien.setRowHeight(20);
		JScrollPane scroll;
		scroll=new JScrollPane(tableNhanVien,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, 
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scroll.setBorder(BorderFactory.createTitledBorder("Thông tin khách hàng"));
		b2.add(scroll,BorderLayout.CENTER);
		
		String []headerthuoc= {"Mã thuốc","Tên thuốc","Ngày sản xuất","Hạn sử dụng","Đơn giá","Mã nhà cung cấp","Tên nhà cung cấp","Loại thuốc","Nước sản xuất"};
		dfThuoc=new DefaultTableModel(headerthuoc,0);
		tableThuoc=new JTable(dfThuoc);
		tableThuoc.setRowHeight(20);
		JScrollPane scrollthuoc;
		scrollthuoc=new JScrollPane(tableThuoc,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollthuoc.setBorder(BorderFactory.createTitledBorder("Thông tin thuốc khách hàng đã mua"));
		b3.add(scrollthuoc,BorderLayout.SOUTH);
		
		Box d,d1,d2,d3;
		d=Box.createVerticalBox();
		d1=Box.createHorizontalBox();
		d2=Box.createHorizontalBox();
		d3=Box.createHorizontalBox();
		
		d1.add(lbDoanhThu=new JLabel("Tổng doanh thu:  "));
		d1.add(txtDoanhThu=new JTextField(15));
		d.add(d1);
		d.add(Box.createVerticalStrut(10));
		d2.add(lbTienGoc=new JLabel("Tiền gốc:              "));
		d2.add(txtTienGoc=new JTextField(15));
		d.add(d2);
		d.add(Box.createVerticalStrut(10));
		d3.add(lbLoiNhuan=new JLabel("Lợi nhuận:            "));
		d3.add(txtLoiNhuan=new JTextField(15));
		d.add(d3);
		pduoi.add(d,BorderLayout.EAST);
		b4.add(pduoi);
		
		b.add(b1);
		b.add(b2);
		b.add(b3);
		b.add(b4);
		add(b);
		
		//add(lbDoanhThu=new JLabel("Doanh thu"),BorderLayout.SOUTH);

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new FrmThongKe().setVisible(true);
	}
}

