package to_do;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class To_Do {

   private static final String FILE_PATH = "tasks.txt";
   private static final ArrayList<Task> tasks = new ArrayList<>();
   private static final Scanner scanner = new Scanner(System.in);
   public static void main(String[] args) {
        loadTask();
        while(true){
        System.out.println("====TO-DO MENU====");
        System.out.println("1. Add Task");
        System.out.println("2. List Task");
        System.out.println("3. Delete Task");
        System.out.println("4. Exit");
        System.out.println("choose one of the above!");
        int choice = scanner.nextInt();
        scanner.nextLine();
        switch(choice){
            case 1 -> addTask();
            case 2 -> listTask();
            case 3 -> deleteTask();
            case 4 -> Exit();
            default -> System.out.println("invalid choice! please Try again.");
        }
        }
    }
   private static void addTask(){
            System.out.println("Type your title!");
            String title = scanner.nextLine();
            System.out.println("Type your task description!");
            String description = scanner.nextLine();
            tasks.add(new Task(title, description));
            System.out.println(title + " task added");
    }
   private static void listTask(){
            if (tasks.isEmpty()){
                System.out.println("no task yet.");
            }
            for(int i=0;i<tasks.size();i++){
                  Task  task=tasks.get(i);
                  System.out.println((i+1) + "." + task);
            }
    }
   private static void deleteTask(){
            if(tasks.isEmpty()){
                System.out.println(" There is no task yet.");
                return;
            }
               listTask();
               System.out.println("choose one you want to delete!");
               int index= scanner.nextInt()-1; 
            if(index >= 0 && index < tasks.size()){
               Task removedTask = tasks.remove(index);
               System.out.println(removedTask + " is deleted");
               saveTask();
            }else{
            System.out.println("invalid input!");
            }
            
           
    }
   private static void saveTask(){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for(Task task:tasks){
                 writer.write(task.getTitle() + ":- " + task.getDescription());
                 writer.newLine();
                }
    }catch(IOException e){
         System.out.println("Error saving tasks: " + e.getMessage());}
    }
   private static void loadTask(){
            try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while((line = reader.readLine()) != null) {
                String[] parts = line.split("\\:-",2);
                if (parts.length == 2){
                tasks.add(new Task(parts[0], parts[1]));
            } else{ 
                  System.out.println("Skipping invalid task line:"+ line);
                }
            }
        } catch (IOException e) {
            // File not found (okay on first run)
            System.out.println("Error loading tasks: " + e.getMessage());
        }
    }
   private static void Exit(){
        saveTask();
        System.out.println("Goodbye...");
        System.exit(0);
    }
}
