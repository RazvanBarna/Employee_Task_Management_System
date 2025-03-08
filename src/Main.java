import BusinessLogic.EmployeesManagement;
import DataAccess.SerializationOperations;
import DataModel.ComplexTask;
import DataModel.Employee;
import DataModel.SimpleTask;
import DataModel.Task;

public class Main {
    public static void main(String[] args) {
        SerializationOperations serializationOperations=new SerializationOperations();
        Task t1=new SimpleTask("a","b",0,1);
        Task t2=new ComplexTask("b","c",11,12,"aa");
        Employee e1 = new Employee("John",30);
        serializationOperations.writeFile(t1);
        serializationOperations.writeFile(t2);
        serializationOperations.readAllObjectsFromFile("src/DataAccess/taskFile.txt");
        EmployeesManagement employeesManagement = new EmployeesManagement();
        employeesManagement.addEmployee(e1);


    }
}