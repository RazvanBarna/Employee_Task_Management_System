package DataAccess;

import DataModel.ComplexTask;
import DataModel.Employee;
import DataModel.SimpleTask;
import DataModel.Task;
import com.sun.source.tree.BreakTree;

import java.io.*;
import java.nio.file.Path;

public class SerializationOperations {
    private String errorMessage="";

    public Boolean isEmployee(Object object) {
        return object instanceof Employee;
    }

    public Boolean isTask(Object object){
        return object instanceof Task;
    }

    public void initWriteFile(String fileName,Object objectToWrite) throws Exception{
        FileOutputStream fileOutputStream = new FileOutputStream(fileName,true);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(objectToWrite);
        objectOutputStream.flush();
        objectOutputStream.close();
    }

    public void writeFile(Object objectSerialized){
        try{
            if (isEmployee(objectSerialized)){
                initWriteFile("src/DataAccess/employeeFile.txt",objectSerialized);
            }
            else if (isTask(objectSerialized)){
                initWriteFile("src/DataAccess/taskFile.txt",objectSerialized);
            }
        } catch (Exception e){
            errorMessage=e.getMessage();
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    //TO do : will be 2 methods for read : one for employee and return list and one with tasks
    public void readAllObjectsFromFile(String file) {
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            Object objectRead = objectInputStream.readObject();
            objectInputStream.close();

            if( objectRead instanceof ComplexTask){
                Task task = (ComplexTask) objectRead;
                System.out.println(task.toString());
            }
            else if ( objectRead instanceof SimpleTask){
                Task task = (SimpleTask) objectRead;
                System.out.println(task.toString());
            }
            else if ( objectRead instanceof Employee){
                Employee employee = (Employee) objectRead;
                System.out.println(employee.toString());
            }

        } catch (Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }


}
