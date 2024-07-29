package persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import domain.model.Customer;
import domain.model.CustomerForeign;
import domain.model.CustomerVn;


public class ElectricBillPersistence  {



    private static final String URL = "jdbc:mysql://localhost:3306/electricbill";
    private static final String USER = "root";
    private static final String PASSWORD = "123456";

   

     public void loadData(DefaultTableModel tableModel) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT * FROM electric")) {

            // Xóa dữ liệu cũ trong bảng
            tableModel.setRowCount(0);

            while (resultSet.next()) {
              String maKH = resultSet.getString("MaKH");
              String hoTen = resultSet.getString("HoTen");
              String Date = resultSet.getString("NGAY");
              String quocTich = resultSet.getString("QuocTich");
              String doiTuong = resultSet.getString("DoiTuong");
              int soKW = resultSet.getInt("SoKW");
              Double donGia = resultSet.getDouble("DonGia");
              int dinhMuc = resultSet.getInt("DinhMuc");
            Object[] rowData = {maKH, hoTen, Date, quocTich, doiTuong, soKW, donGia, dinhMuc};
            tableModel.addRow(rowData);
            }
        }
    }

    public void saveCustomer(Customer customer) throws SQLException, ClassNotFoundException{
       try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
        String sql = "INSERT INTO electric (MaKH, HoTen, NGAY, QuocTich, DoiTuong, SoKW, DonGia, DinhMuc) VALUES (?, ?, ?, ?, ?, ?, ? , ?);";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, customer.getMaKh());
                statement.setString(2, customer.getHoTen());
                statement.setString(3, customer.getDate());
                statement.setString(4, customer.getQuocTich());
                statement.setString(5, customer.getDoiTuong());
                statement.setInt(6, customer.getSoLuong());
                statement.setDouble(7, customer.getDonGia());
                statement.setInt(8, customer.getDinhMuc());
                statement.executeUpdate();
            }
        }

        // Hiển thị thông báo thêm hóa đơn thành công
        JOptionPane.showMessageDialog(null, "Thêm hóa đơn thành công!");
    }

    public int getNextMaKH() throws SQLException, ClassNotFoundException {
        int maxMaKH = 0;
        Class.forName("com.mysql.cj.jdbc.Driver");
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT MAX(MaKH) FROM electric")) {
    
            if (resultSet.next()) {
                maxMaKH = resultSet.getInt(1);
            }
        }
        return maxMaKH + 1;
    }
   
    public void updateCustomer(Customer customer) throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String sql = "UPDATE electric SET HoTen = ?, NGAY = ?, QuocTich = ?, DoiTuong = ?, SoKW = ?, DonGia = ?, DinhMuc = ? WHERE MaKH = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, customer.getHoTen());
                statement.setString(2, customer.getDate());
                statement.setString(3, customer.getQuocTich());
                statement.setString(4, customer.getDoiTuong());
                statement.setInt(5, customer.getSoLuong());
                statement.setDouble(6, customer.getDonGia());
                statement.setInt(7, customer.getDinhMuc());
                statement.setInt(8, customer.getMaKh());
                statement.executeUpdate();
            }
        }
        // Hiển thị thông báo sửa hóa đơn thành công
        JOptionPane.showMessageDialog(null, "Sửa hóa đơn thành công!");
    }
 
    
    public void deleteCustomer(Customer customer) throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String sql = "DELETE FROM electric WHERE MaKH = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, customer.getMaKh());
                statement.executeUpdate();
            }
        }
    
        // Hiển thị thông báo xóa hóa đơn thành công
        JOptionPane.showMessageDialog(null, "Xóa hóa đơn thành công!");
    }

    public void findCustomer(int maKH, DefaultTableModel tableModel) throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String sql = "SELECT * FROM electric WHERE MaKH = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, maKH);
                try (ResultSet resultSet = statement.executeQuery()) {
                    tableModel.setRowCount(0); // Xóa dữ liệu cũ trong bảng
                    if (resultSet.next()) {
                        String hoTen = resultSet.getString("HoTen");
                        String date = resultSet.getString("NGAY");
                        String quocTich = resultSet.getString("QuocTich");
                        String doiTuong = resultSet.getString("DoiTuong");
                        int soKW = resultSet.getInt("SoKW");
                        double donGia = resultSet.getDouble("DonGia");
                        int dinhMuc = resultSet.getInt("DinhMuc");
                        Object[] rowData = {maKH, hoTen, date, quocTich, doiTuong, soKW, donGia, dinhMuc};
                        tableModel.addRow(rowData);
                    } else {
                        throw new SQLException("Không tìm thấy khách hàng với mã khách hàng: " + maKH);
                    }
                }
            }
        }
    }


    public double tinhTongSoKWVn() throws SQLException, ClassNotFoundException {
        double totalKW = 0;
        Class.forName("com.mysql.cj.jdbc.Driver");
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement statement = connection.prepareStatement("SELECT SUM(SoKW) FROM electric WHERE QuocTich = 'Việt Nam'")) {
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    totalKW = resultSet.getDouble(1);
                }
            }
        }
        return totalKW;
    }

    public double tinhTongSoKWForeign() throws SQLException, ClassNotFoundException {
        double totalKW = 0;
        Class.forName("com.mysql.cj.jdbc.Driver");
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement statement = connection.prepareStatement("SELECT SUM(SoKW) FROM electric WHERE QuocTich != 'VN'")) {
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    totalKW = resultSet.getDouble(1);
                }
            }
        }
        return totalKW;
    }


     public List<CustomerVn> getAllCustomersVn() throws SQLException, ClassNotFoundException {

        Class.forName("com.mysql.cj.jdbc.Driver");
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT * FROM electric")) {

                    List<CustomerVn> customersVn = new ArrayList<>();
        String query = "SELECT * FROM electric WHERE QuocTich = 'Việt Nam'";
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(query);

        while (rs.next()) {
            CustomerVn customer = new CustomerVn(
                rs.getInt("MaKH"),
                rs.getString("HoTen"),
                rs.getString("NGAY"),
                rs.getString("QuocTich"),
                rs.getString("DoiTuong"),
                rs.getInt("SoKW"),
                rs.getDouble("DonGia"),
                rs.getInt("DinhMuc"),
                new DefaultTableModel()
            );
            customersVn.add(customer);
        }

        return customersVn;
            }
        }
       
    

    public List<CustomerForeign> getAllCustomersForeign() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT * FROM electric")) {

                    List<CustomerForeign> customersForeign = new ArrayList<>();
                    String query = "SELECT * FROM electric WHERE QuocTich != 'Việt Nam'";
                    Statement stmt = connection.createStatement();
                    ResultSet rs = stmt.executeQuery(query);
            
                    while (rs.next()) {
                        CustomerForeign customer = new CustomerForeign(
                            rs.getInt("MaKH"),
                            rs.getString("HoTen"),
                            rs.getString("NGAY"),
                            rs.getString("QuocTich"),
                            rs.getInt("SoKW"),
                            rs.getDouble("DonGia"),
                            new DefaultTableModel()
                        );
                        customersForeign.add(customer);
                    }
            
                    return customersForeign;
            }
        }
      
      
}
    


