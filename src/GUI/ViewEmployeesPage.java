package GUI;

import DataModel.ComplexTask;
import DataModel.Employee;
import DataModel.Task;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ViewEmployeesPage extends JFrame {

    private JPanel viewEmployeesPanel;
    private JTable table1;
    private JButton backButton;
    private JComboBox<Employee> comboBox1;
    private JButton button1;
    private JLabel workDuration;


    public ViewEmployeesPage() {
        setContentPane(viewEmployeesPanel);
        setSize(700, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        fillComboboxWithEmployees();
        Employee employee = (Employee) comboBox1.getSelectedItem();

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
                Employee employee = (Employee) comboBox1.getSelectedItem();
                setTable(employee.getIdEmployee());
                workDuration.setText("");
            }
        });
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               try {
                   if(comboBox1.getSelectedItem() == null) throw new Exception("Select an employee !");
                   Employee employee = (Employee) comboBox1.getSelectedItem();
                   int time = MainMenuPage.getEmployeesManagement().calculateEmployeeWorkDuration(employee.getIdEmployee());
                   workDuration.setText(String.valueOf(time) +"hours");
               }catch (Exception ex){
                   ex.printStackTrace();
               }
            }
        });
    }

    private void getDataForTable(int idEmployee,List<Task> tasks,ComplexTask complexTask) throws Exception {
       for(Task task : complexTask.getTasksOfComplexTask()){
           if(!tasks.contains(task)){
               tasks.add(task);
               if(task instanceof ComplexTask){
                   getDataForTable(idEmployee, tasks, (ComplexTask) task);
               }
           }
       }
    }

    private Object[][] getData(int idEmployee) throws Exception{
        List<Task> tasks = new ArrayList<>();
        for(Task task : MainMenuPage.getTasksManagement().findListOfTasksFromMap(idEmployee)) {
            if(!tasks.contains(task)){
                tasks.add(task);
                if(task instanceof ComplexTask){
                    getDataForTable(idEmployee,tasks,(ComplexTask) task);
                }
            }
        }
        Object [][] data = new Object[tasks.size()][1];

        for (int i = 0; i < tasks.size(); i++) {
            data[i][0] = tasks.get(i);
        }

        return data;
    }

    private void setTable(int idEmployee) {
        try {
            DefaultTableModel model = new DefaultTableModel(
                    getData(idEmployee),
                    new String[]{"Tasks"}
            );
            table1.setModel(model);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    private void fillComboboxWithEmployees(){
        try {
            comboBox1.removeAllItems();
            for (Employee employee : MainMenuPage.getTasksManagement().getListOfEmployeesFromMap()) {
                comboBox1.addItem(employee);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

