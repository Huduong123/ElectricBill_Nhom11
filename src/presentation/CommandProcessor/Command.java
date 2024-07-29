package presentation.CommandProcessor;


import java.sql.SQLException;

import javax.swing.table.DefaultTableModel;

import domain.ElectricBillModel;
import domain.model.Customer;

public abstract class Command {
    public ElectricBillModel electricBillModel;
   public  Customer customer;

public Command(ElectricBillModel electricBillModel, Customer customer){
    this.electricBillModel = electricBillModel;
    this.customer = customer;
}
    
 








    public abstract void execute() throws ClassNotFoundException, SQLException;
}
