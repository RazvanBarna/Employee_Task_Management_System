package BusinessLogic;
import DataModel.Employee;

import java.util.*;

public class EmployeesManagement {
    private List<Employee> listOfEmployees=new ArrayList<>();
    private String errorMessageEmployee="";

    public void addEmployee(Employee employee){
        if(employee==null){
            errorMessageEmployee="The employee must have an input.";
        }
        else {
            listOfEmployees.add(employee);
        }
    }

}
