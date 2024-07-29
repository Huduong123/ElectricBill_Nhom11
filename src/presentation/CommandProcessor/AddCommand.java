package presentation.CommandProcessor;

import javax.swing.table.DefaultTableModel;
import java.sql.SQLException;
import domain.ElectricBillModel;
import domain.model.Customer;

public class AddCommand extends Command{
    private DefaultTableModel defaultTableModel;

    public AddCommand(ElectricBillModel electricBillModel, Customer customer){
        super(electricBillModel, customer);
    }


    @Override
    public void execute()throws ClassNotFoundException, SQLException {
        electricBillModel.addElectricBill(customer);
    }
    
}
