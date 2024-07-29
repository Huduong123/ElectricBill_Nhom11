package presentation.CommandProcessor;

import java.sql.SQLException;

import domain.ElectricBillModel;
import domain.model.Customer;

public class EditCommand extends Command{


    public EditCommand(ElectricBillModel electricBillService, Customer customer){
        super(electricBillService, customer);
    }


    @Override
    public void execute() throws ClassNotFoundException, SQLException{
        this.electricBillModel.editElectricBill(customer);
    }
    
}
