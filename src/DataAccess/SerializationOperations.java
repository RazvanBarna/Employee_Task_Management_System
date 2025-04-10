package DataAccess;
import DataModel.Employee;
import DataModel.Task;

import java.io.*;
import java.util.List;
import java.util.Map;

public class SerializationOperations {

    private static void initWriteFile(String fileName,Object objectToWrite) throws Exception{
        FileOutputStream fileOutputStream = new FileOutputStream(fileName,false);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(objectToWrite);
        objectOutputStream.flush();
        objectOutputStream.close();
    }

    public static void writeFile(Object objectSerialized) throws Exception{
        if (objectSerialized instanceof Map<?, ?>) {
            initWriteFile("src/DataAccess/mapFile.txt", objectSerialized);
        } else if (objectSerialized instanceof List<?>) {
            initWriteFile("src/DataAccess/taskFile.txt", objectSerialized);
        }
        else initWriteFile("src/DataAccess/idTask.txt",objectSerialized);
    }

    private static Object initRead(String file) throws Exception{
        FileInputStream fileInputStream = new FileInputStream(file);
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        Object objectRead = objectInputStream.readObject();
        objectInputStream.close();
        return objectRead;
    }

    public static Object readFile(String file) throws Exception {
        Object objectRead = initRead(file);
        if(file.equals("src/DataAccess/taskFile.txt"))
        {
            return (List<Task>) objectRead;
        }
        else if (file.equals("src/DataAccess/mapFile.txt")){
            return (Map<Employee,List<Task>>)objectRead;
        }
        else return (Integer) objectRead;
    }


}
