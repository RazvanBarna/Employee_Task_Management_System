package BusinessLogic;
import DataAccess.SerializationOperations;
import DataModel.Employee;
import DataModel.Task;

import java.util.*;

public class EmployeesManagement {
    private String errorMessageEmployee="";
    SerializationOperations serializationOperations =new SerializationOperations();
    TasksManagement tasksManagement = new TasksManagement();

    public void addEmployee(Employee employee){
        if(employee==null){
            errorMessageEmployee="The employee must have an input.";
        }
        else {
            serializationOperations.writeFile(employee);
        }
    }

    public List<Task> findListOfTasksFromMap(int idEmployee){
        for(Map.Entry<Employee,List<Task>> entry: tasksManagement.getMapOfTasks().entrySet()){
            if(entry.getKey().getIdEmployee() ==idEmployee){
                return entry.getValue();
            }
        }
        return null;
    }

    public int calculateEmployeeWorkDuration(int idEmployee){
        List<Task> tasksOfEmployer = findListOfTasksFromMap(idEmployee);
        if(tasksOfEmployer == null) return 0;
        int totalOfHours=0;
        for(Task task : tasksOfEmployer){
            totalOfHours+=task.estimateDuration();
        }
        return totalOfHours;
    }

}
