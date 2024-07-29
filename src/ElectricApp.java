
import domain.*;
import persistence.*;
import presentation.*;
import presentation.CommandProcessor.CommandProcessor;


public class ElectricApp {
 public static void main(String[] args) throws ClassNotFoundException  {

    CommandProcessor commandProcessor = CommandProcessor.makeCommandProcessor();
  
    ElectricBillPersistence electricBillPersistence = new ElectricBillPersistence();

    ElectricBillModel electricBillModel = new ElectricBillModel(electricBillPersistence);

    ElectricManagementUI electricBillUI = new ElectricManagementUI(electricBillModel, commandProcessor);
     
    
     }
}
