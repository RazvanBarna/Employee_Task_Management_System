package DataModel;

import java.io.Serializable;

public class Employee implements Serializable {
    private String name;
    private static int idStatic=1;
    private int idEmployee;
    private int age;
    private int nrHoursWorks;

    public Employee(String name, int age) {
        this.name = name;
        this.idEmployee = idStatic++;
        this.age = age;
        this.nrHoursWorks=0;
    }

    public Employee(){};

    public int getIdEmployee() {
        return idEmployee;
    }

    public String getName() {
        return name;
    }

    public int getNrHoursWorks() {
        return nrHoursWorks;
    }

    public void setNrHoursWorks(int nrHoursWorks) {
        this.nrHoursWorks = nrHoursWorks;
    }

    public String toString(){
        return this.idEmployee+" name: "+this.name+" age: "+this.age+" nr. Hours :"+nrHoursWorks;
    }

    @Override
    public int hashCode() {
        return idEmployee;
    }
}
