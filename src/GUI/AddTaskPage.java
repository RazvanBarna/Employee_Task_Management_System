package GUI;

import DataModel.ComplexTask;
import DataModel.SimpleTask;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddTaskPage extends JFrame {
    private JPanel addTaskPanel;
    private JTextField titleField;
    private JTextField statusField;
    private JTextField startHourField;
    private JTextField endHourField;
    private JButton backButton;
    private JButton addButton;
    private JButton complexTaskButton;
    private JButton simpleTaskButton;
    private JLabel errorMessage;
    private JButton addTaskToComplexButton;
    private JLabel endLabel;
    private JLabel startLabel;
    private JLabel fieldTitle;
    private int whichTaskIsWanted = 0;

    public AddTaskPage() {

        setContentPane(addTaskPanel);
        setSize(700, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        startHourField.setVisible(true);
        endHourField.setVisible(true);
        startLabel.setVisible(true);
        endLabel.setVisible(true);

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               new MainMenuPage();
                dispose();
            }
        });

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String fieldTitle = titleField.getText();
                    String fieldStaus = statusField.getText();
                    String fieldStartH = startHourField.getText();
                    String fieldEndH = endHourField.getText();

                    if( (fieldTitle.isEmpty() || fieldStaus.isEmpty()) && whichTaskIsWanted==2) {
                        throw new RuntimeException("All filed must be completed!");
                    }
                    else if ( (fieldTitle.isEmpty() || fieldStaus.isEmpty() || fieldStartH.isEmpty() || fieldEndH.isEmpty() ) && whichTaskIsWanted==1) {
                        throw new RuntimeException("All filed must be completed!");
                    }
                    if(! (fieldStaus.equals("Uncompleted") || fieldStaus.equals("Completed")))
                    {
                        throw new RuntimeException("Task's status must be \"Completed\" or \"Uncompleted\" ");
                    }

                    try {
                        if (whichTaskIsWanted == 1) {
                            int startHour = Integer.parseInt(fieldStartH);
                            int endHour = Integer.parseInt(fieldEndH);
                            if(startHour < 0 || startHour >23 || endHour < 0 || endHour >23)
                                throw new Exception("Please enter a valid hour!");
                            MainMenuPage.getTasksManagement().addTaskInApplication(new SimpleTask(fieldStaus, fieldTitle, startHour, endHour));
                            errorMessage.setText("Successfully added simple task!");
                        } else if (whichTaskIsWanted == 2) {
                            MainMenuPage.getTasksManagement().addTaskInApplication(new ComplexTask(fieldStaus, fieldTitle));
                            errorMessage.setText("Successfully added complex task!");
                        } else {
                            errorMessage.setText("Please select which type of task you want to add !");
                            addButton.setVisible(true);
                        }
                    }catch (Exception ex){
                        ex.printStackTrace();
                        errorMessage.setText(ex.getMessage());
                    }
                    }catch (NumberFormatException ex){
                    ex.printStackTrace();
                    errorMessage.setText("Hour field and id must be numbers! ");
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
                whichTaskIsWanted=2;
                startHourField.setVisible(false);
                endHourField.setVisible(false);
                startLabel.setVisible(false);
                endLabel.setVisible(false);
            }
        });

        simpleTaskButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                whichTaskIsWanted=1;
                startHourField.setVisible(true);
                endHourField.setVisible(true);
                startLabel.setVisible(true);
                endLabel.setVisible(true);
            }
        });

        addTaskToComplexButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new OperationsOnComplexTaskPage();
                dispose();
            }
        });
    }

}
