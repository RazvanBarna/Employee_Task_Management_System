package BusinessLogic;
import DataModel.Employee;
import DataModel.EmployeeHourRetainer;
import DataModel.Task;

import java.util.*;

public class EmployeesManagement {
    TasksManagement tasksManagement ;
    List<EmployeeHourRetainer> listOfHourRetainerForEmployee = new ArrayList<>();

    public EmployeesManagement(TasksManagement tasksManagement){
        this.tasksManagement = tasksManagement;
    }

    private boolean checkIfEmployeeAlreadyExists(Employee employee){
        for(Map.Entry<Employee,List<Task>> entry : tasksManagement.getMapOfTasks().entrySet()){
            if(entry.getKey().equals(employee)) return true;
        }
        return false;
    }

    public void addEmployee(Employee employee) throws Exception{
        if(employee==null){
            throw new RuntimeException("Employee is null");
        }
        else if (!checkIfEmployeeAlreadyExists(employee)){
            tasksManagement.deserializeMap();
            tasksManagement.getMapOfTasks().put(employee,new ArrayList<>());
            tasksManagement.serializeMap();
        }
        else throw new RuntimeException("This employee already exists");
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

    public List<EmployeeHourRetainer> setHourRetainerForEmployee() throws Exception{
        tasksManagement.deserializeMap();
        for(Map.Entry<Employee,List<Task>> entry : tasksManagement.getMapOfTasks().entrySet()){
            Employee employee = entry.getKey();
            if(calculateEmployeeWorkDuration(employee.getIdEmployee()) >40) {
                listOfHourRetainerForEmployee.add(new EmployeeHourRetainer(entry.getKey(), calculateEmployeeWorkDuration(entry.getKey().getIdEmployee())));
            }
        }
        return listOfHourRetainerForEmployee;
    }

    public int calculateEmployeeWorkDuration(int idEmployee) throws Exception{
        List<Task> tasksOfEmployer = tasksManagement.findListOfTasksFromMap(idEmployee);
        int totalOfHours = calculateNrOfHours(tasksOfEmployer);
        return totalOfHours;
    }

}
