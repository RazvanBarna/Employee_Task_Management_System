package GUI;

import DataModel.ComplexTask;
import DataModel.Task;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class OperationsOnComplexTaskPage extends JFrame {
    private JPanel complexTaskPanel;
    private JLabel errorMessage;
    private JComboBox<ComplexTask> allComplexTasks;
    private JComboBox<Task> addTask;
    private JComboBox<Task> deleteTask;
    private JButton addTaskButton;
    private JButton deleteTaskButton;
    private JButton backButton;

    public OperationsOnComplexTaskPage(){
        setContentPane(complexTaskPanel);
        setSize(500,500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        fillComplexTasks();
        fillTasksToAdd();
        fillTasksToDelete((ComplexTask) allComplexTasks.getSelectedItem());


        addTaskButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    ComplexTask selectedComplexTask = (ComplexTask) allComplexTasks.getSelectedItem();
                    if(addTask.getSelectedItem() == null){
                        throw new RuntimeException("Please select a task to add");
                    }
                    MainMenuPage.getTasksManagement().addTaskInComplexTask(selectedComplexTask,(Task) addTask.getSelectedItem());
                    fillComplexTasks();
                    fillTasksToAdd();
                    fillTasksToDelete(selectedComplexTask);
                    errorMessage.setText("Successfully added");
                }catch (Exception ex){
                    ex.printStackTrace();
                    errorMessage.setText(ex.getMessage());
                }
            }
        });

        deleteTaskButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    ComplexTask selectedComplexTask = (ComplexTask) allComplexTasks.getSelectedItem();
                    if(deleteTask.getSelectedItem() == null){
                        throw new RuntimeException("Please select a task to delete");
                    }
                    MainMenuPage.getTasksManagement().deleteTaskInComplexTask(selectedComplexTask,(Task) deleteTask.getSelectedItem());
                    fillTasksToAdd();
                    fillComplexTasks();
                    fillTasksToDelete(selectedComplexTask);
                    errorMessage.setText("Successfully deleted");
                }catch (Exception ex){
                    ex.printStackTrace();
                    errorMessage.setText(ex.getMessage());
                }
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddTaskPage();
                dispose();
            }
        });
        allComplexTasks.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fillTasksToAdd();
                ComplexTask complexTask = (ComplexTask) allComplexTasks.getSelectedItem();
                fillTasksToDelete(complexTask);
            }
        });
    }

    private void fillTasksToAdd(){
        try {
            addTask.removeAllItems();
            MainMenuPage.getTasksManagement().deserializeTaskList();
            List<Task> taskToAdd = MainMenuPage.getTasksManagement().getListOfTaskUnssigned();
            for (Task task : taskToAdd) {
                if(task.equals(allComplexTasks.getSelectedItem())){
                    continue;
                }
                if(task instanceof ComplexTask){
                    if (((ComplexTask) task).getTasksOfComplexTask().contains(allComplexTasks.getSelectedItem())){
                        continue;
                    }
                }

                this.addTask.addItem(task);
            }

        }
        catch (Exception e){
            e.printStackTrace();
            errorMessage.setText(e.getMessage());
        }
    }

    private void fillComplexTasks() {
        try {
            allComplexTasks.removeAllItems();
            List<ComplexTask> tasks =MainMenuPage.getTasksManagement().fillAllComplexTaskFromMapAndUnassigned();
            for(ComplexTask complexTask :tasks )
                allComplexTasks.addItem(complexTask);

        } catch (Exception e){

            e.printStackTrace();
            errorMessage.setText(e.getMessage());
            System.out.println(e.getMessage());
        }
    }

    private void fillTasksToDelete(ComplexTask complexTask){
        deleteTask.removeAllItems();
        try {
            List<ComplexTask> tasks =MainMenuPage.getTasksManagement().fillAllComplexTaskFromMapAndUnassigned();
            for(ComplexTask complexTask1 : tasks)
                if (complexTask1.equals(complexTask))
                    for(Task task1 : complexTask1.getTasksOfComplexTask())
                        deleteTask.addItem(task1);

        }
        catch (Exception e){
            e.printStackTrace();
            errorMessage.setText(e.getMessage());
        }
    }

}
