import BusinessLogic.TasksManagement;
import DataModel.ComplexTask;
import DataModel.Employee;
import DataModel.SimpleTask;
import DataModel.Task;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        try {

            Employee employee1 = new Employee("John Doe", 30);


            Task task1 = new SimpleTask("Complete documentation", "Pending", 0, 1);
            Task task2 = new ComplexTask("Develop feature X", "In Progress");


            TasksManagement tasksManagement = new TasksManagement();
            tasksManagement.addTaskInApplication(task1);
            tasksManagement.addTaskInApplication(task2);
            for(Task task : tasksManagement.getListOfTaskUnssigned()){
                System.out.println(task.toString());
            }
            tasksManagement.getMapOfTasks().put(employee1,new ArrayList<>());


             //Verificăm dacă task-urile au fost adăugate
            //System.out.println("List of unassigned tasks:");
            //tasksManagement.getListOfTaskUnssigned().forEach(task -> System.out.println(task.toString()));

            // Testarea metodei assignTaskToEmployee
            tasksManagement.assignTaskToEmployee(employee1.getIdEmployee(), task1); // Atribuim task1 angajatului
            tasksManagement.assignTaskToEmployee(employee1.getIdEmployee(), task2); // Atribuim task2 angajatului

            // Verificăm lista de task-uri atribuite angajatului
            //System.out.println("Tasks assigned to employee " + employee1.getName() + ":");
            //tasksManagement.getMapOfTasks().get(employee1).forEach(task -> System.out.println(task.toString()));

            // Modificăm statusul unui task
            tasksManagement.modifyTaskStatus(employee1.getIdEmployee(), task1.getIdTask(), "Completed");

            // Verificăm dacă statusul a fost modificat
            //System.out.println("Status for task1 after modification: " + task1.getStatusTask());

        } catch (Exception e) {
            e.printStackTrace();
        }


             }
        }