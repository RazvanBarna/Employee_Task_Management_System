package GUI;

import BusinessLogic.EmployeesManagement;
import BusinessLogic.TasksManagement;

import javax.swing.*;
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

    public MainMenu(EmployeesManagement employeesManagement,TasksManagement tasksManagement){
        this.tasksManagement=tasksManagement;
        this.employeesManagement = new EmployeesManagement(tasksManagement);
        setContentPane(Panel);
        setSize(500,500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);

        addEmployeeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddEmployeePage(employeesManagement,tasksManagement);
                dispose();
            }
        });

        addTaskButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        assignTasksButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        modifyStatusOfTasksButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        viewEmployeesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        viewStatisticsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

    }
}

