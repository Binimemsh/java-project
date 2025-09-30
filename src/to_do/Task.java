
package to_do;


public class Task {
    private final String title;
    private final String description;
    
    public Task(String title , String description){
            this.title = title;
            this.description = description;
    }
   
    public String getTitle(){
        return title;
    }
    public String getDescription(){
        return description;
    }
    @Override
    public String toString(){
            return title + ":- " + description;
    }
}
