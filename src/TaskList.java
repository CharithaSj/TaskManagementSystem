import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

//manages the list of tasks
public class TaskList {
    //created a list to store the tasks that are in the task list
    private List<Task> tasks;

    //constructor
    public TaskList() {
        tasks = new ArrayList<>();
    }

    //adding new task onto the list
    public void addTask(Task task) {
        tasks.add(task);
    }


    // public void removeTask(Task task) {
    //     tasks.remove(task);
    // }

    //shows the tasks that aren't yet marked complete
    public List<Task> getPendingTasks() {
        List<Task> pending = new ArrayList<>();
        for (Task task : tasks) {
            if (!task.isCompleted()) {
                pending.add(task);
            }
        }
        return pending;
    }

    public List<Task> getAllTasks() {
        return tasks;
    }

    //goes through each task, writes it in a compatible reading format, and add new line in between the tasks
    public void saveTasksToFile(String filename) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (Task task : tasks) {
                writer.write(task.toCSV());  // Write the task in CSV format
                writer.newLine();  // Move to the next line
            }
        }
    }
    

    //this is to save the files that aren't marked finished in the file, like it sorta is used to update the txt file
    public void saveIncompleteTasksToNewFile(String newFilename) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(newFilename))) {
            for (Task task : getPendingTasks()) {
                writer.write(task.toCSV());
                writer.newLine();
            }
        }
    }

    //adding a new task to the txt file
    public void appendNewTaskToFile(String filename, Task task) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true))) {
            writer.write(task.toCSV());
            writer.newLine();
        }
    }


    //this function is used to load the tasks from the task txt file, and then parse the data to work with
    public void loadTasksFromTextFile(String filename) throws IOException {
    try (BufferedReader readerHere = new BufferedReader(new InputStreamReader(new FileInputStream(filename), StandardCharsets.UTF_8))) {
        String line;
        while ((line = readerHere.readLine()) != null) {
            //System.out.println("debugggggg line: " + line);  // Debugging stuff

            String[] parts = line.split(",");
            if (parts.length == 6) {  // Ensure we have the right number of elements
                String withoutWeirdLetters = parts[0].trim().replaceAll("[^\\x20-\\x7E]", "");  // Remove non-ASCII chars
                String description = parts[1].trim(); //parsing stuff into their variables so it's all sorted right
                String priority = parts[2].trim();
                String dueDate = parts[3].trim();
                Category category = new Category(parts[4].trim());
                boolean completed = Boolean.parseBoolean(parts[5].trim());  // Reading completion status

                Task newTask = new Task(withoutWeirdLetters, description, priority, dueDate, category);
                newTask.setCompleted(completed);  // Set the completion status
                addTask(newTask); //adding task to the list

            }
        }
    }
}

}
