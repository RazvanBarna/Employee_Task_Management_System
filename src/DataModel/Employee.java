package DataModel;

import java.io.Serializable;

public class Employee implements Serializable {
    private String name;
    private static int idStatic=1;
    private int idEmployee;
    private int age;

    public Employee(String name, int age) {
        this.name = name;
        this.idEmployee = idStatic++;
        this.age = age;
    }

    public Employee(){};

    public int getIdEmployee() {
        return idEmployee;
    }

    public String getName() {
        return name;
    }

    public String toString(){
        return this.idEmployee+" "+this.name+" "+this.age;
    }
}
