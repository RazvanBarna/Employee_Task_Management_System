package BusinessLogic;
import DataAccess.SerializationOperations;
import DataModel.Employee;
import DataModel.Schedule;
import DataModel.Task;

import java.util.*;

public class EmployeesManagement {
    TasksManagement tasksManagement ;
    List<Schedule> listOfSchedulesForEmployees = new ArrayList<>();

    public EmployeesManagement(TasksManagement tasksManagement){
        this.tasksManagement = tasksManagement;
    }

    public EmployeesManagement(){};

    private boolean checkIfEmployeeAlreadyExists(Employee employee){
        for(Map.Entry<Employee,List<Task>> entry : tasksManagement.getMapOfTasks().entrySet()){
            if(entry.getKey().equals(employee)) return true;
        }
        return false;
    }

    public void addEmployee(Employee employee) throws RuntimeException,Exception{
        if(employee==null){
            throw new RuntimeException("Employee is null");
        }
        else if (!checkIfEmployeeAlreadyExists(employee)){

            tasksManagement.getMapOfTasks().put(employee,new ArrayList<>());
            SerializationOperations.writeFile(tasksManagement.getMapOfTasks());
           // tasksManagement.setMapOfTasks((Map<Employee, List<Task>>) SerializationOperations.readFile("src/DataAccess/mapFile.txt"));
        }
        else throw new RuntimeException("This employee already exists");
    }

    private Employee searchEmployeeToSetWorkDuration(int idEmployee){
        for(Map.Entry<Employee,List<Task>> entry: tasksManagement.getMapOfTasks().entrySet()){
            if(entry.getKey().getIdEmployee() ==idEmployee){
                return entry.getKey();
            }
        }
        return null;
    }

    private int calculateNrOfHours(List<Task> tasks){
        if(tasks == null) return 0;
        int totalOfHours=0;
        for(Task task : tasks){
            if(task.getStatusTask().equals("Completed")) {
                totalOfHours += task.estimateDuration();
            }
        }
        return totalOfHours;
    }

    public List<Schedule> setSchedulesForEmployees() throws Exception{
        Map <Employee,List<Task>> map = (Map<Employee, List<Task>>) SerializationOperations.readFile("src/DataAccess/mapFile.txt");
        for(Map.Entry<Employee,List<Task>> entry : tasksManagement.getMapOfTasks().entrySet()){
            listOfSchedulesForEmployees.add(new Schedule(entry.getKey(),calculateEmployeeWorkDuration(entry.getKey().getIdEmployee())));
        }
        return listOfSchedulesForEmployees;
    }

    public int calculateEmployeeWorkDuration(int idEmployee){
        List<Task> tasksOfEmployer = tasksManagement.findListOfTasksFromMap(idEmployee);
        int totalOfHours = calculateNrOfHours(tasksOfEmployer);
        return totalOfHours;
    }

}
