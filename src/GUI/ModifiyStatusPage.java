package GUI;

import BusinessLogic.EmployeesManagement;
import BusinessLogic.TasksManagement;
import BusinessLogic.Utility;
import DataModel.Employee;
import DataModel.Task;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ModifiyStatusPage extends JFrame {
    private JPanel ModifyStatusPanel;
    private JLabel titleLabel;
    private JComboBox<Task> comboBox1;
    private JButton CompletedButton;
    private JButton changeStatusToUncompletedButton;
    private JButton backButton;
    private JLabel errorLabel;
    private JComboBox<Employee> comboboxEmployee;


    public ModifiyStatusPage(){
        setContentPane(ModifyStatusPanel);
        setSize(700,700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        fillEmployeeCombobox();
        Employee employee =(Employee) comboboxEmployee.getSelectedItem();
        fillTaskCombobox(employee);



        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MainMenuPage();
                dispose();
            }
        });

        CompletedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    Employee employee = (Employee) comboboxEmployee.getSelectedItem();
                    Task task = (Task) comboBox1.getSelectedItem();
                    if(employee != null && task !=null) {
                        MainMenuPage.getTasksManagement().modifyTaskStatus(employee.getIdEmployee(), task.getIdTask()
                                , "Completed");
                        errorLabel.setText("successfully changed status ");
                    }
                    else throw new RuntimeException("Select a task and an employee");
                    fillTaskCombobox(employee);
                    fillEmployeeCombobox();

                }catch (RuntimeException ex){
                    ex.printStackTrace();
                    errorLabel.setText(ex.getMessage());
                }catch (Exception ex){
                    ex.printStackTrace();
                    errorLabel.setText(ex.getMessage());
                }
            }
        });

        changeStatusToUncompletedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    Employee employee = (Employee) comboboxEmployee.getSelectedItem();
                    Task task = (Task) comboBox1.getSelectedItem();
                    if(employee != null && task !=null) {
                        MainMenuPage.getTasksManagement().modifyTaskStatus(employee.getIdEmployee(), task.getIdTask()
                                , "Uncompleted");
                        errorLabel.setText("successfully changed status ");
                    }
                    else throw new RuntimeException("Select a task and an employee");
                    fillTaskCombobox(employee);
                    fillEmployeeCombobox();

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
        comboboxEmployee.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Employee selectedEmployee = (Employee) comboboxEmployee.getSelectedItem();

                comboBox1.removeAllItems();
                if (selectedEmployee != null) {
                    fillTaskCombobox(selectedEmployee);
                }

            }
        });
    }

    private void fillEmployeeCombobox(){
        try {
            comboboxEmployee.removeAllItems();
            List<Employee> employees = MainMenuPage.getTasksManagement().getListOfEmployeesFromMap();
            for(Employee employee: employees)
                comboboxEmployee.addItem(employee);
        }catch (Exception e){
            e.printStackTrace();
            errorLabel.setText(e.getMessage());
        }
    }

    private void fillTaskCombobox(Employee employee){
        try{
            comboBox1.removeAllItems();
            List<Task> tasks = MainMenuPage.getTasksManagement().findListOfTasksFromMap(employee.getIdEmployee());
            for(Task task : tasks)
                comboBox1.addItem(task);
        }catch (Exception e){
            e.printStackTrace();
            errorLabel.setText(e.getMessage());
        }
    }
}
