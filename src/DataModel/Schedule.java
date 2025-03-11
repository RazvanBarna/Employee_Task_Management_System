package DataModel;

public class Schedule {
    private Employee employee;
    private  int nrOfHoursWork;

    public Schedule(Employee employee, int nrOfHoursWork){
        this.employee = employee;
        this.nrOfHoursWork = nrOfHoursWork;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public int getNrOfHoursWork() {
        return nrOfHoursWork;
    }

    public void setNrOfHoursWork(int nrOfHoursWork) {
        this.nrOfHoursWork = nrOfHoursWork;
    }

    @Override
    public String toString() {
        return "Schedule{" + "employee=" + employee + ", nrOfHoursWork=" + nrOfHoursWork + '}';
    }

}
