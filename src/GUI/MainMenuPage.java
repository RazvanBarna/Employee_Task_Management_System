package GUI;

import BusinessLogic.EmployeesManagement;
import BusinessLogic.TasksManagement;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenuPage extends JFrame {
    private JLabel fieldTitle;
    private JButton viewEmployeesButton;
    private JButton addEmployeeButton;
    private JButton addTaskButton;
    private JButton assignTasksButton;
    private JButton modifyStatusOfTasksButton;
    private JButton viewStatisticsButton;
    private JPanel Panel;
    private static TasksManagement tasksManagement = new TasksManagement();
    private static EmployeesManagement employeesManagement = new EmployeesManagement(tasksManagement);

    public MainMenuPage(){

        setContentPane(Panel);
        setSize(500,500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);

        addEmployeeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddEmployeePage();
                dispose();
            }
        });

        addTaskButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddTaskPage();
                dispose();
            }
        });

        assignTasksButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AssignTaskToEmployeePage();
                dispose();
            }
        });

        modifyStatusOfTasksButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ModifiyStatusPage();
                dispose();
            }
        });

        viewEmployeesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ViewEmployeesPage();
                dispose();
            }
        });

        viewStatisticsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ViewStatisticsPage();
                dispose();
            }
        });

    }

    public static EmployeesManagement getEmployeesManagement() {
        return employeesManagement;
    }

    public static void setEmployeesManagement(EmployeesManagement employeesManagement) {
        MainMenuPage.employeesManagement = employeesManagement;
    }


    public static TasksManagement getTasksManagement() {
        return tasksManagement;
    }

}

