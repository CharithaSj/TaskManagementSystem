public class User { //this class is used to manage who is using the task list
    private String name; 
    private TaskList userTasks;

    public User(String name) { //constructor, initializing the variables
        this.name = name;
        this.userTasks = new TaskList();
    }

    /**
     * This is used to Create a new task with the details and add it to user's current tasklist
     * 
     * @param title       The title of task
     * @param description A brief description
     * @param priority    The priority of the task
     * @param dueDate     The due date of the task
     * @param category    The category of the task 
     */
    public void createTask(String title, String description, String priority, String dueDate, Category category) {
        Task task = new Task(title, description, priority, dueDate, category);
        userTasks.addTask(task);
    }

    //marking the task as completed, calling the method from task class
    public void markTaskCompleted(Task task) {
        task.markAsCompleted();
    }

    //this is to print out all the tasks for the user to see
    public void viewTasks() {
        for (Task task : userTasks.getAllTasks()) {
            System.out.println(task);
        }
    }

    //finds the specific task 
    public TaskList getUserTasks() {
        return userTasks;
    }

    public String getName() {
        return name;
    }
}


