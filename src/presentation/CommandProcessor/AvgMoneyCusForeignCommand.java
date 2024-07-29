package presentation.CommandProcessor;

import java.sql.SQLException;

import domain.ElectricBillModel;
import domain.model.Customer;
import domain.model.CustomerForeign;
import domain.model.CustomerVn;

public class AvgMoneyCusForeignCommand extends Command{
   

    public AvgMoneyCusForeignCommand(ElectricBillModel electricBillModel, CustomerForeign customerForeign) {
        super(electricBillModel, customerForeign);
    }

    @Override
    public void execute() throws ClassNotFoundException, SQLException {
        electricBillModel.tinhTongTienForeign();
    }
}
