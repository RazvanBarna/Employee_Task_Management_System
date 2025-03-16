package DataModel;

public class SimpleTask extends Task{
    private int startHour;
    private int endHour;

    public SimpleTask(String statusTask, String title, int startHour, int endHour) throws Exception {
        super(statusTask, title);
        this.endHour = endHour;
        this.startHour = startHour;
    }

    @Override
    public int estimateDuration(){
        if(this.endHour < this.startHour){
            return 24 + this.endHour - this.startHour;
        }
        return this.endHour-this.startHour;
    }

    @Override
    public String toString() {
        return String.format("%s, Duration: %d hours", super.toString(), estimateDuration());
    }

}
