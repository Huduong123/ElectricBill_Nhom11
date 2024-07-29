package presentation.CommandProcessor;

import java.sql.SQLException;
import java.text.ParseException;

import domain.ElectricBillModel;
import domain.model.CustomerForeign;
import domain.model.CustomerVn;

public class TotalKWForeignCommand extends Command{
    public TotalKWForeignCommand (ElectricBillModel electricBillModel, CustomerForeign customerForeign) {
    super(electricBillModel, customerForeign);
    }

    @Override
    public void execute() throws ClassNotFoundException, SQLException {
        electricBillModel.tinhTongSoKWVn();
        
    }

}
