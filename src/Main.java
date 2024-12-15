import java.io.IOException;
import java.util.Scanner;

//Main class to run everything here
public class Main {
        public static void main(String[] args) {
        User user = new User("Thor"); //getting user name
        String filename = "tasks.txt"; //file name

        try {
            //loading the tasks from the text file into the task list for user
            user.getUserTasks().loadTasksFromTextFile(filename); 
            System.out.println("Tasks loaded from " + filename + ":\n");
            user.viewTasks(); //shows the user's task list

            // Prompt user to mark a task as completed
            Scanner scanner = new Scanner(System.in);
            System.out.println("\nEnter the title of the task to mark as completed:");
            String taskTitle = scanner.nextLine();

            // Mark task as completed as well as update the text file with the new changes of completion
            Task taskToMark = findTaskByTitle(user, taskTitle);
            if (taskToMark != null) {
                user.markTaskCompleted(taskToMark);
                System.out.println("Task marked as completed: " + taskToMark);
                user.getUserTasks().saveTasksToFile(filename); // Save changes
            } else {
                System.out.println("Task not found."); //if the task entered is non existant, then this prints out
            }

            // Prompt user to add a new task to the list
            System.out.println("\nDo you want to add a new task? (yes/no)");
            String addNewTaskResponse = scanner.nextLine();

            //if user answer's yes it goes into the first if statement and gets all the necessary data to add the new task to list
            if (addNewTaskResponse.equalsIgnoreCase("yes")) {
                System.out.println("Enter task title:");
                String newTitle = scanner.nextLine();
                System.out.println("Enter task description:");
                String newDescription = scanner.nextLine();
                System.out.println("Enter task priority (High, Medium, Low):");
                String newPriority = scanner.nextLine();
                System.out.println("Enter task due date (YYYY-MM-DD):");
                String newDueDate = scanner.nextLine();
                System.out.println("Enter task category:");
                String newCategory = scanner.nextLine();

                // Create new categiry for the task, creating the new task
                Category category = new Category(newCategory);
                Task newTask = new Task(newTitle, newDescription, newPriority, newDueDate, category);

                // Add new task to the task list
                user.getUserTasks().addTask(newTask);
                System.out.println("New task added: " + newTask);

                // Save the updated task list to the file
                user.getUserTasks().saveTasksToFile(filename);
            }

            // Display updated tasks
            user.viewTasks();
            scanner.close(); //close scanner
        } catch (IOException e) {
            //if there's any errors happening in the file this prints out 
            System.out.println("Error loading tasks: " + e.getMessage());
        }
    }

    // Helper method to find a task by its title
    private static Task findTaskByTitle(User user, String title) {
        for (Task task : user.getUserTasks().getAllTasks()) { //iterating thru the list
            // Perform a comparison and ignore whitespace
            if (task.getTitle().trim().equalsIgnoreCase(title.trim())) {
                return task; //return if the task is found
            }
        }
        return null; //if there is no match, then returns null
    }
} //end of class
