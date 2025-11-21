import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class TodoListApp {

    static ArrayList<String> tasks = new ArrayList<>();
    static final String FILE_NAME = "tasks.txt";

    public static void main(String[] args) {
        loadTasksFromFile();

        Scanner sc = new Scanner(System.in);
        int choice;

        while (true) {
            System.out.println("\n===== TODO LIST APP =====");
            System.out.println("1. View Tasks");
            System.out.println("2. Add Task");
            System.out.println("3. Update Task");
            System.out.println("4. Delete Task");
            System.out.println("5. Save and Exit");
            System.out.print("Enter choice: ");

            choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1 -> viewTasks();
                case 2 -> addTask(sc);
                case 3 -> updateTask(sc);
                case 4 -> deleteTask(sc);
                case 5 -> {
                    saveTasksToFile();
                    System.out.println("Tasks saved. Exiting... Goodbye!");
                    return;
                }
                default -> System.out.println("Invalid choice!");
            }
        }
    }

    // View all tasks
    public static void viewTasks() {
        if (tasks.isEmpty()) {
            System.out.println("No tasks available.");
            return;
        }
        System.out.println("\nYour Tasks:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
    }

    // Add a new task
    public static void addTask(Scanner sc) {
        System.out.print("Enter new task: ");
        String task = sc.nextLine();
        tasks.add(task);
        System.out.println("Task added successfully!");
    }

    // Update an existing task
    public static void updateTask(Scanner sc) {
        viewTasks();
        if (tasks.isEmpty()) return;

        System.out.print("Enter task number to update: ");
        int index = sc.nextInt();
        sc.nextLine(); // consume newline

        if (index < 1 || index > tasks.size()) {
            System.out.println("Invalid task number!");
            return;
        }

        System.out.print("Enter updated task: ");
        String updatedTask = sc.nextLine();
        tasks.set(index - 1, updatedTask);
        System.out.println("Task updated successfully!");
    }

    // Delete a task
    public static void deleteTask(Scanner sc) {
        viewTasks();
        if (tasks.isEmpty()) return;

        System.out.print("Enter task number to delete: ");
        int index = sc.nextInt();
        sc.nextLine(); // consume newline

        if (index < 1 || index > tasks.size()) {
            System.out.println("Invalid task number!");
            return;
        }

        tasks.remove(index - 1);
        System.out.println("Task deleted successfully!");
    }

    // Save tasks to file
    public static void saveTasksToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (String task : tasks) {
                writer.write(task);
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving tasks: " + e.getMessage());
        }
    }

    // Load tasks from file
    public static void loadTasksFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                tasks.add(line);
            }
        } catch (IOException e) {
            // File may not exist initially, that's fine
        }
    }
}