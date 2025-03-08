package BusinessLogic;
import DataAccess.SerializationOperations;
import DataModel.Employee;
import DataModel.Task;

import java.util.*;

public class TasksManagement {
    private Map<Employee,List<Task>> mapOfTasks = new HashMap<>();
    private String errorMessageTaskk ="";
    SerializationOperations serializationOperations = new SerializationOperations();

    public Map<Employee, List<Task>> getMapOfTasks() {
        return mapOfTasks;
    }

    public void addTask(Task task){
        if(task==null){
            errorMessageTaskk ="The task must have an input.";
        }
        else {
            serializationOperations.writeFile(task);
        }
    }

    /*public void deleteTask(Task task){
        for(Task taskIndex: this.listOfTasks){
            if(taskIndex.equals(task)){
                this.listOfTasks.remove(task);
                return;
            }
        }
        errorMessageTaskk ="The task you want to delete does not exist.";
    }

     */
    public List<Task> findListOfTasksFromMap(int idEmployee){
        for(Map.Entry<Employee,List<Task>> entry: mapOfTasks.entrySet()){
            if(entry.getKey().getIdEmployee() ==idEmployee){
                return entry.getValue();
            }
        }
        return null;
    }

    public boolean hasAlreadyTheTask(List<Task> tasksOfEmployer,Task task){
        for(Task taskIndex : tasksOfEmployer){
            if(taskIndex.equals(task)){
                return false;
            }
        }
        return true;
    }

    public void assignTaskToEmployee(int idEmployee,Task currentTask){
        List<Task> tasksOfEmployer = findListOfTasksFromMap(idEmployee);
        if(currentTask !=null && tasksOfEmployer !=null && hasAlreadyTheTask(tasksOfEmployer,currentTask)){
            tasksOfEmployer.add(currentTask);
        }
    }

    public Task findTaskInListModify(List <Task> tasks,int idTask){
        if (tasks !=null) {
            for (Task taskIndex : tasks) {
                if (taskIndex.getIdTask() == idTask) {
                    return taskIndex;
                }
            }
        }
        return null;
    }

    public void modifyTaskStatus(int idEmployee,int idTask,String statusModified){
        List<Task> tasksOfEmployer = findListOfTasksFromMap(idEmployee);
        Task taskToModify = findTaskInListModify(tasksOfEmployer,idTask);
        if (taskToModify!=null) {
            taskToModify.setStatusTask(statusModified);
        }
    }

}
