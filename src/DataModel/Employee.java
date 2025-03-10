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
        return String.format("ID: %d, Name: %s, Age: %d",
                idEmployee, name, age);
    }

    @Override
    public int hashCode() {
        return idEmployee;
    }

}
