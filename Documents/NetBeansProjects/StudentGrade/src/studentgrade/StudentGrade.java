
package studentgrade;

import java.util.Scanner;
import java.util.ArrayList;
import java.io.*;
public class StudentGrade {
    private static final String Fial_path= "Stud.txt";
    private static final ArrayList<StudentInfo> dataList= new ArrayList<>();
    private static final Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        StudentGrade gradeSystem = new StudentGrade();
        gradeSystem.loadData(); 
        loadData();
        while(true){
        System.out.println("====TO-DO MENU====");
        System.out.println("1. Add Information");
        System.out.println("2. List Student");
        System.out.println("3. Exit");
        System.out.println("choose one of the above!");
        int choice = scanner.nextInt();
        scanner.nextLine();
        switch(choice){
            case 1 -> addInfo();
            case 2 -> viewAllStudent();
            //case 3 -> loadData();
            case 3 -> Exit();
            default -> System.out.println("invalid choice! please Try again.");
        }
        }     
    }
    private static void addInfo(){
            System.out.println("\n=== Add Student Information ===");
            System.out.print("if you don't have any data on the system. fill the following information!");
            System.out.println("Type your full name. ");
            String Name= scanner.nextLine();
            
            System.out.println("Type your ID. ");
            String ID= scanner.nextLine();
            
            System.out.println("Type your CGPA. ");
            float CGPA= scanner.nextFloat();
            scanner.nextLine();
            
            dataList.add(new StudentInfo(Name, ID, CGPA));
            System.out.println(Name + " information is successfully add");
    }
    private static void viewAllStudent(){
               System.out.println("\n=== All Student ===");
               if(dataList.isEmpty()){
               System.out.println("No Student found.");
               return;
               }
               for(StudentInfo student : dataList){
                    System.out.println(student);
               }
    }
    private static void saveData(){
      try{ BufferedWriter bw= new BufferedWriter(new FileWriter(Fial_path));
            for(StudentInfo studentinfo:dataList){
                 bw.write(studentinfo.getName() + ":-" + studentinfo.getID() +  ":-" + studentinfo.getCGPA());
                 bw.newLine();
                }
            bw.close();
            System.out.println("Data saved succeccfully!");
      }
      catch(IOException e)
        {
            System.out.println("Error saving data: " + e.getMessage());
        }
    }
    private static void loadData(){
        File file = new File(Fial_path);
        if(!file.exists()){
            System.out.println("No existing data file found. Starting fresh.");
            return;
        }
        try(BufferedReader br= new BufferedReader(new FileReader(Fial_path))){
            String line;
            while((line = br.readLine()) != null) {
                String[] parts = line.split(":-",3);
                if (parts.length == 3){
                     float CGPA = Float.parseFloat(parts[2]);
                dataList.add(new StudentInfo(parts[0], parts[1], CGPA));
            } else{ 
                  System.out.println("Skipping invalid task line:"+ line);
                }
            }
            System.out.println("Data loaded successfully! " + dataList.size() + " students found.");
          
        }catch(IOException e){
             System.out.println("Error saving data: " + e.getMessage());
        }catch (NumberFormatException e) {
            System.out.println("Error parsing CGPA data: " + e.getMessage());
        }
    }
       private static void Exit(){
        saveData();
        System.out.println("Goodbye...");
        System.exit(0);
    }
}
