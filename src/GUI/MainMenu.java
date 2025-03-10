package GUI;

import BusinessLogic.EmployeesManagement;
import BusinessLogic.TasksManagement;
import BusinessLogic.Utility;

import javax.swing.*;
import javax.swing.text.View;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu extends JFrame {
    private JLabel fieldTitle;
    private JButton viewEmployeesButton;
    private JButton addEmployeeButton;
    private JButton addTaskButton;
    private JButton assignTasksButton;
    private JButton modifyStatusOfTasksButton;
    private JButton viewStatisticsButton;
    private JPanel Panel;
    private TasksManagement tasksManagement;
    private EmployeesManagement employeesManagement;
    private Utility utility;

    public MainMenu(EmployeesManagement employeesManagement,TasksManagement tasksManagement,Utility utility){
        this.tasksManagement= tasksManagement;
        this.employeesManagement = employeesManagement;
        this.utility = utility;

        setContentPane(Panel);
        setSize(500,500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);

        addEmployeeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddEmployeePage(employeesManagement,tasksManagement,utility);
                dispose();
            }
        });

        addTaskButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               // new AddTaskPage(employeesManagement,tasksManagement,utility);
                dispose();
            }
        });

        assignTasksButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AssignTaskToEmployeePage(tasksManagement,employeesManagement,utility);
                dispose();
            }
        });

        modifyStatusOfTasksButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ModifiyStatusPage(tasksManagement,employeesManagement,utility);
                dispose();
            }
        });

        viewEmployeesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ViewEmployeesPage(tasksManagement,employeesManagement,utility);
                dispose();
            }
        });

        viewStatisticsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ViewStatisticsPage(tasksManagement,employeesManagement,utility);
                dispose();
            }
        });

    }
}

