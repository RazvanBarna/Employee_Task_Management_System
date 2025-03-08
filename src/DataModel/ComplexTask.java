package DataModel;

public class ComplexTask extends Task{
    private String description;

    public ComplexTask(String statusTask, String title, int startHour, int endHour, String description) {
        super(statusTask, title, startHour, endHour);
        this.description = description;
    }

    @Override
    public int estimateDuration() {
        return this.getEndHour()-this.getStartHour();
    }

    public String toString(){
        return super.toString()+" description:"+this.description;
    }

}
