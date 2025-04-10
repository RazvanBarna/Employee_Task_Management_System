package BusinessLogic;
import DataAccess.SerializationOperations;
import DataModel.ComplexTask;
import DataModel.Employee;
import DataModel.Task;

import java.io.File;
import java.util.*;

public class TasksManagement {
    private Map<Employee,List<Task>> mapOfTasks = new HashMap<>();
    private String errorMessageTask ="";
    private static Integer idAutoIncrement =1;
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

    public void deserializeId() throws Exception{
        if(new File("src/DataAccess/idTask.txt").length() >0) {
            idAutoIncrement = (Integer) SerializationOperations.readFile("src/DataAccess/idTask.txt");
            idAutoIncrement++;
        }
        else idAutoIncrement=1;
    }

    public void serializeId() throws Exception{
        SerializationOperations.writeFile(idAutoIncrement);
    }


    public List<Task> getListOfTaskUnssigned() {
        return listOfTaskUnssigned;
    }

    public void addTaskInApplication(Task task) throws Exception{
        this.deserializeId();

        if(task==null){
            errorMessageTask ="The task must have an input.";
            throw new Exception(errorMessageTask);
        }
        else {
            this.deserializeTaskList();
            if(!hasAlreadyTheTask(this.listOfTaskUnssigned,task)){
                listOfTaskUnssigned.add(task);
                this.serializeTaskList();
            }
            else {
                errorMessageTask = " This task already exists";
                throw new Exception(errorMessageTask);
            }
        }
    }

    public void addTaskInComplexTask(ComplexTask complexTask, Task taskToAdd) throws Exception {
        if (taskToAdd == null) {
            errorMessageTask = "The taskToAdd must have an input.";
            return;
        }
        List<ComplexTask> complexTasks = this.fillAllComplexTaskFromMapAndUnassigned();
        for(ComplexTask complexFromList : complexTasks)
            if(complexFromList.equals(complexTask))
                complexFromList.addTask(taskToAdd);

        this.listOfTaskUnssigned.remove(taskToAdd);
        this.serializeMap();
        this.serializeTaskList();

    }

    public void deleteTaskInComplexTask(ComplexTask complexTask, Task taskToDelete) throws Exception{
        if(taskToDelete ==null){
            errorMessageTask ="The taskToDelete must have an input.";
            return;
        }
        List<ComplexTask> listOfComplexTasks = this.fillAllComplexTaskFromMapAndUnassigned();

        for(ComplexTask complexFromList : listOfComplexTasks)
                if(complexFromList.equals(complexTask))
                    complexFromList.deleteTask(taskToDelete);


        this.listOfTaskUnssigned.add(taskToDelete);
        this.serializeTaskList();
        this.serializeMap();
    }

    public List<Employee> getListOfEmployeesFromMap() throws Exception{
        this.deserializeMap();
        List<Employee> employees= new ArrayList<>();
        for(Map.Entry<Employee,List<Task>> entry: mapOfTasks.entrySet())
            employees.add(entry.getKey());

        return employees;
    }

    private boolean hasAlreadyTheTask(List<Task> tasksOfEmployee, Task task){
        for ( Task taskIndex : tasksOfEmployee) {
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
            errorMessageTask = "The employee with this id does not exist !";
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
        for(Task complexToAdd : this.listOfTaskUnssigned)
            if (complexToAdd instanceof ComplexTask)
                allComplexTasksRecursively((ComplexTask) complexToAdd,complexTasks);

        this.deserializeMap();
        for (Map.Entry<Employee, List<Task>> entry : this.mapOfTasks.entrySet())
            for (Task complexToAdd : entry.getValue())
                if (complexToAdd instanceof ComplexTask && !complexTasks.contains(complexToAdd))
                    allComplexTasksRecursively((ComplexTask) complexToAdd,complexTasks);

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

    public static Integer getIdAutoIncrement() {
        return idAutoIncrement;
    }
}
