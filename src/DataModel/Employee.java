package DataModel;

import java.io.Serializable;
import java.util.Objects;

public class Employee implements Serializable {
    private String name;
    private int idEmployee;
    private int age;

    public Employee(String name, int age,int idEmployee) {
        this.name = name;
        this.idEmployee = idEmployee;
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

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Employee employee = (Employee) obj;
        if (idEmployee == employee.getIdEmployee()) {
            return true;
        }
        return false;
    }
}
