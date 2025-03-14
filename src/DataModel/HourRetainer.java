package DataModel;

import java.util.Objects;

public class HourRetainer {
    private Employee employee;
    private  int nrOfHoursWork;

    public HourRetainer(Employee employee, int nrOfHoursWork){
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

    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        HourRetainer that = (HourRetainer) obj;
        return nrOfHoursWork == that.nrOfHoursWork && employee.equals(this.getEmployee());
    }

}
