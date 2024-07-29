package presentation.CommandProcessor;

import java.sql.SQLException;

import domain.ElectricBillModel;
import domain.model.Customer;

public class FindCommand extends Command{
     public FindCommand(ElectricBillModel electricBillService, Customer customer){
        super(electricBillService, customer);
    }


    @Override
    public void execute() throws ClassNotFoundException, SQLException {
        this.electricBillModel.findElectricBill(0, null);
    }
}
