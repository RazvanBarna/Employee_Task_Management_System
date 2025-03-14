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
import java.util.List;
import java.util.Map;

public class ViewEmployeesPage extends JFrame {

    private JPanel viewEmployeesPanel;
    private JTable table1;
    private JButton backButton;
    private TasksManagement tasksManagement;
    private EmployeesManagement employeesManagement;
    private Utility utility;

    public ViewEmployeesPage(TasksManagement tasksManagement, EmployeesManagement employeesManagement, Utility utility) {
        this.tasksManagement = tasksManagement;
        this.employeesManagement = employeesManagement;
        this.utility = utility;

        setContentPane(viewEmployeesPanel);
        setSize(700, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        setTable();


        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               // new MainMenu(employeesManagement, tasksManagement, utility);
                dispose();
            }
        });
    }

    private String convertAllTaskInOneString(List<Task> tasks){
        String allTasks="";
        if(!tasks.isEmpty()) {
            for (Task task : tasks) {
                allTasks += task.toString() + " ; ";
            }
            allTasks = allTasks.substring(0,allTasks.length()-2);
        }
        else {
            allTasks="No tasks";
        }
        return allTasks;
    }

    private Object[][] getDataForTable() {
        Object[][] data = new Object[30][2];
        int index =0;
        for (Map.Entry<Employee, List<Task>> entry : tasksManagement.getMapOfTasks().entrySet()) {
            Employee employee = entry.getKey();
            List<Task> tasks = entry.getValue();
            String taskConcatenate = convertAllTaskInOneString(tasks);
            data[index][0]=employee;
            data[index++][1]=taskConcatenate;
        }
        return data;
    }

    private void setTable() {
        DefaultTableModel model = new DefaultTableModel(
                getDataForTable(),
                new String[]{"Employee", "Tasks"}
        );
        table1.setModel(model);
    }
}

