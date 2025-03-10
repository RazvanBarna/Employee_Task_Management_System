package DataModel;

import java.io.Serializable;

public abstract class Task implements Serializable {
    private static int idStatic=1;
    private int idTask;
    private String statusTask;
    private String title;

    public Task(String statusTask, String title) {
        this.idTask=idStatic++;
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

    public static int getIdStatic() {
        return idStatic;
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


}
