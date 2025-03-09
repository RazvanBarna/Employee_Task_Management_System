package GUI;

import BusinessLogic.EmployeesManagement;
import BusinessLogic.TasksManagement;
import BusinessLogic.Utility;
import DataModel.ComplexTask;
import DataModel.SimpleTask;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddTaskPage extends JFrame {
    private TasksManagement tasksManagement;
    private EmployeesManagement employeesManagement;
    private Utility utility;
    private JPanel addTaskPanel;
    private JLabel fieldTitle;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField5;
    private JButton backButton;
    private JButton addButton;
    private JButton complexTaskButton;
    private JButton simpleTaskButton;
    private JLabel description;
    private JLabel errorMessage;
    private int whichTaskIsWanted=0;

    public AddTaskPage(EmployeesManagement employeesManagement, TasksManagement tasksManagement,Utility utility){
        this.employeesManagement = employeesManagement;
        this.tasksManagement = tasksManagement;
        this.utility = utility;

        setContentPane(addTaskPanel);
        setSize(700,700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        description.setVisible(false);
        textField5.setVisible(false);

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MainMenu(employeesManagement,tasksManagement,utility);
                dispose();
            }
        });

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String fieldInformation1 = textField1.getText();
                    String fieldInformation2 = textField2.getText();
                    String fieldInformation3 = textField3.getText();
                    String fieldInformation4 = textField4.getText();
                    String fieldInformation5 = textField5.getText();

                    if( (fieldInformation1.isEmpty() || fieldInformation2.isEmpty() || fieldInformation3.isEmpty() || fieldInformation4.isEmpty()
                        || fieldInformation5.isEmpty() ) && whichTaskIsWanted==2) {
                        throw new RuntimeException("All filed must be completed!");
                    }
                    else if ( (fieldInformation1.isEmpty() || fieldInformation2.isEmpty() || fieldInformation3.isEmpty() || fieldInformation4.isEmpty() ) && whichTaskIsWanted==1) {
                        throw new RuntimeException("All filed must be completed!");
                    }
                    if(! (fieldInformation2.equals("Uncompleted") || fieldInformation2.equals("Completed")))
                    {
                        throw new RuntimeException("Task's status must be \"Completed\" or \"Uncompleted\" ");
                    }
                    int startHour = Integer.parseInt(fieldInformation3);
                    int endHour = Integer.parseInt(fieldInformation4);
                         if(whichTaskIsWanted == 1) {
                             tasksManagement.addTask(new SimpleTask(fieldInformation2, fieldInformation1, startHour, endHour));
                             errorMessage.setText("Successfully added simple task!");
                         }
                         else if (whichTaskIsWanted == 2) {
                            tasksManagement.addTask(new ComplexTask(fieldInformation2, fieldInformation1, startHour, endHour, fieldInformation5));
                            errorMessage.setText("Successfully added complex task!");
                         } else {
                            errorMessage.setText("Please select which type of task you want to add !");
                            addButton.setVisible(true);
                        }

                }catch (NumberFormatException ex){
                    ex.printStackTrace();
                    errorMessage.setText("Hours field must be numbers! ");
                }
                catch (RuntimeException ex){
                    ex.printStackTrace();
                    errorMessage.setText(ex.getMessage());
                }


            }
        });

        complexTaskButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                description.setVisible(true);
                textField5.setVisible(true);
                whichTaskIsWanted=2;

            }
        });

        simpleTaskButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textField5.setVisible(false);
                description.setVisible(false);
                whichTaskIsWanted=1;
            }
        });

    }
}
