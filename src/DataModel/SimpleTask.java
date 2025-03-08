package DataModel;

public class SimpleTask extends Task{

    public SimpleTask(String statusTask, String title, int startHour, int endHour, String description) {
        super(statusTask, title, startHour, endHour);
    }

    @Override
    public int estimateDuration(){
        return this.getEndHour()-this.getStartHour();
    }

}
