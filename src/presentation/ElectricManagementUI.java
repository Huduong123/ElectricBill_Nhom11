package presentation;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.List;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;



import domain.ElectricBillModel;
import presentation.CommandProcessor.AddCommand;
import presentation.CommandProcessor.Command;
import presentation.CommandProcessor.CommandProcessor;
import presentation.CommandProcessor.DeleteCommand;
import presentation.CommandProcessor.EditCommand;
import presentation.CommandProcessor.AvgMoneyCusForeignCommand;
import presentation.CommandProcessor.AvgMoneyCusVnCommand;
import presentation.CommandProcessor.TotalKWForeignCommand;
import presentation.CommandProcessor.TotalKWVnCommand;

import domain.model.*;



    public class ElectricManagementUI extends JFrame implements ActionListener{
    private String title;

     private JMenuBar jMenuBar = null;
    private JLabel lbMaKH, lbHoTen, lbDate,lbNhapQuocTich, lbQuocTich, lbDoiTuong, lbSoKW, lbDonGia, lbDinhMuc,lbTongSoKWVn, lbTongSoKWForeign,lbtongTienVn, lbtongTienForeign, lbTimKiem, lbMonth;
    
    private JTextField txtMaKH, txtHoTen,txtQuocTich, txtSoKW, txtDonGia, txtDinhMuc, txtTimKiem, txtDate;
    private JButton addButton, editButton, deleteButton, findButton, AvgButton, sumTongKWButton , exiButton;
    private JPanel buttonPanel, mainPanel;
    private DefaultTableModel tableModel = null;
    private JTable electricBillTable = null;
    private JComboBox<String> jComboBoxDT, jComboxListMonth;
    private JRadioButton vietNamJRadioButton, foreigJRadioButton;
    private ButtonGroup bgLoaiKhach;

    private ElectricBillModel electricBillModel = null;
    private CommandProcessor commandProcessor = null;


        public ElectricManagementUI(ElectricBillModel electricBillModel, CommandProcessor commandProcessor) {
            this.electricBillModel = electricBillModel;
            this.commandProcessor = commandProcessor;

            builPanel();
            buildMenuBar();
            setJMenuBar(jMenuBar);
            title = "Quản lí hóa Đơn Tiền Điện";
            setTitle(title);
            setSize(850, 750);
            setLocationRelativeTo(null);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setVisible(true);
        }

        public void builPanel() {
            lbMaKH = new JLabel("Mã Khách hàng: ");
        lbHoTen = new JLabel("Họ tên: ");
        lbDate = new JLabel("Ngày");
        lbQuocTich = new JLabel("Quốc tịch");
        lbNhapQuocTich = new JLabel("Nhập quốc tịch");
        lbDoiTuong = new JLabel("Đối tượng: ");
        lbSoKW = new JLabel("Số KW: ");
        lbDonGia = new JLabel("Đơn giá");
        lbDinhMuc = new JLabel("Định mức");
        lbTongSoKWVn = new JLabel("Tổng KW VN: ");
        lbTongSoKWForeign = new JLabel("Tổng KW Nước ngoài: ");
        lbtongTienVn = new JLabel("Tổng trung bình tiền VN: ");
        lbtongTienForeign = new JLabel("Tổng trung bình tiền Foreign: ");
        lbMonth = new JLabel("Tháng ");

       
        
        txtMaKH = new JTextField(18);
        txtHoTen = new JTextField(20);
        txtDate = new JTextField(20);
        txtQuocTich = new JTextField(18);
        txtSoKW = new JTextField(20);
        txtDonGia = new JTextField(20);
        txtDinhMuc = new JTextField(20);
        

        
        vietNamJRadioButton = new JRadioButton("Khách Việt Nam");
        foreigJRadioButton = new JRadioButton("Khách Nước ngoài");

        bgLoaiKhach = new ButtonGroup();
        bgLoaiKhach.add(vietNamJRadioButton);
        bgLoaiKhach.add(foreigJRadioButton);

      
        jComboBoxDT = new JComboBox<>(new String[] {"Sinh hoạt", "Kinh doanh", "Sản xuất"});
        jComboBoxDT.setPreferredSize(txtMaKH.getPreferredSize());

        jComboxListMonth = new JComboBox<>(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"});
        jComboxListMonth.setPreferredSize(txtMaKH.getPreferredSize());


        addButton = new JButton("Thêm");
        editButton = new JButton("Sửa");
        deleteButton = new JButton("Xóa");
        findButton = new JButton("Tìm kiếm HĐ");
        AvgButton = new JButton("Tính tổng trung bình tiền");
        sumTongKWButton = new JButton("Tổng số KW");
        exiButton = new JButton("Thoát");
      

        lbTimKiem = new JLabel("Tìm kiếm HĐ");
        txtTimKiem = new JTextField(20);
       
        

      

        String[] columnNames = {"ID", "Họ tên", "Ngày ra hóa đơn", "Quốc Tịch", "Đối tượng", "Số KW", "Đơn Giá","Định mức"};
        tableModel = new DefaultTableModel(columnNames, 0);
        electricBillTable = new JTable(tableModel);
         electricBillTable.getSelectionModel().addListSelectionListener(electricBillTable);
        //Button actions

        


        

     addButton.addActionListener(new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {
           try {
            addElectricBill();
        } catch (ClassNotFoundException | SQLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        }
        
     });

        editButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
             editElectricBill();
            }
        });


        deleteButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
               deleteElectricBill();
            }
        });


        findButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                findelEctricBill();
            }
            
        });
        
        sumTongKWButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                    tinhTongKW();
            }
            
        });

        AvgButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                tinhTongTien();
            }
            
        });

        exiButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                int confirmed = JOptionPane.showConfirmDialog(null, 
                "Bạn có chắc chắn muốn thoát chương trình?", "Xác nhận thoát",
                JOptionPane.YES_NO_OPTION);
    
            if (confirmed == JOptionPane.YES_OPTION) {
                System.exit(0); 
            }
            }
            
        });


     
        //Table selection acction
        electricBillTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent e) {
               int selectedRow = electricBillTable.getSelectedRow();
               if(selectedRow != -1) {
                txtMaKH.setText(electricBillTable.getValueAt(selectedRow, 0).toString());
                txtHoTen.setText(electricBillTable.getValueAt(selectedRow, 1).toString());
                txtDate.setText(electricBillTable.getValueAt(selectedRow, 2).toString());
              
                    txtQuocTich.setText(electricBillTable.getValueAt(selectedRow, 3).toString());
                    jComboBoxDT.setSelectedItem(electricBillTable.getValueAt(selectedRow, 4));
                txtSoKW.setText(electricBillTable.getValueAt(selectedRow, 5).toString());
                txtDonGia.setText(electricBillTable.getValueAt(selectedRow, 6).toString());
                txtDinhMuc.setText(electricBillTable.getValueAt(selectedRow, 7).toString());
               }
            }
            
        });
        try {
            loadData();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        
        //RadioButton ACtion
        
        vietNamJRadioButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                txtDinhMuc.setEnabled(true);
                jComboBoxDT.setEnabled(true);
                txtQuocTich.setEnabled(false);
            }
            
        });

        foreigJRadioButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                txtDinhMuc.setEnabled(false);
                jComboBoxDT.setEnabled(false);
                txtQuocTich.setEnabled(true);
            }
            
        });


    // Lắng nghe sự kiện chọn hàng trong JTable
    electricBillTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
        @Override
        public void valueChanged(ListSelectionEvent event) {
            if (!event.getValueIsAdjusting()) {
                int selectedRow = electricBillTable.getSelectedRow();
                if (selectedRow != -1) {
                    String selectedCustomerType = (String) electricBillTable.getValueAt(selectedRow, 2);
                    jComboBoxDT.setSelectedItem(selectedCustomerType);
                }
            }
        }
    });


   

         // Layout setup
         JPanel inputPanel = new JPanel(new GridBagLayout());
         GridBagConstraints gbc = new GridBagConstraints();
         gbc.gridx = 0;
         gbc.gridy = 0;
         gbc.anchor = GridBagConstraints.WEST;
         gbc.insets = new Insets(5, 5, 5, 5);
      
         inputPanel.add(lbMaKH, gbc);
         gbc.gridx++;
         inputPanel.add(txtMaKH, gbc);
         gbc.gridy++;
         gbc.gridx = 0;

         inputPanel.add(lbHoTen, gbc);
         gbc.gridx++;
         inputPanel.add(txtHoTen, gbc);
         gbc.gridy++;
         gbc.gridx = 0;

         inputPanel.add(lbDate, gbc);
         gbc.gridx++;
         inputPanel.add(txtDate, gbc);
         gbc.gridy++;
         gbc.gridx = 0;
    
   
        inputPanel.add(lbQuocTich, gbc);
        gbc.gridx++;
        inputPanel.add(vietNamJRadioButton, gbc);
        gbc.gridy++;
        inputPanel.add(foreigJRadioButton, gbc);

        gbc.gridy++;
        gbc.gridx = 0;  
        inputPanel.add(lbNhapQuocTich, gbc);
        gbc.gridx++;
        inputPanel.add(txtQuocTich, gbc);


        gbc.gridy++;
        gbc.gridx = 0;
        inputPanel.add(lbDoiTuong, gbc);
        gbc.gridx++;
        inputPanel.add(jComboBoxDT, gbc);
         gbc.gridy++;
         gbc.gridx = 0;

         inputPanel.add(lbSoKW, gbc);
         gbc.gridx++;
         inputPanel.add(txtSoKW, gbc);
         gbc.gridy++;
         gbc.gridx = 0;

         inputPanel.add(lbDonGia, gbc);
         gbc.gridx++;
         inputPanel.add(txtDonGia, gbc);
         gbc.gridy++;
         gbc.gridx = 0;

         inputPanel.add(lbDinhMuc, gbc);
         gbc.gridx++;
         inputPanel.add(txtDinhMuc, gbc);
         gbc.gridy++;
         gbc.gridx = 0;

         inputPanel.add(lbTongSoKWVn, gbc);
         gbc.gridx++;
         inputPanel.add(lbTongSoKWForeign, gbc);
         gbc.gridy++;
         gbc.gridx = 0;

         inputPanel.add(lbtongTienVn, gbc);
         gbc.gridx++;
         inputPanel.add(lbtongTienForeign, gbc);
         gbc.gridy++;
         gbc.gridx = 0;

         inputPanel.add(lbTimKiem, gbc);
         gbc.gridx++;
         inputPanel.add(txtTimKiem, gbc);

 
         buttonPanel = new JPanel();
         buttonPanel.add(addButton);
         buttonPanel.add(editButton);
         buttonPanel.add(deleteButton);
         buttonPanel.add(AvgButton);
         buttonPanel.add(sumTongKWButton);
         buttonPanel.add(findButton);
   
         buttonPanel.add(exiButton);

        mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(new JScrollPane(electricBillTable), BorderLayout.CENTER);
        mainPanel.add(inputPanel, BorderLayout.NORTH);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        
        this.add(mainPanel);
     
         txtMaKH.setEnabled(false);
      
        }
       

        public void buildMenuBar(){
            jMenuBar = new JMenuBar();
            JMenu jMenu = new JMenu("Hệ thống");
            JMenuItem jMenuItemClose = new JMenuItem("Đóng");
    
            jMenuItemClose.addActionListener(this);
    
            jMenu.add(jMenuItemClose);
            jMenuBar.add(jMenu);
        }
        
    
 

   private void addElectricBill() throws ClassNotFoundException, SQLException{
    SimpleDateFormat formater = new SimpleDateFormat("yyyy/MM/dd");
    try {
        int nextMaKH = electricBillModel.getNextMaKH();
        txtMaKH.setText(String.valueOf(nextMaKH));
        String hoTen = txtHoTen.getText();
        String date = txtDate.getText();
         String quocTich = txtQuocTich.getText().isEmpty() ? "Việt Nam" : txtQuocTich.getText();
        String doiTuong = jComboBoxDT.getSelectedItem() != null ? jComboBoxDT.getSelectedItem().toString() : "";
        int soKW = Integer.parseInt(txtSoKW.getText());
        Double  donGia =  Double.parseDouble(txtDonGia.getText());
        int dinhMuc = txtDinhMuc.getText().isEmpty() ? 0 : Integer.parseInt(txtDinhMuc.getText());
   
        Customer customer;
        if (quocTich.equals("Việt Nam")) {
            customer = new Customer(nextMaKH, hoTen, date, quocTich, doiTuong, soKW, donGia, dinhMuc, tableModel);
        } else {
            customer = new Customer(nextMaKH, hoTen, date, quocTich, soKW, donGia, tableModel);
        }
  

        //tạo lệnh để thêm khách hàng 
        Command addCus = new AddCommand(electricBillModel, customer);
        try {
              // Thực hiện lệnh thêm hóa đơn
              commandProcessor.execute(addCus);

              // Làm mới bảng dữ liệu sau khi thêm hóa đơn
              loadData();
              clearInputFields();
            } catch (ClassNotFoundException | SQLException e1) {
                e1.printStackTrace();
                JOptionPane.showMessageDialog(this, "Lỗi khi thêm hóa đơn: " + e1.getMessage());
            }
    }catch(NumberFormatException ex){
        JOptionPane.showMessageDialog(this, "Vui lòng nhập số hợp lệ cho đơn giá.");
    }
   }
 
  private void editElectricBill() {
    try {
        // Lấy thông tin từ các trường nhập liệu
        int maKH = Integer.parseInt(txtMaKH.getText());
        String hoTen = txtHoTen.getText();
        String date = txtDate.getText();
        String quocTich = txtQuocTich.getText().isEmpty() ? "Việt Nam" : txtQuocTich.getText();
        String doiTuong = jComboBoxDT.getSelectedItem() != null ? jComboBoxDT.getSelectedItem().toString() : "";
        int soKW = Integer.parseInt(txtSoKW.getText());
        double donGia = Double.parseDouble(txtDonGia.getText());
        int dinhMuc = txtDinhMuc.getText().isEmpty() ? 0 : Integer.parseInt(txtDinhMuc.getText());

        Customer customer;
        if (quocTich.equals("Việt Nam")) {
            customer = new Customer(maKH, hoTen, date, quocTich, doiTuong, soKW, donGia, dinhMuc, tableModel);
        } else {
            customer = new Customer(maKH, hoTen, date, quocTich, soKW, donGia, tableModel);
        }

        Command editCus = new EditCommand(electricBillModel, customer);
        try {
            commandProcessor.execute(editCus);
            loadData();
            clearInputFields();
        } catch (ClassNotFoundException | SQLException e1) {
            e1.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi khi sửa hóa đơn: " + e1.getMessage());
        }
    } catch (NumberFormatException ex) {
        JOptionPane.showMessageDialog(this, "Vui lòng nhập số hợp lệ cho đơn giá.");
    }
}




private void deleteElectricBill() {
    try {
        int maKH = Integer.parseInt(txtMaKH.getText());

        Customer customer = new Customer(maKH, tableModel);

        Command deleteCus = new DeleteCommand(electricBillModel, customer);
        try {
            commandProcessor.execute(deleteCus);
            loadData();
            clearInputFields();
        } catch (ClassNotFoundException | SQLException e1) {
            e1.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi khi xóa hóa đơn: " + e1.getMessage());
        }
    } catch (NumberFormatException ex) {
        JOptionPane.showMessageDialog(this, "Vui lòng nhập mã khách hàng hợp lệ.");
    }
}


   private void findelEctricBill(){
    try {
        int maKH = Integer.parseInt(txtTimKiem.getText().trim());
        electricBillModel.findElectricBill(maKH, tableModel);
    } catch (NumberFormatException ex) {
        JOptionPane.showMessageDialog(this, "Vui lòng nhập mã khách hàng hợp lệ.");
    } catch (ClassNotFoundException | SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "Lỗi khi tìm kiếm hóa đơn: " + e.getMessage());
    }
   }

  




private void loadData() throws ClassNotFoundException, SQLException {

     try {
        electricBillModel.loadElectricBill(tableModel);
     } catch (ClassNotFoundException | SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Lỗi khi tải dữ liệu!");
    }

    }


private void tinhTongKW() {
    try {
       

        // Tính tổng KW cho khách hàng Việt Nam
        Command totalKWVnCommand = new TotalKWVnCommand(electricBillModel, null);
        commandProcessor.execute(totalKWVnCommand);

        // Tính tổng KW cho khách hàng nước ngoài
        Command totalKWForeignCommand = new TotalKWForeignCommand(electricBillModel, null);
        commandProcessor.execute(totalKWForeignCommand);

        // Cập nhật giao diện người dùng với các giá trị tổng KW
        lbTongSoKWVn.setText("Tổng KW Việt Nam: " + electricBillModel.tinhTongSoKWVn());
        lbTongSoKWForeign.setText("Tổng KW Nước Ngoài: " + electricBillModel.tinhTongSoKWForeign());

    } catch (SQLException | ClassNotFoundException ex) {
        ex.printStackTrace();
    }
}
    
    private void tinhTongTien(){
        try {
            // Khởi tạo các Command cho việc tính tổng tiền
            CustomerVn dummyCustomerVn = new CustomerVn(0, "", "", "", "", 0, 0.0, 0, new DefaultTableModel());
            CustomerForeign dummyCustomerForeign = new CustomerForeign(0, "", "", "", 0, 0.0, new DefaultTableModel());
    
            Command sumMoneyVn = new AvgMoneyCusVnCommand(electricBillModel, dummyCustomerVn);
            Command sumMoneyForeign = new AvgMoneyCusForeignCommand(electricBillModel, dummyCustomerForeign);
    
            // Sử dụng CommandProcessor để thực thi các Command
            CommandProcessor processor = CommandProcessor.makeCommandProcessor();
            
            // Execute sumMoneyVn Command and get the result
            processor.execute(sumMoneyVn);
            String totalVn = electricBillModel.getResult();
            
            // Execute sumMoneyForeign Command and get the result
            processor.execute(sumMoneyForeign);
            String totalForeign = electricBillModel.getResult();
    
            // Hiển thị kết quả
            JOptionPane.showMessageDialog(null, "Tổng tiền trung bình khách hàng Việt Nam: " + totalVn + "\nTổng tiền trung bình khách hàng nước ngoài: " + totalForeign);
            lbtongTienVn.setText("Tổng tiền trung bình Việt Nam: " + electricBillModel.tinhTongTienVn());
        lbtongTienForeign.setText("Tổng tiền trung bình Nước Ngoài: " + electricBillModel.tinhTongTienForeign());
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
        }
    }




    private void clearInputFields(){
        txtMaKH.setText("");
        txtHoTen.setText("");
        txtDate.setText("");
        txtSoKW.setText("");
        txtDonGia.setText("");
        txtDinhMuc.setText("");
        txtTimKiem.setText("");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
    }

     public void update() {
        String result = electricBillModel.getResult();
        DecimalFormat df = new DecimalFormat("#.###");
        String s = df.format(result);

        txtDonGia.setText(s);
    }
     
    }
