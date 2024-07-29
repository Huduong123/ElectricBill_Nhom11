package domain.model;

import javax.swing.table.DefaultTableModel;

public class Customer {
    public int maKH;
    public String hoTen;
    public String Date;
    public String quocTich;
    public String doiTuong;
    public int soLuong;
    public double donGia;
    public int dinhMuc;
    private DefaultTableModel tableModel;

    public   Customer(int maKh, String hoTen, String Date, String quocTich, String doiTuong, int soLuong, Double donGia, int dinhMuc, DefaultTableModel defaultTableModel){
        this.maKH = maKh;
        this.hoTen = hoTen;
        this.soLuong = soLuong;
        this.donGia = donGia;
        this.Date = Date;
        this.quocTich = quocTich;
        this.doiTuong = doiTuong;
        this.dinhMuc = dinhMuc;
        tableModel = defaultTableModel;

    }
    public   Customer(int maKh, String hoTen, String Date, String quocTich,  int soLuong, Double donGia,DefaultTableModel defaultTableModel){
        this.maKH = maKh;
        this.hoTen = hoTen;
        this.soLuong = soLuong;
        this.donGia = donGia;
        this.Date = Date;
        this.quocTich = quocTich;
        tableModel = defaultTableModel;
    }
    public Customer (int maKH, DefaultTableModel defaultTableModel) {
        this.maKH = maKH;
        tableModel = defaultTableModel;
    }



    public int getMaKh() {
        return maKH;
    }
    public void setMaKh(int maKh) {
        this.maKH = maKh;
    }
    public String getHoTen() {
        return hoTen;
    }
    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }
    public int getSoLuong() {
        return soLuong;
    }
    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }
    public double getDonGia() {
        return donGia;
    }
    public void setDonGia(double donGia) {
        this.donGia = donGia;
    }
    public String getDate() {
        return Date;
    }
    public void setDate(String date) {
        Date = date;
    }

    public String getQuocTich() {
        return quocTich;
    }

    public void setQuocTich(String quocTich) {
        this.quocTich = quocTich;
    }





    public String getDoiTuong() {
        return doiTuong;
    }

    public void setDoiTuong(String doiTuong) {
        this.doiTuong = doiTuong;
    }


    public int getDinhMuc() {
        return dinhMuc;
    }

    public void setDinhMuc(int dinhMuc) {
        this.dinhMuc = dinhMuc;
    }
    public int getMaKH() {
        return maKH;
    }
    public void setMaKH(int maKH) {
        this.maKH = maKH;
    }
    public DefaultTableModel getTableModel() {
        return tableModel;
    }
    public void setTableModel(DefaultTableModel tableModel) {
        this.tableModel = tableModel;
    }

    


    }




