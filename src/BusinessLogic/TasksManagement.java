package BusinessLogic;
import DataAccess.SerializationOperations;
import DataModel.ComplexTask;
import DataModel.Employee;
import DataModel.Task;

import java.io.File;
import java.util.*;

public class TasksManagement {
    private Map<Employee,List<Task>> mapOfTasks = new HashMap<>();
    private String errorMessageTaskk="";
    private List<Task> listOfTaskUnssigned= new ArrayList<>();

    public Map<Employee, List<Task>> getMapOfTasks() {
        return mapOfTasks;
    }

    public void serializeTaskList() throws Exception{
        SerializationOperations.writeFile(this.listOfTaskUnssigned);
    }

    public void deserializeTaskList() throws Exception{
        File file = new File("src/DataAccess/taskFile.txt");
        if(file.length()> 0) {
            this.listOfTaskUnssigned = (List<Task>) SerializationOperations.readFile("src/DataAccess/taskFile.txt");
        }
        else this.listOfTaskUnssigned = new ArrayList<>();
    }

    public void serializeMap() throws Exception{
        SerializationOperations.writeFile(this.mapOfTasks);
    }

    public void deserializeMap() throws Exception{
        File file = new File("src/DataAccess/mapFile.txt");
        if(file.length()> 0) {
            this.mapOfTasks = (Map<Employee, List<Task>>) SerializationOperations.readFile("src/DataAccess/mapFile.txt");
        }
        else this.mapOfTasks = new HashMap<>();
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
        if(task==null){
            errorMessageTaskk ="The task must have an input.";
            throw new Exception(errorMessageTaskk);
        }
        else {
            this.deserializeTaskList();
            if(!hasAlreadyTheTask(this.listOfTaskUnssigned,task)){
                listOfTaskUnssigned.add(task);
                this.serializeTaskList();
            }
            else {
                errorMessageTaskk = " This task already exists";
                throw new Exception(errorMessageTaskk);
            }
        }
    }

    public void addTaskInComplexTask(ComplexTask complexTask, Task task) throws Exception{
        if(task==null){
            errorMessageTaskk ="The task must have an input.";
        }
        else {
                this.deserializeTaskList();
                for(Task task1 : listOfTaskUnssigned){
                    if(task1 instanceof ComplexTask && task1.equals(complexTask)){
                        if(!hasAlreadyTheTask(((ComplexTask) task1).getTasksOfComplexTask(),task))
                            ((ComplexTask) task1).getTasksOfComplexTask().add(task);
                    }
                    listOfTaskUnssigned.remove(task);
                    break;
                }

                this.serializeTaskList();
            }
        }

    public void deleteTaskInComplexTask(ComplexTask complexTask, Task task) throws Exception{
        if(task==null){
            errorMessageTaskk ="The task must have an input.";
            return;
        }
        List<ComplexTask> listOfComplexTasks = this.fillAllComplexTaskFromMapAndUnassigned();

        for(Task task1 : listOfComplexTasks)
            if(task1 instanceof ComplexTask)
                if(task1.equals(complexTask))
                  ((ComplexTask) task1).getTasksOfComplexTask().remove(task);


        this.listOfTaskUnssigned.add(task);
        this.serializeTaskList();
    }

    public List<Employee> getListOfEmployeesFromMap() throws Exception{
        this.deserializeMap();
        List<Employee> employees= new ArrayList<>();
        for(Map.Entry<Employee,List<Task>> entry: mapOfTasks.entrySet())
            employees.add(entry.getKey());

        return employees;
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

    public List<Task> findListOfTasksFromMap(int idEmployee) throws Exception{
        this.deserializeMap();
        for(Map.Entry<Employee,List<Task>> entry: mapOfTasks.entrySet())
            if( entry.getKey().getIdEmployee() == idEmployee )
                return entry.getValue();

        return null;
    }

    public void assignTaskToEmployee(int idEmployee,Task currentTask) throws Exception{
        List<Task> tasksOfEmployer = findListOfTasksFromMap(idEmployee);
        if(tasksOfEmployer == null)
            errorMessageTaskk = "The employee with this id does not exist !";
        else {
            tasksOfEmployer.add(currentTask);
            listOfTaskUnssigned.remove(currentTask);
            this.serializeTaskList();
            this.serializeMap();
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

    public void modifyTaskStatus(int idEmployee, int idTask, String statusModified) throws Exception{
        List<Task> tasksOfEmployer = findListOfTasksFromMap(idEmployee);
        Task taskToModify = findTaskInListToModify(tasksOfEmployer,idTask);
        taskToModify.setStatusTask(statusModified);
        this.serializeMap();
    }


    public List<ComplexTask> fillAllComplexTaskFromMapAndUnassigned() throws Exception{
        List<ComplexTask> complexTasks = new ArrayList<>();

        this.deserializeTaskList();
        for(Task task : this.listOfTaskUnssigned)
            if (task instanceof ComplexTask)
                allComplexTasksRecursively((ComplexTask) task,complexTasks);

        this.deserializeMap();
        for (Map.Entry<Employee, List<Task>> entry : this.mapOfTasks.entrySet())
            for (Task task : entry.getValue())
                if (task instanceof ComplexTask && !complexTasks.contains(task))
                    allComplexTasksRecursively((ComplexTask) task,complexTasks);

        int a=0;
        for(ComplexTask complexTask :complexTasks){
           // System.out.println(a++);
          //  System.out.println(complexTask);
            //System.out.println("Inainte de for 1");
            for(Task task : complexTask.getTasksOfComplexTask()){
            //    System.out.println(task);
            }
        }

        return complexTasks;
    }

    private void allComplexTasksRecursively(ComplexTask complexTaskFromList, List<ComplexTask> complexTasks) {
        if (!complexTasks.contains(complexTaskFromList)) {
            complexTasks.add(complexTaskFromList);
            for (Task task : complexTaskFromList.getTasksOfComplexTask())
                if (task instanceof ComplexTask)
                    allComplexTasksRecursively((ComplexTask) task, complexTasks);
        }

    }

}
