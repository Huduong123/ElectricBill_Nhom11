package presentation.CommandProcessor;

import java.sql.SQLException;
import java.text.ParseException;

import domain.ElectricBillModel;
import domain.model.CustomerVn;

public class TotalKWVnCommand extends Command{
    
    public TotalKWVnCommand (ElectricBillModel electricBillModel, CustomerVn customerVn) {
super(electricBillModel, customerVn);
    }

    @Override
    public void execute() throws ClassNotFoundException, SQLException {
    electricBillModel.tinhTongSoKWForeign();
    }
}
