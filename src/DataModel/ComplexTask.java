package DataModel;

import java.util.ArrayList;
import java.util.List;

public class ComplexTask extends Task{
    private List<Task> tasksOfaComplexTask = new ArrayList<>();

    public ComplexTask(String statusTask, String title) throws Exception{
        super(statusTask, title);
    }

    public ComplexTask(){};

    public List<Task> getTasksOfComplexTask() {
        return tasksOfaComplexTask;
    }

    public void setTasksOfaComplexTask(List<Task> tasksOfaComplexTask) {
        this.tasksOfaComplexTask = tasksOfaComplexTask;
    }

    public void addTask(Task task){
        tasksOfaComplexTask.add(task);
    }

    public void deleteTask(Task task){
        this.tasksOfaComplexTask.remove(task);
    }

    public Task findTask(Task task){
        for(Task taskIndex :tasksOfaComplexTask){
            if (taskIndex.equals(task)) {
                return taskIndex;
            }
            else if(taskIndex instanceof ComplexTask){
                Task taskIndex2 = ((ComplexTask) taskIndex).findTask(task);
                if(taskIndex2!=null){
                    return taskIndex2;
                }
            }
        }
        return null;
    }

    @Override
    public int estimateDuration() {
        int totalDuration = 0;
        for(Task task : tasksOfaComplexTask){
          totalDuration+= task.estimateDuration();
        }
        return totalDuration;
    }

    public String toString(){
        return String.format("%s | Estimated Duration: %d", super.toString(), estimateDuration());
    }

}
