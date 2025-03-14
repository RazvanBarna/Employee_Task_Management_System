package GUI;

import DataModel.Employee;
import DataModel.Task;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Map;

public class AssignTaskToEmployeePage extends JFrame {
    private JPanel assignTaskPanel;
    private JComboBox<Employee> comboBox1;
    private JLabel employeeLabel;
    private JLabel taskLabel;
    private JComboBox<Task> comboBox2;
    private JButton backButton;
    private JButton assignButton;
    private JLabel errorLabel;


    public AssignTaskToEmployeePage (){
        setContentPane(assignTaskPanel);
        setSize(700,700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        this.addEmployeeInComboBox();
        this.addTasksInComboBox();

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MainMenuPage();
                dispose();
            }
        });

        assignButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               try {
                   Task taskChosen = (Task) comboBox2.getSelectedItem();
                   Employee employeeChosen = (Employee) comboBox1.getSelectedItem();
                   if(taskChosen == null || employeeChosen == null)
                       throw new RuntimeException("Please select in both comboboxes");

                   MainMenuPage.getTasksManagement().assignTaskToEmployee(employeeChosen.getIdEmployee(),taskChosen);
                   errorLabel.setText("Successfully assigned!");
                   addEmployeeInComboBox();
                   addTasksInComboBox();

               }catch (RuntimeException ex){
                   ex.printStackTrace();
                   errorLabel.setText(ex.getMessage());
               }
               catch (Exception ex){
                   ex.printStackTrace();
                   errorLabel.setText(ex.getMessage());
               }
            }
        });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MainMenuPage();
                dispose();
            }
        });
        comboBox1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addTasksInComboBox();

            }
        });


    }


    private void addEmployeeInComboBox(){
        try {
            comboBox1.removeAllItems();
            List <Employee> employees= MainMenuPage.getTasksManagement().getListOfEmployeesFromMap();
            for (Employee employee : employees) {
                comboBox1.addItem(employee);
            }
        }catch (Exception e){
            e.printStackTrace();
            errorLabel.setText(e.getMessage());
        }
    }

    private void addTasksInComboBox() {
        try {
            comboBox2.removeAllItems();
            MainMenuPage.getTasksManagement().deserializeTaskList();
            for(Task task : MainMenuPage.getTasksManagement().getListOfTaskUnssigned()){
                comboBox2.addItem(task);
            }
        }catch (Exception e){
            e.printStackTrace();
            errorLabel.setText(e.getMessage());
        }

    }
}
