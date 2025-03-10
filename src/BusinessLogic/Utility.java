package BusinessLogic;

import DataModel.Employee;
import DataModel.Task;

import java.util.*;

public class Utility {
    private EmployeesManagement employeesManagement ;
    private TasksManagement tasksManagement ;

    public Utility(TasksManagement tasksManagement,EmployeesManagement employeesManagement){
        this.tasksManagement = tasksManagement;
        this.employeesManagement = employeesManagement;
    }

    public List<Employee> sortByNrOfHoursGt40 ( List<Employee> employees){
        for(Employee employee : employees){
            this.employeesManagement.calculateEmployeeWorkDuration(employee.getIdEmployee()) ;
        }
        Collections.sort(employees, new CompareByHours());
        for ( Employee employee : employees){
            System.out.println(employee.toString());
        }
        return employees;
    }

    private HashMap<String , Map<String, Integer>> initMap(List<Employee> employees){
        HashMap<String , Map<String, Integer>> map= new HashMap<String , Map<String, Integer>>();
        for(Employee employee : employees){
            map.put(employee.getName(),new HashMap<>());

        }
        return map;
    }

    private List<Task> getListOfTaskWithEmployeessName(String employeeName){
        for(Map.Entry<Employee,List<Task>> entry: tasksManagement.getMapOfTasks().entrySet()){
            if(entry.getKey().getName().equals(employeeName)){
                return entry.getValue();
            }
        }
        return null;
    }

    private int nrOfStatusTasks(List<Task> tasks,String status){
        int totalNumber=0;
        for(Task task : tasks){
            if(task.getStatusTask().equals(status)){
                totalNumber++;
            }
        }
        return totalNumber;
    }

    private HashMap<String , Integer> initInnerHash(List<Task> tasks){
        HashMap<String, Integer> map = new HashMap<>();
        map.put("Completed", nrOfStatusTasks(tasks,"Completed"));
        map.put("Uncompleted", nrOfStatusTasks(tasks,"Uncompleted"));
        return map;
    }

    public Map<String,Map<String,Integer>> calculateStatusOfTaskPerEmployee(List<Employee> employees){
        HashMap<String , Map<String, Integer>> map = initMap(employees);
        for(Map.Entry<String , Map<String, Integer>> entry: map.entrySet()){
            if(getListOfTaskWithEmployeessName(entry.getKey()) == null){
                entry.setValue(new HashMap<>());
            }
            HashMap <String, Integer> innerHash = initInnerHash(getListOfTaskWithEmployeessName(entry.getKey()));
            entry.setValue(innerHash);
        }

        return map;
    }

    class CompareByHours implements Comparator<Employee>{
        @Override
        public int compare(Employee e1, Employee e2) {
           // if (e1.getNrHoursWorks() > e2.getNrHoursWorks()) return 1;
            //if (e1.getNrHoursWorks() < e2.getNrHoursWorks()) return -1;
            return 0;
        }
    }
}
