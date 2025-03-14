package GUI;

import DataModel.Employee;
import DataModel.Task;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Map;

public class AddEmployeePage extends JFrame {
    private JPanel AddEmployeePanel;
    private JLabel fieldTitle;
    private JTextField textField1;
    private JTextField textField2;
    private JButton backButton;
    private JButton addButton;
    private JLabel fieldMessageError;
    private JLabel idLabel;
    private JTextField textFieldId;


    public AddEmployeePage(){
        setContentPane(AddEmployeePanel);
        setSize(500,500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new MainMenuPage();
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
                            int ageOfEmployee = Integer.parseInt(fieldInfornation2Age);
                            int id =Integer.parseInt(textFieldId.getText());
                            Employee employee = new Employee(fieldInformation1Name, ageOfEmployee,id);
                            MainMenuPage.getEmployeesManagement().addEmployee(employee);
                            fieldMessageError.setText("Successfully added!");
                        } catch (NumberFormatException ex) {
                            fieldMessageError.setText("Please insert a valid age and a valid id!");
                            fieldMessageError.setBackground(Color.RED);
                        } catch (Exception ex){
                            fieldMessageError.setText(ex.getMessage());
                        }
                    }
            }
        });
    }
}
