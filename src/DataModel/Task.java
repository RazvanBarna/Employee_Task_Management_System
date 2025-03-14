package DataModel;

import java.io.Serializable;

public abstract class Task implements Serializable {
    private int idTask;
    private String statusTask;
    private String title;

    public Task(String statusTask, String title, int idTask) {
        this.idTask=idTask;
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
        return this.idTask == task.getIdTask() || this.getTitle().equals(task.getTitle());
    }

}
