
package exceptionhandling;
import java.util.Scanner;
import java.util.InputMismatchException;
public class ExceptionHandling {

   public static int qiatient(int numreter, int denomineter)throws ArithmeticException{
            return numreter/denomineter;
   }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
       boolean continulooping = true;
       do{
           try{
               System.out.println("type the numreter! ");
               int num1 = scanner.nextInt();
               System.out.println("type the denomineter! ");
               int num2 = scanner.nextInt();
               int result = qiatient(num1 ,num2);
               System.out.println("Result:- " + num1 + "/"  + num2 + "= " + result);
               continulooping = false;
           }catch(ArithmeticException arithmeticException){
               System.out.println("ERROR: denomineter can't be Zero. division by zero is not allowed.");
                System.out.println("Please try again!");
                scanner.nextLine();
           }
           catch(InputMismatchException inputMismachException){
              System.out.println("invalid input! Please input only integer number.");
              scanner.nextLine();
           }
       }while(continulooping);
       scanner.close();
    }
    
}
