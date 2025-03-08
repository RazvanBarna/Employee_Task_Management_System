import BusinessLogic.EmployeesManagement;
import BusinessLogic.TasksManagement;
import DataAccess.SerializationOperations;
import DataModel.ComplexTask;
import DataModel.Employee;
import DataModel.SimpleTask;
import DataModel.Task;
import GUI.MainMenu;

import javax.swing.*;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {

       /* Employee e1 = new Employee("John",30);
        Employee e2 = new Employee("Jerome",20);
        Employee e3 = new Employee("Marius",40);
        TasksManagement tasksManagement = new TasksManagement();
        EmployeesManagement employeesManagement = new EmployeesManagement(tasksManagement);
        employeesManagement.addEmployee(e1);
        employeesManagement.addEmployee(e2);
        employeesManagement.addEmployee(e3);

        Task t1 =new ComplexTask("b","c",11,12,"aa");
        Task t2= new SimpleTask("a","b",0,1);
        Task t3 =new ComplexTask("c","d",10,20,"bb");

        tasksManagement.addTask(t1);
        tasksManagement.addTask(t2);
        tasksManagement.addTask(t3);

        tasksManagement.assignTaskToEmployee(e1.getIdEmployee(),t1);
        tasksManagement.assignTaskToEmployee(e1.getIdEmployee(),t2);
        tasksManagement.assignTaskToEmployee(e1.getIdEmployee(),t3);
        tasksManagement.assignTaskToEmployee(e2.getIdEmployee(),t2);
        tasksManagement.assignTaskToEmployee(e3.getIdEmployee(),t3);

        tasksManagement.modifyTaskStatus(e1.getIdEmployee(), t1.getIdTask(),"Completed");
       System.out.println( employeesManagement.calculateEmployeeWorkDuration(1));

        for(Map.Entry<Employee, List<Task>> entry: tasksManagement.getMapOfTasks().entrySet()){
            System.out.println(entry.getKey().toString());
            System.out.println(entry.getValue());
        }

        */

        TasksManagement tasksManagement = new TasksManagement();
        EmployeesManagement employeesManagement = new EmployeesManagement(tasksManagement);
        new MainMenu(employeesManagement,tasksManagement);



    }
}