package domain.model;

import javax.swing.table.DefaultTableModel;
import javax.xml.crypto.Data;


public class CustomerVn extends Customer   {
   

    public CustomerVn(int maKh, String hoTen, String Date, String quocTich, String doiTuong, int soLuong,
            Double donGia, int dinhMuc, DefaultTableModel defaultTableModel) {
        super(maKh, hoTen, Date, quocTich, doiTuong, soLuong, donGia, dinhMuc, defaultTableModel);
        //TODO Auto-generated constructor stub
    }

    public double THANHTIEN() {
        if (getSoLuong() <= dinhMuc) {
            return getSoLuong() * getDonGia();
        } else {
            int soKWVuot = getSoLuong() - dinhMuc;
            return (dinhMuc * getDonGia()) + (soKWVuot * getDonGia() * 2.5);
        }
    }
}

