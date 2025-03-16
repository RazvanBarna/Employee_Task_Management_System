package DataModel;

import DataAccess.SerializationOperations;

import java.io.File;
import java.io.Serializable;

public abstract class Task implements Serializable {
    private int idTask;
    private static Integer idAutoIncrement =1;
    private String statusTask;
    private String title;

    public Task(String statusTask, String title) throws Exception {
        if(new File("src/DataAccess/idTask.txt").length() >0) {
            idAutoIncrement = (Integer) SerializationOperations.readFile("src/DataAccess/idTask.txt");
        }
        this.idTask=idAutoIncrement++;
        SerializationOperations.writeFile(idAutoIncrement);
        this.statusTask = statusTask;
        this.title = title;
    }

    public Task(){};

    public String getStatusTask() {
        return statusTask;
    }

    public int getIdTask() {
        return idTask;
    }

    public String getTitle() {
        return title;
    }

    public void setStatusTask(String statusTask) {
        this.statusTask = statusTask;
    }

    public abstract int estimateDuration();

    public String toString(){
        return String.format("ID: %d, Title: %s, Status: %s",
                idTask, title, statusTask);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null ||!(obj instanceof Task)) return false;
        Task task = (Task) obj;
        return this.idTask == task.getIdTask();
    }

}
