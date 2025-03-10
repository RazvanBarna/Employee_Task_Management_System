package GUI;

import BusinessLogic.EmployeesManagement;
import BusinessLogic.TasksManagement;
import BusinessLogic.Utility;
import DataModel.ComplexTask;
import DataModel.SimpleTask;
import DataModel.Task;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class ModifiyStatusPage extends JFrame {
    private JPanel ModifyStatusPanel;
    private JLabel titleLabel;
    private JComboBox<Task> comboBox1;
    private JButton CompletedButton;
    private JButton changeStatusToUncompletedButton;
    private JButton backButton;
    private JLabel errorLabel;
    private Utility utility;
    private EmployeesManagement employeesManagement;
    private  TasksManagement tasksManagement;

    public ModifiyStatusPage(TasksManagement tasksManagement, EmployeesManagement employeesManagement, Utility utility){
        this.tasksManagement = tasksManagement;
        this.employeesManagement = employeesManagement;
        this.utility = utility;

        setContentPane(ModifyStatusPanel);
        setSize(700,700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        //Task t1 =new ComplexTask("b","c",11,12,"aa");
        //Task t2= new SimpleTask("a","b",0,1);
        //Task t3 =new ComplexTask("c","d",10,20,"bb");

        List<Task>tasks = new ArrayList<Task>();
        //tasks.add(t1);
       // tasks.add(t2);
        //tasks.add(t3);
        insertTaskInCombobox(tasks,comboBox1);

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MainMenu(employeesManagement,tasksManagement,utility);
                dispose();
            }
        });

        CompletedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    changeStatusToTask(comboBox1,"Completed",tasks);
                    insertTaskInCombobox(tasks,comboBox1);
                }catch (RuntimeException ex){
                    ex.printStackTrace();
                    errorLabel.setText(ex.getMessage());
                }
            }
        });

        changeStatusToUncompletedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    changeStatusToTask(comboBox1,"Uncompleted",tasks);
                    insertTaskInCombobox(tasks,comboBox1);
                }catch (RuntimeException ex){
                    ex.printStackTrace();
                    errorLabel.setText(ex.getMessage());
                }
            }
        });
    }


    private void insertTaskInCombobox(List<Task> allTasks,JComboBox<Task> comboBox1){
        comboBox1.removeAllItems();
        for(Task task : allTasks){
            comboBox1.addItem(task);
        }
    }

    private void changeStatusToTask(JComboBox<Task>comboBox1,String status,List<Task> allTasks) throws RuntimeException{
        if(comboBox1.getSelectedItem() == null) throw  new RuntimeException("Please select which task you want to modify");
        else {
            Task taskSelected = (Task) comboBox1.getSelectedItem();
            for(Task task : allTasks){
                    if(task.equals(taskSelected)){
                        task.setStatusTask(status);
                    }
            }
        }
    }
}
