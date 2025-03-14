package GUI;

import BusinessLogic.EmployeesManagement;
import BusinessLogic.Utility;
import DataModel.Employee;
import DataModel.HourRetainer;
import com.sun.tools.javac.Main;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ViewStatisticsPage extends JFrame {
    private JTable table1;
    private JButton backButton;
    private JButton pressToSortAscendingButton;
    private JPanel ViewStatisticsPanel;
    private JButton viewOriginalTableButton;

    public ViewStatisticsPage(){
        setContentPane(ViewStatisticsPanel);
        setSize(700, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        setTable();

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MainMenuPage();
                dispose();
            }
        });

        pressToSortAscendingButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setTableSort();
            }

        });

        viewOriginalTableButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setTable();
            }
        });
    }

    private int getNrTaskWithStatus(Map<String,Integer> map,String status){
        for( Map.Entry<String,Integer> entry : map.entrySet()){
            if(entry.getKey().equals(status)){
                return entry.getValue();
            }
        }
        return 0;
    }

    private Object[][] getDataForTable(List<Employee> employees) {
        Object[][] data = new Object[100][3];
        try {
            int index = 0;
            MainMenuPage.getTasksManagement().deserializeMap();
            for (Map.Entry<String, Map<String, Integer>> entry : Utility.calculateStatusOfTaskPerEmployee(MainMenuPage.getTasksManagement().getMapOfTasks()).entrySet()) {
                data[index][0] = entry.getKey();
                data[index][1] = getNrTaskWithStatus(entry.getValue(), "Completed");
                data[index++][2] = getNrTaskWithStatus(entry.getValue(), "Uncompleted");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return data;
    }

    private Object[][] updateTableForSort(){
        Object[][] data = new Object[100][2];
        List<HourRetainer> duplicates = new ArrayList<>();
        try {
            List<HourRetainer> hourRetainerForEmployee = MainMenuPage.getEmployeesManagement().setHourRetainerForEmployees();
            Utility.sortByNrOfHoursGt40(hourRetainerForEmployee);
            int index = 0;
            for (HourRetainer hourRetainer : hourRetainerForEmployee) {
                    if(!duplicates.contains(hourRetainer)){
                        duplicates.add(hourRetainer);
                        data[index][0] = hourRetainer.getEmployee().getName();
                        data[index++][1] = hourRetainer.getNrOfHoursWork();
                    }
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return data;
    }

    private void setTable() {
        try {
            List<Employee> employees = MainMenuPage.getTasksManagement().getListOfEmployeesFromMap();
            for(Employee employee : employees){
                System.out.println();
            }
            DefaultTableModel model = new DefaultTableModel(
                    getDataForTable(employees),
                    new String[]{"Employee", "Tasks Completed", "Tasks Uncompleted"}
            );
            table1.setModel(model);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void setTableSort(){
        DefaultTableModel model = new DefaultTableModel(
                updateTableForSort(),
                new String[]{"Employee","Number of hours"}
        );
        table1.setModel(model);
        table1.repaint();

    }
}
