package studentgrade;

import java.util.Scanner;
import java.util.ArrayList;
import java.io.*;

public class StudentGrade {
    private static final String File_path= "Stud.txt";
    private static final ArrayList<StudentInfo> dataList= new ArrayList<>();
    private static final Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        StudentGrade gradeSystem = new StudentGrade(); 
        loadData();
        while(true){
        System.out.println("====TO-DO MENU====");
        System.out.println("1. Add Information");
        System.out.println("2. List Student");
        System.out.println("3. Delete Student");
        System.out.println("4. Delete All Student");
        System.out.println("5. Exit");
        System.out.println("choose one of the above!");
        int choice = scanner.nextInt();
        scanner.nextLine();
        switch(choice){
            case 1 -> addInfo();
            case 2 -> viewAllStudent();
            case 3 -> deleteData();
            case 4 -> deleteAll();
            case 5 -> Exit();
            default -> System.out.println("invalid choice! please Try again.");
        }
        }     
    }
    private static void addInfo(){
            System.out.println("\n=== Add Student Information ===");
            System.out.println("if you don't have any data on the system. fill the following information!");
            System.out.print("Type your full name. ");
            String Name= scanner.nextLine();
            
            System.out.print("Type your ID. ");
            String ID= scanner.nextLine();
            
            System.out.print("Type your CGPA. ");
            float CGPA= Grade();
            
            
            dataList.add(new StudentInfo(Name, ID, CGPA));
            saveData();
            System.out.println(Name + " information is successfully add");
    }
    private static void viewAllStudent(){
               System.out.println("\n=== All Student ===");
               if(dataList.isEmpty()){
               System.out.println("No Student found.");
               return;
               }
               for(int i=0;i<dataList.size();i++){
                  StudentInfo  student=dataList.get(i);
                  System.out.println((i+1) + "." + student);
            }
    }
    private static void deleteData(){
               if(dataList.isEmpty()){
                System.out.println(" There is no student to delete.");
                return;
            }
               viewAllStudent();
               System.out.println("choose one you want to delete!");
               int index= scanner.nextInt()-1; 
               scanner.nextLine();
            if(index >= 0 && index < dataList.size()){
               StudentInfo removedStudent = dataList.remove(index);
               System.out.println(removedStudent + " is deleted");
               saveData();
            }else{
            System.out.println("invalid input!");
            }
    }
     private static void deleteAll(){
              if(dataList.isEmpty()){
                  System.out.println("There are no student to delete.");
                  return;
              }
              System.out.println("\n=== Delete All Students ===");
              System.out.println("This will Permanently delete all " + dataList.size()+ " Students!");
              System.out.println("Are You sure want to continue? (Yes/No)");
              String confirmation = scanner.nextLine().trim().toLowerCase();
              if(confirmation.equals("yes")|| confirmation.equals("y")){
                  int deletedCount = dataList.size();
                  dataList.clear();
                  saveData();
                  System.out.println("All" + deletedCount + "student have been deleted successfully.");
              }else{
                  System.out.println("Delete operation cancelled.");
              }
     }
    private static void saveData(){
      try{ BufferedWriter bw= new BufferedWriter(new FileWriter(File_path));
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
        File file = new File(File_path);
        if(!file.exists()){
            System.out.println("No existing data file found. Starting fresh.");
            return;
        }
        try(BufferedReader br= new BufferedReader(new FileReader(File_path))){
            String line;
            while((line = br.readLine()) != null) {
                String[] parts = line.split(":-",3);
                if (parts.length == 3){
                    try{ float CGPA = Float.parseFloat(parts[2]);
                dataList.add(new StudentInfo(parts[0], parts[1], CGPA));}catch(NumberFormatException e){
                    System.out.println("invalid CGPA value for :  " + parts[0]);
                }
            } else{ 
                  System.out.println("Skipping invalid info line:"+ line);
                }
            }
            System.out.println("Data loaded successfully! " + dataList.size() + " students found.");
          
        }catch(IOException e){
             System.out.println("Error loading data: " + e.getMessage());
        }catch (NumberFormatException e) {
            System.out.println("Error parsing CGPA data: " + e.getMessage());
        }
    }
       private static void Exit(){
        saveData();
        System.out.println("Goodbye...");
        System.exit(0);
    }
       private static float Grade(){
             float Sum=0;
           int totalCHs=0;
            System.out.print("How many course do you have? ");
            int course= scanner.nextInt();
            for(int i=1;i<=course;i++){
                 System.out.print("Subject:- ");
                 String subject = scanner.nextLine();
                 
                 //scanner.nextLine();
                 System.out.print("Grade:- ");
                 String Grade= scanner.nextLine().trim().toUpperCase();
                 
                 System.out.print("Credit Hs:- ");
                 int CHs= scanner.nextInt();
                 scanner.nextLine();
                 
                
                 
                 float GradeinFloat=switch(Grade){
                     case "A+","A"->4.0f;
                     case "A-" -> 3.75f;
                     case "B+" -> 3.5f;
                     case "B" -> 3.0f;
                     case "B-" -> 2.75f;
                     case "C+" -> 2.5f;
                     case "C" ->2.0f;
                     case "C-" -> 1.75f;
                     case "D" -> 1;
                     case "F" -> 0.0f;
                     default -> {
                         System.out.println("invalid input. please try again! ");
                         i--;
                         yield 0f;
                     }
                 };
                         totalCHs+= CHs;
                        //float  CHsByGrade = GradeinFloat * CHs;
                          Sum += GradeinFloat * CHs;
                          //CHsByGrade=0;
            }
           // float CGPA=Sum/totalCHs;
            return (totalCHs == 0)? 0 : Sum / totalCHs;
            
       }
}
