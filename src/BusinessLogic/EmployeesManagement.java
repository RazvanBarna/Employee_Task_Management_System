package BusinessLogic;
import DataAccess.SerializationOperations;
import DataModel.Employee;
import DataModel.Task;

import java.util.*;

public class EmployeesManagement {
    private String errorMessageEmployee="";
    SerializationOperations serializationOperations =new SerializationOperations();
    TasksManagement tasksManagement ;

    public EmployeesManagement(TasksManagement tasksManagement){
        this.tasksManagement = tasksManagement;
    }

    public EmployeesManagement(){};

    public void addEmployee(Employee employee){
        if(employee==null){
            errorMessageEmployee="The employee must have an input.";
        }
        else {
            //TO DO : VeRIFY DUPLICATE
            tasksManagement.getMapOfTasks().put(employee , new ArrayList<Task>());
            serializationOperations.writeFile(employee);
        }
    }

    private List<Task> findListOfTasksFromMap(int idEmployee){
        for(Map.Entry<Employee,List<Task>> entry: tasksManagement.getMapOfTasks().entrySet()){
            if(entry.getKey().getIdEmployee() ==idEmployee){
                return entry.getValue();
            }
        }
        return null;
    }

    private Employee searchEmployeeToSetWorkDuration(int idEmployee){
        for(Map.Entry<Employee,List<Task>> entry: tasksManagement.getMapOfTasks().entrySet()){
            if(entry.getKey().getIdEmployee() ==idEmployee){
                return entry.getKey();
            }
        }
        return null;
    }

    private void setWordDuration(Employee employee , int nrHours){
        if (employee !=null){
            employee.setNrHoursWorks(nrHours);
        }
        else {
            errorMessageEmployee=" this employee does not exist";
        }
    }

    private int calculateNrOfHours(List<Task> tasks){
        if(tasks == null) return 0;
        int totalOfHours=0;
        for(Task task : tasks){
            totalOfHours+=task.estimateDuration();
        }
        return totalOfHours;
    }

    public int calculateEmployeeWorkDuration(int idEmployee){
        List<Task> tasksOfEmployer = findListOfTasksFromMap(idEmployee);
        int totalOfHours = calculateNrOfHours(tasksOfEmployer);
        Employee employeeToSetWorkDuration = searchEmployeeToSetWorkDuration(idEmployee);
        setWordDuration(employeeToSetWorkDuration,totalOfHours);
        return totalOfHours;
    }

}
