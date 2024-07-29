package domain.model;

import java.io.Serializable;

import javax.swing.table.DefaultTableModel;

public class CustomerForeign extends Customer implements Serializable{   

    public CustomerForeign(int maKh, String hoTen, String Date, String quocTich, int soLuong, Double donGia,
            DefaultTableModel defaultTableModel) {
        super(maKh, hoTen, Date, quocTich, soLuong, donGia, defaultTableModel);
    }

    
    public void CustomerForeign(){

    }
    public double THANHTIEN2(){
        return getSoLuong() * getDonGia();

    }
   
}
