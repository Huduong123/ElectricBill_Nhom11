package domain;

import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import domain.model.*;
import observer.Publisher;
import persistence.ElectricBillPersistence;


public class ElectricBillModel  extends Publisher{
    private ElectricBillPersistence electricBillPersistence;
    private String result;
   

    public ElectricBillModel(ElectricBillPersistence electricBillPersistence){
        this.electricBillPersistence = electricBillPersistence;

    }

    public void addElectricBill(Customer customer) throws SQLException, ClassNotFoundException{
        electricBillPersistence.saveCustomer(customer);
        changeState();
    }

    public void editElectricBill(Customer customer) throws SQLException, ClassNotFoundException{
        electricBillPersistence.updateCustomer(customer);
        changeState();
    }
    public void deleteElectricBill(Customer customer) throws SQLException, ClassNotFoundException{
        electricBillPersistence.deleteCustomer(customer);
        changeState();
    }
    public void findElectricBill(int maKH, DefaultTableModel tableModel) throws SQLException, ClassNotFoundException {
        electricBillPersistence.findCustomer(maKH, tableModel);
        changeState();
    }
    public void loadElectricBill(DefaultTableModel tableModel) throws SQLException, ClassNotFoundException{
        electricBillPersistence.loadData(tableModel);
        changeState();
    }

    public int getNextMaKH() throws SQLException, ClassNotFoundException {
        return electricBillPersistence.getNextMaKH();
    }

  
  

    public double tinhTongSoKWVn() throws ClassNotFoundException, SQLException{


        return electricBillPersistence.tinhTongSoKWVn();
    }

    public double tinhTongSoKWForeign() throws ClassNotFoundException, SQLException{

        return electricBillPersistence.tinhTongSoKWForeign();
    }

    public String tinhTongTienVn() throws SQLException, ClassNotFoundException {
        List<CustomerVn> customersVn = electricBillPersistence.getAllCustomersVn();
        double total = 0;
        double AvgVN = 0;
        int count = 0;
        for (CustomerVn customer : customersVn) {
            total  = total + customer.THANHTIEN();
            count = count + 1;
        }
        AvgVN = (double)(total / count) ;
          DecimalFormat decimalFormat = new DecimalFormat("#.###");
        String formattedNumber = decimalFormat.format(AvgVN);
         result = formattedNumber;
        return result;

        
    }

    public String tinhTongTienForeign() throws SQLException, ClassNotFoundException {
        List<CustomerForeign> customersForeign = electricBillPersistence.getAllCustomersForeign();
        double total = 0;
        double AvgForeign = 0;
        int count = 0;
        for (CustomerForeign customer : customersForeign) {
            total = total + customer.THANHTIEN2();
            count = count + 1;
        }

         AvgForeign = (double)(total / count) ;
          DecimalFormat decimalFormat = new DecimalFormat("#.###");
        String formattedNumber = decimalFormat.format(AvgForeign);
         result = formattedNumber;
        return result;

        
    }


   
    public String getResult() {
        return result;
    }

    public void changeState() {
        notifySubscribers();
    }
}
