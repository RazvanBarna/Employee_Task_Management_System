package BusinessLogic;
import DataModel.Employee;
import DataModel.Task;

import java.util.*;

public class TasksManagement {
    private List<Task> listOfTasks = new ArrayList<>();
    private Map<Employee,List<Task>> mapOfTasks = new HashMap<>();
    private String errorMessageTaskk =" ";

    public void addTask(Task task){
        if(task==null){
            errorMessageTaskk ="The task must have an input.";
        }
        else {
            this.listOfTasks.add(task);
        }
    }

    public void deleteTask(Task task){
        for(Task taskIndex: this.listOfTasks){
            if(taskIndex.equals(task)){
                this.listOfTasks.remove(task);
                return;
            }
        }
        errorMessageTaskk ="The task you want to delete does not exist.";
    }

}
