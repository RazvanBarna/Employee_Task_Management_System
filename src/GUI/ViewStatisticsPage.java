package GUI;

import BusinessLogic.EmployeesManagement;
import BusinessLogic.TasksManagement;
import BusinessLogic.Utility;
import DataModel.Employee;
import DataModel.Task;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ViewStatisticsPage extends JFrame {
    private JTable table1;
    private JButton backButton;
    private JButton pressToSortAscendingButton;
    private JPanel ViewStatisticsPanel;
    private TasksManagement tasksManagement;
    private Utility utility;
    private EmployeesManagement employeesManagement;

    public ViewStatisticsPage(TasksManagement tasksManagement, EmployeesManagement employeesManagement,Utility utility){
        this.tasksManagement = tasksManagement;
        this.employeesManagement = employeesManagement;
        this.utility = utility;

        setContentPane(ViewStatisticsPanel);
        setSize(700, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        List<Employee> employees = new ArrayList<>();
        setTable(employees);

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               // new MainMenu(employeesManagement,tasksManagement,utility);
                dispose();
            }
        });
        pressToSortAscendingButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //  utility.sortByNrOfHoursGt40(employees);
                setTable(employees);
            }
        });
    }

    private int getNrTaskWithStatus(Map<String,Integer> map,String status){
        for( Map.Entry<String,Integer> entry : map.entrySet()){
            if(entry.getKey().equals(status)){
                return entry.getValue();
            }
        }
        return 0;
    }

    private Object[][] getDataForTable(List<Employee> employees) {
        Object[][] data = new Object[30][3];
        int i=0;
        int index =0;
        for (Map.Entry<String,Map<String,Integer>> entry : utility.calculateStatusOfTaskPerEmployee(tasksManagement.getMapOfTasks()).entrySet()) {
            data[index][0] = entry.getKey();
            data[index][1] = getNrTaskWithStatus(entry.getValue(),"Completed");
            data[index][2] = getNrTaskWithStatus(entry.getValue(),"Uncompleted");
        }
        return data;
    }

    private void setTable(List<Employee> employees) {
        DefaultTableModel model = new DefaultTableModel(
                getDataForTable(employees),
                new String[]{"Employee", "Tasks Completed" , "Task Uncompleted"}
        );
        table1.setModel(model);
    }

    private List<Employee> listOfEmployeeFromMap(){
        List<Employee> employees = new ArrayList<>();
        for(Map.Entry<Employee,List<Task>> entry : tasksManagement.getMapOfTasks().entrySet()){
            if(entry.getKey() != null){
                employees.add(entry.getKey());
            }
        }
        return employees;
    }
}
