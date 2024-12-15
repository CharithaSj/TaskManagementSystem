public class Category { 
    //this class is to divide the tasks bt category so the user can have that clarity of what kind of task it is
    private String name;

    //constructor
    public Category(String name) {
        this.name = name;
    }

    //get name of category
    public String getName() {
        return name;
    }

    //convert to string
    @Override
    public String toString() {
        return name;
    }
}

