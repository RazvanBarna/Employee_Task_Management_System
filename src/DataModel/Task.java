package DataModel;

import java.io.Serializable;

public abstract class Task implements Serializable {
    private static int idStatic=1;
    private int idTask;
    private String statusTask;
    private String title;
    private int startHour;
    private int endHour;

    public Task(String statusTask, String title, int startHour, int endHour) {
        this.idTask=idStatic++;
        this.statusTask = statusTask;
        this.title = title;
        this.startHour = startHour;
        this.endHour = endHour;
    }

    public Task(){};

    public String getStatusTask() {
        return statusTask;
    }

    public int getIdTask() {
        return idTask;
    }

    public int getStartHour() {
        return startHour;
    }

    public int getEndHour() {
        return endHour;
    }

    public static int getIdStatic() {
        return idStatic;
    }

    public String getTitle() {
        return title;
    }

    public abstract int estimateDuration();

    public String toString(){
        return this.idTask+" "+this.title+" start hour:"+this.startHour+" end hour:"+this.endHour+" status:"+this.statusTask;
    }


}
