package GUI;

import BusinessLogic.EmployeesManagement;
import BusinessLogic.TasksManagement;
import BusinessLogic.Utility;
import DataModel.ComplexTask;
import DataModel.Employee;
import DataModel.SimpleTask;
import DataModel.Task;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
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
    private TasksManagement tasksManagement;
    private Utility utility;
    private EmployeesManagement employeesManagement;

    public AssignTaskToEmployeePage (TasksManagement tasksManagement , EmployeesManagement employeesManagement, Utility utility){
        this.tasksManagement = tasksManagement;
        this.employeesManagement = employeesManagement;
        this.utility = utility;

        setContentPane(assignTaskPanel);
        setSize(700,700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        //din deserializare
        /*Employee e1 = new Employee("John",30);
        Employee e2 = new Employee("Jerome",20);
        Employee e3 = new Employee("Marius",40);
        List<Employee> employees = new ArrayList<Employee>();
        employees.add(e1);
        employees.add(e2);
        employees.add(e3);

        employeesManagement.addEmployee(e1);
        employeesManagement.addEmployee(e2);
        employeesManagement.addEmployee(e3);

        addEmployeeInComboBox(employees,comboBox1);

        Task t1 =new ComplexTask("b","c",11,12,"aa");
        Task t2= new SimpleTask("a","b",0,1);
        Task t3 =new ComplexTask("c","d",10,20,"bb");

        List<Task>tasks = new ArrayList<Task>();
        tasks.add(t1);
        tasks.add(t2);
        tasks.add(t3);

        addTasksInComboBox(tasks,comboBox2);

         */

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MainMenu(employeesManagement,tasksManagement,utility);
                dispose();
            }
        });

        assignButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               try {
                   Task taskChosen = (Task) comboBox2.getSelectedItem();
                   Employee employeeChosen = (Employee) comboBox1.getSelectedItem();
                   if(taskChosen == null || employeeChosen == null){
                       throw new RuntimeException("Please select in both comboboxes");
                   }
                   //tasksManagement.assignTaskToEmployee(employeeChosen.getIdEmployee(),taskChosen);
                   employeesManagement.calculateEmployeeWorkDuration(employeeChosen.getIdEmployee());
                   for(Map.Entry<Employee, List<Task>> entry: tasksManagement.getMapOfTasks().entrySet()){
                       System.out.println(entry.getKey().toString());
                       System.out.println(entry.getValue());
                   }
                   errorLabel.setText("Successfully assigned!");
               }catch (RuntimeException ex){
                   ex.printStackTrace();
                   errorLabel.setText(ex.getMessage());
               }

            }

        });
    }


    private void addEmployeeInComboBox(List<Employee> employees ,JComboBox<Employee> comboBox1){
        comboBox1.removeAllItems();
        for(Employee employee : employees){
            comboBox1.addItem(employee);
        }
    }

    private void addTasksInComboBox(List<Task> tasks,JComboBox<Task> comboBox2){
        comboBox2.removeAllItems();
        for(Task task : tasks){
            comboBox2.addItem(task);
        }
    }
}
