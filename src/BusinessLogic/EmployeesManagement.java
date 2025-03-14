package BusinessLogic;
import DataAccess.SerializationOperations;
import DataModel.Employee;
import DataModel.HourRetainer;
import DataModel.Task;

import java.io.File;
import java.util.*;

public class EmployeesManagement {
    TasksManagement tasksManagement ;
    List<HourRetainer> listOfSchedulesForEmployees = new ArrayList<>();

    public EmployeesManagement(TasksManagement tasksManagement){
        this.tasksManagement = tasksManagement;
    }

    public EmployeesManagement(){};

    public boolean checkIfEmployeeAlreadyExists(Employee employee){
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
            File file = new File("src/DataAccess/mapFile.txt");
            if(file.length() > 0 ){
            tasksManagement.setMapOfTasks((Map<Employee, List<Task>>) SerializationOperations.readFile("src/DataAccess/mapFile.txt"));
            }
            tasksManagement.getMapOfTasks().put(employee,new ArrayList<>());
            SerializationOperations.writeFile(tasksManagement.getMapOfTasks());
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

    public List<HourRetainer> setHourRetainerForEmployees() throws Exception{
        tasksManagement.deserializeMap();
        for(Map.Entry<Employee,List<Task>> entry : tasksManagement.getMapOfTasks().entrySet()){
            Employee employee = entry.getKey();
            if(calculateEmployeeWorkDuration(employee.getIdEmployee()) >40) {
                listOfSchedulesForEmployees.add(new HourRetainer(entry.getKey(), calculateEmployeeWorkDuration(entry.getKey().getIdEmployee())));
            }
        }
        return listOfSchedulesForEmployees;
    }

    public int calculateEmployeeWorkDuration(int idEmployee) throws Exception{
        List<Task> tasksOfEmployer = tasksManagement.findListOfTasksFromMap(idEmployee);
        int totalOfHours = calculateNrOfHours(tasksOfEmployer);
        return totalOfHours;
    }

}
