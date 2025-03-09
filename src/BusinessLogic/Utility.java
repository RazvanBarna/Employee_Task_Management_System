package BusinessLogic;

import DataModel.Employee;
import DataModel.Task;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Utility {
    private EmployeesManagement employeesManagement ;
    private TasksManagement tasksManagement ;

    public Utility(TasksManagement tasksManagement,EmployeesManagement employeesManagement){
        this.tasksManagement = tasksManagement;
        this.employeesManagement = employeesManagement;
    }

    public List<Employee> sortByNrOfHoursGt40 ( List<Employee> employees){
        for(Employee employee : employees){
            this.employeesManagement.calculateEmployeeWorkDuration(employee.getIdEmployee()) ;
        }
        Collections.sort(employees, new CompareByHours());
        for ( Employee employee : employees){
            System.out.println(employee.toString());
        }
        return employees;
    }

    class CompareByHours implements Comparator<Employee>{
        @Override
        public int compare(Employee e1, Employee e2) {
            if (e1.getNrHoursWorks() > e2.getNrHoursWorks()) return 1;
            if (e1.getNrHoursWorks() < e2.getNrHoursWorks()) return -1;
            return 0;
        }
    }
}
