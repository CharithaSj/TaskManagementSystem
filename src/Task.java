public class Task {
    private String title; //adding all the variables I'll use to store info
    private String description;
    private String priority;
    private String dueDate;
    private boolean isCompleted;
    private Category category;

    //the constructer
    public Task(String title, String description, String priority, String dueDate, Category category) {
        this.title = title; 
        this.description = description;
        this.priority = priority;
        this.dueDate = dueDate;
        this.category = category;
        this.isCompleted = false;
    }

    //method to mark the task as completed
    public void markAsCompleted() {
        isCompleted = true;
    }

    //set method to update is something is completed
    public void setCompleted(boolean completed) {
        this.isCompleted = completed;
    }

    //get method for title
    public String getTitle() {
        return title;
    }
    
    //get method for marking complete
    public boolean isCompleted() {
        return isCompleted;
    }

    //get method for getting  category
    public Category getCategory() {
        return category;
    }

//get method for description
    public String getDescription() {
        return description;
    }

    //get function for priority
    public String getPriority() {
        return priority;
    }

    //get function for due date
    public String getDueDate() {
        return dueDate;
    }

    //returning stuff in the compartible string format so it can be read and used properly
    public String toCSV() {
        return title + ", " + description + ", " + priority + ", " + dueDate + ", " + category.getName() + ", " + isCompleted;
    }
    
    @Override
    public String toString() {
        return "Task: " + title + ", Priority: " + priority + ", Due: " + dueDate + ", Completed: " + isCompleted + ", Category: " + category;
    }
}
