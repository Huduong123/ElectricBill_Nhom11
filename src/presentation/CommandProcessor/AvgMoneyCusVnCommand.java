package presentation.CommandProcessor;

import java.sql.SQLException;

import domain.ElectricBillModel;
import domain.model.CustomerVn;

public class AvgMoneyCusVnCommand extends Command{
    public AvgMoneyCusVnCommand(ElectricBillModel electricBillModel, CustomerVn customerVn){
        super(electricBillModel, customerVn);
    }

    @Override
    public void execute() throws ClassNotFoundException, SQLException {
       electricBillModel.tinhTongTienVn();
    }
}
