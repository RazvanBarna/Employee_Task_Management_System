package GUI;

import BusinessLogic.EmployeesManagement;
import BusinessLogic.TasksManagement;
import DataModel.Employee;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddEmployeePage extends JFrame {
    private JPanel AddEmployeePanel;
    private JLabel fieldTitle;
    private JTextField textField1;
    private JTextField textField2;
    private JButton backButton;
    private JButton addButton;
    private JLabel fieldMessageError;
    private EmployeesManagement employeesManagement;
    private TasksManagement tasksManagement;

    public AddEmployeePage(EmployeesManagement employeesManagement, TasksManagement tasksManagement){
        this.employeesManagement = employeesManagement;
        this.tasksManagement = tasksManagement;
        setContentPane(AddEmployeePanel);
        setSize(500,500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new MainMenu(employeesManagement,tasksManagement);
            }
        });


        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    String fieldInformation1Name = textField1.getText();
                    String fieldInfornation2Age = textField2.getText();
                    if(fieldInfornation2Age.isEmpty() || fieldInformation1Name.isEmpty()){
                        fieldMessageError.setText(" Please insert in all text filed !");
                    }
                    else {
                        try {
                            int ageOfEmployee = Integer.parseInt(fieldInfornation2Age); // Parcurgem conversia
                            Employee employee = new Employee(fieldInformation1Name, ageOfEmployee);
                            employeesManagement.addEmployee(employee);
                            fieldMessageError.setBackground(Color.GREEN);
                            fieldMessageError.setText("Successfully added!");
                        } catch (NumberFormatException ex) {
                            fieldMessageError.setText("Please insert a valid age!");
                            fieldMessageError.setBackground(Color.RED);
                        }
                    }
            }
        });
    }
}
