package BusinessLogic;

import DataModel.Employee;
import DataModel.Schedule;
import DataModel.Task;

import java.util.*;

public class Utility {
    //private EmployeesManagement employeesManagement ;
    //private TasksManagement tasksManagement ;

    //public Utility(TasksManagement tasksManagement,EmployeesManagement employeesManagement){
      //  this.tasksManagement = tasksManagement;
        //this.employeesManagement = employeesManagement;
    //}

    public static List<Schedule> sortByNrOfHoursGt40 (List<Schedule> schedules){
        Collections.sort(schedules, new Comparator<Schedule>(){
            public int compare(Schedule s1, Schedule s2) {
                if (s1.getNrOfHoursWork() > s2.getNrOfHoursWork()) return 1;
                if (s1.getNrOfHoursWork() < s2.getNrOfHoursWork()) return -1;
                return 0;
            }
        });
        for ( Schedule schedule : schedules){
            System.out.println(schedule.toString());
        }
        return schedules;
    }

    private static Map<String , Map<String, Integer>> initMap(List<Employee> employees){
        HashMap<String , Map<String, Integer>> map= new HashMap<String , Map<String, Integer>>();
        for(Employee employee : employees){
            map.put(employee.getName(),new HashMap<>());

        }
        return map;
    }

    private static List<Task> getListOfTaskWithEmployeessName(String employeeName, Map<Employee,List<Task>> map){
        for(Map.Entry<Employee,List<Task>> entry: map.entrySet() ){
            if(entry.getKey().getName().equals(employeeName)){
                return entry.getValue();
            }
        }
        return null;
    }

    private static int nrOfStatusTasks(List<Task> tasks, String status){
        int totalNumber=0;
        for(Task task : tasks){
            if(task.getStatusTask().equals(status)){
                totalNumber++;
            }
        }
        return totalNumber;
    }

    private static HashMap<String , Integer> initInnerHash(List<Task> tasks){
        HashMap<String, Integer> map = new HashMap<>();
        map.put("Completed", nrOfStatusTasks(tasks,"Completed"));
        map.put("Uncompleted", nrOfStatusTasks(tasks,"Uncompleted"));
        return map;
    }

    private static List<Employee> listOfEmployeesInMap(Map<Employee, List<Task>> map){
        List<Employee> employees =new ArrayList<>();
        for(Map.Entry<Employee,List<Task>> entry : map.entrySet()){
            if(entry.getKey()!=null){
                employees.add(entry.getKey());
            }
        }
        return employees;
    }

    public static Map<String,Map<String,Integer>> calculateStatusOfTaskPerEmployee(Map<Employee,List<Task>> map){
        List<Employee> employees = listOfEmployeesInMap(map);
        Map<String , Map<String, Integer>> mapForStatus = initMap(employees);
        for(Map.Entry<String , Map<String, Integer>> entry: mapForStatus.entrySet()){
            if(getListOfTaskWithEmployeessName(entry.getKey(),map) == null){
                entry.setValue(new HashMap<>());
            }
            HashMap <String, Integer> innerHash = initInnerHash(getListOfTaskWithEmployeessName(entry.getKey(),map));
            entry.setValue(innerHash);
        }
        return mapForStatus;
    }
}
