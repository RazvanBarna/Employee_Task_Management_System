package BusinessLogic;
import DataAccess.SerializationOperations;
import DataModel.ComplexTask;
import DataModel.Employee;
import DataModel.Task;

import java.util.*;

public class TasksManagement {
    private Map<Employee,List<Task>> mapOfTasks = new HashMap<>();
    private String errorMessageTaskk="";
    private List<Task> listOfTaskUnssigned= new ArrayList<>();

    public Map<Employee, List<Task>> getMapOfTasks() {
        return mapOfTasks;
    }

    public List<Task> getListOfTaskUnssigned() {
        return listOfTaskUnssigned;
    }

    public void setMapOfTasks(Map<Employee, List<Task>> mapOfTasks) {
        this.mapOfTasks = mapOfTasks;
    }

    public String getErrorMessageTaskk() {
        return errorMessageTaskk;
    }

    public void addTaskInApplication(Task task) throws Exception{
       //this.listOfTaskUnssigned =(List<Task>) SerializationOperations.readFile("src/DataAccess/taskFile.txt");
        if(task==null){
            errorMessageTaskk ="The task must have an input.";
        }
        else {
            if(!hasAlreadyTheTask(this.listOfTaskUnssigned,task)){
                listOfTaskUnssigned.add(task);
                SerializationOperations.writeFile(listOfTaskUnssigned);
                //System.out.println("Tasks after serialization: " + listOfTaskUnssigned);
                this.listOfTaskUnssigned = (List<Task>) SerializationOperations.readFile("src/DataAccess/taskFile.txt");
                //System.out.println("Tasks after deserialization: " + listOfTaskUnssigned);
            }
            else {
                errorMessageTaskk = " This task already exists";
            }
        }
    }

    protected List<Task> findListOfTasksFromMap(int idEmployee){
        for(Map.Entry<Employee,List<Task>> entry: mapOfTasks.entrySet()){
            if( entry.getKey().getIdEmployee() ==idEmployee ){
                return entry.getValue();
            }
        }
        return null;
    }

    private boolean hasAlreadyTheTask(List<Task> tasksOfEmployer,Task task){
        for ( Task taskIndex : tasksOfEmployer ) {
            if (taskIndex.equals(task)) {
                return true;
            }
            if ( taskIndex instanceof ComplexTask ) {
                if ( ( ( ComplexTask) taskIndex).findTask(task) != null ) {
                    return true;
                }
            }
        }
        return false;
    }

    public void assignTaskToEmployee(int idEmployee,Task currentTask) throws Exception{
        List<Task> tasksOfEmployer = findListOfTasksFromMap(idEmployee);

        if(tasksOfEmployer == null){
            errorMessageTaskk = "The employee with this id does not exist !";
        }
        else if(hasAlreadyTheTask(tasksOfEmployer,currentTask)) throw  new RuntimeException("The task is already assign to this employee");
        else if(!hasAlreadyTheTask(this.listOfTaskUnssigned,currentTask)){
            tasksOfEmployer.add(currentTask);
            listOfTaskUnssigned.remove(currentTask);
            SerializationOperations.writeFile(listOfTaskUnssigned);
            this.listOfTaskUnssigned = (List<Task>) SerializationOperations.readFile("src/DataAccess/taskFile.txt");
        }
    }

    private Task findTaskInListToModify(List <Task> tasks, int idTask){
        if (tasks !=null) {
            for (Task taskIndex : tasks) {
                if (taskIndex.getIdTask() == idTask) {
                    return taskIndex;
                }
            }
        }
        return null;
    }

    private void modifyStatusForList(List<Task> tasks, String status){
        for(Task task : tasks){
            task.setStatusTask(status);
        }
    }

    public void modifyTaskStatus(int idEmployee,int idTask,String statusModified){
        List<Task> tasksOfEmployer = findListOfTasksFromMap(idEmployee);
        Task taskToModify = findTaskInListToModify(tasksOfEmployer,idTask);
        if (taskToModify!=null) {
            if(taskToModify instanceof ComplexTask){
                modifyStatusForList(((ComplexTask) taskToModify).getTasksOfaComplexTask(),statusModified);
            }
        } else {
        errorMessageTaskk = "Task with the given ID does not exist for this employee.";
        }
    }

}
