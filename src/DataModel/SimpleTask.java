package DataModel;

public class SimpleTask extends Task{
    private int startHour;
    private int endHour;

    public SimpleTask(String statusTask, String title, int startHour, int endHour) {
        super(statusTask, title);
        this.endHour = endHour;
        this.startHour = startHour;
    }

    @Override
    public int estimateDuration(){
        return this.endHour-this.startHour;
    }

    @Override
    public String toString() {
        return String.format("%s, Duration: %d hours", super.toString(), estimateDuration());
    }

}
