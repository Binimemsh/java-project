
package studentgrade;

public class StudentInfo {
    private final String Name, ID;
    private final float CGPA;
    public StudentInfo(String Name, String ID, float CGPA){
               this.Name=Name;
               this.ID= ID;
               this.CGPA= CGPA;
    }
 
      public String getName(){
             return Name;
      }
      public String getID(){
             return ID;
      }
      public float getCGPA(){
             return CGPA;
      }
      @Override
    public String toString(){
            return String.format("Name: %s, ID: %s, CGPA: %.2f", Name, ID, CGPA);
    }
}
