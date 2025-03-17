# PT2025_30222_Barna_Razvan_1 - Project 1

## About Data Model
- In this section, I used inheritance to implement the SimpleTask and ComplexTask classes, as required. Additionally, I implemented serialization to retain the task id as a static attribute. Whenever a new task is created, the id is updated automatically to ensure uniqueness.
- The Employee class is implemented according to the requirements, with overridden hashCode and equals methods.
- To improve employee management, I created an additional class, EmployeeHourRetainer, which tracks the number of hours worked by each employee. The EmployeesManagement class maintains a list of EmployeeHourRetainer instances, which can be sorted using utility methods.

## About Serialization 
* Serialization is managed through two main methods: read and write. Depending on the input, the program determines the appropriate file for reading or writing data.

## About Graphic User Interface
 The application consists of eight pages/classes, all implemented using Java Swing.The application starts with the Main Menu, where the Project Manager has multiple options:
* Add Employees & Tasks – The manager can add new employees and tasks.
* Manage Complex Tasks – Tasks can be added or removed from a Complex Task, allowing for more structured workflows.
* Assign Tasks to Employees – Tasks can be assigned to employees.
* Update Task Status – Once a task is completed, the manager can change its status to "Completed". If issues arise, the status can be reverted to "Uncompleted". For Complex Tasks, only the main task (which contains the sub-tasks) can have its status updated.
* View Employee Data – The manager can check all employees and calculate their total completed work.
* Employee Statistics Page – Provides insights into the number of completed and uncompleted tasks per employee.


## About Business Logic
The business logic of the program is ensured by three key classes, each responsible for different aspects of functionality:
- **Utility** : This class implements two static methods: :
  - sortByNrOfHoursGt40 – Generates an ascending list of employees who have worked more than 40 hours, helping the project manager monitor workload distribution.
  - calculateStatusOfTaskPerEmployee – Calculates the number of tasks per status (Completed or Uncompleted) for each employee.
- **EmployeesManagement** : This class handles employee-related operations and includes three main methods:
  - addEmployee – Links the business logic with the GUI, allowing the addition of new employees.
  - createHourRetainerList – Manages the list of hour retainers, ensuring accurate tracking of worked hours.
  - calculateCompletedHours – Calculates the total number of hours an employee has worked on completed tasks.
- **TaskManagement** : This class contains various methods essential for task management, including:
    - addTaskInApplication – Connects task management with the GUI, enabling the creation of new tasks.
    - addTaskInComplexTask & deleteTaskInComplexTask – Manage the structure of Complex Tasks, allowing tasks to be added or removed dynamically.
    - modifyStatusTask & assignTask – Facilitate task status updates and task assignment through the GUI.
    - allTaskUnassigned  – A crucial attribute that stores all unassigned tasks. Whenever the project manager creates a new task, it is first placed in this list before being assigned to an employee.

